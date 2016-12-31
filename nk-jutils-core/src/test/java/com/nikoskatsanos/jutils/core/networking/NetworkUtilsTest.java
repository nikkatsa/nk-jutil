package com.nikoskatsanos.jutils.core.networking;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author nikkatsa
 */
public class NetworkUtilsTest {

    @Test
    public void testGetLocalAddress() {
        assertNotNull(NetworkUtils.getLocalAddress());
    }
}