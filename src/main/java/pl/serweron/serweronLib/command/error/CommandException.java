package pl.serweron.serweronLib.command.error;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }
}
