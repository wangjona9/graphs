import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

/**
 * A class implementing Dijkstra's algorithm for finding the shortest paths
 * from a source vertex to all other vertices in a weighted graph.
 *
 * This implementation uses a PriorityQueue to efficiently select the vertex
 * with the minimum distance at each step and updates the distances iteratively.
 *
 * The main method demonstrates how to use this algorithm with a sample graph.
 *
 * @author Jonathan Wang
 */
public class DijkstraAlgorithm {

    /**
     * Find the shortest paths from a source vertex to all other vertices in the graph
     * using Dijkstra's algorithm.
     *
     * @param graph The graph for which to find the shortest paths.
     * @param source The source vertex from which to start the algorithm.
     * @return A map containing the shortest distances from the source to each vertex.
     * @throws Exception If the graph is empty or the source vertex is not valid.
     */
    public static Map<Integer, Integer> dijkstra(Graph graph, int source) throws Exception {
        if (graph.numVertices() == 0) {
            throw new Exception("Graph is empty");
        }
        if (!graph.validVertex(source)) {
            throw new Exception("Invalid source vertex");
        }

        // Priority queue to store vertices based on their distances
        PriorityQueue<VertexDistancePair> remaining = new PriorityQueue<>();
        // Map to store the shortest distances from the source to each vertex
        Map<Integer, Integer> distances = new HashMap<>();

        // Initialize distances with infinity for all vertices except the source
        for (int vertex : graph.vertices()) {
            distances.put(vertex, (vertex == source) ? 0 : Integer.MAX_VALUE);
            remaining.add(new VertexDistancePair(vertex, distances.get(vertex)));
        }

        // Main loop to update distances iteratively
        while (!remaining.isEmpty()) {
            // Get the vertex with the minimum distance
            int currentVertex = remaining.poll().vertex;

            // Update distances for adjacent vertices
            for (Edge edge : graph.edgesFrom(currentVertex)) {
                int neighbor = edge.to();
                int newDistance = distances.get(currentVertex) + edge.weight();

                // If a shorter path is found, update the distance
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    remaining.add(new VertexDistancePair(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    /**
     * Main method to demonstrate the use of Dijkstra's algorithm with a sample graph.
     *
     * @param args Command-line arguments (not used).
     * @throws Exception If there is an issue with the graph operations.
     */
    public static void main(String[] args) throws Exception {
        // Create a directed graph
        Graph graph = new Graph();
    
        // Add vertices to the graph
        int vertex1 = graph.addVertex("A");
        int vertex2 = graph.addVertex("B");
        int vertex3 = graph.addVertex("C");
        int vertex4 = graph.addVertex("D");
    
        // Add edges to the graph with weights
        graph.addEdge(vertex1, vertex2, 2);
        graph.addEdge(vertex1, vertex3, 1);
        graph.addEdge(vertex2, vertex3, 3);
        graph.addEdge(vertex2, vertex4, 1);
        graph.addEdge(vertex3, vertex4, 4);
    
        // Specify the source vertex for Dijkstra's algorithm
        int sourceVertex = vertex1;
    
        // Run Dijkstra's algorithm to find the shortest paths
        Map<Integer, Integer> shortestDistances = dijkstra(graph, sourceVertex);
    
        // Display the shortest distances from the source vertex
        System.out.println("Shortest Distances from Vertex " + graph.vertexName(sourceVertex) + ":");
        for (int vertex : shortestDistances.keySet()) {
            System.out.println("  " + graph.vertexName(vertex) + ": " + shortestDistances.get(vertex));
        }
    }



    rable<VertexDistancePair> {
        int vertex;
        int distance;

        VertexDistancePair(int vertex, int distance) {




        }

        @Override
        public int compareTo(VertexDistancePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
