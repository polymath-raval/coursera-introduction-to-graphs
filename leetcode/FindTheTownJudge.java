//https://leetcode.com/problems/find-the-town-judge/

import java.util.*;
public class FindTheTownJudge {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.findJudge(3, new int[][]{{1,3},{2,3}}));
	}
	private static class Solution {
	    public int findJudge(int N, int[][] trust) {
	        List<List<Integer>> graph = createGraph(N, trust, true);
	        List<List<Integer>> rGraph = createGraph(N, trust, false);
	        for(int i = 0; i < N; i++) {
	            if(graph.get(i).size() == 0 && rGraph.get(i).size() == N - 1)
	                return i + 1;
	        }
	        return -1;
	    }
	    
	    private List<List<Integer>> createGraph(int N, int[][] edges, boolean forward) {
	        List<List<Integer>> graph = new ArrayList<>();
	        for(int i = 0; i < N; i++) {
	            graph.add(new ArrayList<Integer>());
	        }
	        
	        for(int[] edge:edges) {
	            if(forward)
	                graph.get(edge[0] - 1).add(edge[1] - 1);
	            else
	                graph.get(edge[1] - 1).add(edge[0] - 1);
	        }
	        return graph;
	    }

	}
}
