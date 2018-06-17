package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 0.5;
        int N = 200;
        int M = 50;

        HashTableDrawingUtility.setScale(scale);
       /* List<Oomage> oomies = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            oomies.add(ComplexOomage.randomComplexOomage());
        }*/
        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer>[] deadlyParams = new ArrayList[N];
        for (int j=0;j<deadlyParams.length;j++) {
            deadlyParams[j] = new ArrayList(5);
            for (int i = 0; i < 4; i += 1) {
                deadlyParams[j] .add(StdRandom.uniform(0, 255));
            }
            deadlyParams[j].add(2);
        }
        for (List param : deadlyParams) {
            deadlyList.add(new ComplexOomage(param));
        }

        visualize(deadlyList, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
