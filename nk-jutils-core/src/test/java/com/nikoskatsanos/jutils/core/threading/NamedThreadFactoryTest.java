package com.nikoskatsanos.jutils.core.threading;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class NamedThreadFactoryTest {

    private static final String THREAD_PREFIX = "SimpleThread";
    private ThreadFactory threadFactory;

    @Before
    public void setupNamedThreadFactoryTest() {
        this.threadFactory = new NamedThreadFactory(THREAD_PREFIX);
    }

    @Test
    public void testThreadCreation() {
        assertNotNull(this.threadFactory.newThread(() -> {
        }));
    }

    @Test
    public void testThreadCounters() {
        assertEquals(String.format("%s-%d", THREAD_PREFIX, 1), this.threadFactory.newThread(() -> {
        }).getName());
        assertEquals(String.format("%s-%d", THREAD_PREFIX, 2), this.threadFactory.newThread(() -> {
        }).getName());
    }

    @Test
    public void testDefaultThreadCreation() {
        final Thread defaultThread = new NamedThreadFactory().newThread(() -> {
        });
        assertNotNull(defaultThread);
        assertEquals("Thread-1", defaultThread.getName());
        assertTrue(defaultThread.isDaemon());
    }
}
