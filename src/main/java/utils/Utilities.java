package utils;

import controllers.Comparator;

public class Utilities
{
    public static <T> void insertionSort(Object[] array, Comparator<T> comparator) {
        int n = array.length;

        // Start from the second element (index 1),
        // since a single element is already "sorted"
        for (int i = 1; i < n; i++) {
            T key = (T) array[i];
            int j = i - 1;  // Index of the last element in the sorted portion

            /*
             * Shift elements that are greater than 'key'
             * one position to the right
             */
            while (j >= 0 && comparator.compare((T) array[j], key) > 0) {
                array[j + 1] = array[j]; // Shift element right
                j--; // Move left in the array
            }
            array[j + 1] = key; // Insert the key into its correct position
        }
    }
}
