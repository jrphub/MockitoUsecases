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

import static org.mockito.Mockito.*;

/**
 * Family of methods:
 * <p>
 * 1) doReturn()
 * 2) doThrow()
 * 3) doAnswer()
 * 4) doNothing()
 * 5) doCallRealMethod()
 */

@RunWith(MockitoJUnitRunner.class)
public class GenericServiceTestDoFamily {

    @Mock
    private LibraryService.DAO dao;

    @Mock
    private LoggerService logger;

    @InjectMocks
    private GenericService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoCallRealMethod() {

        doCallRealMethod().when(dao).fetchBookById(anyInt());
        final Book book = service.fetchBookById(42);

        verify(dao).fetchBookById(anyInt());
        verify(logger).logToErr(anyString());

        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(LibraryService.DEFAULT_BOOK)));
    }

    @Test
    public void testDoNothing() {
        /*doNothing().when(dao).foo();
        dao.foo();*/

        doNothing().doThrow(RuntimeException.class).when(dao).foo();
        dao.foo();

        final RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> dao.foo());
        MatcherAssert.assertThat(exception.getClass(), CoreMatchers.is(CoreMatchers.equalTo(RuntimeException.class)));
    }

    @Test
    public void testDoThrow() {
        doThrow(RuntimeException.class).when(dao).foo();
        Assertions.assertThrows(RuntimeException.class, () -> {
            dao.foo();
        });
    }

    @Test
    public void testDoAnswer() {
        //when(dao.fetchBookById(42)).thenReturn(LibraryService.DEFAULT_BOOK);
        //or
        doAnswer( invocation -> {
            final Integer id = invocation.getArgument(0, Integer.class);
            return id == 42 ? LibraryService.DEFAULT_BOOK : null;
        }).when(dao).fetchBookById(anyInt());

        final Book book = service.fetchBookById(42);
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(LibraryService.DEFAULT_BOOK)));
    }

    @Test
    public void testDoReturn() {
        final Book book = doReturn(LibraryService.DEFAULT_BOOK).when(dao).fetchBookById(42);
        service.fetchBookById(42);

        verify(logger).logToOut(anyString());
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(book)));

    }


}
