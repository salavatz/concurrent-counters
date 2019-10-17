package com.sbt.concurrent;

public class SimpleCounter implements Counter{
    private int i = 0;
    @Override
    public void increment() {
        i++;
    }

    @Override
    public long getValue() {
        return i;
    }
}
