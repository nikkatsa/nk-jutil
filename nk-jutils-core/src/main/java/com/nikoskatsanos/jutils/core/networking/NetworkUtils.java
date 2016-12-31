package com.nikoskatsanos.jutils.core.networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Static utilities for networking</p>
 *
 * @author nikkatsa
 */
public class NetworkUtils {

    private NetworkUtils() {
    }

    /**
     * @return The local address or the loopback interface if it the local address cannot be resolv†ed
     */
    public static InetAddress getLocalAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            return InetAddress.getLoopbackAddress();
        }
    }
}
