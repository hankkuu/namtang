package kosta.namtang.talkbook.model.dto.request;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PurchaseSetRequest {

	private String imp_uid;
	private String merchant_uid;

	private String billKey;
	private List<Book> book;
	private PurchaseOrder order;
	private PurchasePayment payment;

}
