package kosta.namtang.talkbook.repository.bill;


import org.springframework.data.repository.CrudRepository;
import kosta.namtang.talkbook.model.domain.bill.PurchaseCancel;

public interface PurchaseCancelRepository extends CrudRepository<PurchaseCancel, Long> {

//	int insert(CancelDTO dto);
}
