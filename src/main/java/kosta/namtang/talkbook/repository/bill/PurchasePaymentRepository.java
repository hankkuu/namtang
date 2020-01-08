package kosta.namtang.talkbook.repository.bill;

import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import org.springframework.data.repository.CrudRepository;

public interface PurchasePaymentRepository extends CrudRepository<PurchasePayment, Long> {

    PurchasePayment findByPurchaseOrderIdx(long purchaseOrderIdx);
}
