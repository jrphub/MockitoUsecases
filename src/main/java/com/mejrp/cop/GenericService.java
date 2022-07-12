package com.mejrp.cop;

/**
 * 1) Constructor based injection
 * 2) Setter based injection
 * 3) Field based injection
 *
 */
public class GenericService {

    private LibraryService.DAO dao;
    private LoggerService outLogger;
    private LoggerService errLogger;

    public GenericService(LibraryService.DAO dao, LoggerService outLogger, LoggerService errLogger) {
        System.out.println("GenericService constructor called");
        this.dao = dao;
        this.outLogger = outLogger;
        this.errLogger = errLogger;
    }

    public Book fetchBookById(Integer id) {
        System.out.println("GenericService.foo");

        final Book book = dao.fetchBookById(id);
        outLogger.logToOut(book.getTitle());
        errLogger.logToErr(book.getTitle());
        return book;
    }

    public Book fetchBook(Integer id, String title) {
        System.out.println("GenericService.fetchBook");

        Book book = dao.fetchBookById(id);
        if (book==null) {
            book = dao.fetchBookByTitle(title);
        }
        outLogger.logToOut(book.getTitle());
        errLogger.logToErr(book.getTitle());
        return book;
    }
}