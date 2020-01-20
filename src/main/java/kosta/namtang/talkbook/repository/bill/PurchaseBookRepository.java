package kosta.namtang.talkbook.repository.bill;

import kosta.namtang.talkbook.model.domain.bill.PurchaseBook;
import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseBookRepository extends CrudRepository<PurchaseBook, PurchaseBookId> {

    List<PurchaseBook> findByPurchaseBookIdPurchaseOrderIdx(long orderIdx);

    PurchaseBook findByPurchaseBookId(PurchaseBookId purchaseBookId);

    PurchaseBook findByPurchaseBookIdBookIdx(long bookIdx);
//
//	int update(PurchaseGoodsDTO dto);
}
