package edu.syr.oodproject.trelloclonesu.common.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLogger {
    private static ApplicationLogger appLogger;
    private Logger logger;
    private ApplicationLogger(){
        logger = LoggerFactory.getLogger(getClass());
    }

    public static ApplicationLogger getApplicationLogger(){
        if(appLogger == null)
            return new ApplicationLogger();
        else
            return appLogger;
    }

    public Logger getLogger() {
        return logger;
    }
}
