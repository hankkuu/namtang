package kosta.namtang.talkbook.service.bill;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.repository.bill.BillKeyRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillKeySystem {

	@Autowired
	private BillKeyRepository billkey;
	
	public String issueBillKey() throws Exception {
		
		// 새로운 키 발급 
		UUID uuid = UUID.randomUUID();
		
		// 기존에 있는 키인지 확인한다
		BillKey key = billkey.findById(uuid.toString()).orElse(null);
		if(key == null) {
			return uuid.toString();
		}
		
		return "";
	}
	
	public BillKey registerPurchase(String billKey, Timestamp date) throws Exception {
		
		BillKey key = new BillKey();
		key.setBillKey(billKey);
		key.setIssueDate(date);
		key.setUpdateDate(date);
		key.setCancelDate(DateTimeHelper.timeStamp("1900-01-01 00:00:00"));
		//key.setCreateDate(DateTimeHelper.sqlDateNow());

		BillKey result = billkey.save(key);
		return result;
	}
	
	public BillKey registerCancel(String billKey, Timestamp date) throws Exception {
		
		BillKey key = billkey.findById(billKey).orElse(null);
		if(key != null) {
			key.setCancelDate(date);
			key.setUpdateDate(date);

			BillKey result = billkey.save(key);
			if(result != null)
				return result;
		}

		return key;
	}

}
