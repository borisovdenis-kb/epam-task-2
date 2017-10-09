package ru.intodayer;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


public class FilteredLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private Predicate<T> condition;
    private int size;

    public FilteredLinkedList() {
    }

    public FilteredLinkedList(Predicate<T> condition) {
        this.condition = condition;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void setCondition(Predicate<T> condition) {
        this.condition = condition;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }

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

    public void clear() {
        first = null;
        last = null;
    }

    public <E> FilteredLinkedList<E> map(MapInterface<T, E> mapInterface) {
        FilteredLinkedList<E> newList = new FilteredLinkedList<>();
        Iterator<T> itr = iterator();

        while (itr.hasNext()) {
            try {
                E next = mapInterface.map(itr.next());
                newList.addLast(next);
            } catch (NoSuchElementException e) {
                return newList;
            }
        }
        return newList;
    }

    public T reduce(ReduceInterface<T> reduceIntr) {
        Iterator<T> itr = iterator();

        if (size == 1)
            return itr.next();

        T result = null;
        try {
            result = reduceIntr.reduce(itr.next(), itr.next());
            while (itr.hasNext()) {
                result = reduceIntr.reduce(result, itr.next());
            }
        } catch (NoSuchElementException e) {
            return result;
        }

        return result;
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

    private class SimpleIterator implements Iterator<T> {
        private Iterator<Node<T>> itr;

        public SimpleIterator() {
            this.itr = utilityIterator();
        }

        public boolean hasNext() {
            return itr.hasNext();
        }

        public T next() {
            return itr.next().getData();
        }
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
                curNode = itr.next();
            }
            return curNode.getData();
        }
    }

    public Iterator<T> iterator() {
        if (condition == null) {
            return new SimpleIterator();
        } else {
            return new FilteredIterator();
        }
    }

    private Iterator<Node<T>> utilityIterator() {
        return new UtilityIterator();
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