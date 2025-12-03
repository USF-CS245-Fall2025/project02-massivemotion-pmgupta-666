public class DoublyLinkedList<T> implements List<T> {

    private class Node {
        T value;
        Node next, prev;
        Node(T v) { value = v; }
    }

    private Node head, tail;
    private int size;

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

    public void add(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        Node n = new Node(item);

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

        if (index == size) {
            tail.next = n;
            n.prev = tail;
            tail = n;
            size++;
            return;
        }

        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;

        Node p = curr.prev;

        p.next = n;
        n.prev = p;
        n.next = curr;
        curr.prev = n;

        size++;
    }


    public T get(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.value;
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    public T remove(int index) {
        if (index == 0) {
            T val = head.value;
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            size--;
            return val;
        }
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        T val = curr.value;
        if (curr.prev != null) curr.prev.next = curr.next;
        if (curr.next != null) curr.next.prev = curr.prev;
        if (curr == tail) tail = curr.prev;
        size--;
        return val;
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }
}
