package com.example.demo.response;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseError extends ResponseCommon {

	Map<String, String> errors;

	public ResponseError(HttpStatus status, String responseCode, String responseDescription,
			Map<String, String> errors) {
		super(status, responseCode, responseDescription);
		setErrors(errors);
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
