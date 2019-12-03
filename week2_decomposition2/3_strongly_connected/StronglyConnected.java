import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.util.LinkedList;


public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        ArrayList<Integer>[] rAdj = reverse(adj);
        boolean[] visited = new boolean[rAdj.length];
        int[] pre = new int[rAdj.length];
        int[] post = new int[rAdj.length];
        int val = 0;
        for(int vertex = 0; vertex < rAdj.length; vertex++) {
            val = dfs(rAdj, visited, vertex, pre, post, val);
        }
        //System.out.printf("Pre --> %s\n", Arrays.toString(pre));
        //System.out.printf("Post -> %s\n", Arrays.toString(post));
        
        //write your code here
        return findStronglyConnectedComponents(adj, post);
    }

    private static int dfs(ArrayList<Integer>[] graph, boolean[] visited, int vertex, int[] pre, int[] post, int val) {
        if(visited[vertex]) {
            return val;
        } else {
            pre[vertex] = ++val;
            visited[vertex] = true;
            for(int neighbour : graph[vertex]) {
                val = dfs(graph, visited, neighbour, pre, post, val);
            }
            post[vertex] = ++val;
            return val;
        }
    }

    private static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int vertex) {
        if(visited[vertex]) {
            return;
        } else {
            visited[vertex] = true;
            for(int neighbour : graph[vertex]) {
                if( ! visited[neighbour] ) {
                    dfs(graph, visited, neighbour);   
                }
            }
        }
    }

    private static int findStronglyConnectedComponents(ArrayList<Integer>[] graph, int[] post) {
        boolean[] visited = new boolean[graph.length];
        int caption = 0;
        PriorityQueue<Integer[]> maxHeap = maxHeap(post);
        while( !maxHeap.isEmpty() ) {
            int vertex = maxHeap.remove()[1];
            if( !visited[vertex] ) {
                caption++;
                dfs(graph, visited, vertex);
            }
        }
        return caption;
    }

    private static PriorityQueue<Integer[]> maxHeap(int[] post) {
        PriorityQueue<Integer[]> maxHeap = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        for(int i = 0; i < post.length; i++) {
            maxHeap.add(new Integer[]{post[i], i});
        }
        return maxHeap;
    }
 

    private static void print(ArrayList<Integer>[] graph) {
        for(int i = 0; i < graph.length; i++) {
            System.out.printf("%d ==> %s\n", i, graph[i]);
        }
    }

    private static ArrayList<Integer>[] reverse(ArrayList<Integer>[] adj) {
        ArrayList<Integer>[] rAdj = (ArrayList<Integer>[])new ArrayList[adj.length];
        for(int i = 0; i < rAdj.length; i++) {
            rAdj[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < adj.length; i++) {
            for(int neighbour : adj[i]) {
                rAdj[neighbour].add(i);
            }
        }
        return rAdj;
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

