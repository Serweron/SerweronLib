package pl.serweron.serweronLib.logger;

import lombok.Data;

/**
 * Represents a logging module with a name and debug mode status.
 */
@Data
public class LogModule {
    private final String name;
    private boolean debugEnabled;

    /**
     * Constructs a new LogModule with the specified name and debug mode.
     *
     * @param name         the name of the logging module
     * @param debugEnabled whether debug mode is enabled for this module
     */
    public LogModule(String name, boolean debugEnabled) {
        this.name = name;
        this.debugEnabled = debugEnabled;
    }
}
