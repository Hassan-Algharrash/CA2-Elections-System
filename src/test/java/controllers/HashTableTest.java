package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void put() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("A", "Apple");

        assertEquals("Apple", table.get("A"));
    }

    @Test
    void get() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("X", "Xray");

        assertEquals("Xray", table.get("X"));
        assertNull(table.get("MissingKey"));
    }

    @Test
    void remove() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("K", "Kiwi");

        table.remove("K");

        assertNull(table.get("K"));
    }

    @Test
    void size() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("1", "One");
        table.put("2", "Two");

        assertEquals(2, table.size());
    }

    @Test
    void contains() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("Dog", "Bark");

        assertTrue(table.contains("Dog"));
        assertFalse(table.contains("Cat"));
    }

    @Test
    void values() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("A", "Apple");
        table.put("B", "Bot");

        DynamicArray<String> vals = table.values();

        assertEquals(2, vals.size());
        assertTrue(vals.get(0).equals("Apple") || vals.get(1).equals("Apple"));
        assertTrue(vals.get(0).equals("Bot") || vals.get(1).equals("Bot"));
    }
}
