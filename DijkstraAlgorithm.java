import java.util.*;

public class DijkstraAlgorithm {
    
    private static final int Infinity = Integer.MAX_VALUE;
    
    private int vertices;
    private int[][] graph;
    
    public DijkstraAlgorithm(int vertices) {
        this.vertices = vertices;
        graph = new int[vertices][vertices];
    }
    
    public void addEdge(int source, int destination, int weight) {
        graph[source][destination] = weight;
        graph[destination][source] = weight;
    }
    
    public void dijkstra(int source) {
        boolean[] visited = new boolean[vertices];
        int[] distance = new int[vertices];
        Arrays.fill(distance, Infinity);
        distance[source] = 0;
        
        for (int count = 0; count < vertices - 1; count++) {
            int u = minDistance(distance, visited);
            visited[u] = true;
            
            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != Infinity && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        
        printSolution(distance);
    }
    
    private int minDistance(int[] distance, boolean[] visited) {
        int min = Infinity;
        int minIndex = -1;
        
        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }
    
    private void printSolution(int[] distance) {
        System.out.println("Vertex \t\t Distance from Source");
        
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t\t " + distance[i]);
        }
    }
    
    public static void main(String[] args) {
        int vertices = 6;
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(vertices);
        
        dijkstra.addEdge(0, 1, 4);
        dijkstra.addEdge(0, 2, 3);
        dijkstra.addEdge(1, 2, 1);
        dijkstra.addEdge(1, 3, 2);
        dijkstra.addEdge(2, 3, 4);
        dijkstra.addEdge(3, 4, 2);
        dijkstra.addEdge(4, 5, 6);
        
        dijkstra.dijkstra(0);
    }
}
