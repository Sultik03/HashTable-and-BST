import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // inner class so we can access both key and value during iteration
    public class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public int size() {
        return size;
    }

    // put without recursion - just iterative loop
    public void put(K key, V val) {
        Node newNode = new Node(key, val);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    size++;
                    return;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = newNode;
                    size++;
                    return;
                }
                current = current.right;
            } else {
                // key already exists, just update value
                current.val = val;
                return;
            }
        }
    }

    // get without recursion
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    // delete without recursion
    public void delete(K key) {
        Node parent = null;
        Node current = root;
        boolean isLeftChild = false;

        // find the node to delete
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                parent = current;
                isLeftChild = true;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                isLeftChild = false;
                current = current.right;
            } else {
                break; // found it
            }
        }
    }
}
