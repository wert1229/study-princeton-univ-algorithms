package com.kdpark.priorityqueue;

public class HeapSort {
    public static <T extends Comparable<? super T>> void sort(T[] pq) {
        int N = pq.length - 1;

        for (int k = (N - 1) / 2; k >= 0; k--) {
            sink(pq, k, N);
        }

        while (N >= 0) {
            exch(pq, 0, N);
            sink(pq, 0, --N);
        }
    }

    private static <T extends Comparable<? super T>> void sink(T[] pq, int k, int N) {
        while(2 * k + 1 <= N) {
            int j = 2 * k + 1;
            if (j < N && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static <T extends Comparable<? super T>> boolean less(T[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
}
