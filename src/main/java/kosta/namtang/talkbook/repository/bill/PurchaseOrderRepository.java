package kosta.namtang.talkbook.repository.bill;

import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

    PurchaseOrder findByBillKey(String billKey);

    List<PurchaseOrder> findByUserIdx(long userIdx);

//
//	int update(PurchaseBasketDTO dto);
}
