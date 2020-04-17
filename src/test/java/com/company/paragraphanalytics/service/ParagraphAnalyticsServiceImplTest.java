package com.company.paragraphanalytics.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.paragraphanalytics.dto.Paragraph;
import com.company.paragraphanalytics.dto.ParagraphAnalytics;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParagraphAnalyticsServiceImplTest {
	
	@Autowired
	ParagraphAnalyticsService service;
	
	

	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsLongestWordsResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();
		List<String> longestWords=new ArrayList<>();
		longestWords.add("application");
		analytics.setLongestWords(longestWords);
		
		List<String> mostUsedWords=new ArrayList<>();
		mostUsedWords.add("test");
		mostUsedWords.add("the");
		analytics.setMostUsedWords(mostUsedWords);
		analytics.setNumberOfSentences(String.valueOf("3"));
		analytics.setTotalLength(String.valueOf("85"));
		
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		assertEquals(longestWords, analyticsResponse.getLongestWords());
		
		
	}
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsMostWordsResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();
		List<String> longestWords=new ArrayList<>();
		longestWords.add("application");
		analytics.setLongestWords(longestWords);
		
		List<String> mostUsedWords=new ArrayList<>();
		mostUsedWords.add("test");
		mostUsedWords.add("the");
		analytics.setMostUsedWords(mostUsedWords);
		analytics.setNumberOfSentences(String.valueOf("3"));
		analytics.setTotalLength(String.valueOf("85"));
		
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		assertEquals(mostUsedWords, analyticsResponse.getMostUsedWords());
		
		
	}
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsValidNoOfSentencesResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();
		List<String> longestWords=new ArrayList<>();
		longestWords.add("application");
		analytics.setLongestWords(longestWords);
		
		List<String> mostUsedWords=new ArrayList<>();
		mostUsedWords.add("test");
		mostUsedWords.add("the");
		analytics.setMostUsedWords(mostUsedWords);
		analytics.setNumberOfSentences(String.valueOf("3"));
		analytics.setTotalLength(String.valueOf("85"));
		
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		assertEquals(analytics.getNumberOfSentences(), analyticsResponse.getNumberOfSentences());
		
		
	}
	
	@Test
    public void testGetParagraphAnalytics_WithValidParams_ReturnsTotalLengthResponse() {
		
		Paragraph para=	new Paragraph("This is an input string used to test the application. Spring boot rocks. test the app");
		ParagraphAnalytics analytics=new ParagraphAnalytics();
		List<String> longestWords=new ArrayList<>();
		longestWords.add("application");
		analytics.setLongestWords(longestWords);
		
		List<String> mostUsedWords=new ArrayList<>();
		mostUsedWords.add("test");
		mostUsedWords.add("the");
		analytics.setMostUsedWords(mostUsedWords);
		analytics.setNumberOfSentences(String.valueOf("3"));
		analytics.setTotalLength(String.valueOf("85"));
		
		ParagraphAnalytics analyticsResponse = service.getParagraphAnalytics(para);
		assertEquals(analytics.getTotalLength(), analyticsResponse.getTotalLength());
		
		
	}
}
