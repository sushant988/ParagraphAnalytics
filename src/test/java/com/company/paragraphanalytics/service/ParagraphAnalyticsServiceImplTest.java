package com.company.paragraphanalytics.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.paragraphanalytics.dto.Paragraph;
import com.company.paragraphanalytics.dto.ParagraphAnalytics;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParagraphAnalyticsServiceImplTest {
	
	@Autowired
	private ParagraphAnalyticsService service;
	
	

	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsLongestWordsResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();
		List<String> longestWords=new ArrayList<>();
		longestWords.add("application");
		analytics.setLongestWords(longestWords);		
		
		//call
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		
		//assert
		assertEquals(longestWords.contains("application"), analyticsResponse.getLongestWords().contains("application"));
		assertEquals(longestWords.size(), analyticsResponse.getLongestWords().size());
		
	}
	
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsWordCountResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring Boot application rocks");
		Map<String, Integer> wordCount=new LinkedHashMap<>();
		wordCount.put("application", 2);
		wordCount.put("This", 1);
		wordCount.put("is", 1);
		wordCount.put("an", 1);
		wordCount.put("input", 1);
		wordCount.put("string", 1);
		wordCount.put("used", 1);
		wordCount.put("to", 1);
		wordCount.put("test", 1);
		wordCount.put("the", 1);		
		wordCount.put("spring", 1);
		wordCount.put("boot", 1);
		wordCount.put("rocks", 1);
		
	
		//call
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		
		//assert
		assertEquals(wordCount.size(), analyticsResponse.getWordCount().size());
		assertEquals(wordCount.get("application"), analyticsResponse.getWordCount().get("application"));
		assertEquals(wordCount.get("spring"), analyticsResponse.getWordCount().get("spring"));
		
		
	}
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsMostWordsResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();		
		List<String> mostUsedWords=new ArrayList<>();
		mostUsedWords.add("test");
		mostUsedWords.add("the");
		analytics.setMostUsedWords(mostUsedWords);
		
		
		//call
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		
		//assert
		assertEquals(mostUsedWords.size(), analyticsResponse.getMostUsedWords().size());
		assertEquals(mostUsedWords.contains("test"), analyticsResponse.getMostUsedWords().contains("test"));
		assertEquals(mostUsedWords.contains("the"), analyticsResponse.getMostUsedWords().contains("the"));
		
		
	}
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsValidNoOfSentencesResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();		
		analytics.setNumberOfSentences(String.valueOf("3"));
		analytics.setTotalLength(String.valueOf("85"));
		
		//call
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		
		//assert
		assertEquals(analytics.getNumberOfSentences(), analyticsResponse.getNumberOfSentences());
		
		
	}
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsTotalLengthResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();
	
		analytics.setTotalLength(String.valueOf("85"));
		
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		assertEquals(analytics.getTotalLength(), analyticsResponse.getTotalLength());
		
		
	}
}
