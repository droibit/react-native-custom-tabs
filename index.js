/**
 * TODO: add license
 *
 * @providesModule CustomTabsAndroid
 * @flow
 */
'use strict';

import {Platform} from 'react-native';
import CustomTabsIOS from './src/CustomTabsIOS';
import CustomTabsAndroid, {
  ANIMATIONS_SLIDE,
  ANIMATIONS_FADE
} from './src/CustomTabsAndroid';

import type {TabOptionAndroid} from './src/CustomTabsAndroid';
import type {TabOptionIOS} from './src/CustomTabsIOS';

export type TabOption = TabOptionAndroid | TabOptionIOS;

const CustomTabs = Platform.OS === 'android' ? CustomTabsAndroid : CustomTabsIOS;
export {CustomTabs, ANIMATIONS_SLIDE, ANIMATIONS_FADE};
