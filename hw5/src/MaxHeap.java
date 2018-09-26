import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Input data set cannot be null");
        }

        backingArray = (T[]) new Comparable[2 * data.size() + 1];

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Input data cannot be null");
            }
            backingArray[i + 1] = data.get(i);
        }

        size = data.size();

        for (int j = size / 2; j > 0; j--) {
            downHeap(j);
        }
    }

    @Override
    public void add(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Data to add cannot be null");
        }

        if (backingArray.length == size + 1) {
            T[] newArr = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < backingArray.length; i++) {
                newArr[i] = backingArray[i];
            }

            backingArray = newArr;
        }

        backingArray[++size] = item;

        upHeap(size);
    }

    /**
     * Starting from a given index, compares it to its parent and switches if
     * necessary, moving upwards until no swaps are made or the root is reached.
     *
     * @param curr the index that will be moved up the heap as much as possible
     */
    private void upHeap(int curr) {
        boolean swapped = true;

        while (curr / 2 > 0 && swapped) {
            if (backingArray[curr].compareTo(backingArray[curr / 2]) > 0) {
                T temp = backingArray[curr];
                backingArray[curr] = backingArray[curr / 2];
                backingArray[curr / 2] = temp;
            } else {
                swapped = false;
            }

            curr = curr / 2;
        }
    }

    /**
     * Starting from a given index, compares it to its children and switches if
     * necessary, continuing down the heap until no switch is made or a leaf is
     * reached.
     *
     * @param curr the index that will be moved down as much as possible
     */
    private void downHeap(int curr) {
        boolean swapped = true;
        int leftComp;
        int rightComp;

        // Until a node without children is reached or no swap is made
        while (curr <= size / 2 && swapped) {
            if (backingArray[curr * 2] == null) {
                swapped = false;
            } else  {
                leftComp = backingArray[curr].compareTo(backingArray[curr * 2]);

                // If no right child
                if (backingArray[curr * 2 + 1] == null) {
                    if (leftComp < 0) {
                        T temp = backingArray[curr];
                        backingArray[curr] = backingArray[curr * 2];
                        backingArray[curr * 2] = temp;
                        curr = curr * 2;
                    } else {
                        swapped = false;
                    }
                } else {
                    rightComp = backingArray[curr].
                            compareTo(backingArray[curr * 2 + 1]);

                    // If either child is greater than the parent
                    if (leftComp < 0 || rightComp < 0) {
                        int childComp = backingArray[curr * 2].
                                compareTo(backingArray[curr * 2 + 1]);
                        // If left child is smaller
                        if (childComp < 0) {
                            T temp = backingArray[curr];
                            backingArray[curr] = backingArray[curr * 2 + 1];
                            backingArray[curr * 2 + 1] = temp;
                            curr = curr * 2 + 1;
                        } else {
                            // If right child is smaller
                            T temp = backingArray[curr];
                            backingArray[curr] = backingArray[curr * 2];
                            backingArray[curr * 2] = temp;
                            curr = curr * 2;
                        }
                    } else {
                        swapped = false;
                    }
                }
            }
        }
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T removed = backingArray[1];

        backingArray[1] = backingArray[size];
        backingArray[size] = null;

        downHeap(1);
        size--;

        return removed;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Comparable[] getBackingArray() {
        return backingArray;
    }
}
