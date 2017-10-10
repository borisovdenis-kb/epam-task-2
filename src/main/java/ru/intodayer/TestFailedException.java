package ru.intodayer;


public class TestFailedException extends RuntimeException {
    public static <T> String getMessage(T expected, T actual) {
        return String.format(
                "expected [%s] but got [%s]", expected, actual
        );
    }

    public TestFailedException(String message) {
        super(message);
    }

}
