package kosta.namtang.talkbook.model.dto.response;

import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import lombok.Data;

@Data
public class PurchaseOrderStatReponse extends PurchaseOrder {
	String StatusName;
}
