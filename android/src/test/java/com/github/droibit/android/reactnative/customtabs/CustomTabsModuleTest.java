package com.github.droibit.android.reactnative.customtabs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    // TODO
    public void setupWithoutAnimation() {
    }

    // TODO
    public void setupAnimation() {

    }

    // TODO
    public void emptyOption() {

    }

    // TODO
    public void failedCastOption() {

    }
}