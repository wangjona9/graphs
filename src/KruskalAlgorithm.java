import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalAlgorithm {

    public static ArrayList<Edge> kruskal(Graph graph) throws Exception {
        if (graph.numVertices() == 0) {
            throw new Exception("Graph is empty");
        }

        List<Edge> edges = new ArrayList<>();
        for (Edge edge : graph.edges()) {
            edges.add(edge);
        }

        // Sort edges by weight in non-decreasing order
        Collections.sort(edges, (e1, e2) -> Integer.compare(e1.weight(), e2.weight()));

        Set<Integer> vertices = new HashSet<>();
        ArrayList<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int from = edge.from();
            int to = edge.to();

            if (!vertices.contains(from) || !vertices.contains(to)) {
                mst.add(edge);
                vertices.add(from);
                vertices.add(to);
            }
        }

        return mst;
    }

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();

        // Add vertices to the graph
        int vertex1 = graph.addVertex("A");
        int vertex2 = graph.addVertex("B");
        int vertex3 = graph.addVertex("C");
        int vertex4 = graph.addVertex("D");

        // Add edges to the graph
        graph.addEdge(vertex1, vertex2, 2);
        graph.addEdge(vertex3, vertex4, 3);
        graph.addEdge(vertex4, vertex1, 1);
        graph.addEdge(vertex2, vertex4, 2);

        // Run Kruskal's algorithm
        ArrayList<Edge> minimumSpanningTree = kruskal(graph);

        // Display the minimum spanning tree
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(minimumSpanningTree);
        pw.flush();
    }
}
