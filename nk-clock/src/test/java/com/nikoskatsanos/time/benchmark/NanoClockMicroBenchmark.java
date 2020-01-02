package com.nikoskatsanos.time.benchmark;

import com.nikoskatsanos.time.NanoClock;
import com.nikoskatsanos.time.NativeNanoClock;
import com.nikoskatsanos.time.RelativeNanoClock;
import com.nikoskatsanos.time.WallNanoClock;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.CommandLineOptionException;
import org.openjdk.jmh.runner.options.CommandLineOptions;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class NanoClockMicroBenchmark {

    private final NanoClock nativeNanoClock = new NativeNanoClock();
    private final NanoClock wallNanoClock = new WallNanoClock();
    private final NanoClock relativeNanoClock = new RelativeNanoClock();

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void nativeNanoClock(final Blackhole bh) {
        bh.consume(this.nativeNanoClock.currentTimeNanos());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void wallNanoClock(final Blackhole bh) {
        bh.consume(this.wallNanoClock.currentTimeNanos());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void relativeNanoClock(final Blackhole bh) {
        bh.consume(this.relativeNanoClock.currentTimeNanos());
    }

    public static void main(final String... args) throws RunnerException, CommandLineOptionException {
        final Options options = new OptionsBuilder()
            .parent(new CommandLineOptions(args))
            .include(NanoClockMicroBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(5)
            .measurementIterations(10)
            .jvmArgsAppend(
                "-Xbatch",
                "-XX:-TieredCompilation",
                "-XX:+UnlockDiagnosticVMOptions"
            )
            .build();

        new Runner(options).run();
    }
}
