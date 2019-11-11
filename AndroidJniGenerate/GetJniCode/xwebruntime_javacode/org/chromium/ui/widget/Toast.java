package org.chromium.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources$NotFoundException;
import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.base.SysUtils;

public class Toast {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT;
    private ViewGroup mSWLayout;
    private android.widget.Toast mToast;

    public Toast(Context arg2) {
        this(arg2, UiWidgetFactory.getInstance().createToast(arg2));
    }

    private Toast(Context arg3, android.widget.Toast arg4) {
        super();
        this.mToast = arg4;
        if((SysUtils.isLowEndDevice()) && Build$VERSION.SDK_INT >= 21) {
            this.mSWLayout = new FrameLayout(new ContextWrapper(arg3) {
                public ApplicationInfo getApplicationInfo() {
                    ApplicationInfo v0 = new ApplicationInfo(super.getApplicationInfo());
                    v0.targetSdkVersion = 19;
                    v0.flags &= 0xDFFFFFFF;
                    return v0;
                }
            });
            this.setView(arg4.getView());
        }
    }

    public void cancel() {
        this.mToast.cancel();
    }

    public android.widget.Toast getAndroidToast() {
        return this.mToast;
    }

    public int getDuration() {
        return this.mToast.getDuration();
    }

    public int getGravity() {
        return this.mToast.getGravity();
    }

    public float getHorizontalMargin() {
        return this.mToast.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return this.mToast.getVerticalMargin();
    }

    public View getView() {
        if(this.mToast.getView() == null) {
            return null;
        }

        if(this.mSWLayout != null) {
            return this.mSWLayout.getChildAt(0);
        }

        return this.mToast.getView();
    }

    public int getXOffset() {
        return this.mToast.getXOffset();
    }

    public int getYOffset() {
        return this.mToast.getYOffset();
    }

    @SuppressLint(value={"ShowToast"}) public static Toast makeText(Context arg2, CharSequence arg3, int arg4) {
        return new Toast(arg2, UiWidgetFactory.getInstance().makeToast(arg2, arg3, arg4));
    }

    public static Toast makeText(Context arg1, int arg2, int arg3) throws Resources$NotFoundException {
        return Toast.makeText(arg1, arg1.getResources().getText(arg2), arg3);
    }

    public void setDuration(int arg2) {
        this.mToast.setDuration(arg2);
    }

    public void setGravity(int arg2, int arg3, int arg4) {
        this.mToast.setGravity(arg2, arg3, arg4);
    }

    public void setMargin(float arg2, float arg3) {
        this.mToast.setMargin(arg2, arg3);
    }

    public void setText(int arg2) {
        this.mToast.setText(arg2);
    }

    public void setText(CharSequence arg2) {
        this.mToast.setText(arg2);
    }

    public void setView(View arg3) {
        if(this.mSWLayout != null) {
            this.mSWLayout.removeAllViews();
            if(arg3 != null) {
                this.mSWLayout.addView(arg3, -2, -2);
                this.mToast.setView(this.mSWLayout);
            }
            else {
                this.mToast.setView(null);
            }
        }
        else {
            this.mToast.setView(arg3);
        }
    }

    public void show() {
        this.mToast.show();
    }
}

