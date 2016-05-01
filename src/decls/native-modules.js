/**
 * @flow
 */
'use strict';

declare module "react-native" {
  declare class _NativeModules {
    DBChromeManager: any;
    CustomTabsManager: any;
  }
  declare var NativeModules: _NativeModules;
} 
