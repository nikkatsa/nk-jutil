package com.nikoskatsanos.jutils.core.threading;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Implementation of a {@link ThreadFactory}, which creates threads based on name provided by the user</p>
 *
 * @author nikkatsa
 */
public class NamedThreadFactory implements ThreadFactory {

    private final String name;
    private final boolean isDaemon;
    private final AtomicInteger threadCounter;

    public NamedThreadFactory() {
        this("Thread");
    }

    public NamedThreadFactory(String name) {
        this(name, true);
    }

    public NamedThreadFactory(String name, boolean isDaemon) {
        this.name = name;
        this.isDaemon = isDaemon;
        this.threadCounter = new AtomicInteger(1);
    }

    @Override
    public Thread newThread(final Runnable r) {
        final Thread t = new Thread();
        t.setName(String.format("%s-%d", this.name, threadCounter.getAndIncrement()));
        t.setDaemon(this.isDaemon);
        return t;
    }

}
