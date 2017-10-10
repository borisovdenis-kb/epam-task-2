package ru.intodayer.List;

import java.util.Iterator;


public interface List<T> {
    boolean isEmpty();
    int getSize();
    Node<T> getFirst();
    Node<T> getLast();
    void addFirst(T data);
    void addLast(T data);
    void addBefore(T key, T data);
    Node<T> deleteFirst();
    Node<T> deleteLast();
    Node<T> delete(T key);
    void clear();
    Iterator<T> iterator();
}
