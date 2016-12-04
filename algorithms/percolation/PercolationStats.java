import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean, stddev, lo, hi;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            String msg = "n or trials is less than or equal to zero";
            throw new IllegalArgumentException(msg);
        }

        double[] openedSitesPerTrial = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            double openedSitesCounter = 0.0;
            double totalSites = n * n;

            while (!perc.percolates()) {
                int a = StdRandom.uniform(1, n+1);
                int b = StdRandom.uniform(1, n+1);

                if (!perc.isOpen(a, b))
                    openedSitesCounter++;
                perc.open(a, b);
            }
            openedSitesPerTrial[i] = openedSitesCounter / totalSites;
        }

        mean = StdStats.mean(openedSitesPerTrial);
        stddev = StdStats.stddev(openedSitesPerTrial);
        lo = mean - ((1.96*stddev)/Math.sqrt(trials));
        hi = mean + ((1.96*stddev)/Math.sqrt(trials));
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return lo;
    }

    public double confidenceHi() {
        return hi;
    }

    public static void main(String[] args) {

        String input = System.console().readLine();
        int n = Integer.parseInt(input);

        input = System.console().readLine();
        int t = Integer.parseInt(input);

        PercolationStats p = new PercolationStats(n, t);
    }
}
