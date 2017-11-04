//
//  DBCustomTabsManager.m
//  ReactNativeCustomTabs
//
//  Created by Shinya Kumagai.
//  Copyright © 2016 Facebook. All rights reserved.
//

#if __has_include(<React/RCTUtils.h>)
#import <React/RCTUtils.h>
#else
#import "RCTUtils.h"
#endif
#import "DBChromeManager.h"

@implementation DBChromeManager

RCT_EXPORT_MODULE()

/**
 * Starts a Chrome for the given URL.
 * If Chrome is not installed, start Safari.
 */
RCT_EXPORT_METHOD(openURL:(NSString *)urlString
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
  NSURL *srcUrl = [NSURL URLWithString:urlString];
  NSString *chromeScheme = nil;
  if ([srcUrl.scheme isEqualToString:@"http"]) {
    chromeScheme = @"googlechrome";
  } else if ([srcUrl.scheme isEqualToString:@"https"]) {
    chromeScheme = @"googlechromes";
  } else {
    reject(@"Invalid URL", [NSString stringWithFormat:@"Allow only http or https: %@", urlString], nil);
  }
  
  if (chromeScheme) {
    NSString *absoluteString = [srcUrl absoluteString];
    NSRange rangeForScheme = [absoluteString rangeOfString:@":"];
    NSString *urlNoScheme = [absoluteString substringFromIndex:rangeForScheme.location];
    NSString *chromeURLString = [chromeScheme stringByAppendingString:urlNoScheme];
    NSURL *chromeURL = [NSURL URLWithString:chromeURLString];
    
    if ([RCTSharedApplication() canOpenURL:chromeURL]) {
      BOOL opend = [RCTSharedApplication() openURL:chromeURL];
      resolve(@(opend));
      return;
    }
  }
  
  // Open safari
  BOOL opend = [RCTSharedApplication() openURL:srcUrl];
  resolve(@(opend));
}

@end
