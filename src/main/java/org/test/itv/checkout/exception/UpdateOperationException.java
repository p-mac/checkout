package org.test.itv.checkout.exception;

/**
 * Created on 26/11/18.
 */
public class UpdateOperationException extends Exception {

    public UpdateOperationException() { }

    public UpdateOperationException(String message) {
        super(message);
    }

    public UpdateOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateOperationException(Throwable cause) {
        super(cause);
    }

    public UpdateOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
