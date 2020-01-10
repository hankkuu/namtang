package kosta.namtang.talkbook.model.dto.response;

import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import lombok.Data;

@Data
public class PurchaseOrderResponse {

    private PurchaseOrder order;
    private PurchasePayment payment;

}
