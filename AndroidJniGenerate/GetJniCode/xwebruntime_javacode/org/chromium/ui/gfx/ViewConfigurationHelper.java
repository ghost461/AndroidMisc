package org.chromium.ui.gfx;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="gfx") public class ViewConfigurationHelper {
    private static final float MIN_SCALING_SPAN_MM = 12f;
    private float mDensity;
    private ViewConfiguration mViewConfiguration;

    static {
    }

    private ViewConfigurationHelper() {
        super();
        this.mViewConfiguration = ViewConfiguration.get(ContextUtils.getApplicationContext());
        this.mDensity = ContextUtils.getApplicationContext().getResources().getDisplayMetrics().density;
    }

    static void access$000(ViewConfigurationHelper arg0) {
        arg0.updateNativeViewConfigurationIfNecessary();
    }

    @CalledByNative private static ViewConfigurationHelper createWithListener() {
        ViewConfigurationHelper v0 = new ViewConfigurationHelper();
        v0.registerListener();
        return v0;
    }

    @CalledByNative private float getDoubleTapSlop() {
        return this.toDips(this.mViewConfiguration.getScaledDoubleTapSlop());
    }

    @CalledByNative private static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    @CalledByNative private static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    @CalledByNative private float getMaximumFlingVelocity() {
        return this.toDips(this.mViewConfiguration.getScaledMaximumFlingVelocity());
    }

    @CalledByNative private float getMinScalingSpan() {
        return this.toDips(this.getScaledMinScalingSpan());
    }

    @CalledByNative private float getMinimumFlingVelocity() {
        return this.toDips(this.mViewConfiguration.getScaledMinimumFlingVelocity());
    }

    private int getScaledMinScalingSpan() {
        Resources v0 = ContextUtils.getApplicationContext().getResources();
        int v1 = 0x7F050051;
        try {
            return v0.getDimensionPixelSize(v1);
        }
        catch(Resources$NotFoundException ) {
            return ((int)TypedValue.applyDimension(5, 12f, v0.getDisplayMetrics()));
        }
    }

    @CalledByNative private static int getTapTimeout() {
        return ViewConfiguration.getTapTimeout();
    }

    @CalledByNative private float getTouchSlop() {
        return this.toDips(this.mViewConfiguration.getScaledTouchSlop());
    }

    private native void nativeUpdateSharedViewConfiguration(float arg1, float arg2, float arg3, float arg4, float arg5) {
    }

    private void registerListener() {
        ContextUtils.getApplicationContext().registerComponentCallbacks(new ComponentCallbacks() {
            public void onConfigurationChanged(Configuration arg1) {
                ViewConfigurationHelper.this.updateNativeViewConfigurationIfNecessary();
            }

            public void onLowMemory() {
            }
        });
    }

    private float toDips(int arg2) {
        return (((float)arg2)) / this.mDensity;
    }

    private void updateNativeViewConfigurationIfNecessary() {
        ViewConfiguration v0 = ViewConfiguration.get(ContextUtils.getApplicationContext());
        if(this.mViewConfiguration == v0) {
            return;
        }

        this.mViewConfiguration = v0;
        this.mDensity = ContextUtils.getApplicationContext().getResources().getDisplayMetrics().density;
        this.nativeUpdateSharedViewConfiguration(this.getMaximumFlingVelocity(), this.getMinimumFlingVelocity(), this.getTouchSlop(), this.getDoubleTapSlop(), this.getMinScalingSpan());
    }
}

