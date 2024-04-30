package de.arthurpicht.consoleToSlf4j;

import de.arthurpicht.console.message.Level;

public record Slf4jChannelConfiguration(boolean muted, String loggerName, Level level) {

    public boolean hasLevel() {
        return this.level != null;
    }

}
