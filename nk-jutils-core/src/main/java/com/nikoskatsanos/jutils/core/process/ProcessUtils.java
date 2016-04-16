package com.nikoskatsanos.jutils.core.process;

import java.lang.management.ManagementFactory;

/**
 * <p>Various Java process utilities</p>
 *
 * @author nikkatsa
 */
public class ProcessUtils {

    /**
     * @return The Java process' id
     */
    public static int getPid() {
        return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
    }
}
