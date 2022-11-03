package com.file.content.searching.utils;

import java.util.Arrays;
import java.util.Optional;

/**
 * This enum contains regex pattern and with its corresponding matcher group location 
 *
 */
public enum RegexGroupEnum {

	HOURS_REGEX("(\\s)(\\d\\d)(\\s)", 2),
	VIOLATION_CODE_REGEX("([0-9]{2})([.]{1})([0-9]{2})([.]{1})([0-9]{3})", 0),
	LEL_PERCENT_REGEX("([(]{1})([0-9]{1,3})([%]{1})([)]{1})", 2);
	
	private final String regexPattern;

	private final int matcherGroup;
	
	
	RegexGroupEnum(String regexPattern, int matcherGroup) {
		
		this.regexPattern = regexPattern;
		this.matcherGroup = matcherGroup;
	}
	
	public String getRegexPattern() {
		return regexPattern;
	}


	public int getMatcherGroup() {
		return matcherGroup;
	}
	
	public static Optional<Integer> getStateByName(String regex) {
		return Arrays.stream(RegexGroupEnum.values())
				.filter(regexEnum -> regexEnum.regexPattern.equals(regex))
				.map(RegexGroupEnum::getMatcherGroup)
				.findFirst();
	}

	public static Optional<String> getNameByState(int matcherGroup) {
		return Arrays.stream(RegexGroupEnum.values()).filter(regexEnum -> regexEnum.matcherGroup == matcherGroup).map(RegexGroupEnum::getRegexPattern)
				.findFirst();
	}
	
}
