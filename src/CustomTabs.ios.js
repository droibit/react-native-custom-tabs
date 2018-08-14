'use strict';

import { NativeModules } from 'react-native';

const ChromeManager = NativeModules.DBChromeManager;

/**
 * To open the URL of the http or https in Chrome.
 * If Chrome is not installed, opens the URL in safari.
 */
export default class CustomTabs {

  static openURL(url, option = {}) {
    return ChromeManager.openURL(url)
  }
}