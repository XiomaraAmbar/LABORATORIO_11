package listLinked;

public class ListArray<E> {
    private E array[];
    private int n;
    private int idx;

    @SuppressWarnings("unchecked")
    public ListArray(int n){
        this.n = n;
        this.array = (E[]) new Object[n];
        this.idx = 0;

    }

    public boolean isEmpty() {
        return this.idx == 0;
    }

    public int length() {
        return this.idx;
    }

    public void destroyList(){
        this.idx = 0;

    }

    public int search(E x) {
        for (int i = 0; i < this.idx; i++)
            if (this.array[i].equals(x))
                return i;
        return -1;
    }

    @SuppressWarnings("null")
    public int searchK(int k) {
        if (k >= 0 && k < this.idx) {
            return (int) this.array[k];
        } else {
            System.out.println(">>>>>>>>>Índice fuera de rango ..........");
            return (Integer) null;
        }
    }

    public void insertFirst(E x) {
        if (this.idx < this.n){
            for (int i = this.idx; i > 0; i--) {
                this.array[i] = this.array[i-1];
            }
            this.array[0] = x;
            this.idx++;
        }
        else System.out.println(">>>>>>>>>>>>>Espacio insuficiente ............");
    }

    public void insertLast(E x) {
        if (this.idx < this.n) {
            this.array[this.idx] = x;
            this.idx++;
        } else {
            System.out.println(">>>>>>>>>>>>>>Espacio insuficiente ............");
        }
    }

    public void insertNth(E x, int k) {
        if (this.idx < this.n) {
            for (int i = this.idx; i > k; i--) {
                this.array[i] = this.array[i-1];
            }
            this.array[k] = x;
            this.idx++;
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>Espacio insuficiente ............");
        }
    }

    public void removeNode(E x) {
        int pos = search(x);
        if (pos >= 0 && pos < this.idx) {
            for (int i = pos; i < this.idx - 1; i++) {
                this.array[i] = this.array[i+1];
            }
            this.array[this.idx-1] = null;
            this.idx--;
        } else {
            System.out.println(">>>>>>>>>>>>>>>>El elemento no se encuentra en la lista...............");
        }
    }

    public String toString(){
        String str = "";
        for (int i = 0; i < this.idx; i++)
            str += this.array[i] + ", ";
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 2); //Elimina la coma al final
        }
        return "{" + str + "}";
    }

}
