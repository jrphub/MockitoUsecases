package com.mejrp.mockito2.privateMethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LuckyNumberGenerator.class})
public class LuckyNumberTest {

    @Test
    public void getLuckyNumber() throws Exception {
        LuckyNumberGenerator mock = spy(new LuckyNumberGenerator());
        when(mock, "getDefaultLuckyNumber").thenReturn(300);
        int result = mock.getLuckyNumber(null);

        assertEquals(300, result);
    }

    @Test
    public void getComputedLuckyNumberWithArgument() throws Exception {
        LuckyNumberGenerator mock = spy(new LuckyNumberGenerator());
        doReturn(9).when(mock, "getComputedLuckyNumber", ArgumentMatchers.anyInt());
        int result = mock.getLuckyNumber("Jack");
        assertEquals(9, result);
    }

    @Test
    public void saveIntoDatabase() throws Exception {
        LuckyNumberGenerator mock = spy(new LuckyNumberGenerator());
        int result = mock.getLuckyNumber("hello");

        verifyPrivate(mock).invoke("saveIntoDatabase", ArgumentMatchers.anyString());
        assertEquals(10, result);
    }
}