package com.kdpark.unionfind.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final boolean[] openMap;
    private final int n;
    private final int blockCount;
    private int count;
    private final int top;
    private final int bottom;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        blockCount = n * n;
        uf = new WeightedQuickUnionUF(blockCount + 2);
        top = blockCount;
        bottom = blockCount + 1;
        openMap = new boolean[blockCount];
        Arrays.fill(openMap, false);

        for (int i = 0; i < n; i++) {
            uf.union(top, i);
            uf.union(bottom, n * (n - 1) + i);
        }
    }

    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException();
        int idx = getIndex(row, col);

        if (openMap[idx]) return;

        if (row != 1 && isOpen(row - 1, col)) uf.union(idx, getIndex(row - 1, col));
        if (row != n && isOpen(row + 1, col)) uf.union(idx, getIndex(row + 1, col));
        if (col != 1 && isOpen(row, col - 1)) uf.union(idx, getIndex(row, col - 1));
        if (col != n && isOpen(row, col + 1)) uf.union(idx, getIndex(row, col + 1));

        openMap[idx] = true;
        count++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException();
        return openMap[getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException();
        return isOpen(row, col) && uf.find(getIndex(row, col)) == uf.find(top);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        if (n == 1) return openMap[0];
        return uf.find(bottom) == uf.find(top);
    }

    private int getIndex(int row, int col) {
        return n * (row - 1) + (col - 1);
    }
}
