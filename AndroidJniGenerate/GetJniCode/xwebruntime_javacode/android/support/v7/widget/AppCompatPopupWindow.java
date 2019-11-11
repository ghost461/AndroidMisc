package android.support.v7.widget;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR = false;
    private static final String TAG = "AppCompatPopupWindow";
    private boolean mOverlapAnchor;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 21 ? true : false;
        AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR = v0;
    }

    public AppCompatPopupWindow(@NonNull Context arg2, @Nullable AttributeSet arg3, @AttrRes int arg4) {
        super(arg2, arg3, arg4);
        this.init(arg2, arg3, arg4, 0);
    }

    public AppCompatPopupWindow(@NonNull Context arg1, @Nullable AttributeSet arg2, @AttrRes int arg3, @StyleRes int arg4) {
        super(arg1, arg2, arg3, arg4);
        this.init(arg1, arg2, arg3, arg4);
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean getSupportOverlapAnchor() {
        if(AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            return this.mOverlapAnchor;
        }

        return PopupWindowCompat.getOverlapAnchor(((PopupWindow)this));
    }

    private void init(Context arg2, AttributeSet arg3, int arg4, int arg5) {
        TintTypedArray v2 = TintTypedArray.obtainStyledAttributes(arg2, arg3, styleable.PopupWindow, arg4, arg5);
        if(v2.hasValue(styleable.PopupWindow_overlapAnchor)) {
            this.setSupportOverlapAnchor(v2.getBoolean(styleable.PopupWindow_overlapAnchor, false));
        }

        this.setBackgroundDrawable(v2.getDrawable(styleable.PopupWindow_android_popupBackground));
        int v3 = Build$VERSION.SDK_INT;
        if(arg5 != 0 && v3 < 11 && (v2.hasValue(styleable.PopupWindow_android_popupAnimationStyle))) {
            this.setAnimationStyle(v2.getResourceId(styleable.PopupWindow_android_popupAnimationStyle, -1));
        }

        v2.recycle();
        if(Build$VERSION.SDK_INT < 14) {
            AppCompatPopupWindow.wrapOnScrollChangedListener(((PopupWindow)this));
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setSupportOverlapAnchor(boolean arg2) {
        if(AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = arg2;
        }
        else {
            PopupWindowCompat.setOverlapAnchor(((PopupWindow)this), arg2);
        }
    }

    public void showAsDropDown(View arg2, int arg3, int arg4) {
        if((AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) && (this.mOverlapAnchor)) {
            arg4 -= arg2.getHeight();
        }

        super.showAsDropDown(arg2, arg3, arg4);
    }

    public void showAsDropDown(View arg2, int arg3, int arg4, int arg5) {
        if((AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) && (this.mOverlapAnchor)) {
            arg4 -= arg2.getHeight();
        }

        super.showAsDropDown(arg2, arg3, arg4, arg5);
    }

    public void update(View arg7, int arg8, int arg9, int arg10, int arg11) {
        if((AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) && (this.mOverlapAnchor)) {
            arg9 -= arg7.getHeight();
        }

        super.update(arg7, arg8, arg9, arg10, arg11);
    }

    private static void wrapOnScrollChangedListener(PopupWindow arg4) {
        try {
            Field v0 = PopupWindow.class.getDeclaredField("mAnchor");
            v0.setAccessible(true);
            Field v2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            v2.setAccessible(true);
            v2.set(arg4, new ViewTreeObserver$OnScrollChangedListener(v0, arg4, v2.get(arg4)) {
                public void onScrollChanged() {
                    try {
                        Object v0 = this.val$fieldAnchor.get(this.val$popup);
                        if(v0 != null) {
                            if(((WeakReference)v0).get() == null) {
                            }
                            else {
                                this.val$originalListener.onScrollChanged();
                                return;
                            }
                        }
                    }
                    catch(IllegalAccessException ) {
                        return;
                    }
                }
            });
        }
        catch(Exception v4) {
            Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", ((Throwable)v4));
        }
    }
}

