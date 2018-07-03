/**
 * @public
 * @flow
 */
'use strict';

import { Platform } from 'react-native';
import ChromeIOS from './src/ChromeIOS';
import CustomTabsAndroid from './src/CustomTabsAndroid';
import { ANIMATIONS_SLIDE, ANIMATIONS_FADE } from './src/TabOption';

import type { TabOption as _TabOption } from './src/TabOption';

export type TabOption = _TabOption;

const CustomTabs = Platform.OS === 'android' ? CustomTabsAndroid : ChromeIOS;
export { CustomTabs, ANIMATIONS_SLIDE, ANIMATIONS_FADE };
