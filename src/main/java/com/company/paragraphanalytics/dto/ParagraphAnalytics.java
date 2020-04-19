package com.company.paragraphanalytics.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.company.paragraphanalytics.util.ParagraphAnalyticsUtil;

@Component
public class ParagraphAnalytics {

	private List<String> longestWords;

	private List<String> mostUsedWords;

	private String totalLength;

	private String numberOfSentences;

	private Map<String, Integer> wordCount;

	public List<String> getLongestWords() {
		return longestWords;
	}

	public void setLongestWords(List<String> longestWords) {
		this.longestWords = longestWords;
	}

	public List<String> getMostUsedWords() {
		if (wordCount!=null && !wordCount.isEmpty()) {
			Map.Entry<String, Integer> entry = wordCount.entrySet().iterator().next();
			Integer mostUsedWordLength = entry.getValue();
			return ParagraphAnalyticsUtil.getKeysBasedOnValue(wordCount, mostUsedWordLength);
		}
		return mostUsedWords;
	}

	public void setMostUsedWords(List<String> mostUsedWords) {
		this.mostUsedWords = mostUsedWords;
	}

	public String getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getNumberOfSentences() {
		return numberOfSentences;
	}

	public void setNumberOfSentences(String numberOfSentences) {
		this.numberOfSentences = numberOfSentences;
	}

	public Map<String, Integer> getWordCount() {
		return wordCount;
	}

	public void setWordCount(Map<String, Integer> wordCount) {
		this.wordCount = wordCount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return longestWords + " " + mostUsedWords + " " + totalLength + " " + numberOfSentences + " " + wordCount;
	}

}
