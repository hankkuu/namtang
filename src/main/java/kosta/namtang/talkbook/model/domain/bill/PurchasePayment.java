package kosta.namtang.talkbook.model.domain.bill;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PurchasePayment {

	@Id
	private long purchaseOrderIdx;
	@Column(name = "TOTAL_PRICE", nullable = false)
	private BigDecimal totalPrice;
	@Column(name = "SHIPPING_PRICE", nullable = false)
	private BigDecimal shippingPrice;
	private int paymentCode;
	@Column(name = "RECEIVER_NAME", nullable = false)
	private String receiverName;
	@Column(name = "RECEIVER_PHONE", nullable = false)
	private String receiverPhone;
	@Column(name = "DELIVERY_COMMENT", nullable = false)
	private String deliveryComment;
	@Column(name = "CREATE_DATE", nullable = false)
	private Timestamp createDate;
	@Column(name = "UPDATE_DATE", nullable = false)
	private Timestamp updateDate;
}
