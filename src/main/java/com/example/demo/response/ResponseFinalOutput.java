package com.example.demo.response;

import org.springframework.http.HttpStatus;

import com.example.demo.model.FinalOutput;

public class ResponseFinalOutput extends ResponseCommon {

	private FinalOutput finalOutput;
	
	public ResponseFinalOutput(HttpStatus status, String responseCode, String responseDescription, FinalOutput finalOutput) {
		super(status, responseCode, responseDescription);
		setFinalOutput(finalOutput);
	}

	public FinalOutput getFinalOutput() {
		return finalOutput;
	}

	public void setFinalOutput(FinalOutput finalOutput) {
		this.finalOutput = finalOutput;
	}



}
