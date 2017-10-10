package ru.intodayer;

import java.util.*;
import java.util.Scanner;
import java.util.function.Predicate;


public class App {
    public static void main(String[] args) {
        TestFilteredIterator test = new TestFilteredIterator();
        test.runTests();
    }
}


//public class App {
//    public static void showUserInterface() {
//        System.out.print(
//            "Now you are testing custom FilteredLinkedList.\n" +
//            "Available operations:\n" +
//            "1 - add first\n"    + "2 - add last\n" +
//            "3 - add before\n"   + "4 - delete first\n" +
//            "5 - delete last\n"  + "6 - delete\n" +
//            "7 - clear\n"        + "8 - iterator\n" +
//            "9 - map\n"          + "10 - reduce\n" +
//            "stop - break a cycle\n\n" +
//            "Choose operation: "
//        );
//    }
//
//    private static <T> void showIteratorWork(FilteredLinkedList<T> list) {
//        Iterator<T> itr = list.iterator();
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//        }
//    }
//
//    private static void addElements(FilteredLinkedList<Integer> list, boolean addLast) {
//        Scanner in = new Scanner(System.in);
//        while (true) {
//            System.out.print(": ");
//            String s = in.next();
//
//            if (s.equals("stop")) {
//                break;
//            }
//
//            if (addLast) {
//                list.addLast(Integer.parseInt(s));
//            } else {
//                list.addFirst(Integer.parseInt(s));
//            }
//        }
//    }
//
//    public static void main(String args[]) {
//        Predicate<Integer> filter = (x) -> {
//            Integer[] array = {1, 5, 9};
//            List<Integer> notAllowed = Arrays.asList(array);
//            return notAllowed.contains(x);
//        };
//        MapInterface<Integer, String> toString = new MapInterface<Integer, String>() {
//            @Override
//            public String map(Integer element) {
//                return String.valueOf(element) + "#";
//            }
//        };
//        ReduceInterface<Integer> sum = new ReduceInterface<Integer>() {
//            @Override
//            public Integer reduce(Integer x, Integer y) {
//                return x + y;
//            }
//        };
//
//        FilteredLinkedList<Integer> list = new FilteredLinkedList<>(filter);
//        Scanner in = new Scanner(System.in);
//
//        while (true) {
//            showUserInterface();
//
//            String decision = in.next();
//            if (decision.equals("stop")) break;
//
//            switch (decision) {
//                case "1":
//                    addElements(list,false);
//                    break;
//                case "2":
//                    addElements(list,true);
//                    break;
//                case "3":
//                    System.out.print(": ");
//                    Integer key = in.nextInt();
//                    System.out.print(": ");
//                    Integer data = in.nextInt();
//                    list.addBefore(key, data);
//                    break;
//                case "4":
//                    list.deleteFirst();
//                    break;
//                case "5":
//                    list.deleteLast();
//                    break;
//                case "6":
//                    System.out.print(": ");
//                    list.delete(in.nextInt());
//                    break;
//                case "7":
//                    list.clear();
//                case "8":
//                    showIteratorWork(list);
//                    break;
//                case "9":
//                    FilteredLinkedList<String> mappedList = list.map(toString);
//                    System.out.println("Mapped list: " + mappedList);
//                    break;
//                case "10":
//                    System.out.println(list.reduce(sum));
//                default:
//                    break;
//            }
//            System.out.println();
//            System.out.println(list);
//            System.out.println();
//        }
//    }
//}
