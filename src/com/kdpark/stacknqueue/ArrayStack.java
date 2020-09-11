package com.kdpark.stacknqueue;

/**
 * init -> O(1)
 * push -> O(1) in average
 * pop  -> O(1) in average
 * size -> O(1)
 */
public class ArrayStack implements Stack {

    private String[] s;
    private int n = 0;
    private final int INIT_SIZE = 10;

    public ArrayStack() {
        s = new String[INIT_SIZE];
    }

    @Override
    public void push(String item) {
        if (n == s.length) resize(s.length * 2);

        s[n++]= item;
    }

    @Override
    public String pop() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();

        if (n > 0 && n == s.length / 4) resize(s.length / 2);

        String item = s[--n];
        s[n] = null;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    private void resize(int size) {
        String[] doubled = new String[size];
        System.arraycopy(s, 0, doubled, 0, n);
        s = doubled;
    }
}
