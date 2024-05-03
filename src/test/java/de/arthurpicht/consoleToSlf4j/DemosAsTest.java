package de.arthurpicht.consoleToSlf4j;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.consoleToSlf4j.testUtils.Logging;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class DemosAsTest {

    @Test
    @Order(7)
    public void simpleColoredLineWithLoggerDelegation() {
        Logging.initLoggerToConsole("CONSOLE");

        Slf4jChannel slf4jChannel = new Slf4jChannelBuilder()
                .withLoggerName("CONSOLE")
                .build();

        Console.configure(new ConsoleConfigurationBuilder()
                .addMessageChannel(slf4jChannel)
                .build());

        Console.println("red Text", Format.RED_TEXT());

        Console.configureWithDefaults();
    }

}
