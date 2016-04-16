package com.nikoskatsanos.jutils.core.process;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class ProcessUtilsTest {

    @Test
    public void testGetProcessId() {
        assertTrue(ProcessUtils.getPid() > 0);
    }
}
