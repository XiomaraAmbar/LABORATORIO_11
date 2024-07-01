package ejercicios;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CaminosCortos {
    private int V; // Número de vértices
    private int[][] graph; // Matriz de adyacencia

    // Constructor
    public CaminosCortos(int V) {
        this.V = V;
        this.graph = new int[V][V];
        // Inicializar la matriz de adyacencia con valores infinitos
        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0; // Distancia de un vértice a sí mismo es 0
        }
    }

    // Método de Floyd-Warshall para todos los pares de vértices
    public void FloydWarshall() {
        int[][] dist = new int[V][V];

        // Inicializar la matriz de distancias con la matriz de adyacencia actual
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, V);
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Imprimir las distancias mínimas entre todos los pares de vértices
        System.out.println("Matriz de distancias mínimas (Floyd-Warshall):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    // Algoritmo de Dijkstra para un solo origen
    public void dijkstra(int src) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(V, (e1, e2) -> Integer.compare(e1.weight, e2.weight));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE) {
                    int weight = graph[u][v];

                    if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + weight) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Edge(v, dist[v]));
                    }
                }
            }
        }

        // Imprimir distancias más cortas desde el origen
        System.out.println("Distancias más cortas desde el vértice " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vértice " + i + ": " + dist[i]);
        }
    }

    // Método para añadir una arista al grafo
    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        // Si el grafo es no dirigido, descomentar la siguiente línea
        // graph[v][u] = weight;
    }

    // Clase privada Edge
    private static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

}
