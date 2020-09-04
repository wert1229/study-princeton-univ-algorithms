package com.kdpark.unionfind;

public abstract class UnionFind {
    protected int[] arr;

    public UnionFind(int n) {
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
    }

    public abstract void union(int p, int q);
    public abstract boolean connected(int p, int q);
}
