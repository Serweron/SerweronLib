package pl.serweron.serweronLib.utils;

import lombok.Getter;

public class Response<T> {
    /**
     * Enum for types of Responses indicating the status of a method call.
     */
    public enum ResponseType {
        SUCCESS(1),
        FAILURE(2),
        NOT_IMPLEMENTED(3);

        private int id;

        ResponseType(int id) {
            this.id = id;
        }

        int getId() {
            return id;
        }
    }

    /**
     * Success or failure of call. Using Enum of ResponseType to determine valid
     * outcomes
     */
    protected final ResponseType type;
    /**
     * Error message if the variable 'type' is ResponseType.FAILURE
     */
    @Getter
    protected final String errorMessage;

    /**
     * Data object
     */
    @Getter
    protected final T data;

    /**
     * Constructor for Response
     * @param type Success or failure type of the operation
     * @param errorMessage Error message if necessary (commonly null)
     * @param data Data object if necessary (commonly null)
     */
    public Response(ResponseType type, String errorMessage, T data) {
        this.type = type;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.type == ResponseType.SUCCESS;
    }
    public boolean isFailure() {
        return this.type == ResponseType.FAILURE;
    }
    public boolean isNotImplemented() {
        return this.type == ResponseType.NOT_IMPLEMENTED;
    }
}
