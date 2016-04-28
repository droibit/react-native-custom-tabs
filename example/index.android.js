/// <reference path="../../typings/main.d.ts" />

/**
 * @flow
 */

import {CustomTabs, ANIMATIONS_SLIDE} from 'react-native-custom-tabs';
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  StatusBar,
  ToolbarAndroid,
  Text,
  TouchableHighlight,
  View
} from 'react-native';

class Example extends Component {
  render() {
    return (
      <View style={styles.container}>
        <StatusBar backgroundColor={'#455A64'} />
        <ToolbarAndroid
          title={'React Native Custom Tabs'}
          titleColor={'#FFFFFF'}
          style={styles.toolbar} />
        <View style={styles.subContainer}>
          <Text style={styles.welcome}>
            Click on the link below!
          </Text>
          <TouchableHighlight
            underlayColor={'#CFD8DC'}
            onPress={() => this.openGoogle()} >
            <Text style={styles.link}>
              https://www.google.com
            </Text>
          </TouchableHighlight>
        </View>
      </View>
    );
  }

  openGoogle() {
    CustomTabs.build({
      toolbarColor: '#607D8B',
      enableUrlBarHiding: true,
      showPageTitle: true,
      enableDefaultShare: true,
      animations: ANIMATIONS_SLIDE
    }).openURL('https://www.google.com')
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
  subContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  link: {
    color: 'crimson',
  },
  toolbar: {
    backgroundColor: '#607D8B',
    height: 56
  }
});

AppRegistry.registerComponent('Example', () => Example);
