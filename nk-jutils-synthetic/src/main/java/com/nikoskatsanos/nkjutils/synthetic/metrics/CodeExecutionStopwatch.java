package com.nikoskatsanos.nkjutils.synthetic.metrics;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>A simple code execution stopwatch, which encapsulates a {@link com.google.common.base.Stopwatch} and can
 * conveniently be used in micro benchmarking. It implements {@link java.io.Closeable} so that it can be used with
 * try-with-resources.</p> <p>
 * <pre>
 *         {@code
 *
 * try (CodeExecutionStopwatch stopwatch = new CodeExecutionStopwatch("MY_TASK", TimeUnit.NANOSECONDS, true)) {
 *  // do stuff ...
 * } finally {
 * }
 *
 * }
 * </pre>
 * </p>
 *
 * @author nikkatsa
 */
public class CodeExecutionStopwatch implements Closeable, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeExecutionStopwatch.class);

    private final Stopwatch stopwatch;

    private final String executionTaskName;
    private final TimeUnit timeUnit;
    private final boolean startUponConstruction;
    private long finalExecutionTime;

    public CodeExecutionStopwatch(String executionTaskName) {
        this(executionTaskName, TimeUnit.NANOSECONDS);
    }

    public CodeExecutionStopwatch(String executionTaskName, TimeUnit timeUnit) {
        this(executionTaskName, timeUnit, false);
    }

    public CodeExecutionStopwatch(String executionTaskName, TimeUnit timeUnit, boolean startUponConstruction) {
        this.executionTaskName = executionTaskName;
        this.timeUnit = timeUnit;
        this.startUponConstruction = startUponConstruction;

        stopwatch = this.startUponConstruction ? Stopwatch.createStarted() : Stopwatch.createUnstarted();
    }


    public void start() {
        if (this.stopwatch.isRunning()) {
            throw new IllegalStateException("Stopwatch is already running");
        }
        this.stopwatch.start();
    }

    public long pause() {
        if (!this.stopwatch.isRunning()) {
            throw new IllegalStateException("Stopwatch is not running");
        }
        this.stopwatch.stop();
        return this.stopwatch.elapsed(this.timeUnit);
    }

    public void stop() {
        if (!this.stopwatch.isRunning()) {
            throw new IllegalStateException("Stopwatch is not running");
        }
        this.stopwatch.stop();
    }

    public String getExecutionTaskName() {
        return executionTaskName;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public boolean isStartUponConstruction() {
        return startUponConstruction;
    }

    public long getFinalExecutionTime() {
        return finalExecutionTime;
    }

    public void setFinalExecutionTime(long finalExecutionTime) {
        this.finalExecutionTime = finalExecutionTime;
    }

    @Override
    public void close() throws IOException {
        if (this.stopwatch.isRunning()) {
            stopwatch.stop();
        }
        this.finalExecutionTime = stopwatch.elapsed(this.timeUnit);
        LOGGER.info("TASK [{}] ELAPSED_TIME [{} {}]", this.executionTaskName, this.finalExecutionTime, this.timeUnit
                .toString());
    }
}
