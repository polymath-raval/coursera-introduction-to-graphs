//https://leetcode.com/problems/course-schedule-ii

import java.util.*;

public class CourseScheduleII {
	public static void main(String[] str) {
        Solution solution = new Solution();

        
        System.out.println(Arrays.toString(solution.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
    }

	private static class Solution {
	    public int[] findOrder(int numCourses, int[][] prerequisites) {
	        List<Integer>[] graph = createGraph(numCourses, prerequisites);
	        List<Integer> result = new ArrayList<>();
	        boolean[] visited = new boolean[numCourses];
	        for(int i = 0; i < numCourses; i++) {
	            if(containsLoop(i, graph)) {
	                return new int[]{};
	            }
	        }
	        for(int i = 0; i < numCourses; i++) {
	            depthFirstSearch(i, graph, result, visited);
	        }
	        int[] resultArr = new int[result.size()];
	        for(int j = 0;  j < result.size(); j++) {
	            resultArr[j] = result.get(j);
	        }
	        return resultArr;
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
	    private void depthFirstSearch(int vertex, List<Integer>[] graph, 
	                                  List<Integer> result, boolean[] visited) {
	        if( !visited[vertex] ) {
	            visited[vertex] = true;
	            for(int neighbour: graph[vertex]) {
	                depthFirstSearch(neighbour, graph, result, visited);
	            }
	            result.add(vertex);
	        }
	    }
	    
	    
	    private List<Integer>[] createGraph(int n, int[][] edges) {
	        List<Integer>[] graph = (List<Integer>[])new ArrayList[n];
	        for(int i = 0; i < n; i++) {
	            graph[i] = new ArrayList<>();
	        }
	        for(int[] edge: edges) {
	            graph[edge[0]].add(edge[1]);
	        }
	        return graph;
	    }
	}
}