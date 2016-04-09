package com.nikoskatsanos.jutils.core;

import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author nikkatsa
 */
public class CloseableUtilsTest {

    @Test
    public void testClose_withCloseables() {
        final int[] arrayI = new int[1];
        final int[] arrayII = new int[1];

        final Closeable closeableI = () -> arrayI[0] = 1;
        final Closeable closeableII = () -> arrayII[0] = 1;

        CloseableUtils.close(closeableI, closeableII);

        assertEquals(1, arrayI[0]);
        assertEquals(1, arrayII[0]);
    }

    @Test
    public void testClose_withCloseable_throwingException() {
        final ThrowingCloseable throwingCloseable = new ThrowingCloseable();

        CloseableUtils.close(throwingCloseable);
        return;
    }

    @Test
    public void testClose_withAutoCloseable_throwingException() {
        final ThrowingAutoCloseable throwingAutoCloseable = new ThrowingAutoCloseable();

        CloseableUtils.close(throwingAutoCloseable);
        return;
    }

    private final class ThrowingCloseable implements Closeable {
        @Override
        public void close() throws IOException {
            throw new IOException("Error");
        }
    }

    private final class ThrowingAutoCloseable implements AutoCloseable {

        @Override
        public void close() throws Exception {
            throw new RuntimeException("Error");
        }
    }
}
