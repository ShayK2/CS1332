import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input key/val cannot be null");
        }

        if ((double) (size + 1) / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }

        int hash = Math.abs(key.hashCode() % table.length);

        if (table[hash] == null) {
            table[hash] = new MapEntry<K, V>(key, value);
        } else {
            MapEntry<K, V> entry = table[hash];

            while (entry != null) {
                if (key.equals(entry.getKey())) {
                    V returnVal = entry.getValue();
                    entry.setValue(value);
                    return returnVal;
                }

                entry = entry.getNext();
            }

            table[hash] = new MapEntry<K, V>(key, value, table[hash]);
        }

        size++;
        return null;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException,
            NoSuchElementException {
        if (key == null) {
            throw new IllegalArgumentException("Key to get cannot be null");
        }

        int hash = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> next = table[hash];
        MapEntry<K, V> curr = null;

        while (next != null) {
            if (next.getKey().equals(key)) {
                V val = next.getValue();
                if (curr == null) {
                    table[hash] = next.getNext();
                } else {
                    curr.setNext(next.getNext());
                }

                size--;
                return val;
            }

            curr = next;
            next = next.getNext();
        }

        throw new NoSuchElementException("Given key was not found in table");
    }

    @Override
    public V get(K key) throws IllegalArgumentException,
            NoSuchElementException {
        if (key == null) {
            throw new IllegalArgumentException("Key to get cannot be null");
        }

        int hash = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> entry = table[hash];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }

            entry = entry.getNext();
        }

        throw new NoSuchElementException("Given key was not found in table");
    }

    @Override
    public boolean containsKey(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Input key cannot be null");
        }

        int hash = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> entry = table[hash];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }

            entry = entry.getNext();
        }

        return false;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();

        for (MapEntry<K, V> entry: table) {
            while (entry != null) {
                keySet.add(entry.getKey());
                entry = entry.getNext();
            }
        }

        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<V>();

        for (MapEntry<K, V> entry: table) {
            while (entry != null) {
                values.add(entry.getValue());
                entry = entry.getNext();
            }
        }

        return values;
    }

    @Override
    public void resizeBackingTable(int length) throws IllegalArgumentException {
        if (length < 1 || length < size) {
            throw new IllegalArgumentException("Input table size is too small");
        }

        MapEntry<K, V>[] tempTable = table;
        table = (MapEntry<K, V>[]) new MapEntry[length];

        for (MapEntry<K, V> entry: tempTable) {
            while (entry != null) {
                int hash = Math.abs(entry.getKey().hashCode() % table.length);
                K key = entry.getKey();
                V val = entry.getValue();

                table[hash] = new MapEntry<K, V>(key, val, table[hash]);

                entry = entry.getNext();
            }
        }
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        return table;
    }
}
