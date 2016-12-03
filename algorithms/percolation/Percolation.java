import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][], top_site, bottom_site;
    private WeightedQuickUnionUF uf;
    private int size, bound, top_id, bottom_id;

    private void ChkBounds(int i) {
        if (i <= 0 || i > bound)
            throw new IndexOutOfBoundsException("row index i out of bounds");        
    }

    private void ConnectToOpenNeighbors(int row, int col,
                                        int neighbor_r, int neighbor_c) {

        int b = size + 1;

        // System.out.println(b);
        // System.out.println(neighbor_r + "     " + neighbor_c);
        // ChkBounds(q);
        if (neighbor_c < b && neighbor_r < b) {
            if (grid[neighbor_r][neighbor_c] == true) {
                int q = xyTo1D(neighbor_r, neighbor_c);
                int p = xyTo1D(row, col);
                uf.union(p, q);
            }
        }
    }

    private void ConnectToVirtualTop (int p) {
        // int q = xyTo1D(0, 0);
        int q = top_id;
        uf.union(p, q);
    }

    private void ConnectToVirtualBottom(int p) {
        //int q = xyTo1D(0, 1);
        int q = bottom_id;
        uf.union(p, q);
    }

    private int xyTo1D(int row, int col) {
        return size * row + col;
    }

    public Percolation(int n) {

        size = n;
        bound = (n + 1) * (n + 1);
        //System.out.println(bound);
        grid = new boolean[n+1][n+1];
        uf = new WeightedQuickUnionUF(bound + 1);

        // set virtual top and bottom site to open
        // grid[0][0] = grid[0][1] = true;
        // top_site = bottom_site = true;
 
        top_id = 0;
        bottom_id = bound; 
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col);
        ChkBounds(p);

        if (grid[row][col] == false)
            grid[row][col] = true;

        if (row == 1)
            ConnectToVirtualTop(p);
        if (row == size)
            ConnectToVirtualBottom(p);

        ConnectToOpenNeighbors(row, col, row - 1, col);
        ConnectToOpenNeighbors(row, col, row + 1, col);
        ConnectToOpenNeighbors(row, col, row, col - 1);
        ConnectToOpenNeighbors(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        int p = xyTo1D(row, col);
        // int q = xyTo1D(0, 0);
        int q = top_id;

        return uf.connected(p, q) == false;
    }

    public boolean isFull(int row, int col) {
        int p = xyTo1D(row, col);
        // int q = xyTo1D(0, 0);
        int q = top_id;

        return uf.connected(p, q) == true;
    }

    public boolean percolates() {
        // int p = xyTo1D(0, 0);
        // int q = xyTo1D(0, 1);
        int p = top_id;
        int q = bottom_id;
        return uf.connected(p, q) == true;
    }

    public static void main(String[] args) {

        Percolation test = new Percolation(1);

        test.open(1,1);
        // test.open(2,3);
        // test.open(3,3);
        // test.open(3,1);
        // test.open(2,1);
        // test.open(1,1);

        // System.out.println(test.isFull(1,1));
        // System.out.println(test.isFull(1,2));
        System.out.println(test.percolates());

        // for (int i = 0; i < 800; i++) {
        //     int a = StdRandom.uniform(1, 20);
        //     int b = StdRandom.uniform(1, 20);
        //     test.open(a, b);
        //     System.out.println("I'm Here!");
        //     if (test.percolates() == true) {
        //         System.out.println("It percolates!");
        //         return;
        //     }
        // }
    }
}
