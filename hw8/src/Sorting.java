import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator)
            throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Input array and comparator cannot be null.");
        }

        boolean swapped = true;
        for (int j = 0; j < (arr.length - 1) && swapped; j++) {
            swapped = false;
            for (int i = 0; i < (arr.length - j - 1); i++) {

                // Move through the array, switching out-of-order values
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator)
            throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Input array and comparator cannot be null.");
        }

        boolean swapped;
        for (int i = 1; i < arr.length; i++) {
            swapped = true;
            for (int j = i - 1; j > -1 && swapped; j--) {

                // Keep sliding element back as long as it is smaller
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else {
                    swapped = false;
                }
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator)
            throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Input array and comparator cannot be null.");
        }

        for (int j = 0; j < arr.length; j++) {
            int smallestInd = j;

            // Find index of smallest element, move it to the front
            for (int i = j + 1; i < arr.length; i++) {
                if (comparator.compare(arr[i], arr[smallestInd]) < 0) {
                    smallestInd = i;
                }
            }
            T temp = arr[smallestInd];
            arr[smallestInd] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
            Random rand) throws IllegalArgumentException {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException(
                    "Input array, random and comparator cannot be null.");
        }

        quickSort(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Given a subsection of an array, quicksorts that region by placing
     * pointers at the end and near the front. The pointers move through
     * the subsection, switching values that are out of place with relation
     * to a randomly chosen pivot. The pivot is switched to the location
     * of the right pointer, and each ~half of the array is quicksorted.
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param start the first index of the subsection to be quicksorted
     * @param end the last index of the subsection to be quicksorted
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
            Random rand, int start, int end) {

        if (start >= end) {
            return;
        }

        int pivotIndex = rand.nextInt(end - start) + start;
        T temp = arr[pivotIndex];
        arr[pivotIndex] = arr[start];
        arr[start] = temp;

        int front = start + 1;
        int back = end;

        while (front <= back) {
            while (front <= end && front <= back
                    && comparator.compare(arr[front], arr[start]) <= 0) {
                front++;
            }

            while (back > start && front <= back
                    && comparator.compare(arr[back], arr[start]) > 0) {
                back--;
            }

            if (front <= back) {
                temp = arr[front];
                arr[front] = arr[back];
                arr[back] = temp;
                front++;
                back--;
            }
        }

        temp = arr[start];
        arr[start] = arr[back];
        arr[back] = temp;

        quickSort(arr, comparator, rand, start, back - 1);
        quickSort(arr, comparator, rand, back + 1, end);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator)
            throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Input array and comparator cannot be null.");
        }

        if (arr.length < 2) {
            return;
        }

        // Cut array in half

        T[] leftArr = (T[]) new Object[arr.length / 2];
        T[] rightArr = (T[]) new Object[arr.length - arr.length / 2];

        for (int i = 0; i < arr.length / 2; i++) {
            leftArr[i] = arr[i];
        }

        for (int j = arr.length / 2; j < arr.length; j++) {
            rightArr[j - arr.length / 2] = arr[j];
        }

        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);

        int left = 0;
        int right = 0;
        int curr = 0;

        // Compare elements from left and right arrays, putting the
        // smaller one back into the original array

        while (left < arr.length / 2 && right < arr.length - arr.length / 2) {
            if (comparator.compare(leftArr[left], rightArr[right]) <= 0) {
                arr[curr] = leftArr[left];
                left++;
            } else {
                arr[curr] = rightArr[right];
                right++;
            }
            curr++;
        }

        // Put any leftover values from one array into the original

        while (left < arr.length / 2) {
            arr[curr] = leftArr[left];
            left++;
            curr++;
        }

        while (right < arr.length - arr.length / 2) {
            arr[curr] = rightArr[right];
            right++;
            curr++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) throws IllegalArgumentException {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        if (arr.length < 2) {
            return;
        }

        // 19 buckets for 9 negative digits, 0, 9 positive digits
        LinkedList<Integer>[] buckets =
                (LinkedList<Integer>[]) new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = (LinkedList<Integer>) new LinkedList();
        }

        // Find number of digits in the largest number

        long max = Math.abs((long) arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs((long) arr[i]) > max) {
                max = Math.abs((long) arr[i]);
            }
        }

        int iterations = 0;
        while (max != 0) {
            iterations++;
            max = max / 10;
        }

        // Sort into buckets, then reassemble the array
        // for units digit, tens, etc.

        int divisor = 1;
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / divisor;

                int digit = num - 10 * (num / 10) + 9;
                buckets[digit].add(arr[j]);
            }
            divisor *= 10;

            int bucketNum = 0;
            for (int j = 0; j < arr.length; j++) {
                if (!buckets[bucketNum].isEmpty()) {
                    arr[j] = buckets[bucketNum].remove();
                } else {
                    bucketNum++;
                    j--;
                }
            }
        }
    }
}
