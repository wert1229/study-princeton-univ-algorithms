package com.kdpark.unionfind.test;

import com.kdpark.unionfind.QuickUnion;
import com.kdpark.utils.FileUtil;

import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        Scanner sc = FileUtil.getFileReadScanner("unionfind/test/testset.txt");

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
