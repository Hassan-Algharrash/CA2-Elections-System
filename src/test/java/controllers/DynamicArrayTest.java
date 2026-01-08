package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void add() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("Apple");

        assertEquals("Apple", arr.get(0));
        assertEquals(1, arr.size());
    }

    @Test
    void addAtIndex() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("A");
        arr.add("C");

        arr.addAtIndex(1, "B");

        assertEquals("A", arr.get(0));
        assertEquals("B", arr.get(1));
        assertEquals("C", arr.get(2));
    }

    @Test
    void removeAtIndex() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("A");
        arr.add("B");
        arr.add("C");

        arr.removeAtIndex(1);

        assertEquals("A", arr.get(0));
        assertEquals("C", arr.get(1));
        assertEquals(2, arr.size());
    }

    @Test
    void get() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("Hello");

        assertEquals("Hello", arr.get(0));
    }

    @Test
    void set() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("Old");

        arr.set(0, "New");

        assertEquals("New", arr.get(0));
    }

    @Test
    void size() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("X");
        arr.add("Y");

        assertEquals(2, arr.size());
    }

    @Test
    void isEmpty() {
        DynamicArray<String> arr = new DynamicArray<>();

        assertTrue(arr.isEmpty());

        arr.add("Not empty");

        assertFalse(arr.isEmpty());
    }

    @Test
    void toArray() {
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("A");
        arr.add("B");

        Object[] array = arr.toArray();

        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
    }
}
