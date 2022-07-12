package com.mejrp.cop;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedStatic;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.exceptions.misusing.UnfinishedVerificationException;

import static org.mockito.Mockito.*;

/**
 * Mockito 2 limitations:
 * <p>
 * 1) Can't mock static methods
 * 2) Can't mock constructors
 * 3) Can't mock equals(), hashCode()
 * 4) Can't mock final methods/type
 * 5) Can't mock private methods
 */
public class BMockableTypes {
    //can't mock final type
    //Error it throws

    /**
     * org.mockito.exceptions.base.MockitoException:
     * Cannot mock/spy class com.mejrp.cop.BMockableTypes$FinalClass
     * Mockito cannot mock/spy because :
     * - final class
     * <p>
     * Solution:
     * 1. Use mockito-inline with extension
     */
    @Test
    public void testFinalClass() {
        //error
        //mock(FinalClass.class);
        //handle error to pass the test
        //MockitoException exception = Assertions.assertThrows(MockitoException.class, () -> mock(FinalClass.class));
        //MatcherAssert.assertThat(exception.getClass(), CoreMatchers.is(CoreMatchers.equalTo(MockitoException.class)));

        //Mockito-inline is used
        final FinalClass mockFinalClass = mock(FinalClass.class);
        mockFinalClass.foo();
        verify(mockFinalClass).foo();

    }

    //Test Abstract Class
    @Test
    public void testAbstractClass() {
        final AbstractClass mock = mock(AbstractClass.class);

        mock.concreteMethod();
        verify(mock).concreteMethod();

        when(mock.abstractMethod()).thenReturn(22);
        mock.abstractMethod();
        verify(mock).abstractMethod();

    }

    //test Concrete class
    @Test
    public void testConcreteClass() {
        final ConcreteClass mockConcreteClass = mock(ConcreteClass.class);
        mockConcreteClass.instanceMethod();
        verify(mockConcreteClass).instanceMethod();

        //final method, but we are mocking the concrete class, so it works
        mockConcreteClass.finalMethod();
        verify(mockConcreteClass).finalMethod();

        mockConcreteClass.privateMethod();
        //error : org.mockito.exceptions.misusing.UnfinishedVerificationException
        //verify(mockConcreteClass).privateMethod();

        //TODO : static methods
    }

    //test interface
    @Test
    public void testInterface() {
        final MyInterface mockInterface = mock(MyInterface.class);
        mockInterface.abstractMethod();
        mockInterface.defaultMethod();
        verify(mockInterface).abstractMethod();
        verify(mockInterface).defaultMethod();
    }

    /**
     * Inner types declarations
     */
    static final class FinalClass {
        private void foo() {
            System.out.println("MyFinalClass.foo");
        }
    }

    abstract class AbstractClass {
        void concreteMethod() {
            System.out.println("AbstractClass.concreteMethod");
        }

        abstract int abstractMethod();
    }

    static class ConcreteClass {
        void instanceMethod() {
            System.out.println("ConcreteClass.instanceMethod");
        }

        final void finalMethod() {
            System.out.println("ConcreteClass.finalMethod");
        }

        static void staticMethod() {
            System.out.println("ConcreteClass.staticMethod");
        }

        static void staticMethod2() {
            System.out.println("ConcreteClass.staticMethod2");
        }

        private void privateMethod() {
            System.out.println("ConcreteClass.privateMethod");
        }
    }

    interface MyInterface {
        default void defaultMethod() {
            System.out.println("myInterface.defaultMethod");
        }

        void abstractMethod();

        static void staticMethod() {
            System.out.println("myInterface.staticMethod");
        }
    }
}
