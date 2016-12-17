package com.nikoskatsanos.nkjutils.synthetic.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * <p>Metrics factory for creating singleton {@link com.codahale.metrics.MetricRegistry} and {@link
 * com.codahale.metrics.Metric}</p>
 *
 * @author nikkatsa
 */
public class MetricsFactory {

    private static AtomicBoolean HAS_INITIALIZED = new AtomicBoolean(false);

    /**
     * @return A singleton instance of a {@link MetricRegistry}. This instance of the {@link MetricRegistry} can be used
     * throughout an application in order to re-use the metrics created
     */
    public static final MetricRegistry getInstance() {
        final MetricRegistry metrics = MetricsRegistryHolder.METRICS;
        if (HAS_INITIALIZED.compareAndSet(false, true)) {
            JmxReporter.forRegistry(metrics).build().start();
        }
        return metrics;
    }

    /**
     * @return A brand new instance of a {@link MetricRegistry}, by also exposing it in JMX
     */
    public static final MetricRegistry getNewInstance() {
        return MetricsFactory.getNewInstance(true);
    }

    /**
     * @param exposeToJMX boolean indicating if the returned {@link MetricRegistry} should be exposed to JMX or not
     * @return A brand new instance of a {@link MetricRegistry}
     */
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

    public static final <T> Gauge<T> createGauge(final Class<?> clazz, final String metricName, final Supplier<T>
            gaugeFunc) {
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

    public static final Meter createMeter(final String metricName) {
        return MetricsFactory.getInstance().meter(metricName);
    }

    private static class MetricsRegistryHolder {
        public static final MetricRegistry METRICS = new MetricRegistry();
    }
}
