public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> nex;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    private int M = 11; //default number of chains
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }
    private int hash(K key) {
        int hashCode = key.hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % M;
    }
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }
    public V get(K key) {}
    public V remove(K key) {}
    public boolean contains(V value) {}
    public K getKey(V value) {}
}
