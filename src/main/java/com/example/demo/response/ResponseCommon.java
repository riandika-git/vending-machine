package com.example.demo.response;

import org.springframework.http.HttpStatus;

public class ResponseCommon {

	private HttpStatus status;
	private String responseCode;
	private String responseDescription;

	public ResponseCommon(HttpStatus status, String responseCode, String responseDescription) {
		this.status = status;
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

}
