package listLinked;

public class TestList {

    public static void main(String[] args) {
        // Create a linked list of Strings
        LinkedList<String> list = new LinkedList<>();

        // Test insertFirst
        System.out.println("\nInsertando 'Apple' al inicio:");
        list.insertFirst("Apple");
        System.out.println(list.toString()); // Output: {Apple}

        // Test insertLast
        System.out.println("\nInsertando'Banana' al final:");
        list.insertLast("Banana");
        System.out.println(list.toString()); // Output: {Apple, Banana}

        // Test insertLast again
        System.out.println("\nInsertando 'Cherry' al final:");
        list.insertLast("Cherry");
        System.out.println(list.toString()); // Output: {Apple, Banana, Cherry}

        // Test search
        System.out.println("\nBuscando 'Banana':");
        int bananaPos = list.search("Banana");
        if (bananaPos != -1) {
            System.out.println("Encontrando 'Banana' en la posicion " + bananaPos);
        } else {
            System.out.println("Banana no encontrada");
        } // Output: Found Banana at position 1

        // Test removeNode
        System.out.println("\nEliminando 'Apple':");
        list.removeNode("Apple");
        System.out.println(list.toString()); // Output: {Banana, Cherry}

        // Test deleteDuplicates (assuming Strings have proper equals method)
        System.out.println("\nInsertando duplicado 'Cherry':");
        list.insertLast("Cherry");
        System.out.println(list.toString()); // Output: {Banana, Cherry, Cherry}

        System.out.println("\nRemoviendo duplicados:");
        list.deleteDuplicates();
        System.out.println(list.toString()); // Output: {Banana, Cherry}

        // Test additional functionalities (optional)
        // You can test insertNth and deleteNth here if you want

    }
}
