package org.test.itv.checkout.datasource;

import org.test.itv.checkout.logging.LogManager;
import org.test.itv.checkout.logging.Logger;
import org.test.itv.checkout.config.ConfigManager;
import org.test.itv.checkout.util.ClassLoadUtil;

import java.io.InvalidObjectException;
import java.util.HashMap;


/**
 * Created on 23/11/18.
 */
public class DataSourceProvider {

    private static final Logger logger = LogManager.getLogger(DataSourceProvider.class);
    private static final DataSourceProvider instance = new DataSourceProvider();
    private HashMap<String, DataSourceAdapter> adapters = new HashMap<>();
    private DataProviderConfig config;

    private DataSourceProvider() { }

    public static DataSourceProvider getInstance() {
        return instance;
    }

    /**
     * it performs the initialisztion activities, loading all the DataSourceAdapters into the mapping,
     * based on the configuration retrieved from the ConfigManager
     */
    public DataSourceProvider init() {
        try {
            this.config = ConfigManager.getInsntance().getConfig("datasources", DataProviderConfig.class);
        } catch (InvalidObjectException e) {
            logger.error("ERROR on DataSourceProvider config not found ", e);
        }
        config.getDatasources().entrySet().forEach(entry -> {
            try {
                DataSourceAdapter adapter = ClassLoadUtil.getObject(entry.getValue().getClassName());
                this.adapters.put(entry.getKey(), adapter.init());
            } catch (ClassNotFoundException e) {
                logger.error("ERROR on DataSourceAdapter creation, Class name not found ".concat(entry.getKey()), e);
            } catch (IllegalAccessException e) {
                logger.error("ERROR on DataSourceAdapter creation, Illegal Access thrown ".concat(entry.getKey()), e);
            } catch (InstantiationException e) {
                logger.error("ERROR on DataSourceAdapter creation, Instantiation not possible for given class ".concat(entry.getKey()), e);
            }
        });
        return this;
    }

    /**
     * getDataSource is the method for retrieving the DataSource for the name provided,
     * the DataSource has to be present in the "adapters" Map,
     * it throws an esception if there is not DataSourceAdapter matching the given name
     * @param name
     * @return MapDBDataSourceAdapter instance
     * @throws UnsupportedOperationException
     */
    public DataSourceAdapter getDataSource(String name) throws UnsupportedOperationException {
        if (!adapters.containsKey(name)) {
            throw new UnsupportedOperationException("ERROR No Resource found with the given name: ".concat(name));
        }
        return adapters.get(name);
    }


    /**
     * it performs the destroy operations for the DataSourceProvider,
     * it will call dispose() on each of the mapped DataSourceAdapters and clear the mapping
     */
    public void destroy() {
        this.adapters.entrySet().stream().forEach((entry) -> {
            entry.getValue().dispose();
            entry.setValue(null);
        });
        this.adapters.clear();
    }
}
