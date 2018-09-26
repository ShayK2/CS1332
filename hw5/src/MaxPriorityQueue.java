import java.util.NoSuchElementException;

/**
 * Your implementation of a max priority queue.
 * 
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;

    /**
     * Creates a priority queue.
     */
    public MaxPriorityQueue() {
        backingHeap = new MaxHeap<T>();
    }

    @Override
    public void enqueue(T item) throws IllegalArgumentException {
        backingHeap.add(item);
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        return backingHeap.remove();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    @Override
    public boolean isEmpty() {
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        return backingHeap.size();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        return backingHeap;
    }
}
