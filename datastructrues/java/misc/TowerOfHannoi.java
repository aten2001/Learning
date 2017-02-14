import java.util.*;

public class TowerOfHannoi {

    static class Disc {
        int id;
        boolean ignore;

        public Disc(int i){
            this.id = i;
            this.ignore = false;
        }
    }

    public static void main(String[] args){
        int n = 4; //no of disk
        byte [] pegs = new byte[3];
        byte bitMap = 0;
        
        bitMap |= 1 << 1; //disc 1
        bitMap |= 1 << 2; //disc 2
        bitMap |= 1 << 3; //disc 3
        bitMap |= 1 << 4; //disc 4
        pegs[0] = bitMap;
        pegs[1] = 0;
        pegs[2] = 0
        int moves = computeMoves(bitMap,4,0,2);
    }


    private static int computeMoves(byte[] pegs,int disc, int src, int dest){
        if (disc = 1) return 1;
        int other = getOtherPeg(src,dest); 
        int findSrc = getSrcPeg(disc-1);
        int output   computeMoves(pegs,disc-1,findSrc,other);
    }

    private static int getOtherPeg(int v, int w){
        if(v == 0 && w == 1) return 2;
        if(w == 0 && v == 1) return 2;
        if(v == 1 && w == 2) return 0;
        if(w == 1 && v == 2) return 0;
        if(v == 2 && w == 0) return 1;
        if(w == 2 && v == 0) return 1;
        throw new IllegalArgumentException();
    }




}
