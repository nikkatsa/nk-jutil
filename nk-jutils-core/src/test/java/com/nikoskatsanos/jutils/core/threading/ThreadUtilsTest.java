package com.nikoskatsanos.jutils.core.threading;

import com.nikoskatsanos.time.NanoClock;
import com.nikoskatsanos.time.WallNanoClock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class ThreadUtilsTest {

    @Test
    public void testSleepWithoutInterruption() {
        final NanoClock clock = new WallNanoClock();
        final long start = clock.currentTimeNanos();
        final long timeout = 500L;
        ThreadUtils.sleepWithoutInterruption(timeout, TimeUnit.MILLISECONDS);
        final long end = clock.currentTimeNanos();

        assertTrue(end > (start + timeout));
    }
}
