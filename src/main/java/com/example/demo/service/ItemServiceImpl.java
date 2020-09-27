package com.example.demo.service;

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
import com.example.demo.model.Item;
import com.example.demo.repo.ItemRepository;
import com.example.demo.response.ResponseCommon;
import com.example.demo.response.ResponseError;
import com.example.demo.response.ResponseItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Override
	public ResponseEntity<Object> getItemList() {
		List<Item> itemList = itemRepo.findAll();

		ResponseItem resp = new ResponseItem(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), itemList);
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addItem(Item item) {
		itemRepo.save(item);
		ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_SAVED.getResponseCode(),
				CommonMessage.RECORD_SAVED.getResponseDescription());
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getItemById(Long id) {
		Item item = itemRepo.findById(id).orElse(null);
		ResponseItem resp = new ResponseItem(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), Arrays.asList(item));
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateItem(Item item) {
		Item existingItem = itemRepo.findById(item.getId()).orElse(null);

		if (existingItem == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed update record for id " + item.getId());
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			itemRepo.save(item);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_UPDATED.getResponseCode(),
					CommonMessage.RECORD_UPDATED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Object> deleteItem(Long id) {
		Item existingItem = itemRepo.findById(id).orElse(null);

		if (existingItem == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed delete record for id " + id);
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			itemRepo.deleteById(id);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_DELETED.getResponseCode(),
					CommonMessage.RECORD_DELETED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
	}

}
