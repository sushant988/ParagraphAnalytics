package com.company.paragraphanalytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.paragraphanalytics.ParagraphanalyticsApplication;
import com.company.paragraphanalytics.dto.Paragraph;
import com.company.paragraphanalytics.dto.ParagraphAnalytics;
import com.company.paragraphanalytics.exception.InvalidRequestException;
import com.company.paragraphanalytics.service.ParagraphAnalyticsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ParagraphAnalyticsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphanalyticsApplication.class);

	@Autowired
	private ParagraphAnalyticsService paragraphAnalyticsService;

	@PostMapping(path = "/getAnalytics", consumes = "application/json")
	public ResponseEntity<Object> getAnalytics(@RequestBody Paragraph paragraph) throws InvalidRequestException {
		LOGGER.debug("Service /getAnalytics called " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now()));
		if (paragraph == null || paragraph.getParagraph() == null || paragraph.getParagraph().trim().length() == 0) {
			LOGGER.info("Invalid Input . Throwing back  InvalidRequestException");
			throw new InvalidRequestException("Invalid Input");
		}
		ParagraphAnalytics paragraphAnalytics = paragraphAnalyticsService.getParagraphAnalytics(paragraph);
		LOGGER.debug("Service /getAnalytics is giving back response " + paragraph);
		LOGGER.debug("Service /getAnalytics ended " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now()));
		return ResponseEntity.ok(paragraphAnalytics);

	}

}
