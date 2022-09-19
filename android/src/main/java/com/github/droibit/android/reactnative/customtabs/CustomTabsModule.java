package com.github.droibit.android.reactnative.customtabs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;

import com.droibit.android.customtabs.launcher.CustomTabsLauncher;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;

import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

/**
 * CustomTabs module.
 */
public class CustomTabsModule extends ReactContextBaseJavaModule {

    private static final String KEY_TOOLBAR_COLOR = "toolbarColor";
    private static final String KEY_ENABLE_URL_BAR_HIDING = "enableUrlBarHiding";
    private static final String KEY_SHOW_PAGE_TITLE = "showPageTitle";
    private static final String KEY_DEFAULT_SHARE_MENU_ITEM = "enableDefaultShare";
    private static final String KEY_ANIMATIONS = "animations";
    private static final String KEY_HEADERS = "headers";
    private static final String KEY_FORCE_CLOSE_ON_REDIRECTION = "forceCloseOnRedirection";
    private static final String KEY_ANIMATION_START_ENTER = "startEnter";
    private static final String KEY_ANIMATION_START_EXIT = "startExit";
    private static final String KEY_ANIMATION_END_ENTER = "endEnter";
    private static final String KEY_ANIMATION_END_EXIT = "endExit";

    private static final Map<String, Object> CONSTANTS;

    static {
        CONSTANTS = MapBuilder.newHashMap();
        CONSTANTS.put(KEY_TOOLBAR_COLOR, KEY_TOOLBAR_COLOR);
        CONSTANTS.put(KEY_ENABLE_URL_BAR_HIDING, KEY_ENABLE_URL_BAR_HIDING);
        CONSTANTS.put(KEY_SHOW_PAGE_TITLE, KEY_SHOW_PAGE_TITLE);
        CONSTANTS.put(KEY_DEFAULT_SHARE_MENU_ITEM, KEY_DEFAULT_SHARE_MENU_ITEM);
        CONSTANTS.put(KEY_ANIMATIONS, KEY_ANIMATIONS);
        CONSTANTS.put(KEY_HEADERS, KEY_HEADERS);
        CONSTANTS.put(KEY_FORCE_CLOSE_ON_REDIRECTION, KEY_FORCE_CLOSE_ON_REDIRECTION);
    }

    private static final String MODULE_NAME = "CustomTabsManager";

    // Note: The full resource qualifier is "package:type/entry".
    // https://developer.android.com/reference/android/content/res/Resources.html#getIdentifier(java.lang.String, java.lang.String, java.lang.String)
    private static final Pattern animationIdentifierPattern = Pattern.compile("^.+:.+/");

    public CustomTabsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

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

        if (!httpOrHttpsScheme(url)) {
            promise.reject(new JSApplicationIllegalArgumentException("Allow only http or https URL: " + url));
            return;
        }

        try {
            final CustomTabsIntent customTabsIntent = buildIntent(
                    getReactApplicationContext(),
                    new CustomTabsIntent.Builder(),
                    option
            );
            final Activity activity = getCurrentActivity();
            if (activity != null) {
                CustomTabsLauncher.launch(activity, customTabsIntent, android.net.Uri.parse(url));
                promise.resolve(true);
            } else {
                promise.resolve(false);
            }
        } catch (JSApplicationIllegalArgumentException | UnexpectedNativeTypeException e) {
            // If build option is invalid.
            promise.reject(e);
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException(
                    "Could not open URL '" + url + "': " + e.getMessage()));
        }
    }

    @VisibleForTesting
    /* package */ CustomTabsIntent buildIntent(Context context,
                                               CustomTabsIntent.Builder builder,
                                               ReadableMap option) {
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
            final ReadableMap animations = option.getMap(KEY_ANIMATIONS);
            applyAnimation(context, builder, animations);
        }
        CustomTabsIntent customTabsIntent = builder.build();

        // Add custom headers if present
        if (option.hasKey(KEY_HEADERS)) {
            ReadableMap readableMap = option.getMap(KEY_HEADERS);

            if (readableMap != null) {
                ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
                if (iterator.hasNextKey()) {
                    Bundle headers = new Bundle();
                    while (iterator.hasNextKey()) {
                        String key = iterator.nextKey();
                        ReadableType readableType = readableMap.getType(key);
                        switch (readableType) {
                            case String:
                                headers.putString(key, readableMap.getString(key));
                                break;
                            default:
                                // ignore
                                break;
                        }
                    }
                    customTabsIntent.intent.putExtra(Browser.EXTRA_HEADERS, headers);
                }
            }
        }

        if (option.hasKey(KEY_FORCE_CLOSE_ON_REDIRECTION) &&
                option.getBoolean(KEY_FORCE_CLOSE_ON_REDIRECTION)) {
                  customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                  customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        
        return customTabsIntent;
    }

    @VisibleForTesting
    /* package */ boolean httpOrHttpsScheme(String url) {
        return url.startsWith("http") || url.startsWith("https");
    }

    @VisibleForTesting
    /* package */ void applyAnimation(Context context, CustomTabsIntent.Builder builder, ReadableMap animations) {
        final int startEnterAnimationId = animations.hasKey(KEY_ANIMATION_START_ENTER)
            ? resolveAnimationIdentifierIfNeeded(context, animations.getString(KEY_ANIMATION_START_ENTER))
            : -1;
        final int startExitAnimationId = animations.hasKey(KEY_ANIMATION_START_EXIT)
            ? resolveAnimationIdentifierIfNeeded(context, animations.getString(KEY_ANIMATION_START_EXIT))
            : -1;
        final int endEnterAnimationId = animations.hasKey(KEY_ANIMATION_END_ENTER)
            ? resolveAnimationIdentifierIfNeeded(context, animations.getString(KEY_ANIMATION_END_ENTER))
            : -1;
        final int endExitAnimationId = animations.hasKey(KEY_ANIMATION_END_EXIT)
            ? resolveAnimationIdentifierIfNeeded(context, animations.getString(KEY_ANIMATION_END_EXIT))
            : -1;

        if (startEnterAnimationId != -1 && startExitAnimationId != -1) {
            builder.setStartAnimations(context, startEnterAnimationId, startExitAnimationId);
        }

        if (endEnterAnimationId != -1 && endExitAnimationId != -1) {
            builder.setExitAnimations(context, endEnterAnimationId, endExitAnimationId);
        }
    }

    // Complement the application name of the resource qualifier as necessary.
    private int resolveAnimationIdentifierIfNeeded(Context context, String identifier) {
        if (animationIdentifierPattern.matcher(identifier).find()) {
            return context.getResources().getIdentifier(identifier, null, null);
        } else {
            return context.getResources().getIdentifier(identifier, "anim", context.getPackageName());
        }
    }
}
