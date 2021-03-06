package com.company.paragraphanalytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	  @Override
	    public synchronized Throwable fillInStackTrace() {
	        return this;
	    }
}
