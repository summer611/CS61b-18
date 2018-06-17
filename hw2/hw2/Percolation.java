package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] open;
    int[][] ID;
    int size;
    WeightedQuickUnionUF wqu;
    int openSites = 0;
    int top;
    int bottom;
 //   boolean newID=false;

    public Percolation(int N) {
        size=N;
        top=size*size;
        bottom=size*size+1;
        open = new boolean[N][N];
        ID = new int[N][N];
        wqu = new WeightedQuickUnionUF(N * N+2);
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                open[i][j] = false;
                ID[i][j] = count++;
            }
        }
    }           // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if (!isOpen(row,col)) {
            open[row][col] = true;
            openSites++;
            if (row == 0) {
                wqu.union(ID[0][col], top);
            }
            if (row > 0 && open[row - 1][col]) {
                wqu.union(ID[row - 1][col], ID[row][col]);
            }
            if (row < size - 1 && open[row + 1][col]) {
                wqu.union(ID[row + 1][col], ID[row][col]);
            }
            if (col > 0 && open[row][col - 1]) {
                wqu.union(ID[row][col - 1], ID[row][col]);
            }
            if (col < size - 1 && open[row][col + 1]) {
                wqu.union(ID[row][col + 1], ID[row][col]);
            }
        }
        if(row==size-1&&isFull(row,col)){
            wqu.union(ID[row][col],bottom);
        }
    // open the site (row, col) if it is not open already
    }


    public boolean isOpen(int row, int col) {
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        return wqu.connected(ID[row][col],top);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        boolean percolates=wqu.connected(top,bottom);

/*        if (percolates&&!newID) {
            for(int i=0;i<size;i++){
                ID[size-1][i]+=(size+2);
                if(wqu.connected(ID[size-1][i]-size-2,size*size)){
                    wqu.union(ID[size-1][i],size*size);
                }
            }
            newID=true;
        }*/
        return percolates;
    }

}