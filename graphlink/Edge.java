package graphlink;

public class Edge<E> {
    private int weight;
    private Vertex<E> refDest;
    private String label; // Nueva propiedad para la etiqueta de la arista

    public Edge(Vertex<E> refDest){
        this(refDest,-1);
    }

    public Edge(Vertex<E> refDest, int weight) {
        this.weight = weight;
        this.refDest = refDest;
    }

    public Vertex<E> getRefDest() {
        return refDest;
    }

    public int getWeight() {
        return weight;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<?> e = (Edge<?>) o;
            return this.refDest.equals(e.refDest) && this.weight == e.weight;
        }
        return false;
    }

    public String toString() {
        if (this.weight > -1)
            return refDest.getData() + " [" + this.weight + "], ";
        else
            return refDest.getData() + ", ";
    }
}
