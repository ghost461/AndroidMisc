package org.chromium.content.browser;

import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.device.nfc.NfcDelegate;

public class ContentNfcDelegate implements NfcDelegate {
    public ContentNfcDelegate() {
        super();
    }

    @CalledByNative private static ContentNfcDelegate create() {
        return new ContentNfcDelegate();
    }

    public void stopTrackingActivityForHost(int arg1) {
        NfcHost v1 = NfcHost.fromContextId(arg1);
        if(v1 == null) {
            return;
        }

        v1.stopTrackingActivityChanges();
    }

    public void trackActivityForHost(int arg1, Callback arg2) {
        NfcHost v1 = NfcHost.fromContextId(arg1);
        if(v1 == null) {
            return;
        }

        v1.trackActivityChanges(arg2);
    }
}

