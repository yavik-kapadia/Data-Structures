
import java.util.Collection;

/**
 * My Own LinkedList
 * @author Yavik Kapadia
 * @version 11-16-2020
 */
interface MyList<E> extends Collection<E> {

    /**
     * Add a new element at the specified index in this list
     */
    public void add(int index, E e);

    /**
     * Return the element from this list at the specified index
     */
    public E get(int index);

    /**
     * Return the index of the first matching element in this list.
     * Return −1 if no match.
     */
    public int indexOf(Object e);

    /**
     * Return the index of the last matching element in this list
     * Return −1 if no match.
     */
    public int lastIndexOf(E e);

    /**
     * Remove the element at the specified position in this list
     * Shift any subsequent elements to the left.
     * Return the element that was removed from the list.
     */
    public E remove(int index);

    /**
     * Replace the element at the specified position in this list
     * with the specified element and returns the new set.
     */
    public E set(int index, E e);

    @Override
    /** Add a new element at the end of this list */
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    /** Return true if this list contains no elements */
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    /** Remove the first occurrence of the element e
     * from this list. Shift any subsequent elements to the left.
     * Return true if the element is removed. */
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;

        } else
            return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {

        for (Object e : c) {
            if (!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {

        boolean changed = false;
        for (E element : c) {
            if (this.add(element)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            if (this.remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        boolean changed = false;

        int index = size() - 1;

        //looping from last index to first

        while (index >= 0) {
            Object o = get(index);
            if (c.contains(o)) {
                remove(index);
                changed = true;
            }
            index--;
        }
        return changed;
    }


    @Override
    public default Object[] toArray() {
        //creating an Object array
        Object array[] = new Object[size()];
        //copying elements
        for (int i = 0; i < size(); i++) {
            array[i] = get(i);
        }
        //returning array
        return array;
    }


    @Override
    public default <T> T[] toArray(T[] array) {
        if (array.length < size()) {
            array = (T[]) new Object[size()];
        }
        for (int i = 0; i < size(); i++) {
            array[i] = (T) get(i);
        }
        return array;

    }
}

public class MyLinkedList<E> implements MyList<E> {

    private Node<E> head, tail;
    private int size = 0; // number of elements in the list

    /**
     * Constructs an empty list
     */
    public MyLinkedList() {
    }

    /**
     * Creates a list from an array of objects
     */
    public MyLinkedList(E[] objects) {
        for (E object : objects)
            add(object);
    }

    public static void main(String[] args) {
        System.out.println("My LinkedList Methods Testing: ");
        MyLinkedList<String> linkedList1 = new MyLinkedList<>();
        linkedList1.add("Albert");
        System.out.println("Added Albert to the list: " + linkedList1);
        linkedList1.add(0, "Bob");
        System.out.println("Added Bob at index 0: " + linkedList1);
        linkedList1.addFirst("Cory");
        System.out.println("Added Cory at first index: " + linkedList1);
        linkedList1.add(1, "Dorthy");
        System.out.println("Added Dorthy at index 1: " + linkedList1);
        linkedList1.addLast("Emily");
        System.out.println("Added Emily to the tail of the list: " + linkedList1);
        linkedList1.add("Fred");
        System.out.println("Added Fred to the list: " + linkedList1);
        linkedList1.add(3, "Gladys");
        System.out.println("Added Gladys at index 3: " + linkedList1);
        linkedList1.addFirst("Hilda");
        System.out.println("Added Hilda to first index: " + linkedList1);
        linkedList1.addLast("Illyr");
        System.out.println("Added Illyr to the tail of the list: " + linkedList1);
        linkedList1.add(6, "Jill");
        System.out.println("Added Jill to index 6: " + linkedList1);
        linkedList1.remove(2);
        System.out.println("Removed element at index 2: " + linkedList1);
        System.out.println("First element in list: " + linkedList1.getFirst());
        System.out.println("Last element in list: " + linkedList1.getLast());
        System.out.println("Removed last element: " + linkedList1.removeLast());
        System.out.println("Element at index 5: " + linkedList1.get(5));
        System.out.println("Index of Illyr: " + linkedList1.indexOf("Illyr"));
        System.out.println("Size of LinkedList: " + linkedList1.size());
        System.out.println("LinkedList contains Yavik: " + linkedList1.contains("Yavik"));
        System.out.println("LinkedList contains Dorthy: " + linkedList1.contains("Dorthy"));
        System.out.println("Replaced item at index "+ (linkedList1.size() - 1) +": "+ linkedList1.set(linkedList1.size() - 1, "Hilda"));
        System.out.println("Item at index 3: " + linkedList1.get(3) );
        System.out.println("Current LinkedList: "+ linkedList1);
        System.out.println("Last index of Hilda: "+ linkedList1.lastIndexOf("Hilda"));
        linkedList1.clear();
        System.out.println("Cleared List");
        System.out.println("Size: " + linkedList1.size() );




    }

    /**
     * gets the head element in the list
     *
     * @return the head element in the list
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /**
     * gets the tail element in the list
     *
     * @return the last element in the list
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /**
     * Creates a new node for holding the element e and the new node becomes the first node in the list
     *
     * @param e element being added to the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // creates a new node
        newNode.next = head; // link the new node with the head
        head = newNode; //head points to the new node
        size++;

        if (tail == null) {
            tail = head;
        }


    }

    /**
     * Creates a node to hold the element and appends the node at the end of the list
     *
     * @param e element being added to the list
     */
    public void addLast(E e) {
        if (size == 0) {
            addFirst(e);
        } else {
            Node<E> newNode = new Node<>(e);
            tail.next = newNode;
            size++;
            tail = newNode;
        }


    }

    /**
     * inserts an element into the list at the specified index
     *
     * @param index where the element is being added
     * @param e     element that is being inserted
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {

            Node<E> newNode = new Node<>(e);
            Node<E> previous = head;

            for (int i = 0; i < index; i++) {
                previous = previous.next;
            }
            newNode.next = previous.next;
            previous.next = newNode;
            size++;
        }
    }

    /**
     * removes the first element from the list
     *
     * @return the element that was removed
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
            return null;
        } else {
            E current = head.element;
            head = head.next;
            size--;
            return current;
        }
    }

    /**
     * removes last element
     *
     * @return element that was deleted
     */
    public E removeLast() {
        if(size == 0) {
            return null;
        } else if(size == 1) {
            return removeFirst();
        } else {
            Node<E> previous = head;

            while(previous.next != tail) {
                previous = previous.next;
            }
            E current = tail.element;
            previous.next = null;
            tail = previous;
            size--;
            return current;
        }
    }

    /**
     * finds the node at specified index and then removes it.
     *
     * @param index index of node to be removed
     * @return element that is removed
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;

            while (index != 1) {
                previous = previous.next;
                index--;
            }
            E current = previous.next.element;
            previous.next = previous.next.next;
            size--;
            return current;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    /**
     * clears the list
     */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    /**
     * returns true if this list contains the element e
     *
     * @param e target element that is being searched for.
     * @return true if target is in the list false is not in the list.
     */
    public boolean containsAll(Object e) {
        return indexOf(e) != -1;
    }

    /**
     * returns the element at the specified index
     *
     * @param index of the node that contains the element
     * @return specified element
     */
    public E get(int index) {
        if (head == null) {
            return null;
        } else if (index < 0 || index > size - 1)
            return null;
        else if (index == 0)
            return getFirst();
        else if (index == size - 1)
            return getLast();
        else {
            Node<E> current = head;
            for (int i = 0; i <= index; i++) {
                current = current.next;
            }
            return current.element;
        }
    }

    /**
     * returns the first index of the target element
     *
     * @param e the target element whose index is being searched for
     * @return the index of the target element else -1
     */
    public int indexOf(Object e) {
        int indexOf = -1;
        Node<E> current = head;
        if (head == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if ((current.element).equals(e)) {
                indexOf = i;
                break;
            }
            current = current.next;
        }
        return indexOf;
    }

    /**
     * Returns the index of the last matching element in this list
     *
     * @param e element whose index is being searched for
     * @return last matching index of target element else -1 if no match
     */
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int indexOf = -1;
        for (int i = 0; i < size; i++) {
            if ((current.element).equals(e)) {
                indexOf = i;
            }
            current = current.next;
        }
        return indexOf;
    }

    @Override
    public boolean remove(Object e) {
        return false;
    }

    /**
     * Replaces element at specified index with specified element
     *
     * @param index of element that is being remove
     * @param e     element that is taking place of the element at index
     * @return element that was replaced
     */
    public E set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = head;
        while(index != 0) {
            current = current.next;
            index--;
        }
        E replacer = current.element;
        current.element = e;
        return replacer;
    }

    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * gets size of this linked list
     *
     * @return size of linked list
     */
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) != -1;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private class LinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = head; // current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            if (current == head) {
                removeFirst();
                current = head;
            } else {
                Node<E> previous = head;
                while (previous.next != current) {
                    previous = previous.next;
                }
                previous.next = current.next;
                if (current == tail) {
                    tail = previous;
                }
                current = previous.next;
            }

        }

    }
}



