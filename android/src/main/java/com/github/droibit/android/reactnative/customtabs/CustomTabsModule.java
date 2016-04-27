package com.github.droibit.android.reactnative.customtabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;

import com.droibit.android.customtabs.launcher.CustomTabsLauncher;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import static android.content.pm.PackageManager.*;
import static com.github.droibit.android.reactnative.customtabs.Constants.*;

/**
 * CustomTabs module.
 */
public class CustomTabsModule extends ReactContextBaseJavaModule {

    private static final String KEY_TOOLBAR_COLOR = "toolbarColor";
    private static final String KEY_ENABLE_URL_BAR_HIDING = "enableUrlBarHiding";
    private static final String KEY_SHOW_PAGE_TITLE = "showPageTitle";
    private static final String KEY_DEFAULT_SHARE_MENU_ITEM = "enableDefaultShare";
    private static final String KEY_ANIMATIONS = "animations";

    private static final int ANIMATIONS_SLIDE = 0;
    private static final int ANIMATIONS_FADE = 1;

    private static final Map<String, Object> CONSTANTS;
    static {
        CONSTANTS = MapBuilder.newHashMap();
        CONSTANTS.put(KEY_TOOLBAR_COLOR, KEY_TOOLBAR_COLOR);
        CONSTANTS.put(KEY_ENABLE_URL_BAR_HIDING, KEY_ENABLE_URL_BAR_HIDING);
        CONSTANTS.put(KEY_SHOW_PAGE_TITLE, KEY_SHOW_PAGE_TITLE);
        CONSTANTS.put(KEY_DEFAULT_SHARE_MENU_ITEM, KEY_DEFAULT_SHARE_MENU_ITEM);
        CONSTANTS.put(KEY_ANIMATIONS, KEY_ANIMATIONS);
    }

    private static final String MODULE_NAME = "CustomTabsManager";

    public CustomTabsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        return CONSTANTS;
    }

    /**
     * Starts a chrome custom tabs activity for the given URL.
     *
     * @param url the URL to open
     */
    @ReactMethod
    public void openURL(String url, ReadableMap option, Promise promise) {
        if (TextUtils.isEmpty(url)) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + url));
            return;
        }

        final Uri uri = Uri.parse(url);
        if (!uri.getScheme().equals("http") && !uri.getScheme().equals("https")) {
            promise.reject(new JSApplicationIllegalArgumentException("Allow only http or https: " + url));
            return;
        }

        try {
            final CustomTabsIntent customTabsIntent = buildIntent(option);
            final Activity activity = getCurrentActivity();
            if (activity != null) {
                CustomTabsLauncher.launch(activity, customTabsIntent, uri);
                promise.resolve(true);
            } else {
                promise.resolve(false);
            }
        } catch (JSApplicationIllegalArgumentException e) {
            // If build option is invalid.
            promise.reject(e);
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException(
                    "Could not open URL '" + url + "': " + e.getMessage()));
        }
    }

    @VisibleForTesting
    /* package */ CustomTabsIntent buildIntent(ReadableMap option) {
        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        if (option.hasKey(KEY_TOOLBAR_COLOR)) {
            final String colorString = option.getString(KEY_TOOLBAR_COLOR);
            try {
                builder.setToolbarColor(Color.parseColor(colorString));
            } catch (IllegalArgumentException e) {
                throw new JSApplicationIllegalArgumentException(
                        "Invalid toolbar color '" + colorString + "': " + e.getMessage());
            }
        }
        if (option.hasKey(KEY_ENABLE_URL_BAR_HIDING) &&
                option.getBoolean(KEY_ENABLE_URL_BAR_HIDING)) {
            builder.enableUrlBarHiding();
        }
        if (option.hasKey(KEY_SHOW_PAGE_TITLE)) {
            builder.setShowTitle(option.getBoolean(KEY_SHOW_PAGE_TITLE));
        }
        if (option.hasKey(KEY_DEFAULT_SHARE_MENU_ITEM) &&
                option.getBoolean(KEY_DEFAULT_SHARE_MENU_ITEM)) {
            builder.addDefaultShareMenuItem();
        }

        // TODO: If it does not launch Chrome, animation is unnecessary?

        if (option.hasKey(KEY_ANIMATIONS)) {
            final Context context = getReactApplicationContext();
            final int animation = option.getInt(KEY_ANIMATIONS);
            switch (animation) {
                case ANIMATIONS_SLIDE:
                    builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
                            .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;
                case ANIMATIONS_FADE:
                    builder.setStartAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
                            .setExitAnimations(context, android.R.anim.fade_out, android.R.anim.fade_in);
                    break;
            }
        }
        return builder.build();
    }
}
