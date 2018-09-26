/**
 * The interface for an AVL tree.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public interface AVLInterface<T extends Comparable<? super T>> {
    /**
     * Add the data as a leaf to the AVL. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    void add(T data);

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace the node with
     * the child node.
     * 3: the data has 2 children. For this assignment, use the successor to
     * replace the data you are removing, not the predecessor.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @param data data to remove from the tree
     * @return the data removed from the tree.  Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    T remove(T data);

    /**
     * Returns the data in the tree matching the parameter passed in.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data data to get in the AVL tree
     * @return the data in the tree equal to the parameter.  Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    T get(T data);

    /**
     * Returns whether or not the parameter is contained within the tree.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data data to find in the AVL tree
     * @return whether or not the parameter is contained within the tree
     */
    boolean contains(T data);

    /**
     * Get the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Retrieves the second largest data from the tree.
     *
     * @throws java.util.NoSuchElementException if there isn't enough data in
     * the tree for there to be a second largest element
     * @return the second largest data in the tree
     */
    T getSecondLargest();

    /**
     * Determines whether this tree is equal to the passed in object.
     *
     * Two trees are considered equal if they are equivalent structurally
     * with equal data being in the same locations in each (use value equality).
     *
     * Do not consider the stored heights and balance factors in your equality
     * check, only the structure and the location of the data.
     *
     * Remember, the .equals() method from the Object class takes in an Object,
     * not an AVL object. So, once you've verified that the passed in object
     * is indeed an instance of AVL, you need to cast it to type AVL. If the
     * passed in object is not an AVL, then return false. Keep in mind that if
     * it's an AVL instance, you can access the root directly; do not use
     * the getRoot() method.
     *
     * You may not use anything implementing java.util.List or equivalent to
     * store the data for later use.
     *
     * Ordinarily, you would override the hashCode method as well, but you
     * shouldn't do so for this assignment.
     *
     * @param obj the object we are checking equality with
     * @return true if the trees are equal, false otherwise
     */
    boolean equals(Object obj);

    /**
     * Clear the tree.
     */
    void clear();

    /**
     * Return the height of the root of the tree.
     * 
     * This method does not need to traverse the tree since this is an AVL.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    int height();
    
    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    AVLNode<T> getRoot();
}
