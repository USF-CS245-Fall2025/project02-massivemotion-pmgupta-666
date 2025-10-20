public class DoublyLinkedList<T> implements List<T> {
    private static class Node<T> { T v; Node<T> next, prev; Node(T v){ this.v = v; } }
    private Node<T> head, tail;
    private int size;

    @Override public int size(){ return size; }

    @Override
    public boolean add(T element) {
        Node<T> n = new Node<>(element);
        if (tail == null) head = tail = n;
        else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == size) { add(element); return; }
        if (index == 0) {
            Node<T> n = new Node<>(element);
            n.next = head;
            if (head != null) head.prev = n;
            head = n;
            if (tail == null) tail = n;
            size++;
            return;
        }
        Node<T> cur = nodeAt(index);
        Node<T> n = new Node<>(element);
        n.prev = cur.prev;
        n.next = cur;
        cur.prev.next = n;
        cur.prev = n;
        size++;
    }

    @Override
    public T get(int index) { return nodeAt(index).v; }

    @Override
    public T remove(int index) {
        Node<T> cur = nodeAt(index);
        if (cur.prev != null) cur.prev.next = cur.next; else head = cur.next;
        if (cur.next != null) cur.next.prev = cur.prev; else tail = cur.prev;
        size--;
        return cur.v;
    }

    private Node<T> nodeAt(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node<T> cur;
        if (idx < (size >> 1)) {
            cur = head; for (int i = 0; i < idx; i++) cur = cur.next;
        } else {
            cur = tail; for (int i = size - 1; i > idx; i--) cur = cur.prev;
        }
        return cur;
    }
}
