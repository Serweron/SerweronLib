package pl.serweron.serweronLib.commands.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandData {
    String[] aliases() default {};
    String permission() default "";
    String description() default "Command description not provided";
}
