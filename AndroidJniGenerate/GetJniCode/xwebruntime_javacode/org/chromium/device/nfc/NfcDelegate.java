package org.chromium.device.nfc;

import org.chromium.base.Callback;

public interface NfcDelegate {
    void stopTrackingActivityForHost(int arg1);

    void trackActivityForHost(int arg1, Callback arg2);
}

