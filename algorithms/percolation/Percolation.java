import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    private WeightedQuickUnionUF wqu;
    private int width;
    private int bound;
    // private int matrix_map[];

    private void ChkBounds(int i) {
        if (i <= 0 || i > bound)
            throw new IndexOutOfBoundsException("row index i out of bounds");        
    }

    private void ChkNeighbors(int row, int col) {
        int p = xyTo1D(row, col);
        if ( p < bound && grid[])
    }

    private int xyTo1D(int row, int col) {
        return width * row + col;
    }

    public Percolation(int n) {
        width = n;
        bound = n * n;
        grid = new boolean[n+1][n+1];
        wqu = new WeightedQuickUnionUF(n*n);
        // matrix_map = new int[n*n];
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col);
        ChkBounds(p);

        if (grid[row][col] == false)
            grid[row][col] = true;

        neighbor = xyTo1D(row - 1, col);
        ChkNeighbors(p, neighbor)
        p = xyTo1D(row - 1, col);

        p = xyTo1D(row + 1, col);

        p = xyTo1D(row, col - 1);

        p = xyTo1D(row, col + 1);

    }

    public boolean isOpen(int row, int col) {
        return (grid[row][col] == true);
    }

    public boolean isFull(int row, int col) {
        return (grid[row][col] == false);
    }

    public static void main(String[] args) {

        Percolation test = new Percolation(4);

        //System.out.println(test.ChkBounds(6));
        //WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(4);
        //System.out.println(test.isFull(4, 4));
        //test.open(1,1);
        //System.out.println(test.isFull(4, 5));
    }
}
