package ejercicios;

public class Test {
    public static void main(String[] args) {
        int V = 4;
        CaminosCortos graph = new CaminosCortos(V);

        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 3, 10);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);

        // Ejemplo de uso de Floyd-Warshall
        graph.FloydWarshall();

        // Ejemplo de uso de Dijkstra desde el vértice 0
        graph.dijkstra(0);

        int V1 = 4; // Número de vértices en el grafo
        int E = 5; // Número de aristas en el grafo

        ExpansionMinima graph2 = new ExpansionMinima(V1, E);

        // Añadir aristas al grafo
        graph2.addEdge(0, 1, 10);
        graph2.addEdge(0, 2, 6);
        graph2.addEdge(0, 3, 5);
        graph2.addEdge(1, 3, 15);
        graph2.addEdge(2, 3, 4);

        // Ejecutar el algoritmo de Prim
        System.out.println("Árbol de expansión mínima usando Prim:");
        graph2.primMST();

        System.out.println();

        // Ejecutar el algoritmo de Kruskal
        System.out.println("Árbol de expansión mínima usando Kruskal:");
        graph2.kruskalMST();
    }
}
