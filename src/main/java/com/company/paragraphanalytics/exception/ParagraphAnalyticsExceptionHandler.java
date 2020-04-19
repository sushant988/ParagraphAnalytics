package com.company.paragraphanalytics.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//defining exception handling for all the exceptions  
@ControllerAdvice
public class ParagraphAnalyticsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
    		AccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Access denied message here",  HttpStatus.UNAUTHORIZED);
    }
     

	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<InvalidRequestException> handleAllExceptions(InvalidRequestException ex,
			WebRequest request) {

		return new ResponseEntity<InvalidRequestException>(ex, HttpStatus.BAD_REQUEST);
	}
}
