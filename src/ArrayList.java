public class ArrayList<T> implements List<T> {
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size == data.length) {
            Object[] newArr = new Object[data.length * 2];
            System.arraycopy(data, 0, newArr, 0, data.length);
            data = newArr;
        }
        data[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        size--;
        return removed;
    }

    public int size() {
        return size;
    }
}
