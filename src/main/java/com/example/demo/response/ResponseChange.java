package com.example.demo.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.demo.model.Change;

public class ResponseChange extends ResponseCommon {

	private List<Change> data;

	public ResponseChange(HttpStatus status, String reponseCode, String responseDescription, List<Change> data) {
		super(status, reponseCode, responseDescription);
		setData(data);
	}

	public List<Change> getData() {
		return data;
	}

	public void setData(List<Change> data) {
		this.data = data;
	}

}
