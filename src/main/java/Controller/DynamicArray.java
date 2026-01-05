package Controller;

public class DynamicArray<T> {

    private T[] data;
    private int size;

    private static final int INITIAL_CAPACITY = 10;

    public DynamicArray() {
        data = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    //adds an element at the end of the array
    public void add(T element) {
        if (size == data.length) {
            resize();
        }
        data[size] = element;
    }

    //adds an array at a sepcified index
    public void addAtIndex(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == data.length) {
            resize();
        }

        // Shift all elements one to the right starting from the last element before the one bring added.
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        data[index] = element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private void resize() {
        // Create a new array that's double the size of the old one
        T[] newData = (T[]) new Object[data.length * 2];

        // Copy everything from the old array into the new one
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public T[] toArray() {
        return data;
    }
}
