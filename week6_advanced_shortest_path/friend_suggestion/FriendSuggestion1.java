import java.util.*;

public class FriendSuggestion {
    private static class Node implements Comparable<Node>{
        int previousVertex;
        int vertex;
        int distance;

        public Node(int p, int v, int d) {
            this.previousVertex = p;
            this.vertex = v;
            this.distance = d;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }

        public String toString() {
            return String.format("{p: [%d], v: [%d], d: [%d]}", this.previousVertex, this.vertex, this. distance);
        }
    }

    private static class Graph {
        int numberOfNodes;
        ArrayList<Integer>[] adj;
        ArrayList<Integer>[] cost;

        public Graph(int n){
            this.numberOfNodes = n;
            this.adj = (ArrayList<Integer>[])new ArrayList[n];
            this.cost = (ArrayList<Integer>[])new ArrayList[n];
            for(int i = 0; i < n; i++) {
                this.adj[i] = new ArrayList<Integer>();
                this.cost[i] = new ArrayList<Integer>();
            }          
        }
    }

    private static class BiDirectionalDijkstra {
        Graph graph;
        Graph rGraph;
        int source;
        int target;
        Node[] visited;
        Node[] rVisited;
        PriorityQueue<Node> queue;
        PriorityQueue<Node> rQueue;


        BiDirectionalDijkstra(Graph g, Graph rG, int s, int t) {
            this.graph = g;
            this.rGraph = rG;
            this.source = s;
            this.target = t;
            this.visited = new Node[this.graph.numberOfNodes];
            this.rVisited = new Node[this.rGraph.numberOfNodes];
            this.queue = new PriorityQueue<>();
            this.rQueue = new PriorityQueue<>();
            this.queue.add(new Node(s, s, 0));
            this.rQueue.add(new Node(t, t, 0));
        }

        private static Node relaxNode(PriorityQueue<Node> q, Node[] v, Graph g) {
            Node node = q.remove();
            int vertex = node.vertex;
            v[vertex] = node;
            for(int i = 0; i < g.adj[vertex].size(); i++) {
                int neighbourVertex = g.adj[vertex].get(i);
                int neighbourCost = g.cost[vertex].get(i);
                if(v[neighbourVertex] == null 
                    || v[neighbourVertex].distance > node.distance + neighbourCost) {
                    q.add(new Node(vertex, neighbourVertex, node.distance + neighbourCost));
                }
            }
            return node;
        }

        int optimalPath(int intersectionVertex) {
            if(intersectionVertex == -1){
                return -1;
            }

            int edgeVertex = intersectionVertex;
            int rEdgeVertex = intersectionVertex;
            int minimumCost = visited[edgeVertex].distance + rVisited[rEdgeVertex].distance;
            //System.out.printf("{e: %d, r: %d, m: %d}\n", edgeVertex, rEdgeVertex, minimumCost);
            for(int vertex = 0; vertex < visited.length; vertex++) {
                if(visited[vertex] != null) {
                    for(int j = 0; j < graph.adj[vertex].size(); j++) {
                        int neighbourVertex = graph.adj[vertex].get(j);
                        if(rVisited[neighbourVertex] != null) {
                            int proposedCost = visited[vertex].distance 
                                                + graph.cost[vertex].get(j)
                                                + rVisited[neighbourVertex].distance;

                            //System.out.printf("{e: %d, r: %d, m: %d}\n", 
                                //vertex, neighbourVertex, proposedCost);

                            if(minimumCost > proposedCost) {
                                edgeVertex = vertex;
                                rEdgeVertex = neighbourVertex;
                                minimumCost = visited[vertex].distance + rVisited[neighbourVertex].distance;
                            }
                        }
                    }
                }
            }
            return minimumCost;
        }

        int traverse() {
            
            int intersectionPoint = -1;
            //System.out.printf("visited: %s\n rVisited: %s\n\n", 
                //Arrays.toString(visited), Arrays.toString(rVisited));            
                
            while(!this.queue.isEmpty() && !this.rQueue.isEmpty()) {
                    
                if(!this.queue.isEmpty()) {
                    Node node = relaxNode(this.queue, this.visited, this.graph);
                    //System.out.printf("visited: %s\n rVisited: %s\n\n", 
                        //Arrays.toString(visited), Arrays.toString(rVisited));
                    if(rVisited[node.vertex] != null) {
                        intersectionPoint = node.vertex;
                        break;
                    }
                }
                if(!this.rQueue.isEmpty()) {
                    Node node = relaxNode(this.rQueue, this.rVisited, this.rGraph);        
                    //System.out.printf("visited: %s\n rVisited: %s\n\n", 
                        //Arrays.toString(visited), Arrays.toString(rVisited));
                    if(visited[node.vertex] != null) {
                        intersectionPoint = node.vertex;
                        break;
                    }
                }

            }
            //System.out.printf("intersectionPoint: %d\n", intersectionPoint);
            //System.out.printf("visited: %s\n rVisited: %s\n\n", 
                //Arrays.toString(visited), Arrays.toString(rVisited));
            return optimalPath(intersectionPoint);
        }

    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n);
        Graph rGraph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int x, y, c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextInt();
            graph.adj[x -1].add(y - 1);
            graph.cost[x - 1].add(c);
            rGraph.adj[y - 1].add(x - 1);
            rGraph.cost[y - 1].add(c);
        }

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            System.out.println(new BiDirectionalDijkstra(graph, rGraph, u - 1, v - 1).traverse());
        }
    }




    
}
