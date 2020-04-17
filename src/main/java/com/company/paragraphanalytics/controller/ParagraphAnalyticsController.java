package com.company.paragraphanalytics.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.paragraphanalytics.dto.Paragraph;
import com.company.paragraphanalytics.dto.ParagraphAnalytics;
import com.company.paragraphanalytics.exception.InvalidRequestException;
import com.company.paragraphanalytics.service.ParagraphAnalyticsService;



@RestController
public class ParagraphAnalyticsController {
	
	@Autowired
	ParagraphAnalyticsService paragraphAnalyticsService;
	
	
@PostMapping( path="/getAnalytics",consumes="application/json")
	public ResponseEntity<Object> getAnalytics(@RequestBody Paragraph paragraph) throws InvalidRequestException {
		if(paragraph==null || paragraph.getParagraph()==null || paragraph.getParagraph().trim().length()==0) {
			throw new InvalidRequestException( "Invalid Input");
		}
	ParagraphAnalytics paragraphAnalytics=paragraphAnalyticsService.getParagraphAnalytics(paragraph);
		return ResponseEntity.ok(paragraphAnalytics);
		
	}
	
	
	
	

}
