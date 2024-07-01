package graphmatrix;

public class TestGraphMatrix {
    public static void main(String[] args) {
        GraphMat<String> graph = new GraphMat<>(5); // Creando un grafo con un máximo de 5 vértices

        // Insertando vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        // Insertando aristas
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "E");

        // Mostrando la matriz de adyacencia
        System.out.println(graph);

        // Buscando vértices
        System.out.println("¿Existe el vértice A? " + graph.searchVertex("A"));
        System.out.println("¿Existe el vértice F? " + graph.searchVertex("F"));

        // Buscando aristas
        System.out.println("¿Existe la arista A-B? " + graph.searchEdge("A", "B"));
        System.out.println("¿Existe la arista A-E? " + graph.searchEdge("A", "E"));

        // Realizando recorrido en profundidad desde el vértice A
        System.out.print("DFS desde A: ");
        graph.dfs("A");
    }
}
