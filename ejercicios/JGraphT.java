package ejercicios;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class JGraphT {

    public static void main(String[] args) {
        // Crear un grafo no dirigido simple
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Añadir vértices
        graph.addVertex("V1");
        graph.addVertex("V2");
        graph.addVertex("V3");

        // Añadir aristas
        graph.addEdge("V1", "V2");
        graph.addEdge("V2", "V3");
        graph.addEdge("V3", "V1");

        // Imprimir vértices y aristas
        System.out.println("Vértices: " + graph.vertexSet());
        System.out.println("Aristas: " + graph.edgeSet());

        // Imprimir vecinos de un vértice específico
        String vertex = "V2";
        System.out.println("Vecinos de " + vertex + ": " + Graphs.neighborListOf(graph, vertex));
    }
}

