package com.file.content.searching.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.content.searching.bean.PositionDetailsBean;
import com.file.content.searching.bean.SearchTextResponseBean;
import com.file.content.searching.utils.RegexGroupEnum;

public class ReadFileUsingStreams {
	
	private static Logger log = LogManager.getLogger(ReadFileUsingStreams.class);
	
	/**
	 * @param filePathWithFileName
	 * @throws IOException
	 */
	public void getStringPositionDetailsForInputText(String filePathWithFileName) throws IOException {
		
		List<SearchTextResponseBean> searchTextResponseBeanList = new ArrayList<>();
		
		try(Stream<String> inputStream = Files.lines(Paths.get(filePathWithFileName), StandardCharsets.UTF_8)) {
			
			AtomicInteger lineCounter = new AtomicInteger(0);
			
			inputStream.forEachOrdered(fileContent -> {
				
				lineCounter.incrementAndGet();
				
				if(Objects.nonNull(fileContent)) {
					
					EnumSet.allOf(RegexGroupEnum.class).forEach(enumValues -> {
						
						List<SearchTextResponseBean> intermediaryResponseList = validatePatternAndSetPositionDetails(enumValues.getRegexPattern(), enumValues.getMatcherGroup(), lineCounter, fileContent);
						
						if(CollectionUtils.isNotEmpty(intermediaryResponseList)) {
							
							searchTextResponseBeanList.addAll(intermediaryResponseList);
						}
					});
				}
			});
			
			log.info("Response via ReadFileUsingStreams : {}", new ObjectMapper().writeValueAsString(searchTextResponseBeanList));
	        
	    } catch(Exception exception) {
	    	
	    	log.error("Exception in processing the file via ReadFileUsingStreams : ",exception);
	    }
	}

	/**
	 * @param regexPattern
	 * @param matcherGroupPosition 
	 * @param lineCounter
	 * @param fileContent
	 * @return
	 */
	private List<SearchTextResponseBean> validatePatternAndSetPositionDetails(String regexPattern, int matcherGroupPosition, AtomicInteger lineCounter, String fileContent) {
		
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(fileContent);
		
		if(matcher.find()) {
		
			matcher.reset();
			
			List<SearchTextResponseBean> intermediaryResponseList = new ArrayList<>();
			
			while(matcher.find()) {
			    
				SearchTextResponseBean searchTextResponseBean = new SearchTextResponseBean();
				searchTextResponseBean.setSearchText(matcher.group(matcherGroupPosition));
				
			    PositionDetailsBean positionDetailsBean = new PositionDetailsBean();
				positionDetailsBean.setLineNumber(lineCounter.get());
				positionDetailsBean.setStartingPosition(matcher.start(matcherGroupPosition));
				positionDetailsBean.setEndingPosition(matcher.end(matcherGroupPosition));
				
				searchTextResponseBean.setPositionDetailsBean(positionDetailsBean);
				
				intermediaryResponseList.add(searchTextResponseBean);
			}
			
			return intermediaryResponseList;
		}
		
		return Collections.emptyList();
	}

}
