package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.message.CommonMessage;
import com.example.demo.model.Balance;
import com.example.demo.model.Change;
import com.example.demo.model.FinalOutput;
import com.example.demo.model.Item;
import com.example.demo.repo.ChangeRepository;
import com.example.demo.repo.ItemRepository;
import com.example.demo.response.ResponseBalance;
import com.example.demo.response.ResponseCommon;
import com.example.demo.response.ResponseError;
import com.example.demo.response.ResponseFinalOutput;
import com.example.demo.response.ResponseItem;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private ChangeRepository changeRepo;
	
	private int currentBalance = 0;
	private Item selectedItem = null;
	private static List<Integer> acceptedDenomination = new ArrayList<Integer>() {{
		add(2000);
		add(5000);
		add(10000);
		add(20000);
		add(50000);
	}};
	


	@Override
	public ResponseEntity<Object> selectItem(Long itemId) {
		Item item = itemRepo.findById(itemId).orElse(null);
		selectedItem = item;
		FinalOutput finalOutput = new FinalOutput(item,null);
		
		if(item.getQuantity() == 0) {
			ResponseFinalOutput resp = new ResponseFinalOutput(HttpStatus.OK, CommonMessage.ITEM_NOT_AVAILABLE.getResponseCode(),
					CommonMessage.ITEM_NOT_AVAILABLE.getResponseDescription(), finalOutput);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
		else {
			return processTransaction();
		}
		
	}

	@Override
	public ResponseEntity<Object> insertMoney(Integer value) {
		if(!acceptedDenomination.contains(value)) {
			Balance balance = new Balance(currentBalance, selectedItem.getPrice() - currentBalance);
			ResponseBalance resp = new ResponseBalance(HttpStatus.OK, CommonMessage.INVALID_DENOMINATION.getResponseCode(),
					CommonMessage.INVALID_DENOMINATION.getResponseDescription(), balance);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
		currentBalance = currentBalance + value;
		return processTransaction();
	}
	
	private ResponseEntity<Object> processTransaction() {
		FinalOutput finalOutput = new FinalOutput();
		finalOutput.setItem(selectedItem);
		
		if(currentBalance < selectedItem.getPrice()) {
			Balance balance = new Balance(currentBalance, selectedItem.getPrice() - currentBalance);
			ResponseBalance resp = new ResponseBalance(HttpStatus.OK, CommonMessage.BALANCE_NOT_ENOUGH.getResponseCode(),
					CommonMessage.BALANCE_NOT_ENOUGH.getResponseDescription(), balance);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
		
		int totalChangeAmount = currentBalance - selectedItem.getPrice();
		List<Change> changeList = new ArrayList<>();
		
		if(totalChangeAmount > 0) {
			List<Change> availableDenominationList = changeRepo.findByDenominationLessThanEqualOrderByDenominationDesc(totalChangeAmount);
			for(Change denomination : availableDenominationList) {
				while(totalChangeAmount > 0) {
					if(totalChangeAmount - denomination.getDenomination() >= 0) {
						totalChangeAmount = totalChangeAmount - denomination.getDenomination();
						changeList.add(denomination);
					}
					else {
						break;
					}
				}
			}
			finalOutput.setChange(changeList);
		}
		
		selectedItem.setQuantity(1);
		ResponseFinalOutput resp = new ResponseFinalOutput(HttpStatus.OK, CommonMessage.PURCHASE_SUCCESS.getResponseCode(),
				CommonMessage.PURCHASE_SUCCESS.getResponseDescription(), finalOutput);
		currentBalance = 0;
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> refund() {
		Balance balance = new Balance(currentBalance, 0);
		ResponseBalance resp = new ResponseBalance(HttpStatus.OK, CommonMessage.PURCHASE_CANCELED.getResponseCode(),
				CommonMessage.PURCHASE_CANCELED.getResponseDescription(), balance);
		currentBalance = 0;
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

}
