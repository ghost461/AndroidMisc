package org.chromium.base;

import org.chromium.base.annotations.CalledByNative;

public interface Callback {
    public abstract class Helper {
        public Helper() {
            super();
        }

        @CalledByNative(value="Helper") static void onBooleanResultFromNative(Callback arg0, boolean arg1) {
            arg0.onResult(Boolean.valueOf(arg1));
        }

        @CalledByNative(value="Helper") static void onIntResultFromNative(Callback arg0, int arg1) {
            arg0.onResult(Integer.valueOf(arg1));
        }

        @CalledByNative(value="Helper") static void onObjectResultFromNative(Callback arg0, Object arg1) {
            arg0.onResult(arg1);
        }
    }

    void onResult(Object arg1);
}

