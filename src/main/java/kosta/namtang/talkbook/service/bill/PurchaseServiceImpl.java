
package kosta.namtang.talkbook.service.bill;

import kosta.namtang.talkbook.common.GlobalException;
import kosta.namtang.talkbook.common.PurchaseCode;
import kosta.namtang.talkbook.common.StatusCode;
import kosta.namtang.talkbook.common.bill.BillKeySystem;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.bill.*;
import kosta.namtang.talkbook.model.domain.User;
import kosta.namtang.talkbook.model.dto.response.PurchaseOrderResponse;
import kosta.namtang.talkbook.repository.bill.PurchaseBookRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseCancelRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseOrderRepository;
import kosta.namtang.talkbook.repository.bill.PurchasePaymentRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class PurchaseServiceImpl implements PurchaseService {
	// 필요한 dAO 목록.....
	@Autowired
	private BillKeySystem keySystem;
	@Autowired
	private PurchaseOrderRepository purchaseOrder;
	@Autowired
	private PurchasePaymentRepository purchasePayment;
	@Autowired
	private PurchaseBookRepository purchaseBook;
	@Autowired
	private PurchaseCancelRepository purchaseCancel;
	
	
	@Override
	@Transactional
	public BillKey insertPurchase(List<Book> booksList, PurchaseOrder order,
							  PurchasePayment payment, User account) throws Exception{
		try {
			
			// [0] 빌키 받아오기 //uuid
			String key = keySystem.issueBillKey();
			// 시간 동기화
			Date purchaseDate = DateTimeHelper.sqlDateNow();

			// [1] 계정 정보 알아오기 (로그인 세션에서 알수있음)
			long userIdx = account.getUserIdx();

			// 주문정보 셋팅
			order.setUserIdx(userIdx);
			order.setStateCode(PurchaseCode.Payment_Success.getValue());
			order.setOrderDate(purchaseDate);
			//order.setCreateDate(purchaseDate);
			order.setUpdateDate(purchaseDate);

			///////////////////////////////////////////// 트랜잭션 시작
			// [2] basket 정보를 넣어주기 (seq로 return을 받는것이 가장 좋다)
			PurchaseOrder orderResult = purchaseOrder.save(order);
			if (orderResult == null) {
				throw new GlobalException("장바구니 입력실패", StatusCode.Fail_Add_PurchseBasket);
			}
			// 이 부분이 꼬였다.. basketId가 자동증가 값이라. 다시 select를 해야 한다 ㅠㅠㅠ
			// billkey로 pk를 잡는게 나을지도 모르겠다.
			// 1:1 관계는 join으로 해결해야 하는데 일단 급하니 join은 패스한다
			long orderIdx = orderResult.getPurchaseOrderIdx();

			// 받는사람 정보 삽입
			payment.setPurchaseOrderIdx(orderIdx);
			payment.setCreateDate(purchaseDate);
			payment.setUpdateDate(purchaseDate);
			// [3] 구매 payment 정보 넣어주기
			PurchasePayment paymentResult = purchasePayment.save(payment);
			if (paymentResult == null) {
				throw new GlobalException("결제정보 입력실패", StatusCode.Fail_Add_PurchasePayment);
			}
			// [4] 구매할 상품 정보 넣어 주기 (아마 insert?)
			// 벌그인서트가 없다.. 아씨...ㅋㅋㅋ 아아아ㅏ아아ㅏ아얼닝런이런이러 일단 무식하게(추후 수정)
			for (Book book : booksList) {

				PurchaseBook pBook = new PurchaseBook(book.getBookIdx(), orderIdx, book.getBookPrice(), PurchaseCode.Payment_Success.getValue()
						,  book.getBookTitle(), book.getBookCount(), book.getBookImg(), key, purchaseDate, purchaseDate);

				PurchaseBook purchaseBookResult = purchaseBook.save(pBook);
				if (purchaseBookResult == null) {
					// 완전 존망 ㅠㅜㅜ
					throw new GlobalException("구매상품 입력실패", StatusCode.Fail_Add_PurchaseGoods);
				}
			}

			// 여기 까지 문제가 없다면
			// [5] billkey table에 완료시간을 기록하기
			BillKey keyResult = keySystem.registerPurchase(key, purchaseDate);
			/////////////////////////////////////////////////////////////////////// 트랜잭션 종료 시키기 

			// [6] 구매 완료 시키기
			if (keyResult != null) {
				return keyResult;
			} else {
				throw new GlobalException("구매 진행을 취소시킵니다", StatusCode.Fail_Issue_BillKey);
			}

		} catch(Exception e) {
			// 임시로 
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	@Transactional
	public BillKey refund(String billKey, String reason, int refundCode, List<Long> purchaseBookIdsList) throws Exception {
		
		try {
			//구매당시의 Billkey를 가져 옴
			// 빌키 검증이 필요할까??? 
			Date cancelDate = DateTimeHelper.sqlDateNow();
			
			//해당 BillKey의 Basket 정보를 가져옴
			PurchaseOrder order = purchaseOrder.findByBillKey(billKey);
			//해당 BillKey(basketId )의 Payment 정보를 가져옴 
			//(취소할 때 refund type을 넣도록 변경)
			//PurchaseBasketPaymentDTO payment = dao.selectPayment(basket.getBasketId());
			
			//해당 Basket 정보의 Goods를 가져옴
			// 단, 여기서 전체환불인지 부분환불인지 구분한다
			List<PurchaseBook> booksList = new ArrayList<PurchaseBook>();
			if(purchaseBookIdsList.size() == 0) {
				booksList = purchaseBook.findByPurchaseOrderIdx(order.getPurchaseOrderIdx());
			} else {
				for(long purchaseBookId : purchaseBookIdsList) {
					PurchaseBook cancelGoods = purchaseBook.findByBookIdx(purchaseBookId);
					booksList.add(cancelGoods);
				}
			}

			// 아래 부터는 환불 데이터를 수정 또는 입력하는 단계이다 즉 List<PurchaseGoodsDTO> goodsList 를 처리한다
			if(booksList.size() > 0) {

				//Basket 상태 정보를 update 
				order.setStateCode(PurchaseCode.Cancel_Ing.getValue());
				PurchaseOrder purchaseOrderResult = purchaseOrder.save(order);
				if(purchaseOrderResult == null) {
					throw new GlobalException("환불 상품 수정 실패", StatusCode.Fail_Update_BaksetState);
				}
				
				//해당 Basket의 Goods 상태 정보를 update
				for(PurchaseBook book : booksList) {
					book.setStateCode(PurchaseCode.Cancel_Ing.getValue());
					
					// 수량만 부분 환불할 경우 대처가 필요하다 (정책상 아예 환불 후 재구매를 할지 아니면 부분 환불을 수량까지 지원할지 정책이 필요함)
					// 세부 정책이 없기 때문에 수량 환불은 구매 수정으로 따로 API를 만들어 지원한다 / 옥션의 경우 수량 수정은 없고 배송정보 수정만 있다
					PurchaseBook purchaseBookResult = purchaseBook.save(book);
					if (purchaseBookResult == null) {
						// 완전 존망 ㅠㅜㅜ
						throw new GlobalException("환불 상품 수정 실패", StatusCode.Fail_Update_RefundState);
					}
					
					// 아래와 같이 1:1 로 맵핑되는 부분은 procedure로 하면 좋을 듯
					PurchaseCancel cancel = new PurchaseCancel();
					cancel.setBillKey(billKey);
					cancel.setCancelBookIdx(book.getBookIdx());
					cancel.setPurchaseOrderIdx(order.getPurchaseOrderIdx());
					//cancel.setPurchaseGoodsId(goods.getPurchaseGoodId());
					cancel.setReason(reason);
					cancel.setRefundCode(refundCode);
					
					//해당 Goods의 Cancel 정보를 insert
					PurchaseCancel cancelResult = purchaseCancel.save(cancel);
					if(cancelResult == null) {
						throw new GlobalException("환불 상품 수정 실패", StatusCode.Fail_Add_CancelGoods);
					}
				}
				
				//해당 Billkey의 Cancel Date를 update 
				BillKey keyResult = keySystem.registerCancel(billKey, cancelDate);
				
				//환불 완료 후 환불완료 페이지 이동  
				if (keyResult != null) {
					return keyResult;
				} else {
					throw new GlobalException("환불 진행을 취소시킵니다", StatusCode.Fail_Cancel_BillKey);
				}
				
			} else {
				throw new GlobalException("환불 진행할 상품이 없습니다", StatusCode.Fail_Not_Exist_Goods);
			}

		}catch(Exception e) {
			// 임시 트랜잭션
			e.printStackTrace();
		}
		return null;
				
	}

	@Override
	public BillKey changePurchase(String billKey, List<PurchaseBook> purchaseGoodsList) throws Exception {
		// 신발과 같은 경우는 수량이나 사이즈 수정이 필요해 보인다 
		// 배송정보의 수정과는 다르게 처리할지 정책이 필요하다
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<PurchaseOrderResponse> selectPurchaseDetail(User account) throws Exception {

		List<PurchaseOrderResponse> list = new ArrayList<>();
		try {

			List<PurchaseOrder> orderList = purchaseOrder.findByUserIdx(account.getUserIdx());
			for(PurchaseOrder order : orderList) {
				
				PurchasePayment payment = purchasePayment.findByPurchaseOrderIdx(order.getPurchaseOrderIdx());

				//dto 사용
				PurchaseOrderResponse response = new PurchaseOrderResponse();
				response.setPayment(payment);
				list.add(response);
				
//				List<PurchaseBook> purchaseGoodsList = purchaseBook.findByOrderIdx(order.getPurchaseOrderIdx());
//				for(PurchaseBook purchaseGoods : purchaseGoodsList) {
//					int state = purchaseGoods.getStateCode();
//					switch(state) {
//						case PurchaseCode.Cancel_Ing.getValue(): {
//							PurchaseCancel cancel = purchaseCancel.selectCancel(purchaseGoods.getPurchaseGoodId());
//							purchaseGoods.setCancel(cancel);
//							break;
//						}
//						case PurchaseCode.Cancel_Success.getValue(): {
//							//PurchaseCancel cancel = purchaseCancel.selectCancel(purchaseGoods.getPurchaseGoodId());
//							//purchaseGoods.setCancel(cancel);
//							break;
//						}
//					}
//				}
//				order.setPurchaseGoodList(purchaseGoodsList);
			}

			return list;

		}catch(Exception e) {
			// 위의 쿼리에서 문제가 없다면 모든 구매 정보를 보낸다 
			e.printStackTrace();
		}
		
		return null;
	}
}