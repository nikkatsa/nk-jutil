package com.nikoskatsanos.nkjutils.yalf;

import org.apache.log4j.Logger;

import java.util.function.Supplier;

/**
 * <p>{@link YalfLogger} implementation based on <b>Log4j</b>. <b><u>NOTE</u></b> that <b>Log4j</b> has become end of life as of August 2015 and <b>Log4j2.x</b> has replaced it</p>
 *
 * @author nikkatsa
 * @see <a href="https://logging.apache.org/log4j/1.2/">Log4j</a>
 */
public class YalfLoggerLog4jImpl implements YalfLogger {

    private final Logger logger;

    public YalfLoggerLog4jImpl(String name) {
        logger = Logger.getLogger(name);
    }

    @Override
    public void trace(final String msg) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg);
        }
    }

    @Override
    public void trace(final Supplier<String> msgSupplier) {
        if (logger.isTraceEnabled()) {
            this.trace(msgSupplier.get());
        }
    }

    @Override
    public void trace(final String msg, final Object... objects) {
        if (logger.isTraceEnabled()) {
            if (hasThrowable(objects)) {
                logger.trace(this.formatMsgWithThrowable(msg, objects), (Throwable) objects[objects.length - 1]);
            } else {
                logger.trace(String.format(msg, objects));
            }
        }
    }

    @Override
    public void trace(final String msg, final Throwable throwable) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, throwable);
        }
    }

    @Override
    public void debug(final String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    @Override
    public void debug(final Supplier<String> msgSupplier) {
        if (logger.isDebugEnabled()) {
            logger.debug(msgSupplier.get());
        }
    }

    @Override
    public void debug(final String msg, final Object... objects) {
        if (logger.isDebugEnabled()) {
            if (hasThrowable(objects)) {
                logger.debug(this.formatMsgWithThrowable(msg, objects), (Throwable) objects[objects.length - 1]);
            } else {
                logger.debug(String.format(msg, objects));
            }
        }
    }

    @Override
    public void debug(final String msg, final Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, throwable);
        }
    }

    @Override
    public void info(final String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    @Override
    public void info(final Supplier<String> msgSupplier) {
        if (logger.isInfoEnabled()) {
            logger.info(msgSupplier.get());
        }
    }

    @Override
    public void info(final String msg, final Object... objects) {
        if (logger.isInfoEnabled()) {
            if (hasThrowable(objects)) {
                logger.info(formatMsgWithThrowable(msg, objects), (Throwable) objects[objects.length - 1]);
            } else {
                logger.info(String.format(msg, objects));
            }
        }
    }

    @Override
    public void info(final String msg, final Throwable throwable) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, throwable);
        }
    }

    @Override
    public void warn(final String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(final Supplier<String> msgSupplier) {
        logger.warn(msgSupplier.get());
    }

    @Override
    public void warn(final String msg, final Object... objects) {
        if (hasThrowable(objects)) {
            logger.warn(formatMsgWithThrowable(msg, objects), (Throwable) objects[objects.length - 1]);
        } else {
            logger.warn(String.format(msg, objects));
        }
    }

    @Override
    public void warn(final String msg, final Throwable throwable) {
        logger.warn(msg, throwable);
    }

    @Override
    public void error(final String msg) {
        logger.error(msg);
    }

    @Override
    public void error(final Supplier<String> msgSupplier) {
        logger.error(msgSupplier.get());
    }

    @Override
    public void error(final String msg, final Object... objects) {
        if (hasThrowable(objects)) {
            logger.error(formatMsgWithThrowable(msg, objects), (Throwable) objects[objects.length - 1]);
        } else {
            logger.error(String.format(msg, objects));
        }
    }

    @Override
    public void error(final String msg, final Throwable throwable) {
        logger.error(msg, throwable);
    }

    @Override
    public void fatal(final String msg) {
        logger.fatal(msg);
    }

    @Override
    public void fatal(final Supplier<String> msgSupplier) {
        logger.fatal(msgSupplier.get());
    }

    @Override
    public void fatal(final String msg, final Object... objects) {
        if (hasThrowable(objects)) {
            logger.fatal(formatMsgWithThrowable(msg, objects), (Throwable) objects[objects.length - 1]);
        } else {
            logger.fatal(String.format(msg, objects));
        }
    }

    @Override
    public void fatal(final String msg, final Throwable throwable) {
        logger.fatal(msg, throwable);
    }
}
