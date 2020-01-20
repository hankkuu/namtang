package kosta.namtang.talkbook.model.domain.bill;

import kosta.namtang.talkbook.common.PurchaseCode;
import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Purchase_Book")
public class PurchaseBook {

    @EmbeddedId
    private PurchaseBookId purchaseBookId;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;
    @Column(name = "STATE_CODE", nullable = false)
    private PurchaseCode stateCode;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "COUNT", nullable = false)
    private int count;
    @Column(name = "IMAGE_PATH", nullable = false)
    private String imagePath;

    @Column(name = "BILL_KEY", nullable = false)
    private String billKey;
    //private long bookIdx;

    @Column(name = "CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name = "UPDATE_DATE", nullable = false)
    private Timestamp updateDate;

    //private PurchaseOrder purchaseOrder;
    //private PurchaseCancel cancel;

}
