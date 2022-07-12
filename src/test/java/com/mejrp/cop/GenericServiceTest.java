package com.mejrp.cop;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenericServiceTest {

    @Mock
    private LibraryService.DAO dao;

    @Mock
    private LoggerService logger;
    //or
    /*@Mock(name = "outLogger")
    private LoggerService logger1;

    @Mock(name = "errLogger")
    private LoggerService logger2;*/

    @InjectMocks
    private GenericService service;

    /**
     * InjectMocks didn't work at
     * - inner class, static inner class, abstract class or interface
     * - the field that has been injected should not be final or static
     *
     */

    @BeforeEach
    void setup() {
        //MockitoAnnotations.initMocks(this); //deprecated
        MockitoAnnotations.openMocks(this);
    }

    static Book DEFAULT_BOOK = new Book("Effective Java", 280, Topic.COMPUTING, Year.of(2000), "Joshua Block");

    @Test
    public void testFetchBookById() {
        when(dao.fetchBookById(42)).thenReturn(DEFAULT_BOOK);

        final Book book = service.fetchBookById(42);
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(LibraryService.DEFAULT_BOOK)));
        verify(logger).logToOut(anyString());
        verify(logger).logToErr(anyString());

        //verify(logger1).logToOut(anyString());
        //verify(logger2).logToErr(anyString());
    }

}