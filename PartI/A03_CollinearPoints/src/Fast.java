import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Fast {

    // delta value for comparing two double numbers
    private final static double DELTA = 1e-10;

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No input given");
        }
        new Fast().run(args[0]);
    }


    private Point[] points;

    // find all line segments that have at least 4 points
    private void run(String filename) {

        // set draw background
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLUE);

        points = readInput(filename);
        Arrays.sort(points);
        System.out.println(Arrays.toString(points));
        int len = points.length;
        for (int i = 0; i < len - 3; i++) {
            points[i].draw();
            Point[] neighbors = new Point[len - 1 - i];
            Arrays.sort(neighbors, points[i].SLOPE_ORDER);
            // System.out.println(Arrays.toString(neighbors));
            double lastSlope = points[i].slopeTo(neighbors[0]);
            List<Point> group = new ArrayList<>();
            group.add(points[i]);
            group.add(points[i + 1]);
            for (int j = 1; j < neighbors.length; j++) {
                double slope = points[i].slopeTo(neighbors[j]);
                if (slopeEqual(lastSlope, slope)) {
                    group.add(points[j]);
                } else {
                    printLine(group);
                    lastSlope = slope;
                    group.clear();
                }
            }
            printLine(group);
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
        System.out.println("done");
    }

    private void printLine(List<Point> group) {
        if (group.size() >= 4) {
            Collections.sort(group);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < group.size(); i++) {
                sb.append(group.get(i));
                if (i != group.size() - 1) {
                    sb.append(" -> ");
                }
            }
            StdOut.println(sb.toString());

        }
    }

    private boolean slopeEqual(double s, double t) {
        return Math.abs(s - t) < DELTA;
    }

    // read in points from input file
    private Point[] readInput(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }
        return points;
    }

}
