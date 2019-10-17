package com.sbt.concurrent;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Control;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Group)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
public class LatencyBenchmark {

    private volatile boolean flagLockCounter = false;
    private final Counter lockCounter = new LockCounter();
    private volatile boolean flagMutexCounter = false;
    private final Counter mutexCounter = new MutexCounter();
    private volatile boolean flagMagicCounter = false;
    private final Counter magicCounter = new MagicCounter();
    private volatile boolean flagConcurrentCounter = false;
    private final Counter concurrentCounter = new ConcurrentCounter();

    @Benchmark
    @Group("LockCounter")
    public void pingLockCounter(Control cnt) {
        while (!cnt.stopMeasurement && !flagLockCounter){
            lockCounter.increment();
            flagLockCounter = true;
        }
    }

    @Benchmark
    @Group("LockCounter")
    public void pongLockCounter(Control cnt) {
        while (!cnt.stopMeasurement && flagLockCounter) {
            lockCounter.getValue();
            flagLockCounter = false;
        }
    }

    @Benchmark
    @Group("MutexCounter")
    public void pingMutexCounter(Control cnt) {
        while (!cnt.stopMeasurement && !flagMutexCounter){
            mutexCounter.increment();
            flagMutexCounter = true;
        }
    }

    @Benchmark
    @Group("MutexCounter")
    public void pongMutexCounter(Control cnt) {
        while (!cnt.stopMeasurement && flagMutexCounter) {
            mutexCounter.getValue();
            flagMutexCounter = false;
        }
    }

    @Benchmark
    @Group("MagicCounter")
    public void pingMagicCounter(Control cnt) {
        while (!cnt.stopMeasurement && !flagMagicCounter){
            magicCounter.increment();
            flagMagicCounter = true;
        }
    }

    @Benchmark
    @Group("MagicCounter")
    public void pongMagicCounter(Control cnt) {
        while (!cnt.stopMeasurement && flagMagicCounter) {
            magicCounter.getValue();
            flagMagicCounter = false;
        }
    }

    @Benchmark
    @Group("ConcurrentCounter")
    public void pingConcurrentCounter(Control cnt) {
        while (!cnt.stopMeasurement && !flagConcurrentCounter){
            concurrentCounter.increment();
            flagConcurrentCounter = true;
        }
    }

    @Benchmark
    @Group("ConcurrentCounter")
    public void pongConcurrentCounter(Control cnt) {
        while (!cnt.stopMeasurement && flagConcurrentCounter) {
            concurrentCounter.getValue();
            flagConcurrentCounter = false;
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LatencyBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .threads(2)
                .forks(1)
                .warmupTime(TimeValue.seconds(2))
                .measurementTime(TimeValue.seconds(2))
                .build();

        new Runner(opt).run();
    }
}
