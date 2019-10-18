package com.sbt.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockCounter implements Counter {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    private long i = 0;

    public void increment() {
        lock.writeLock().lock();
        try {
            i++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public long getValue() {
        lock.readLock().lock();
        try {
            return i;
        } finally {
            lock.readLock().unlock();
        }
    }
}
