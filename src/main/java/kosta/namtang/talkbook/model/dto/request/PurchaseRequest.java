package kosta.namtang.talkbook.model.dto.request;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.bill.PurchaseBook;
import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PurchaseRequest {

	private String imp_uid;
	private String merchant_uid;
	private String billKey;
	private String paid_amount;


	//	private long bookIdx;
//	private String bookName;
//	private int bookCount;
//	private BigDecimal price;
	private List<Book> book;
	
	//private String imagebox;
	//private BigDecimal totalPricae;
	//private BigDecimal shippingPrice;
	//private int paymentCode;
	//private String

	private PurchaseOrder order;
	private PurchasePayment payment;


}
