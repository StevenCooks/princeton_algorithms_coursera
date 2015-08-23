import java.awt.Color;
import java.util.Arrays;

public class Brute {

    // delta value for comparing two double numbers
    private final static double DELTA = 1e-10;

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No input given");
        }
        new Brute().run(args[0]);
    }

    private void run(String filename) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLUE);

        Point[] points = readInput(filename);
        int len = points.length;
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                for (int m = j + 1; m < len - 1; m++) {
                    for (int n = m + 1; n < len; n++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point s = points[m];
                        Point t = points[n];
                        Point[] group = { p, q, s, t };
                        Arrays.sort(group);
                        if (isCollinear(p, q, s, t)) {
                            printLine(group);
                            drawLine(group);
                        }
                    }
                }
            }
        }
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }

    private void drawLine(Point[] group) {
        for (Point point : group) {
            point.draw();
        }
        if (group.length >= 2) {
            group[0].drawTo(group[group.length - 1]);
        }
    }

    private void printLine(Point[] group) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < group.length; i++) {
            sb.append(group[i]);
            if (i != group.length - 1) {
                sb.append(" -> ");
            }
        }
        StdOut.println(sb.toString());
    }

    private boolean isCollinear(Point p, Point q, Point r, Point s) {
        double slope = p.slopeTo(q);
        return slopeEqual(slope, p.slopeTo(r))
                && slopeEqual(slope, p.slopeTo(s));
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
