
package kosta.namtang.talkbook.service.bill;

import kosta.namtang.talkbook.common.CancelCode;
import kosta.namtang.talkbook.common.GlobalException;
import kosta.namtang.talkbook.common.PurchaseCode;
import kosta.namtang.talkbook.common.StatusCode;
import kosta.namtang.talkbook.core.bill.response.payco.OrderStatus;
import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.model.domain.bill.*;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import kosta.namtang.talkbook.model.dto.request.Refund;
import kosta.namtang.talkbook.model.dto.request.RefundRequest;
import kosta.namtang.talkbook.model.dto.response.OrderStatusResponse;
import kosta.namtang.talkbook.model.dto.response.PurchaseBookResponse;
import kosta.namtang.talkbook.model.dto.response.PurchaseOrderResponse;
import kosta.namtang.talkbook.repository.ReviewRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseBookRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseCancelRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseOrderRepository;
import kosta.namtang.talkbook.repository.bill.PurchasePaymentRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    @Transactional
    public BillKey insertPurchase(List<PurchaseBook> booksList, PurchaseOrder order,
                                  PurchasePayment payment, long accountIdx, String key) throws Exception {

        BillKey keyResult = null;
        try {

            // [0] 빌키 받아오기 //uuid
            //String key = keySystem.issueBillKey();
            // 시간 동기화
            Timestamp purchaseDate = DateTimeHelper.timeStampNow();

            // [1] 계정 정보 알아오기 (로그인 세션에서 알수있음)
            //long accountIdx = account.getAccountIdx();

            // 주문정보 셋팅
            order.setUserIdx(accountIdx);
            order.setStateCode(PurchaseCode.Payment_Success);
            order.setOrderDate(purchaseDate);
            //order.setCreateDate(purchaseDate);
            order.setUpdateDate(purchaseDate);
            order.setBillKey(key);

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
            for (PurchaseBook book : booksList) {
                book.setPurchaseBookId(new PurchaseBookId(book.getPurchaseBookId().getBookIdx(), orderIdx));
                book.setStateCode(PurchaseCode.Payment_Success);
                book.setBillKey(key);
                book.setCreateDate(purchaseDate);
                book.setUpdateDate(purchaseDate);

                PurchaseBook purchaseBookResult = purchaseBook.save(book);
                if (purchaseBookResult == null) {
                    // 완전 존망 ㅠㅜㅜ
                    throw new GlobalException("구매상품 입력실패", StatusCode.Fail_Add_PurchaseGoods);
                }
            }

            // 여기 까지 문제가 없다면
            // [5] billkey table에 완료시간을 기록하기
            keyResult = keySystem.registerPurchase(key, purchaseDate);
            /////////////////////////////////////////////////////////////////////// 트랜잭션 종료 시키기

            // [6] 구매 완료 시키기
            if (keyResult != null) {
                return keyResult;
            } else {
                throw new GlobalException("구매 진행을 취소시킵니다", StatusCode.Fail_Issue_BillKey);
            }

        } catch (Exception e) {
            // 임시로
            e.printStackTrace();
        }
        return keyResult;

    }

    @Override
    @Transactional
    public BillKey refund(List<Refund> list) throws Exception {
        //String billKey, String reason, int refundCode, List<PurchaseBook> cancelBookList
        try {

            //구매당시의 Billkey를 가져 옴
            // 빌키 검증이 필요할까???
            Timestamp cancelDate = DateTimeHelper.timeStampNow();
            long oderIdx = list.get(0).getOrderIdx();
            //해당 BillKey의 Basket 정보를 가져옴

            PurchaseOrder order = purchaseOrder.findById(oderIdx).orElse(null);
            order.setStateCode(PurchaseCode.Cancel_Ing);
            PurchaseOrder purchaseOrderResult = purchaseOrder.save(order);
            //해당 BillKey(basketId )의 Payment 정보를 가져옴
            //(취소할 때 refund type을 넣도록 변경)
            //PurchaseBasketPaymentDTO payment = dao.selectPayment(basket.getBasketId());

            if (purchaseOrderResult == null) {
                throw new GlobalException("환불 상품 수정 실패", StatusCode.Fail_Update_BaksetState);
            }

            for (Refund r : list) {

//                //해당 Basket 정보의 Goods를 가져옴
//                // 단, 여기서 전체환불인지 부분환불인지 구분한다
//                List<PurchaseBook> booksList = new ArrayList<PurchaseBook>();
//                if (cancelBookList.size() == 0) {
//                    booksList = purchaseBook.findByPurchaseBookIdPurchaseOrderIdx(order.getPurchaseOrderIdx());
//                } else {
//                    for (PurchaseBook cancelBook : cancelBookList) {
//                        PurchaseBook book = purchaseBook.findByPurchaseBookIdBookIdx(cancelBook.getPurchaseBookId().getBookIdx());
//                        book.setCount(cancelBook.getCount());
//                        booksList.add(book);
//                    }
//                }


                // 아래 부터는 환불 데이터를 수정 또는 입력하는 단계이다 즉 List<PurchaseGoodsDTO> goodsList 를 처리한다
                //if (booksList.size() > 0) {

                //Basket 상태 정보를 update


                Optional<PurchaseBook> book = purchaseBook.findByPurchaseBookId(new PurchaseBookId(r.getBookIdx(), r.getOrderIdx()));
                //book.setCount(r.getBookCount());

//                    //해당 Basket의 Goods 상태 정보를 update
//                    for (PurchaseBook book : booksList) {
                book.get().setStateCode(PurchaseCode.Cancel_Ing);

                // 수량만 부분 환불할 경우 대처가 필요하다 (정책상 아예 환불 후 재구매를 할지 아니면 부분 환불을 수량까지 지원할지 정책이 필요함)
                // 세부 정책이 없기 때문에 수량 환불은 구매 수정으로 따로 API를 만들어 지원한다 / 옥션의 경우 수량 수정은 없고 배송정보 수정만 있다
                PurchaseBook purchaseBookResult = purchaseBook.save(book.get());
                if (purchaseBookResult == null) {
                    // 완전 존망 ㅠㅜㅜ
                    throw new GlobalException("환불 상품 수정 실패", StatusCode.Fail_Update_RefundState);
                }

                // 아래와 같이 1:1 로 맵핑되는 부분은 procedure로 하면 좋을 듯
                PurchaseCancel cancel = new PurchaseCancel();
                cancel.setBillKey(book.get().getBillKey());
                cancel.setPurchaseBookId(new PurchaseBookId(book.get().getPurchaseBookId().getBookIdx(), order.getPurchaseOrderIdx()));
                //cancel.setPurchaseGoodsId(goods.getPurchaseGoodId());
                cancel.setReason(r.getReason());
                CancelCode cc =  CancelCode.fromInteger(r.getRefundCode());
                cancel.setCancelCode(cc);
                cancel.setCreateDate(cancelDate);

                cancel.setUpdateDate(cancelDate);
                cancel.setCount(r.getBookCount());
                cancel.setPrice(book.get().getPrice());

                //해당 Goods의 Cancel 정보를 insert
                PurchaseCancel cancelResult = purchaseCancel.save(cancel);
                if (cancelResult == null) {
                    throw new GlobalException("환불 상품 수정 실패", StatusCode.Fail_Add_CancelGoods);
                }
            }

            //해당 Billkey의 Cancel Date를 update
            BillKey keyResult = keySystem.registerCancel(order.getBillKey(), cancelDate);

            //환불 완료 후 환불완료 페이지 이동
            if (keyResult != null) {
                return keyResult;
            } else {
                throw new GlobalException("환불 진행을 취소시킵니다", StatusCode.Fail_Cancel_BillKey);
            }


            //}


        } catch (Exception e) {
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
    public List<PurchaseOrderResponse> selectOrderList(long userIdx) throws Exception {

        List<PurchaseOrderResponse> list = new ArrayList<>();
        try {

            List<PurchaseOrder> orderList = purchaseOrder.findByUserIdx(userIdx);
            for (PurchaseOrder order : orderList) {

                PurchasePayment payment = purchasePayment.findByPurchaseOrderIdx(order.getPurchaseOrderIdx());

                List<PurchaseBook> bookList = purchaseBook.findByPurchaseBookIdPurchaseOrderIdx(order.getPurchaseOrderIdx());
                String productName = bookList.get(0).getName();
                if (bookList.size() > 1) {
                    //일단 대충 더하기
                    productName = productName + "외 " + String.valueOf(bookList.size() - 1) + "권";
                }

                //dto 사용
                PurchaseOrderResponse response = new PurchaseOrderResponse();
                response.setOrderDate(DateTimeHelper.sqlDate(order.getOrderDate().getTime()));
                response.setBillKey(order.getBillKey().substring(0, 8));
                response.setTotalPrice(payment.getTotalPrice());
                response.setReceiveName(payment.getReceiverName());
                response.setProductName(productName);
                response.setOrderIdx(order.getPurchaseOrderIdx());
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

        } catch (Exception e) {
            // 위의 쿼리에서 문제가 없다면 모든 구매 정보를 보낸다
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public OrderStatusResponse selectOrderStatus(long orderIdx) throws Exception {
        Optional<PurchaseOrder> order = purchaseOrder.findById(orderIdx);
        Optional<PurchasePayment> payment = purchasePayment.findById(orderIdx);
        OrderStatusResponse response = new OrderStatusResponse();
        order.ifPresent(o -> {
            response.setAddress(o.getDeliveryAddress());
            response.setPhone(payment.get().getReceiverPhone());

            response.setPurchaseStatus(o.getStateCode().name());
        });
        return response;
    }

    @Override
    public List<PurchaseBookResponse> selectOrderDetail(long orderIdx, long userIdx) throws Exception {
        List<PurchaseBook> books = purchaseBook.findByPurchaseBookIdPurchaseOrderIdx(orderIdx);
        List<PurchaseBookResponse> responses = new ArrayList<>();
        for (PurchaseBook book : books) {
            PurchaseBookResponse item = new PurchaseBookResponse();
            item.setBookName(book.getName());
            item.setCount(book.getCount());
            item.setPrice(book.getPrice());
            item.setBillKey(book.getBillKey());
            item.setState(book.getStateCode().name());
            item.setBookIdx(book.getPurchaseBookId().getBookIdx());
            List<Review> review = reviewRepository.findByUserIdxAndBookIdx(userIdx, book.getPurchaseBookId().getBookIdx());
            if (review.size() > 0) {
                item.setReview(true);
            } else {
                item.setReview(false);
            }
            responses.add(item);
        }

        return responses;
    }

    @Override
    @Transactional
    public boolean selectPurchaseStatus(long bookIdx, long userIdx) throws Exception {

        List<PurchaseOrder> orderList = purchaseOrder.findByUserIdx(userIdx);
        for (PurchaseOrder order : orderList) {
            long orderIdx = order.getPurchaseOrderIdx();
            PurchaseBook book = purchaseBook.findByPurchaseBookId(new PurchaseBookId(bookIdx, orderIdx)).get();
            if (book != null) {
                return true;
            }
        }
        return false;
    }
}
