package com.file.content.searching.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.content.searching.bean.SearchTextResponseBean;
import com.file.content.searching.service.defn.ReadFileContent;
import com.file.content.searching.service.helper.ServiceHelper;
import com.file.content.searching.utils.RegexGroupEnum;

/**
 * This class will process the user input file based on Buffered Reader
 *
 */
public class ReadFileUsingBufferReader implements ReadFileContent {
	
	private static Logger log = LogManager.getLogger(ReadFileUsingBufferReader.class);

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
		
		try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(filePathWithFileName))) {
            
			AtomicInteger lineCounter = new AtomicInteger(0);
            
			while (fileBufferReader.readLine() != null) {
                
				lineCounter.incrementAndGet();
				
				String fileLineContent = fileBufferReader.readLine();
				
				if(StringUtils.isNotBlank(fileLineContent)) {
					
					EnumSet.allOf(RegexGroupEnum.class).forEach(enumValues -> {
						
						List<SearchTextResponseBean> intermediaryResponseList = ServiceHelper.validatePatternAndSetPositionDetails(enumValues.getRegexPattern(), enumValues.getMatcherGroup(), lineCounter, fileLineContent);
						
						if(CollectionUtils.isNotEmpty(intermediaryResponseList)) {
							
							searchTextResponseBeanList.addAll(intermediaryResponseList);
						}
					});
				}
            }
			
			//log.info("Response via ReadFileUsingScanners : {}", new ObjectMapper().writeValueAsString(searchTextResponseBeanList));
			
        } catch(Exception exception) {
	    	
	    	log.error("Exception in processing the file via ReadFileUsingScanners : ",exception);
	    }
		
	}
	
	

}
