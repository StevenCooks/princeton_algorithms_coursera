/**
 * File: Dequeue.java
 ************************************************************************
 *  Compilation : javac-algs4 Dequeue.java
 *    Execution : java-algs4 Dequeue
 * Dependencies : StdIn.java StdOut.java
 *
 * @date July 23, 2015
 ************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>Deque</tt> class represents a double-end queue, which supports adding
 * and removing items from either the front or the back of the data structure.
 * Each operation, including each operation in iterator is constant worst-case
 * time and use space proportional to the number of items <em>currently</em> in
 * the queue.
 * 
 * @author Steven Cooks
 */
public class Deque<Item> implements Iterable<Item> {

    // dummy head node at the front of deque
    private Node head;

    // dummy tail node at the back of deque
    private Node tail;

    // number of items in deque excluding head and tail
    private int N;

    // helper linked list class
    private class Node {
        private Item item;
        private Node pre;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    /**
     * Constructs an empty deque.
     */
    public Deque() {
        Item item = null;
        head = new Node(item);
        tail = new Node(item);
        head.next = tail;
        tail.pre = head;
        N = 0;
    }

    /**
     * Returns <tt>true</tt> if this deque contains no element.
     * 
     * @return <tt>true</tt> if this deque contains no element
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of elements in this deque.
     * 
     * @return the number of elements in this deque
     */
    public int size() {
        return N;
    }

    /**
     * Adds the item to the front of this deque.
     * 
     * @param item element to be added to the front of this deque
     * @throws NullPointerException if the specific item is null
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        // insert node before head and head.next
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        N++;
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
        Node node = new Node(item);
        // insert node before tail.pre and tail;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre.next = node;
        tail.pre = node;
        N++;
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
        Node node = head.next;
        Item item = node.item;
        node.next.pre = head;
        head.next = node.next;
        node = null; // allow GC to work loitering
        N--;
        return item;
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
        Node node = tail.pre;
        Item item = node.item;
        node.pre.next = tail;
        tail.pre = node.pre;
        node = null;
        N--;
        return item;
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
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node cur = head.next;

        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        /**
         * Does support remove operation in iterator.
         * @throws UnsupportedOperationException if <em>remove</em> is called in
         *             iterator
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println(deque.size());
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
        StringBuilder sb = new StringBuilder("[");
        for (int integer : deque) {
            sb.append(integer + " ");
        }
        sb.append("]");
        StdOut.println(sb.toString());
    }

}
