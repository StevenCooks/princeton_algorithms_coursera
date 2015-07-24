/**
 * File: RandomizedQueue.java
 *************************************************************************
 *
 * @author Steven Cooks
 *************************************************************************
 */
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    /**
     *  Constructs an empty randomized queue
     */
    public RandomizedQueue() {
    }
    
    /**
     * Returns <tt>true</tt> if this queue contains no element.
     * 
     * @return <tt>true</tt> if this queue contains no element
     */
    public boolean isEmpty() {
       return false; 
    }
    
    /**
     * Returns the number of elements in this queue.
     * 
     * @return the number of elements in this queue 
     */
    public int size() {
        return 0;
    }
    
    /**
     * Adds the item to the front of this queue.
     * 
     * @param item element to be added to the front of this queue 
     */
    public void enqueue(Item item) {
        
    }
    
    /**
     * Removes the returns a random item from the queue
     * 
     * @return element previously at the random position before removing
     * @throws NoSuchElementException if queue is empty before removing
     */
    public Item dequeue() {
        return null;
    }
    
    /**
     * Returns (but do not remove) a random item
     * 
     * @return element randomly chosen from the queue
     * @throws NoSuchElementException if queue is empty before removing
     */
    public Item sample() {
        return null;
    }
    
    /**
     * Returns a queue iterator over items in order from front to end.
     * 
     * @return a queue iterator over items in order from front to end
     * @throws UnsupportedOperationException if remove() is called in the
     *             iterator
     * @throws NoSuchElementException if next() is called and there are no more
     *             items to return
     */
    public Iterator<Item> iterator() {
        return null;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
