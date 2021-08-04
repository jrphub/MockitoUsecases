package com.mejrp.mockito2.abstractClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClassAbstractTest {
    @InjectMocks
    private ClientClass clientClass;

    @Test
    public void testMethod1() {
        ClassAbstract mockAbstract = Mockito.mock(ClassAbstract.class,
                Mockito.withSettings().useConstructor().defaultAnswer(Mockito.RETURNS_SMART_NULLS));
        //This creates a MockitoSettings instance
        //1) which should use abstract class’s constructor to create future mock instance
        //2) and should set the default answer of all the method in the mock to return
        // appropriate default values.


        when(mockAbstract.method1()).thenReturn(5);
        when(mockAbstract.method2()).thenReturn(6);

        //for static method
        Mockito.mockStatic(ClassAbstract.class);
        when(ClassAbstract.method3()).thenReturn(7);

        clientClass.setClassA(mockAbstract);

        assertEquals(5, clientClass.callMethod1A());
        assertEquals(6, clientClass.callMethod2A());
        assertEquals(7, clientClass.callMethod3());
    }
}