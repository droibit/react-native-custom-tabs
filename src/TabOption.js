/**
 * @flow
 */
'use strict';

/**
 * Start and exit animations of Custom Tabs.
 * Slide in from left at start, Slide out to right.at exit.
 */
export const ANIMATIONS_SLIDE: Animations = {
  startEnter: 'slide_in_right',
  startExit: 'slide_out_left',
  endEnter: 'android:anim/slide_in_left',
  endExit: 'android:anim/slide_out_right',
};

/**
 * Start and exit animations of Custom Tabs.
 * Fade in at start, Fade out  at exit.
 */
export const ANIMATIONS_FADE: Animations = {
  startEnter: 'android:anim/fade_in',
  startExit: 'android:anim/fade_out',
  endEnter: 'android:anim/fade_in',
  endExit: 'android:anim/fade_out',
};

export type Animations = {
  startEnter: string,
  startExit: string,
  endEnter: string,
  endExit: string,
}

/**
 * Options to customize Custom Tabs of look & feel.
 */
export type TabOption = {

  /**
   * the Toolbar color.
   * Supported formats are: #RRGGBB, #AARRGGBB, etc.
   *
   * {@link http://d.android.com/reference/android/graphics/Color.html#parseColor(java.lang.String) Color.parseColor(String)}
   */
  toolbarColor?: string;

  /**
   * Enables the url bar to hide as the user scrolls down on the page.
   */
  enableUrlBarHiding?: boolean;

  /**
   * Sets whether the title should be shown in the custom tab.
   */
  showPageTitle?: boolean;

  /**
   * Whether to add a default shared items of the menu.
   */
  enableDefaultShare?: boolean;

  /**
   * Sets the exit and start animations.
   *
   * Each property needs to be an Andrion animation resource ID,
   * e.g. 'com.github.droibit.android.reactnative.customtabs.example:anim/slide_out_bottom'
   *
   * @see ANIMATIONS_FADE
   * @see ANIMATIONS_SLIDE
   */
  animations?: Animations;

  /**
   * Sets any custom headers that should be used.
   */
  headers?: Object;

  /**
   * Workaround that Custom Tabs doesn't close on redirecting back to app scheme.
   */
  forceCloseOnRedirection?: boolean;
};


