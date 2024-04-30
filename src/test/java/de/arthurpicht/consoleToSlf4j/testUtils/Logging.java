package de.arthurpicht.consoleToSlf4j.testUtils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;

public class Logging {

    public static void initLoggerToConsole(String loggerName) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder patternLayoutEncoderFile = new PatternLayoutEncoder();
        patternLayoutEncoderFile.setPattern("%d{\"yyyy-MM-dd'T'HH:mm:ss,SSSXXX\", UTC} [%level] %logger{10} - %msg%n");
        patternLayoutEncoderFile.setContext(loggerContext);
        patternLayoutEncoderFile.start();

        PatternLayoutEncoder patternLayoutEncoderConsole = new PatternLayoutEncoder();
        patternLayoutEncoderConsole.setPattern("[%logger{10}] %msg%n");
        patternLayoutEncoderConsole.setContext(loggerContext);
        patternLayoutEncoderConsole.start();

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setEncoder(patternLayoutEncoderConsole);
        consoleAppender.setContext(loggerContext);
        consoleAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger("ROOT");
        // detach "console" which comes from logback default BasicConfigurator
        logger.detachAppender("console");
//        logger.setLevel(Level.TRACE);
//        logger.setAdditive(false); /* set to true if root should log too */

        Logger consoleLogger = (Logger) LoggerFactory.getLogger(loggerName);
        consoleLogger.addAppender(consoleAppender);
        consoleLogger.setLevel(Level.TRACE);
    }

}
