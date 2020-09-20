package com.kdpark.sort.collinearpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }

        Point[] newArr = Arrays.copyOf(points, points.length);
        Arrays.sort(newArr);

        for (int i = 0; i < newArr.length - 1; i++) {
            if (newArr[i].compareTo(newArr[i + 1]) == 0) throw new IllegalArgumentException();
        }

        segments = new ArrayList<>();

        for (int i = 0; i < newArr.length; i++) {
            for (int j = i + 1; j < newArr.length; j++) {
                for (int k = j + 1; k < newArr.length; k++) {
                    for (int l = k + 1; l < newArr.length; l++) {
                        double slope1 = newArr[i].slopeTo(newArr[j]);
                        double slope2 = newArr[j].slopeTo(newArr[k]);
                        double slope3 = newArr[k].slopeTo(newArr[l]);
                        if (slope1 == slope2 && slope2 == slope3)
                            segments.add(new LineSegment(newArr[i], newArr[l]));
                    }
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
