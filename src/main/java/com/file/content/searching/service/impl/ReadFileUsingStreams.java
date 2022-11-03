package com.file.content.searching.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.content.searching.bean.SearchTextResponseBean;
import com.file.content.searching.service.defn.ReadFileContent;
import com.file.content.searching.service.helper.ServiceHelper;
import com.file.content.searching.utils.RegexGroupEnum;

/**
 * This class will process the user input file based on Streams Files.lines(...)
 *
 */
public class ReadFileUsingStreams implements ReadFileContent {
	
	private static Logger log = LogManager.getLogger(ReadFileUsingStreams.class);
	
	/**
	 * 
	 * This method will get the file name with its full path as input and process lines of file
	 * against regex and prints the json response of search text and its corresponding positions
	 * 
	 * @param filePathWithFileName
	 * @throws IOException
	 */
	@Override
	public void getStringPositionDetailsForInputText(String filePathWithFileName) throws IOException {
		
		List<SearchTextResponseBean> searchTextResponseBeanList = new ArrayList<>();
		
		try(Stream<String> inputStream = Files.lines(Paths.get(filePathWithFileName), StandardCharsets.UTF_8)) {
			
			AtomicInteger lineCounter = new AtomicInteger(0);
			
			inputStream.forEachOrdered(fileContent -> {
				
				lineCounter.incrementAndGet();
				
				if(Objects.nonNull(fileContent)) {
					
					EnumSet.allOf(RegexGroupEnum.class).forEach(enumValues -> {
						
						List<SearchTextResponseBean> intermediaryResponseList = ServiceHelper.validatePatternAndSetPositionDetails(enumValues.getRegexPattern(), enumValues.getMatcherGroup(), lineCounter, fileContent);
						
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

}
