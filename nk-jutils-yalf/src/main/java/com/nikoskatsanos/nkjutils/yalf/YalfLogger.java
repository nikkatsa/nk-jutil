package com.nikoskatsanos.nkjutils.yalf;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <p>Simple logger wrapper. It provides an API for explicitly using the {@link String#format(String, Object...)}
 * function. The implementations can wrap well known logging frameworks and act as proxies</p>
 *
 * @author nikkatsa
 */
public interface YalfLogger {

    public static YalfLogger getLogger(final Class<?> clazz) {
        return YalfLogger.getLogger(clazz, defaultLoggerWrapper);
    }

    public static YalfLogger getLogger(final Class<?> clazz, final LoggerWrapper loggerWrapper) {
        return YalfLogger.getLogger(clazz.getName(), loggerWrapper);
    }

    public static YalfLogger getLogger(final String name) {
        return YalfLogger.getLogger(name, defaultLoggerWrapper);
    }

    public static YalfLogger getLogger(final String name, final LoggerWrapper loggerWrapper) {
        switch (loggerWrapper) {
            case LOG4J2:
                return new YalfLog4J2Impl(name);
            case LOG4J:
                return new YalfLoggerLog4jImpl(name);
            default:
                throw new IllegalArgumentException(String.format("Unhandled Yalf logger implementation %s",
                        loggerWrapper.toString()));
        }
    }

    public static enum LoggerWrapper {
        LOG4J2, LOG4J, UNKNOWN
    }

    public static LoggerWrapper defaultLoggerWrapper = LoggerWrapper.LOG4J2;

    public void trace(final String msg);

    public void trace(final Supplier<String> msgSupplier);

    public void trace(final String msg, final Object... objects);

    public void trace(final String msg, final Throwable throwable);

    public void debug(final String msg);

    public void debug(final Supplier<String> msgSupplier);

    public void debug(final String msg, final Object... objects);

    public void debug(final String msg, final Throwable throwable);

    public void info(final String msg);

    public void info(final Supplier<String> msgSupplier);

    public void info(final String msg, final Object... objects);

    public void info(final String msg, final Throwable throwable);

    public void warn(final String msg);

    public void warn(final Supplier<String> msgSupplier);

    public void warn(final String msg, final Object... objects);

    public void warn(final String msg, final Throwable throwable);

    public void error(final String msg);

    public void error(final Supplier<String> msgSupplier);

    public void error(final String msg, final Object... objects);

    public void error(final String msg, final Throwable throwable);

    public void fatal(final String msg);

    public void fatal(final Supplier<String> msgSupplier);

    public void fatal(final String msg, final Object... objects);

    public void fatal(final String msg, final Throwable throwable);


    /**
     * @param objects A varargs of objects
     * @return True if the last element of the varargs {@code objects} is a {@link java.lang.Throwable}
     */
    public default boolean hasThrowable(final Object... objects) {
        return Objects.nonNull(objects) && objects.length > 0 && objects[objects.length - 1] instanceof Throwable;
    }

    /**
     * @param msg     A string message pattern to be formatted
     * @param objects A varargs object which will be the input to {@code msg}
     * @return The formatted {@code msg} string, assuming the last element of {@code objects} is not used as an input to
     * the {@link java.lang.String#format(String, Object...)} method
     */
    public default String formatMsgWithThrowable(final String msg, final Object... objects) {
        return String.format(msg, Arrays.copyOfRange(objects, 0, objects.length - 1));
    }
}
