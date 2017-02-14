//https://www.hackerrank.com/contests/world-codesprint-9/challenges/kingdom-division


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KingdomDivision {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[] leafNode = new boolean[n];
        List<Integer> [] adjList = new ArrayList[n];
        for(int i=0; i<n; i++) adjList[i] = new ArrayList<>();
        for(int a0 = 0; a0 < n-1; a0++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1 ;
            adjList[u].add(v);
            adjList[v].add(u);
            leafNode[u] = adjList[u].size() == 1;
            leafNode[v] = adjList[v].size() == 1;
        }

        int count = 0;  // count vertex with leaf nodes (ignore leaf nodes and nodes connected to just nonleaf nodes
        int upperNodes = 0; // nodes not connected to leaf but other nonleaf nodes
        for(int i=0; i<n; i++){
            if(leafNode[i]) continue;
            if(isConnectedToLeafNodes(adjList[i],leafNode)){
                count++;
            }else{
                upperNodes++;
            }
        }

//        System.out.println(count+","+upperNodes);
        long output = count;
        for(int i = count-1; i>0; i--){
            output *= i;
        }
        for(int i=upperNodes; i>0; i--){
            output *= 2;
        }
        output +=2;
        System.out.println(output % 1000000007);
    }

    private static boolean isConnectedToLeafNodes(List<Integer> adj, boolean[] leafNodes){
        for(int val : adj){
            if(leafNodes[val]) return true;
        }
        return false;
    }
}
