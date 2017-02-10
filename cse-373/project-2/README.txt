
Zach Willson
CSE 373 - WI17
Homework 2 - Due 1/27/17

1. I spent a lot of time testing because I had a lot of issues with my code. I used my client code to compare
   what I expected to the actual value. I checked several end cases and general use.

2. buildHeap has a runtime of O(n*log(n)), isEmpty(), size(), and findMin() are 0(1), and insert() is O(log(n)).

3. For each of the following program fragments, determine the asymptotic runtime in terms of n.
    a. O(n^2)
    b. O(n^2)
    c. O(n)

4.a] Write pseudocode for a function that calculates the largest difference between any two numbers in an array of positive integers with a runtime in Θ(n2).

    pseudocode:
        int maxIndex = 0;
        int minIndex = 0;
        for the length of array
            if the number is a max
                maxIndex = index
            if the number is a min
                minIndex = index
ffw                compare number to min
                    set smaller to min
                compare to max
                    set larget to max
        return maxIndex - minIndex
4.b] Can it be written in O(n)?
    Maybe?
4.c] Can it be written in O(1)?
    Maybe?

5.a] buildHeap has a runtime of O(n^2*log(n)) because your algorithim is not a muliplaction of size
     isEmpty(), size(), and findMin() are 0(1) the same
     and insert() is now O(n*log(n)) because it is no longer amoritzed
5.b] buildHeap has a runtime of O(n*log(n)) is the same
     isEmpty(), size(), and findMin() are 0(1) is the same
     and insert() is O(log(n)) and is the same
5.c] buildHeap has a runtime of O(n*log(n)) is the same
     isEmpty(), size(), and findMin() are 0(1) is the same
     and insert() is O(log(n)). is the same

     For b and c, the runtimes are the same because as long as the multiplation factor
     is > 1, then as the limit goes to infinity, the equations will converge on the same
     result.