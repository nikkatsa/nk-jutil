package com.nikoskatsanos.nkjutils.synthetic.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.nikoskatsanos.jutils.core.VarHolder;
import com.nikoskatsanos.jutils.core.threading.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * @author nikkatsa
 */
public class MetricsFactory {

    private static AtomicBoolean HAS_INITIALIZED = new AtomicBoolean(false);

    public static final MetricRegistry getInstance() {
        final MetricRegistry metrics = MetricsRegistryHolder.METRICS;
        if (HAS_INITIALIZED.compareAndSet(false, true)) {
            JmxReporter.forRegistry(metrics).build().start();
        }
        return metrics;
    }

    public static final MetricRegistry getNewInstance() {
        return MetricsFactory.getNewInstance(true);
    }

    public static final MetricRegistry getNewInstance(final boolean exposeToJMX) {
        final MetricRegistry metrics = new MetricRegistry();
        if (exposeToJMX) {
            JmxReporter.forRegistry(metrics).build().start();
        }
        return metrics;
    }

    public static final <T> Gauge<T> createGauge(final String metricName, final Supplier<T> gaugeFunc) {
        return MetricsFactory.<T>createGauge(MetricsFactory.class, metricName, gaugeFunc);
    }

    public static final <T> Gauge<T> createGauge(final Class<?> clazz, final String metricName, final Supplier<T> gaugeFunc) {
        return MetricsFactory.getInstance().register(MetricRegistry.name(clazz, metricName), new Gauge<T>() {
            @Override
            public T getValue() {
                return gaugeFunc.get();
            }
        });
    }

    public static final Counter createCounter(final String metricName) {
        return MetricsFactory.getInstance().counter(metricName);
    }

    public static final Timer createTimer(final String metricName) {
        return MetricsFactory.getInstance().timer(metricName);
    }

    public static final Histogram createHistogram(final String metricName) {
        return MetricsFactory.getInstance().histogram(metricName);
    }

    private static class MetricsRegistryHolder {
        public static final MetricRegistry METRICS = new MetricRegistry();
    }

    public static void main(final String... args) {
        final VarHolder<String> myString = new VarHolder<String>("");
        MetricsFactory.<String>createGauge(MetricsFactory.class, "nikosG", () -> myString.getValue());

        while (true) {
            if (myString.getValue().length() > 2000) {
                myString.setValue("");
            }
            for (char c = 'a'; c < 'z'; c++) {
                myString.setValue(myString.getValue() + c);
            }

            ThreadUtils.sleepWithoutInterruption(3000L, TimeUnit.MILLISECONDS);
        }
    }
}
