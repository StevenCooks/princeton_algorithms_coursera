/**
 * File: Dequeue.java
 ************************************************************************
 *
 * @date July 23, 2015
 * @author Steven Cooks
 ************************************************************************
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    /**
     * Constructs an empty deque.
     */
    public Deque() {
        // TODO Auto-generated constructor stub
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
    }

    /**
     * Returns <tt>true</tt> if this deque contains no element.
     * 
     * @return <tt>true</tt> if this deque contains no element
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the number of elements in this deque.
     * 
     * @return the number of elements in this deque
     */
    public int size() {
        return 0;
    }

    /**
     * Adds the item to the front of this deque.
     * 
     * @param item element to be added to the front of this deque
     */
    public void addFirst(Item item) {

    }

    /**
     * Adds the item to the end of this deque.
     * 
     * @param item element to be added to the end of this deque
     * @throws NullPointerException if the specific item is null
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Removes the returns the item from the front of this deque.
     * 
     * @return element previously at the front of deque before removing
     * @throws NoSuchElementException if deque is empty before removing
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return null;
    }

    /**
     * Removes the returns the item from the end of this deque.
     * 
     * @return element previously at the end of deque before removing
     * @throws NoSuchElementException if deque is empty before removing
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return null;
    }

    /**
     * Returns a deque iterator over items in order from front to end.
     * 
     * @return a deque iterator over items in order from front to end
     * @throws UnsupportedOperationException if remove() is called in the
     *             iterator
     * @throws NoSuchElementException if next() is called and there are no more
     *             items to return
     */
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {

    }

}
