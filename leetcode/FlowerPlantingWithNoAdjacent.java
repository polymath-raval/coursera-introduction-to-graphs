//https://leetcode.com/problems/flower-planting-with-no-adjacent/
import java.util.*;
public class FlowerPlantingWithNoAdjacent {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(Arrays.toString(solution.gardenNoAdj(3, new int[][]{{1,2},{2,3},{3,1}})));
	}
	
	private static class Solution {
	    public int[] gardenNoAdj(int N, int[][] paths) {
	        List<List<Integer>> graph = createGraph(N, paths);
	        int[] result = new int[N];
	        for(int i = 0; i < N; i++) {
	            List<Integer> neighbours = graph.get(i);
	            boolean[] occupied = new boolean[5];
	            occupied[0] = true;
	            for(int neighbour: neighbours) {
	                occupied[result[neighbour]] = true;
	            }
	            
	            for(int j = 0; j < occupied.length; j++) {
	                if(!occupied[j]) {
	                    result[i] = j;
	                    break;
	                }
	            }
	        }
	        return result;
	    }
	    
	    private static List<List<Integer>> createGraph(int N, int[][] paths) {
	        List<List<Integer>> graph = new ArrayList<>();
	        for(int i = 0; i < N; i++) {
	            graph.add(new ArrayList<>());
	        }
	        for(int[] path:paths) {
	            graph.get(path[0] - 1).add(path[1] - 1);
	            graph.get(path[1] - 1).add(path[0] - 1);
	        }
	        return graph;
	    }
	}
}