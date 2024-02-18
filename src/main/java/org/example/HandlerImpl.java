package org.example;

import java.time.Duration;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HandlerImpl implements Handler {
    private final BlockingDeque<RetryEvent> retryQueue = new LinkedBlockingDeque<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    private final Client client = new ClientImpl();

    public HandlerImpl() {
        Duration timeout = timeout();
        ScheduledExecutorService sendRetryQueueScheduler = Executors.newScheduledThreadPool(1);
        sendRetryQueueScheduler.scheduleWithFixedDelay(() ->
            retryQueue.removeIf(retryEvent ->
                client.sendData(retryEvent.recipient(), retryEvent.payload()) == Result.ACCEPTED
            ),
            timeout.getSeconds(), timeout.getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public Duration timeout() {
        return Duration.ofSeconds(10L);
    }

    @Override
    public void performOperation() {
        Event event = client.readData();
        if (event == null) {
            return;
        }
        executor.submit(() -> {
            for (Address recipient : event.recipients()) {
                Result result = client.sendData(recipient, event.payload());
                if (result == Result.REJECTED) {
                    retryQueue.add(new RetryEvent(recipient, event.payload()));
                }
            }
        });
    }
}
