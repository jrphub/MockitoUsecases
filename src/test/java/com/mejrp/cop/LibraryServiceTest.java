package com.mejrp.cop;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Year;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    //Without @RunWith, @Mock annotation won't work
    @Mock
    private LibraryService.DAO dao;

    @Mock
    private LibraryService service;

    static Book DEFAULT_BOOK = new Book("Effective Java", 280, Topic.COMPUTING, Year.of(2000), "Joshua Block");

    @BeforeEach
    void setup() {
        //MockitoAnnotations.initMocks(this); //deprecated
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMethodVerification() {
        //substitute of annotation based mock
        //final LibraryService.DAO dao = mock(LibraryService.DAO.class);

        final LibraryService service = new LibraryService(dao);
        when(dao.fetchBookById(anyInt())).thenReturn(DEFAULT_BOOK);
        final boolean isBookPresent = service.hasBookWithId(42);


        verify(dao).fetchBookById(anyInt());
        verify(dao).fetchBookById(eq(42));
        //lambda
        verify(dao).fetchBookById(argThat(arg -> arg.equals(42)));

        Assertions.assertTrue(isBookPresent);
        MatcherAssert.assertThat(true, CoreMatchers.is(CoreMatchers.equalTo(isBookPresent)));
    }

    @Test
    public void testMethodVerificationWithNull() {
        final LibraryService service = new LibraryService(dao);
        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> service.hasBookWithId(null));
        MatcherAssert.assertThat(exception.getClass(), CoreMatchers.is(CoreMatchers.equalTo(IllegalArgumentException.class)));
    }

    @Test
    public void testArgMatcherAllArgs() {
        //mock service is used, so actual method is not invoked
        final String author = "Joshua Bloch";
        when(service.hasBookWithTopicAndAuthor(any(Topic.class), eq(author))).thenReturn(false);
        Assertions.assertFalse(service.hasBookWithTopicAndAuthor(Topic.COMPUTING, author));
    }

    @Test
    public void testHasBookWithTopicAndAuthor() {
        final LibraryService service = new LibraryService(dao);
        Assertions.assertTrue(service.hasBookWithTopicAndAuthor(any(Topic.class), anyString()));

    }

    @Test
    public void testArgumentMatcher() {
        when(service.hasBookWithId(argThat(isValid()))).thenReturn(true);
        Assertions.assertTrue(service.hasBookWithId(42));
        Assertions.assertFalse(service.hasBookWithId(-42));
    }

    @Test
    public void testArgumentMatcherWLambda() {
        when(service.hasBookWithId(argThat(arg -> arg != null && arg > 0))).thenReturn(true);
        Assertions.assertTrue(service.hasBookWithId(42));
        Assertions.assertFalse(service.hasBookWithId(-42));
    }

    private MyArgMatcher isValid() {
        return new MyArgMatcher();
    }

    private class MyArgMatcher implements ArgumentMatcher<Integer> {
        private Integer argument;

        @Override
        public boolean matches(Integer argument) {
            this.argument = argument;
            return argument != null && argument > 0;
        }

        @Override
        public String toString() {
            return String.format("%d must be positive integer", argument);
        }

    }

    @Test
    public void testArgumentCaptor() {
        final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        service.hasBookWithId(42);

        verify(service).hasBookWithId(captor.capture());

        MatcherAssert.assertThat(captor.getValue(), CoreMatchers.is(CoreMatchers.equalTo(42)));
    }
}