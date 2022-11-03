package com.file.content.searching.main;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.file.content.searching.service.impl.ReadFileUsingStreams;
import com.file.content.searching.utils.Utility;

public class FileContentSearching {
	
	private static Logger log = LogManager.getLogger(FileContentSearching.class);

	public static void main(String[] args) throws IOException {
		
		ReadFileUsingStreams readFileUsingStreams = new ReadFileUsingStreams();
		
		readFileUsingStreams.getStringPositionDetailsForInputText("/Users/vseeema690/Downloads/indiv18/by_date/testing.txt");
		
		//Users/vseeema690/Downloads/indiv18/by_date/itcont_2018_invalid_dates.txt
		
		
		/*Instant lineCountStart = Instant.now();
		
        Stream<String> lines = Files.lines(Paths.get("/Users/vseeema690/Downloads/indiv18/itcont.txt"));
        
        

        Instant lineCountEnd = Instant.now();
        
        long timeElapsedLineCount = Duration.between(lineCountStart, lineCountEnd).toMillis();
        
        System.out.println("Line count time: " + timeElapsedLineCount + "ms");
        
        Instant lineCountStart = Instant.now();
        
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/vseeema690/Downloads/indiv18/itcont.txt"));
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(content);
        
        Instant lineCountEnd = Instant.now();
        
        long timeElapsedLineCount = Duration.between(lineCountStart, lineCountEnd).toMillis();
        
        System.out.println("Line count time: " + timeElapsedLineCount + "ms");*/
		
		Utility.regexPatternList.forEach(regexString -> {
		
			System.out.println(regexString);
			Pattern pattern = Pattern.compile(regexString);
			Matcher matcher = pattern.matcher("Take immediate action to stop the violation and notify King County Industrial Waste within 24 hours and 48 hours of learning of the violation. Whenever an effluent check shows a pH violation, as defined in King County Code 28.84.060.N \"Violations,\" the Permittee shall take immediate steps to bring the discharge back into compliance. If this is not possible, the Permittee shall cease discharge. At no time shall two successive readings on an explosive hazard meter at the point of discharge into the King County sewerage system (or at any point in the system) be more than five percent (5%) of the LEL. No single reading shall exceed ten percent (10%) of the LEL.");
			
			while(matcher.find()) {
				System.out.println(matcher.group(0));
			}
			matcher.reset();
		});
		
	}

}
