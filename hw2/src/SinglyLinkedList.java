/**
 * Your implementation of a circular singly linked list.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Input index is invalid, "
                    + "either negative or too large.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(data,
                    current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null.");
        }
        if (head == null) {
            head = new LinkedListNode<T>(data);
            head.setNext(head);
        } else if (size == 1) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            head.setNext(newNode);
            newNode.setNext(head);
            head = newNode;
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data,
                    head.getNext());
            head.setNext(newNode);
            T headData = head.getData();
            head.setData(newNode.getData());
            newNode.setData(headData);
        }
        size++;
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null.");
        }
        if (head == null) {
            head = new LinkedListNode<T>(data);
            head.setNext(head);
        } else if (size == 1) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            head.setNext(newNode);
            newNode.setNext(head);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data,
                    head.getNext());
            head.setNext(newNode);
            T headData = head.getData();
            head.setData(newNode.getData());
            newNode.setData(headData);
            head = newNode;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Input index is invalid, "
                    + "either negative or too large.");
        }
        if (size == 0) {
            return null;
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        LinkedListNode<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        size--;
        return data;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T data = head.getData();
            head = null;
            size = 0;
            return data;
        } else {
            T data = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return data;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T data = head.getData();
            head = null;
            size = 0;
            return data;
        }
        if (size == 2) {
            T data = head.getNext().getData();
            head.setNext(head);
            size = 1;
            return data;
        }
        LinkedListNode<T> current = head;
        for (int i = 0; i < size - 2; i++) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        current.setNext(head);
        size--;
        return data;
    }

    @Override
    public T removeLastOccurrence(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null.");
        }
        if (head == null) {
            return null;
        }
        int lastIndex = -1;
        LinkedListNode<T> justBefore = null;
        LinkedListNode<T> current = head;
        if (head.getData().equals(data)) {
            lastIndex = 0;
        }
        for (int i = 0; i < size - 1; i++) {
            if (current.getNext().getData().equals(data)) {
                lastIndex = i + 1;
                justBefore = current;
            }
            current = current.getNext();
        }
        if (lastIndex > -1) {
            if (lastIndex == 0) {
                return removeFromFront();
            } else if (lastIndex == size - 1) {
                return removeFromBack();
            } else {
                T removed = justBefore.getNext().getData();
                justBefore.setNext(justBefore.getNext().getNext());
                size--;
                return removed;
            }
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Input index is invalid, "
                    + "either negative or too large.");
        }
        if (index == 0) {
            return head.getData();
        }
        LinkedListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
