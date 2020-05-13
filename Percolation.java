import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {
    public int m_iSize;
    public int m_iOpenSite = 0;
    public boolean m_bIsPercolated = false;
    public boolean[][] m_aSites;
    public boolean[][] m_bIsFull;
    public QuickUnionUF m_qUf;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException("n is not <= 0")
        }
        m_iSize = n;
        m_aSites = new boolean[n][n];
        m_bIsFull = new boolean[n][n];
        m_qUf = new QuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col) == false){
            int[] adj_x = {-1, 0, 1, 0};
            int[] adj_y = {0, 1, 0, -1};
            m_aSites[row-1][col-1] = true;
            for (int i = 0; i < 4; i++) {
                int x = row - 1 + adj_x[i];
                int y = col - 1 + adj_y[i];
                if (x < 0 || x >= m_iSize || y < 0 || y >= m_iSize){
                    continue;
                }
                if (isFull(x + 1, y + 1) == true){
                    m_bIsFull[row - 1][col - 1] = true;
                }
                m_qUf.union((row - 1) * m_iSize + col - 1, (x - 1) * m_iSize + y - 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > m_iSize || col < 1 || col > m_iSize){
            throw new IllegalArgumentException("row or col is not in [1:n]")
        }
        return m_aSites[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > m_iSize || col < 1 || col > m_iSize){
            throw new IllegalArgumentException("row or col is not in [1:n]")
        }
        return m_bIsFull[row - 1][col - 1];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return m_iOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return m_bIsPercolated;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}