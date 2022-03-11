package com.springboot.backend.utils;

import org.springframework.http.HttpStatus;

public class RestResponse {

	private HttpStatus status;
	private String message;
	private Object response;

	public RestResponse() {
	}

	public RestResponse(HttpStatus status, String message, Object response) {
		this.status = status;
		this.message = message;
		this.response = response;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
