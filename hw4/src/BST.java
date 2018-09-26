import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementation of a binary search tree.
 *
 * @author Akshay Karthik
 * @userid akarthik3
 * @GTID 903212846
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection, in the same
     * order as the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Input data set cannot be null");
        }

        for (T contents: data) {
            add(contents);
        }
        size = data.size();
    }

    @Override
    public void add(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data to add cannot be null");
        }
        root = add(data, root);
    }

    /**
     * Adds the input data to the tree, basing recursive calls on how the
     * data compares to the input node. If a null node is reached, the
     * input is added as a new leaf. The updated node is returned as output.
     *
     * @param data the data to search for in the tree.
     * @param node the node to compare the data to.
     * @return The input node, updated with new children as needed.
     */
    private BSTNode<T> add(T data, BSTNode<T> node) {
        if (node == null) {
            node = new BSTNode<T>(data);
            size++;
            return node;
        }

        int comparison = node.getData().compareTo(data);

        if (comparison < 0) {
            node.setRight(add(data, node.getRight()));
            return node;
        }

        if (comparison > 0) {
            node.setLeft(add(data, node.getLeft()));
            return node;
        }

        return node;
    }

    @Override
    public T remove(T data) throws IllegalArgumentException,
        NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException(
                    "Data to remove cannot be null");
        }

        return remove(data, root, null, false);
    }

    /**
     * Removes a node from the BST recursively, by descending the tree
     * based on how the node compares to the current node. If the node
     * to remove has no children, it is removed. If it has one child,
     * that is made the parent's new child. If it has two children,
     * the predecessor is found and placed into the current node's position.
     *
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove in the tree.
     * @param current the node to compare the data to and continue the search
     * @param parent the parent of the current node
     * @param rightChild whether or not current is the right child of parent
     * @return The data of the removed node
     */
    private T remove(T data, BSTNode<T> current, BSTNode<T> parent,
                     boolean rightChild) throws NoSuchElementException {
        if (current == null) {
            throw new NoSuchElementException(
                "Data to remove is not in the tree");
        }

        int comparison = current.getData().compareTo(data);

        if (comparison < 0) {
            return remove(data, current.getRight(), current, true);
        }

        if (comparison > 0) {
            return remove(data, current.getLeft(), current, false);
        }

        T removed = current.getData();
        size--;

        if (current.getRight() == null && current.getLeft() == null) {
            if (size == 0) {
                root = null;
            } else {
                if (rightChild) {
                    parent.setRight(null);
                } else {
                    parent.setLeft(null);
                }
            }
            return removed;
        }

        if (current.getRight() == null && current.getLeft() != null) {
            if (parent == null) {
                root = current.getLeft();
            } else if (rightChild) {
                parent.setRight(current.getLeft());
            } else {
                parent.setLeft(current.getLeft());
            }
            return removed;
        }

        if (current.getRight() != null && current.getLeft() == null) {
            if (parent == null) {
                root = current.getRight();
            } else if (rightChild) {
                parent.setRight(current.getRight());
            } else {
                parent.setLeft(current.getRight());
            }
            return removed;
        }

        BSTNode<T> predecessor = current.getLeft();
        BSTNode<T> predecessorParent = current;
        while (predecessor.getRight() != null) {
            predecessorParent = predecessor;
            predecessor = predecessor.getRight();
        }

        if (predecessorParent.getData().compareTo(current.getData()) == 0) {
            current.setLeft(null);
        } else {
            predecessorParent.setRight(predecessor.getLeft());
        }

        current.setData(predecessor.getData());
        return removed;
    }

    @Override
    public T get(T data) throws IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Data to find cannot be null");
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
    private T get(T data, BSTNode<T> node) {
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
    public List<T> preorder() {
        return preorder(root);
    }

    /**
     * Returns an ArrayList containing a preorder traversal (via
     * recursion) of the BST. The parent is added, followed by its
     * left subtree and then the right.
     *
     * @param node the root of the subtree that will be traversed
     * @return A preorder traversal of the subtree
     */
    private List<T> preorder(BSTNode<T> node) {
        if (node == null) {
            return new ArrayList<T>();
        }

        List<T> subtree = new ArrayList<T>();
        subtree.add(node.getData());
        subtree.addAll(preorder(node.getLeft()));
        subtree.addAll(preorder(node.getRight()));
        return subtree;
    }

    @Override
    public List<T> postorder() {
        return postorder(root);
    }

    /**
     * Returns an ArrayList containing a postorder traversal (via
     * recursion) of the BST. The left subtree is added, followed
     * by the right subtree and then the parent.
     *
     * @param node the root of the subtree that will be traversed
     * @return A postorder traversal of the subtree
     */
    private List<T> postorder(BSTNode<T> node) {
        if (node == null) {
            return new ArrayList<T>();
        }

        List<T> subtree = new ArrayList<T>();
        subtree.addAll(postorder(node.getLeft()));
        subtree.addAll(postorder(node.getRight()));
        subtree.add(node.getData());
        return subtree;
    }

    @Override
    public List<T> inorder() {
        return inorder(root);
    }

    /**
     * Returns an ArrayList containing an inorder traversal (via
     * recursion) of the BST. The parent is added in between its
     * left and right subtrees.
     *
     * @param node the root of the subtree that will be traversed
     * @return An inorder traversal of the subtree
     */
    private List<T> inorder(BSTNode<T> node) {
        if (node == null) {
            return new ArrayList<T>();
        }

        List<T> subtree = new ArrayList<T>();
        subtree.addAll(inorder(node.getLeft()));
        subtree.add(node.getData());
        subtree.addAll(inorder(node.getRight()));
        return subtree;
    }

    @Override
    public List<T> levelorder() {
        List<T> levelorder = new ArrayList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> toAdd = queue.remove();
            if (toAdd != null) {
                levelorder.add(toAdd.getData());
                queue.add(toAdd.getLeft());
                queue.add(toAdd.getRight());
            }
        }

        return levelorder;
    }

    @Override
    public int distanceBetween(T data1, T data2)
            throws IllegalArgumentException, NoSuchElementException {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data input cannot be null");
        }
        return distanceBetween(data1, data2, root);
    }

    /**
     * Returns the distance between the two nodes, after determining if
     * a recursive step must be taken (if the input node is not the lowest
     * common ancestor). Cases for how the two input data relate to the
     * ancestor's data include: both less, both greater, one or both equal,
     * and one less and one greater.
     *
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data1 the first value for distance calculation
     * @param data2 the second value for distance calculation
     * @param ancestor the node to test as the common ancestor for the data
     * @return The distance between the two nodes
     */
    private int distanceBetween(T data1, T data2, BSTNode<T> ancestor)
            throws NoSuchElementException {
        if (ancestor == null) {
            throw new NoSuchElementException(
                "At least one input data is not in the tree");
        }

        int compare1 = ancestor.getData().compareTo(data1);
        int compare2 = ancestor.getData().compareTo(data2);

        if (compare1 < 0 && compare2 < 0) {
            return distanceBetween(data1, data2, ancestor.getRight());
        }

        if (compare1 > 0 && compare2 > 0) {
            return distanceBetween(data1, data2, ancestor.getLeft());
        }

        if (compare1 == 0) {
            return distanceBetween(ancestor, data2);
        }

        if (compare2 == 0) {
            return distanceBetween(ancestor, data1);
        }

        return distanceBetween(ancestor, data1)
            + distanceBetween(ancestor, data2);
    }

    /**
     * Returns the distance between a node and a data value, descending
     * from the node recursively until the data value is found. This is
     * only called from the three-argument distanceBetween method, and
     * only when a specific node is determined as the lowest common
     * ancestor of the two input data.
     *
     * @throws java.util.NoSuchElementException if the data is not found
     * @param ancestor the first value for distance calculation
     * @param descendant the second value for distance calculation
     * @return The distance between the ancestor node and the input data
     */
    private int distanceBetween(BSTNode<T> ancestor, T descendant)
            throws NoSuchElementException {
        if (ancestor == null) {
            throw new NoSuchElementException(
                "One input data is not in the tree");
        }

        int comparison = ancestor.getData().compareTo(descendant);

        if (comparison > 0) {
            return 1 + distanceBetween(ancestor.getLeft(), descendant);
        }

        if (comparison < 0) {
            return 1 + distanceBetween(ancestor.getRight(), descendant);
        }

        return 0;

    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of a given node by moving down the BST until a null
     * node is reached. The height is the maximum possible distance to
     * a leaf, so the max of the two subtree heights is used.
     *
     * @param node the node whose height will be calculated
     * @return The height of the input node
     */
    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        return root;
    }
}
