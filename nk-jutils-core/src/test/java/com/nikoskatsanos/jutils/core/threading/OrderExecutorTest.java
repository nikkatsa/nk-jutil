package com.nikoskatsanos.jutils.core.threading;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class OrderExecutorTest {

    private OrderExecutor orderExecutor;

    @Before
    public void setupOrderedExecutorTest() {
        this.orderExecutor = new OrderExecutor(Executors.newFixedThreadPool(4, new NamedThreadFactory("order-executor-delegate")));
    }

    @Test
    public void testOrder() throws InterruptedException {
        final List<Integer> order = new CopyOnWriteArrayList<Integer>();
        final CountDownLatch syncPoint = new CountDownLatch(16);
        final List<Runnable> runnables = IntStream.range(0, 16).mapToObj(idx -> new Runnable() {
            @Override
            public void run() {
                order.add(idx);
                syncPoint.countDown();
            }
        }).collect(Collectors.toList());

        runnables.forEach(r -> this.orderExecutor.execute(r));

        syncPoint.await();
        final AtomicInteger prev = new AtomicInteger(-1);
        order.forEach(i -> {
            assertTrue(prev.get() < i);
            prev.set(i);
        });
    }
}