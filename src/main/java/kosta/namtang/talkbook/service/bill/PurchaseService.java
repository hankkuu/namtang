
package kosta.namtang.talkbook.service.bill;

import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.model.domain.bill.PurchaseBook;
import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.dto.response.PurchaseOrderResponse;

import java.util.List;


public interface PurchaseService {

    /**
     * 상품 구매 (바스켓은 왜 DTO가 아니지??? PurchaseBasketPayment 도. 네이밍 수정 부탁)
     * 아래 파라미터는 화면에서 오는 정보로 내부에서 사용하기 편하게 변경될 수 있음
     *
     * @throws Exception
     */

    BillKey insertPurchase(List<PurchaseBook> goodsList, PurchaseOrder order,
                           PurchasePayment payment, Users user, String billKey) throws Exception;

    // 환불
    BillKey refund(String billKey, String reason, int refundType, List<PurchaseBook> cancelBookList) throws Exception;

    // 구매 수정
    BillKey changePurchase(String billKey, List<PurchaseBook> purchaseGoodsList) throws Exception;

    // 구매 내역 조회
    List<PurchaseOrderResponse> selectOrderList(Users account) throws Exception;

    /**
     * 장바구니에서 구매 넘어갈때 들고갈 상품 정보(조회)
     * 주문자 정보 조회
     * 같이 뿌려주는 작업
     * */
    //PurchasePage selectProduct(String loginId, String billKey) throws Exception;


    /**
     * 구매내역 조회
     *
     * */
    boolean selectPurchaseStatus(long book, long userIdx) throws Exception;


}
