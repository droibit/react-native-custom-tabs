"use strict";

/**
 * Start and exit animations of Custom Tabs.
 * Slide in from left at start, Slide out to right.at exit.
 */
export const ANIMATIONS_SLIDE = {
  startEnter: "slide_in_right",
  startExit: "slide_out_left",
  endEnter: "android:anim/slide_in_left",
  endExit: "android:anim/slide_out_right",
};

/**
 * Start and exit animations of Custom Tabs.
 * Fade in at start, Fade out  at exit.
 */
export const ANIMATIONS_FADE = {
  startEnter: "android:anim/fade_in",
  startExit: "android:anim/fade_out",
  endEnter: "android:anim/fade_in",
  endExit: "android:anim/fade_out",
};
