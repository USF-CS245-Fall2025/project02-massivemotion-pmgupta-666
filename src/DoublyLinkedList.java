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

    public T get(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.value;
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
    }

    public int size() {
        return size;
    }
}
