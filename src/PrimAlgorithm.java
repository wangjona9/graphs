import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Iterator;

public class PrimAlgorithm {

    public static Graph prim(Graph graph) throws Exception {
        if (graph.numVertices() == 0) {
            throw new Exception("Graph is empty");
        }

        PriorityQueue<Edge> remaining = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight(), e2.weight()));
        Graph mst = new Graph(graph.numVertices());

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

            if (!mst.isMarked(minWeightEdge.from()) || !mst.isMarked(minWeightEdge.to())) {
                mst.addEdge(minWeightEdge.from(), minWeightEdge.to(), minWeightEdge.weight());
                mst.mark(minWeightEdge.from());
                mst.mark(minWeightEdge.to());

                for (Edge edge : graph.edgesFrom(minWeightEdge.to())) {
                    if (!mst.isMarked(edge.to())) {
                        remaining.add(edge);
                    }
                }
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
    
        // Add edges to the graph
        graph.addEdge(vertex1, vertex2, 3);
        graph.addEdge(vertex2, vertex3, 2);
        graph.addEdge(vertex3, vertex1, 5);
    
        // Run Prim's algorithm
        Graph minimumSpanningTree = prim(graph);
    
        // Display the minimum spanning tree
        PrintWriter pw = new PrintWriter(System.out);
        minimumSpanningTree.dumpWithNames(pw);
        pw.flush();
    }
    
}
