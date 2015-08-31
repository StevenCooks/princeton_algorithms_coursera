import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSETTest {

    PointSET set;

    @Before
    public void setUp() throws Exception {
        set = new PointSET();
    }

    @After
    public void tearDown() throws Exception {
        set = null;
    }

    @Test
    public void testNewPointSET() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testInsertIntoEmptySet() {
        Point2D p = new Point2D(0, 0);
        set.insert(p);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
    }

    @Test
    public void testContainsAfterInsert() {
        int N = 10;
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                Point2D p = new Point2D(i, j);
                set.insert(p);
            }
        }
        assertEquals(N * N, set.size());
        assertTrue(set.contains(new Point2D(0, 9)));
        assertTrue(set.contains(new Point2D(5, 4)));
        assertTrue(set.contains(new Point2D(9, 0)));
        assertFalse(set.contains(new Point2D(5, 5.5)));
        assertFalse(set.contains(new Point2D(10, 0)));
        assertFalse(set.contains(new Point2D(0.1, 9)));
    }

    @Test
    public void testRangeSearch() {
        int N = 10;
        double offset = 0.5;
        for (int i = 0; i < N; i++) {
            Point2D p = new Point2D(i + offset, N - i - offset);
            set.insert(p);
        }
        assertEquals(N, set.size());

        RectHV rect = new RectHV(4, 4, 6, 6);
        Iterable<Point2D> iterable = set.range(rect);
        List<Point2D> actual = new ArrayList<Point2D>();
        for (Point2D point2d : iterable) {
            actual.add(point2d);
        }

        List<Point2D> expected = new ArrayList<>();
        expected.add(new Point2D(4.5, 5.5));
        expected.add(new Point2D(5.5, 4.5));

        Collections.sort(actual);
        Collections.sort(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testRangeSearchOfEmpty() {
        int N = 10;
        double offset = 0.5;
        for (int i = 0; i < N; i++) {
            Point2D p = new Point2D(i + offset, N - i - offset);
            set.insert(p);
        }
        RectHV rect = new RectHV(-4, 4, -2, 6);
        Iterable<Point2D> iterable = set.range(rect);
        List<Point2D> actual = new ArrayList<Point2D>();
        for (Point2D point2d : iterable) {
            actual.add(point2d);
        }
        List<Point2D> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void testNearest() {
        int N = 10;
        double offset = 0.5;
        for (int i = 0; i < N; i++) {
            Point2D p = new Point2D(i + offset, N - i - offset);
            set.insert(p);
        }
        Point2D p = new Point2D(0, 0);
        Point2D actual = set.nearest(p);
        Set<Point2D> expected = new HashSet<>();
        expected.add(new Point2D(5.5, 4.5));
        expected.add(new Point2D(4.5, 5.5));
        assertTrue(expected.contains(actual));
    }
    
    @Test(expected=NullPointerException.class)
    public void testNullException() {
        assertTrue(set.isEmpty());
        Point2D p = null;
        set.insert(p);
    }

}
