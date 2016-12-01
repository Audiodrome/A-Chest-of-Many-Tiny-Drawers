import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    public static WeightedQuickUnionUF uf;
    private int width;
    private int bound;
    // private int matrix_map[];

    private void ChkBounds(int i) {
        if (i <= 0 || i > bound)
            throw new IndexOutOfBoundsException("row index i out of bounds");        
    }

    private void ConnectToOpenNeighbors(int row, int col,
                                        int neighbor_r, int neighbor_c) {
        int q = xyTo1D(neighbor_r, neighbor_c);
        // ChkBounds(q);
        if ( q < bound && grid[neighbor_r][neighbor_c] == true) {
            int p = xyTo1D(row, col);
            uf.union(p, q);
        }
    }

    private void ConnectToVirtualTop (p) {
        int q = xyTo1D(0, 0);
        uf.union(p, q);
    }

    private void ConnectToVirtualBottom(p) {
        int q = xyTo1D(0, 1);
        uf.union(p, q);
    }

    private int xyTo1D(int row, int col) {
        return width * row + col;
    }

    public Percolation(int n) {
        width = n;
        bound = n * n;
        grid = new boolean[n+1][n+1];
        uf = new WeightedQuickUnionUF(n*n);
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col);
        ChkBounds(p);

        if (grid[row][col] == false)
            grid[row][col] = true;

        if (row == 1)
            ConnectToVirtualTop(p);
        if (row == n)
            ConnectToVirtualBottom(p);

        ConnectToOpenNeighbors(row, col, row - 1, col);
        ConnectToOpenNeighbors(row, col, row + 1, col);
        ConnectToOpenNeighbors(row, col, row, col - 1);
        ConnectToOpenNeighbors(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        return (grid[row][col] == true);
    }

    public boolean isFull(int row, int col) {
        return (grid[row][col] == false);
    }

    public static void main(String[] args) {

        Percolation test = new Percolation(3);

        test.open(4,4);
        test.open(1,2);

        System.out.println(uf.connected(4,5));
        //System.out.println(test.ChkBounds(6));
        //WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(4);
        //System.out.println(test.isFull(4, 4));
        //test.open(1,1);
        //System.out.println(test.isFull(4, 5));
    }
}
