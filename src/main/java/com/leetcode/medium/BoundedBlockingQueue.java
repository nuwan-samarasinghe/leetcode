package com.leetcode.medium;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class QueueException extends RuntimeException {

    public QueueException(String message) {
        super(message);
    }
}

public class BoundedBlockingQueue<T> {

    private final int capacity;
    private final Queue<T> queue;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        } 
        queue.offer(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }

}

class TestBoundedBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Producing " + i);
                    queue.enqueue(i);
                    System.out.println("Produced " + i + ", size = " + queue.size());
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1000);
                    int item = queue.dequeue();
                    System.out.println("Consumed " + item + ", size = " + queue.size());
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Done");
    }
}
