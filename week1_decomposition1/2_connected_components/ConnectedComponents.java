import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int captionOfComponent = 0;
        boolean[] visited = new boolean[adj.length];

        for(int vertex = 0; vertex < adj.length; vertex++) {
            if( !visited[vertex] ) {
                captionOfComponent++;
                visited[vertex] = true;
                
                /** 
                 * Breadth first search on the node
                 * All the strongly connected components of the node will
                 * marked as belonging to that component and
                 * then they will be ignored
                 */
                Queue<Integer> queue = new LinkedList<>();
                queue.add(vertex);
                visited[vertex] = true;
                while( !queue.isEmpty() ) {
                    int current = queue.remove();
                    for( int neighbour : adj[current] ) {
                        if (!visited[neighbour] ) {
                            queue.add(neighbour);
                            visited[neighbour] = true;
                        }
                    }
                }
            }
        }

        return captionOfComponent;
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
        System.out.println(numberOfComponents(adj));
    }
}

