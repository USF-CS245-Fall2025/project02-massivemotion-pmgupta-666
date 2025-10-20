public class SinglyLinkedList<T> implements List<T> {
    private static class Node<T> { T v; Node<T> next; Node(T v){ this.v = v; } }
    private Node<T> head;
    private int size;

    @Override public int size(){ return size; }

    @Override
    public boolean add(T element) {
        Node<T> n = new Node<>(element);
        if (head == null) head = n;
        else {
            Node<T> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            Node<T> n = new Node<>(element);
            n.next = head;
            head = n;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.next;
            Node<T> n = new Node<>(element);
            n.next = prev.next;
            prev.next = n;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.v;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T val;
        if (index == 0) {
            val = head.v;
            head = head.next;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.next;
            val = prev.next.v;
            prev.next = prev.next.next;
        }
        size--;
        return val;
    }
}
