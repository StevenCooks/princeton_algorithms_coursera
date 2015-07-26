/**
 * File: Subset.java
 *************************************************************************
 *
 * Compilation: javac-algs4 Subset.java
 *
 * Usage Example: 
 *  % echo A B C D E F G H I | java-algs4 Subset 3
 *  % echo AA BB BB BB BB BB CC CC | java-algs4 Subset 8
 *
 * @date July 23, 2015
 *************************************************************************
 */

/**
 * @author Steven Cooks
 */
public class Subset {

    /**
     * The first argument is k (i.e. the size of random set we want). This
     * algorithm uses <a
     * href="http://www.geeksforgeeks.org/reservoir-sampling/">reservoir
     * algorithm</a> for selecting k items from N uniformly at random.
     */
    public static void main(String[] args) {
        int N = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            N++;
            if (N > k) {
                // for probability = k / N, we take in the coming new item
                // and dequeue a random item before taking in.
                // Otherwise, re reject the coming item and keep the items we have.
                int random = StdRandom.uniform(N);
                if (random < k) {
                    // we need delete from element from current queue
                    queue.dequeue();
                    queue.enqueue(item);
                }
            } else {
                queue.enqueue(item);
            }
        }
        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
    }

}
