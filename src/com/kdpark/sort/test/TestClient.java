package com.kdpark.sort.test;

import com.kdpark.priorityqueue.HeapSort;
import com.kdpark.utils.FileUtil;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        Scanner sc = FileUtil.getFileReadScanner("sort/test/testset.txt");

        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        int i = 0;

        while (sc.hasNext()) {
            arr[i++] = sc.nextInt();
        }


//        ShellSort.sort(arr);
//        MergeSort.sort(arr);
//        QuickSort.sort(arr);
        HeapSort.sort(arr);

        for (Integer integer : arr) {
            StdOut.print(integer);
        }
    }
}
