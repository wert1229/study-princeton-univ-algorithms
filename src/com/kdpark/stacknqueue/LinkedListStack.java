package com.kdpark.stacknqueue;

public class LinkedListStack implements Stack {

    private Node first = null;

    @Override
    public void push(String item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
    }

    @Override
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        Node node = first;
        int count = 0;

        while (node != null) {
            node = node.next;
            count++;
        }

        return count;
    }

    private class Node {
        String item;
        Node next;
    }
}
