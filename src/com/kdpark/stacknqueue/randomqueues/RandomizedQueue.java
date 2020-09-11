package com.kdpark.stacknqueue.randomqueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(items.length * 2);

        items[size++] = item;

        shuffle();
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == items.length / 4) resize(items.length / 2);
        Item item = items[--size];
        items[size] = null;

        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(size);
        return items[random];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return items[currentIndex++];
            }
        };
    }

    private void shuffle() {
        int random = StdRandom.uniform(size);
        Item temp = items[size - 1];
        items[size - 1] = items[random];
        items[random] = temp;
    }

    private void resize(int resize) {
        Item[] d = (Item[]) new Object[resize];
        System.arraycopy(items, 0, d, 0, size);
        items = d;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        queue.enqueue("0");
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.dequeue();

//        StdOut.print(queue.isEmpty());
//        StdOut.print(queue.sample());
//        StdOut.print(queue.dequeue());
//        StdOut.print(queue.size());

        for (String s : queue) {
            StdOut.print(s);
        }
    }
}
