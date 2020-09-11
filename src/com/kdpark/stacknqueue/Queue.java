package com.kdpark.stacknqueue;

public interface Queue {
    void enqueue(String item);
    String dequeue();
    boolean isEmpty();
    int size();
}
