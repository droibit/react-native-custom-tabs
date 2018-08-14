"use strict";

import { NativeModules } from "react-native";

const CustomTabsManager = NativeModules.CustomTabsManager;

/**
 * To open the URL of the http or https in Chrome Custom Tabs.
 * If Chrome is not installed, opens the URL in other browser.
 */
export default class CustomTabs {
  static openURL(url, option = {}) {
    return CustomTabsManager.openURL(url, option);
  }
}
