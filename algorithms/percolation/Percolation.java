import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    private WeightedQuickUnionUF uf;
    private int size, bound, top_id, bottom_id;

    private void ChkBounds(int i) {
        if (i <= 0 || i > bound)
            throw new IndexOutOfBoundsException("row index i out of bounds");
    }

    private void ConnectToOpenNeighbors(int row, int col,
                                        int neighbor_r, int neighbor_c) {
        int b = size + 1;
        
        if (neighbor_c < b && neighbor_r < b) {
            if (grid[neighbor_r][neighbor_c] == true) {
                int q = xyTo1D(neighbor_r, neighbor_c);
                int p = xyTo1D(row, col);
                uf.union(p, q);
                if (row == 1) ConnectToVirtualTop(p);
                if (row == size && uf.connected(p, top_id) == true)
                    ConnectToVirtualBottom(p);
            }
        }
    }

    private void ConnectToVirtualTop (int p) {
        uf.union(p, top_id);
    }

    private void ConnectToVirtualBottom(int p) {
        uf.union(p, bottom_id);
    }

    private int xyTo1D(int row, int col) {
        return size * row + col;
    }

    public Percolation(int n) {
        size = n;
        bound = (n + 1) * (n + 1);
        grid = new boolean[n+1][n+1];
        uf = new WeightedQuickUnionUF(bound + 1);
        top_id = 0;
        bottom_id = bound;
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col); ChkBounds(p);

        if (grid[row][col] == false) grid[row][col] = true;

        ConnectToOpenNeighbors(row, col, row - 1, col);
        ConnectToOpenNeighbors(row, col, row + 1, col);
        ConnectToOpenNeighbors(row, col, row, col - 1);
        ConnectToOpenNeighbors(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        int p = xyTo1D(row, col); ChkBounds(p);
        return uf.connected(p, top_id) == false && grid[row][col] == true;
    }

    public boolean isFull(int row, int col) {
        int p = xyTo1D(row, col);
        return uf.connected(p, top_id) == true;
    }

    public boolean percolates() {
        int p = top_id;
        int q = bottom_id;
        return uf.connected(p, q) == true;
    }
}
