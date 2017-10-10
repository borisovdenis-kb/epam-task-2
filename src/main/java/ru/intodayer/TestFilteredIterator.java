package ru.intodayer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class TestFilteredIterator {
    private <T> Predicate<T> getPredicate(T... exclusion) {
        Predicate<T> filter = (x) -> {
            List<T> notAllowed = Arrays.asList(exclusion);
            return notAllowed.contains(x);
        };
        return filter;
    }

    private <T> void assertEquals(T[] expected, FilteredLinkedList<T> actual) {
        int index = 0;
        for (T x : actual) {
            if (expected == null) {
                if (x != null)
                    throw new TestFailedException(
                            TestFailedException.getMessage(null, x)
                    );
                return;
            }
            if (!x.equals(expected[index]))
                throw new TestFailedException(
                        TestFailedException.getMessage(expected[index], x)
                );
            index++;
        }
    }

    private void testCase1() {
        Predicate<String> filter = getPredicate("Haskel", "PHP", "Fortran");
        FilteredLinkedList<String> list = new FilteredLinkedList<>(filter);
        list.addLast("Fortran");
        list.addLast("Java");
        list.addLast("C++");
        list.addLast("Haskel");
        list.addLast("Python");
        list.addLast("PHP");

        String[] expected = {"Java", "C++", "Python"};
        assertEquals(expected, list);
    }

    private void testCase2() {
        Predicate<Integer> filter = getPredicate(1, 5, 100);
        FilteredLinkedList<Integer> list = new FilteredLinkedList<>(filter);
        list.addLast(1);
        list.addLast(5);
        list.addLast(100);
        list.addLast(5);
        list.addLast(1);

        assertEquals(null, list);
    }

    public void runTests() {
        testCase1();
        testCase2();
        System.out.println("All tests are passed.");
    }
}
