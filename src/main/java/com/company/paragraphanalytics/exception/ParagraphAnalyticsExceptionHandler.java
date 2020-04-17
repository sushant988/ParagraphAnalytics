package com.company.paragraphanalytics.exception;

import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;  
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.context.request.WebRequest;  
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;  
//defining exception handling for all the exceptions  
@ControllerAdvice  
@SuppressWarnings(value = { "" })
public class ParagraphAnalyticsExceptionHandler extends ResponseEntityExceptionHandler  
{  

@ExceptionHandler(InvalidRequestException.class)  
//override method of ResponseEntityExceptionHandler class  
public final ResponseEntity<Object> handleAllExceptions(InvalidRequestException ex, WebRequest request)  
{  

return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);  
}  
}  
