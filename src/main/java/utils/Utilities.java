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

    public static <T> void mergeSort(Object[] array, Comparator<T> comparator) {
        if (array.length < 2) return;

        //split the array
        int mid = array.length / 2;
        Object[] left = new Object[mid];
        Object[] right = new Object[array.length - mid];

        //copy the first half to the left and the other to the right
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        //recursive sort both halves
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        //merge the sorted halves
        merge(array, left, right, comparator);
    }

    private static <T> void merge(Object[] array, Object[] left, Object[] right, Comparator<T> comparator) {
        // i = left array j = right k= main
        int i = 0, j = 0, k = 0;


        //take first item from left and first from right
        //whchever is smaller goes into k
        //
        while (i < left.length && j < right.length) {
            //compare the items to each other
            if (comparator.compare((T) left[i], (T) right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
