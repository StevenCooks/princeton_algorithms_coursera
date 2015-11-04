import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTreeTest {
    
    /** Test method for {@link .Solution } */
    KdTree tree;
    
    @Before
    public void setUp() throws Exception {
        tree = new KdTree();
    }
    
    @After
    public void tearDown() throws Exception {
        tree = null;
    }
    
    @Test
    public void testEmpty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }
    
    @Test
    public void testInsertIntoEmptyTree() {
        tree.insert(new Point2D(0.5, 0.5));
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertTrue(tree.contains(new Point2D(0.5, 0.5)));
        assertFalse(tree.contains(new Point2D(0.3, 0.5)));
    }

    @Test
    public void testInsertLeft() {
        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.2, 0.2));
        assertTrue(tree.contains(new Point2D(0.2, 0.2)));
    }

    @Test
    public void testInsertBottom() {
        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.2, 0.2));
        tree.insert(new Point2D(0.1, 0.1));
        assertTrue(tree.contains(new Point2D(0.1, 0.1)));
    }

    @Test
    public void testInsertRight() {
        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.8, 0.2));
        assertTrue(tree.contains(new Point2D(0.8, 0.2)));
    }

    @Test
    public void testInsertTop() {
        tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.8, 0.2));
        tree.insert(new Point2D(0.6, 0.8));
        assertTrue(tree.contains(new Point2D(0.6, 0.8)));
    }
    
    @Test
    public void testMultipleInsert() {
        tree.insert(new Point2D(0.5, 0.5));
        assertTrue(tree.contains(new Point2D(0.5, 0.5)));
        tree.insert(new Point2D(0.8, 0.8));
        assertTrue(tree.contains(new Point2D(0.8, 0.8)));
        tree.insert(new Point2D(0.2, 0.8));
        assertTrue(tree.contains(new Point2D(0.2, 0.8)));
        tree.insert(new Point2D(0.2, 0.2));
        assertTrue(tree.contains(new Point2D(0.2, 0.2)));
        tree.insert(new Point2D(0.8, 0.2));
        assertTrue(tree.contains(new Point2D(0.8, 0.2)));
        
        assertEquals(5, tree.size());
        assertFalse(tree.isEmpty());
        
        assertFalse(tree.contains(new Point2D(0.4, 0.4)));
        assertFalse(tree.contains(new Point2D(-0.4, 0.4)));
        assertFalse(tree.contains(new Point2D(0.4, -0.4)));
        assertFalse(tree.contains(new Point2D(-0.4, -0.4)));
        assertFalse(tree.contains(new Point2D(0, 1)));
        assertFalse(tree.contains(new Point2D(0.8, 0.4)));
        assertFalse(tree.contains(new Point2D(0.2, 0.4)));
    }
    
    @Test
    public void testMultipleInsertion() {
        tree.insert(new Point2D(0.6, 0.7));
        tree.insert(new Point2D(0.3, 0.5));
        tree.insert(new Point2D(0.2, 0.8));
        tree.insert(new Point2D(0.5, 0.6));
        assertTrue(tree.contains(new Point2D(0.5, 0.6)));
    }

    @Test
    public void testInsertOverlappingPoints() {
        tree.insert(new Point2D(0.6, 0.7));
        tree.insert(new Point2D(0.3, 0.5));
        tree.insert(new Point2D(0.3, 0.5));
        tree.insert(new Point2D(0.3, 0.5));
        assertTrue(tree.contains(new Point2D(0.3, 0.5)));
        assertFalse(tree.contains(new Point2D(0.4, 0.9)));
    }

    @Test
    public void testRangeSearch() {
        tree.insert(new Point2D(0.6, 0.7));
        tree.insert(new Point2D(0.3, 0.5));
        tree.insert(new Point2D(0.2, 0.8));
        tree.insert(new Point2D(0.5, 0.6));
        RectHV rect = new RectHV(0.55, 0.55, 0.8, 0.8);
        List<Point2D> actual = (List<Point2D>) tree.range(rect);
        List<Point2D> expected = new ArrayList<>();
        expected.add(new Point2D(0.6, 0.7));
        assertTrue(rect.contains(new Point2D(0.6, 0.7)));
        assertEquals(expected, actual);
    }

    @Test
    public void testRangeSearchMultiple() {
        tree.insert(new Point2D(0.6, 0.7));
        tree.insert(new Point2D(0.3, 0.5));
        tree.insert(new Point2D(0.2, 0.8));
        tree.insert(new Point2D(0.5, 0.6));
        RectHV rect = new RectHV(0.2, 0.2, 0.8, 0.8);
        List<Point2D> actual = (List<Point2D>) tree.range(rect);
        List<Point2D> expected = new ArrayList<>();
        expected.add(new Point2D(0.6, 0.7));
        expected.add(new Point2D(0.3, 0.5));
        expected.add(new Point2D(0.2, 0.8));
        expected.add(new Point2D(0.5, 0.6));
        assertEqualsIgnoreOrder(expected, actual);
    }

    @Test
    public void testRangeSearchInCircleExpectEmpty() {
        tree.insert(new Point2D(0.5, 0.8));
        tree.insert(new Point2D(0.2, 0.5));
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.8, 0.5));
        RectHV rect = new RectHV(0.4, 0.4, 0.6, 0.6);
        List<Point2D> actual = (List<Point2D>) tree.range(rect);
        List<Point2D> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void testRangeSearchInCircle() {
        tree.insert(new Point2D(0.5, 0.8));
        tree.insert(new Point2D(0.2, 0.5));
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.8, 0.5));
        RectHV rect = new RectHV(0.1, 0.3, 0.9, 0.7);
        List<Point2D> actual = (List<Point2D>) tree.range(rect);
        List<Point2D> expected = new ArrayList<>();
        expected.add(new Point2D(0.2, 0.5));
        expected.add(new Point2D(0.8, 0.5));
        assertFalse(rect.contains(new Point2D(0.5, 0.8)));
        assertFalse(rect.contains(new Point2D(0.5, 0.2)));
        assertTrue(rect.contains(new Point2D(0.2, 0.5)));
        assertTrue(rect.contains(new Point2D(0.8, 0.5)));
        assertEqualsIgnoreOrder(expected, actual);
    }

    @Test
    public void testRangeSearchInCircle2() {
        tree.insert(new Point2D(0.5, 0.8));
        tree.insert(new Point2D(0.2, 0.5));
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.8, 0.5));
        tree.insert(new Point2D(0.9, 0.6));
        RectHV rect = new RectHV(0.1, 0.3, 0.3, 0.7);
        List<Point2D> actual = (List<Point2D>) tree.range(rect);
        List<Point2D> expected = new ArrayList<>();
        expected.add(new Point2D(0.2, 0.5));
        assertEqualsIgnoreOrder(expected, actual);
    }
    
    @Test
    public void testRangeSearchInCircle3() {
        tree.insert(new Point2D(0.5, 0.8));
        tree.insert(new Point2D(0.2, 0.5));
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.8, 0.5));
        RectHV rect = new RectHV(0.3, 0.1, 0.7, 0.9);
        List<Point2D> actual = (List<Point2D>) tree.range(rect);
        List<Point2D> expected = new ArrayList<>();
        expected.add(new Point2D(0.5, 0.8));
        expected.add(new Point2D(0.5, 0.2));
        assertTrue(rect.contains(new Point2D(0.5, 0.8)));
        assertTrue(rect.contains(new Point2D(0.5, 0.2)));
        assertFalse(rect.contains(new Point2D(0.2, 0.5)));
        assertFalse(rect.contains(new Point2D(0.8, 0.5)));
        assertEqualsIgnoreOrder(expected, actual);
    }
    
    @Test
    public void testNearestNeighborFromTopRight() {
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.6, 0.5));
        Point2D p = new Point2D(0.3, 0.5);
        Point2D actual = tree.nearest(p);
        Point2D expected = new Point2D(0.6, 0.5);
        assertEquals(expected, actual);
    }

    @Test
    public void testNearestNeighborFromTopRight2() {
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.3, 0.4));
        tree.insert(new Point2D(0.6, 0.9));
        Point2D p = new Point2D(0.3, 0.9);
        Point2D actual = tree.nearest(p);
        Point2D expected = new Point2D(0.6, 0.9);
        assertEquals(expected, actual);
    }

    @Test
    public void testNearestNeighborFromTopLeft() {
        tree.insert(new Point2D(0.5, 0.2));
        tree.insert(new Point2D(0.3, 0.4));
        tree.insert(new Point2D(0.6, 0.9));
        Point2D p = new Point2D(0.3, 0.9);
        Point2D actual = tree.nearest(p);
        Point2D expected = new Point2D(0.6, 0.9);
        assertEquals(expected, actual);
    }


    private void assertEqualsIgnoreOrder(List<Point2D> expected, List<Point2D> actual) {
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

}
