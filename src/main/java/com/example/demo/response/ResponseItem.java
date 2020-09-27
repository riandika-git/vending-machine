package com.example.demo.response;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.example.demo.model.Item;

public class ResponseItem extends ResponseCommon {

	private List<Item> data;

	public ResponseItem(HttpStatus status, String responseCode, String responseDescription, List<Item> data) {
		super(status, responseCode, responseDescription);
		setData(data);
	}

	public List<Item> getData() {
		return data;
	}

	public void setData(List<Item> data) {
		this.data = data;
	}


}
