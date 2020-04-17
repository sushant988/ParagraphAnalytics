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

import org.springframework.stereotype.Service;

import com.company.paragraphanalytics.dto.ParagraphAnalytics;
import com.company.paragraphanalytics.dto.Paragraph;

@Service
public class ParagraphAnalyticsServiceImpl implements ParagraphAnalyticsService {

	private Integer getSentencesCount(String paragrah) {
		String sentenceDelimiter = ".";
		System.out.println(paragrah.length());
		System.out.println(paragrah.replace(sentenceDelimiter, "").length());
		int sentenceCounter = paragrah.length() - paragrah.replace(sentenceDelimiter, "").length();
		System.out.println(sentenceCounter);

		if (paragrah.trim().endsWith(".")) {
			return sentenceCounter;
		}
		return ++sentenceCounter;
	}

	private List<String> getLongestWord(String paragraph) {
		// minWord and maxWord are received by reference // and not by value // will be
		// used to store and returoutput
		List<String> longestWords=new ArrayList<>();
		paragraph = paragraph.replace(".", " ");
		int paraLength = paragraph.length();
		int startingIndex = 0, endingIndex = 0;
		int max_length = 0, max_start_index = 0;
		System.out.println(paragraph); // Loop while input string is not empty
		while (endingIndex <= paraLength) {
			if (endingIndex < paraLength && paragraph.charAt(endingIndex) != ' ') {
				endingIndex++;
			} else {
				// end of a word
				// find curr word length
				int curr_length = endingIndex - startingIndex;

				if (curr_length > max_length) {
					longestWords.clear();
					max_length = curr_length;
					max_start_index = startingIndex;
					String word=paragraph.substring(max_start_index, (max_length + max_start_index));
					System.out.println("---------->"+word);
					if(longestWords.indexOf(word)==-1) {
						longestWords.add(word);
					}
					
					
				}else if(curr_length == max_length) {
					max_start_index = startingIndex;
					String word=paragraph.substring(max_start_index, (max_length + max_start_index));
					System.out.println("---------->"+word);
					if(longestWords.indexOf(word)==-1) {
						longestWords.add(word);
					}
				}
				endingIndex++;
				startingIndex = endingIndex;
			}
		} // store minimum and
		// maximum length words
		return longestWords;
	}

	private Map<String, Integer> getWordCount(String paragraph) {
		// get the length of each word
		paragraph = paragraph.replace(".", " ");
		List<String> list = Stream.of(paragraph).map(w -> w.split("\\s+")).flatMap(Arrays::stream)
				.collect(Collectors.toList());

		Map<String, Integer> wordCounter = list.stream()
				.collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));

		System.out.println(wordCounter);

		List<Map.Entry<String, Integer>> listMap = new LinkedList<Map.Entry<String, Integer>>(wordCounter.entrySet());

		// Sort the list
		Collections.sort(listMap, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : listMap) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	private Integer getLength(String paragraph) {
		return paragraph.trim().length();

	}

	private List<String> keys(Map<String, Integer> map, Integer value) {

		return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	@Override
	public ParagraphAnalytics getParagraphAnalytics(Paragraph paragraph) {
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
		return paragraphAnalytics;
	}



}
