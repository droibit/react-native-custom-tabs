/**
 * TODO: add license
 *
 * @flow
 */
'use strict';

/**
 * Start and exit animations of Custom Tabs.
 * Slide in from left at start, Slide out to right.at exit.
 */
export const ANIMATIONS_SLIDE:number = 0;

/**
 * Start and exit animations of Custom Tabs.
 * Fade in at start, Fade out  at exit.
 */
export const ANIMATIONS_FADE:number = 1;

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
   * @see ANIMATIONS_FADE
   * @see ANIMATIONS_SLIDE
   */
    animations?: number;
};


