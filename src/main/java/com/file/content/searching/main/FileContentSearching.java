package com.file.content.searching.main;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.file.content.searching.service.impl.ReadFileUsingScanners;
import com.file.content.searching.service.impl.ReadFileUsingStreams;
import com.file.content.searching.utils.Utility;

public class FileContentSearching {

	private static Logger log = LogManager.getLogger(FileContentSearching.class);

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		log.info("Enter filename with full path :");

		String filePathWithFileName = scanner.nextLine();
		log.info("filePathWithFileName is: {}", filePathWithFileName);

		scanner.close();

		ReadFileUsingStreams readFileUsingStreams = new ReadFileUsingStreams();

		Instant startTime = Instant.now();

		Utility.logMemory();

		readFileUsingStreams.getStringPositionDetailsForInputText(filePathWithFileName);

		Instant endTime = Instant.now();
		long timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();

		Utility.logMemory();

		log.info("ReadFileUsingStreams :: timeElapsedLineCount {} millis", timeElapsedLineCount);

		log.info("\n\n");

		ReadFileUsingScanners readFileUsingScanners = new ReadFileUsingScanners();

		startTime = Instant.now();

		Utility.logMemory();

		readFileUsingScanners.getStringPositionDetailsForInputText(filePathWithFileName);

		endTime = Instant.now();
		timeElapsedLineCount = Duration.between(startTime, endTime).toMillis();

		Utility.logMemory();

		log.info("ReadFileUsingScanners :: timeElapsedLineCount {} millis", timeElapsedLineCount);

	}

}
