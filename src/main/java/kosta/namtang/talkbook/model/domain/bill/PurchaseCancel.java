package kosta.namtang.talkbook.model.domain.bill;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PurchaseCancel {

	//@Id
	//private long cancelBookIdx;
	@Id
	private long cancelBookIdx;
	@Id
	private long purchaseOrderIdx;
	private BigDecimal price;
	private String reason;
	private int count;
	private int refundCode;
	private Date createDate;
	private Date updateDate;
	private String billKey;

}
