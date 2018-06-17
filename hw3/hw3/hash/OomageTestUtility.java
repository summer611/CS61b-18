package hw3.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucket=new int[M];
        for(int i=0;i<bucket.length;i++){
            bucket[i]=0;
        }
        for(Oomage oo:oomages){
            int bucketNum = (oo.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum]+=1;
        }
        int oonum=oomages.size();
        for (int num:bucket){
            if(num>oonum/2.5||num<oonum/50) return false;
        }
        return true;
    }
}
