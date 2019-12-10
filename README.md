# React Native Custom Tabs
[![npm version](https://badge.fury.io/js/react-native-custom-tabs.svg)](https://badge.fury.io/js/react-native-custom-tabs) [![Software License](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)](https://github.com/droibit/react-native-custom-tabs/blob/develop/LICENSE)

[Chrome Custom Tabs](https://developer.chrome.com/multidevice/android/customtabs) for React Native.   
Custom Tabs is supported only for Android, so the behavior on each platform is bellow.

* Android  
    If Chrome is installed, open the URL in Chrome that you have customized some of the look & feel. If it is not installed, open in other browser.

* iOS  
    Presents SafariViewController by default for iOS >9.0. Else opens in Safari.

Customization and detailed behavior refer to the [Usage](#Usage).

## Installation

```
npm install react-native-custom-tabs --save
```

To link the native module automatically, it is recommended that you use the [rnpm](https://github.com/rnpm/rnpm).

```
rnpm link
```

#### Android

In Android, Add it in your **root** `build.gradle`([e.g. example](https://github.com/droibit/react-native-custom-tabs/blob/develop/example/android/build.gradle)) at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

And, provide `CustomTabsPackage` in your Application class.

```java
import com.github.droibit.android.reactnative.customtabs.CustomTabsPackage;

@Override
protected List<ReactPackage> getPackages() {
    return Arrays.asList(
            ...,
            new CustomTabsPackage()
    );
}
```

If you use version `0.1.5` or higher, change the **app** `build.gradle`.

```groovy
android {
    ...
    compileSdkVersion 25
    buildToolsVersion "25.0.1"
}

dependencies {
    ...
    compile "com.android.support:appcompat-v7:25.0.1"
}
```

## Usage

Open the URL as `Linking` of React Native.

```javascript
CustomTabs.openURL('https://www.google.com').then((launched: {boolean}) => {
  console.log(`Launched custom tabs: ${launched}`);
}).catch(err => {
  console.error(err)
});
```

#### Customize for Android

You can customize the look & feel in Android. The following option is ignored in iOS.

```javascript
import {
    ANIMATIONS_SLIDE,
    ANIMATIONS_FADE
} from 'react-native-custom-tabs';

CustomTabs.openURL(url, {
  toolbarColor: '#607D8B',
  enableUrlBarHiding: true,
  showPageTitle: true,
  enableDefaultShare: true,
  // Specify full animation resource identifier(package:anim/name)
  // or only resource name(in case of animation bundled with app).
  animations: {
    startEnter: 'slide_in_bottom',
    startExit: 'slide_out_bottom',
    endEnter: 'slide_in_bottom',
    endExit: 'slide_out_bottom',
  },
  // And supports SLIDE and FADE as default animation.
  // animations: ANIMATIONS_SLIDE or ANIMATIONS_FADE.
  headers: {
    'my-custom-header': 'my custom header value'
  },
  forceCloseOnRedirection: true,
});
```

The option to support:

|property|type|default|description|
|--------|----|-------|-----------|
|toolbarColor|string|undefined|the Toolbar color. Supported formats are: #RRGGBB, #AARRGGBB, [etc](http://d.android.com/reference/android/graphics/Color.html#parseColor(java.lang.String)). |
|enableUrlBarHiding|boolean|undefined|Enables the url bar to hide as the user scrolls down on the page.|
|showPageTitle|boolean|undefined|Sets whether the title should be shown in the custom tab.|
|enableDefaultShare|boolean|undefined|Whether to add a default shared items of the menu.|
|animations|Object|undefined|Sets the exit and start animations. ANIMATIONS_FADE, ANIMATIONS_SLIDE or custom object with string properties `startEnter`, `startExit`, `endEnter` and `endExit` each defining an Android animation resource ID to use for the animations, such as `slide_in_right`.|
|headers|Object|undefined|Sets any custom headers that should be used.|
|forceCloseOnRedirection|boolean|undefined|Workaround that Custom Tabs doesn't close on redirecting back to app scheme.([#11](https://github.com/droibit/react-native-custom-tabs/pull/11))|


`undefined` property is default behavior of the Custom Tabs.

Customize and default look & feel.  
![screenshot](http://i.imgur.com/0qE2E7a.gif)

## License

    Copyright (C) 2015 The Android Open Source Project
    Copyright (C) 2016 Shinya Kumagai

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
