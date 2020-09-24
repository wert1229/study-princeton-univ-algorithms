package com.kdpark.tree;

import java.util.ArrayList;
import java.util.List;

public class RedBlackBST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val, RED);
        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            node.left = put(node.left, key, val);
        else if (cmp > 0)
            node.right = put(node.right, key, val);
        else
            node.val = val;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Value get(Key key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            if (cmp > 0) node = node.right;
            else         return node.val;
        }

        return null;
    }

    // Hibbard deletion
    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);

        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }

        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        DFS(list, root);
        return list;
    }

    private void DFS(List<Key> list, Node node) {
        if (node == null) return;

        DFS(list, node.left);
        list.add(node.key);
        DFS(list ,node.right);
    }

    private boolean isRed(Node x) {
        if (x == null) return BLACK;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;
        boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }
}
