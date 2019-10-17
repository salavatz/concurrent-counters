package com.sbt.concurrent;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@Fork(1)
public class CounterBenchmark {

    private static final int n = 32;

    @Benchmark
    @Group("MutexCounter")
    @GroupThreads(n)
    public void incrementMutexCounter(MutexCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("MutexCounter")
    @GroupThreads(n)
    public long getValueMutexCounter(MutexCounterState state) {
        return state.counter.getValue();
    }

    @Benchmark
    @Group("LockCounter")
    @GroupThreads(n)
    public void incrementLockCounter(LockCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("LockCounter")
    @GroupThreads(n)
    public long getValueLockCounter(LockCounterState state) {
        return state.counter.getValue();
    }

    @Benchmark
    @Group("ConcurrentCounter")
    @GroupThreads(n)
    public void incrementConcurrentCounter(ConcurrentCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("ConcurrentCounter")
    @GroupThreads(n)
    public long getValueConcurrentCounter(ConcurrentCounterState state) {
        return state.counter.getValue();
    }

    @Benchmark
    @Group("MagicCounter")
    @GroupThreads(n)
    public void incrementMagicCounter(MagicCounterState state) {
        state.counter.increment();
    }

    @Benchmark
    @Group("MagicCounter")
    @GroupThreads(n)
    public long getValueMagicCounter(MagicCounterState state) {
        return state.counter.getValue();
    }

    @State(Scope.Group)
    public static class MutexCounterState {
        Counter counter = new MutexCounter();
    }

    @State(Scope.Group)
    public static class LockCounterState {
        Counter counter = new LockCounter();
    }

    @State(Scope.Group)
    public static class ConcurrentCounterState {
        Counter counter = new ConcurrentCounter();
    }

    @State(Scope.Group)
    public static class MagicCounterState {
        Counter counter = new MagicCounter();
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(CounterBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                //.threadGroups(2, 4)
                //.threads(32)
                .warmupTime(TimeValue.seconds(2))
                .measurementTime(TimeValue.seconds(2))
                .build();

        new Runner(opt).run();
    }

}
