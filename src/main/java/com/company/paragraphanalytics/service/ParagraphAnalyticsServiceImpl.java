package com.company.paragraphanalytics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.company.paragraphanalytics.dto.ParagraphAnalytics;
import com.company.paragraphanalytics.util.ParagraphAnalyticsUtil;
import com.company.paragraphanalytics.ParagraphanalyticsApplication;
import com.company.paragraphanalytics.dto.Paragraph;

@Service
public class ParagraphAnalyticsServiceImpl implements ParagraphAnalyticsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphanalyticsApplication.class);

	private Integer getSentencesCount(String paragrah) {
		LOGGER.debug("Started process to find number of sentences in a paragraph");
		String sentenceDelimiter = ParagraphAnalyticsUtil.FULLSTOP;
		int sentenceCounter = paragrah.length()
				- paragrah.replace(sentenceDelimiter, ParagraphAnalyticsUtil.EMPTY).length();
		LOGGER.debug("Number of . found in the paragrah are " + sentenceCounter);

		if (paragrah.trim().endsWith(ParagraphAnalyticsUtil.FULLSTOP)) {
			LOGGER.debug("Number of sentences found " + sentenceCounter);
			return sentenceCounter;
		}
		LOGGER.debug("Paragraph not ending with full stop , thus increeasing counter by one ");
		return ++sentenceCounter;
	}

	private List<String> getLongestWord(String paragraph) {
		LOGGER.debug("Started process to find longest words in a paragraph");
		List<String> longestWords = new ArrayList<>();
		paragraph = paragraph.replace(ParagraphAnalyticsUtil.FULLSTOP, ParagraphAnalyticsUtil.SPACE);
		int paraLength = paragraph.length();
		int startingIndex = 0, endingIndex = 0;
		int maxLength = 0, maxStartindex = 0;
		LOGGER.debug("Loop while input string is not empty");
		while (endingIndex <= paraLength) {
			if (endingIndex < paraLength && paragraph.charAt(endingIndex) != ParagraphAnalyticsUtil.SPACEASCHAR
					&& paragraph.charAt(endingIndex) != ParagraphAnalyticsUtil.COMMA) {
				LOGGER.debug(
						"Input char in at the position is not empty so increasing the ending index of the string by one");
				endingIndex++;
			} else {
				LOGGER.debug(
						"Input char in at the position is  empty.We have got the first index and last index of a woord in paragraph");

				// end of a word
				// find curr word length
				int currlength = endingIndex - startingIndex;
				LOGGER.debug("Starting index of the word is " + startingIndex + ". Ending Index is " + endingIndex
						+ " Total length of the word is " + currlength);
				if (currlength > maxLength) {
					LOGGER.debug("Max Length we had was " + maxLength + " which is greater than " + currlength
							+ "Saving the word in a list");

					longestWords.clear();
					maxLength = currlength;
					maxStartindex = startingIndex;
					String word = paragraph.substring(maxStartindex, (maxLength + maxStartindex));
					if (longestWords.indexOf(word) == -1) {
						LOGGER.debug("Word added to list" + word);
						longestWords.add(word);
					}

				} else if (currlength == maxLength) {
					LOGGER.debug("Current Length of the word is equal to the max length we had for word until now");
					maxStartindex = startingIndex;
					String word = paragraph.substring(maxStartindex, (maxLength + maxStartindex));
					LOGGER.debug("Starting index of the word is " + startingIndex + ". Ending Index is " + endingIndex
							+ " Total length of the word is " + currlength);
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
		LOGGER.debug("Longest Words in paragraph are " + longestWords);
		return longestWords;
	}

	private Map<String, Integer> getWordCount(String paragraph) {

		LOGGER.debug("Started process to find words count in a paragraph");
		// get the length of each word
		paragraph = paragraph.replace(ParagraphAnalyticsUtil.FULLSTOP, ParagraphAnalyticsUtil.SPACE);
		paragraph = paragraph.replace(ParagraphAnalyticsUtil.COMMA, ParagraphAnalyticsUtil.SPACEASCHAR);
		List<String> list = Stream.of(paragraph).map(word -> word.split("\\s+")).flatMap(Arrays::stream)
				.collect(Collectors.toList());
		LOGGER.debug("Completed process to convert paragraph to list of words using streams " + list);

		Map<String, Integer> wordCounter = list.stream()
				.collect(Collectors.toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
		LOGGER.debug("Completed process to convert list of words to map having word count " + wordCounter);

		Map<String, Integer> countByWordSorted = wordCounter.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> {
					throw new IllegalStateException();
				}, LinkedHashMap::new));

		LOGGER.debug("Completed process to find words count in a paragraph");

		return countByWordSorted;
	}

	private Integer getLength(String paragraph) {
		return paragraph.trim().length();
	}

	@Override
	public ParagraphAnalytics getParagraphAnalytics(Paragraph paragraph) {
		LOGGER.debug("Started process to get ParagraphAnalytics");
		ParagraphAnalytics paragraphAnalytics = new ParagraphAnalytics();
		Map<String, Integer> wordCount = getWordCount(paragraph.getParagraph());
		paragraphAnalytics.setLongestWords(getLongestWord(paragraph.getParagraph()));
		paragraphAnalytics.setWordCount(wordCount);
		paragraphAnalytics.setTotalLength(String.valueOf(getLength(paragraph.getParagraph())));
		paragraphAnalytics.setNumberOfSentences(String.valueOf(getSentencesCount(paragraph.getParagraph())));
		LOGGER.debug("Ended process to get ParagraphAnalytics");
		return paragraphAnalytics;
	}

}
