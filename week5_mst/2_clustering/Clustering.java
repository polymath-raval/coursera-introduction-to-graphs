import java.util.*;
import java.math.*;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        Point[] points = points(x, y);
        Map<Point, Set<Point>> adj = adj(points);
        PriorityQueue<Edge> queue = createPriorityQueue(points);
        List<Double> edgeWeights = new ArrayList<>();
        while(!queue.isEmpty() && edgeWeights.size() != points.length - 1) {
            Edge edge = queue.remove();
            if( !reach(adj, edge.p1, edge.p2) ) {
                adj.get(edge.p1).add(edge.p2);
                adj.get(edge.p2).add(edge.p1);
                edgeWeights.add(edge.distance);
            }
        }

        int indexOfInterest = edgeWeights.size() - k + 1;
        return BigDecimal.valueOf(edgeWeights.get(indexOfInterest))
                .setScale(7, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }

    private static Map<Point, Set<Point>> adj(Point[] points) {
        Map<Point, Set<Point>> adj = new HashMap<>();
        for(int i = 0; i < points.length; i++) {
            adj.put(points[i], new HashSet<Point>());
        }
        return adj;
    }

    private static boolean reach(Map<Point, Set<Point>> adj, Point p1, Point p2) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> marked = new HashSet<>();
        
        queue.add(p1);
        marked.add(p1);

        while( !queue.isEmpty() ) {
            Point current = queue.remove();
            if(current.x == p2.x && current.y == p2.y) {
                return true;
            }
            for(Point neighbour : adj.get(current)) {
                if( !marked.contains(neighbour) ) {
                    queue.add(neighbour);
                    marked.add(neighbour);
                }
            }
        }

        return false;
    }

    private static PriorityQueue<Edge> createPriorityQueue(Point[] points) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for(int i = 0; i < points.length; i++) {
            Point first = points[i];
            for(int j = i + 1; j < points.length; j++) {
                Point second = points[j];
                Edge edge = new Edge(first, second);
                queue.add(edge);
            }
        }
        return queue;
    }

    private static Point[] points(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for(int i = 0; i < x.length; i++) {
            points[i] = new Point(i, x[i], y[i]);
        }
        return points;
    }

    private static class Point {
        int idx;
        int x;
        int y;
        
        Point(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(x = %d, y = %d)", x, y);
        }

        public boolean equals(Object other) {
            if(!(other instanceof Point)) {
                return false;
            }
            Point otherPoint = (Point) other;
            return this.x == otherPoint.x &&
                    this.y == otherPoint.y;
        }
    }

    private static class Edge implements Comparable<Edge>{
        Point p1;
        Point p2;
        double distance;

        Edge(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        }

        public int compareTo(Edge other) {
            return Double.compare(this.distance, other.distance);
        }

        public String toString() {
            return String.format("p1 --> %s, p2 --> %s, d --> %f", p1, p2, distance);
        }
    }
}

