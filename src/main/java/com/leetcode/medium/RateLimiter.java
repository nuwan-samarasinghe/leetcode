package com.leetcode.medium;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

class RateLimiterModel {

    private Deque<Instant> requestTimestamps;

    private final Duration window;
    private final int maxRequestCount;

    public RateLimiterModel(Duration window, int maxRequestCount) {
        this.requestTimestamps = new java.util.LinkedList<>();
        this.window = window;
        this.maxRequestCount = maxRequestCount;
    }

    private void resetWindowIfNeeded() {
        Instant now = Instant.now();
        Instant slidingWindow = now.minus(window);
        while (!this.requestTimestamps.isEmpty() && this.requestTimestamps.peek().isBefore(slidingWindow)) {
            this.requestTimestamps.poll();
        }
    }

    public synchronized boolean isAllowed() {
        resetWindowIfNeeded();
        if (this.requestTimestamps.size() < maxRequestCount) {
            this.requestTimestamps.offer(Instant.now());
            return true;
        }
        return false;
    }
}

public class RateLimiter {

    private final ConcurrentHashMap<String, RateLimiterModel> userRateLimiters;
    private final Duration windowMinutes;
    private final int maxRequestCount;

    public RateLimiter(Duration windowMinutes, int maxRequestCount) {
        this.userRateLimiters = new ConcurrentHashMap<>();
        this.windowMinutes = windowMinutes;
        this.maxRequestCount = maxRequestCount;
    }

    public boolean isAllowed(String user) {
        return this.userRateLimiters.computeIfAbsent(user, k -> new RateLimiterModel(windowMinutes, maxRequestCount)).isAllowed();
    }

}

class TestRateLimiter {
    
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private static String now() {
        return LocalTime.now().format(FORMATTER);
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(Duration.ofSeconds(5), 5);

        String userId = "user-1";

        System.out.println("First burst:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(now() + " request " + i + " -> " + rateLimiter.isAllowed(userId));
        }

        System.out.println(now() + " extra request -> " + rateLimiter.isAllowed(userId));

        System.out.println("Sleeping until just before window reset...");
        Thread.sleep(4_900);

        System.out.println("Request just before reset:");
        System.out.println(now() + " before reset request -> " + rateLimiter.isAllowed(userId));

        System.out.println("Sleeping a little more to cross window boundary...");
        Thread.sleep(200);

        System.out.println("Second burst after reset:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(now() + " request " + i + " -> " + rateLimiter.isAllowed(userId));
        }

        System.out.println(now() + " extra request -> " + rateLimiter.isAllowed(userId));
    }
}
