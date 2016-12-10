package com.nikoskatsanos.jutils.core.chars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author nikkatsa
 */
public class CharUtilsTest {

    @Test
    public void testCharsets() {
        assertEquals("UTF-8", CharUtils.UTF_8.displayName());
        assertEquals("UTF-16", CharUtils.UTF_16.displayName());
        assertFalse(CharUtils.UTF_8.equals(CharUtils.UTF_16));
    }
}