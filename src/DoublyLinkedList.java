/*
 * A doubly linked list implementation of the List<T> interface.
 * Supports indexed insertion, removal, and bidirectional traversal.
 *
 * @param <T> the type of elements stored
 */
public class DoublyLinkedList<T> implements List<T> {

    /*
     * A node containing a value and references to both the next and previous nodes.
     */
    private class Node {
        T value;
        Node next, prev;
        Node(T v) { value = v; }
    }

    private Node head, tail;
    private int size;

    /*
     * Appends an item to the end of the list.
     *
     * @param item value to insert
     */
    @Override
    public void add(T item) {
        Node n = new Node(item);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        size++;
    }

    /*
     * Inserts an item at a specified index.
     *
     * @param index position to insert
     * @param item  value to insert
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        Node n = new Node(item);

        // Insert at the front
        if (index == 0) {
            if (head == null) {
                head = tail = n;
            } else {
                n.next = head;
                head.prev = n;
                head = n;
            }
            size++;
            return;
        }

        // Insert at the end
        if (index == size) {
            tail.next = n;
            n.prev = tail;
            tail = n;
            size++;
            return;
        }

        // Insert in the middle
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;

        Node p = curr.prev;

        p.next = n;
        n.prev = p;
        n.next = curr;
        curr.prev = n;

        size++;
    }

    /*
     * Retrieves the item at a specified index.
     * Uses bidirectional traversal for efficiency.
     *
     * @param index position to read
     * @return the value at that index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node curr;

        // Efficient traversal: head or tail depending on index
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }

        return curr.value;
    }

    /*
     * Removes and returns the item at a specified index.
     *
     * @param index position to remove
     * @return removed value
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        // Remove front
        if (index == 0) {
            T val = head.value;
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            size--;
            return val;
        }

        // Efficient traversal to index
        Node curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }

        T val = curr.value;

        if (curr.prev != null) curr.prev.next = curr.next;
        if (curr.next != null) curr.next.prev = curr.prev;
        if (curr == tail) tail = curr.prev;

        size--;
        return val;
    }

    /*
     * Returns the number of elements in the list.
     *
     * @return list size
     */
    @Override
    public int size() {
        return size;
    }
}