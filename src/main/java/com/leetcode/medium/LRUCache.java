package com.leetcode.medium;

import java.util.LinkedHashMap;

public class LRUCache {

    private int capacity;

    private LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }
    
    public int get(int key) {
        if (!this.cache.containsKey(key)) {
            return -1;
        }
        return this.cache.get(key);
    }
    
    public void put(int key, int value) {
        if(this.cache.containsKey(key)) {
            this.cache.put(key, value);
            return;
        }
        if(this.cache.size() >= this.capacity) {
            this.cache.pollFirstEntry();
        }
        this.cache.put(key, value);
    }
}


class TestLRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1)); // returns 1
        cache.put(4, 4); // evicts key 2
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(5, 5); // evicts key 3
        System.out.println(cache.get(3)); // returns -1 (not found)
        System.out.println(cache.get(4)); // returns 4
        System.out.println(cache.get(5)); // returns 5
    }
}

