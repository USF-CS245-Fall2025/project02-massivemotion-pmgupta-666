public class SinglyLinkedList<T> implements List<T> {

    private class Node {
        T value;
        Node next;
        Node(T v) { value = v; }
    }

    private Node head;
    private int size;

    @Override
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

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        Node n = new Node(item);

        if (index == 0) {
            n.next = head;
            head = n;
            size++;
            return;
        }

        Node curr = head;
        for (int i = 0; i < index - 1; i++) curr = curr.next;

        n.next = curr.next;
        curr.next = n;
        size++;

        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    }

    @Override
    public T get(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.value;

        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    @Override
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

        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }
}
