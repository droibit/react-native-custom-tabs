//
//  DBCusomTabsManager.m
//  ReactNativeCustomTabs
//
//  Created by Shinya Kumagai.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "DBCusomTabsManager.h"
#import "RCTUtils.h"

@implementation DBCusomTabsManager

RCT_EXPORT_MODULE()

/**
 * Starts a Chrome for the given URL.
 * If Chrome is not installed, start Safari.
 */
RCT_EXPORT_METHOD(openURL:(NSString *)urlString)
{
  NSURL *srcUrl = [NSURL URLWithString:urlString];
  NSString *chromeScheme = nil;
  if ([srcUrl.scheme isEqualToString:@"http"]) {
    chromeScheme = @"googlechrome";
  } else if ([srcUrl.scheme isEqualToString:@"https"]) {
    chromeScheme = @"googlechromes";
  } else {
    // TODO:
  }
  
  if (chromeScheme) {
    NSString *absoluteString = [srcUrl absoluteString];
    NSRange rangeForScheme = [absoluteString rangeOfString:@":"];
    NSString *urlNoScheme = [absoluteString substringFromIndex:rangeForScheme.location];
    NSString *chromeURLString = [chromeScheme stringByAppendingString:urlNoScheme];
    NSURL *chromeURL = [NSURL URLWithString:chromeURLString];
    
    if ([[UIApplication sharedApplication] canOpenURL:chromeURL]) {
      [[UIApplication sharedApplication] openURL:chromeURL];
      return;
    }
  }
  
  // Open safari
  [[UIApplication sharedApplication] openURL:srcUrl];
}

@end
