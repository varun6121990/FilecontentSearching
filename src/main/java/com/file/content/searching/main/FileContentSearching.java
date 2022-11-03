package com.file.content.searching.main;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.file.content.searching.service.impl.ReadFileUsingBufferReader;
import com.file.content.searching.service.impl.ReadFileUsingScanners;
import com.file.content.searching.service.impl.ReadFileUsingStreams;

/**
 * This class is an entry point where user is given an option to choose type of reader and file name.
 * 
 * Case:1 = ReadFileUsingStreams
 * Case:2 = ReadFileUsingScanners
 * Case:3 = ReadFileUsingBufferReader
 * 
 */
public class FileContentSearching {

	private static Logger log = LogManager.getLogger(FileContentSearching.class);

	public static void main(String[] args) throws IOException {
		
		log.info("Option 1 - Run with ReadFileUsingStreams");
		log.info("Option 2 - Run with ReadFileUsingScanner");
		log.info("Option 3 - Run with ReadFileUsingBufferReader");
		
		Scanner scanner = new Scanner(System.in);
		log.info("Enter option :");

		String option = scanner.nextLine();
		
		log.info("Enter file name with full path :");
		
		String filePathWithFileName = scanner.nextLine();
		
		scanner.close();
		
		switch(option)
		{
		    case "1":
		    	
		    	log.info("==================== ReadFileUsingStreams ======================");
		    	
		    	ReadFileUsingStreams readFileUsingStreams = new ReadFileUsingStreams();

				Instant startTime = Instant.now();

				readFileUsingStreams.getStringPositionDetailsForInputText(filePathWithFileName);

				Instant endTime = Instant.now();
				
				long timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();

				log.info("ReadFileUsingStreams :: timeElapsedLineCount {} millis", timeElapsedLineCount);

				log.info("==================================================================");
				
			    break;
			    
		    case "2":
		    	
		    	log.info("==================== ReadFileUsingScanners ======================");
		    	
		    	ReadFileUsingScanners readFileUsingScanners = new ReadFileUsingScanners();

				startTime = Instant.now();

				readFileUsingScanners.getStringPositionDetailsForInputText(filePathWithFileName);

				endTime = Instant.now();
				
				timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();

				log.info("ReadFileUsingScanners :: timeElapsedLineCount {} millis", timeElapsedLineCount);/**/
				
				log.info("==================================================================");
				
			    break;
			    
		    case "3":
		    	
		    	log.info("==================== ReadFileUsingBufferReader ======================");
		    	
		    	ReadFileUsingBufferReader readFileUsingBufferReader = new ReadFileUsingBufferReader();

				startTime = Instant.now();

				readFileUsingBufferReader.getStringPositionDetailsForInputText(filePathWithFileName);

				endTime = Instant.now();
				
				timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();

				log.info("ReadFileUsingBufferReader :: timeElapsedLineCount {} millis", timeElapsedLineCount);
				
			    break;
		    
		    default:
		    	
		    	log.info("Invalid option");
		    	
		    	break;
		}

	}

}
