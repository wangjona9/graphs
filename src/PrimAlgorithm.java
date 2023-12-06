import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ArrayList;

public class PrimAlgorithm {

    public static ArrayList<Edge> prim(Graph graph) throws Exception {
        if (graph.numVertices() == 0) {
            throw new Exception("Graph is empty");
        }

        PriorityQueue<Edge> remaining = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight(), e2.weight()));
        ArrayList<Edge> mst = new ArrayList<Edge>();

        //create a set of vertices in mst to check against

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

            if (!mst.contains(minWeightEdge.from()) || !mst.contains(minWeightEdge.to())) {
                mst.add(minWeightEdge);
                // if (!mst.validVertex(minWeightEdge.from())) {
                //     mst.addVertex(graph.vertexName(minWeightEdge.from()));
                // }
                // if (!mst.validVertex(minWeightEdge.to())) {
                //     mst.addVertex(graph.vertexName(minWeightEdge.to()));
                // }
                // mst.addEdge(minWeightEdge.from(), minWeightEdge.to(), minWeightEdge.weight());
                // mst.mark(minWeightEdge.from());
                // mst.mark(minWeightEdge.to());

                for (Edge edge : graph.edgesFrom(minWeightEdge.to())) {
                    if (!mst.contains(edge.to())) {
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
        int vertex4 = graph.addVertex("D");
    
        // Add edges to the graph
        graph.addEdge(vertex1, vertex2, 2);
        graph.addEdge(vertex3, vertex4, 3);
        graph.addEdge(vertex4, vertex1, 1);
        graph.addEdge(vertex2, vertex4, 2);
    
        // Run Prim's algorithm
        ArrayList<Edge> minimumSpanningTree = prim(graph);
    
        // Display the minimum spanning tree
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(minimumSpanningTree);
        //minimumSpanningTree.dumpWithNames(pw);
        pw.flush();
    }
    
}
