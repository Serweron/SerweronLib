package pl.serweron.serweronLib.command.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the usage pattern for a command.
 * This annotation should be applied to classes that represent commands,
 * providing a clear description of how the command should be used.
 *
 * Example usage:
 * <pre>
 * {@code
 * @Usage(usage = "/mycommand <arg1> <arg2>")
 * public class MyCommand extends Command {
 *     // Command implementation
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Usage {
    /**
     * The usage string for the command.
     * This should describe how to use the command, including any required arguments.
     *
     * @return the usage string
     */
    String usage();
}
