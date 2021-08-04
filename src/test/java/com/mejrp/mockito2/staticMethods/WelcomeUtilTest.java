package com.mejrp.mockito2.staticMethods;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

//Just for generateWelcomeOutput, we are using Powermock Runner, else we don't need
@RunWith(PowerMockRunner.class)
@PrepareForTest(WelcomeUtil.class)
public class WelcomeUtilTest {

    @Test
    public void generateWelcome() {
        assertEquals("Welcome John", WelcomeUtil.generateWelcome("John"));

        Mockito.mockStatic(WelcomeUtil.class);
        when(WelcomeUtil.generateWelcome(any(String.class))).thenReturn("Hi John");

        //Instead of "Welcome John", "Hi John" will be produced
        assertEquals("Hi John", WelcomeUtil.generateWelcome("John"));
    }

    @Ignore
    @Test
    public void generateWelcomeOutput() throws Exception {
        //No need to do mockStatic
        //Mockito.mockStatic(WelcomeUtil.class);
        WelcomeHelper helper = mock(WelcomeHelper.class);
        //We need to use Powermock for whenNew
        whenNew(WelcomeHelper.class).withAnyArguments().thenReturn(helper);
        when(helper.getMessage()).thenReturn("Hey jrp");

        assertEquals("Hey jrp", WelcomeUtil.generateWelcomeOutput("abcd"));
    }
}