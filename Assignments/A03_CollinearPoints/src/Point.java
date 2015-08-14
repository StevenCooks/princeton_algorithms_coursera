import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            return (int) (slopeTo(p1) - slopeTo(p2));
        }
    };

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // plot this point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // draw line between this point and that point to standard drawing
    @Override
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return 0;
        } else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Returns slope between this point and that point.
     * <p>
     * Return positive infinity for vertical line segment; return negative infinity
     * for a line between a point and itself; otherwise, (that.y - this.y) /
     * (that.x - this.x).
     * 
     * @param that argument point
     * @return slope between invoking point and argument point
     */
    public double slopeTo(Point that) {
        if (this.x == that.x) {
            if (this.y == that.y) {
                // a line between a point and itself
                return Double.NEGATIVE_INFINITY;
            } else {
                // vertical line segment
                return Double.POSITIVE_INFINITY;
            }
        } else {
            if (this.y == that.y) {
                // horizontal line => return positive 0
                return 0;
            } else {
                return 1.0 * (that.y - this.y) / (that.x - this.x);
            }
        }
    }

}
