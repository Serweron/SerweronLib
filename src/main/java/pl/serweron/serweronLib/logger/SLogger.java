package pl.serweron.serweronLib.logger;

import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A simple logging utility for managing log messages with different severity levels and modules.
 */
public class SLogger {
    private final Logger backend;
    private final Map<String, LogModule> modules = new HashMap<>();

    /**
     * Constructs an SLogger instance using the provided plugin's logger.
     *
     * @param plugin the plugin whose logger will be used as the backend
     */
    public SLogger(Plugin plugin) {
        this.backend = plugin.getLogger();
    }

    /**
     * Retrieves a LogModule by name, creating it if it doesn't exist.
     *
     * @param name the name of the logging module
     * @return the LogModule instance
     */
    public LogModule getModule(String name) {
        return modules.computeIfAbsent(name, n -> new LogModule(n, false));
    }

    /**
     * Logs a message with the specified level and module.
     *
     * @param level  the severity level of the log message
     * @param module the name of the logging module
     * @param msg    the log message format string
     * @param args   the arguments for the log message format string
     */
    public void log(LogLevel level, String module, String msg, Object... args) {
        LogModule mod = getModule(module);
        if ((level == LogLevel.DEBUG || level == LogLevel.TRACE) && !mod.isDebugEnabled()) return;

        String prefix = String.format("[%s] [%s] ", module, level);
        String message = prefix + String.format(msg, args);

        switch(level) {
            case TRACE, DEBUG, INFO -> backend.info(message);
            case WARN -> backend.warning(message);
            case ERROR -> backend.severe(message);
        }
    }

    // Convenience methods for each log level
    public void info(String module, String msg, Object... args) { log(LogLevel.INFO, module, msg, args); }
    public void warn(String module, String msg, Object... args) { log(LogLevel.WARN, module, msg, args); }
    public void error(String module, String msg, Object... args) { log(LogLevel.ERROR, module, msg, args); }
    public void debug(String module, String msg, Object... args) { log(LogLevel.DEBUG, module, msg, args); }
    public void trace(String module, String msg, Object... args) { log(LogLevel.TRACE, module, msg, args); }
}
