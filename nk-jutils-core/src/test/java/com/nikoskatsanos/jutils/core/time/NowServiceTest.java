package com.nikoskatsanos.jutils.core.time;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class NowServiceTest {

    @Test
    public void testNowService_dummyImplementation() {
        final NowService dummyNowService = () -> 10;
        assertEquals(10, dummyNowService.nowMillis());
    }

    @Test
    public void testSystemClockNowService() {
        final long currentTime = System.currentTimeMillis();
        final NowService systemClockNowService = new SystemClockNowService();
        assertTrue(systemClockNowService.nowMillis() >= currentTime);
    }
}
