package kosta.namtang.talkbook.model.domain.bill.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PurchaseBookId implements Serializable {

    private Long bookIdx;
    private Long purchaseOrderIdx;
}
