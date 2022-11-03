package com.file.content.searching.service.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.file.content.searching.bean.PositionDetailsBean;
import com.file.content.searching.bean.SearchTextResponseBean;

/**
 * This class contains common method for file content processing which will be called by different readers
 *
 */
public class ServiceHelper {
	
	private ServiceHelper() {}
	
	/**
	 * 
	 * This method will generate the json response for the search text with its corresponding position
	 * 
	 * @param regexPattern
	 * @param matcherGroupPosition 
	 * @param lineCounter
	 * @param fileContent
	 * @return
	 */
	public static List<SearchTextResponseBean> validatePatternAndSetPositionDetails(String regexPattern, int matcherGroupPosition, AtomicInteger lineCounter, String fileContent) {
		
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
