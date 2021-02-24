package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int n;
    int t;
    double[] exp;
    PercolationFactory pf;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        n = N;
        t = T;
        exp = new double[T];
        this.pf = pf;
    }

    public double mean() {
        int random;
        int row, col;
        Percolation pc;
        for(int i = 0; i < t; i++) {
            pc = pf.make(n);
            while(!pc.percolates()) {
                random = StdRandom.uniform(0,n*n);
                row = random % n;
                col = random - row * n;
                if(!pc.isOpen(row, col)) {
                    pc.open(row, col);
                }
            }
            exp[i] = (double)(pc.numOpen);
        }

        return StdStats.mean(exp);
    }

    public double stddev() {
        return StdStats.stddev(exp);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev()/Math.sqrt(t);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev()/Math.sqrt(t);
    }

}
