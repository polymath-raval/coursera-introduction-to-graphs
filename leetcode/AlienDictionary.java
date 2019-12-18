// https://leetcode.com/problems/alien-dictionary/
import java.util.*;
public class AlienDictionary {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.alienOrder(new String[] {"wrt","wrf","er","ett","rftt"}));
    }


    private static class Solution {
    //["wrt","wrf","er", "ett","rftt"]
    //["z","x","z"]
    public String alienOrder(String[] words) {
        List<char[]> edges = createEdges(words);
        Map<Character, Set<Character>> graph = createGraph(words, edges);
        List<Character> result = topologicalSort(graph);
        //System.out.println(graph);
        String str = "";
        for(int i = 0; i < result.size(); i++)
            str += result.get(i);
        return str;
    }
    
    private List<Character> topologicalSort(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = computeInDegree(graph);
        //System.out.println(indegree);
        Queue<Character> q = setupQueue(indegree);
        //System.out.println(q);


        List<Character> result = new ArrayList<>();
        while( !q.isEmpty() ) {
            Character ch0 = q.remove();
            result.add(ch0);
            if(graph.containsKey(ch0)) {
                for(Character ch1 : graph.get(ch0)) {
                    int d = indegree.get(ch1) - 1;
                    indegree.put(ch1, d);
                    if(d == 0)
                        q.add(ch1);
                }
            }
        }
        
        if(result.size() != indegree.size())
            return new ArrayList<>();
        return result;
    }
    
    private Queue<Character> setupQueue(Map<Character, Integer> indegree) {
        Queue<Character> q = new LinkedList<>();
        for(Character ch: indegree.keySet()) {
            if(indegree.get(ch) == 0) {
                q.add(ch);
            }
        }
        return q;
    }
    
    private Map<Character, Integer> computeInDegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        for(Character ch0: graph.keySet()) {
            indegree.putIfAbsent(ch0, 0);
            for(Character ch1: graph.get(ch0)) {
                indegree.putIfAbsent(ch1, 0);
                indegree.put(ch1, indegree.get(ch1) + 1);
            }
        }
        return indegree;
    }
    
    private Map<Character, Set<Character>> createGraph(String[] words, List<char[]> edges) {
        Map<Character, Set<Character>> graph = 
            new HashMap<Character, Set<Character>>();
        
        for(String word : words) {
            for(char ch : word.toCharArray()) {
                graph.put(ch, new HashSet<>());
            }
        }
        
        for(char[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }
    
    private List<char[]> createEdges(String[] words) {
        List<char[]> edges = new ArrayList<>();
        for(int i = 1; i < words.length; i++) {
            String word0 = words[i - 1];
            String word1 = words[i];
            int len = Integer.min(word0.length(), word1.length());
            for(int j = 0; j < len; j++) {
                char a = word0.charAt(j);
                char b = word1.charAt(j);
                if(a != b) {
                    char[] edge = new char[]{a, b};
                    edges.add(edge);
                    break;
                }
            }
            
        }
        return edges;
    }
}
}


