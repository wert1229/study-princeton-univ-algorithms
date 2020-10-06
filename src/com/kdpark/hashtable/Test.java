package com.kdpark.hashtable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        int[][] arr = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(arr).forEach(ints -> {
            pq.add(ints[1]);
        });
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        int answer = 0;
        for (int[] route : arr) {
            if (pq.peek() != null && route[0] <= pq.peek()) continue;
            pq.poll();
            answer++;
        }

        System.out.println(answer + 1);
    }
}
