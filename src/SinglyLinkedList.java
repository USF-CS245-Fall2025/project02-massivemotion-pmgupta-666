public class SinglyLinkedList<T> implements List<T> {

    private class Node {
        T value;
        Node next;
        Node(T v) { value = v; }
    }

    private Node head;
    private int size;

    public void add(T item) {
        Node n = new Node(item);
        if (head == null) head = n;
        else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = n;
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
            size--;
            return val;
        }
        Node curr = head;
        for (int i = 0; i < index - 1; i++) curr = curr.next;
        T val = curr.next.value;
        curr.next = curr.next.next;
        size--;
        return val;
    }

    public int size() {
        return size;
    }
}
