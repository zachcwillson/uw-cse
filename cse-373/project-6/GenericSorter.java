package sorting;

import java.util.Comparator;
import java.util.Arrays;

public class GenericSorter<E> {

    /**
     * Sorts the given array of E in ascending order according to the comparator
     * using mergesort. You may create as many private helper functions as you
     * wish to implement this method.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the E to sort
     * @param comparator
     *            The comparator the will be used to compare two E.
     */
    public static <E> void mergeSort(E[] array, Comparator<E> comparator) {
        int start = 0;
        int end = array.length - 1;
        merge(array, comparator, start, end);
    }

    // Parameters: E[], Comparator<E>, int, int
    // Finds the middle of the passed array, and recurses until only one index in array
    // Merges back together the sorted bits
    private static <E> void merge(E[] array, Comparator<E> comparator, int start, int end) {
        int middleSpot = 0;
        if (start < end) {
            middleSpot = (start + end) / 2;
            merge(array, comparator, start, middleSpot);
            merge(array, comparator, middleSpot + 1, end);
            merge(array, comparator, start, middleSpot, end);
        }
    }

    // Parameters: E[], Comparator<E>, int, int, int
    // Creates a temporary array structure to store data
    // Performs the bulk of mergeSort
    private static <E> void merge(E[] array, Comparator<E> comp, int start, int middleSpot, int end) {
        // The following 4 integers act as my pointers as I pass through indices of my array
        int leftSpot = start;
        int rightSpot = end;
        int middle = middleSpot + 1;
        int current = leftSpot;
        E[] tempArray = Arrays.copyOf(array, array.length);
        // Here I move my pointers until these conditions are met
        // Looks at two different parts and merges them together, sorted
        while (leftSpot <= middleSpot && middle <= rightSpot) {
            if (comp.compare(array[leftSpot], array[middle]) <= 0) {
                tempArray[current++] = array[leftSpot++];
            } else {
                tempArray[current++] = array[middle++];
            }
        }
        // Left Side
        while (leftSpot <= middleSpot) {
            tempArray[current++] = array[leftSpot++];
        }
        // Right Side
        while (middle <= rightSpot) {
            tempArray[current++] = array[middle++];
        }
        // Puts all the data back from my temp array to the passed structure
        for (int i = start; i <= end; i++) {
            array[i] = tempArray[i];
        }
    }

    /**
     * Sort the array of E in ascending order according to the comparator using
     * selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of E that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two E.
     */
    public static <E> void selectionSort(E[] array, Comparator<E> comparator) {
        for (int index = 0; index < array.length; index++) {
            int newIndex = findNextMin(index, comparator, array);
            swap(newIndex, index, array);
        }
    }

    // Parameters: int, Comparator<E>, E[]
    // returns the index of next smallest value in array passed
    private static <E> int findNextMin(int index, Comparator<E> comparator, E[] array) {
        E returnd = array[index];
        int returndIndex = index;
        for (int j = index + 1; j < array.length; j++) {
            if (comparator.compare(returnd, array[j]) > 0) {
                returndIndex = j;
                returnd = array[j];
            }
        }
        return returndIndex;
    }

    // Parameters: int, int, E[]
    // swaps the two indices in the passed array.
    private static <E> void swap(int index1, int index2, E[] array) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}