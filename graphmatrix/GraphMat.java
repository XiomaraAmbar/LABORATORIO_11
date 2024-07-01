package graphmatrix;

import java.util.*;

public class GraphMat<E> {
    private Map<E, Integer> vertexMap; // Mapa para almacenar los vértices y sus índices
    private LinkedList<E> vertices; // Lista enlazada para almacenar los vértices en orden
    private boolean[][] adjMatrix; // Matriz de adyacencia
    private int maxVertices; // Capacidad máxima de vértices
    private int numVertices; // Número actual de vértices

    public GraphMat(int maxVertices) {
        this.maxVertices = maxVertices;
        this.vertexMap = new HashMap<>();
        this.vertices = new LinkedList<>();
        this.adjMatrix = new boolean[maxVertices][maxVertices];
        this.numVertices = 0;
    }

    public void insertVertex(E data) {
        if (numVertices >= maxVertices) {
            throw new IllegalArgumentException("Capacidad máxima de vértices alcanzada");
        }
        if (!vertexMap.containsKey(data)) {
            vertexMap.put(data, numVertices);
            vertices.add(data);
            numVertices++;
        }
    }

    public void insertEdge(E verOri, E verDes) {
        if (!vertexMap.containsKey(verOri) || !vertexMap.containsKey(verDes)) {
            throw new IllegalArgumentException("Uno o ambos vértices no existen");
        }

        int indexOri = vertexMap.get(verOri);
        int indexDes = vertexMap.get(verDes);

        adjMatrix[indexOri][indexDes] = true;
        adjMatrix[indexDes][indexOri] = true; // Para grafo no dirigido
    }

    public boolean searchVertex(E v) {
        return vertexMap.containsKey(v);
    }

    public boolean searchEdge(E verOri, E verDes) {
        if (!vertexMap.containsKey(verOri) || !vertexMap.containsKey(verDes)) {
            return false;
        }

        int indexOri = vertexMap.get(verOri);
        int indexDes = vertexMap.get(verDes);

        return adjMatrix[indexOri][indexDes];
    }

    public void dfs(E startVertex) {
        if (!vertexMap.containsKey(startVertex)) {
            throw new IllegalArgumentException("El vértice inicial no existe");
        }

        boolean[] visited = new boolean[maxVertices];
        int startIndex = vertexMap.get(startVertex);
        dfsUtil(startIndex, visited);
    }

    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(vertices.get(v) + " ");

        for (int i = 0; i < maxVertices; i++) {
            if (adjMatrix[v][i] && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matriz de Adyacencia:\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                sb.append(adjMatrix[i][j] ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
