import java.util.ArrayList;

/**
 * Create a new concrete class that implements MyMap (Listing 27.1)
 * using open addressing with quadratic probing. For simplicity, use f(key) = key % size
 * as the hash function, where size is the hash-table size.
 * Initially, the hash-table size is 4. The table size is doubled whenever the load factor exceeds the threshold (0.5).
 * rite a driver program to test your class.
 * @author Yavik Kapadia
 * @version 12-06-2020
 */
public class TestingMap {
    public static void main(String[] arg) {
        MyMap<String, Integer> map = new MyHashMap<>();
        map.put("Cole", 30);
        map.put("Ken", 31);
        map.put("Ken", 32);
        map.put("Jason", 29);
        map.put("Stacy", 29);
        map.put("Barbie", 21);


        System.out.println("Entries in map: " + map);

        System.out.println("Jason's age is " +
                map.get("Jason"));

        System.out.println("Kyle's age is " +
                map.get("Kyle"));

        System.out.println("Is Ken the map? " +
                map.containsKey("Ken"));

        System.out.println("Is Mark in the map? " +
                map.containsKey("Mark"));

        System.out.println("Is someone 33 in the map? " +
                map.containsValue(33));

        System.out.println("Is age 31 in the map? " +
                map.containsValue(31));

        System.out.println("Size of Map" +
                map.size());

        System.out.print("Keys in map: ");
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }

        System.out.println();

        System.out.print("Values in map: ");
        for (int value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Removed Ken");
        map.remove("Ken");
        System.out.println("Entries in map " + map);

        System.out.println();

        System.out.println("Removed all Entries");
        map.clear();
        System.out.println("Map is empty: " + map.isEmpty());
    }


}
class MyHashMap<K, V> implements MyMap<K, V> {
    // Define the default hash table size. Must be a power of 2
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    // Specify a load factor used in the hash table
    private float loadFactorThreshold;

    // The number of entries in the map
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    ArrayList<MyMap.Entry<K, V>> table;


    /** Construct a map with the default capacity and load factor */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity and
     * default load factor */
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity
     * and load factor */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
    }

    @Override /** Remove all of the entries from this map */
    public void clear() {
        size = 0;
        removeEntries();
    }

    @Override /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
        if (get(key) != null)
            return true;
        else
            return false;
    }

    @Override /** Return true if this map contains the value */
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null && table.get(i).getValue() == value)
                return true;
        }

        return false;
    }

    @Override /** Return a set of entries in the map */
    public java.util.Set<MyMap.Entry<K, V>> entrySet() {
        java.util.Set<MyMap.Entry<K, V>> set =
                new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null) {
                set.add(table.get(i));
            }
        }

        return set;
    }

    @Override /** Return the value that matches the specified key */
    public V get(K key) {
        int index = hash(key.hashCode());
        int j = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                return table.get(index).getValue();
            }
            index += Math.pow(j++, 2);
            index %= capacity;
        }

        return null;
    }
    @Override /** Return true if this map contains no entries */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null) {
                set.add(table.get(i).getKey());
            }
        }

        return set;
    }

    @Override /** Add an entry (key, value) into the map */
    public V put(K key, V value) {
        int index = hash(key.hashCode());
        int j = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                Entry<K, V> entry = table.get(index);
                V oldValue = entry.getValue();
                entry.value = value;
                table.set(index, entry);
                return oldValue;
            }

            index += Math.pow(j++, 2);
            index %= capacity;
        }

        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");
            rehash();
        }

        table.set(index, new MyMap.Entry<K, V>(key, value));

        size++;

        return value;
    }

    @Override /** Remove the entries for the specified key */
    public void remove(K key) {
        int index = hash(key.hashCode());
        int j = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                table.remove(index);
                size--;
                break;
            }
            index += Math.pow(j++, 2);
            index %= capacity;
        }
    }

    @Override /** Return the number of entries in this map */
    public int size() {
        return size;
    }

    @Override /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null)
                set.add(table.get(i).getValue());
        }

        return set;
    }

    /** Hash function */
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /** Return a power of 2 for initialCapacity */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    /** Remove all entries from each bucket */
    private void removeEntries() {
        table.clear();
    }

    /** Rehash the map */
    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet();
        capacity <<= 1;
        size = 0;
        table.clear();
        for (int i = 0; i < capacity; i++)
            table.add(null);

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (Entry<K, V> entry: table) {
            if (entry != null && table.size() > 0)
                builder.append(entry);
        }

        builder.append("]");
        return builder.toString();
    }
}
interface MyMap<K, V> {
    /** Remove all of the entries from this map */
    public void clear();

    /** Return true if the specified key is in the map */
    public boolean containsKey(K key);

    /** Return true if this map contains the specified value */
    public boolean containsValue(V value);

    /** Return a set of entries in the map */
    public java.util.Set<Entry<K, V>> entrySet();

    /** Return the first value that matches the specified key */
    public V get(K key);

    /** Return true if this map contains no entries */
    public boolean isEmpty();

    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet();

    /** Add an entry (key, value) into the map */
    public V put(K key, V value);

    /** Remove the entries for the specified key */
    public void remove(K key);

    /** Return the number of mappings in this map */
    public int size();

    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values();

    /** Define inner class for Entry */
    public static class Entry<K, V> {
        K key;
        V value;

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

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }
}