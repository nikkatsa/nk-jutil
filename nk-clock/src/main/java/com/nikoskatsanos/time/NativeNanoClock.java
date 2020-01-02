package com.nikoskatsanos.time;

import com.nikoskatsanos.jutils.core.nativelib.NativeLibLoader;
import com.nikoskatsanos.jutils.core.os.OS;
import com.nikoskatsanos.jutils.core.os.OS.OS_ARCH;

/**
 * A {@link NanoClock} that uses a native method to retrieve system's time in nanoseconds since epoch
 */
public class NativeNanoClock implements NanoClock {

    static {
        loadNativeLib();
    }

    @Override
    public long currentTimeNanos() {
        return this.currentNanos();
    }

    private native long currentNanos();

    private static void loadNativeLib() {
        final OS os = OS.os();
        if (OS_ARCH._64 != os.getOsArch()) {
            throw new UnsupportedOperationException(String.format("OS_ARCH=%s is not supported. Supported architectures are [%s]", os.getOsArch(), OS_ARCH._64));
        }

        final String nativeLibPath;
        switch (os.getOsType()) {
            case LINUX:
                nativeLibPath = String.format("/native-libs/linux/nk_clock.so");
                break;
            case MACOS:
                nativeLibPath = String.format("/native-libs/macos/nk_clock.dylib");
                break;
            case WINDOWS:
                nativeLibPath = String.format("/native-libs/win/nk_clock.dll");
                break;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported OS. OS_TYPE=%s", os.getOsType()));
        }

        NativeLibLoader.loadFromJar(nativeLibPath);
    }
}
