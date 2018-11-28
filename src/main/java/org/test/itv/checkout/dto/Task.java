package org.test.itv.checkout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.test.itv.checkout.types.OperationType;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 26/11/18.
 */
public interface Task<T extends Entity> extends Serializable {

    String getId();

    OperationType getOperationType();

    List<T> getEntities();

    void setEntities(List<T> entities);

    DynaMap getParams();

    TaskError getError();

    void setError(TaskError error);

    @JsonIgnore
    @Transient
    Class<T> getEntityClass() throws ClassNotFoundException;

    @JsonIgnore
    @Transient
    void putError(String message);

    @JsonIgnore
    @Transient
    void putError(String code, String message);
}
