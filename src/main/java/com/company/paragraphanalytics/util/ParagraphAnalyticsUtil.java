package com.company.paragraphanalytics.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ParagraphAnalyticsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphAnalyticsUtil.class);

	public static final char SPACEASCHAR = ' ';
	public static final char COMMA = ',';
	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String FULLSTOP = ".";
	
	public static List<String> getKeysBasedOnValue(Map<String, Integer> map, Integer value) {
		LOGGER.debug("Started process to find keys of the map having the highest word count "+value);
		return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
	

}
