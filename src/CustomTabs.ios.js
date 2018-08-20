"use strict";

import { NativeModules } from "react-native";

const ChromeManager = NativeModules.DBChromeManager;

export const ANIMATIONS_SLIDE = {};
export const ANIMATIONS_FADE = {};

/**
 * To open the URL of the http or https in Chrome.
 * If Chrome is not installed, opens the URL in safari.
 */
export default class CustomTabs {
  /* eslint no-unused-vars: 0 */
  static openURL(url, option = {}) {
    return ChromeManager.openURL(url);
  }
}
