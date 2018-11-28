package org.test.itv.checkout.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.test.itv.checkout.dto.BaseDTO;

import java.beans.Transient;
import java.util.LinkedHashMap;

/**
 * Created on 23/11/18.
 */
public class DataProviderConfig extends BaseDTO {

    private LinkedHashMap<String, DataSourceConfig> datasources = new LinkedHashMap<>();


    public LinkedHashMap<String, DataSourceConfig> getDatasources() {
        return datasources;
    }

    public void setDatasources(LinkedHashMap<String, DataSourceConfig> datasources) {
        this.datasources = datasources;
    }

    @JsonIgnore
    @Transient
    public DataSourceConfig getDataSource(String name) {
        return this.datasources.get(name);
    }

}
