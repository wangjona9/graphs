import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ArrayList;

public class PrimAlgorithm {

    public static ArrayList<Edge> prim(UndirectedGraph graph) throws Exception {
        if (graph.numVertices() == 0) {
            throw new Exception("Graph is empty");
        }
    
        PriorityQueue<Edge> remaining = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight(), e2.weight()));
        ArrayList<Edge> mst = new ArrayList<>();
    
        Iterator<Integer> verticesIterator = graph.vertices().iterator();
        if (!verticesIterator.hasNext()) {
            throw new Exception("Graph does not have any vertices");
        }
        int randomVertex = verticesIterator.next();
    
        for (Edge edge : graph.edgesFrom(randomVertex)) {
            remaining.add(edge);
        }
    
        graph.mark(randomVertex);
    
        while (!remaining.isEmpty()) {
            Edge minWeightEdge = remaining.poll();
    
            if (!graph.isMarked(minWeightEdge.from()) || !graph.isMarked(minWeightEdge.to())) {
                mst.add(minWeightEdge);
                graph.mark(minWeightEdge.from());
                graph.mark(minWeightEdge.to());
    
                for (Edge edge : graph.edgesFrom(minWeightEdge.to())) {
                    if (!graph.isMarked(edge.to())) {
                        remaining.add(edge);
                    }
                }
            }
        }
    
        return mst;
    }
    

    public static void main(String[] args) throws Exception {
        UndirectedGraph graph = new UndirectedGraph();
        
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
        
        // Run Prim's algorithm
        ArrayList<Edge> minimumSpanningTree = prim(graph);
        
        // Display the minimum spanning tree
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println("  " + graph.vertexName(edge.from()) + " --" + edge.weight() + "-- " + graph.vertexName(edge.to()));
        }
    }
}    