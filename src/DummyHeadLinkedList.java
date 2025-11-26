public class DummyHeadLinkedList<T> implements List<T> {

    private class Node {
        T value;
        Node next;
        Node(T v) { value = v; }
        Node() {}
    }

    private Node head;
    private int size;

    public DummyHeadLinkedList() {
        head = new Node();
        size = 0;
    }

    public void add(T item) {
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = new Node(item);
        size++;
    }

    public T get(int index) {
        Node curr = head.next;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.value;
    }

    public T remove(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        T val = curr.next.value;
        curr.next = curr.next.next;
        size--;
        return val;
    }

    public int size() {
        return size;
    }
}
