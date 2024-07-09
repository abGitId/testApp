package com.test.testApp.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
@Slf4j
public class AsyncService {

    @Autowired
    private TaskExecutor threadPoolTaskExecutor;

    @Async("threadPoolTaskExecutor")
    public void testAsync() {
        IntStream.range(1, 10).forEach(i -> {
            try {
                Thread.sleep(1000);
                log.info("i = " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public int testWithoutSupplyAsyncCall() {
        AtomicInteger total = new AtomicInteger();
        IntStream.range(1, 3).forEach(value -> {
            total.addAndGet(logTimeTakingCall());
        });
        return total.get();
    }

    public int testWithSupplyAsyncCall() {
        AtomicInteger total = new AtomicInteger(0);
        List<Integer> list = List.of(1, 2, 3);
        List<CompletableFuture<Integer>> futureList = list.stream().map(operand -> CompletableFuture.supplyAsync(() -> logTimeTakingCall(), threadPoolTaskExecutor)).toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        try {
            allFutures.get();
            IntStream.range(0, list.size()).forEach(value -> total.addAndGet(futureList.get(value).join()));
        } catch (InterruptedException | ExecutionException e) {
            log.error("InterruptedException occurred");
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            log.error("Exception occurred");
        }
        return total.get();
    }

    public int testWithRunAsyncCall() {
        AtomicInteger total = new AtomicInteger(0);
        List<Integer> list = List.of(1, 2, 3);
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> logTimeTakingCall(total));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> logTimeTakingCall(total));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> logTimeTakingCall(total));

        try {
            CompletableFuture.allOf(future1, future2, future3).thenRun(() -> System.out.println("total = " + total.get())).join();
        } catch (Exception e) {
            log.error("Exception occurred");
        }
        return total.get();
    }

    public int logTimeTakingCall() {
        AtomicInteger result = new AtomicInteger();
        IntStream.range(1, 5).forEach(i -> {
            try {
                Thread.sleep(1000);
                result.addAndGet(i);
                log.info("i = " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return result.get();
    }

    public void logTimeTakingCall(AtomicInteger result) {
        IntStream.range(1, 5).forEach(i -> {
            try {
                Thread.sleep(1000);
                result.addAndGet(+1);
                log.info("i = " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
