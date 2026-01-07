package controllers;

public class HashTable<K, V> {

    //entry is a tiny container it holds a key and value
    private static class Entry<K, V> {
        private K key;
        private V value;
        boolean isDeleted;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isDeleted = false;
        }
    }

    private Entry<K, V>[] table;
    private int size;
    private int capacity;

    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public HashTable(int initialCapacity) {
         this.capacity = initialCapacity;
         this.table = (Entry<K, V>[]) new Entry[initialCapacity];
         this.size = 0;
    }

    //finds the correct slot for a key using linear probing, itll keep going until it finds the correct key or an empty slot
    private int findSlot(K key) {
        int index = Math.abs(key.hashCode()) % capacity;

        while (table[index] != null) {
            if (table[index].key.equals(key) && !table[index].isDeleted) {
                return index;
            }
            index = (index + 1) % capacity;
        }
        return index;
    }

    //makes the table bigger when it gets too full so we just double the size and reinsert it all
    private void resize() {
        Entry<K, V>[] oldTable = table;

        capacity *= 2;
        Entry<K, V>[] newTable = new Entry[capacity];
        size = 0;

        //reinsert everything
        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }

    public void put(K key, V value) {
        //if it gets too full
        if ((double) size / table.length >= DEFAULT_LOAD_FACTOR) {
            resize();
        }

        int index = findSlot(key);

        if (table[index] == null||table[index].isDeleted) {
            table[index] = new Entry<>(key, value);
            size++;
        }
        else {
            //if the key already exists
            table[index].value = value;
        }
    }

    //gets te value attached to a key
    public V get(K key) {
        int index = findSlot(key);

        if (table[index] == null || table[index].isDeleted) {
            return null;
        }
        else {
            return table[index].value;
        }
    }

    //marks an entry as deleted so we can probe past it (tombstone) (we cant delete it completely or itll break the search chain)
    public void remove(K key) {
        int index = findSlot(key);
        if (table[index] == null || table[index].isDeleted) {
            return;
        }
        else {
            table[index].isDeleted = true;
            size--;
        }
    }

    public int size() {
        return size;
    }

    //checks if a key is in the table
    public boolean contains(K key) {
        int index = findSlot(key);
        if (table[index] == null || table[index].isDeleted) {
            return false;
        }
        else {
            return true;
        }
    }

    public DynamicArray<V> values() {
        DynamicArray<V> list = new DynamicArray<>();

        for (Entry<K, V> entry : table) {
            if (entry == null || entry.isDeleted) {
                continue;
            }
            else {
                list.add(entry.value);
            }
        }
        return list;
    }


}
