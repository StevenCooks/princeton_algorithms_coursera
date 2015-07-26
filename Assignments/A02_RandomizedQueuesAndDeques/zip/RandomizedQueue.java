/**
 * File: RandomizedQueue.java
 *************************************************************************
 *  Compilation : javac-algs4 RandomizedQueue.java
 *    Execution : java-algs4 RandomizedQueue
 * Dependencies : StdIn.java StdOut.java
 *
 * @date July 23, 2015
 *************************************************************************
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 * 
 * @author Steven Cooks
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // array of items
    private Item[] a;

    // number of items on queue
    private int N;

    /**
     * Initializes an empty randomized queue.
     * 
     * Because java does not support generic array, so casting like
     * <tt>(T[]) new Object[]</tt> is type unsafe for compiler. We add
     * `@SuppressingWarings("unchecked")` to notify compiler that we know that
     * might be unsafe and we are almost confident that it will be type safe.
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        N = 0;
        // because java does not support generic array like new Item[2],
        // we have to use cast here.
        a = (Item[]) new Object[2];
    }

    /**
     * Returns <tt>true</tt> if this queue contains no element.
     * 
     * @return <tt>true</tt> if this queue contains no element
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of elements in this queue.
     * 
     * @return the number of elements in this queue
     */
    public int size() {
        return N;
    }

    // resize the underlying array holding the elements
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * Adds the item to the front of this queue.
     * 
     * @param item element to be added to the front of this queue
     * @throws NullPointerException when a null item is attempted to add
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == a.length) {
            // double size of array when array is full
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    /**
     * Removes the returns a random item from the queue.
     * 
     * @return element previously at the random position before removing
     * @throws NoSuchElementException if queue is empty before removing
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        int random = StdRandom.uniform(N);
        if (random != N - 1) {
            // swap item at random with item at the end
            Item item = a[N - 1];
            a[N - 1] = a[random];
            a[random] = item;
        }
        Item item = a[N - 1];
        a[N - 1] = null;
        N--;
        // halve the size of array when queue is one-quarter full
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    /**
     * Returns (but do not remove) a random item.
     * 
     * @return element randomly chosen from the queue
     * @throws NoSuchElementException if queue is empty before removing
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        int random = StdRandom.uniform(N);
        return a[random];
    }

    /**
     * Returns an independent queue iterator over items in random order.
     * 
     * @return a queue iterator over items in order from front to end
     * @throws UnsupportedOperationException if remove() is called in the
     *             iterator
     * @throws NoSuchElementException if next() is called and there are no more
     *             items to return
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i;
        private int[] indices;

        public ArrayIterator() {
            // shuffle an array of indices
            indices = new int[N];
            for (int j = 0; j < N; j++) {
                indices[j] = j;
            }
            StdRandom.shuffle(indices);
            i = N - 1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        /**
         * @throws UnsupportedOperationException if the {@code remove} operation
         *             is not supported by this iterator
         */
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[indices[i--]];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Iterator<Integer> iter = queue.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        StdOut.println("size: " + queue.size());
        StdOut.println(queue.sample());
        StdOut.println("size: " + queue.size());
        StdOut.println(queue.sample());
        StdOut.println("size: " + queue.size());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println("size: " + queue.size());
    }

}
