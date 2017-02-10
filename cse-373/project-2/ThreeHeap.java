// Zach Willson
// CSE 373 - WI
// Homework 2 - Due 1/27/2017

// This class implements the PriorityQueue ADT as a ThreeHeap, methods include a constructor,
// methods to add values and return/delete the minimum values, check if the heap
// is empty or its size, and a method to make a heap empty if you want to start over.

import java.util.List;

public class ThreeHeap implements PriorityQueue {

    // Fields for class - double array stores values and int points to the last node
    private double[] heap;
    private int front;

    // Creates a new ThreeHeap
    public ThreeHeap() {
        this.heap = new double[10];
        this.front = -1;
    }

    // Returns a boolean if heap is empty (there are no elements in heap)
    public boolean isEmpty() {
        return this.front == -1;
    }

    // Returns an integer representing the number of elements in the heap
    public int size() {
        return this.front + 1;
    }

    // Returns the minimum value (double) in the heap, throws an
    // EmptyHeapException if heap is empty
    public double findMin() {
        if (isEmpty()) {
            throw new EmptyHeapException();
        }
        return this.heap[0];
    }

    // Inserts the double passed to the method into the heap
    public void insert(double x) {
        resize();
        this.front++;
        this.heap[this.front] = x;
        percolateUp(this.front);
    }

    // Finds if the heap is full, and doubles it's size if it is
    private void resize() {
        if (this.front == this.heap.length - 1) {
            double[] newHeap = new double[this.heap.length * 2];
            for (int i = 0; i < this.heap.length; i++) {
                newHeap[i] = this.heap[i];
            }
            this.heap = newHeap;
        }
    }

    // Returns and deletes the minimum value (double) in the heap
    // Throws an EmptyHeapException if heap is empty
    public double deleteMin() {
        double found = findMin();
        this.heap[0] = this.heap[this.front];
        this.front--;
        percolateDown(0);
        return found;
    }

    // Passed a list of doubles as a parameter, this method adds each value of
    // the list into the heap. Each time this is called, the heap is emptied,
    // and the new values from list passed replace any old values
    public void buildQueue(List<Double> list) {
        makeEmpty();
        for (double x : list) {
            insert(x);
        }
    }

    // Makes the heap empty
    public void makeEmpty() {
        this.front = -1;
    }

    // After inserting a new double, this method moves the double up to restore
    // heap property
    private void percolateUp(int startIndex) {
        double temp = this.heap[startIndex];
        while (startIndex > 0 && temp < this.heap[parentOf(startIndex)]) {
            this.heap[startIndex] = this.heap[parentOf(startIndex)];
            startIndex = parentOf(startIndex);
        }
        this.heap[startIndex] = temp;
    }

    // Passed index that represents the current location in heap, returns an
    // integer of the index of the smallest child
    private int bestChild(int index) {
        if (child(index, 2) > this.front) {
            return 1;
        } else if (child(index, 3) > this.front) {
            if (this.heap[child(index, 1)] <= this.heap[child(index, 2)]) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (this.heap[child(index, 1)] <= this.heap[child(index, 2)]
                    && this.heap[child(index, 1)] <= this.heap[child(index, 3)]) {
                return 1;
            } else if (this.heap[child(index, 2)] <= this.heap[child(index, 3)]
                    && this.heap[child(index, 2)] <= this.heap[child(index, 1)]) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    // Given a index, returns the index of that node's parent
    private int parentOf(int node) {
        return (node - 1) / 3;
    }

    // Given an index and node, returns the index of that index's node'th child
    private int child(int index, int node) {
        return 3 * index + node;
    }

    // After deleting a minimum, replaces the right-most leaf to the top and
    // moves it down to maintain heap property
    private void percolateDown(int index) {
        int smallestChild = bestChild(index);
        double temp = this.heap[index];
        while (!isLeaf(index) && (this.heap[index] > this.heap[child(index, 1)]
                || this.heap[index] > this.heap[child(index, 2)] || this.heap[index] > this.heap[child(index, 3)])) {
            temp = this.heap[index];
            this.heap[index] = this.heap[child(index, smallestChild)];
            this.heap[child(index, smallestChild)] = temp;
            index = smallestChild;
            smallestChild = bestChild(index);
        }
        if (this.front == 1) {
            if (this.heap[0] > this.heap[1]) {
                temp = this.heap[0];
                this.heap[0] = this.heap[1];
                this.heap[1] = temp;
            }
        }
    }

    // Returns a boolean if the node at a given index has 3 children
    // In other words, returns whether the node is a leaf-node
    private boolean isLeaf(int index) {
        boolean isChild1Out = child(index, 1) >= this.front;
        boolean isChild2Out = child(index, 2) >= this.front;
        boolean isChild3Out = child(index, 3) >= this.front;
        return isChild3Out && isChild2Out && isChild1Out;
    }
}