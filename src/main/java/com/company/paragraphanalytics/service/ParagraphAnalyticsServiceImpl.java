package com.company.paragraphanalytics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.company.paragraphanalytics.dto.ParagraphAnalytics;
import com.company.paragraphanalytics.ParagraphanalyticsApplication;
import com.company.paragraphanalytics.dto.Paragraph;

@Service
public class ParagraphAnalyticsServiceImpl implements ParagraphAnalyticsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphanalyticsApplication.class);


	private Integer getSentencesCount(String paragrah) {
		
		LOGGER.debug("Started process to find number of sentences in a paragraph");
		String sentenceDelimiter = ".";
		System.out.println(paragrah.length());
		System.out.println(paragrah.replace(sentenceDelimiter, "").length());
		int sentenceCounter = paragrah.length() - paragrah.replace(sentenceDelimiter, "").length();
		LOGGER.debug("Number of . found in the paragrah are "+sentenceCounter);

		if (paragrah.trim().endsWith(".")) {
			LOGGER.debug("Number of sentences found "+sentenceCounter);
			return sentenceCounter;
		}
		LOGGER.debug("Paragraph not ending with full stop , thus increeasing counter by one ");
		return ++sentenceCounter;
	}

	private List<String> getLongestWord(String paragraph) {
		LOGGER.debug("Started process to find longest words in a paragraph");
		List<String> longestWords = new ArrayList<>();
		paragraph = paragraph.replace(".", " ");
		int paraLength = paragraph.length();
		int startingIndex = 0, endingIndex = 0;
		int max_length = 0, max_start_index = 0;
		System.out.println(paragraph); // 
		LOGGER.debug("Loop while input string is not empty");
		while (endingIndex <= paraLength) {
			if (endingIndex < paraLength && paragraph.charAt(endingIndex) != ' ') {
				LOGGER.debug("Input char in at the position is not empty so increasing the ending index of the string by one");
				endingIndex++;
			} else {
				LOGGER.debug("Input char in at the position is  empty.We have got the first index and last index of a woord in paragraph");
				
				// end of a word
				// find curr word length
				int curr_length = endingIndex - startingIndex;
				LOGGER.debug("Starting index of the word is "+startingIndex+". Ending Index is "+endingIndex+" Total length of the word is "+curr_length);
				if (curr_length > max_length) {
					LOGGER.debug("Max Length we had was "+max_length+" which is greater than "+curr_length+"Saving the word in a list");
					
					longestWords.clear();
					max_length = curr_length;
					max_start_index = startingIndex;
					String word = paragraph.substring(max_start_index, (max_length + max_start_index));
					if (longestWords.indexOf(word) == -1) {
						LOGGER.debug("Word added to list" + word);
						longestWords.add(word);
					}

				} else if (curr_length == max_length) {
					LOGGER.debug("Current Length of the word is equal to the max length we had for word until now");
					max_start_index = startingIndex;
					String word = paragraph.substring(max_start_index, (max_length + max_start_index));
					LOGGER.debug("Starting index of the word is "+startingIndex+". Ending Index is "+endingIndex+" Total length of the word is "+curr_length);
					if (longestWords.indexOf(word) == -1) {
						LOGGER.debug("Word added to list" + word);
						longestWords.add(word);
					}
				}
				endingIndex++;
				startingIndex = endingIndex;
			}
		} // store minimum and
			// maximum length words
		LOGGER.debug("Longest Words in paragraph are "+longestWords);
		return longestWords;
	}

	private Map<String, Integer> getWordCount(String paragraph) {
		
		LOGGER.debug("Started process to find words count in a paragraph");
		// get the length of each word
		paragraph = paragraph.replace(".", " ");
		
		List<String> list = Stream.of(paragraph).map(w -> w.split("\\s+")).flatMap(Arrays::stream)
				.collect(Collectors.toList());
		LOGGER.debug("Completed process to convert paragraph to list of words using streams "+list);

		Map<String, Integer> wordCounter = list.stream()
				.collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
		LOGGER.debug("Completed process to convert list of words to map having word count "+wordCounter);

		

		List<Map.Entry<String, Integer>> listMap = new LinkedList<Map.Entry<String, Integer>>(wordCounter.entrySet());

		// Sort the list
		Collections.sort(listMap, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> wordCountMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : listMap) {
			wordCountMap.put(aa.getKey(), aa.getValue());
		}
		LOGGER.debug("Completed process to sort the map based on word count "+wordCountMap);
		LOGGER.debug("Started process to find words count in a paragraph"); 

		return wordCountMap;
	}

	private Integer getLength(String paragraph) {
		return paragraph.trim().length();

	}

	private List<String> keys(Map<String, Integer> map, Integer value) {
		LOGGER.debug("Started process to find keys of the map having the highest word count "+value);
		return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	@Override
	public ParagraphAnalytics getParagraphAnalytics(Paragraph paragraph) {
		LOGGER.debug("Started process to get ParagraphAnalytics");
		ParagraphAnalytics paragraphAnalytics = new ParagraphAnalytics();

		Map<String, Integer> wordCount = getWordCount(paragraph.getParagraph());
		if (!wordCount.isEmpty()) {
			Map.Entry<String, Integer> entry = wordCount.entrySet().iterator().next();
			Integer mostUsedWordLength = entry.getValue();
			paragraphAnalytics.setMostUsedWords(keys(wordCount, mostUsedWordLength));
		}

		paragraphAnalytics.setLongestWords(getLongestWord(paragraph.getParagraph()));
		paragraphAnalytics.setWordCount(wordCount);
		paragraphAnalytics.setTotalLength(String.valueOf(getLength(paragraph.getParagraph())));
		paragraphAnalytics.setNumberOfSentences(String.valueOf(getSentencesCount(paragraph.getParagraph())));
		LOGGER.debug("Ended process to get ParagraphAnalytics");
		return paragraphAnalytics;
	}

}
