import java.util.Arrays;
import java.util.Collection;


interface Tree<E> extends Collection<E> {
    /**
     * Return true if the element is in the tree
     */
    boolean search(E e);

    /**
     * Insert element e into the binary tree
     * Return true if the element is inserted successfully
     */
    boolean insert(E e);

    /**
     * Delete the specified element from the tree
     * Return true if the element is deleted successfully
     */
    boolean delete(E e);

    /**
     * Get the number of elements in the tree
     */
    int getSize();

    /**
     * Inorder traversal from the root
     */
    default void inorder() {
    }

    /**
     * Postorder traversal from the root
     */
    default void postorder() {
    }

    /**
     * Preorder traversal from the root
     */
    default void preorder() {
    }

    @Override
    /** Return true if the tree is empty */
    default boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    default boolean add(E e) {
        return insert(e);
    }

    @Override
    default boolean remove(Object e) {
        return delete((E) e);
    }

    @Override
    default int size() {
        return getSize();
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E element : c) {
            if (this.add(element)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            if (this.remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (E e : this) {
            if (!c.contains(e)) {
                remove(e);
            }
        }
        return true;

    }


    @Override
    default Object[] toArray() {
        Object[] arr = new Object[this.getSize()];
        int index = 0;
        for (E e : this) {
            arr[index] = e;
            index++;
        }
        return arr;

    }

    @Override
    default <T> T[] toArray(T[] array) {
        // Left as an exercise
        array = (T[]) new Object[this.getSize()];
        int index = 0;
        for (E e : this) {
            array[index] = (T) e;
            index++;
        }
        return array;
    }
}

public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    public static void main(String[] args) {
        System.out.println("Testing BST");
        BST<String> tree1 = new BST<>();
        System.out.println("Is tree1 empty? " + tree1.isEmpty());
        tree1.insert("George");
        tree1.insert("Porter");
        tree1.insert("Jeff");
        tree1.insert("Michael");
        tree1.insert("Adam");
        tree1.insert("Tracy");
        System.out.print("Inorder (Sorted): ");
        tree1.inorder();
        System.out.print("\nPostorder: ");
        tree1.postorder();
        System.out.print("\nPreorder: ");
        tree1.preorder();
        System.out.print("\n The number of nodes is " + tree1.getSize());
        System.out.print("\nIs Peter in the tree1? " + tree1.search("Peter"));
        System.out.print("\nIs Jeff in the tree1? " + tree1.search("Jeff"));
        System.out.print("\nA path from the root to Jeff is: ");
        java.util.ArrayList<BST.TreeNode<String>> path = tree1.path("Jeff");
        for (int i = 0; path != null && i < path.size(); i++)
            System.out.print(path.get(i).element + " ");
        System.out.println();
        System.out.println("Removing Adam from tree1.");
        tree1.delete("Adam");
        System.out.print("Current State of Tree1: ");
        tree1.inorder();
        System.out.print("\nTesting Array to BST: ");
        Integer[] numbers = {2, 4, 3, 1, 8 , 5, 6 , 7};
        System.out.print("\nArray of numbers: "+ Arrays.toString(numbers));
        BST<Integer> intTree = new BST<>(numbers);
        System.out.print("\nNumbers inOrder (Sorted): ");
        intTree.inorder();
        System.out.print("\nDoes intTree contain 3? " + intTree.contains(3));
        System.out.print("\nTesting set manipulation: ");
        BST<String> tree2 =  new BST<>();
        tree2.insert("Jenny");
        tree2.insert("Jeff");
        tree2.insert("Peter");
        tree2.insert("Tom");
        tree2.insert("Adam");
        System.out.print("\nCurrent State of Tree1: ");
        tree1.inorder();
        System.out.print("\nCurrent State of Tree2: ");
        tree2.inorder();
        System.out.print("\nDoes Tree1 containsAll elements of Tree2? " + tree1.containsAll(tree2));
        System.out.print("\nTree1 addAll Tree2: ");
        tree1.addAll(tree2);
        tree1.inorder();
        System.out.print("\nTree2 retainAll Tree1: " );
        tree2.retainAll(tree1);
        tree2.inorder();
        System.out.print("\nTree1 removeAll Tree2: " );
        tree1.removeAll(tree2);
        tree1.inorder();
        System.out.print("\nCurrent State of Tree1: ");
        tree1.inorder();
        System.out.print("\nCurrent State of Tree2: ");
        tree2.inorder();
        System.out.print("\nTree1 as an Array: " + Arrays.toString(tree1.toArray()));
        System.out.print("\nTree2 Cleared");
        tree2.clear();
        System.out.print("\nTree2 as an Array: " + Arrays.toString(tree2.toArray()));

    }
    /**
     * Create a default binary tree
     */
    public BST() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override
    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                return true; // Element is found
        }

        return false;
    }

    @Override
    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    /** Inorder traversal from the root */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Returns a path from the root leading to the specified element
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else
                break;
        }

        return list; // Return an array list of nodes
    }

    @Override
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    @Override
    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    @Override
    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> {
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private final java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        /** More elements for traversing? */
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        /** Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }
}