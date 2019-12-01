import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        for( int vertex = 0; vertex < adj.length; vertex++ ) {
            boolean[] visited = new boolean[adj.length];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(vertex);
            visited[vertex] = true;
            while( !queue.isEmpty() ) {
                int current = queue.remove();
                for( int neighbour : adj[current] ) {
                    if(neighbour == vertex) {
                        return 1;
                    }
                    if( !visited[neighbour] ) {
                        visited[neighbour] = true;
                        queue.add(neighbour);
                    }
                }
            }
        }
        return 0;
    }

    private static int acyclic1(ArrayList<Integer>[] adj) {
        int[] indegree = new int[adj.length];

        // Compute indegrees
        for(int vertex = 0; vertex < adj.length; vertex++) {
            for(int neighbour: adj[vertex]) {
                indegree[neighbour]++;
            }
        }

        // Findout vertex with indegree of zero
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> order = new ArrayList<>();
        for(int vertex = 0; vertex < adj.length; vertex++) {
            if(indegree[vertex] == 0)
                queue.add(vertex);
        }
        while( !queue.isEmpty() ) {
            int current = queue.remove();
            order.add(current);
            for(int neighbour : adj[current]) {
                indegree[neighbour]--;
                if(indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        if(order.size() == adj.length)
            return 0;
        else
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
        }
        System.out.println(acyclic1(adj));
    }
}

