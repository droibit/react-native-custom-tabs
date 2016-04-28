/**
 * TODO: add license
 *
 * @providesModule CustomTabsAndroid
 * @flow
 */
'use strict';

import {NativeModules} from 'react-native';

/**
 * Options to customize Custom Tabs of look & feel.
 */
export type TabOptionAndroid = {
  toolbarColor?: string;
  enableUrlBarHiding?: boolean;
  showPageTitle?: boolean;
  enableDefaultShare?: boolean;
  animations?: number;
};

/**
 * Start and exit animation of Custom Tabs.
 * Slide in from left at start, Slide out to right.at exit.
 */
export const ANIMATIONS_SLIDE: number = 0;

/**
 * Start and exit animation of Custom Tabs.
 * Fade in at start, Fade out  at exit.
 */
export const ANIMATIONS_FADE: number = 1;

const CustomTabsManager = NativeModules.CustomTabsManager;

/**
 * TODO
 */
export default class CustomTabsAndroid {

  /**
   * Opens the URL on a Custom Tab.
   *
   * @param url the Uri to be opened.
   * @param option the Option to customize Custom Tabs of look & feel.
   */
  static openURL(url: string, option: TabOptionAndroid = {}): Promise<boolean> {
    return CustomTabsManager.openURL(url, option)
  }
}