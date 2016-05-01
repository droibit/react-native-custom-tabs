# React Native Custom Tabs
Chrome Custom Tabs for React Native. Custom Tabs is supported only Chrome for Android. For this reason, the interface is the same, but the behavior is following:

* iOS
    If Chrome is installed, open the URL in it. If it is not installed, open in Safari.

* Android
    If Chrome is installed, open the URL in Chrome that you have customized some of the look & feel. If it is not installed, open in other browser.

Customization and detailed behavior refer to the Usage.

## Installation

```
npm install react-native-custom-tabs --save
```

To link the native module automatically, it is recommended that you use the [rnpm](https://github.com/rnpm/rnpm).

```
rnpm link
```

#### Android

In Android, Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

### Manually

TODO

## Usage

Open the URL as `Linking` of React Native.

```javascript
CustomTabs.openURL('https://www.google.com', {}).then((launched: {boolean}) => {
  console.log(`Launched custom tabs: ${launched}`);
}).catch(err => {
  console.error(err)
});
```

#### Customization(Android)

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
  animations: ANIMATIONS_SLIDE // or ANIMATIONS_FADE
});
```

The option to support:

|property|type|default|description|
|--------|----|-------|-----------|
|toolbarColor|string|undefined|the Toolbar color. Supported formats are: #RRGGBB, #AARRGGBB, [etc](http://d.android.com/reference/android/graphics/Color.html#parseColor(java.lang.String)). |
|enableUrlBarHiding|boolean|undefined|Enables the url bar to hide as the user scrolls down on the page.|
|showPageTitle|boolean|undefined|Sets whether the title should be shown in the custom tab.|
|enableDefaultShare|boolean|undefined|Whether to add a default shared items of the menu.|
|animations|number|undefined|Sets the exit and start animations. ANIMATIONS_FADE or ANIMATIONS_SLIDE.|

All The default value is `undefined`, there is no problem even if you specify an empty object(`{}`).
And, `undefined` property is the default behavior of the Custom Tabs.

Customize and default look & feel.
![screenshot](http://i.imgur.com/0qE2E7a.gif)

## License

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
