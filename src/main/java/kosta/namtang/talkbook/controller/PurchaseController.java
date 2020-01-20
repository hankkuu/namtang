package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.common.ShopResponse;
import kosta.namtang.talkbook.common.StatusCode;
import kosta.namtang.talkbook.core.bill.IamportClient;
import kosta.namtang.talkbook.core.bill.exception.IamportResponseException;
import kosta.namtang.talkbook.core.bill.request.CancelData;
import kosta.namtang.talkbook.core.bill.response.*;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.model.dto.request.PurchaseRequest;
import kosta.namtang.talkbook.model.dto.request.PurchaseSetRequest;
import kosta.namtang.talkbook.model.dto.response.OrderStatusResponse;
import kosta.namtang.talkbook.model.dto.response.PurchaseBookResponse;
import kosta.namtang.talkbook.model.dto.response.PurchaseOrderResponse;
import kosta.namtang.talkbook.service.bill.BillKeySystem;
import kosta.namtang.talkbook.service.bill.PurchaseService;
import kosta.namtang.talkbook.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/purchase")
public class PurchaseController {


    private IamportClient client = new IamportClient("3310717107944454"
            , "TYVkJ2x8nJd4QZeVJ9oeyWHGkSxzHmdBl9V5ctdzcrE3qSYa2npAj7gEZOeHbsiwBeCNHPnUh1XIUWSW");

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BillKeySystem keySystem;


    @GetMapping("/myPurchase")
    public ShopResponse getMyPurchase(HttpServletRequest request) throws Exception {
        Object obj = request.getSession().getAttribute("userIdx");
        long userIdx = Long.valueOf(String.valueOf(obj));

        List<PurchaseOrderResponse> list = purchaseService.selectOrderList(1);

        return new ShopResponse(StatusCode.Success, JsonUtil.toJson(list));
    }

    @GetMapping("/myPurchaseStatus")
    public ShopResponse myPurchaseStatus(@RequestParam long id) throws Exception {

        OrderStatusResponse res = purchaseService.selectOrderStatus(id);

        return new ShopResponse(StatusCode.Success, JsonUtil.toJson(res));
    }

    @GetMapping("/myPurchaseDetail")
    public ShopResponse myPurchaseDetail(@RequestParam long orderId, HttpServletRequest request) throws Exception {
        Object obj = request.getSession().getAttribute("userIdx");
        long userIdx = Long.valueOf(String.valueOf(obj));

        List<PurchaseBookResponse> res = purchaseService.selectOrderDetail(orderId, userIdx);

        return new ShopResponse(StatusCode.Success, JsonUtil.toJson(res));
    }




    @PostMapping("/getBillKey")
    public ShopResponse getBillKey() throws Exception {
        String key = keySystem.issueBillKey();

        if (key.isEmpty() == false) {
            return new ShopResponse(StatusCode.Success, key);
        } else {
            throw new Exception();
        }
    }

    @GetMapping("/status")
    public ShopResponse purchaseStatus(@RequestParam long bookIdx, HttpServletRequest req) throws Exception {
        Object obj = req.getSession().getAttribute("userIdx");
        if(obj == null) {
            return new ShopResponse(StatusCode.Fail, "로그인한 사용자만 리뷰를 쓸수있습니다");
        }

        long userIdx = Long.valueOf(String.valueOf(obj));
        log.debug("purchaseStatus");
        boolean result = purchaseService.selectPurchaseStatus(bookIdx, userIdx);
        if(result == true) {
            return new ShopResponse(StatusCode.Success, "구매내역이 있는 책 리뷰작성 가능");
        } else {
            return new ShopResponse(StatusCode.Fail, "구매내역이 없습니다");
        }
    }

    @PostMapping("")
    public ShopResponse purchase(HttpServletRequest req, @RequestBody PurchaseSetRequest request) throws Exception {
        Object obj = req.getSession().getAttribute("userIdx");
        ShopResponse result = null;
        log.debug(request.toString());

        BillKey key = purchaseService.insertPurchase(request.getBook(), request.getOrder(), request.getPayment()
                , request.getUser(), request.getBillKey());

        if (key != null) {
            // 구매 DB 입력 완료
            result = new ShopResponse(StatusCode.Success, JsonUtil.toJson(key));
        } else {
            throw new Exception();
        }

        return result;
    }

    @PostMapping("/complete")
    public ShopResponse purchaseComplete(@RequestBody PurchaseRequest request, Users user) throws Exception {

        ShopResponse result = null;
        String token = this.getToken();
        String paymentData = this.purchaseByImpUid(request.getImp_uid());

        String total = request.getPaid_amount();
        Payment payment = JsonUtil.fromJson(paymentData, Payment.class);
        if (total.equals(payment.getAmount().toString())) {
            // 결제 검증 성공
            String status = payment.getStatus();
            if (status.equals("ready")) {
                log.debug("ready");
            } else if (status.equals("paid")) {
                log.debug("paid");

                result = new ShopResponse(StatusCode.Success, paymentData);
            } else {
                throw new Exception();
            }
        } else {
            // 결제 검증 실패
            throw new Exception();
        }
        return result;
    }

    @PostMapping("/getToken")
    public String getToken() throws IOException, IamportResponseException {

        IamportResponse<AccessToken> auth_response = client.getAuth();
        String token = auth_response.getResponse().getToken();
        log.debug(token);

        return JsonUtil.toJson(auth_response.getResponse());
    }

    @GetMapping("/balance/{imp_uid}")
    public String balanceByImpUid(@PathVariable String imp_uid) throws IOException, IamportResponseException {

        IamportResponse<PaymentBalance> payment_response = client.paymentBalanceByImpUid(imp_uid);
        log.debug(payment_response.getResponse().toString());

        return JsonUtil.toJson(payment_response.getResponse());
    }

    @GetMapping("/cancel/{imp_uid}")
    public String cancelByImpUid(@PathVariable String imp_uid) throws IOException, IamportResponseException {

        IamportResponse<Payment> canceled_response = client.paymentByImpUid(imp_uid);
        PaymentCancelDetail[] cancelDetail = canceled_response.getResponse().getCancelHistory();
        log.debug(String.valueOf(cancelDetail.length));
        log.debug(cancelDetail[0].getPgTid());

        return JsonUtil.toJson(cancelDetail);
    }

    @GetMapping("/{imp_uid}")
    public String purchaseByImpUid(@PathVariable String imp_uid) throws IOException, IamportResponseException {

        IamportResponse<Payment> payment_response = client.paymentByImpUid(imp_uid);
        log.debug(payment_response.getResponse().toString());

        return JsonUtil.toJson(payment_response.getResponse());
    }

    // ready , paid, failed , canceled, all
    @GetMapping("status/{payment_status}")
    public String purchaseStatus(@PathVariable String payment_status) throws IOException, IamportResponseException {
        IamportResponse<PagedDataList<Payment>> response = client.paymentsByStatus(payment_status);
        log.debug(JsonUtil.toJson(response.getResponse()));
        return JsonUtil.toJson(response.getResponse());
    }

    // imp_id를 통한 전액 취소
    @PostMapping("/impCancel")
    public String impCancel(@RequestBody String imp_uid) throws IOException, IamportResponseException {
        CancelData cancel = new CancelData(imp_uid, true);
        IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel);

        log.debug(payment_response.getResponse().toString());
        return JsonUtil.toJson(payment_response.getResponse());
    }

    // merchant_uid를 통한 전액 취소
    @PostMapping("/merchantCancel")
    public String cancel(@RequestBody String merchant_uid) throws IOException, IamportResponseException {
        CancelData cancel = new CancelData(merchant_uid, true);
        IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel);

        log.debug(payment_response.getResponse().toString());
        return JsonUtil.toJson(payment_response.getResponse());
    }

    // 부분 취소는 일단 보류
    //

    // 본인인증??
    @GetMapping("/certification/{imp_uid}")
    public String certification(@PathVariable String imp_uid) throws IOException, IamportResponseException {
        IamportResponse<Certification> response = client.certificationByImpUid(imp_uid);
        log.debug(response.getResponse().toString());
        return JsonUtil.toJson(response.getResponse());
    }

}
