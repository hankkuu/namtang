package kosta.namtang.talkbook.model.domain.bill;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchasePayment {

	@Id
	private long purchaseOrderIdx;
	private BigDecimal totalPrice;
	private BigDecimal shippingPrice;
	private int paymentCode;
	private String receiverName;
	private String receiverPhone;
	private String deliveryComment;
	private Date createDate;
	private Date updateDate;
}
