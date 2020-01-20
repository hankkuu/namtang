package kosta.namtang.talkbook.model.domain.bill;

import kosta.namtang.talkbook.common.PurchaseCode;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_SEQ_NO")
    @SequenceGenerator(sequenceName = "ORDER_SEQ_NO", name = "ORDER_SEQ_NO", allocationSize = 1)
    private long purchaseOrderIdx;
    private int stateCode;
    @Column(name = "DELIVERY_ADDRESS", nullable = false)
    private String deliveryAddress;
    @Column(name = "ORDER_DATE", nullable = false)
    private Timestamp orderDate;
    @Column(name = "UPDATE_DATE", nullable = false)
    private Timestamp updateDate;
    @Column(name = "BILL_KEY", nullable = false)
    private String billKey;
    private long userIdx;

    //private PurchasePayment payment;
    //private List<PurchaseBook> purchaseBookList;
}
