package com.sbt.concurrent;

public class MutexCounter implements Counter{
    private long i = 0;

    public synchronized void increment() {
        i++;
    }

    public synchronized long getValue() {
        return i;
    }
}
