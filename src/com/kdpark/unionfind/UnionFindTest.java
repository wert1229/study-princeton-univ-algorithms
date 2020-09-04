package com.kdpark.unionfind;

import com.kdpark.utils.FileUtil;

import java.util.Scanner;

public class UnionFindTest {
    public static void main(String[] args) {
        Scanner sc = FileUtil.getFileReadScanner("unionfind/testset.txt");

        int N = sc.nextInt();

//        UnionFind uf = new QuickFind(N);
        QuickUnion uf = new QuickUnion(N);

        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();

            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " is connected to " + q);
        }

        uf.printWeights();
    }
}
