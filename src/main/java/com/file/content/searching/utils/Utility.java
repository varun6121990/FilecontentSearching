package com.file.content.searching.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utility {
	
	private static Logger log = LogManager.getLogger(Utility.class);
	
	private Utility() {}
	
	public static final void logMemory() {
		
		log.info(" Free memory : {} ,Total Memory : {}, Max memory : {}", Runtime.getRuntime().freeMemory(), Runtime.getRuntime().totalMemory(), Runtime.getRuntime().maxMemory());
    }
}
