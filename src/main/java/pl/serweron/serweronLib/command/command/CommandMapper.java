package pl.serweron.serweronLib.command.command;

import lombok.Data;
import pl.serweron.serweronLib.command.annotations.*;
import pl.serweron.serweronLib.command.context.completions.CompletionProvider;

import java.util.HashMap;
import java.util.Map;

public class CommandMapper {

    public static MappedCommand mapCommand(Class<? extends BaseCommand> commandClass) {
        MappedCommand mappedCommand = new MappedCommand();

        /* =====================
           Class-level metadata
           ===================== */

        if (commandClass.isAnnotationPresent(Aliases.class)) {
            mappedCommand.setAliases(
                commandClass.getAnnotation(Aliases.class).value()
            );
        }

        if (commandClass.isAnnotationPresent(Permission.class)) {
            mappedCommand.setPermission(
                commandClass.getAnnotation(Permission.class).value()
            );
        }

        if (commandClass.isAnnotationPresent(Description.class)) {
            mappedCommand.setDescription(
                commandClass.getAnnotation(Description.class).value()
            );
        }

        if (commandClass.isAnnotationPresent(ConsoleOnly.class)) {
            mappedCommand.setConsoleOnly(true);
        }

        if (commandClass.isAnnotationPresent(PlayerOnly.class)) {
            mappedCommand.setPlayerOnly(true);
        }

        /* =====================
           Validation
           ===================== */

        if (mappedCommand.isConsoleOnly() && mappedCommand.isPlayerOnly()) {
            throw new IllegalStateException(
                commandClass.getName() +
                    " cannot be both @ConsoleOnly and @PlayerOnly"
            );
        }

        /* =====================
           AutoComplete mapping
           ===================== */

        Map<Integer, Class<? extends CompletionProvider>> completions = new HashMap<>();

        AutoComplete[] completes =
            commandClass.getAnnotationsByType(AutoComplete.class);

        for (AutoComplete ac : completes) {
            int index = ac.index();

            if (completions.containsKey(index)) {
                throw new IllegalStateException(
                    "Duplicate @AutoComplete for index " + index +
                        " in command " + commandClass.getName()
                );
            }

            completions.put(index, ac.value());
        }

        mappedCommand.setAutoCompletes(completions);

        return mappedCommand;
    }

    /* ===================================================== */

    @Data
    public static class MappedCommand {
        private String[] aliases = new String[0];
        private String permission;
        private String description;
        private boolean consoleOnly;
        private boolean playerOnly;

        private Map<Integer, Class<? extends CompletionProvider>> autoCompletes = new HashMap<>();
    }
}
