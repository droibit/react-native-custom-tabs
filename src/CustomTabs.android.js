"use strict";

import { NativeModules } from "react-native";

const CustomTabsManager = NativeModules.CustomTabsManager;

/**
 * Start and exit animations of Custom Tabs.
 * Slide in from left at start, Slide out to right.at exit.
 */
export const ANIMATIONS_SLIDE = {
  startEnter: CustomTabsManager.ANIMATION_SLIDE_IN_RIGHT,
  startExit: CustomTabsManager.ANIMATION_SLIDE_OUT_LEFT,
  endEnter: CustomTabsManager.ANIMATION_SLIDE_IN_LEFT,
  endExit: CustomTabsManager.ANIMATION_SLIDE_OUT_LEFT,
};

/**
 * Start and exit animations of Custom Tabs.
 * Fade in at start, Fade out  at exit.
 */
export const ANIMATIONS_FADE = {
  startEnter: CustomTabsManager.ANIMATION_FADE_IN,
  startExit: CustomTabsManager.ANIMATION_FADE_OUT,
  endEnter: CustomTabsManager.ANIMATION_FADE_IN,
  endExit: CustomTabsManager.ANIMATION_FADE_OUT,
};

/**
 * To open the URL of the http or https in Chrome Custom Tabs.
 * If Chrome is not installed, opens the URL in other browser.
 */
export default class CustomTabs {
  static openURL(url, option = {}) {
    return CustomTabsManager.openURL(url, option);
  }
}
