package pl.serweron.serweronLib.commands.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to provide metadata for command classes.
 * This annotation should be applied to classes that represent commands,
 * allowing for easy retrieval of command information such as aliases, permission requirements, and descriptions.
 *
 * Example usage:
 * <pre>
 * {@code
 * @CommandData(aliases = {"mycmd", "my-command"}, permission = "myplugin.mycmd", description = "My command description")
 * public class MyCommand extends Command {
 *     // Command implementation
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandData {
    /**
     * The aliases for the command.
     * These are alternative names that can be used to invoke the command.
     *
     * @return an array of aliases
     */
    String[] aliases() default {};
    /**
     * The permission required to execute the command.
     * If specified, only players with this permission can use the command.
     *
     * @return the permission string
     */
    String permission() default "";
    /**
     * A brief description of the command.
     * This should explain what the command does and any important details.
     *
     * @return the command description
     */
    String description() default "Command description not provided";
}
