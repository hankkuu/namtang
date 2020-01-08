package kosta.namtang.talkbook.model.domain.bill;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long purchaseOrderIdx;
	private int stateCode;
	private String deliveryAddress;
	private Date orderDate;
	private Date updateDate;
	private String billKey;
	private long userIdx;

	//private PurchasePayment payment;
	//private List<PurchaseBook> purchaseBookList;
}
