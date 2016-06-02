package com.nikoskatsanos.jutils.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author nikkatsa
 */
public class StringUtilsTest {
    @Test
    public void testCentre() throws Exception {
        assertEquals("foo", StringUtils.centre("foo", 3, '*'));
        assertEquals("fo", StringUtils.centre("foo", 2, '*'));

        assertEquals("**foo**", StringUtils.centre("foo", 7, '*'));
        assertEquals("**foo***", StringUtils.centre("foo", 8, '*'));
        assertEquals("foo*", StringUtils.centre("foo", 4, '*'));
    }
}