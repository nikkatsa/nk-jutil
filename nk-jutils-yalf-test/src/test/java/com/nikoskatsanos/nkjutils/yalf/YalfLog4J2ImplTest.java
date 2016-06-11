package com.nikoskatsanos.nkjutils.yalf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

/**
 * @author nikkatsa
 */
public class YalfLog4J2ImplTest {

    private YalfLogger log;
    private Exception ex;

    @Before
    public void setupYalfLog4J2ImplTest() {
        log = Mockito.spy(YalfLogger.getLogger(YalfLog4J2ImplTest.class));
        ex = new Exception("Exception");
    }

    @Test
    public void testSimpleLogInvocation() {
        log.trace("foo");
        Mockito.verify(log, Mockito.times(1)).trace("foo");
        log.debug("foo");
        Mockito.verify(log, Mockito.times(1)).debug("foo");
        log.info("foo");
        Mockito.verify(log, Mockito.times(1)).info("foo");
        log.warn("foo");
        Mockito.verify(log, Mockito.times(1)).warn("foo");
        log.error("foo");
        Mockito.verify(log, Mockito.times(1)).error("foo");
        log.fatal("foo");
        Mockito.verify(log, Mockito.times(1)).fatal("foo");
    }

    @Test
    public void testLogInvocationWithSupplier() {
        final Supplier<String> getMsgSupplier = YalfLog4J2ImplTest::get;

        log.trace(getMsgSupplier);
        Mockito.verify(log).trace(getMsgSupplier);
        log.debug(getMsgSupplier);
        Mockito.verify(log).debug(getMsgSupplier);
        log.info(getMsgSupplier);
        Mockito.verify(log).info(getMsgSupplier);
        log.warn(getMsgSupplier);
        Mockito.verify(log).warn(getMsgSupplier);
        log.error(getMsgSupplier);
        Mockito.verify(log).error(getMsgSupplier);
        log.fatal(getMsgSupplier);
        Mockito.verify(log).fatal(getMsgSupplier);
    }

    @Test
    public void testFormattedMsg() {
        log.trace("%s-%d-%b", "foo", 1, true);
        Mockito.verify(log).trace("%s-%d-%b", "foo", 1, true);
        log.debug("%s-%d-%b", "foo", 1, true);
        Mockito.verify(log).debug("%s-%d-%b", "foo", 1, true);
        log.info("%s-%d-%b", "foo", 1, true);
        Mockito.verify(log).info("%s-%d-%b", "foo", 1, true);
        log.warn("%s-%d-%b", "foo", 1, true);
        Mockito.verify(log).warn("%s-%d-%b", "foo", 1, true);
        log.error("%s-%d-%b", "foo", 1, true);
        Mockito.verify(log).error("%s-%d-%b", "foo", 1, true);
        log.fatal("%s-%d-%b", "foo", 1, true);
        Mockito.verify(log).fatal("%s-%d-%b", "foo", 1, true);
    }

    @Test
    public void testLogFormattedMsgWithThrowables() {
        log.trace("%s-%d", "foo", 1, ex);
        Mockito.verify(log).trace("%s-%d", "foo", 1, ex);
        log.debug("%s-%d", "foo", 1, ex);
        Mockito.verify(log).debug("%s-%d", "foo", 1, ex);
        log.info("%s-%d", "foo", 1, ex);
        Mockito.verify(log).info("%s-%d", "foo", 1, ex);
        log.warn("%s-%d", "foo", 1, ex);
        Mockito.verify(log).warn("%s-%d", "foo", 1, ex);
        log.error("%s-%d", "foo", 1, ex);
        Mockito.verify(log).error("%s-%d", "foo", 1, ex);
        log.fatal("%s-%d", "foo", 1, ex);
        Mockito.verify(log).fatal("%s-%d", "foo", 1, ex);
    }

    @Test
    public void testLogMsgAndThrowable() {
        log.trace("foo", ex);
        Mockito.verify(log).trace("foo", ex);
        log.debug("foo", ex);
        Mockito.verify(log).debug("foo", ex);
        log.info("foo", ex);
        Mockito.verify(log).info("foo", ex);
        log.warn("foo", ex);
        Mockito.verify(log).warn("foo", ex);
        log.error("foo", ex);
        Mockito.verify(log).error("foo", ex);
        log.fatal("foo", ex);
        Mockito.verify(log).fatal("foo", ex);
    }

    public static String get() {
        return "foo";
    }
}
