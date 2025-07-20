package pl.serweron.serweronLib.utils;

import lombok.Getter;

public class Response<T> {

    /**
     * Enum for types of Responses indicating the status of a method call.
     */
    public enum ResponseType {
        SUCCESS,
        FAILURE,
        NOT_IMPLEMENTED;
    }

    /**
     * Success or failure of call. Using Enum of ResponseType to determine valid
     * outcomes
     */
    private final ResponseType type;
    /**
     * Error message if the variable 'type' is ResponseType.FAILURE
     */
    @Getter
    private final String errorMessage;

    /**
     * Data object
     */
    @Getter
    private final T data;

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

    /**
     * Checks if the answer is success
     *
     * @return is the answer success
     */
    public boolean isSuccess() {
        return this.type == ResponseType.SUCCESS;
    }

    /**
     * Checks if the answer is failure
     *
     * @return is the answer failure
     */
    public boolean isFailure() {
        return this.type == ResponseType.FAILURE;
    }

    /**
     * Checks if the answer is not implemented
     *
     * @return is the answer not implemented
     */
    public boolean isNotImplemented() {
        return this.type == ResponseType.NOT_IMPLEMENTED;
    }

    // Factories

    /**
     * Response success factory
     *
     * @param data Data you provide to the user
     * @return Response
     * @param <T> Type of data.
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(ResponseType.SUCCESS, null, data);
    }

    /**
     * Response failure factory
     *
     * @return Response
     */
    public static <T> Response<T> failure(String message) {
        return new Response<>(ResponseType.FAILURE, message, null);
    }

    /**
     * Response notImplemented factory
     *
     * @return Response
     */
    public static <T> Response<T> notImplemented() {
        return new Response<>(ResponseType.NOT_IMPLEMENTED, "Not implemented", null);
    }

}
