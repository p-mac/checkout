package org.test.itv.checkout;

import org.test.itv.checkout.server.Server;

/**
 * Main entrypoint for the application bootstrap
 */
public class Main {

    public static void main(String... args) {

        Server.getInstance().init().start();

    }
}
