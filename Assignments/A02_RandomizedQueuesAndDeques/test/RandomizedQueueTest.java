import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RandomizedQueueTest {

    RandomizedQueue<Integer> queue;

    @Rule
    public Timeout globalTimeOut = new Timeout(1000);

    @Before
    public void setUp() throws Exception {
        queue = new RandomizedQueue<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        queue = null;
    }

    @Test(expected = NullPointerException.class)
    public void enqueueNull_throwsNullPointerException() {
        queue.enqueue(null);
    }

    @Test
    public void enqueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSize() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(3);
        assertEquals(4, queue.size());
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        assertEquals(8, queue.size());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals(4, queue.size());
        queue.dequeue();
        queue.dequeue();
        assertEquals(2, queue.size());
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    public void enqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
        queue.enqueue(4);
        assertEquals(1, queue.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueFromEmptyQueue_throwsNoSuchElementException() {
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueFromEmptyQueue2_throwsNoSuchElementException() {
        queue.enqueue(1);
        queue.dequeue();
        queue.dequeue();
    }

    @Test
    public void testIterator() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        for (int num : queue) {
            assertTrue(set.remove(num));
        }
        assertTrue(set.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIterator_throwsNoSuchElementException() {
        Iterator<Integer> iter = queue.iterator();
        iter.next();
    }

    @Test
    public void testIteratorForEmptyQueue() {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : queue) {
            set.add(num);
        }
        assertTrue(set.isEmpty());
    }

    @Test
    public void testNestedIterator_IteratorsShouldBeIndependent() {
        List<String> strs = new ArrayList<String>();
        strs.add("A");
        strs.add("B");
        strs.add("C");
        strs.add("D");
        strs.add("E");
        RandomizedQueue<String> rd = new RandomizedQueue<String>();
        for (String string : strs) {
            rd.enqueue(string);
        }
        Set<String> outer = new HashSet<String>(strs);
        for (String out : rd) {
            assertTrue(outer.remove(out));
            Set<String> inner = new HashSet<String>(strs);
            for (String in : rd) {
                assertTrue(inner.remove(in));
            }
            assertTrue(inner.isEmpty());
        }
        assertTrue(outer.isEmpty());
    }

    // Test7 iterator() returns correct items after a sequence of enqueue()
    // operations.
    @Test
    public void iteratorReturnsCorrectItemsAfterSequentialEnqueue() {
        int N = 100;
        enqueueIntegerN(queue, N);
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            set.add(i);
        }
        Iterator<Integer> iter = queue.iterator();
        while (iter.hasNext()) {
            assertTrue(set.remove(iter.next()));
        }
        assertTrue(set.isEmpty());
    }

    // Test8: iterator returns correct items after a sequence of enqueue()
    // and dequeue() operations.
    @Test
    public void iteratorReturnsCorrectItemsAfterSequentialEnqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        queue.dequeue();
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(6);
        queue.enqueue(6);
        queue.enqueue(6);
        Iterator<Integer> iter = queue.iterator();
        List<Integer> l1 = getValuesByIterator(iter);
        List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(1, 4, 6, 6, 6));
        assertListIgnoreOrder(l1, l2);
    }

    /**
     * <tt>[Test 9]</tt> For nested iterators, outer and inner iterator should
     * return the same set of items but in a different order.
     */
    @Test
    public void createTwoNestedIteratorsOverSameRandomizedQueue() {
        int N = 20;
        enqueueIntegerN(queue, N);
        boolean same = true;
        int n = 3;
        int t = 0;
        while (same && t < n) {
            Iterator<Integer> iterOuter = queue.iterator();
            List<Integer> outerList = new ArrayList<Integer>();
            List<Integer> innerList = new ArrayList<Integer>();
            int i = 0;
            while (iterOuter.hasNext()) {
                int outer = iterOuter.next();
                outerList.add(outer);
                Iterator<Integer> iterInner = queue.iterator();
                int j = 0;
                innerList.clear();
                while (iterInner.hasNext()) {
                    int inner = iterInner.next();
                    innerList.add(inner);
                    if (same) {
                        same = j == i && inner != outer;
                    }
                    j++;
                }
                i++;
            }
            assertListIgnoreOrder(outerList, innerList);
            t++;
        }
        assertFalse(same);
    }

    /**
     * <tt>[Test10]</tt>
     * 
     * For two parallel iterators, they should return the same set of values but
     * in a different order. In 500 times simulation, the values they return
     * should be different at least one time in theory.
     */
    @Test
    public void createTwoParallelIteratorsOverSameRandomizedQueue() {
        enqueueIntegerN(queue, 20);
        boolean same = true;
        int i = 0;
        int N = 500;
        while (i < N && same) {
            Iterator<Integer> iter1 = queue.iterator();
            List<Integer> l1 = getValuesByIterator(iter1);
            Iterator<Integer> iter2 = queue.iterator();
            List<Integer> l2 = getValuesByIterator(iter2);
            same = l1.equals(l2);
            assertListIgnoreOrder(l1, l2);
            i++;
        }
        assertFalse(same);
    }

    /**
     * <tt>[Test15.1]</tt>
     */
    @Test
    public void checkRandomnessOfIterator() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        int N = 3000;
        int[] counts = new int[q.size()];
        for (int i = 0; i < N; i++) {
            int count = 0;
            boolean Breturned = false;
            Iterator<String> iter = q.iterator();
            while (!Breturned && iter.hasNext()) {
                count++;
                Breturned = iter.next().equals("B");
            }
            counts[count - 1]++;
        }
        // +---------------------------------------------------------------+
        // | items until B is returned |     1     |     2     |     3     |
        // | --------------------------|-----------|-----------|-----------|
        // | observed frequency        | counts[0] | counts[1] | counts[2] |
        // | expected frequency        |   1000    |   1000    |    1000   |
        // +---------------------------------------------------------------+
        double sum = 0;
        int expected = N / 3;
        for (int observed : counts) {
            sum += 1.0 * (observed - expected) * (observed - expected) / expected;
        }
        double chi_squared = sum;
        // degree of freedom = (possible different outcome - 1) = 3 - 1 = 2;
        // then check the p-value table for significance level = 0.001
        double p_value = 13.82;
        // i.e. is chi_squared < p_value, we have 99.9% confidence that
        // the result is consistent.
        assertTrue(chi_squared < p_value);
    }
    
    /**
     * <tt>[Test15.2]</tt>
     * 
     * Enqueue strings A to H, create iterator(), and call next() until D is returned;
     * Repeat 8000 times.
     */
    @Test
    public void checkRandomnessOfIterator2() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        q.enqueue("D");
        q.enqueue("E");
        q.enqueue("F");
        q.enqueue("G");
        q.enqueue("H");
        int N = 8000;
        String target = "D";
        double chi_squared = chiSquaredTest(N, target, q);
        double p_value = 18.48;
        assertTrue(chi_squared < p_value);
    }
    
    /**
     * <tt>[Test15.3]</tt>
     * 
     * Enqueue strings A to J, create iterator(), and call next() until H is returned; 
     * Repeat 10000 times
     * 
     */
    @Test
    public void checkRandomnessOfIterator3() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (char ch = 'A'; ch <= 'J'; ch++) {
            q.enqueue(String.valueOf(ch));
        }
        int N = 10000;
        String target = "H";
        double chi_squared = chiSquaredTest(N, target, q);
        double p_value = 21.67;
        assertTrue(chi_squared < p_value);
    }

    private double chiSquaredTest(int N, String target, RandomizedQueue<String> q) {
        int[] counts = new int[q.size()];
        for (int i = 0; i < N; i++) {
            int count = 0;
            boolean Breturned = false;
            Iterator<String> iter = q.iterator();
            while (!Breturned && iter.hasNext()) {
                count++;
                Breturned = iter.next().equals(target);
            }
            counts[count - 1]++;
        }
        double chi_square = 0;
        int expected = N / q.size();
        for (int observed : counts) {
            chi_square += 1.0 * (observed - expected) * (observed - expected) / expected;
        }
        return chi_square;
    }

    // assert two lists contains the same set of values ignoring the order
    private void assertListIgnoreOrder(List<Integer> l1, List<Integer> l2) {
        Collections.sort(l1);
        Collections.sort(l2);
        assertEquals(l1, l2);
    }

    private List<Integer> getValuesByIterator(Iterator<Integer> iter) {
        List<Integer> list = new ArrayList<Integer>();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }

    private void enqueueIntegerN(RandomizedQueue<Integer> queue, int N) {
        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }
    }

}
