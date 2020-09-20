package com.kdpark.sort.collinearpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }

        Point[] pointsCopySO = Arrays.copyOf(points, points.length);
        Point[] pointsCopyNO = Arrays.copyOf(points, points.length);

        segments = new ArrayList<>();

        Arrays.sort(pointsCopyNO);

        for (int i = 0; i < points.length - 1; i++) {
            if (pointsCopyNO[i].compareTo(pointsCopyNO[i + 1]) == 0) throw new IllegalArgumentException();
        }

        for (int i = 0; i < pointsCopyNO.length; ++i) {
            Point origin = pointsCopyNO[i];
            Arrays.sort(pointsCopySO);
            Arrays.sort(pointsCopySO, origin.slopeOrder());
            int count = 1;
            Point lineBeginning = null;
            for (int j = 0; j < pointsCopySO.length - 1; ++j) {
                if (pointsCopySO[j].slopeTo(origin) == pointsCopySO[j + 1].slopeTo(origin)) {
                    count++;
                    if (count == 2) {
                        lineBeginning = pointsCopySO[j];
                        count++;
                    }
                    else if (count >= 4 && j + 1 == pointsCopySO.length - 1) {
                        if (lineBeginning.compareTo(origin) > 0) {
                            segments.add(new LineSegment(origin, pointsCopySO[j + 1]));
                        }
                        count = 1;
                    }
                }
                else if (count >= 4) {
                    if (lineBeginning.compareTo(origin) > 0) {
                        segments.add(new LineSegment(origin, pointsCopySO[j]));
                    }
                    count = 1;
                }
                else {
                    count = 1;
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] arr = new LineSegment[segments.size()];
        return segments.toArray(arr);
    }
}
