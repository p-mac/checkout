package org.test.itv.checkout.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.test.itv.checkout.logging.LogManager;
import org.test.itv.checkout.logging.Logger;
import org.test.itv.checkout.util.MapperFactory;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;

/**
 * The ConfigManager Singleton, provides access to the base configuration loaded from an external json file
 */
public class ConfigManager {

    private static final ConfigManager insntance = new ConfigManager();
    private static Logger logger = LogManager.getLogger(ConfigManager.class);
    private static final String CONF = "conf/";
    private static final String EXT = ".json";

    private ConfigManager() {
        try {
            init();
        } catch (IOException e) {
            logger.error("Configuration initialisation failed", e);
        }
    }

    /**
     * getInstance method returns the Singleton instance
     * @return ConfigManager instance
     */
    public static ConfigManager getInsntance() {
        return insntance;
    }

    /**
     * init method initialises the ConfigManager main configuration
     * @throws IOException
     */
    public void init() throws IOException {
    }

    /**
     * getConfig retrieves the configuration from file based on a given key
     * @param name
     * @return JsonNode
     * @throws InvalidObjectException
     */
    public JsonNode getConfig(String name) throws InvalidObjectException {
        String path = CONF.concat(name).concat(EXT);
        File base = new File(path);
        ObjectMapper mapper = MapperFactory.getMapper();
        try {
            return (base.exists()) ? mapper.readTree(base) : mapper.readTree(getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            String message = "ERROR loading the configuration for ".concat(name);
            logger.error(message, e);
            throw new InvalidObjectException(e.getMessage());
        }
    }

    /**
     * getConfig retrieves the configuration from file based on a given key and binds it to a given Class<T>
     * @param name
     * @param configclass
     * @param <T>
     * @return T instance
     */
    public <T> T getConfig(String name, Class<T> configclass) throws InvalidObjectException {
        String path = CONF.concat(name).concat(EXT);
        File base = new File(path);
        ObjectMapper mapper = MapperFactory.getMapper();
        try {

            return (base.exists()) ? mapper.readValue(base, configclass) : mapper.readValue(getClass().getClassLoader().getResourceAsStream(path), configclass);
        } catch (IOException e) {
            String message = "ERROR loading the configuration for ".concat(name);
            logger.error(message, e);
            throw new InvalidObjectException(e.getMessage());
        }
    }

}
