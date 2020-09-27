package com.example.demo.message;

public enum CommonMessage {
	RECORD_RETRIEVED("0101", "Successfully retrieve data"), 
	RECORD_SAVED("0102", "New record has been saved"),
	RECORD_UPDATED("0103", "Record has been updated"), 
	RECORD_DELETED("0104", "Record has been deleted"),
	PURCHASE_SUCCESS("0105", "Purchase success"),
	BALANCE_NOT_ENOUGH("0106", "Balance not enough, please insert money"),
	PURCHASE_CANCELED("0107", "Purchase is canceled, refund money"),
	ID_DOES_NOT_EXIST("0201", "Id does not exist"), 
	INVALID_PARAMETER("0202", "Invalid parameter"),
	INVALID_DENOMINATION("0203", "This machine only accept 2000, 5000, 10000, 20000, 50000"),
	ITEM_NOT_AVAILABLE("0204", "This item is not available");

	public final String responseCode;
	public final String responseDescription;

	private CommonMessage(String responseCode, String responseDescription) {
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

}
