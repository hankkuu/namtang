package kosta.namtang.talkbook.common.bill;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.repository.bill.BillKeyRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;

public class BillKeySystem {

	@Autowired
	private BillKeyRepository billkey;
	
	public String issueBillKey() throws Exception {
		
		// 새로운 키 발급 
		UUID uuid = UUID.randomUUID();
		
		// 기존에 있는 키인지 확인한다
		Optional<BillKey> key = billkey.findById(uuid.toString());
		if(key == null) {
			return uuid.toString();
		}
		
		return "";
	}
	
	public BillKey registerPurchase(String billKey, Date date) throws Exception {
		
		BillKey key = new BillKey();
		key.setBillKey(billKey);
		key.setIssueDate(date);
		key.setUpdateDate(date);
		key.setCancelDate(DateTimeHelper.convertSqldate(DateTimeHelper.dateFormatUtil("yyyy-MM-dd", "1900-01-01")));
		//key.setCreateDate(DateTimeHelper.sqlDateNow());

		BillKey result = null;
		result = billkey.save(key);
		return result;
	}
	
	public BillKey registerCancel(String billKey, Date date) throws Exception {
		
		BillKey key = new BillKey();
		key.setCancelDate(date);
		key.setUpdateDate(date);

		BillKey result = null;
		result = billkey.save(key);
		return result;
	}

}
