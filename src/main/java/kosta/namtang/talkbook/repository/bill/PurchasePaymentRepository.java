package kosta.namtang.talkbook.repository.bill;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;

public interface PurchasePaymentRepository extends CrudRepository<PurchasePayment, Long> {

    PurchasePayment findByPurchaseOrderIdx(long purchaseOrderIdx);
    
}
