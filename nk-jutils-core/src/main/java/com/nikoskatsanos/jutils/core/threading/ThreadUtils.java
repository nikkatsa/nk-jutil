package com.nikoskatsanos.jutils.core.threading;

import java.util.concurrent.TimeUnit;

/**
 * <p>Utilities around threading</p>
 *
 * @author nikkatsa
 */
public class ThreadUtils {

    private ThreadUtils() {
    }

    public static void sleepWithoutInterruption(final long timeout, final TimeUnit tu) {
        try {
            tu.sleep(timeout);
        } catch (final InterruptedException e) {
            // swallow
        }
    }
}
