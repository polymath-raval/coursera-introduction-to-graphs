import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, 
        ArrayList<Integer>[] cost, int s, 
        long[] distance, int[] reachable, int[] shortest) {
        distance[s] = 0;
        boolean[] improving = 
            bellmanFordAlgo(adj, cost, distance, s);
        for(int i = 0; i < shortest.length; i++) {
            shortest[i] = improving[i] == true ? 0 : 1;
        }
        for(int i = 0; i < reachable.length; i++) {
            reachable[i] = distance[i] == Long.MAX_VALUE ? 0 : 1;
        }
    }

    private static boolean[] bellmanFordAlgo(ArrayList<Integer>[] adj, 
        ArrayList<Integer>[] cost, long[] distance, int s) {
        boolean[] improving = new boolean[adj.length];

        for(int i = 0; i < adj.length - 1; i++) {
            boolean[] isImproving = 
                generateDistanceTable(adj, cost, s, distance);
        }
        return generateDistanceTable(adj, cost, s, distance);
    }

    private static boolean[] generateDistanceTable(ArrayList<Integer>[] adj, 
        ArrayList<Integer>[] cost, int s, long[] distance) {
        boolean[] improving = new boolean[adj.length];
        for(int i = 0; i < improving.length; i++) {
            improving[i] = false;
        }

        boolean[] visited = new boolean[adj.length];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while( !queue.isEmpty() ) {
            int current = queue.remove();
            for(int i = 0; i < adj[current].size(); i++) {
                int neighbour = adj[current].get(i);
                int weight = cost[current].get(i);
                
                if(distance[neighbour] > distance[current] + weight) {
                    distance[neighbour] = distance[current] + weight;
                    improving[neighbour] = true;
                    for(int j = 0; j < adj[neighbour].size(); j++) {
                        int neighbourOfNeighbour = adj[neighbour].get(j);
                        improving[neighbourOfNeighbour] = true;
                    }
                }
                if( !visited[neighbour] ) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        return improving;
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

