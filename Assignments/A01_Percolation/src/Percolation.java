/****************************************************
 * {@link http://coursera.cs.princeton.edu/algs4/assignments/percolation.html }
 ****************************************************/

/**
 * @author Steven Cooks
 *
 */
public class Percolation {

    // grid sites with sites[i][j] = if site (row i, column j) is open
    private boolean[][] sites;

    // union-find data structure
    private WeightedQuickUnionUF uf;

    // number of rows in grid sites
    private int n;

    /**
     * Initializes a <em>N</em>-by-<em>N</em> grid, with all sites blocked,
     * and open two dummy sites.
     * 
     * @param N the integer of the number of rows and columns in grid
     * @throws java.lang.IllegalArgumentException if N <= 0
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be positive");
        }
        n = N;
        sites = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2); // sites + two dummy sites
    }

    /**
     * Opens site (row <tt>i</tt>, column <tt>j</tt>) if it is not open already.
     * i and j are 1-based indexed.
     * 
     * @param i the integer representing row number of one site (1-based)
     * @param j the integer representing column number of one site (1-based)
     * @throws java.lang.IndexOutOfBoundsException if (i, j) is out of boundary
     */
    public void open(int i, int j) {
        validateBound(i, j);
        if (!isOpen(i, j)) {
            sites[i - 1][j - 1] = true;
            int p = convertToSite(i, j);
            union(p, i - 1, j);
            union(p, i + 1, j);
            union(p, i, j - 1);
            union(p, i, j + 1);
            if (i == 1) {
                // union open site in top row with dummy0
                uf.union(p, n * n); 
            }
            if (i == n) {
                // union open site in bottom row with dummy1
                uf.union(p, n * n + 1);
            }
        }
    }

    /**
     * Is site (row <tt>i</tt>, column <tt>j</tt>) open?
     * 
     * @param i the integer representing row number of one site
     * @param j the integer representing column number of one site
     * @return <tt>true</tt> if this site is open, and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException if (i, j) is out of boundary
     */
    public boolean isOpen(int i, int j) {
        validateBound(i, j);
        return sites[i - 1][j - 1];
    }

    /**
     * Is site (row <tt>i</tt>, column <tt>j</tt>) full?
     * 
     * @param i the integer representing row number of one site
     * @param j the integer representing column number of one site
     * @return <tt>true</tt> if site (row <tt>i</tt>, column <tt>j</tt>) is an
     *         open site that can be connected to an open site in the top row
     *         via a chain of neighboring (left, right, up, down) open sites,
     *         and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException if (i, j) is out of boundary
     */
    public boolean isFull(int i, int j) {
        validateBound(i, j);
        // whether it is connected to dummy0?
        return uf.connected(convertToSite(i, j), n * n);
    }

    /**
     * Does the system percolate?
     * 
     * @return <tt>true</tt> if there is a full site in the bottom row, and
     *         <tt>false</tt> otherwise
     */
    public boolean percolates() {
        // is two dummy sites connected?
        return uf.connected(n * n, n * n + 1);
    }

    // union two open sites
    private void union(int p, int i, int j) {
        if (checkBound(i, j) && isOpen(i, j)) {
            uf.union(p, convertToSite(i, j));
        }
    }

    // convert (i, j) (1-based) to 1D site identifier (0-based)
    private int convertToSite(int i, int j) {
        validateBound(i, j);
        return (i - 1) * n + (j - 1);
    }

    // check given row and column is within boundary
    private void validateBound(int i, int j) {
        if (!checkBound(i, j)) {
            throw new IndexOutOfBoundsException("(row " + i + ", column " + j
                    + ") is not between 1 and N");
        }
    }

    private boolean checkBound(int i, int j) {
        return (i >= 1 && i <= n && j >= 1 && j <= n);
    }

    /**
     * Monte Carlo simulation to estimate the percolation threshold.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int counts = 0; // number of open sites
        int N = 20;
        Percolation perco = new Percolation(N);
        while (!perco.percolates()) {
            // open a new site when we find a blocked site
            while (true) {
                int i = 1 + StdRandom.uniform(N);
                int j = 1 + StdRandom.uniform(N);
                if (perco.isOpen(i, j)) {
                    perco.open(i, j);
                    break;
                }
            }
        }
        double p = 1.0 * counts / (N * N);
        StdOut.print(p);

    }
}
