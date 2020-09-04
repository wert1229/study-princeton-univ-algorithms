package com.kdpark.unionfind;

import java.util.Arrays;

/**
 * Implement weighting, path compression
 * init  -> O(N)
 * union -> O(lg*N)
 * find  -> O(lg*N)
 */
public class QuickUnion extends UnionFind {

    private final int[] weights;

    public QuickUnion(int n) {
        super(n);
        weights = new int[n];
        Arrays.fill(weights, 1);
    }

    @Override
    public void union(int p, int q) {
        int proot = find(p);
        int qroot = find(q);

        if (weights[proot] >= weights[qroot]) {
            arr[qroot] = proot;
            weights[proot] += weights[qroot];
        } else {
            arr[proot] = qroot;
            weights[qroot] += weights[proot];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int x) {
        if (x == arr[x]) return x;
        return arr[x] = find(arr[x]);
    }

    public void printWeights() {
        StringBuilder sb = new StringBuilder();
        for (int weight : weights) {
            sb.append(weight).append(" ");
        }
        System.out.println(sb);
    }
}
