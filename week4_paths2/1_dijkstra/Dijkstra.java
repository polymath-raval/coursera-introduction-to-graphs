import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] distanceMatrix = new int[adj.length];
        for(int i = 0; i < distanceMatrix.length; i++)
            distanceMatrix[i] = Integer.MAX_VALUE;

        PriorityQueue<Node> minHeap = createQueue(adj, cost, s);
        while(!minHeap.isEmpty()) {
            Node node = minHeap.remove();
            if(distanceMatrix[node.vertex] > node.distance) {
                distanceMatrix[node.vertex] = node.distance;
                for(int i = 0; i < node.neighbours.size(); i++) {
                    int neighbourVertex = node.neighbours.get(i);
                    minHeap.add(new Node(neighbourVertex, 
                                    adj[neighbourVertex], 
                                    cost[neighbourVertex], 
                                    node.cost.get(i) + node.distance));
                }
            }
        }
        //System.out.printf("distance matrix : %s\n", Arrays.toString(distanceMatrix));
        return distanceMatrix[t] == Integer.MAX_VALUE ? -1 : distanceMatrix[t];
    
    }

    private static PriorityQueue<Node> createQueue(ArrayList<Integer>[] adj, 
        ArrayList<Integer>[] cost, int s) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        for(int i = 0; i < adj.length; i++) {
            minHeap.add(new Node(i, adj[i], cost[i], Integer.MAX_VALUE));
        }
        minHeap.add(new Node(s, adj[s], cost[s], 0));
        return minHeap;
    }

    private static class Node implements Comparable<Node>{
        public final int vertex;
        public final ArrayList<Integer> neighbours;
        public final ArrayList<Integer> cost;
        public final int distance;

        Node(int vertex, ArrayList<Integer> neighbours, ArrayList<Integer> cost, int distance) {
            this.vertex = vertex;
            this.neighbours = neighbours;
            this.cost = cost;
            this.distance = distance;
        }

        public int compareTo(Node other){
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

