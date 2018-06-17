package hw2;
import  edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    int trial;
    int[] openSites;

    public PercolationStats(int N, int T) {
        trial = T;
        openSites = new int[T];
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException("input should be non-negative");
        }
        for (int i = 0; i < T; i++) {
            Percolation grid = new Percolation(N);
            while (!grid.percolates()) {
                int rdmRow = StdRandom.uniform(N);
                int rdmCol = StdRandom.uniform(N);
                try {
                    grid.open(rdmRow, rdmCol);
                } catch (Exception e) {
                    continue;
                }
            }
            openSites[i] = grid.openSites;
        }

    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(openSites);
    }                                           // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(openSites);
    }                                         // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(trial);
    }                                  // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(trial);
    }                                 // high endpoint of 95% confidence interval

    public static void main(String[] Args) {
        /*Stopwatch timer1=new Stopwatch();
        PercolationStats test = new PercolationStats(100, 100);
        System.out.println("Mean: " + test.mean() + " Sd: " + test.stddev() + " 95% CI: " + test.confidenceLow() + "," + test.confidenceHigh());
        System.out.println(timer1.elapsedTime());*/
        Stopwatch timer2=new Stopwatch();
        PercolationStats test2 = new PercolationStats(10, 100);
        System.out.println("Mean: " + test2.mean() + " Sd: " + test2.stddev() + " 95% CI: " + test2.confidenceLow() + "," + test2.confidenceHigh());
        System.out.println(timer2.elapsedTime());
    }
}


