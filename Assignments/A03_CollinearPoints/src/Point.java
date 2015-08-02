import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            // TODO Auto-generated method stub
            return 0;
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

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point that) {
        // TODO Auto-generated method stub
        return 0;
    }

    public double slopeTo(Point that) {
        return 0.0;
    }

}
