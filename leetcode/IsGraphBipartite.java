// https://leetcode.com/problems/is-graph-bipartite/

import java.util.*;

public class IsGraphBipartite {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isBipartite(new int[][]{{1,3}, {0,2}, {1,3}, {0,2}}));
    }

    private static class Solution {
        public boolean isBipartite(int[][] graph) {
            for(int vertex = 0; vertex < graph.length; vertex++) {
                if( !isBipartite(graph, vertex) ) {
                    return false;
                }
            }
            return true;
        }
        
        private boolean isBipartite(int[][] graph, int node) {
            boolean[] visited = new boolean[graph.length];
            int[] partite = new int[graph.length];
            
            Queue<Integer> q = new LinkedList<>();
            q.add(node);
            visited[node] = true;
            partite[node] = 1;
            
            while( !q.isEmpty() ) {
                int current = q.remove();
                for(int neighbour : graph[current]) {
                    if(partite[neighbour] == partite[current])
                        return false;
                    if( !visited[neighbour] ) {
                        visited[neighbour] = true;
                        partite[neighbour] = ((partite[current] + 1) % 2) == 0 ? 2 : 1;
                        q.add(neighbour);
                    }
                }
            }
            return true;
        }
    }
}
