import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data to add cannot be null");
        }

        for (T toAdd: data) {
            add(toAdd);
        }
    }

    @Override
    public void add(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data to add cannot be null");
        }

    }

    @Override
    public T remove(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data to remove cannot be null");
        }
    }

    @Override
    public T getSecondLargest() throws NoSuchElementException {
        if (size < 2) {
            throw new NoSuchElementException(
                    "Tree is too small for 2nd largest");
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AVL)) {
            return false;
        }

        AVL<T> avl = (AVL<T>) obj;

        return equals(root, avl.root);
    }

    /**
     * Given two nodes, one from this AVL and one from the input,
     * compares their values/if both are null, and does the same
     * for their children to confirm the AVLs have the same shape.
     *
     * @param currNode the node from this AVL
     * @param otherNode the node to compare to in the input AVL
     * @return Whether or not the nodes are equal in value and children
     */
    private boolean equals(AVLNode<T> currNode, AVLNode<T> otherNode) {
        if (currNode == null || otherNode == null) {
            return otherNode == currNode;
        }

        return currNode.getData().equals(otherNode.getData())
                && equals(currNode.getLeft(), otherNode.getLeft())
                && equals(currNode.getRight(), otherNode.getRight());
    }

    @Override
    public T get(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data to get cannot be null");
        }

        T foundData = get(data, root);

        if (foundData == null) {
            throw new NoSuchElementException("Input data is not in the tree");
        }

        return foundData;
    }

    /**
     * Gets the input data if it is in the tree, starting from the input node,
     * and moving down the tree via comparisons. If the node is not found,
     * null is returned.
     *
     * @param data the data to find in the tree.
     * @param node the node to compare the data to and continue the search
     * @return The data of the found node
     */
    private T get(T data, AVLNode<T> node) {
        if (node == null) {
            return null;
        }

        int comparison = node.getData().compareTo(data);

        if (comparison == 0) {
            return node.getData();
        }

        if (comparison < 0) {
            return get(data, node.getRight());
        }

        return get(data, node.getLeft());
    }

    @Override
    public boolean contains(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data to find cannot be null");
        }
        T foundData = get(data, root);
        return foundData != null;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public AVLNode<T> getRoot() {
        return root;
    }
}