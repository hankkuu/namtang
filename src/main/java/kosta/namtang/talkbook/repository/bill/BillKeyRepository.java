package kosta.namtang.talkbook.repository.bill;

import kosta.namtang.talkbook.model.domain.bill.BillKey;
import org.springframework.data.repository.CrudRepository;


public interface BillKeyRepository extends CrudRepository<BillKey, String> {


//	public BillKeyDTO select(String billkey) throws Exception ;
//
//	public int update(BillKeyDTO bill) throws Exception ;
//
//	public int insert(BillKeyDTO bill) throws Exception ;
}
