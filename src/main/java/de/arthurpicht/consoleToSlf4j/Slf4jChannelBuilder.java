package de.arthurpicht.consoleToSlf4j;

import de.arthurpicht.console.message.Level;
import de.arthurpicht.utils.core.strings.Strings;

public class Slf4jChannelBuilder {

    private boolean muted = false;
    private String loggerName;
    private Level level = null;

    public Slf4jChannelBuilder withLoggerName(String loggerName) {
        this.loggerName = loggerName;
        return this;
    }

    public Slf4jChannelBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    public Slf4jChannelBuilder withLevelInheritedFromConsoleConfiguration() {
        this.level = null;
        return this;
    }

    public Slf4jChannelBuilder withMutedOutput() {
        this.muted = true;
        return this;
    }

    public Slf4jChannelBuilder withMutedOutput(boolean muted) {
        this.muted = muted;
        return this;
    }

    public Slf4jChannel build() {
        if (!Strings.isSpecified(this.loggerName))
            throw new IllegalArgumentException("Parameter [loggerName] must be specified");
        Slf4jChannelConfiguration slf4jChannelConfiguration = new Slf4jChannelConfiguration(
                this.muted,
                this.loggerName,
                this.level
        );
        return new Slf4jChannel(slf4jChannelConfiguration);
    }

}
