package com.nikoskatsanos.nkjutils.synthetic.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author nikkatsa
 */
public class MetricsFactoryTest {

    private MetricRegistry metricRegistry;

    @Before
    public void setUpMetricsFactoryTest() throws Exception {
        this.metricRegistry = MetricsFactory.getInstance();
    }

    @Test
    public void testSingleton() {
        assertTrue(this.metricRegistry == MetricsFactory.getInstance());
    }

    @Test
    public void testMetricsCreation() {
        final Counter counter = MetricsFactory.createCounter("Counter");
        this.assertMetric(counter, MetricsFactory.createCounter("Counter"));

        final Timer timer = MetricsFactory.createTimer("Timer");
        this.assertMetric(timer, MetricsFactory.createTimer("Timer"));

        final Histogram histogram = MetricsFactory.createHistogram("Histogram");
        this.assertMetric(histogram, MetricsFactory.createHistogram("Histogram"));

        final Gauge<Integer> gauge = MetricsFactory.<Integer>createGauge("Gauge", () -> 1);
        assertNotNull(gauge);
    }

    @Test
    public void testNewMetricsInstanceCreation() {
        final MetricRegistry metricRegistry = MetricsFactory.getNewInstance();
        assertNotNull(metricRegistry);
        assertFalse(metricRegistry == this.metricRegistry);
    }

    @Test
    public void testGauge() {
        final Gauge<String> stringGauge = MetricsFactory.<String>createGauge("DummyGauge", () -> "foo");
        assertEquals("foo", stringGauge.getValue());
    }

    private <T extends Metric> void assertMetric(T metric, T newMetric) {
        assertNotNull(metric);
        assertTrue(metric == newMetric);
    }
}