package org.test.itv.checkout.exception;

/**
 * Created on 26/11/18.
 */
public class CreateOperationException extends Exception {

    public CreateOperationException() { }

    public CreateOperationException(String message) {
        super(message);
    }

    public CreateOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateOperationException(Throwable cause) {
        super(cause);
    }

    public CreateOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
