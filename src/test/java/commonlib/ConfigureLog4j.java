package commonlib;


import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
 
public class ConfigureLog4j {
	//public static String fullClassName;
    public static Logger getConigLogger(String fullClassName) {
        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);
 
        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
 
        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("applog3.txt");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();
 
        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);
 
        // creates a custom logger and log messages
       
        Logger logger = Logger.getLogger(fullClassName);
        return logger;
    }
}