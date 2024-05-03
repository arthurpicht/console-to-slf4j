# console-to-slf4j

Channeling console messages to slf4j. An extension to project arthurpicht/console.

Example:

```java
Slf4jChannel slf4jChannel = new Slf4jChannelBuilder()
        .withLoggerName("CONSOLE")                                
        .build();

Console.configure(new ConsoleConfigurationBuilder()
        .addMessageChannel(slf4jChannel)
        .build());
```

See `de.arthurpicht.consoleToSlf4j.Slf4jChannelBuilder` for more infos.
