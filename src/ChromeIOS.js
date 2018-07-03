/**
 * @private
 * @providesModule ChromeIOS
 * @flow
 */
'use strict';

import { NativeModules } from 'react-native';
import type { TabOption } from './TabOption';

const ChromeManager = NativeModules.DBChromeManager;

/**
 * To open the URL of the http or https in Chrome.
 * If Chrome is not installed, opens the URL in safari.
 */
export default class ChromeIOS {

  /**
   * Opens the URL on a Chrome.
   *
   * @param url the Uri to be opened.
   * @param option the Option in iOS is ignored
   */
  static openURL(url: string, option: TabOption = {}): Promise<boolean> {
    return ChromeManager.openURL(url)
  }
}