package com.file.content.searching.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utility {
	
	private static Logger log = LogManager.getLogger(Utility.class);
	
	private Utility() {}
	
	public static final List<String> regexPatternList = Arrays.asList("(\\d\\d)(\\s)", "([0-9]{2})([.]{1})([0-9]{2})([.]{1})([0-9]{3})", "\\b(?<!\\.)(?!0+(?:\\.0+)?%)(?:\\d|[1-9]\\d|100)(?:(?<!100)\\.\\d+)?%");

	public static final void logMemory() {
		
		log.info(" Free memory : {} ,Total Memory : {}, Max memory : {}", Runtime.getRuntime().freeMemory(), Runtime.getRuntime().totalMemory(), Runtime.getRuntime().maxMemory());
    }
}
