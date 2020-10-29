package com.kdpark.radixsort;

public class MSD {
    private static final int R = 256;
    private static String[] aux;

    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) return;
        int[] count = new int[R + 2];

        for (int i = lo; i <= hi ; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i < hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        System.arraycopy(aux, 0, a, lo, hi - lo);

        for (int r = 0; r < R; r++) {
            sort(a, aux, lo + count[r], lo + count[r + 1], d + 1);
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }
}
