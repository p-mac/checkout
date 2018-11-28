package org.test.itv.checkout.exception;

/**
 * Created on 26/11/18.
 */
public class DeleteOperationException extends Exception {

    public DeleteOperationException() { }

    public DeleteOperationException(String message) {
        super(message);
    }

    public DeleteOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteOperationException(Throwable cause) {
        super(cause);
    }

    public DeleteOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
