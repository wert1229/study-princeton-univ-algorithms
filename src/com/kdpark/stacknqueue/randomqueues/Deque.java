package com.kdpark.stacknqueue.randomqueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public Deque() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node();
        newNode.item = item;

        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;

        if (isEmpty()) tail = head;

        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node();
        newNode.item = item;

        newNode.prev = tail;
        if (tail != null)  tail.next = newNode;
        tail = newNode;

        if (isEmpty()) head = tail;

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.item;
        head = head.next;
        size--;

        if (isEmpty()) tail = null;
        else           head.prev = null;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = tail.item;
        tail = tail.prev;
        size--;

        if (isEmpty()) head = null;
        else           tail.next = null;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.removeFirst();
        deque.addLast(4);
        deque.addFirst(5);
        deque.addFirst(6);
        StdOut.print(deque.removeLast());

        deque.size();

        for (Integer integer : deque) {
            StdOut.print(integer);
        }
//        Deque<String> deque = new Deque<>();
//        deque.addFirst("1");
//        deque.addLast("2");
//        deque.addLast("3");
//        deque.addFirst("0");
//        deque.addFirst("-1");
//
//        for (String s : deque) {
//            StdOut.print(s);
//        }
//
//        StdOut.print(deque.removeLast());
//        StdOut.print(deque.removeLast());
//        StdOut.print(deque.removeFirst());
//        StdOut.print(deque.removeLast());
//        StdOut.print(deque.size());
//        StdOut.print(deque.isEmpty());
    }
}
