package com.mejrp.cop;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 1. Stubbing method
 * 2. Stubbing Exception
 */
public class AUtilsTest {
    /**
     * Stubbing a method
     */
    @Test
    public void testParseLibraryFrom() {
        Utils utils = mock(Utils.class);
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book("Effective Java", 200, Topic.COMPUTING, Year.of(2000), "Joshua Bloch")));
        when(utils.parseLibraryFrom(any(Path.class))).thenReturn(expectedBooks);

        MatcherAssert.assertThat(utils.parseLibraryFrom(Paths.get("src/main/resources/book.csv")), CoreMatchers.is(CoreMatchers.equalTo(expectedBooks)));
    }

    /**
     * Stubbing an exception
     */
    @Test
    public void testStubbingOfException() {
        Utils utils = mock(Utils.class);
        when(utils.getBook(anyString())).thenThrow(RuntimeException.class);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> utils.getBook(""));
        MatcherAssert.assertThat(exception.getClass(), CoreMatchers.is(CoreMatchers.equalTo(RuntimeException.class)));
    }
}
