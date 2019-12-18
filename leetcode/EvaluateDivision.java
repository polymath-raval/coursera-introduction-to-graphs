//https://leetcode.com/problems/evaluate-division///

import java.util.*;
public class EvaluateDivision {
	public static void main(String[] str) {
		Solution solution = new Solution();
		String[][] equations = {{"a", "b"}, {"b", "c"} };
		double[] values = {2.0, 3.0};;
		String[][] queries = { {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"} };
		List<List<String>> eqs = new ArrayList<>();
		List<List<String>> qs = new ArrayList<>();
		for(String[] e : equations) eqs.add(Arrays.asList(e));
		for(String[] q : queries) qs.add(Arrays.asList(q));

		System.out.println(Arrays.toString(solution.calcEquation(eqs, values, qs)));
	}

	private static class Solution {
	    public double[] calcEquation(List<List<String>> equations, double[] values, 
	                                 List<List<String>> queries) {
	        Map<String, Node> graph = createGraph(equations, values);
	        double[] result = new double[queries.size()];
	        
	        for(int i = 0; i < queries.size(); i++) {
	            List<String> query = queries.get(i);
	            String current = query.get(0);
	            String target = query.get(1);
	            List<String> route = new ArrayList<>();
	            route.add(current);
	            Set<String> visited = new HashSet<>();
	            visited.add(current);
	            
	            boolean routeFound = dfs(visited, graph, route, current, target);
	            result[i] = 1;
	            if( !routeFound ) {
	                result[i] = -1;
	            } else {
	                for(int k = 1; k < route.size(); k++) {
	                    String s = route.get(k - 1);
	                    String t = route.get(k);
	                    for(int j = 0; j < graph.get(s).neighbours.size(); j++) {
	                        if(graph.get(s).neighbours.get(j) == t) {
	                            result[i] *= graph.get(s).weights.get(j);
	                            break;
	                        }
	                    }
	                }
	            }
	            //System.out.printf("routeFound: %b, route: %s\n", routeFound, route);
	        }
	        return result;
	    }
	    
	    public boolean dfs(Set<String> visited,
	                      Map<String, Node> graph, 
	                      List<String> result,
	                      String current, 
	                      String target) {
	        if(graph.containsKey(current) && 
	           graph.containsKey(target) &&
	           current.equals(target)) {
	            return true;
	        }
	        
	        if(graph.containsKey(current)) {
	            List<String> neighbours = graph.get(current).neighbours;

	            for(int i = 0; i < neighbours.size(); i++) {
	                String neighbour = neighbours.get(i);
	                if( !visited.contains(neighbour) ) {
	                    result.add(neighbour);
	                    visited.add(neighbour);
	                    if(dfs(visited, graph, result, neighbour, target)) {
	                        return true;
	                    }
	                    result.remove(result.size() - 1);  
	                }
	                  
	            }
	        }
	        return false;
	    } 
	    
	    private Map<String, Node> createGraph(List<List<String>> equations, double[] values) {
	        Map<String, Node> graph = new HashMap<>();
	        for(int i = 0; i < equations.size(); i++) {
	            graph.putIfAbsent(equations.get(i).get(0), new Node(equations.get(i).get(0)));
	            graph.putIfAbsent(equations.get(i).get(1), new Node(equations.get(i).get(1)));
	            graph.get(equations.get(i).get(0)).neighbours.add(equations.get(i).get(1));
	            graph.get(equations.get(i).get(0)).weights.add(values[i]);
	            graph.get(equations.get(i).get(1)).neighbours.add(equations.get(i).get(0));
	            graph.get(equations.get(i).get(1)).weights.add(1 / values[i]);
	        }
	        return graph;
	    }
	    
	    private static class Node {
	        public final String label;
	        public final List<String> neighbours;
	        public final List<Double> weights;
	        
	        Node(String l) {
	            this.label = l;
	            this.neighbours = new ArrayList<>();
	            this.weights = new ArrayList<>();
	        }
	        
	        public String toString() {
	            return String.format("{l: %s, n: %s, w: %s}\n",
	                                 this.label, this.neighbours, this.weights);
	        }
	    }
	}
}