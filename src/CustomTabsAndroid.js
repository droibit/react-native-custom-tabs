/**
 * @private
 * @providesModule CustomTabsAndroid
 * @flow
 */
'use strict';

import { NativeModules } from 'react-native';
import type { TabOption } from './TabOption';

const CustomTabsManager = NativeModules.CustomTabsManager;

/**
 * To open the URL of the http or https in Chrome Custom Tabs.
 * If Chrome is not installed, opens the URL in other browser.
 */
export default class CustomTabsAndroid {

  /**
   * Opens the URL on a Custom Tab.
   *
   * @param url the Uri to be opened.
   * @param option the Option to customize Custom Tabs of look & feel.
   */
  static openURL(url: string, option: TabOption = {}): Promise<boolean> {
    return CustomTabsManager.openURL(url, option)
  }
}