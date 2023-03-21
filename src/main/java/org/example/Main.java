package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        // STREAMS WITH NUMBERS

        int[] array = {1, 3, 10, 5, 6, 8, 2, 9};
        Arrays.stream(array).forEach(System.out::println); // makes a for loop

        int sum_array = Arrays.stream(array).sum();
        System.out.println("The sum of array is: " + sum_array);

        IntStream.range(1, 6).forEach(System.out::println);

        IntStream.range(0, 5).forEach(u -> System.out.print(u + " "));

        // .filter() -> filters out the values to the specific condition
        IntStream.range(7, 13).filter(x -> x < 12).forEach(u -> System.out.print(u + " "));
        System.out.println();
        IntStream.range(1, 13).filter(x -> x % 2 == 0).forEach(u -> System.out.print(u + " "));
        System.out.println("\n");

        // STREAMS WITH STRINGS

        String[] names = {"Jenny", "Margarethe", "Ally", "Daniel", "Brad", "Martha", "Susan", "Kevin", "Zack"};

        // Stream.of(names).forEach(System.out::println); is ok too
        Stream.of(names).sorted().forEach(System.out::println);

        System.out.println();

        Stream.of(names).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.println();

        Stream.of(names).filter(name -> name.startsWith("M")).forEach(u -> System.out.println(
                "Name starts with M: " + u
        ));

        // ===

        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));
        books.add(new Book("The Plague", "Albert Camus", 308, Type.PHILOSOPHY));


        List<String> books_list = books.stream().filter(book -> book.getType() == Type.PHILOSOPHY)
                .sorted(Comparator.comparing(Book::getTitle)) // sort by title
                .map(Book::getTitle) // get only authors' names
                .collect(Collectors.toList());
        // System.out.println("\n" + Arrays.toString(books_list.toArray()));
        // books_list.stream().forEach(System.out::println);

        // grouping by type
        Map<Type, List<Book>> booksByType = books.stream()
                .collect(Collectors.groupingBy(Book::getType));
        // booksByType.entrySet().stream().forEach(e -> System.out.println(e));

        // finding 2 longest books (by number of pages)
        List<String> longestBooks = books.stream()
                .filter(p -> p.getPages() > 500)
                .map(Book::getTitle)
                .limit(2)
                .collect(Collectors.toList());
        longestBooks.stream().forEach(System.out::println);

        // iteration
        List<String> titles = books.stream().
                map(Book::getTitle)
                .collect(Collectors.toList());

        // titles.forEach(e -> System.out.println(e));

        System.out.println();

        // .map() and .flatMap() methods
        // they both are very similar to selecting a column in SQL
        List<String> words = Arrays.asList("Adam", "Ana", "Daniel");
        List<Integer> length = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        length.stream().forEach(System.out::println);


        // .flatMap() basically lets us to handle multiple streams -> both hello and shell
        String[] word_array = {"hello", "shell"};
        List<String> unique = Arrays.stream(word_array)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        unique.stream().forEach(System.out::println);
    }
}