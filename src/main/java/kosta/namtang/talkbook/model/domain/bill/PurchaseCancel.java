package kosta.namtang.talkbook.model.domain.bill;

import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PurchaseCancel {

	@EmbeddedId
	private PurchaseBookId purchaseBookId;

	@Column(name = "PRICE", nullable = false)
	private BigDecimal price;
	@Column(name = "REASON", nullable = false)
	private String reason;
	private int count;
	private int refundCode;
	@Column(name = "CREATE_DATE", nullable = false)
	private Timestamp createDate;
	@Column(name = "UPDATE_DATE", nullable = false)
	private Timestamp updateDate;
	@Column(name = "BILL_KEY", nullable = false)
	private String billKey;

}
