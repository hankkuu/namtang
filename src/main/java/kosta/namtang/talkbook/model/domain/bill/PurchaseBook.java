package kosta.namtang.talkbook.model.domain.bill;

import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseBook {

    @EmbeddedId
    private PurchaseBookId purchaseBookId;

	private BigDecimal price;
	private int stateCode;
	private String name;
    private int count;
    private String imagePath;

	private String billKey;
	//private long bookIdx;

    private Date createDate;
    private Date updateDate;
    
    //private PurchaseOrder purchaseOrder;
    //private PurchaseCancel cancel;
    
}
