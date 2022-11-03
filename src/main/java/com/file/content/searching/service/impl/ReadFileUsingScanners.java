package com.file.content.searching.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.file.content.searching.bean.SearchTextResponseBean;
import com.file.content.searching.service.defn.ReadFileContent;
import com.file.content.searching.service.helper.ServiceHelper;
import com.file.content.searching.utils.RegexGroupEnum;

public class ReadFileUsingScanners implements ReadFileContent {

	private static Logger log = LogManager.getLogger(ReadFileUsingScanners.class);

	@Override
	public void getStringPositionDetailsForInputText(String filePathWithFileName) throws IOException {
		
		List<SearchTextResponseBean> searchTextResponseBeanList = new ArrayList<>();
		
		try(InputStream inputStream = new FileInputStream(filePathWithFileName);
				Scanner fileScanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
			
			AtomicInteger lineCounter = new AtomicInteger(0);
			
			while(fileScanner.hasNextLine()){
				
				lineCounter.incrementAndGet();
				
				String fileContent = fileScanner.nextLine();
				
				if(StringUtils.isNotBlank(fileContent)) {
					
					EnumSet.allOf(RegexGroupEnum.class).forEach(enumValues -> {
						
						List<SearchTextResponseBean> intermediaryResponseList = ServiceHelper.validatePatternAndSetPositionDetails(enumValues.getRegexPattern(), enumValues.getMatcherGroup(), lineCounter, fileContent);
						
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
