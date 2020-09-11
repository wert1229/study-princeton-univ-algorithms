package com.kdpark.stacknqueue;

public class ArrayQueue implements Queue {

    private String[] s;
    private int head = 0;
    private int tail = 0;
    private final int INIT_SIZE = 10;

    public ArrayQueue() {
        s = new String[INIT_SIZE];
    }

    @Override
    public void enqueue(String item) {
        if (tail == s.length) resize(s.length * 2);
        s[tail++] = item;
    }

    @Override
    public String dequeue() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if (size() == s.length / 4) resize(s.length / 2);
        String item = s[head];
        s[head++] = null;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public int size() {
        return tail - head;
    }

    private void resize(int size) {
        String[] d = new String[size];
        System.arraycopy(s, head, d, 0, size());
        s = d;
        tail = size();
        head= 0;
    }
}
