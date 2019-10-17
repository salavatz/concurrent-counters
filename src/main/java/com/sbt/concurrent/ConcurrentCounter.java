package com.sbt.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentCounter implements Counter{
    private AtomicLong i = new AtomicLong(0);

    public void increment() {
        i.getAndIncrement();
    }

    public long getValue() {
        return i.get();
    }
}
