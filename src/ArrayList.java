/**
 * A basic dynamic array implementation for the List interface.
 * Grows automatically when it runs out of space.
 */
public class ArrayList<T> implements List<T> {
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[10];
        size = 0;
    }

    /** {@inheritDoc} */
    public void add(T item) {
        if (size == data.length) {
            Object[] newArr = new Object[data.length * 2];
            System.arraycopy(data, 0, newArr, 0, data.length);
            data = newArr;
        }
        data[size++] = item;
    }

    
    /** {@inheritDoc} */
    public void add(int index, T item) {

        if (size == data.length) {
            Object[] newArr = new Object[data.length * 2];
            System.arraycopy(data, 0, newArr, 0, data.length);
            data = newArr;
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = item;
        size++;

        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    }


    @SuppressWarnings("unchecked")
    /** {@inheritDoc} */
    public T get(int index) {
        return (T) data[index];
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    @SuppressWarnings("unchecked")
    /** {@inheritDoc} */
    public T remove(int index) {
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        size--;
        return removed;
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }
}
