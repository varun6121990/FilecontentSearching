package com.file.content.searching.main;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.file.content.searching.service.impl.ReadFileUsingStreams;
import com.file.content.searching.utils.Utility;

public class FileContentSearching {
	
	private static Logger log = LogManager.getLogger(FileContentSearching.class);

	public static void main(String[] args) throws IOException {
		
		ReadFileUsingStreams readFileUsingStreams = new ReadFileUsingStreams();
		
		Instant startTime = Instant.now();
		
		Utility.logMemory();
		
		readFileUsingStreams.getStringPositionDetailsForInputText("/Users/vseeema690/Downloads/indiv18/by_date/testing.txt");
		
		Instant endTime = Instant.now();
		long timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();
		
		Utility.logMemory();
		
		log.info("timeElapsedLineCount {} millis",timeElapsedLineCount);
			
	}

}
