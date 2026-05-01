public class Main {
    public static void main(String[] args) {
        // ---- Test MyHashTable ----
        System.out.println("=== Testing MyHashTable ===");
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("cherry", 3);

        System.out.println("get apple: " + hashTable.get("apple"));
        System.out.println("get banana: " + hashTable.get("banana"));
        System.out.println("contains value 3: " + hashTable.contains(3));
        System.out.println("getKey for value 2: " + hashTable.getKey(2));

        hashTable.remove("banana");
        System.out.println("after removing banana, get banana: " + hashTable.get("banana"));
        System.out.println("size: " + hashTable.getSize());

        System.out.println();

        // ---- Test BST ----
        System.out.println("=== Testing BST ===");
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(9, "nine");

        System.out.println("get 3: " + tree.get(3));
        System.out.println("get 7: " + tree.get(7));
        System.out.println("size: " + tree.size());

        System.out.println("In-order traversal:");
        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        System.out.println();
        tree.delete(3);
        System.out.println("After deleting key 3, in-order traversal:");
        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        System.out.println("size after delete: " + tree.size());

        System.out.println();

        // ---- Test MyTestingClass with 10000 elements ----
        System.out.println("=== Testing MyTestingClass bucket distribution ===");
        MyTestingClass.main(args);
    }
}
