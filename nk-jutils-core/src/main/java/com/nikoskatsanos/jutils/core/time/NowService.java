package com.nikoskatsanos.jutils.core.time;

/**
 * <p>Functional interface returning the current time milliseconds sine 1 January 1970. This interface can be used as an
 * alternative in order to make classes more testable, by injecting a mock of a service which returns the
 * milliseconds</p>
 *
 * @author nikkatsa
 */
@FunctionalInterface
public interface NowService {

    /**
     * @return the number of milliseconds since 1st January 1970 UTC
     */
    long nowMillis();

}
