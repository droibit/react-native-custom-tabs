package com.github.droibit.android.reactnative.customtabs;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * CustomTabs module.
 */
public class CustomTabsModule extends ReactContextBaseJavaModule {

    private static final String KEY_TOOLBAR_COLOR = "toolbarColor";
    private static final String KEY_ENABLE_URLBAR_HIDING = "enableUrlBarHiding";
    //private static final String KEY_CLOSE_BUTTON_ICON = "closeButtonIcon"; // TODO
    private static final String KEY_SHOW_PAGE_TITLE = "showPageTitle";
    private static final String KEY_DEFAULT_SHARE_MENU_ITEM = "enableDefaultShare";
    private static final String KEY_ANIMATIONS = "animations";

    private static final String KEY_ANIMATIONS_SLIDE = "animationsSlide";
    private static final String KEY_ANIMATIONS_FADE = "animationsFade";

    private static final int ANIMATIONS_SLIDE = 0;
    private static final int ANIMATIONS_FADE = 1;

    private static final Map<String, Object> CONSTANTS;
    static {
        CONSTANTS = MapBuilder.newHashMap();
        CONSTANTS.put(KEY_TOOLBAR_COLOR, KEY_TOOLBAR_COLOR);
        CONSTANTS.put(KEY_ENABLE_URLBAR_HIDING, KEY_ENABLE_URLBAR_HIDING);
        //KEY_CLOSE_BUTTON_ICON, KEY_CLOSE_BUTTON_ICON,
        CONSTANTS.put(KEY_SHOW_PAGE_TITLE, KEY_SHOW_PAGE_TITLE);
        CONSTANTS.put(KEY_DEFAULT_SHARE_MENU_ITEM, KEY_DEFAULT_SHARE_MENU_ITEM);
        CONSTANTS.put(KEY_ANIMATIONS, KEY_ANIMATIONS);
        CONSTANTS.put(KEY_ANIMATIONS_SLIDE, ANIMATIONS_SLIDE);
        CONSTANTS.put(KEY_ANIMATIONS_FADE, ANIMATIONS_FADE);
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
    public void openURL(String url, ReadableMap options, Promise promise) {
        if (TextUtils.isEmpty(url)) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + url));
            return;
        }

        final Uri uri = Uri.parse(url);
        if (!uri.getScheme().equals("http") && !uri.getScheme().equals("https")) {
            promise.reject(new JSApplicationIllegalArgumentException("Allow only http or https: " + url));
            return;
        }

        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        if (options.hasKey(KEY_TOOLBAR_COLOR)) {
            final String colorString = options.getString(KEY_TOOLBAR_COLOR);
            try {
                builder.setToolbarColor(Color.parseColor(colorString));
            } catch (IllegalArgumentException e) {
                promise.reject(new JSApplicationIllegalArgumentException(
                        "Invalid toolbar color '" + colorString + "': " + e.getMessage()));
            }
        }
        if (options.hasKey(KEY_ENABLE_URLBAR_HIDING) ||
                options.getBoolean(KEY_ENABLE_URLBAR_HIDING)) {
            builder.enableUrlBarHiding();
        }
        if (options.hasKey(KEY_SHOW_PAGE_TITLE)) {
            builder.setShowTitle(options.getBoolean(KEY_SHOW_PAGE_TITLE));
        }
        if (options.hasKey(KEY_DEFAULT_SHARE_MENU_ITEM) ||
                options.getBoolean(KEY_DEFAULT_SHARE_MENU_ITEM)) {
            builder.addDefaultShareMenuItem();
        }
        if (options.hasKey(KEY_ANIMATIONS)) {
            final Context context = getReactApplicationContext();
            final int animation = options.getInt(KEY_ANIMATIONS);
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

        try {
            if (getCurrentActivity() != null) {
                builder.build().launchUrl(getCurrentActivity(), uri);
                promise.resolve(true);
            }
            // TODO: activity == null
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException(
                    "Could not open URL '" + url + "': " + e.getMessage()));
        }
    }
}
