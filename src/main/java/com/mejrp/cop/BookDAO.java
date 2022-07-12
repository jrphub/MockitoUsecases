package com.mejrp.cop;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Parses each line of a csv file containing a list of books onto a Book object and runs
 * a simple query against it.
 */
public class BookDAO {

    static Path DEFAULT_PATH = Paths.get("src/main/resources/book.csv");
    private List<Book> library;
    private final Utils utils = new Utils();

    public List<Book> getSortedComputingBooksByTitle() {
        return library.stream()
                .filter(b -> b.getTopic() == Topic.COMPUTING)
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public Map<Topic, List<Book>> booksByTopic() {
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic));
    }

    public Map<Topic, Long> bookCountByTopic() {
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.counting()));
    }

    public Optional<Topic> mostPopularTopic() {
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    private void parseLibraryFrom(Path path) {
        this.library = utils.parseLibraryFrom(path);
    }

    public List<Book> getLibrary() {
        return Collections.unmodifiableList(library);
    }

    public static void main(String[] args) {
        final BookDAO bookDAO = new BookDAO();
        bookDAO.parseLibraryFrom(DEFAULT_PATH);
    }

    public List<String> getBookTitles() {
        return library.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }
}