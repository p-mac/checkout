package org.test.itv.checkout.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.itv.checkout.config.RouteConfig;
import org.test.itv.checkout.config.RoutingConfig;
import org.test.itv.checkout.config.ServerConfig;
import org.test.itv.checkout.dto.DataMap;
import org.test.itv.checkout.logging.Logger;

import static org.mockito.Mockito.when;

public class ServerTest {
    @Mock
    Logger logger;
    @Mock
    Server instance;
    @Mock
    ServerConfig config;
    @Mock
    RoutingConfig routesMap;
    @InjectMocks
    Server server;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        Server result = server.init();
        Assert.assertEquals(Server.class, result.getClass());
    }

    @Test
    public void testStart() throws Exception {
        when(config.getPort()).thenReturn(Integer.valueOf(0));
        when(config.getBasePath()).thenReturn("getBasePathResponse");
        when(config.getWelcome()).thenReturn("getWelcomeResponse");
        when(routesMap.getRoutes()).thenReturn(new DataMap<RouteConfig>(new DataMap<RouteConfig>() {{
            put("some", new RouteConfig());
        }}));

        Server result = server.start();
        Assert.assertEquals(Server.class, result.getClass());
    }

    @Test
    public void testStop() throws Exception {
        server.stop();
    }
}
