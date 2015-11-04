/**
 * File: PercolationStats.java
 *************************************************************************
 * compile:
 *   $ javac-algs4 PercolationStats.java
 * 
 * use: (e.g. T=200, N=100)
 *   $ java-algs4 PercolationStats 200 100
 *   
 * dependency:
 *     Percolation.java  // provides API service  
 * 
 * @date 2015.07.20
 * @author Steven Cooks
 *************************************************************************
 */
public class PercolationStats {

    // mean value of sample threshold 
    private double mean = 0.0;
    
    // standard variation of sample threshold
    private double stddev = 0.0;
    
    // counts[i] = number of open sites in experiment i
    private int[] counts;
    
    // number of independent experiments
    private int T;

    /**
     * @param N number of rows of N-by-N grid
     * @param T number of independent experiments
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be positive");
        }
        this.T = T;
        experiment(N);
    }

    // Monte Carlo simulation for T times
    private void experiment(int N) {
        counts = new int[T];
        for (int t = 0; t < T; t++) {
            int count = 0; // number of open sites
            Percolation perco = new Percolation(N);
            while (!perco.percolates()) {
                // open a new site when we find a blocked site
                while (true) {
                    int i = 1 + StdRandom.uniform(N);
                    int j = 1 + StdRandom.uniform(N);
                    if (!perco.isOpen(i, j)) {
                        perco.open(i, j);
                        count++;
                        break;
                    }
                }
            }
            counts[t] = count;
        }
        mean = StdStats.mean(counts) / (N * N);
        stddev = StdStats.stddev(counts) / (N * N);
    }

    /**
     * Calculates the sample mean of percolation threshold.
     * 
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * Calculates sample standard deviation of percolation threshold.
     * 
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * Calculates low endpoint of 95% confidence interval
     * 
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean - 1.96 * stddev / Math.sqrt(T);
    }

    /**
     * Calculates high endpoint of 95% confidence interval
     * 
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt(T);
    }

    /**
     * 
     * @param args two arguments, T and N
     */
    public static void main(String[] args) {
        int N = 1;
        int T = 1;
        for (int i = 0; i < 2; i++) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        }
        PercolationStats percStats = new PercolationStats(N, T);
        StdOut.printf("%-23s = ", "mean");
        StdOut.println(percStats.mean());
        StdOut.printf("%-23s = ", "stddev");
        StdOut.println(percStats.stddev());
        StdOut.printf("%-23s = ", "95% confidence interval");
        StdOut.println(percStats.confidenceLo() + ", " + percStats.confidenceHi());

    }

}
