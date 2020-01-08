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
@AllArgsConstructor
public class PurchaseBook {

    @Id
    //private long purchaseBookIdx;
    private long bookIdx;
    @Id
    private long purchaseOrderIdx;

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
