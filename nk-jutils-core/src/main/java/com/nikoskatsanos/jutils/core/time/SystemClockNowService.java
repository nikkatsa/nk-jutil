package com.nikoskatsanos.jutils.core.time;

/**
 * @author nikkatsa
 */
public class SystemClockNowService implements NowService {

    @Override
    public long nowMillis() {
        return System.currentTimeMillis();
    }
}
