package listLinked;

public class LinkedList<E>{
    Node<E> first;
    int size;

    public LinkedList(){
        this.first = null;
        this.size = 0;
    }

    public Node<E> getFirst() {
        return first;
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public int length(){
        return this.size;

    }

    public void destroyList(){
        while ( first != null)
            this.first = this.first.getNext();

        this.size = 0;
    }

    public int search(E searchItem){
        Node <E> aux = this.first;
        int pos = 0;
        while (aux != null && !aux.getData().equals(searchItem)){
            pos ++;
            aux = aux.getNext();
        }
        if ( aux != null)
            return pos;
        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }    

    public void insertFirst(E newItem){
        this.first = new Node<E>(newItem, this.first);
        size ++;
    }

    public void insertLast(E newItem){
        if (isEmpty())
            insertFirst(newItem);
        else{
            Node<E> aux = this.first;
            while(aux.getNext() != null)
                aux = aux.getNext();
            aux.setNext(new Node<E>(newItem));
            size ++;
        }
    }

    public void removeNode(E deleteItem) {
        Node<E> prev = null;
        Node<E> curr = first;

        while (curr != null && !curr.getData().equals(deleteItem)) {
            prev = curr;
            curr = curr.getNext();
        }

        if (curr != null) {
            if (prev == null) {
                first = first.getNext();
            } else {
                prev.setNext(curr.getNext());
            }
            size--;
        }
    }



    public String toString(){
        String str = "";
        Node<E> aux = this.first;
        for(; aux != null; aux =  aux.getNext())
            str += aux.getData().toString() + ", ";
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 2); //Elimina la coma al final
        }
        return "{" + str + "}";
    }

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //EJERCICIO N°1
    
    public void deleteDuplicates() {
        Node<E> curr = first;
    
        while (curr != null) {
            Node<E> prev = curr;
            Node<E> next = curr.getNext();
    
            while (next != null) {
                if (areEqual(curr.getData(), next.getData())) {
                    prev.setNext(next.getNext());
                    size--;
                } else {
                    prev = next;
                }
                next = next.getNext();
            }
            curr = curr.getNext();
        }
    }
    
    private boolean areEqual(E data1, E data2) {
        if (data1 == null && data2 == null) {
            return true; // Ambos datos son nulos, por lo que son iguales
        } else if (data1 == null || data2 == null) {
            return false; // Uno de los datos es nulo, por lo que no son iguales
        } else {
            // Por ejemplo, si T es Integer:
            return data1.equals(data2);
        }
    }
    
    

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //EJERCICIO N°2

    public void insertNth(E data, int position) {
        if (position < 0 || position > size) {
            System.out.println(">>>>>>>>>  Posición fuera de rango  <<<<<<<<<<");
            return;
        }
    
        if (position == 0) {
            insertFirst(data);
        } else if (position == size) {
            insertLast(data);
        } else {
            Node<E> aux = first;
            for (int i = 1; i < position; i++) {
                aux = aux.getNext();
            }
            aux.setNext(new Node<E>(data, aux.getNext()));
            size++;
        }
    }
    

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //EJERCICIO N°3

    public void deleteNth(int position) {
        if (position < 0 || position > size) {
            System.out.println(">>>>>>>>>  Posición fuera de rango  <<<<<<<<<<");
            return;
        }
    
        if (position == 0) {
            first = first.getNext();
        } else {
            Node<E> aux = first;
            for (int i = 1; i < position; i++) {
                aux = aux.getNext();
            }
            aux.setNext(aux.getNext().getNext());
        }
    
        size--;
    }

}
