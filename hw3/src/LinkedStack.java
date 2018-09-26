import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The stack is empty");
        }
        T data = head.getData();
        head = head.getNext();
        size--;
        return data;
    }

    @Override
    public void push(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data input cannot be null");
        }
        if (size == 0) {
            head = new LinkedNode<T>(data);
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data);
            newNode.setNext(head);
            head = newNode;
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
     * Returns the head of this stack.
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
}