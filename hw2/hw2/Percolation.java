package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int n;
    int[][] grid;
    int upNode, downNode;
    int numOpen;
    WeightedQuickUnionUF UF;

    public Percolation(int N) {
        if(N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        n = N;
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        upNode = n*n + 1;
        downNode = n*n;
        UF = new WeightedQuickUnionUF(n*n+2);
        numOpen = 0;
    }

    private int xyTo1D(int row, int col) {
        return row*n+col;
    }

    private void connectNeighbor(int row, int col) {
        if(row == 0) {
            UF.union(upNode, xyTo1D(row, col));
        }
        if(row == n-1) {
            UF.union(downNode, xyTo1D(row, col));
        }
        if(row - 1 >= 0 && isOpen(row-1, col)) {
            UF.union(xyTo1D(row, col), xyTo1D(row-1, col));
        }
        if(row + 1 <= n-1 && isOpen(row+1, col)) {
            UF.union(xyTo1D(row, col), xyTo1D(row+1, col));
        }
        if(col - 1 >= 0 && isOpen(row, col-1)) {
            UF.union(xyTo1D(row, col), xyTo1D(row, col-1));
        }
        if(col + 1 <= n-1 && isOpen(row, col+1)) {
            UF.union(xyTo1D(row, col), xyTo1D(row, col+1));
        }
    }

    public void open(int row, int col) {
        if(row < 0 || row > n-1 || col < 0 || col > n-1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(!isOpen(row, col)) {
            grid[row][col] = 1;
            numOpen += 1;
            connectNeighbor(row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        if(row < 0 || row > n-1 || col < 0 || col > n-1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if(row < 0 || row > n-1 || col < 0 || col > n-1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return UF.connected(upNode, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        return UF.connected(upNode, downNode);
    }
}
