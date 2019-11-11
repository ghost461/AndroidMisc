package org.chromium.ui.widget;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import org.xwalk.core.R$styleable;

@TargetApi(value=21) public class ButtonCompat extends Button {
    private static final int DISABLED_COLOR = 0x424F4F4F;
    private static final float PRE_L_PRESSED_BRIGHTNESS = 0.85f;
    private int mColor;

    public ButtonCompat(Context arg2, int arg3, boolean arg4) {
        this(arg2, arg3, arg4, null);
    }

    private ButtonCompat(Context arg3, int arg4, boolean arg5, AttributeSet arg6) {
        super(new ContextThemeWrapper(arg3, 0x7F0D00A8), arg6);
        this.getBackground().mutate();
        this.setButtonColor(arg4);
        this.setRaised(arg5);
    }

    public ButtonCompat(Context arg3, AttributeSet arg4) {
        this(arg3, ButtonCompat.getColorFromAttributeSet(arg3, arg4), ButtonCompat.getRaisedStatusFromAttributeSet(arg3, arg4), arg4);
    }

    public static Button createBorderlessButton(Context arg3) {
        return new Button(new ContextThemeWrapper(arg3, 0x7F0D00A7));
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(Build$VERSION.SDK_INT < 21) {
            this.updateButtonBackgroundPreL();
        }
    }

    private int getBackgroundColorPreL() {
        int[] v0 = this.getDrawableState();
        int v1 = v0.length;
        int v2 = 0;
        int v3 = 0;
        while(true) {
            if(v3 >= v1) {
                goto label_33;
            }

            int v4 = v0[v3];
            if(v4 != 0x10100A7 && v4 != 0x101009C) {
                if(v4 == 0x10100A1) {
                }
                else {
                    ++v3;
                    continue;
                }
            }

            break;
        }

        return Color.rgb(Math.round((((float)Color.red(this.mColor))) * 0.85f), Math.round((((float)Color.green(this.mColor))) * 0.85f), Math.round((((float)Color.blue(this.mColor))) * 0.85f));
    label_33:
        v0 = this.getDrawableState();
        v1 = v0.length;
        while(v2 < v1) {
            if(v0[v2] == 0x101009E) {
                return this.mColor;
            }

            ++v2;
        }

        return 0x424F4F4F;
    }

    private static int getColorFromAttributeSet(Context arg2, AttributeSet arg3) {
        TypedArray v2 = arg2.obtainStyledAttributes(arg3, styleable.ButtonCompat, 0, 0);
        int v3 = v2.getColor(0, -1);
        v2.recycle();
        return v3;
    }

    private static boolean getRaisedStatusFromAttributeSet(Context arg2, AttributeSet arg3) {
        TypedArray v2 = arg2.obtainStyledAttributes(arg3, styleable.ButtonCompat, 0, 0);
        boolean v3 = v2.getBoolean(1, true);
        v2.recycle();
        return v3;
    }

    public void setButtonColor(int arg2) {
        if(arg2 == this.mColor) {
            return;
        }

        this.mColor = arg2;
        if(Build$VERSION.SDK_INT >= 21) {
            this.updateButtonBackgroundL();
        }
        else {
            this.updateButtonBackgroundPreL();
        }
    }

    private void setRaised(boolean arg5) {
        if(Build$VERSION.SDK_INT < 21) {
            return;
        }

        AttributeSet v0 = null;
        if(arg5) {
            TypedArray v5 = this.getContext().obtainStyledAttributes(v0, new int[]{0x1010448}, 0, 0x1030258);
            int v1 = v5.getResourceId(0, 0);
            v5.recycle();
            if(v1 != 0) {
                StateListAnimator v0_1 = AnimatorInflater.loadStateListAnimator(this.getContext(), v1);
            }

            this.setStateListAnimator(((StateListAnimator)v0));
        }
        else {
            this.setElevation(0f);
            this.setStateListAnimator(((StateListAnimator)v0));
        }
    }

    @TargetApi(value=21) private void updateButtonBackgroundL() {
        ColorStateList v0 = new ColorStateList(new int[][]{new int[]{0xFEFEFF62}, new int[0]}, new int[]{0x424F4F4F, this.mColor});
        Drawable v1 = this.getBackground().getDrawable(0);
        ((GradientDrawable)v1).mutate();
        ((GradientDrawable)v1).setColor(v0);
    }

    private void updateButtonBackgroundPreL() {
        this.getBackground().setColor(this.getBackgroundColorPreL());
    }
}

