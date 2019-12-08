import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        int[] parts = new int[adj.length];
        for(int i = 0; i < parts.length; i++) {
            parts[i] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];
        q.add(0);
        parts[0] = 0;
        visited[0] = true;
        while( !q.isEmpty() ) {
            int current = q.remove();
            for(int neighbour : adj[current]) {
                if(parts[neighbour] == parts[current]) {
                    return 0;
                } else if( !visited[neighbour] ) {
                    parts[neighbour] = (parts[current] + 1) % 2;
                    visited[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

