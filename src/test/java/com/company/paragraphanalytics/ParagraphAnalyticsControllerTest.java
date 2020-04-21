package com.company.paragraphanalytics;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.company.paragraphanalytics.dto.Paragraph;
import com.company.paragraphanalytics.dto.ParagraphAnalytics;
import com.company.paragraphanalytics.service.ParagraphAnalyticsService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParagraphAnalyticsControllerTest  {
	

	
	@Test
	public void contextLoads() {
	}


	@Mock
	ParagraphAnalyticsService paragraphAnalyticsService;
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	 
	@Test
	public void getParagraphAnalyticsWithValidRequest_ReturnsValidResponse() throws Exception 
	
	{
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
		
		
		
		//analytics.set
				
				when(paragraphAnalyticsService.getParagraphAnalytics(para)).thenReturn(analytics);
	  mvc.perform( MockMvcRequestBuilders.post("/getAnalytics")
	      .content(asJsonString(para))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.longestWords").isArray())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.mostUsedWords").isArray())
	      .andExpect((MockMvcResultMatchers.jsonPath("$.numberOfSentences").isString()))
	      .andExpect((MockMvcResultMatchers.jsonPath("$.totalLength").isString()))
	      .andExpect((MockMvcResultMatchers.jsonPath("$.wordCount").isMap()))
	      ;
	}
	
	@Test
	public void getParagraphAnalyticsWithInValidRequest_ReturnsInvalidRequestException() throws Exception 
	
	{
		Paragraph para=	new Paragraph("");
	mvc.perform( MockMvcRequestBuilders.post("/getAnalytics")
	      .content(asJsonString(para))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isBadRequest());
	
	}
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
