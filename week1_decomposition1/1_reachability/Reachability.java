import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[adj.length];
        
        queue.add(x);
        marked[x] = true;

        while( !queue.isEmpty() ) {
            int current = queue.remove();
            if(current == y) {
                return 1;
            }
            for(int neighbour : adj[current]) {
                if( !marked[neighbour] ) {
                    queue.add(neighbour);
                    marked[neighbour] = true;
                }
            }
        }

        return 0;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

