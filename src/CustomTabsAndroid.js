/**
 * TODO: add license
 *
 * @providesModule CustomTabsAndroid
 * @flow
 */
'use strict';

import {NativeModules} from 'react-native';

export type TabsOptionAndroid = {
  toolbarColor?: string;
  enableUrlBarHiding?: boolean;
  showPageTitle?: boolean;
  enableDefaultShare?: boolean;
  animations?: number;
};

export const ANIMATIONS_SLIDE: number = 0;
export const ANIMATIONS_FADE: number = 1;

const CustomTabsManager = NativeModules.CustomTabsManager;

export default class CustomTabsAndroid {
  options: TabsOptionAndroid;

  constructor(options: TabsOptionAndroid = {}) {
    this.options = options;
  }

  openURL(url: string): Promise<boolean> {
    return CustomTabsManager.openURL(url, this.options)
  }

  static build(options: TabsOptionAndroid = {}): CustomTabsAndroid {
    return new CustomTabsAndroid(options);
  }
}