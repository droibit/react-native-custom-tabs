package com.github.droibit.android.reactnative.customtabs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * @author kumagai
 */
@RunWith(PowerMockRunner.class)
public class CustomTabsModuleTest {

    @Test
    public void invalidUrlScheme() {
        final CustomTabsModule module = new CustomTabsModule(null);

        final String httpGoogle = "http://www.google.com";
        assertTrue(module.httpOrHttpsScheme(httpGoogle));

        final String httpsGoogle = "https://www.google.com";
        assertTrue(module.httpOrHttpsScheme(httpsGoogle));

        final String sdcard = "file://sdcard";
        assertFalse(module.httpOrHttpsScheme(sdcard));
    }

    public void setupWithoutAnimation() {
        final CustomTabsModule module = new CustomTabsModule(null);

        //final ReadableMap option = new JavaOnlyMap();
    }

    public void setupAnimation() {

    }

    public void emptyOption() {

    }

    public void failedCastOption() {

    }
}