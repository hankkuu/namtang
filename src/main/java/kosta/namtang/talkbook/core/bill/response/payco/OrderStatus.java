package kosta.namtang.talkbook.core.bill.response.payco;

import com.google.gson.annotations.SerializedName;

public class OrderStatus {

	@SerializedName("status")
	private String status;

	public String getStatus() {
		return status;
	}
	
}
