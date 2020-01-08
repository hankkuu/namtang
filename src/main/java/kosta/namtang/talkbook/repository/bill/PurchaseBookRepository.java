package kosta.namtang.talkbook.repository.bill;

import kosta.namtang.talkbook.model.domain.bill.PurchaseBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseBookRepository extends CrudRepository<PurchaseBook, Long> {
	
	List<PurchaseBook> findByPurchaseOrderIdx(long orderIdx);

	PurchaseBook findByBookIdx(long bookIdx);
//
//	int update(PurchaseGoodsDTO dto);
}
