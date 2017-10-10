package ru.intodayer.customlist;

import java.util.Iterator;


public interface CustomList<T> {
    /**
     * @return true if list has no elements.
     */
    boolean isEmpty();

    /**
     * @return number of elements in the list.
     */
    int getSize();

    /**
     * @return a reference to the first node.
     */
    Node<T> getFirst();

    /**
     * @return a reference to the last node.
     */
    Node<T> getLast();

    /**
     * Adds new element to the top of the list.
     */
    void addFirst(T data);

    /**
     * Adds new element to the end of the list.
     */
    void addLast(T data);

    /**
     * Finds first Node that contains key, and add new element
     * before that Node.
     */
    void addBefore(T key, T data);

    /**
     * Deletes element from the top of the list.
     */
    Node<T> deleteFirst();

    /**
     * Deletes element from the end of the list.
     */
    Node<T> deleteLast();

    /**
     * Finds first Node that contains key, and delete it.
     */
    Node<T> delete(T key);

    /**
     * Completely clears the list.
     */
    void clear();

    /**
     * @return iterator.
     */
    Iterator<T> iterator();
}
