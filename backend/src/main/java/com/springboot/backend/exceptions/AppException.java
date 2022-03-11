package com.springboot.backend.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

	private HttpStatus status;
	private String message;

	public AppException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public AppException(HttpStatus status, String message, String message1) {
		this.status = status;
		this.message = message;
		this.message = message1;
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
	
	private static final long serialVersionUID = 1L;

}
