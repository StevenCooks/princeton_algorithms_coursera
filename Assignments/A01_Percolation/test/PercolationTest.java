import static org.junit.Assert.*;

import org.junit.Test;


public class PercolationTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void testConstructorException() {
        int N = -1;
        Percolation perco = new Percolation(N);
        perco.percolates();
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testIndexException() {
        int N = 10;
        Percolation perco = new Percolation(N);
        perco.open(10, 0);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testIndexException2() {
        int N = 10;
        Percolation perco = new Percolation(N);
        perco.isOpen(-1, 8);
    }

    @Test
    public void testBackWash() {
        int N = 10;
        Percolation perco = new Percolation(N);
        perco.open(10, 2);
        perco.open(2, 10);
        perco.open(6, 8);
        perco.open(2, 6);
        perco.open(1, 4);
        perco.open(8, 4);
        perco.open(10, 1);
        perco.open(4, 2);
        perco.open(4, 8);
        perco.open(9, 3);
        
        perco.open(2, 2);
        perco.open(9, 1);
        perco.open(4, 3);
        perco.open(5, 5);
        perco.open(5, 7);
        perco.open(2, 8);
        perco.open(6, 4);
        perco.open(7, 5);
        perco.open(9, 6);
        perco.open(3, 7);

        perco.open(4, 7);
        perco.open(7, 1);
        perco.open(9, 4);
        perco.open(3, 10);
        perco.open(1, 10);
        perco.open(10,10);
        perco.open(9, 7);
        perco.open(1, 5);
        perco.open(9, 8);
        perco.open(6, 1);
        
        perco.open(2, 5);
        perco.open(3, 4);
        perco.open(6, 9);
        perco.open(5, 8);
        perco.open(3, 2);
        perco.open(4, 6);
        perco.open(1, 7);
        perco.open(7, 9);
        perco.open(3, 9);
        perco.open(4, 4);
        
        perco.open(4, 10);
        perco.open(3, 5);
        perco.open(3, 8);
        perco.open(1, 8);
        perco.open(3, 1);
        perco.open(6, 7);
        perco.open(2, 3);
        perco.open(7, 4);
        perco.open(9, 10);
        perco.open(7, 6);

        perco.open(5, 2);
        perco.open(8, 3);
        perco.open(10, 8);
        perco.open(7, 10);
        perco.open(4, 5);
        assertTrue(!perco.percolates());
        perco.open(8, 10);
        assertTrue(perco.percolates());
        
        assertTrue(!perco.isFull(10, 1));
        assertTrue(!perco.isFull(9, 1));
        assertTrue(!perco.isFull(10, 8));

    }

}
