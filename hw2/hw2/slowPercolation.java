package hw2;

        import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class slowPercolation {
    int[][] grid;
    int[][] ID;
    WeightedQuickUnionUF wqu;
    int openSites = 0;

    public slowPercolation(int N) {
        grid = new int[N][N];
        ID = new int[N][N];
        wqu = new WeightedQuickUnionUF(N * N);
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
                ID[i][j] = count++;
            }
        }
    }           // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            openSites++;
            if (row == 0) {
                grid[row][col] = 2;
                fill(row,col);
            }
            if (row > 0 && grid[row - 1][col] > 0) {
                //   wqu.union(ID[row - 1][col], ID[row][col]);
                if (isFull(row - 1, col))
                    fill(row-1,col);
            }
            if (row < grid.length - 1 && grid[row + 1][col] > 0) {
                //   wqu.union(ID[row + 1][col], ID[row][col]);
                if (isFull(row + 1, col))
                    fill(row+1,col);
            }
            if (col > 0 && grid[row][col - 1] > 0) {
                //    wqu.union(ID[row][col - 1], ID[row][col]);
                if (isFull(row, col - 1))
                    fill(row,col-1);
            }
            if (col < grid.length - 1 && grid[row][col + 1] > 0) {
                //    wqu.union(ID[row][col + 1], ID[row][col]);
                if (isFull(row, col + 1))
                    fill(row,col+1);
            }
        }
        // open the site (row, col) if it is not open already
    }

    public void fill(int row, int col) {
        if (row > 0 && grid[row - 1][col] == 1) {
            grid[row - 1][col] = 2;
            fill(row-1,col);
        }
        if (row < grid.length - 1 && grid[row + 1][col] == 1) {
            grid[row + 1][col] = 2;
            fill(row+1,col);
        }
        if (col > 0 && grid[row][col - 1] == 1) {
            grid[row][col - 1] = 2;
            fill(row,col-1);
        }
        if (col < grid.length - 1 && grid[row][col + 1] == 1) {
            grid[row][col + 1] = 2;
            fill(row,col+1);
        }
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] > 0;
    }

    public boolean isFull(int row, int col) {
        return grid[row][col] == 2;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        for (int col = 0; col < grid.length; col++) {
            if (isFull(grid.length - 1, col)) {
                return true;
            }
        }
        return false;
    }
    //public static void main(String[] args)   // use for unit testing (not required)
}