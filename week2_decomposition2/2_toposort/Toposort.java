import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        boolean used[] = new boolean[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        for(int s = 0; s < adj.length; s++) {
            dfs(adj, used, order, s);
        }
        //System.out.println(order);

        for(int i = 0; i < order.size() / 2; i++) {
            int temp = order.get(i);
            order.set(i, order.get(order.size() - 1 - i));
            order.set(order.size() - 1 - i, temp);
        }
        //System.out.println(order);

        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] used, ArrayList<Integer> order, int s) {
        if( !used[s] ) {
            for(int neighbour : adj[s]) {
                dfs(adj, used, order, neighbour);
            }
            used[s] = true;
            order.add(s);
            //System.out.println(order);
        }
    }

    private static ArrayList<Integer> toposort1(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<>();

        // Compute the indegree matrix
        int[] inDegree = new int[adj.length];
        for(int vertex = 0; vertex < adj.length; vertex++) {
            for(int neighbour : adj[vertex]) {
                inDegree[neighbour]++;
            }
        }

        // Get all elements where in the the indegree is zero
        Queue<Integer> queue = new LinkedList<>();
        for(int vertex = 0; vertex < adj.length; vertex++) {
            if(inDegree[vertex] == 0) {
                queue.add(vertex);
            }
        }

        while( !queue.isEmpty() ) {
            int vertex = queue.remove();
            order.add(vertex);
            for(int neighbour : adj[vertex]) {
                inDegree[neighbour]--;
                if(inDegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        return order;
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

