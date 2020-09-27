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
import com.example.demo.model.Change;
import com.example.demo.repo.ChangeRepository;
import com.example.demo.response.ResponseChange;
import com.example.demo.response.ResponseCommon;
import com.example.demo.response.ResponseError;

@Service
public class ChangeServiceImpl implements ChangeService {

	@Autowired
	private ChangeRepository changeRepo;

	@Override
	public ResponseEntity<Object> getChangeList() {
		List<Change> changeList = changeRepo.findAll();

		ResponseChange resp = new ResponseChange(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), changeList);
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addChange(Change change) {
		changeRepo.save(change);
		ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_SAVED.getResponseCode(),
				CommonMessage.RECORD_SAVED.getResponseDescription());
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getChangeById(Long id) {
		Change change = changeRepo.findById(id).orElse(null);
		ResponseChange resp = new ResponseChange(HttpStatus.OK, CommonMessage.RECORD_RETRIEVED.getResponseCode(),
				CommonMessage.RECORD_RETRIEVED.getResponseDescription(), Arrays.asList(change));
		return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateChange(Change change) {
		Change existingChange = changeRepo.findById(change.getId()).orElse(null);

		if (existingChange == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed update record for id " + change.getId());
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			changeRepo.save(change);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_UPDATED.getResponseCode(),
					CommonMessage.RECORD_UPDATED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Object> deleteChange(Long id) {
		Change existingChange = changeRepo.findById(id).orElse(null);

		if (existingChange == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("id", "Failed delete record for id " + id);
			ResponseError resp = new ResponseError(HttpStatus.BAD_REQUEST,
					CommonMessage.ID_DOES_NOT_EXIST.getResponseCode(),
					CommonMessage.ID_DOES_NOT_EXIST.getResponseDescription(), errors);
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} else {
			changeRepo.deleteById(id);
			ResponseCommon resp = new ResponseCommon(HttpStatus.OK, CommonMessage.RECORD_DELETED.getResponseCode(),
					CommonMessage.RECORD_DELETED.getResponseDescription());
			return new ResponseEntity<>(resp, new HttpHeaders(), HttpStatus.OK);
		}

	}

}
