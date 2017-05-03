package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort packets received from a
 * server containing image metadata.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class PacketSorter {
    /**
     * Sorts the given array of packets in ascending order according to the
     * comparator using mergesort. You may create as many private helper
     * functions as you wish to implement this method.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the packets to sort
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void mergeSort(Packet[] array, Comparator<Packet> comparator) {
        // TODO: Add your merge sort algorithm here.
        int start = 0;
        int end = array.length - 1;
        merge(array, comparator, start, end);

    }

    // Parameters: Packet[], Comparator<Packet>, int, int
    // Finds the middle of the passed array, and recurses until only one index in array
    // Merges back together the sorted bits
    private static void merge(Packet[] array, Comparator<Packet> comparator, int start, int end) {
        int middleSpot = 0;
        if (start < end) {
            middleSpot = (start + end) / 2;
            merge(array, comparator, start, middleSpot);
            merge(array, comparator, middleSpot + 1, end);
            merge(array, comparator, start, middleSpot, end);
        }
    }

    // Parameters: Packet[], Comparator<Packet>, int, int, int
    // Creates a temporary array structure to store data
    // Performs the bulk of mergeSort
    private static void merge(Packet[] array, Comparator<Packet> comp, int start, int middleSpot, int end) {
        // The following 4 integers act as my pointers as I pass through indices of my array
        int leftSpot = start;
        int rightSpot = end;
        int middle = middleSpot + 1;
        int current = leftSpot;
        Packet[] tempArray = new Packet[array.length];
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
     * Sort the array of packets in ascending order using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void selectionSort(Packet[] array, Comparator<Packet> comparator) {
        for (int index = 0; index < array.length; index++) {
            int newIndex = findNextMin(index, comparator, array);
            swap(newIndex, index, array);
        }
    }

    // Parameters: int, Comparator<Packet>, Packet[]
    // returns the index of next smallest value in array passed
    private static int findNextMin(int index, Comparator<Packet> comparator, Packet[] array) {
        Packet returnd = array[index];
        int returndIndex = index;
        for (int j = index + 1; j < array.length; j++) {
            if (comparator.compare(returnd, array[j]) > 0) {
                returndIndex = j;
                returnd = array[j];
            }
        }
        return returndIndex;
    }

    // Parameters: int, int, Packet[]
    // swaps the two indices in the passed array.
    private static void swap(int index1, int index2, Packet[] array) {
        Packet temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Sort the array of packets in ascending order using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void insertionSort(Packet[] array, Comparator<Packet> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Packet currentPacket = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0 && comparator.compare(currentPacket, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentPacket;
        }
    }
}
