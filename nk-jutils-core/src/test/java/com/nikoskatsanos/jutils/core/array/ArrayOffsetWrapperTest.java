package com.nikoskatsanos.jutils.core.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author nikkatsa
 */
public class ArrayOffsetWrapperTest {

    private ArrayOffsetWrapper<String> stringWrapper;
    private final String[] strArray = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten"};

    @Before
    public void setupArrayOffsetWrapperTest() {
        stringWrapper = new ArrayOffsetWrapper<String>(strArray, (strArray.length - 1) / 2);
    }

    @Test
    public void testLength() {
        assertEquals(6, stringWrapper.length());
    }

    @Test
    public void testGet() {
        assertEquals("five", stringWrapper.get(0));
        assertEquals("ten", stringWrapper.get(stringWrapper.length() - 1));
    }

    @Test
    public void testSet() {
        stringWrapper.set(0, "FIVE");
        assertEquals("FIVE", stringWrapper.get(0));
        assertEquals("FIVE", strArray[4]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGet_expectingArrayIndexOutOfBoundsI() {
        stringWrapper.get(8);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGet_expectingArrayIndexOutOfBoundsII() {
        final ArrayOffsetWrapper<String> wrapper = new ArrayOffsetWrapper<String>(this.strArray, 0, 3);
        wrapper.get(3);
    }

    @Test
    public void testIterator() {
        int idx = 4;
        for (String s : this.stringWrapper) {
            assertEquals(strArray[idx++], s);
        }
    }
}