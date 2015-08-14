import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PointTest {

    @Test
    public void testCompare_compareY() {
        Point p0 = new Point(0, 10000);
        Point p1 = new Point(10000, 0);
        assertTrue(p0.compareTo(p1) > 0);
    }

    @Test
    public void testCompare_compareY_2() {
        Point p0 = new Point(10000, 0);
        Point p1 = new Point(0, 10000);
        assertTrue(p0.compareTo(p1) < 0);
    }

    @Test
    public void testCompare_compareList() {
        Point p0 = new Point(19000, 10000);
        Point p1 = new Point(18000, 10000);
        Point p2 = new Point(32000, 10000);
        Point p3 = new Point(21000, 10000);
        Point p4 = new Point(1234, 5678);
        Point p5 = new Point(14000, 10000);
        Point[] actuals = {p0, p1, p2, p3, p4, p5};
        Arrays.sort(actuals);
        Point[] expecteds = {p4, p5, p1, p0, p3, p2};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testSlopeCompare_randomOrder() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 3);
        Point p4 = new Point(-1, 3);
        Point p5 = new Point(-1, 2);
        Point[] actuals = new Point[]{p3, p4, p1, p2, p5};
        Arrays.sort(actuals, p0.SLOPE_ORDER);
        Point[] expecteds = {p4, p5, p1, p2, p3};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testSlopeCompare_AscendingOrder() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 3);
        Point p4 = new Point(-1, 3);
        Point p5 = new Point(-1, 2);
        Point[] actuals = new Point[]{p4, p5, p1, p2, p3};
        Arrays.sort(actuals, p0.SLOPE_ORDER);
        Point[] expecteds = {p4, p5, p1, p2, p3};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testSlopeCompare_DescendingOrder() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 3);
        Point p4 = new Point(-1, 3);
        Point p5 = new Point(-1, 2);
        Point[] actuals = new Point[]{p3, p2, p1, p5, p4};
        Arrays.sort(actuals, p0.SLOPE_ORDER);
        Point[] expecteds = {p4, p5, p1, p2, p3};
        assertArrayEquals(expecteds, actuals);
    }
    
}
