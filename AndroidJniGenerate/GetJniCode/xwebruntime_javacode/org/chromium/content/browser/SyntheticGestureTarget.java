package org.chromium.content.browser;

import android.view.View;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public class SyntheticGestureTarget {
    private final MotionEventSynthesizer mMotionEventSynthesizer;

    private SyntheticGestureTarget(View arg2) {
        super();
        this.mMotionEventSynthesizer = new MotionEventSynthesizer(arg2);
    }

    @CalledByNative private static SyntheticGestureTarget create(View arg1) {
        return new SyntheticGestureTarget(arg1);
    }

    @CalledByNative private void inject(int arg2, int arg3, long arg4) {
        this.mMotionEventSynthesizer.inject(arg2, arg3, arg4);
    }

    @CalledByNative private void setPointer(int arg2, int arg3, int arg4, int arg5) {
        this.mMotionEventSynthesizer.setPointer(arg2, arg3, arg4, arg5);
    }

    @CalledByNative private void setScrollDeltas(int arg2, int arg3, int arg4, int arg5) {
        this.mMotionEventSynthesizer.setScrollDeltas(arg2, arg3, arg4, arg5);
    }
}

