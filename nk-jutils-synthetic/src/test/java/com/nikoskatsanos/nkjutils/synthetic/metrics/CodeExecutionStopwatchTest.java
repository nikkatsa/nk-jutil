package com.nikoskatsanos.nkjutils.synthetic.metrics;

import com.nikoskatsanos.jutils.core.CloseableUtils;
import com.nikoskatsanos.jutils.core.time.NowService;
import com.nikoskatsanos.jutils.core.time.SystemClockNowService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class CodeExecutionStopwatchTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeExecutionStopwatchTest.class);

    @Test
    public void testStopwatchNormalExecution() throws InterruptedException {
        CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK", TimeUnit.NANOSECONDS);
        final int iterations = 10;
        final long sleepTime = 100L;

        stopwatch.start();
        for (int i = 0; i < iterations; i++) {
            TimeUnit.NANOSECONDS.sleep(sleepTime);
        }

        CloseableUtils.close(stopwatch);

        assertTrue(stopwatch.getFinalExecutionTime() >= sleepTime * iterations);
    }

    @Test
    public void testStopwatchStop() throws InterruptedException {
        CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK", TimeUnit.NANOSECONDS);
        stopwatch.start();

        TimeUnit.NANOSECONDS.sleep(1000L);

        stopwatch.stop();

        assertTrue(stopwatch.getFinalExecutionTime() == 0L);
    }

    @Test(expected = IllegalStateException.class)
    public void testStopwatchStartTwice() {
        CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK", TimeUnit.MILLISECONDS, true);
        stopwatch.start();
    }

    @Test(expected = IllegalStateException.class)
    public void testStopwatchStopWithoutStart() {
        CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK");
        stopwatch.stop();
    }

    @Test
    public void testStopwatchTryWithResources() {
        long elapsedTime = 0L;
        try (CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK", TimeUnit.NANOSECONDS, true)) {

            TimeUnit.NANOSECONDS.sleep(1000L);

        } catch (IOException e) {
            LOGGER.error("", e);
        } catch (InterruptedException e) {
            LOGGER.error("", e);
        } finally {
            assertTrue(true);
        }
    }

    @Test
    public void testStopwatchPause() {
        final CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK");
        stopwatch.start();
        final long millis = stopwatch.pause();
        assertTrue(millis > 0L);
    }

    @Test(expected = IllegalStateException.class)
    public void testStopwatchPause_withoutStart() {
        final CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK");
        stopwatch.pause();
    }

    @Test
    public void testStopwatchProperties() {
        final CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK", TimeUnit.NANOSECONDS, false);
        assertEquals("MY_TASK", stopwatch.getExecutionTaskName());
        assertEquals(TimeUnit.NANOSECONDS, stopwatch.getTimeUnit());
        assertEquals(false, stopwatch.isStartUponConstruction());
    }
}
