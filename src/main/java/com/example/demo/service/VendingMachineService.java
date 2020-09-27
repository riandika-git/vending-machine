package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Item;

public interface VendingMachineService {

	public ResponseEntity<Object> selectItem(Long itemId);

	public ResponseEntity<Object> insertMoney(Integer value);

	public ResponseEntity<Object> refund();

}
