package com.kdpark.tree.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private Node root;
    private int size;

    public KdTree() {}

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;
        root = insert(root, p, VERTICAL);
    }

    private Node insert(Node n, Point2D p, boolean orientation) {
        if (n == null) {
            size++;
            return new Node(p, orientation);
        }

        double newOne = n.orientation == VERTICAL ? p.x(): p.y();
        double cmpOne = n.orientation == VERTICAL ? n.point.x() : n.point.y();

        if (newOne < cmpOne)
            n.left = insert(n.left, p, !n.orientation);
        else
            n.right = insert(n.right, p, !n.orientation);

        return n;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node n = root;
        while (n != null) {
            if (n.point.equals(p)) return true;

            double newOne = n.orientation == VERTICAL ? p.x(): p.y();
            double cmpOne = n.orientation == VERTICAL ? n.point.x() : n.point.y();

            if (newOne < cmpOne)
                n = n.left;
            else
                n = n.right;
        }

        return false;
    }

    public void draw() {
        Node n = root;

        draw(root, 0.0, 1.0, 0.0, 1.0);
    }

    private void draw(Node n, double top, double bottom, double left, double right) {
        if (n == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(n.point.x(), n.point.y());

        StdDraw.setPenColor(n.orientation == VERTICAL ? StdDraw.RED : StdDraw.BLUE);

        if (n.orientation == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), top, n.point.x(), bottom);

            draw(n.left, top, bottom, left, n.point.x());
            draw(n.right, top, bottom, n.point.x(), right);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(left, n.point.y(), right, n.point.y());

            draw(n.left, top, n.point.y(), left, right);
            draw(n.right, n.point.y(), bottom, left, right);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> list = new ArrayList<>();

        range(list, rect, new RectHV(0.0, 0.0, 1.0, 1.0), root);

        return list;
    }

    private void range(List<Point2D> list, RectHV findRect, RectHV rangeRect, Node n) {
        if (n == null || !findRect.intersects(rangeRect)) return;

        if (findRect.contains(n.point)) list.add(n.point);

        if (n.orientation == VERTICAL) {
            range(list, findRect, new RectHV(rangeRect.xmin(), rangeRect.ymin(), n.point.x(), rangeRect.ymax()), n.left);
            range(list, findRect, new RectHV(n.point.x(), rangeRect.ymin(), rangeRect.xmax(), rangeRect.ymax()), n.right);
        } else {
            range(list, findRect, new RectHV(rangeRect.xmin(), rangeRect.ymin(), rangeRect.xmax(), n.point.y()), n.left);
            range(list, findRect, new RectHV(rangeRect.xmin(), n.point.y(), rangeRect.xmax(), rangeRect.ymax()), n.right);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;

        return nearest(root, root.point, p, new RectHV(0.0, 0.0, 1.0, 1.0));
    }

    private Point2D nearest(Node n, Point2D min, Point2D p, RectHV rect) {
        if (n == null || rect.distanceTo(p) > min.distanceTo(p)) return min;

        if (n.point.distanceTo(p) < min.distanceTo(p)) min = n.point;

        if (n.orientation == VERTICAL) {
            min = nearest(n.left, min, p, new RectHV(rect.xmin(), rect.ymin(), n.point.x(), rect.ymax()));
            min = nearest(n.right, min, p, new RectHV(n.point.x(), rect.ymin(), rect.xmax(), rect.ymax()));
        } else {
            min = nearest(n.left, min, p, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.point.y()));
            min = nearest(n.right, min, p, new RectHV(rect.xmin(), n.point.y(), rect.xmax(), rect.ymax()));
        }

        return min;
    }

    private static class Node {
        private final Point2D point;
        private final boolean orientation;
        private Node left;
        private Node right;

        public Node(Point2D point, boolean orientation) {
            this.point = point;
            this.orientation = orientation;
        }
    }
}
