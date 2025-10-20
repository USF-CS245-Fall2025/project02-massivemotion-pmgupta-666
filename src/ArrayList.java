public class ArrayList<T> implements List<T> {
    private Object[] data = new Object[10];
    private int size = 0;

    @Override
    public int size() { return size; }

    @Override
    public boolean add(T element) {
        ensure(size + 1);
        data[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensure(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    @Override @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T old = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return old;
    }

    private void ensure(int minCap) {
        if (minCap <= data.length) return;
        int newCap = Math.max(minCap, data.length * 2);
        Object[] n = new Object[newCap];
        System.arraycopy(data, 0, n, 0, size);
        data = n;
    }
}
