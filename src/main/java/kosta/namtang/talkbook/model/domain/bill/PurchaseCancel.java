package kosta.namtang.talkbook.model.domain.bill;

import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PurchaseCancel {

	@EmbeddedId
	private PurchaseBookId purchaseBookId;

	private BigDecimal price;
	private String reason;
	private int count;
	private int refundCode;
	private Date createDate;
	private Date updateDate;
	private String billKey;

}
