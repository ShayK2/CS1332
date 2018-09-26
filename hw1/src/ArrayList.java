/**
 * Your implementation of an ArrayList.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) throws IllegalArgumentException,
                                                     IndexOutOfBoundsException {
        if (data == null) {
            throw new IllegalArgumentException("Data input cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not compatible"
                + " with current list size (negative or too large)");
        }
        if (index == size) {
            this.addToBack(data);
        } else {
            if (size == backingArray.length) {
                T[] newArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < index; i++) {
                    newArray[i] = backingArray[i];
                }
                newArray[index] = data;
                for (int j = index + 1; j < size + 1; j++) {
                    newArray[j] = backingArray[j - 1];
                }
                backingArray = newArray;
            } else {
                for (int i = size; i > index; i--) {
                    backingArray[i] = backingArray[i - 1];
                }
                backingArray[index] = data;
            }
            size++;
        }
    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        this.addAtIndex(0, data);
    }

    @Override
    public void addToBack(T data)  throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data input cannot be null");
        }
        if (size < backingArray.length) {
            backingArray[size++] = data;
        } else {
            T[] newArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
            backingArray[size++] = data;
        }
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not compatible"
                    + " with current list size (negative or too large)");
        }

        if (index == size - 1) {
            return this.removeFromBack();
        }

        T data = (T) backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[--size] = null;
        return data;
    }

    @Override
    public T removeFromFront() {
        return this.removeAtIndex(0);
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T data = backingArray[--size];
        backingArray[size] = null;
        return data;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not compatible"
                + " with current list size (negative or too large)");
        }
        return (T) backingArray[index];
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
    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
