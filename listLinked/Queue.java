package listLinked;

public class Queue<E> {

    private Node<E> front; // Frente de la cola
    private Node<E> rear;  // Final de la cola
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Método para encolar un elemento
    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }

        size++;
    }

    // Método para desencolar un elemento
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacía");
        }

        E data = front.getData();
        front = front.getNext();
        size--;

        if (isEmpty()) {
            rear = null;
        }

        return data;
    }

    // Método para obtener el elemento en el frente sin desencolarlo
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola Vacía");
        }
        return front.getData();
    }

    // Método para verificar si la cola está vacía
    public boolean isEmpty() {
        return front == null;
    }

    // Método para obtener el tamaño de la cola
    public int size() {
        return size;
    }

    // Método para imprimir los elementos de la cola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = front;
        while (current != null) {
            sb.append(current.getData()).append(" -> ");
            current = current.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
}

