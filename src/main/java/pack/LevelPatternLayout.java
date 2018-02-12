package pack;

import java.util.HashMap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

/**
 * A logback layout which can be configured with different pattern for different levels.
 *
 * E.g. you can configure a pattern for DEBUG messages and an *other* pattern
 * for INFO messages and so on.
 *
 *
 */
public class LevelPatternLayout extends LayoutBase<ILoggingEvent> {
    
    private boolean started;

    // store for patternLayouts per level. The layout stored with 'null' key is the fallback
    private HashMap<Level, PatternLayout> layouts = new HashMap<>();
    
    public LevelPatternLayout() {
    }
    
    public void setDefaultPattern(String pattern) {
        setLevelPattern(null, pattern);
    }

    public void setTracePattern(String pattern) {
        setLevelPattern(Level.TRACE, pattern);
    }

    public void setDebugPattern(String pattern) {
        setLevelPattern(Level.DEBUG, pattern);
    }
    
    public void setInfoPattern(String pattern) {
        setLevelPattern(Level.INFO, pattern);
    }
    
    public void setWarnPattern(String pattern) {
        setLevelPattern(Level.WARN, pattern);
    }
    
    public void setErrorPattern(String pattern) {
        setLevelPattern(Level.ERROR, pattern);
    }
    
    private void setLevelPattern(Level level, String pattern) {
        PatternLayout layout = new PatternLayout();
        layout.setContext(context);
        layout.setPattern(pattern);
        layouts.put(level, layout);
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        PatternLayout matchingLayout = layouts.get(event.getLevel());
        if(matchingLayout != null) {
            return matchingLayout.doLayout(event);
        }
        return layouts.get(null).doLayout(event);
    }
    
    @Override
    public void start() {
        if(layouts.get(null) == null) {
            throw new RuntimeException("default layout is not initialized, probably no 'defaultPattern' is configured!");
        }
        layouts.values().forEach(layout -> layout.start());
        this.started = true;
    }

    @Override
    public void stop() {
        layouts.values().forEach(layout -> layout.stop());
        this.started = false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

}
