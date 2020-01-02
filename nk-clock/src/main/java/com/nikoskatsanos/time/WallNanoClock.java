package com.nikoskatsanos.time;

/**
 * A {@link NanoClock} based on system's wall time. The granularity of this clock is milliseconds, as it uses {@link System#currentTimeMillis()}
 */
public class WallNanoClock implements NanoClock {

    private static final int MILLION = 1_000_000;

    @Override
    public long currentTimeNanos() {
        return System.currentTimeMillis() * MILLION;
    }
}
