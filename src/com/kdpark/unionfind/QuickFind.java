package com.kdpark.unionfind;

/**
 * init  -> O(N)
 * union -> O(N)
 * find  -> O(1)
 */
public class QuickFind extends UnionFind {

    public QuickFind(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        int pvalue = arr[p];
        int qvalue = arr[q];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == pvalue) arr[i] = qvalue;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return arr[p] == arr[q];
    }
}
