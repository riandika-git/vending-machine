package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Item;

public interface ItemService {

	public ResponseEntity<Object> getItemList();

	public ResponseEntity<Object> addItem(Item item);

	public ResponseEntity<Object> getItemById(Long id);

	public ResponseEntity<Object> updateItem(Item item);

	public ResponseEntity<Object> deleteItem(Long id);

}
