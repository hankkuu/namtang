package kosta.namtang.talkbook.repository.bill;

import kosta.namtang.talkbook.model.domain.bill.PurchaseBook;
import kosta.namtang.talkbook.model.domain.bill.id.PurchaseBookId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseBookRepository extends CrudRepository<PurchaseBook, PurchaseBookId> {

    List<PurchaseBook> findByPurchaseBookIdPurchaseOrderIdx(long orderIdx);

    Optional<PurchaseBook> findByPurchaseBookId(PurchaseBookId purchaseBookId);

    Optional<PurchaseBook> findByPurchaseBookIdBookIdxAndPurchaseBookIdPurchaseOrderIdx(long bookIdx, long orderIdx);

    PurchaseBook findByPurchaseBookIdBookIdx(long bookIdx);
//
//	int update(PurchaseGoodsDTO dto);
}
