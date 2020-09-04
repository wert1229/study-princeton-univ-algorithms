package com.kdpark.unionfind.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] results;
    public static final double ZVALUE = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation pcl = new Percolation(n);

            while (!pcl.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                pcl.open(row, col);
            }

            results[i] = (double) pcl.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - ZVALUE * stddev() / Math.sqrt(results.length);
    }

    public double confidenceHi() {
        return mean() + ZVALUE * stddev() / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
//        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        PercolationStats stats = new PercolationStats(1, 100);

        System.out.printf("mean() = %f%n", stats.mean());
        System.out.printf("stddev() = %f%n", stats.stddev());
        System.out.printf("%s confidence interval = [%f, %f]%n", "95%", stats.confidenceHi(), stats.confidenceLo());
    }
}
