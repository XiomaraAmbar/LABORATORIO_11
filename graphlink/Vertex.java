package graphlink;

import java.util.Objects;

import listLinked.*;

public class Vertex<E> {
    private E data;
    private LinkedList<Edge<E>> listAdj;
    private boolean visited; // Para manejar el estado de visitado

    public Vertex(E data) {
        this.data = data;
        listAdj = new LinkedList<Edge<E>>();
        this.visited = false;
    }

    public E getData() {
        return data;
    }

    public LinkedList<Edge<E>> getListAdj() {
        return listAdj;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<?> v = (Vertex<?>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public String toString() {
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}

