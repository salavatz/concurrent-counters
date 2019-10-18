package com.sbt.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicCounter implements Counter {

    private int n = 100_000;
    private List<Long> labels = new ArrayList<>(Collections.nCopies(n, 0L));

    public void increment() {
        labels.set(getId(), labels.get(getId()) + 1);
    }

    public long getValue() {
        return labels.stream().mapToLong(Long::longValue).sum();
    }

    private Integer getId() {
        //for main
        return Integer.parseInt(Thread.currentThread().getName().substring("Thread-".length()));
        //for benchmark
        // return Integer.parseInt(Thread.currentThread().getName().substring("com.sbt.concurrent.CounterBenchmark.MagicCounter-jmh-worker-".length()));
    }
}
