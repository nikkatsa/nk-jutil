package com.nikoskatsanos.jutils.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * <p>Static methods for calling {@link Closeable#close()} and {@link AutoCloseable#close()}</p>
 *
 * @author nikkatsa
 */
public class CloseableUtils {

    public static final void close(final Closeable... closeables) {
        for (final Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (final IOException e) {
            }
        }
    }

    public static final void close(final AutoCloseable... autoCloseables) {
        for (final AutoCloseable autoCloseable : autoCloseables) {
            try {
                autoCloseable.close();
            } catch (final Exception e) {
            }
        }
    }
}
