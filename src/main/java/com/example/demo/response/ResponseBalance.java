package com.example.demo.response;

import org.springframework.http.HttpStatus;

import com.example.demo.model.Balance;

public class ResponseBalance extends ResponseCommon {

	private Balance balance;
	
	public ResponseBalance(HttpStatus status, String responseCode, String responseDescription, Balance balance) {
		super(status, responseCode, responseDescription);
		setBalance(balance);
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}




}
