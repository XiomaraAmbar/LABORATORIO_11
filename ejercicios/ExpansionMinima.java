package ejercicios;

import java.util.Arrays;
import java.util.Comparator;

public class ExpansionMinima {
    private int V; // Número de vértices
    private int[][] graph; // Matriz de adyacencia para almacenar el grafo
    private Edge[] edges; // Arreglo de aristas
    private int numEdges; // Número de aristas
    private Edge[] result; // Arreglo para almacenar el MST resultante en Kruskal

    // Clase interna para representar aristas
    private class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Constructor
    public ExpansionMinima(int numVertices, int numEdges) {
        this.V = numVertices;
        this.numEdges = 0; // Inicializar el número de aristas a cero
        this.graph = new int[V][V];
        this.edges = new Edge[numEdges];
        this.result = new Edge[V - 1]; // El MST tendrá V-1 aristas

        // Inicializar la matriz de adyacencia con valores grandes (infinito)
        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
    }

    // Método para agregar una arista al grafo
    public void addEdge(int src, int dest, int weight) {
        graph[src][dest] = weight;
        graph[dest][src] = weight; // Para grafos no dirigidos
        edges[numEdges++] = new Edge(src, dest, weight);
    }

    // Algoritmo de Prim para encontrar el árbol de expansión mínima
    public void primMST() {
        int[] parent = new int[V]; // Arreglo para almacenar el árbol de expansión mínima
        int[] key = new int[V]; // Clave de peso mínimo de los vértices aún no incluidos en el árbol
        boolean[] mstSet = new boolean[V]; // Conjunto para almacenar vértices incluidos en el MST

        // Inicializar todas las claves como infinito e incluidas en el MST como falso
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // La raíz siempre se elige como el primer vértice
        key[0] = 0; // Clave 0 para el primer vértice
        parent[0] = -1; // El primer nodo es siempre la raíz del MST

        // Construir el MST
        for (int count = 0; count < V - 1; count++) {
            // Escoger el vértice con la clave mínima que no está incluido en el MST aún
            int u = minKey(key, mstSet);

            // Agregar el vértice seleccionado al MST
            mstSet[u] = true;

            // Actualizar las claves de los vértices adyacentes que aún no están en el MST
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Imprimir el MST construido
        System.out.println("Arista \tPeso");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    // Método auxiliar para encontrar el vértice con la clave mínima que no está incluido en el MST
    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Algoritmo de Kruskal para encontrar el árbol de expansión mínima
    public void kruskalMST() {
        // Paso 1: Ordenar todas las aristas en orden no decreciente de su peso
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

        // Arreglo para detectar ciclos
        int[] parent = new int[V];

        // Crear V subconjuntos con un solo elemento
        for (int v = 0; v < V; v++) {
            parent[v] = v;
        }

        int e = 0; // Índice para el resultado en result[]
        int i = 0; // Índice para las aristas ordenadas

        // Número de aristas resultantes es igual a V-1
        while (e < V - 1 && i < numEdges) {
            // Paso 2: Seleccionar la arista más pequeña. Comprobar si añadiéndola crea un ciclo.
            Edge next_edge = edges[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            // Si incluye esta arista, forma un ciclo
            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
        }

        // Paso 3: Imprimir el árbol de expansión mínima
        System.out.println("Aristas del árbol de expansión mínima:");
        for (int j = 0; j < e; j++) {
            System.out.println(result[j].src + " - " + result[j].dest + ": " + result[j].weight);
        }
    }

    // Método auxiliar para encontrar el conjunto al que pertenece el vértice u
    private int find(int[] parent, int u) {
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);
        }
        return parent[u];
    }

    // Método auxiliar para unir dos conjuntos en uno solo
    private void union(int[] parent, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);
        parent[xroot] = yroot;
    }

    // Método para imprimir el MST obtenido por Kruskal
    public void printKruskalMST() {
        System.out.println("Arista \tPeso");
        for (int i = 0; i < V - 1; i++) {
            System.out.println(result[i].src + " - " + result[i].dest + "\t" + result[i].weight);
        }
    }
}