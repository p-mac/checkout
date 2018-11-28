package org.test.itv.checkout.config;

import org.test.itv.checkout.dto.BaseDTO;
import org.test.itv.checkout.util.KEYS;

/**
 * Created on 21/11/18.
 */
public class ServerConfig extends BaseDTO {

    private String address = "localhost";
    private Integer port = 8080;
    private String basePath = KEYS.BLANK;
    private String welcome = "CHECKOUT APP SERVER AVAILABLE";

    public ServerConfig() { }

    public ServerConfig(String address, Integer port, String basePath) {
        this.address = address;
        this.port = port;
        this.basePath = basePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
}
