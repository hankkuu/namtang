package kosta.namtang.talkbook.model.domain.bill.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PurchaseBookId  implements Serializable {

    private long bookIdx;
    private long purchaseOrderIdx;
}
