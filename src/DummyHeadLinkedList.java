public class DummyHeadLinkedList<T> implements List<T> {
    private static class Node<T> { T v; Node<T> next; Node(){} Node(T v){ this.v = v; } }
    private final Node<T> head = new Node<>(); // dummy
    private int size;

    @Override public int size(){ return size; }

    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node<T> prev = head;
        for (int i = 0; i < index; i++) prev = prev.next;
        Node<T> n = new Node<>(element);
        n.next = prev.next;
        prev.next = n;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur = head.next;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.v;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> prev = head;
        for (int i = 0; i < index; i++) prev = prev.next;
        T val = prev.next.v;
        prev.next = prev.next.next;
        size--;
        return val;
    }
}
