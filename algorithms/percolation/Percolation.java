import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int size, bound, topID, bottomID;

    public Percolation(int n) {
        size = n;
        bound = (n + 1) * (n + 1);
        grid = new boolean[n+1][n+1];
        uf = new WeightedQuickUnionUF(bound + 1);
        topID = 0;
        bottomID = bound;
    }

    private void chkBounds(int i, int j) {

        if (i <= 0 || i > size || j <= 0 || j > size) {
            String msg = "Row or column is out of bounds";
            throw new IndexOutOfBoundsException(msg);
        }
    }

    private void connectToOpenNeighbors(int row, int col,
                                        int neighborR, int neighborC) {
        int b = size + 1;
        int p = xyTo1D(row, col);
        if (row == 1) connectToVirtualTop(p);

        if (neighborC < b && neighborR < b) {
            if (grid[neighborR][neighborC]) {
                int q = xyTo1D(neighborR, neighborC);
                uf.union(p, q);
            }
        }
        if (row == size && uf.connected(p, topID))
            connectToVirtualBottom(p);
    }

    private void connectToVirtualTop(int p) {
        uf.union(p, topID);
    }

    private void connectToVirtualBottom(int p) {
        uf.union(p, bottomID);
    }

    private int xyTo1D(int row, int col) {
        return size * row + col;
    }

    public void open(int row, int col) {
        chkBounds(row, col);

        if (!grid[row][col]) {
            grid[row][col] = true;

            connectToOpenNeighbors(row, col, row - 1, col);
            connectToOpenNeighbors(row, col, row + 1, col);
            connectToOpenNeighbors(row, col, row, col - 1);
            connectToOpenNeighbors(row, col, row, col + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        chkBounds(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        int p = xyTo1D(row, col);
        chkBounds(row, col);
        return uf.connected(p, topID);
    }

    public boolean percolates() {
        int p = topID;
        int q = bottomID;
        return uf.connected(p, q);
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(6);
        perc.open(1, 6);

        // System.out.println(perc.isOpen(1, 6));
    }
}
