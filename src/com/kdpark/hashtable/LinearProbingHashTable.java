package com.kdpark.hashtable;

public class LinearProbingHashTable<Key, Value> {
    private int M = 30001;
    private Key[] keys = (Key[]) new Object[M];
    private Value[] values = (Value[]) new Object[M];

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) break;
        }

        keys[i] = key;
        values[i] = value;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i]))
                return values[i];
        }
        return null;
    }
}
