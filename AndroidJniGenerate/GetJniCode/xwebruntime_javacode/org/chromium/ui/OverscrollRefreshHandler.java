package org.chromium.ui;

import org.chromium.base.annotations.CalledByNative;

public interface OverscrollRefreshHandler {
    @CalledByNative void pull(float arg1);

    @CalledByNative void release(boolean arg1);

    @CalledByNative void reset();

    void setEnabled(boolean arg1);

    @CalledByNative boolean start();
}

