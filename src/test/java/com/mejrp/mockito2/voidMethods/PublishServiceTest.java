package com.mejrp.mockito2.voidMethods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

/**
 * Whenever we mock a void method, we do not expect a return value.
 * That is why we can only verify whether that method is being called or not.
 * <p>
 * you can use any of the
 * doThrow(),doAnswer(),doNothing(),doReturn() family of methods
 * from Mockito framework to mock void methods.
 * we can't use when()
 * </p>
 */
@RunWith(MockitoJUnitRunner.class)
public class PublishServiceTest {

    //Mock the class which has void method to be mocked
    @Mock
    private Publisher mockPublisher;

    @Test
    public void testAdd() {
        PublishService service = new PublishService(mockPublisher);

        Mockito.doNothing().when(mockPublisher).publishInfo(any(News.class));

        service.invokePublisher(new News());

        Mockito.verify(mockPublisher, Mockito.times(1))
                .publishInfo(any(News.class));
    }
}