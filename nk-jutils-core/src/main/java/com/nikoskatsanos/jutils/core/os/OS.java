package com.nikoskatsanos.jutils.core.os;

import java.util.Objects;

public enum OS {

    LINUX_32(OS_TYPE.LINUX, OS_ARCH._32),
    LINUX_64(OS_TYPE.LINUX, OS_ARCH._64),
    MACOS_64(OS_TYPE.MACOS, OS_ARCH._64),
    WINDOWS_32(OS_TYPE.WINDOWS, OS_ARCH._32),
    WINDOWS_64(OS_TYPE.WINDOWS, OS_ARCH._64),
    UNKNOWN_32(OS_TYPE.UNKNOWN, OS_ARCH._32),
    UNKNOWN_64(OS_TYPE.UNKNOWN, OS_ARCH._64);

    private final OS_TYPE osType;
    private final OS_ARCH osArch;

    OS(OS_TYPE osType, OS_ARCH osArch) {
        this.osType = osType;
        this.osArch = osArch;
    }

    public OS_TYPE getOsType() {
        return this.osType;
    }

    public OS_ARCH getOsArch() {
        return this.osArch;
    }

    private static final String OS_STR = System.getProperty("os.name", "UNKNOWN").toLowerCase();
    private static final String OS_ARCH_STR = System.getProperty("os.arch", "UNKNOWN").toLowerCase();


    public static OS os() {
        return parseFrom(OS_STR, OS_ARCH_STR);
    }

    public static OS parseFrom(final String osType, final String osArch) {
        Objects.requireNonNull(osType, "osType must be set");
        Objects.requireNonNull(osArch, "osArch must be set");

        final OS_TYPE type = parseOSType(osType);
        final OS_ARCH arch = parseOSArch(osArch);
        switch (type) {
            case LINUX:
                return arch == OS_ARCH._64 ? LINUX_64 : LINUX_32;
            case MACOS:
                return MACOS_64;
            case WINDOWS:
                return arch == OS_ARCH._64 ? WINDOWS_64 : WINDOWS_32;
            case UNKNOWN:
                return arch == OS_ARCH._64 ? UNKNOWN_64 : UNKNOWN_32;
            default:
                throw new RuntimeException(String.format("Failed to determine OS for OSType=%s, OSArch=%s", osType, osArch));
        }
    }

    private static OS_ARCH parseOSArch(final String osArch) {
        if (osArch.contains("64")) {
            return OS_ARCH._64;
        } else if (osArch.contains("32")) {
            return OS_ARCH._32;
        } else {
            return OS_ARCH._UNKNOWN;
        }
    }

    private static OS_TYPE parseOSType(final String osType) {
        if (osType.startsWith("linux")) {
            return OS_TYPE.LINUX;
        } else if (osType.startsWith("mac") || osType.startsWith("darwin")) {
            return OS_TYPE.MACOS;
        } else if (osType.startsWith("win")) {
            return OS_TYPE.WINDOWS;
        } else {
            return OS_TYPE.UNKNOWN;
        }
    }

    public enum OS_TYPE {
        LINUX, MACOS, WINDOWS, UNKNOWN;
    }

    public enum OS_ARCH {
        _64, _32, _UNKNOWN;
    }
}
