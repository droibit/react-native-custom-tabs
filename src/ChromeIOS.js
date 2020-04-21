/**
 * @private
 * @providesModule ChromeIOS
 * @flow
 */
'use strict';

import { NativeModules } from 'react-native';
import { isAvailable } from "./SafariController";

const NativeSafariViewManager = NativeModules.SafariViewManager;
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
   * @param inAppSafari the inAppSafari bool to toggle between inapp safari and default behaviour
   */
  static openURL(url:string, inAppSafari = true):Promise<boolean> {
    if (inAppSafari && isAvailable) {
      return NativeSafariViewManager.show({url});
    } else {
      return ChromeManager.openURL(url);
    }
  }
  
  static dismiss() {
    NativeSafariViewManager.dismiss();
  }
}
