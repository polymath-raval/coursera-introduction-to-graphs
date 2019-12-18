//https://leetcode.com/problems/course-schedule

import java.util.*;

public class CourseSchedule{
    public static void main(String[] str) {
        Solution solution = new Solution();
        System.out.println(solution.canFinish(2, new int[][] {{1,0},{0,1}}));
    }
    
    private static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = createGraph(numCourses, prerequisites);
            for(int i = 0; i < numCourses; i++) {
                if(containsLoop(i, graph)) {
                   return false; 
                }
            }
            return true;
        }
        
        private boolean containsLoop(int vertex, List<Integer>[] graph) {
            boolean[] visited = new boolean[graph.length];
            Queue<Integer> q = new LinkedList<>();
            q.add(vertex);
            visited[vertex] = true;
            while( !q.isEmpty() ) {
                int current = q.remove();
                for(int neighbour: graph[current]) {
                    if(neighbour == vertex)
                        return true;
                    if(!visited[neighbour]) {
                        visited[neighbour] = true;
                        q.add(neighbour);
                    }
                }
            }
            return false;
        }
        
        private List<Integer>[] createGraph(int n, int[][] edges) {
            List<Integer>[] graph = (List<Integer>[])new ArrayList[n];
            for(int i = 0; i < n;i++) {
                graph[i] = new ArrayList<>();
            }
            for(int[] edge:edges) {
                graph[edge[0]].add(edge[1]);
            }
            return graph;
        }
    }
}

