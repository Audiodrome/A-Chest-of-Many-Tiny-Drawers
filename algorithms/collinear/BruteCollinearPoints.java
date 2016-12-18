import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int len;

    public BruteCollinearPoints(Point[] points) {

        int N = points.length;

        System.out.println("size: " + N);
        segments = new LineSegment[2000];

        len = 0;

        //double temp = points[0].slopeTo(points[1]);
        for (int p = 0; p < N; p++) {
            for (int q = p + 1; q < N; q++) {
                double pq = points[p].slopeTo(points[q]);
                for (int r = q + 1; r < N; r++) {
                    double pr = points[p].slopeTo(points[r]);
                    if (pq == pr) {
                        for (int s = r + 1; s < N; s++) {
                            double ps = points[p].slopeTo(points[s]);
                            if (pr == ps) {
                                segments[len] = new LineSegment(points[p],
                                                                points[s]);
                                len++;
                                System.out.println(len);
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return len;
    }

    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        Arrays.sort(points);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        // points[0].drawTo(points[1]);
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        LineSegment[] segment = collinear.segments();
        for (int i = 0; i < collinear.numberOfSegments(); i++) {
            StdOut.println(segment[i].toString());
            segment[i].draw();
        }
        StdDraw.show();
    }
}
