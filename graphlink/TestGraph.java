package graphlink;

import listLinked.LinkedList;
import listLinked.Node;

public class TestGraph {

    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();

        System.out.println("INSERCIÓN VERTICE A,B,C,D,E");

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        System.out.println("El grafo es:");
        System.out.println(graph.toString());

        System.out.println("INSERCIÓN ARISTAS");

        // Insertar aristas sin peso
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "E");

        // Verificar existencia de vértices
        System.out.println("¿Existe el vértice A? " + graph.searchVertex("A")); // true
        System.out.println("¿Existe el vértice F? " + graph.searchVertex("F")); // false

        // Verificar existencia de aristas
        System.out.println("¿Existe la arista A-B? " + graph.searchEdge("A", "B")); // true
        System.out.println("¿Existe la arista A-E? " + graph.searchEdge("A", "E")); // false

        // Recorrido en profundidad (DFS)
        System.out.println("DFS desde A:");
        GraphLink.dft(graph, new Vertex<>("A"));

        // Recorrido en amplitud (BFS)
        System.out.println("BFS desde A:");
        GraphLink.bft(graph, new Vertex<>("A"));

        // Camino más corto usando BFS
        System.out.println("Camino más corto de A a E usando BFS:");
        LinkedList<Vertex<String>> pathBFS = graph.bftPath("A", "E");
        if (pathBFS != null) {
            printPath(pathBFS);
        }

        // Camino más corto usando Dijkstra
        System.out.println("Camino más corto de A a E usando Dijkstra:");
        LinkedList<Vertex<String>> pathDijkstra = graph.shortPath("A", "E");
        if (pathDijkstra != null) {
            printPath(pathDijkstra);
        }

        // Eliminar una arista
        graph.removeEdge("A", "B");
        System.out.println("¿Existe la arista A-B después de eliminarla? " + graph.searchEdge("A", "B")); // false

        // Eliminar un vértice
        graph.removeVertex("C");
        System.out.println("¿Existe el vértice C después de eliminarlo? " + graph.searchVertex("C")); // false

        // Imprimir el grafo final
        System.out.println("Grafo final:");
        System.out.println(graph);
    }

    private static <E> void printPath(LinkedList<Vertex<E>> path) {
        Node<Vertex<E>> current = path.getFirst();
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
