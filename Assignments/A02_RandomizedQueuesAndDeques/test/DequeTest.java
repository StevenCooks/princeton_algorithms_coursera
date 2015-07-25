import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Unit tests for Deque class.
 * 
 * @author Steven Cooks
 */
public class DequeTest {

    /**
     * Test method for {@link Deque#Deque()}.
     */
    @Test
    public void testDeque() {
    }

    @Test(expected = NullPointerException.class)
    public void addFirstNullItemThrowException() {
        Deque<String> deque = new Deque<String>();
        String item = null;
        deque.addFirst(item);
    }

    /**
     * Test method for {@link Deque#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        assertTrue(!deque.isEmpty());
        deque.removeFirst();
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    /**
     * Test method for {@link Deque#size()}.
     */
    @Test
    public void testSize() {
        Deque<Integer> deque = new Deque<Integer>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addFirst(2);
        assertEquals(2, deque.size());
        deque.removeLast();
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    /**
     * Test method for {@link Deque#addFirst(java.lang.Object)}.
     */
    @Test
    public void testAddFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(3);
        deque.addFirst(2);
        assertTrue(deque.removeFirst() == 2);
        assertTrue(deque.removeFirst() == 1);
        assertTrue(deque.removeFirst() == 3);
    }

    /**
     * Test method for {@link Deque#addLast(java.lang.Object)}.
     */
    @Test
    public void testAddLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        assertTrue(deque.removeFirst() == 1);
        assertTrue(deque.removeFirst() == 2);
        deque.addLast(3);
        deque.addLast(4);
        assertTrue(deque.removeLast() == 4);
        assertTrue(deque.removeLast() == 3);
    }

    /**
     * Test method for {@link Deque#removeFirst()}.
     */
    @Test
    public void testRemoveFirst() {

    }

    /**
     * Test method for {@link Deque#removeLast()}.
     */
    @Test
    public void testRemoveLast() {
    }

    /**
     * Test method for {@link Deque#iterator()}.
     */
    @Test
    public void testIterator() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("to");
        deque.addLast("be");
        deque.addLast("or");
        deque.addLast("not");
        String str = toString(deque);
        String expected = "[to be or not]";
        assertEquals(expected, str);
    }

    @Test(expected = NullPointerException.class)
    public void addLastNullItemThrowException() {
        Deque<String> deque = new Deque<String>();
        String item = null;
        deque.addLast(item);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeLastNullItemThrowException() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("a");
        deque.addLast("B");
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeFirstNullItemThrowException() {
        Deque<String> deque = new Deque<String>();
        deque.removeFirst();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void callRemoveInIteratorThrowException() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("cc");
        Iterator<String> iter = deque.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void callNextOnEmptyThrowException() {
        Deque<String> deque = new Deque<String>();
        Iterator<String> iter = deque.iterator();
        iter.next();
    }

    private String toString(Deque<?> queue) {
        StringBuilder sb = new StringBuilder("[");
        if (!queue.isEmpty()) {
            for (Object object : queue) {
                sb.append(object.toString()).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

}
