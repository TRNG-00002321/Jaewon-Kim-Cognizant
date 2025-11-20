package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemo {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggingDemo.class);

        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
    }
}
