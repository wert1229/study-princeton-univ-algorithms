package com.kdpark.radixsort;

public class LSD {
    public static void sort(String[] a, int W) {
        int R = 256;
        int N = a.length;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];

            for (String s : a) {
                count[s.charAt(d) + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            for (String s : a) {
                aux[count[s.charAt(d)]++] = s;
            }

            System.arraycopy(aux, 0, a, 0, N);
        }
    }
}
