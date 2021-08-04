package com.mejrp.mockito2.finalClassMethods;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinalClassTest {

    @Test
    public void testFinalMethod() {
        FinalClass concrete = new FinalClass();
        FinalClass mockFinal = mock(FinalClass.class);
        when(mockFinal.finalMethod()).thenReturn("not anymore");
        assertNotEquals("not anymore", concrete.finalMethod());
    }

}