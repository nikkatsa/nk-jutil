package com.nikoskatsanos.nkjutils.yalf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * <p>Implementation of {@link YalfLogger} based on <b>Log4J2</b>. <b>Log4J2</b> framework provides by default a
 * mechanism for using the {@link String#format(String, Object...)} </p>
 *
 * @author nikkatsa
 * @see <a href="http://logging.apache.org/log4j/2.x/">Log4j2.x</a>
 */
public class YalfLog4J2Impl implements YalfLogger {

    private final Logger logger;

    public YalfLog4J2Impl(String name) {
        logger = LogManager.getFormatterLogger(name);
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
                logger.trace(this.formatMsgWithThrowable(msg, objects), objects[objects.length - 1]);
            } else {
                logger.trace(msg, objects);
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
                logger.debug(this.formatMsgWithThrowable(msg, objects), objects[objects.length - 1]);
            } else {
                logger.debug(msg, objects);
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
                logger.info(formatMsgWithThrowable(msg, objects), objects[objects.length - 1]);
            } else {
                logger.info(msg, objects);
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
        if (logger.isWarnEnabled()) {
            logger.warn(msg);
        }
    }

    @Override
    public void warn(final Supplier<String> msgSupplier) {
        if (logger.isWarnEnabled()) {
            logger.warn(msgSupplier.get());
        }
    }

    @Override
    public void warn(final String msg, final Object... objects) {
        if (logger.isWarnEnabled()) {
            if (hasThrowable(objects)) {
                logger.warn(formatMsgWithThrowable(msg, objects), objects[objects.length - 1]);
            } else {
                logger.warn(msg, objects);
            }
        }
    }

    @Override
    public void warn(final String msg, final Throwable throwable) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, throwable);
        }
    }

    @Override
    public void error(final String msg) {
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    @Override
    public void error(final Supplier<String> msgSupplier) {
        if (logger.isErrorEnabled()) {
            logger.error(msgSupplier.get());
        }
    }

    @Override
    public void error(final String msg, final Object... objects) {
        if (logger.isErrorEnabled()) {
            if (hasThrowable(objects)) {
                logger.error(formatMsgWithThrowable(msg, objects), objects[objects.length - 1]);
            } else {
                logger.error(msg, objects);
            }
        }
    }

    @Override
    public void error(final String msg, final Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, throwable);
        }
    }

    @Override
    public void fatal(final String msg) {
        if (logger.isFatalEnabled()) {
            logger.fatal(msg);
        }
    }

    @Override
    public void fatal(final Supplier<String> msgSupplier) {
        if (logger.isFatalEnabled()) {
            logger.fatal(msgSupplier.get());
        }
    }

    @Override
    public void fatal(final String msg, final Object... objects) {
        if (logger.isFatalEnabled()) {
            if (hasThrowable(objects)) {
                logger.fatal(formatMsgWithThrowable(msg, objects), objects[objects.length - 1]);
            } else {
                logger.fatal(msg, objects);
            }
        }
    }

    @Override
    public void fatal(final String msg, final Throwable throwable) {
        if (logger.isFatalEnabled()) {
            logger.fatal(msg, throwable);
        }
    }
}
