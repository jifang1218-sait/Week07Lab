package com.fang.week7lab.utils;

import org.apache.log4j.Logger;

public class SDLogger {
	private static class Holder {
        private static final SDLogger _instance = new SDLogger();
    }
	
	private Logger logger;
	private static final String kLoggerName = "Week7Lab";
    
    private SDLogger() {
    	logger = Logger.getLogger(kLoggerName) ;
    }
    
    public static SDLogger instance() {
        return Holder._instance;
    }
    
    public void warn(Object msg) {
    	logger.warn(msg);
    }
    
    public void info(Object msg) {
    	logger.info(msg);
    }
    
    public void debug(Object msg) {
    	logger.debug(msg);
    }
    
    public void error(Object msg) {
    	logger.error(msg);
    }
}
