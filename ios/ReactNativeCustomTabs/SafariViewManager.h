//
//  SafariViewManager.h
//  DBCustomTabs
//
//  Created by Avinash on 10/12/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RCTEventEmitter.h"
#import <React/RCTBridgeModule.h>

@import SafariServices;

NS_ASSUME_NONNULL_BEGIN

@interface SafariViewManager : RCTEventEmitter<RCTBridgeModule, SFSafariViewControllerDelegate>

@end

NS_ASSUME_NONNULL_END
