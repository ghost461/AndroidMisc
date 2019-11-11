package org.chromium.device.power_save_blocker;

import android.view.View;
import java.lang.ref.WeakReference;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") class PowerSaveBlocker {
    private WeakReference mKeepScreenOnView;

    static {
    }

    private PowerSaveBlocker() {
        super();
    }

    @CalledByNative private void applyBlock(View arg2) {
        this.mKeepScreenOnView = new WeakReference(arg2);
        arg2.setKeepScreenOn(true);
    }

    @CalledByNative private static PowerSaveBlocker create() {
        return new PowerSaveBlocker();
    }

    @CalledByNative private void removeBlock() {
        if(this.mKeepScreenOnView == null) {
            return;
        }

        Object v0 = this.mKeepScreenOnView.get();
        this.mKeepScreenOnView = null;
        if(v0 == null) {
            return;
        }

        ((View)v0).setKeepScreenOn(false);
    }
}

