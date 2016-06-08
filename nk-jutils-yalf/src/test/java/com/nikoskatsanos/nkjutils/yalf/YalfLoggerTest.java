package com.nikoskatsanos.nkjutils.yalf;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class YalfLoggerTest {

    @Test
    public void testGetLogger() {
        assertTrue(YalfLogger.getLogger(YalfLoggerTest.class) instanceof YalfLog4J2Impl);
        assertTrue(YalfLogger.getLogger(YalfLoggerTest.class, YalfLogger.LoggerWrapper.LOG4J) instanceof
                YalfLoggerLog4jImpl);
        assertTrue(YalfLogger.getLogger("Logger2") instanceof YalfLog4J2Impl);
        assertTrue(YalfLogger.getLogger("Logger3", YalfLogger.LoggerWrapper.LOG4J) instanceof YalfLoggerLog4jImpl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLogger_unkownLoggerImplementation() {
        YalfLogger.getLogger("Unknown Logger", YalfLogger.LoggerWrapper.UNKNOWN);
    }
}