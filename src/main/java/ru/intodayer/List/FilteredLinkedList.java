package ru.intodayer.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


public class FilteredLinkedList<T> implements List<T>, Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private Predicate<T> condition;
    private int size;

    public FilteredLinkedList(Predicate<T> condition) {
        if (condition == null)
            throw new IllegalArgumentException("Predicate can't be null.");

        this.condition = condition;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Node<T> getFirst() {
        return first;
    }

    @Override
    public Node<T> getLast() {
        return last;
    }

    @Override
    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data);
        if (first == null) {
            last = newNode;
            first = newNode;
            newNode.next = null;
            newNode.prev = null;
        } else {
            first.prev = newNode;
            newNode.next = first;
            newNode.prev = null;
            first = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T data) {
        Node<T> newNode = new Node<T>(data);
        if (last == null) {
            last = newNode;
            first = newNode;
            newNode.next = null;
            newNode.prev = null;
        } else {
            last.next = newNode;
            newNode.prev = last;
            newNode.next = null;
            last = newNode;
        }
        size++;
    }

    @Override
    public void addBefore(T key, T data) {
        Node<T> newNode = new Node<T>(data);
        Node<T> current = first;
        while (current != null && !current.getData().equals(key)) {
            current = current.next;
        }
        if (current == null) {
            addLast(data);
        } else if (current.prev == null) {
            addFirst(data);
        } else {
            current.prev.next = newNode;
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev = newNode;
        }
        size++;
    }

    @Override
    public Node<T> deleteFirst() {
        Node<T> tmp = first;
        if (last == first) {
            last = null;
            first = null;
            return tmp;
        }
        first = first.next;
        first.prev = null;
        size--;
        return tmp;
    }

    @Override
    public Node<T> deleteLast() {
        Node<T> tmp = last;
        if (last == first) {
            last = null;
            first = null;
            return tmp;
        }
        last = last.prev;
        last.next = null;
        size--;
        return tmp;
    }

    @Override
    public Node<T> delete(T key) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (x.getData().equals(key)) {
                if (x == first) {
                    return deleteFirst();
                } else if (x == last) {
                    return deleteLast();
                } else {
                    x.prev.next = x.next;
                    x.next.prev = x.prev;
                }
                size--;
                return x;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
    }

    /**
     * Is needed inside FilteredIterator. And SimpleIterator.
     */
    private class UtilityIterator implements Iterator<Node<T>> {
        private Node<T> current;

        public UtilityIterator() {
            this.current = getFirst();
        }

        public boolean hasNext() {
            return !(current == null);
        }

        public Node<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> tmp = current;
            current = current.next;
            return tmp;
        }
    }

    private Iterator<Node<T>> utilityIterator() {
        return new UtilityIterator();
    }

    private class FilteredIterator implements Iterator<T> {
        private Iterator<Node<T>> itr;
        private Node<T> curNode;

        public FilteredIterator() {
            this.itr = utilityIterator();
        }

        public boolean hasNext() {
            if (curNode != null) {
                Node<T> tmp = curNode.next;
                while (tmp != null && condition.test(tmp.getData())) {
                    tmp = tmp.next;
                }
                return !(tmp == null);
            }
            return itr.hasNext();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            curNode = itr.next();
            while (condition.test(curNode.getData())) {
                try {
                    curNode = itr.next();
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
            return curNode.getData();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredIterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Node<T> x = first; x != null; x = x.next) {
            stringBuilder.append("[" + x.getData().toString() + "]");
            stringBuilder.append(x.next != null ? "-" : "");
        }
        return stringBuilder.toString();
    }
}