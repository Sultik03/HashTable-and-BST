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

        if (current == null) return; // key not found

        // case 1: node has no children
        if (current.left == null && current.right == null) {
            if (parent == null) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // case 2: node has only right child
        else if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        // case 3: node has only left child
        else if (current.right == null) {
            if (parent == null) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        // case 4: node has two children
        // find in-order successor (smallest node in right subtree)
        else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.val = successor.val;

            // delete the successor
            if (successorParent == current) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
        }

        size--;
    }

    // iterator using in-order traversal (left, root, right) without recursion
    // uses a Stack to simulate the call stack
    public Iterable<Entry> iterator() {
        List<Entry> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            // go as far left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(new Entry(current.key, current.val));
            current = current.right;
        }

        return list;
    }
}
