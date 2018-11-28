package org.test.itv.checkout.datasource;

import org.test.itv.checkout.dto.BaseDTO;

/**
 * Created on 23/11/18.
 */
public class DataSourceConfig extends BaseDTO {

    private String name;
    private String className;

    public DataSourceConfig() { }

    public DataSourceConfig(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
