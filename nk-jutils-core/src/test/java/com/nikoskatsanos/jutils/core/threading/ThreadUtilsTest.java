package com.nikoskatsanos.jutils.core.threading;

import com.nikoskatsanos.jutils.core.time.NowService;
import com.nikoskatsanos.jutils.core.time.SystemClockNowService;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class ThreadUtilsTest {

    @Test
    public void testSleepWithoutInterruption() {
        final NowService nowService = new SystemClockNowService();
        final long start = nowService.nowMillis();
        final long timeout = 500L;
        ThreadUtils.sleepWithoutInterruption(timeout, TimeUnit.MILLISECONDS);
        final long end = nowService.nowMillis();

        assertTrue(end > (start + timeout));
    }
}
