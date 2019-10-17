package com.sbt.concurrent;

import static java.util.stream.IntStream.range;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new MagicCounter();
        range(0,1000).forEach((j) -> {new Thread(counter::increment).start(); new Thread(counter::getValue);});
        Thread.sleep(10000);
        System.out.println("Final number (should be 1000): " + counter.getValue());
    }

}
