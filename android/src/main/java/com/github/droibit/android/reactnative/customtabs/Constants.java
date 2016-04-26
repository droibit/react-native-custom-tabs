package com.github.droibit.android.reactnative.customtabs;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kumagai on 16/04/25.
 */
/* package */ class Constants {

    /* package */ static final String PACKAGE_STABLE = "com.android.chrome";
    /* package */ static final String PACKAGE_BETA = "com.chrome.beta";
    /* package */ static final String PACKAGE_DEV = "com.chrome.dev";
    /* package */ static final String PACKAGE_LOCAL = "com.google.android.apps.chrome";
    /* package */ static final List<String> CHROME_PACKAGES = Arrays.asList(
            PACKAGE_STABLE,
            PACKAGE_BETA,
            PACKAGE_DEV,
            PACKAGE_LOCAL);

    /* package */  static final String ACTION_CUSTOM_TABS_CONNECTION =
            "android.support.customtabs.action.CustomTabsService";
}
