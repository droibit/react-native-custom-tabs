'use strict';

import {Platform} from 'react-native';
import CustomTabsIOS from './src/CustomTabsIOS';
import CustomTabsAndroid, {
  ANIMATIONS_SLIDE,
  ANIMATIONS_FADE
} from './src/CustomTabsAndroid';

import type {TabsOptionAndroid} from './src/CustomTabsAndroid';
import type {TabsOptionIOS} from './src/CustomTabsIOS';

export type TabsOption = TabsOptionAndroid | TabsOptionIOS;

const CustomTabs = Platform.OS === 'android' ? CustomTabsAndroid : CustomTabsIOS;
export {CustomTabs, ANIMATIONS_SLIDE, ANIMATIONS_FADE};
