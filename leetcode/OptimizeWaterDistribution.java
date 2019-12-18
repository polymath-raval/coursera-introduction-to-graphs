// https://leetcode.com/problems/optimize-water-distribution-in-a-village/
public OptimizeWaterDistribution {
	public static void main(String[] str) {
		Solution solution = new Solution();

	}

	private static class Solution {
	    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
	        Node[] graph = createGraph(n, wells, pipes);
	        applyMinimumSpanningTree(graph);
	        int totalCost = 0;
	        for(Node house : graph) totalCost += house.waterCost;
	        return totalCost;
	    }
	    
	    private void applyMinimumSpanningTree(Node[] graph) {
	        PriorityQueue<Node> heap = initHeap(graph);
	        Set<Integer> houseCovered = new HashSet<>();
	        
	        while( !heap.isEmpty() && houseCovered.size() < graph.length) {
	            Node n = heap.remove();
	            if( !houseCovered.contains(n.houseNo) ) {
	                houseCovered.add(n.houseNo);
	                for(int i = 0; i < n.neighbours.size(); i++) {
	                    if( !houseCovered.contains(n.neighbours.get(i)) && 
	                       n.transferCost.get(i) < graph[n.neighbours.get(i)].waterCost) {
	                        graph[n.neighbours.get(i)].waterCost = n.transferCost.get(i);
	                        heap.add(graph[n.neighbours.get(i)]);
	                    }
	                }
	                //System.out.println(Arrays.toString(graph));
	                //System.out.println(houseCovered);
	                
	            }
	        }
	    }
	    
	    private PriorityQueue<Node> initHeap(Node[] graph) {
	        PriorityQueue<Node> heap = new PriorityQueue<>();
	        for(Node n : graph) {
	            heap.add(n);
	        }
	        return heap;
	    }
	    
	    private Node[] createGraph(int n, int[] wells, int[][] pipes) {
	        Node[] graph = new Node[n];
	        for(int i = 0; i < n; i++) {
	            graph[i] = new Node(i, wells[i]);
	        }
	        for(int[] pipe : pipes) {
	            graph[pipe[0] - 1].neighbours.add(pipe[1] - 1);
	            graph[pipe[1] - 1].neighbours.add(pipe[0] - 1);
	            graph[pipe[0] - 1].transferCost.add(pipe[2]);
	            graph[pipe[1] - 1].transferCost.add(pipe[2]);
	        }
	        return graph;
	    }

	    private NodeCost[] createNodeCost(int n, int[] wells) {
	    	NodeCost[] nodeCosts = new NodeCosts[n];
	    	for(int i = 0; i < n; i++) {
	    		nodeCosts[i] = new NodeCosts();
	    	}
	    }
	    
	    private static class Node implements Comparable<Node> {
	        int houseNo;
	        List<Integer> neighbours;
	        List<Integer> transferCost;
	        
	        Node(int h) {
	            this.houseNo = h;
	            this.neighbours = new ArrayList<>();
	            this.transferCost = new ArrayList<>();
	        }
	        
	        public String toString() {
	            return String.format("[HouseNo: %d, neighbours: %s, transferCost: %s]\n",
	                                this.houseNo,
	                                this.neighbours,
	                                this.transferCost);
	        }
	        
	    }
	    
	    private static class NodeCost implements Comparable<NodeCost> {
	        int houseNo;
	        int waterCost;

	        NodeCost(int h, int w) {
	        	this.houseNo = h;
	        	this.waterCost = w;
	        }

	        public String toString() {
	        	return String.format("[HouseNo: %d, WaterCost: %d]\n", this.houseNo, this.waterCost);
	        }

	        public int compareTo(NodeCost other) {
	            return Integer.compare(this.waterCost, other.waterCost);
	        }
	    
	    }
	}	
}
