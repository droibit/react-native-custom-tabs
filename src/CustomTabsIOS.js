/**
 * TODO: add license
 *
 * @providesModule CustomTabsIOS
 * @flow
 */
'use strict';

import {NativeModules} from 'react-native';

/**
 * Chrome for iOS does not support Custom Tabs.
 */
export type TabOptionIOS = {};

const CustomTabManager = NativeModules.DBCustomTabsManager;

/**
 * TODO
 */
export default class CustomTabsIOS {

  /**
   * Opens the URL on a Custom Tab.
   *
   * @param url the Uri to be opened.
   */
  static openURL(url: string, option: TabOptionIOS = {}): Promise<boolean> {
    return CustomTabsManager.openURL(url)
  }
}