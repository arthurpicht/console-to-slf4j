package de.arthurpicht.consoleToSlf4j;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.messageChannel.MessageChannel;
import de.arthurpicht.console.processor.StringComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.arthurpicht.console.message.StandardStream;
import static de.arthurpicht.console.message.Level.VERBOSE;
import static de.arthurpicht.console.message.Level.VERY_VERY_VERBOSE;

@SuppressWarnings("unused")
public class Slf4jChannel implements MessageChannel {

    private final Slf4jChannelConfiguration slf4JChannelConfiguration;
    private final StringComposer stringComposer;

    public Slf4jChannel(Slf4jChannelConfiguration slf4JChannelConfiguration) {
        this.slf4JChannelConfiguration = slf4JChannelConfiguration;
        this.stringComposer = new StringComposer(false);
    }

    @Override
    public boolean isMuted() {
        return this.slf4JChannelConfiguration.muted();
    }

    @Override
    public void process(Message message) {
        if (!applies(message)) return;
        org.slf4j.event.Level loggerLevel = getLoggerLevel(message);
        String string = this.stringComposer.compose(message);
        if (!message.isLineFeed()) string += "<truncated>";
        Logger logger = LoggerFactory.getLogger(this.slf4JChannelConfiguration.loggerName());
        if (message.isClearLine())
            logger.atLevel(loggerLevel).log("<last line deleted>");
        logger.atLevel(loggerLevel).log(string);
    }

    private org.slf4j.event.Level getLoggerLevel(Message message) {
        if (message.getTarget() == StandardStream.ERROR) return org.slf4j.event.Level.ERROR;
        if (message.getLevel() == VERBOSE) return org.slf4j.event.Level.DEBUG;
        if (message.getLevel() == VERY_VERY_VERBOSE) return org.slf4j.event.Level.TRACE;
        return org.slf4j.event.Level.INFO;
    }

    private boolean applies(Message message) {
        Level level = this.slf4JChannelConfiguration.hasLevel() ?
                this.slf4JChannelConfiguration.level() :
                Console.getConfiguration().getLevel();
        return Level.applies(message.getLevel(), level);
    }

}
