package com.nikoskatsanos.time;


public interface NanoClock {

    /**
     * @return current time in nanoseconds. Based on the implementation this might have different meaning. For example it might represent nanoseconds since epoch, or it might
     * represent nanoseconds since an arbitrary point in time
     */
    long currentTimeNanos();
}
