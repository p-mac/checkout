package org.test.itv.checkout.logging;

import java.io.PrintStream;

/**
 * Created on 27/11/18.
 */
public class LogManager {

    private LogManager() { }


    protected static PrintStream getOutput() {
        return System.out;
    }

    public static <C> Logger<C> getLogger(Class<C> objectClass) {
        return new Logger<C>(objectClass, getOutput());
    }

}
