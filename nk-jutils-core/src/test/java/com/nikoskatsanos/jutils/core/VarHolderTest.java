package com.nikoskatsanos.jutils.core;

import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

/**
 * @author nikkatsa
 */
public class VarHolderTest {

    @Test
    public void testVarHolder() {
        final VarHolder<String> stringVarHolder = new VarHolder<>("Hello World");
        assertEquals("Hello World", stringVarHolder.getValue());
        stringVarHolder.setValue("Hello New World");
        assertEquals("Hello New World", stringVarHolder.getValue());
    }

    @Test
    public void testVarHolderWithLamda() {
        final VarHolder<Integer> intVarHolder = new VarHolder<>(1);
        final Supplier<Integer> lamda = () -> intVarHolder.getValue();
        assertEquals(1, (int) lamda.get());
    }
}
