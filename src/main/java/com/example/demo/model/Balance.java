package com.example.demo.model;

public class Balance {

	private int currentBalance = 0;
	
	private int remainingBalance = 0;

	
	public Balance() {
	}


	
	public Balance(int currentBalance, int remainingBalance) {
		this.currentBalance = currentBalance;
		this.remainingBalance = remainingBalance;
	}



	public int getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}


	public int getRemainingBalance() {
		return remainingBalance;
	}


	public void setRemainingBalance(int remainingBalance) {
		this.remainingBalance = remainingBalance;
	}


}
