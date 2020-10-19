package com.kdpark.mst;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class KruskalMST implements MST{
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);

            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }
}
