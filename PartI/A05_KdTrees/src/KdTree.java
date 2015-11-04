/**
 * File: KdTree.java
 *************************************************************************
 * 
 * 
 *************************************************************************
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private int N;
    private Node root;

    private static class Node {
        private Point2D p; // point in this node
        private RectHV rect; // the axis-aligned rectangle corresponding to this
                             // node
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree

        public Node() { }

        public Node(Point2D p) {
            this.p = p;
        }
        
        @Override
        public String toString() {
            return p.toString() + rect.toString();
        }
    }

    public KdTree() {
        N = 0;
        root = null;
    }

    /**
     * Returns number of points in the set.
     * @return number of points in the set
     */
    public int size() {
        return N;
    }

    /**
     * Whether the set is empty.
     * @return true if set is empty
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Adds the point to the set (if it is not already in the set).
     * @param p
     */
    public void insert(Point2D p) {
        checkNull(p);
        if (root == null || root.p == null) {
            root = new Node();
            root.p = p;
            root.rect = new RectHV(0, 0, 1, 1);
            N++;
//            System.out.println(root);
            return;
        }
        Node node = root;
        boolean compareX = true;
        while (node != null && node.p != null) {
            if (node.p.equals(p)) {
                return;
            } 
            if (compareX) {
                if (p.x() < node.p.x()) {
                    if (node.lb == null) {
                        node.lb = createNode(node, p, compareX, true);
                    node = node.lb;
                        break;
                    } 
                    node = node.lb;
                } else {
                    if (node.rt == null) {
                        node.rt = createNode(node, p, compareX, false);
                    node = node.rt;
                        break;
                    }
                    node = node.rt;
                }
            } else {
                if (p.y() < node.p.y()) {
                    if (node.lb == null) {
                        node.lb = createNode(node, p, compareX, true);
                    node = node.lb;
                        break;
                    }
                    node = node.lb;
                } else {
                    if (node.rt == null) {
                        node.rt = createNode(node, p, compareX, false);
                    node = node.rt;
                        break;
                    }
                    node = node.rt;
                }
            }
            compareX = !compareX;
        }
//        System.out.println(node);
        N++;
    }
    
    private Node createNode(Node parent, Point2D p, boolean compareX, boolean lb) {
        Node node = new Node(p);
        RectHV pr = parent.rect;
        if (!compareX) {
            if (lb) {
                node.rect = new RectHV(pr.xmin(), pr.ymin(), pr.xmax(), parent.p.y());
            } else {
                node.rect = new RectHV(pr.xmin(), parent.p.y(), pr.xmax(), pr.ymax());
            }
        } else {
            if (lb) {
                node.rect = new RectHV(pr.xmin(), pr.ymin(), parent.p.x(), pr.ymax());
            } else {
                node.rect = new RectHV(parent.p.x(), pr.ymin(), pr.xmax(), pr.ymax());
            }
        }
        return node;
    }

    // search a node in the tree
    private Node search(Point2D p) {
        checkNull(p);
        Node node = root;
        boolean compareX = true;
        while (node != null && node.p != null) {
            if (node.p.equals(p)) {
                break;
            } else if (compareX) {
                if (p.x() < node.p.x()) {
                    node = node.lb;
                } else {
                    node = node.rt;
                }
            } else {
                if (p.y() < node.p.y()) {
                    node = node.lb;
                } else {
                    node = node.rt;
                }
            }
            compareX = !compareX;
        }
        return node;
    }

    /**
     * Does the set contain point p?
     * @param p
     * @return true if set contains given point
     */
    public boolean contains(Point2D p) {
        Node node = search(p);
        return node != null && node.p != null && node.p.equals(p);
    }

    /**
     * Draws all points in the set to standard draw.
     * @param p
     */
    public void draw() {
        Queue<Node> level = new LinkedList<>();
        level.add(root);
        boolean compareX = true;
        while (!level.isEmpty()) {
            int n = level.size();
            for (int i = 0; i < n; i++) {
                Node node = level.poll();
                if (node != null && node.p != null) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.setPenRadius(0.01);
                    node.p.draw();
                    if (compareX) {
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.line(node.p.x(), 0, node.p.x(), 1);
                    } else {
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.line(0, node.p.y(), 1, node.p.y());
                    }
                    level.offer(node.lb);
                    level.offer(node.rt);
                }
            }
            compareX = !compareX;
        }
    }

    /**
     * All points that are inside the rectangle.
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect);
        return range(rect, root);
    }

    private List<Point2D> range(RectHV rect, Node node) {
        List<Point2D> res = new ArrayList<>();
        if (node == null || node.p == null) {
            return res;
        }
        if (rect.contains(node.p)) {
            res.add(node.p);
        }
        if (node.lb != null && rect.intersects(node.lb.rect)) {
            res.addAll(range(rect, node.lb));
        }
        if (node.rt != null && rect.intersects(node.rt.rect)) {
            res.addAll(range(rect, node.rt));
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
        return nearest(p, root);
    }

    private Point2D nearest(Point2D p, Node node) {
        if (node == null || node.p == null) {
            return null;
        }
        if (node.p.equals(p)) {
            return node.p;
        }
        double dist = p.distanceTo(node.p);
        Point2D lb = nearest(p, node.lb);
        Point2D point = node.p;
        if (lb != null) {
            double d = p.distanceTo(node.lb.p);
            if (d < dist) {
                dist = d;
                point = lb;
            }
        }
        Point2D rt = nearest(p, node.rt);
        if (rt != null) {
            double d = p.distanceTo(node.rt.p);
            if (d < dist) {
                point = rt;
            }
        }
        return point;
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
