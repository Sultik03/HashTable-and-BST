import java.util.Random;

public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
    // custom hashCode, not using Objects.hash()
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        for (int i = 0; i < name.length(); i++) {
            result = 31 * result + name.charAt(i);
        }
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id && name.equals(other.name);
    }
    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name='" + name + "'}";
    }
    public static void main(String[] args) {
        // using M = 11 (default) for 10000 elements
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random rand = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry"};

        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            String name = names[rand.nextInt(names.length)] + i;
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student("Student" + i, rand.nextInt(100));
            table.put(key, value);
        }

        System.out.println("Bucket distribution after adding 10000 elements:");
        table.printBucketSizes();
    }
}
