// Copyright 2016 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.chrome.browser.autofill;

import android.content.Context;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

/** Native bridge for credit card scanner. */
@JNINamespace("autofill")
public class CreditCardScannerBridge implements CreditCardScanner.Delegate {
    private final long mNativeScanner;
    private final CreditCardScanner mScanner;

    @CalledByNative
    private static CreditCardScannerBridge create(
            long nativeScanner, Context context, WebContents webContents) {
        return new CreditCardScannerBridge(nativeScanner, context, webContents);
    }

    private CreditCardScannerBridge(long nativeScanner, Context context, WebContents webContents) {
        mNativeScanner = nativeScanner;
        mScanner = CreditCardScanner.create(context, webContents, this);
    }

    @CalledByNative
    private boolean canScan() {
        return mScanner.canScan();
    }

    @CalledByNative
    private void scan() {
        mScanner.scan();
    }

    @Override
    public void onScanCancelled() {
        nativeScanCancelled(mNativeScanner);
    }

    @Override
    public void onScanCompleted(
            String cardHolderName, String cardNumber, int expirationMonth, int expirationYear) {
        nativeScanCompleted(
                mNativeScanner, cardHolderName, cardNumber, expirationMonth, expirationYear);
    }

    private native void nativeScanCancelled(long nativeCreditCardScannerViewAndroid);
    private native void nativeScanCompleted(long nativeCreditCardScannerViewAndroid,
            String cardHolderName, String cardNumber, int expirationMonth, int expirationYear);
}
