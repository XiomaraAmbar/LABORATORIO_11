package listLinked;

public class Node<E> {
    private  E data;
    private Node<E> next;

    public Node (E data){
        this(data,null);
    }
    public Node(E data, Node<E> next){
        this.data = data; //valor del nodo
        this.next = next; //posicion del nodo
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    public String toString(){
        return this.data.toString();
    }

    /*//numero negativo si es menor, 0 si son iguales y numero positivo si es mayor
    public int compareTo(Node<T> otroDato){
        return this.data.compareTo(otroDato.data);
    }*/

    public int CompareTo(Node<E> otroDato) {

        if (this.data == null && otroDato.data == null) {
            return 0; // Ambos datos son nulos, por lo que son iguales
        } else if (this.data == null) {
            return -1; // El dato actual es nulo, por lo que es menor que el otro
        } else if (otroDato.data == null) {
            return 1; // El otro dato es nulo, por lo que es mayor que el actual
        } else {

            return Integer.compare(this.data.hashCode(), otroDato.data.hashCode());
        }
    }

}
