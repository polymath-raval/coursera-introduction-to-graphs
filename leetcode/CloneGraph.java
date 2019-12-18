// https://leetcode.com/problems/clone-graph
import java.util.*;

public class CloneGraph {
    
    public static void main(String[] str) {
        Solution solution = new Solution();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.add(n2);
        n2.neighbors.add(n3);
        n3.neighbors.add(n4);
        
        solution.cloneGraph(n1);
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, List<Node> _n) {
            val = _val;
            neighbors = _n;
        }
    }

    private static class Solution {
        public Node cloneGraph(Node node) {
            Queue<Node> q = new LinkedList<Node>();
            Set<Integer> visited = new HashSet<>();
            q.add(node);
            visited.add(node.val);
            Map<Integer, Node> map = new HashMap<>();
            while( !q.isEmpty() ) {
                Node current = q.remove();
                map.putIfAbsent(current.val, new Node(current.val, new ArrayList<>()));
                for(Node neighbour : current.neighbors) {
                    map.putIfAbsent(neighbour.val, new Node(neighbour.val, new ArrayList<>()));
                    map.get(current.val).neighbors.add(map.get(neighbour.val));
                    if( !visited.contains(neighbour.val) ) {
                        visited.add(neighbour.val);
                        q.add(neighbour);
                    }
                }
            }
            return map.get(node.val);
        }
    }
}
