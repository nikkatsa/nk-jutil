package com.nikoskatsanos.nkjutils.yalf;

import com.nikoskatsanos.jutils.core.StringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>This class uses the JMH tool (<a href="http://openjdk.java.net/projects/code-tools/jmh/">http://openjdk.java
 * .net/projects/code-tools/jmh/</a>) in order to produce a benchmark between Log4j and Log4j2. The benchmarking modes
 * used are <b><i>Throughput</i> and <i>Average time</i></b>. The {@code counter} field serves as state and reports how
 * many messages were logged during the benchmarking operations. The only parameter that needs to be tweaked in order to
 * choose between Log4j and Log4j2 is the the {@link com.nikoskatsanos.nkjutils.yalf.YalfLogger.LoggerWrapper} while
 * instantiating the {@link com.nikoskatsanos.nkjutils.yalf.YalfLogger}</p>
 *
 * @author nikkatsa
 */
@State(Scope.Benchmark)
public class Log4JBenchmarking {

    private static final YalfLogger log = YalfLogger.getLogger(Log4JBenchmarking.class, YalfLogger.LoggerWrapper.LOG4J2);

    private final AtomicLong counter = new AtomicLong(0L);

    static {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    public Log4JBenchmarking() {
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        log.warn(StringUtils.centre(" ", 100, '*'));
        log.warn("Messages: %d", counter.get());
        log.warn(StringUtils.centre(" ", 100, '*'));
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void logMessage() {
        log.info("Message %f", Math.random());
        counter.incrementAndGet();
    }

    public static void main(final String... args) throws RunnerException {

        final Options opt = new OptionsBuilder().include(Log4JBenchmarking.class.getSimpleName()).forks(1).build();

        new Runner(opt).run();
    }
}
