package com.file.content.searching.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.content.searching.bean.PositionDetailsBean;
import com.file.content.searching.bean.SearchTextResponseBean;
import com.file.content.searching.utils.Utility;

public class ReadFileUsingStreams {
	
	private static Logger log = LogManager.getLogger(ReadFileUsingStreams.class);
	
	/**
	 * @param filePathWithFileName
	 * @throws IOException
	 */
	public void getStringPositionDetailsForInputText(String filePathWithFileName) throws IOException {
		
		List<SearchTextResponseBean> searchTextResponseBeanList = new ArrayList<>();
		
		Instant startTime = Instant.now();
		
		Utility.logMemory();
		
		try(Stream<String> inputStream = Files.lines(Paths.get(filePathWithFileName), StandardCharsets.UTF_8)) {
			
			Utility.logMemory();
			
			AtomicInteger lineCounter = new AtomicInteger(0);
			
			inputStream.forEachOrdered(fileContent -> {
				
				lineCounter.incrementAndGet();
				
				if(Objects.nonNull(fileContent)) {
					
					Utility.regexPatternList.forEach(regexString -> {
						
						SearchTextResponseBean searchTextResponseBean = validatePatternAndSetPositionDetails(regexString, lineCounter, fileContent);
						
						if(Objects.nonNull(searchTextResponseBean)) {
							
							searchTextResponseBeanList.add(searchTextResponseBean);
						}
					});
				}
			});
			
			Instant endTime = Instant.now();
			long timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();
			
			Utility.logMemory();
			
			log.info("timeElapsedLineCount {} millis",timeElapsedLineCount);
			
			log.info("Response via ReadFileUsingStreams : {}", new ObjectMapper().writeValueAsString(searchTextResponseBeanList));
	        
	    } catch(Exception exception) {
	    	
	    	log.error("Exception in processing the file via ReadFileUsingStreams : ",exception);
	    }
	}

	/**
	 * @param regexPattern
	 * @param lineCounter
	 * @param fileContent
	 * @return
	 */
	private SearchTextResponseBean validatePatternAndSetPositionDetails(String regexPattern, AtomicInteger lineCounter, String fileContent) {
		
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(fileContent);
		
		//if(matcher.find()) {
		
			List<PositionDetailsBean> positionDetailsBeanList = new ArrayList<>();
			SearchTextResponseBean searchTextResponseBean = new SearchTextResponseBean();
			
			while(matcher.find()) {
			    
				searchTextResponseBean.setSearchText(matcher.group());
				
			    PositionDetailsBean positionDetailsBean = new PositionDetailsBean();
				positionDetailsBean.setLineNumber(lineCounter.get());
				positionDetailsBean.setStartingPosition(matcher.start());
				positionDetailsBean.setEndingPosition(matcher.end());
				
				positionDetailsBeanList.add(positionDetailsBean);
			}
			
			searchTextResponseBean.setPositionDetailsBeanList(positionDetailsBeanList);
			
			return searchTextResponseBean;
		//}
		
		//return null;
	}

}
