package com.fang.week7lab.utils;

import org.apache.log4j.Logger;

public class SDLogger {
	private static class Holder {
        private static final SDLogger _instance = new SDLogger();
    }
	
	private final Logger logger;
	private static final String kLoggerName = "Week7Lab";
    
    private SDLogger() {
    	logger = Logger.getLogger(kLoggerName) ;
    }
    
    public static SDLogger instance() {
        return Holder._instance;
    }
    
    public static void warn(Object msg) {
    	SDLogger.instance().logger.warn(msg);
    }
    
    public static void info(Object msg) {
    	SDLogger.instance().logger.info(msg);
    }
    
    public static void debug(Object msg) {
    	SDLogger.instance().logger.debug(msg);
    }
    
    public static void error(Object msg) {
    	SDLogger.instance().logger.error(msg);
    }
}
