package ru.intodayer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class FilteredLinkedListTest {
    private void checkLinks(FilteredLinkedList<?> filteredLinkedList) {
        /* check of next links */
        try {
            for (Node<?> x = filteredLinkedList.getFirst(); x != null; x = x.next) ;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        /* check of prev links */
        try {
            for (Node<?> x = filteredLinkedList.getLast(); x != null; x = x.prev) ;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    private <T> void checkContentIsEquals(T[] expected, FilteredLinkedList<T> actual) {
        assertEquals(1, 2);
//        Iterator<T> itr = actual.iterator();
//        int i = 0;
//        while (itr.hasNext()) {
//            T tmp = itr.next();
//            if (!tmp.equals(expected[i])) {
//                assertEquals(expected[i], tmp);
//            }
//            i++;
//        }
    }


//    @Test
//    void testIterator() {
//        Predicate<Integer> conditionOne = (x) -> {
//            Integer[] array = {1, 5, 9};
//            List<Integer> notAllowed = Arrays.asList(array);
//            return notAllowed.contains(x);
//        };
//        Predicate<String> conditionTwo = (x) -> {
//            String[] array = {"PHP", "ObjectiveC", "Haskel"};
//            List<String> notAllowed = Arrays.asList(array);
//            return notAllowed.contains(x);
//        };
//        FilteredLinkedList<Integer> listOne = new FilteredLinkedList<Integer>(conditionOne);
//        FilteredLinkedList<String> listTwo = new FilteredLinkedList<String>(conditionTwo);
//
//        Integer[] expectedOne = {2, 3, 8};
//        listOne.addLast(1);
//        listOne.addLast(2);
//        listOne.addLast(3);
//        listOne.addLast(5);
//        listOne.addLast(8);
//        listOne.addLast(9);
//        checkContentIsEquals(expectedOne, listOne);
//
//        String[] expectedTwo = {"Java", "Puthon", "JS"};
//        listTwo.addLast("Java");
//        listTwo.addLast("Python");
//        listTwo.addLast("PHP");
//        listTwo.addLast("PHP");
//        listTwo.addLast("ObjectiveC");
//        listTwo.addLast("Haskel");
//        listTwo.addLast("JS");
//        listTwo.addLast("PHP");
//        checkContentIsEquals(expectedTwo, listTwo);
//    }

    @Test
    void testClear() {
        FilteredLinkedList<String> filteredLinkedList = new FilteredLinkedList<String>();
        filteredLinkedList.addFirst("A");
        filteredLinkedList.addFirst("B");
        filteredLinkedList.addFirst("C");
        filteredLinkedList.addFirst("D");
        filteredLinkedList.clear();
        assertEquals(null, filteredLinkedList.getFirst());
        assertEquals(null, filteredLinkedList.getLast());
    }

    @Test
    void testDifferentTypes() {
        /* test #1: with String */
        FilteredLinkedList<String> stringList = new FilteredLinkedList<String>();
        stringList.addFirst("A");
        stringList.addLast("B");
        stringList.addBefore("B", "C");
        assertEquals("A", stringList.getFirst().getData());

        stringList.deleteFirst();
        stringList.deleteLast();
        assertEquals("C", stringList.delete("C").getData());
        assertEquals(null, stringList.getFirst());
        assertEquals(null, stringList.getLast());

        /* test #2: with Integer */
        FilteredLinkedList<Integer> integerList = new FilteredLinkedList<Integer>();
        integerList.addFirst(1);
        integerList.addLast(2);
        integerList.addBefore(2, 3);
        assertEquals(new Integer(1), integerList.getFirst().getData());

        integerList.deleteFirst();
        integerList.deleteLast();
        assertEquals(new Integer(3), integerList.delete(3).getData());
        assertEquals(null, integerList.getFirst());
        assertEquals(null, integerList.getLast());
    }

    @Test
    void testDeleteFirst() {
        FilteredLinkedList<String> One = new FilteredLinkedList<String>();
        One.addFirst("Frodo");
        One.addFirst("Aragorn");
        One.addFirst("Gandalf");
        One.addFirst("Gimli");
        One.addFirst("Legolas");

        // test #1
        assertEquals("Legolas", One.deleteFirst().getData());
        assertEquals("Gimli", One.getFirst().getData());

        // test #2
        checkLinks(One);

        // test #3
        while (!One.isEmpty()) {
            One.deleteFirst();
        }
        assertEquals(null, One.getFirst());
        assertEquals(null, One.getLast());

        // test4: lack of NullPointerException
        FilteredLinkedList<String> Two = new FilteredLinkedList<String>();
        Two.addFirst("Frodo");
        Two.deleteFirst();
    }

    @Test
    void testDeleteLast() {
        FilteredLinkedList<String> One = new FilteredLinkedList<String>();
        One.addFirst("Frodo");
        One.addFirst("Aragorn");
        One.addFirst("Gandalf");
        One.addFirst("Gimli");
        One.addFirst("Legolas");

        // test #1
        assertEquals("Legolas", One.deleteLast().getData());
        assertEquals("Gimli", One.getLast().getData());

        // test #2
        checkLinks(One);

        // test #3
        while (!One.isEmpty()) {
            One.deleteLast();
        }
        assertEquals(null, One.getFirst());
        assertEquals(null, One.getLast());

        // test4: lack of NullPointerException
        FilteredLinkedList<String> Two = new FilteredLinkedList<String>();
        Two.addFirst("Frodo");
        Two.deleteLast();
    }

    @Test
    void testDelete() {
        FilteredLinkedList<String> filteredLinkedList = new FilteredLinkedList<String>();
        filteredLinkedList.addFirst("Thor");
        filteredLinkedList.addFirst("Hulk");
        filteredLinkedList.addFirst("Iron Man");
        filteredLinkedList.addFirst("Spider-Man");

        // test #1: delete from the "middle"
        assertEquals("Hulk", filteredLinkedList.delete("Hulk").getData());

        // test #2: checking of next/prev links
        checkLinks(filteredLinkedList);

        // test #3: delete while list is not empty
        while (!filteredLinkedList.isEmpty()) {
            filteredLinkedList.delete(filteredLinkedList.getFirst().getData());
        }
        assertEquals(null, filteredLinkedList.getFirst());
        assertEquals(null, filteredLinkedList.getLast());
    }

    @Test
    void testAddFirst() {
        FilteredLinkedList<String> listOne = new FilteredLinkedList<String>();
        listOne.addFirst("Thor");
        listOne.addFirst("Hulk");
        listOne.addFirst("Iron Man");
        listOne.addFirst("Spider-Man");
        assertEquals("Spider-man", listOne.getFirst().getData());
    }

    @Test
    void testAddBefore() {
        FilteredLinkedList<String> filteredLinkedList = new FilteredLinkedList<String>();
        filteredLinkedList.addFirst("Thor");

        // test #1: with 1 node [Thor]
        filteredLinkedList.addBefore("Thor", "Hulk");
        assertEquals("Hulk", filteredLinkedList.getFirst().getData());

        filteredLinkedList.clear();

        // test #2: adds the same element as already existed
        filteredLinkedList.addBefore("Tanos", "Tanos");
        filteredLinkedList.addBefore("Tanos", "Tanos");
        assertEquals("Tanos", filteredLinkedList.getFirst().getData());
        checkLinks(filteredLinkedList);
    }
}