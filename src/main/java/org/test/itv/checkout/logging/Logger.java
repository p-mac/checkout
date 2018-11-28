package org.test.itv.checkout.logging;

import org.test.itv.checkout.util.KEYS;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created on 27/11/18.
 */
public class Logger<C> {

    protected Class<C> objectClass;
    private PrintStream output;
    private static final String pattern = "{0} - {1} - {2,date,dd/MM/yyyy_HH:mm:ss:SSS} - {3} - {4}";
    private static String ERROR = "ERROR";
    private static String DEBUG = "DEBUG";
    private static String TRACE = "TRACE";
    private static String INFO = "INFO";

    public Logger(Class<C> objectClass, PrintStream output) {
        this.objectClass = objectClass;
        this.output = output;
    }

    public Class<C> getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Class<C> objectClass) {
        this.objectClass = objectClass;
    }

    public PrintStream getOutput() {
        return output;
    }

    public void setOutput(PrintStream output) {
        this.output = output;
    }

    public <T extends Throwable> void error(String message, T throwable) {
        output.println(MessageFormat.format(pattern, ERROR, objectClass.getCanonicalName(), new Date(), message, throwable));
        throwable.printStackTrace(output);
    }

    public void debug(String message, Object... objects) {
        Object object = (objects != null && objects.length > 0) ? objects[0] : KEYS.BLANK;
        output.println(MessageFormat.format(pattern, DEBUG, objectClass.getCanonicalName(), new Date(), message, object));
    }

    public void trace(String message, Object object) {
        output.println(MessageFormat.format(pattern, TRACE, objectClass.getCanonicalName(), new Date(), message, object));
    }

    public void info(String message) {
        output.println(MessageFormat.format(pattern, INFO, objectClass.getCanonicalName(), new Date(), message, KEYS.BLANK));
    }

}
