/**
 * File: PointSET.java
 *************************************************************************
 * Compilation : javac-algs4 PointSET.java
 * Execution   : java-algs4 PointSET
 * Dependencies: algs4.jar
 * 
 *************************************************************************
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 * The <tt>PointSET</tt> class represents a set of points in the unit square
 * (all points have x- and y-coordinates between 0 and 1 using <em>TreeSet</em>
 * <p>
 * PointSET supports {@link #insert(Point2D) insert} and
 * {@link #contains(Point2D) contains} in time proportional to the logarithm of
 * the number of points in the set in the worst case
 * <p>
 * It supports {@link #nearest(Point2D) nearest} and {@link #range(RectHV)
 * range} in time proportional to the number of points in the set.
 * <p>
 * 
 * @author Steven Cooks
 */
public class PointSET {

    private Set<Point2D> set = new TreeSet<>();

    public PointSET() {
    }

    /**
     * Returns number of points in the set.
     * @return number of points in the set
     */
    public int size() {
        return set.size();
    }

    /**
     * Whether the set is emtpy.
     * @return true if set is empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Adds the point to the set (if it is not already in the set).
     * @param p
     */
    public void insert(Point2D p) {
        checkNull(p);
        set.add(p);
    }

    /**
     * Does the set contain point p?
     * @param p
     * @return true if set contains given point
     */
    public boolean contains(Point2D p) {
        checkNull(p);
        return set.contains(p);
    }

    /**
     * Draws all points in the set to standard draw.
     * @param p
     */
    public void draw() {
        for (Point2D point : set) {
            point.draw();
        }
    }

    /**
     * All points that are inside the rectangle.
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect);
        List<Point2D> res = new ArrayList<>();
        for (Point2D p : set) {
            if (rect.contains(p)) {
                res.add(p);
            }
        }
        return res;
    }

    /**
     * Returns one nearest neighbor in the set to point p.
     * @param p
     * @return the nearest neighbor; null if no point in the set
     */
    public Point2D nearest(Point2D p) {
        checkNull(p);
        Point2D nearest = null;
        Double minDist = Double.MAX_VALUE;
        for (Point2D point : set) {
            double dist = point.distanceTo(p);
            if (dist < minDist) {
                minDist = dist;
                nearest = point;
            }
        }
        return nearest;
    }

    // throw exception if object is null
    private void checkNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) {

    }

}
