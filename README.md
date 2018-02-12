# logback_LevelPatternLayout
Special PatternLayout where formatting patterns can be specified per level

Example logback.xml:

    <configuration scan="true" debug="true">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
                <layout class="pack.LevelPatternLayout">
                    <defaultPattern>DEFAULT PATTERN %c - %m\n</defaultPattern>
                    <debugPattern>DEBUG PATTERN %c - %m\n</debugPattern>
                </layout>
            </encoder>
        </appender>
        
        <root level="DEBUG">
            <appender-ref ref="CONSOLE" />
        </root>
    </configuration>
    
Test code:

    Logger log = LoggerFactory.getLogger(TestStart.class);

    @Test
    public void test() throws Exception{
        log.debug("some debug level log");
        log.info("some info level log");
        log.error("some error level log");
    }

Output:

    DEBUG PATTERN pack.TestStart - some debug level log
    DEFAULT PATTERN pack.TestStart - some info level log
    DEFAULT PATTERN pack.TestStart - some error level log

