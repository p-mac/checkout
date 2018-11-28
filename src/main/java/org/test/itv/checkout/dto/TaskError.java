package org.test.itv.checkout.dto;

/**
 * Created on 26/11/18.
 */
public class TaskError extends BaseDTO {

    private String entityClassName;
    private String code;
    private String message;

    public TaskError() { }

    public TaskError(String entityClassName, String message) {
        this.entityClassName = entityClassName;
        this.message = message;
    }

    public TaskError(String entityClassName, String code, String message) {
        this.entityClassName = entityClassName;
        this.code = code;
        this.message = message;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
