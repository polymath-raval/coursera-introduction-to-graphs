//https://leetcode.com/problems/reconstruct-itinerary

import java.util.*;

public class ReconstructItinerary {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[][] input = new String[][] {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        List<List<String>> list = new ArrayList<>();
        for(String[] i:input) {
            list.add(Arrays.asList(i));
        }
        System.out.println(solution.findItinerary(list));
    }

    private static class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Map<String, Integer>> graph = createGraph(tickets);
        List<String> result = new ArrayList<>();
        result.add("JFK");
        traverse(graph, "JFK", result);
        return result;
    }

    private boolean traverse(Map<String, Map<String, Integer>> graph, String current, List<String> result) {
        if(isEmpty(graph)) {
            return true;
        }
        Map<String, Integer> nexts = graph.get(current);
        if(nexts != null) {
            for (String next : nexts.keySet()) {
                int num = nexts.get(next);
                if (num > 0) {
                    nexts.put(next, num - 1);
                    result.add(next);
                    boolean done = traverse(graph, next, result);
                    if (done) {
                        return true;
                    }
                    result.remove(result.size() - 1);
                    nexts.put(next, num);
                }
            }
        }
        return false;
    }

    private boolean isEmpty(Map<String, Map<String, Integer>> graph) {
        if(graph == null || graph.isEmpty())
            return true;
        for(String from : graph.keySet()) {
            Map<String, Integer> destinations = graph.get(from);
            for(String destAirport : destinations.keySet()) {
                if(destinations.get(destAirport) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private Map<String, Map<String, Integer>> createGraph(List<List<String>> tickets) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        for(List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.putIfAbsent(from, new TreeMap<>());
            graph.get(from).putIfAbsent(to, 0);
            int num = graph.get(from).get(to);
            graph.get(from).put(to, num + 1);
        }
        return graph;
    }

}
}

