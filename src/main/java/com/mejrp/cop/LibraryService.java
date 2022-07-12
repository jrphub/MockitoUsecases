package com.mejrp.cop;

import java.time.Year;

public class LibraryService {

    static Book DEFAULT_BOOK = new Book("Effective Java", 280, Topic.COMPUTING, Year.of(2000), "Joshua Block");
    private final DAO dao;


    public LibraryService(DAO dao) {
        this.dao = dao;
    }

    public boolean hasBookWithId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must be a positive integer");
        }
        return this.dao.fetchBookById(id) != null;
    }


    public boolean hasBookWithTopicAndAuthor(Topic topic, String author) {
        System.out.println("LibraryService.hasBookWithTopicAndAuthor");
        return true;
    }

    public static class DAO {

        private int id;

        final Book fetchBookById(Integer id) {
            System.out.println("DAO.fetchBookById");
            return DEFAULT_BOOK;
        }

        Book fetchBookByTitle(String title) {
            System.out.println("DAO.fetchBookByTitle");
            return DEFAULT_BOOK;
        }

        public void foo() {
            System.out.println("DAO.foo");
        }

        public void addId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

}