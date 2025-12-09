/*
 * A simple list interface with the basic operations we need
 * for the Massive Motion project.
 *
 * @param <T> type of elements stored in the list
 */
public interface List<T> {

    /*
     * Adds an element to the end of the list.
     *
     * @param item element to add
     */
    void add(T item);

    /*
     * Inserts an element at a specific position in the list.
     * Shifts elements to the right when needed.
     *
     * @param index position to insert at (0..size)
     * @param item element to add
     * @throws IndexOutOfBoundsException if index is outside valid range
     */
    void add(int index, T item);

    /*
     * Returns the element at the given index.
     *
     * @param index element position
     * @return the element stored at that index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    T get(int index);

    /*
     * Removes and returns the element at the given index.
     *
     * @param index element position
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    T remove(int index);

    /*
     * Returns how many elements are currently in the list.
     *
     * @return list size
     */
    int size();
}
