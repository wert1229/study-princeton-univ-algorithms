package com.kdpark.unionfind.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
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
        uf2 = new WeightedQuickUnionUF(blockCount + 1);

        top = blockCount;
        bottom = blockCount + 1;
        openMap = new boolean[blockCount];

        for (int i = 0; i < n; i++) {
            uf.union(top, i);
            uf2.union(top, i);
            uf.union(bottom, n * (n - 1) + i);
        }
    }

    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException();
        int idx = getIndex(row, col);

        if (openMap[idx]) return;

        if (row != 1 && isOpen(row - 1, col)) {
            int top = getIndex(row - 1, col);
            uf.union(idx, top);
            uf2.union(idx, top);
        }
        if (row != n && isOpen(row + 1, col)) {
            int bottom = getIndex(row + 1, col);
            uf.union(idx, bottom);
            uf2.union(idx, bottom);
        }
        if (col != 1 && isOpen(row, col - 1)) {
            int left = getIndex(row, col - 1);
            uf.union(idx, left);
            uf2.union(idx, left);
        }
        if (col != n && isOpen(row, col + 1)) {
            int right = getIndex(row, col + 1);
            uf.union(idx, right);
            uf2.union(idx, right);
        }

        openMap[idx] = true;
        count++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException();
        return openMap[getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException();
        return isOpen(row, col) && uf2.find(getIndex(row, col)) == uf2.find(top);
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
