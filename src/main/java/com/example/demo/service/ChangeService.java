package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Change;

public interface ChangeService {

	public ResponseEntity<Object> getChangeList();

	public ResponseEntity<Object> addChange(Change change);

	public ResponseEntity<Object> getChangeById(Long id);

	public ResponseEntity<Object> updateChange(Change change);

	public ResponseEntity<Object> deleteChange(Long id);

}
