import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        for(int vertex = 0; vertex < adj.length; vertex++) {
            boolean hasNegativeCycle = bellmanFordAlgo(adj, cost, vertex);
            if(hasNegativeCycle) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean bellmanFordAlgo(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s) {
        int[] distanceTable = new int[adj.length];
        for(int i = 0; i < distanceTable.length; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
        }
        distanceTable[s] = 0;
        for(int i = 0; i < adj.length - 1; i++) {
            boolean isImproving = generateDistanceTable(adj, cost, s, distanceTable);
            if( !isImproving ) {
                return false;
            }
        }
        return generateDistanceTable(adj, cost, s, distanceTable);
    }

    private static boolean generateDistanceTable(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int[] distanceTable) {
        boolean[] visited = new boolean[adj.length];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean hasDistanceTableChanged = false;
        while( !queue.isEmpty() ) {
            int current = queue.remove();
            for(int i = 0; i < adj[current].size(); i++) {
                int neighbour = adj[current].get(i);
                int weight = cost[current].get(i);
                
                if(distanceTable[neighbour] > distanceTable[current] + weight) {
                    distanceTable[neighbour] = distanceTable[current] + weight;
                    hasDistanceTableChanged = true;
                }
                if( !visited[neighbour] ) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }        
            }
        }
        //System.out.println(Arrays.toString(distanceTable));
        return hasDistanceTableChanged;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

