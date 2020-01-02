package com.nikoskatsanos.time;

public class RelativeNanoClock implements NanoClock {

    private final long start = System.nanoTime();

    @Override
    public long currentTimeNanos() {
        return System.nanoTime() - start;
    }
}
