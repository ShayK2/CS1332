import java.util.NoSuchElementException;

/**
 * Your implementation of a linked queue. It should NOT be circular.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        T data = head.getData();
        head = head.getNext();
        if (size == 1) {
            tail = null;
        }
        size--;
        return data;
    }

    @Override
    public void enqueue(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data input cannot be null");
        }
        if (size == 0) {
            head = new LinkedNode<T>(data);
            tail = head;
        } else {
            tail.setNext(new LinkedNode<T>(data));
            tail = tail.getNext();
        }
        if (size == 1) {
            head.setNext(tail);
        }
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return head.getData();
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}