// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#ifndef IOS_CHROME_BROWSER_UI_ALERT_COORDINATOR_FORM_RESUBMISSION_COORDINATOR_H_
#define IOS_CHROME_BROWSER_UI_ALERT_COORDINATOR_FORM_RESUBMISSION_COORDINATOR_H_

#import <UIKit/UIKit.h>

#import "base/ios/block_types.h"
#import "ios/chrome/browser/chrome_coordinator.h"
#import "ios/web/public/web_state/web_state.h"

// Creates and manages Form Resubmission dialog that has Continue and Cancel
// buttons.
@interface FormResubmissionCoordinator : ChromeCoordinator

// Initializes a coordinator for displaying an alert on this |viewController|.
// |dialogLocation| is a point where resubmission dialog should be presented on
// iPad. |webState| must not be null and must be owned by the caller.
// |completionHandler| will be called with YES when Continue button is tapped
// and with NO when Cancel button is tapped. |completionHandler| can not be
// null.
- (instancetype)initWithBaseViewController:(UIViewController*)viewController
                            dialogLocation:(CGPoint)dialogLocation
                                  webState:(web::WebState*)webState
                         completionHandler:(void (^)(BOOL))completionHandler
    NS_DESIGNATED_INITIALIZER;

- (instancetype)initWithBaseViewController:(UIViewController*)viewController
    NS_UNAVAILABLE;

@end

#endif  // IOS_CHROME_BROWSER_UI_ALERT_COORDINATOR_FORM_RESUBMISSION_COORDINATOR_H_
