package graphlink;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import listLinked.*;

public class GraphLink<E> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<>();
    }

    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        listVertex.insertLast(newVertex);
    }

    public void insertEdge(E verOri, E verDes) {
        int posOri = listVertex.search(new Vertex<>(verOri));
        int posDes = listVertex.search(new Vertex<>(verDes));
    
        if (posOri != -1 && posDes != -1) {
            Vertex<E> originVertex = listVertex.get(posOri);
            Vertex<E> destVertex = listVertex.get(posDes);
            Edge<E> newEdge = new Edge<>(destVertex); // Creación de la arista sin peso
            originVertex.getListAdj().insertLast(newEdge);
        }
    }    
    
    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }
        int posOri = listVertex.search(new Vertex<>(verOri));
        int posDes = listVertex.search(new Vertex<>(verDes));

        if (posOri != -1 && posDes != -1) {
            Vertex<E> originVertex = listVertex.get(posOri);
            Vertex<E> destVertex = listVertex.get(posDes);
            Edge<E> newEdge = new Edge<>(destVertex, weight);
            originVertex.getListAdj().insertLast(newEdge);
        }
    }

    public boolean searchVertex(E v) {
        int position = listVertex.search(new Vertex<>(v));
        return position != -1;
    }
    
    public boolean searchEdge(E verOri, E verDes) {
        int posOri = listVertex.search(new Vertex<>(verOri));
        if (posOri == -1) {
            return false; // El vértice de origen no existe en el grafo
        }
    
        Vertex<E> originVertex = listVertex.get(posOri);
    
        // Utiliza el método search de la lista de adyacencia del vértice de origen
        int posDes = originVertex.getListAdj().search(new Edge<>(new Vertex<>(verDes)));
        
        return posDes != -1;
    }

    public void removeVertex(E v) {
        // Verifica si el vértice existe en el grafo
        int pos = listVertex.search(new Vertex<>(v));
        if (pos == -1) {
            System.out.println("No existe el vértice " + v);
            return;
        }

        Vertex<E> vertexToRemove = listVertex.get(pos);

        // Obtener la lista de adyacencia del vértice a eliminar
        @SuppressWarnings("unused")
        LinkedList<Edge<E>> adjListToRemove = vertexToRemove.getListAdj();

        // Obtener el primer nodo de listVertex
        Node<Vertex<E>> node = listVertex.getFirst();

        // Iterar sobre la lista de vértices
        while (node != null) {
            Vertex<E> vertex = node.getData();

            // Busca y elimina el vértice a remover de la lista de adyacencia del vértice actual
            vertex.getListAdj().removeNode(new Edge<>(vertexToRemove));

            // Avanzar al siguiente nodo
            node = node.getNext();
        }

        // Finalmente, eliminar el vértice de la lista de vértices del grafo
        listVertex.removeNode(vertexToRemove);
    }
    

    public void removeEdge(E verOri, E verDes) {
        // Verificar si la arista existe
        if (!searchEdge(verOri, verDes)) {
            System.out.println("La arista entre " + verOri + " y " + verDes + " no existe.");
            return;
        }
    
        // Obtener las posiciones de los vértices origen y destino
        int posOri = listVertex.search(new Vertex<>(verOri));
        int posDes = listVertex.search(new Vertex<>(verDes));
    
        if (posOri != -1 && posDes != -1) {
            Vertex<E> originVertex = listVertex.get(posOri);
            Vertex<E> destVertex = listVertex.get(posDes);
    
            // Eliminar la arista de la lista de adyacencia del vértice origen
            originVertex.getListAdj().removeNode(new Edge<>(destVertex));
        }
    }
    

    public static <E> void dft(GraphLink<E> graph, Vertex<E> startVertex) {
        graph.resetVisited();
        dftRecursive(startVertex);
    }
    
    private static <E> void dftRecursive(Vertex<E> vertex) {
        vertex.setVisited(true);
        System.out.println(vertex);
    
        Node<Edge<E>> current = vertex.getListAdj().getFirst(); // Utiliza el método getFirst()
    
        while (current != null) {
            Edge<E> edge = current.getData();
            Vertex<E> oppositeVertex = edge.getRefDest();
            
            if (!oppositeVertex.isVisited()) {
                edge.setLabel("DISCOVERY");
                dftRecursive(oppositeVertex);
            }
    
            current = current.getNext();
        }
    }
    
    public static <E> void bft(GraphLink<E> graph, Vertex<E> startVertex) {
        graph.resetVisited();
        Queue<Vertex<E>> queue = new Queue<>();
        queue.enqueue(startVertex); // Utilizamos el método enqueue para agregar el primer vértice
        startVertex.setVisited(true);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.dequeue(); // Utilizamos el método dequeue para obtener y remover el primer vértice
            System.out.println(current);

            Node<Edge<E>> edgeNode = current.getListAdj().getFirst(); // Utilizamos el método getFirst() para obtener el primer nodo de la lista de adyacencia

            while (edgeNode != null) {
                Edge<E> edge = edgeNode.getData();
                Vertex<E> oppositeVertex = edge.getRefDest();

                if (!oppositeVertex.isVisited()) {
                    oppositeVertex.setVisited(true);
                    queue.enqueue(oppositeVertex); // Utilizamos enqueue para agregar el vértice opuesto a la cola
                    // Si necesitas marcar la arista como descubrimiento, hazlo aquí
                    edge.setLabel("DISCOVERY");
                }

                edgeNode = edgeNode.getNext(); // Avanza al siguiente nodo en la lista de adyacencia
            }
        }
    }
    
    public void resetVisited() {
        Node<Vertex<E>> current = listVertex.getFirst(); // Obtener el primer nodo de la lista
        
        while (current != null) {
            current.getData().setVisited(false); // Restablecer el estado de visitado del vértice
            current = current.getNext(); // Avanzar al siguiente nodo
        }
    }

    //BFSPATH

    public LinkedList<Vertex<E>> bftPath(E startData, E endData) {
        // Encontrar el vértice de inicio y el vértice de destino
        Vertex<E> startVertex = null;
        Vertex<E> endVertex = null;

        Node<Vertex<E>> node = listVertex.getFirst();
        while (node != null) {
            Vertex<E> vertex = node.getData();
            if (vertex.getData().equals(startData)) {
                startVertex = vertex;
            }
            if (vertex.getData().equals(endData)) {
                endVertex = vertex;
            }
            node = node.getNext();
        }

        // Verificar que se encontraron ambos vértices
        if (startVertex == null || endVertex == null) {
            System.out.println("Uno de los vértices no existe en el grafo.");
            return null;
        }

        // Realizar la búsqueda usando BFS (Breadth-First Search)
        LinkedList<Vertex<E>> path = new LinkedList<>();
        Queue<Vertex<E>> queue = new Queue<>();
        queue.enqueue(startVertex);
        startVertex.setVisited(true);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Vertex<E> currentVertex = queue.dequeue();
            path.insertLast(currentVertex);

            Node<Edge<E>> edgeNode = currentVertex.getListAdj().getFirst();
            while (edgeNode != null && !found) {
                Edge<E> edge = edgeNode.getData();
                Vertex<E> adjacentVertex = edge.getRefDest();

                if (!adjacentVertex.isVisited()) {
                    adjacentVertex.setVisited(true);
                    queue.enqueue(adjacentVertex);

                    if (adjacentVertex.equals(endVertex)) {
                        found = true;
                    }
                }

                edgeNode = edgeNode.getNext();
            }
        }

        // Reiniciar el estado de visitado de todos los vértices
        resetVisited();

        // Si encontramos el camino, devolver la lista de vértices en el camino
        if (found) {
            return path;
        } else {
            System.out.println("No se encontró camino entre " + startData + " y " + endData);
            return null;
        }
    }

    ///////////SHORTPATH//////////////////////////////////////

    public LinkedList<Vertex<E>> shortPath(E startData, E endData) {
        int startPos = listVertex.search(new Vertex<>(startData));
        int endPos = listVertex.search(new Vertex<>(endData));

        if (startPos == -1 || endPos == -1) {
            throw new IllegalArgumentException("Uno o ambos vértices no se encontraron");
        }

        Vertex<E> startVertex = listVertex.get(startPos);
        Vertex<E> endVertex = listVertex.get(endPos);

        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> previous = new HashMap<>();
        PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        Node<Vertex<E>> currentNode = listVertex.getFirst();
        while (currentNode != null) {
            Vertex<E> vertex = currentNode.getData();
            distances.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
            currentNode = currentNode.getNext();
        }

        distances.put(startVertex, 0);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();

            if (current.equals(endVertex)) {
                break;
            }

            Node<Edge<E>> edgeNode = current.getListAdj().getFirst();
            while (edgeNode != null) {
                Edge<E> edge = edgeNode.getData();
                Vertex<E> neighbor = edge.getRefDest();

                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }

                edgeNode = edgeNode.getNext();
            }
        }

        LinkedList<Vertex<E>> path = new LinkedList<>();
        for (Vertex<E> at = endVertex; at != null; at = previous.get(at)) {
            path.insertFirst(at);
        }

        if (path.getFirst().equals(startVertex)) {
            return path;
        } else {
            return new LinkedList<>(); // No se encontró la ruta
        }
    }

    
    public String toString() {
        return this.listVertex.toString();
    }
}
