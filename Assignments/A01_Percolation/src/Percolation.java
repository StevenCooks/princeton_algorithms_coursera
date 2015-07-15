/****************************************************
 * {@link http://coursera.cs.princeton.edu/algs4/assignments/percolation.html }
 ****************************************************/

/**
 * @author Steven Cooks
 *
 */
public class Percolation {

    /**
     * Initializes a <em>N</em>-by-<em>N</em> grid, with all sites blocked
     * 
     * @param N
     *            the integer of the number of rows and columns in grid
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "Illegal input N to initialize: " + Integer.toString(N));
        }
        n = N;
        sites = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = false;
            }
        }
    }

    /**
     * Opens site (row <tt>i</tt>, column <tt>j</tt>) if it is not open already.
     * i and j are 1-based indexed.
     * 
     * @param i
     *            the integer representing row number of one site
     * @param j
     *            the integer representing column number of one site
     */
    public void open(int i, int j) {
        checkBound(i, j);
    }

    /**
     * Is site (row <tt>i</tt>, column <tt>j</tt>) open?
     * 
     * @param i
     *            the integer representing row number of one site
     * @param j
     *            the integer representing column number of one site
     * @return <tt>true</tt> if this site is open, and <tt>false</tt> otherwise
     */
    public boolean isOpen(int i, int j) {
        checkBound(i, j);
        return false;
    }

    /**
     * Is site (row <tt>i</tt>, column <tt>j</tt>) full?
     * 
     * @param i
     *            the integer representing row number of one site
     * @param j
     *            the integer representing column number of one site
     * @return <tt>true</tt> if site (row <tt>i</tt>, column <tt>j</tt>) is an
     *         open site that can be connected to an open site in the top row
     *         via a chain of neighboring (left, right, up, down) open sites,
     *         and <tt>false</tt> otherwise
     */
    public boolean isFull(int i, int j) {
        checkBound(i, j);
        return false;
    }

    /**
     * Does the system percolate?
     * 
     * @return <tt>true</tt> if there is a full site in the bottom row, and
     *         <tt>false</tt> otherwise
     */
    public boolean percolates() {
        return false;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    private void checkBound(int i, int j) {
        //TODO check for 1-based index
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("row " + i + ", column " + j);
        }
    }

    // grid sites with sites[i][j] = if site (row i, column j) is open
    private boolean[][] sites;
    
    private int n;

}
