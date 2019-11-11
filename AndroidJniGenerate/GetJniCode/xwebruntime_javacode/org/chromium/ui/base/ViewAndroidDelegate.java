package org.chromium.ui.base;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View$DragShadowBuilder;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.ImageView;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="ui") public abstract class ViewAndroidDelegate {
    private int[] mTemporaryContainerLocation;

    static {
    }

    public ViewAndroidDelegate() {
        super();
        this.mTemporaryContainerLocation = new int[2];
    }

    @CalledByNative public void OnGetTranslateString(Object arg1) {
    }

    @CalledByNative public View acquireView() {
        ViewGroup v0 = this.getContainerView();
        if(v0 != null) {
            if(v0.getParent() == null) {
            }
            else {
                View v1 = new View(v0.getContext());
                v0.addView(v1);
                return v1;
            }
        }

        return null;
    }

    public static ViewAndroidDelegate createBasicDelegate(ViewGroup arg1) {
        return org.chromium.ui.base.ViewAndroidDelegate$1.access$000(new ViewAndroidDelegate() {
            private ViewGroup mContainerView;

            static ViewAndroidDelegate access$000(org.chromium.ui.base.ViewAndroidDelegate$1 arg0, ViewGroup arg1) {
                return arg0.init(arg1);
            }

            public ViewGroup getContainerView() {
                return this.mContainerView;
            }

            private ViewAndroidDelegate init(ViewGroup arg1) {
                this.mContainerView = arg1;
                return this;
            }
        }, arg1);
    }

    @CalledByNative public abstract ViewGroup getContainerView();

    @CalledByNative public int getSystemWindowInsetBottom() {
        return 0;
    }

    @CalledByNative private int getXLocationOfContainerViewInWindow() {
        ViewGroup v0 = this.getContainerView();
        if(v0 == null) {
            return 0;
        }

        v0.getLocationInWindow(this.mTemporaryContainerLocation);
        return this.mTemporaryContainerLocation[0];
    }

    @CalledByNative private int getXLocationOnScreen() {
        ViewGroup v0 = this.getContainerView();
        if(v0 == null) {
            return 0;
        }

        v0.getLocationOnScreen(this.mTemporaryContainerLocation);
        return this.mTemporaryContainerLocation[0];
    }

    @CalledByNative private int getYLocationOfContainerViewInWindow() {
        ViewGroup v0 = this.getContainerView();
        if(v0 == null) {
            return 0;
        }

        v0.getLocationInWindow(this.mTemporaryContainerLocation);
        return this.mTemporaryContainerLocation[1];
    }

    @CalledByNative private int getYLocationOnScreen() {
        ViewGroup v0 = this.getContainerView();
        if(v0 == null) {
            return 0;
        }

        v0.getLocationOnScreen(this.mTemporaryContainerLocation);
        return this.mTemporaryContainerLocation[1];
    }

    @CalledByNative private boolean hasFocus() {
        ViewGroup v0 = this.getContainerView();
        boolean v0_1 = v0 == null ? false : ViewUtils.hasFocus(((View)v0));
        return v0_1;
    }

    @CalledByNative public void onBackgroundColorChanged(int arg1) {
    }

    @CalledByNative public void onBottomControlsChanged(float arg1, float arg2) {
    }

    @VisibleForTesting @CalledByNative public void onCursorChanged(int arg8) {
        if(Build$VERSION.SDK_INT < 24) {
            return;
        }

        int v0 = 1004;
        switch(arg8) {
            case 1: {
                v0 = 1007;
                break;
            }
            case 2: {
                v0 = 1002;
                break;
            }
            case 3: {
                v0 = 0x3F0;
                break;
            }
            case 5: {
                v0 = 1003;
                break;
            }
            case 8: 
            case 12: 
            case 16: {
                v0 = 0x3F8;
                break;
            }
            case 9: 
            case 11: 
            case 17: {
                v0 = 0x3F9;
                break;
            }
            case 6: 
            case 13: 
            case 15: 
            case 18: {
                v0 = 0x3F6;
                break;
            }
            case 7: 
            case 10: 
            case 14: 
            case 19: {
                v0 = 0x3F7;
                break;
            }
            case 20: 
            case 29: {
                v0 = 0x3F5;
                break;
            }
            case 30: {
                v0 = 1009;
                break;
            }
            case 31: {
                v0 = 1006;
                break;
            }
            case 32: {
                v0 = 1001;
                break;
            }
            case 33: {
                v0 = 1010;
                break;
            }
            case 4: 
            case 34: {
                break;
            }
            case 36: {
                v0 = 0x3F3;
                break;
            }
            case 37: {
                v0 = 0;
                break;
            }
            case 35: 
            case 38: {
                v0 = 0x3F4;
                break;
            }
            case 39: {
                v0 = 0x3FA;
                break;
            }
            case 40: {
                v0 = 0x3FB;
                break;
            }
            case 41: {
                v0 = 1020;
                break;
            }
            case 42: {
                v0 = 0x3FD;
                break;
            }
            default: {
                v0 = 1000;
                break;
            }
        }

        ViewGroup v8 = this.getContainerView();
        v8.setPointerIcon(PointerIcon.getSystemIcon(v8.getContext(), v0));
    }

    @VisibleForTesting @CalledByNative public void onCursorChangedToCustom(Bitmap arg3, int arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 24) {
            this.getContainerView().setPointerIcon(PointerIcon.create(arg3, ((float)arg4), ((float)arg5)));
        }
    }

    @CalledByNative public void onTopControlsChanged(float arg1, float arg2) {
    }

    @CalledByNative public void removeView(View arg2) {
        ViewGroup v0 = this.getContainerView();
        if(v0 == null) {
            return;
        }

        v0.removeView(arg2);
    }

    @CalledByNative private void requestDisallowInterceptTouchEvent() {
        ViewGroup v0 = this.getContainerView();
        if(v0 != null) {
            v0.requestDisallowInterceptTouchEvent(true);
        }
    }

    @CalledByNative private void requestFocus() {
        ViewGroup v0 = this.getContainerView();
        if(v0 != null) {
            ViewUtils.requestFocus(((View)v0));
        }
    }

    @TargetApi(value=21) @CalledByNative private void requestUnbufferedDispatch(MotionEvent arg2) {
        ViewGroup v0 = this.getContainerView();
        if(v0 != null) {
            v0.requestUnbufferedDispatch(arg2);
        }
    }

    @CalledByNative private void setTitle(String arg1) {
    }

    @CalledByNative public void setViewPosition(View arg3, float arg4, float arg5, float arg6, float arg7, int arg8, int arg9) {
        ViewGroup v5 = this.getContainerView();
        if(v5 == null) {
            return;
        }

        int v0 = Math.round(arg6);
        int v7 = Math.round(arg7);
        if(ApiCompatibilityUtils.isLayoutRtl(((View)v5))) {
            arg8 = v5.getMeasuredWidth() - Math.round(arg6 + arg4);
        }

        if(v0 + arg8 > v5.getWidth()) {
            v0 = v5.getWidth() - arg8;
        }

        FrameLayout$LayoutParams v4 = new FrameLayout$LayoutParams(v0, v7);
        ApiCompatibilityUtils.setMarginStart(((ViewGroup$MarginLayoutParams)v4), arg8);
        v4.topMargin = arg9;
        arg3.setLayoutParams(((ViewGroup$LayoutParams)v4));
    }

    @TargetApi(value=24) @CalledByNative private boolean startDragAndDrop(String arg5, Bitmap arg6) {
        if(Build$VERSION.SDK_INT <= 23) {
            return 0;
        }

        ViewGroup v0 = this.getContainerView();
        if(v0 == null) {
            return 0;
        }

        ImageView v2 = new ImageView(v0.getContext());
        v2.setImageBitmap(arg6);
        v2.layout(0, 0, arg6.getWidth(), arg6.getHeight());
        return v0.startDragAndDrop(ClipData.newPlainText(null, ((CharSequence)arg5)), new View$DragShadowBuilder(((View)v2)), null, 0x100);
    }
}

