package com.nikoskatsanos.jutils.core;

import java.util.Arrays;

/**
 * <p>String utility methods</p>
 *
 * @author nikkatsa
 */
public class StringUtils {

    public static final String centre(final String txt, final int length, final char pad) {
        if (txt.length() >= length) {
            return txt.substring(0, length);
        }

        final int extra = length - txt.length();
        final int padLeft = extra / 2;
        final char[] centred = new char[length];
        Arrays.fill(centred, 0, padLeft, pad);
        System.arraycopy(txt.toCharArray(), 0, centred, padLeft, txt.length());
        Arrays.fill(centred, padLeft + txt.length(), centred.length, pad);
        return new String(centred);
    }
}
