/**
 * TODO: add license
 *
 * @providesModule CustomTabsIOS
 * @flow
 */
'use strict';

import {NativeModules} from 'react-native';

export type TabsOptionIOS = {};

const CustomTabsManager = NativeModules.DBCustomTabManager;

export default class CustomTabsIOS {

  constructor(options: TabsOptionIOS = {}) {
  }

  openURL(url: string): Promise<boolean> {
    return CustomTabsManager.openURL(url)
  }

  static build(options: TabsOptionIOS = {}): CustomTabsIOS {
    return new CustomTabsIOS(options);
  }
}