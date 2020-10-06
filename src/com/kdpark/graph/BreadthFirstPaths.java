package com.kdpark.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;

    public BreadthFirstPaths(Graph G, int s) {
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        marked[s] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }
}
