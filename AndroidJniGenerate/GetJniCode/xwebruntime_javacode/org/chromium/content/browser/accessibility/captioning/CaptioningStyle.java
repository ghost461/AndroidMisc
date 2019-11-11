package org.chromium.content.browser.accessibility.captioning;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.view.accessibility.CaptioningManager$CaptionStyle;

@TargetApi(value=19) public class CaptioningStyle {
    private Integer mBackgroundColor;
    private Integer mEdgeColor;
    private Integer mEdgeType;
    private Integer mForegroundColor;
    private Typeface mTypeface;
    private Integer mWindowColor;

    public CaptioningStyle(Integer arg1, Integer arg2, Integer arg3, Integer arg4, Integer arg5, Typeface arg6) {
        super();
        this.mBackgroundColor = arg1;
        this.mEdgeColor = arg2;
        this.mEdgeType = arg3;
        this.mForegroundColor = arg4;
        this.mWindowColor = arg5;
        this.mTypeface = arg6;
    }

    @SuppressLint(value={"NewApi"}) public static CaptioningStyle createFrom(CaptioningManager$CaptionStyle arg12) {
        Integer v4;
        Integer v3;
        Integer v1;
        Integer v0;
        if(arg12 == null) {
            return new CaptioningStyle(null, null, null, null, null, null);
        }

        Integer v2 = null;
        if(Build$VERSION.SDK_INT >= 21) {
            v0 = arg12.hasBackgroundColor() ? Integer.valueOf(arg12.backgroundColor) : v2;
            v1 = arg12.hasEdgeColor() ? Integer.valueOf(arg12.edgeColor) : v2;
            v3 = arg12.hasEdgeType() ? Integer.valueOf(arg12.edgeType) : v2;
            v4 = arg12.hasForegroundColor() ? Integer.valueOf(arg12.foregroundColor) : v2;
            if(!arg12.hasWindowColor()) {
                goto label_53;
            }

            v2 = Integer.valueOf(arg12.windowColor);
        }
        else {
            v0 = Integer.valueOf(arg12.backgroundColor);
            v1 = Integer.valueOf(arg12.edgeColor);
            v3 = Integer.valueOf(arg12.edgeType);
            v4 = Integer.valueOf(arg12.foregroundColor);
        }

    label_53:
        return new CaptioningStyle(v0, v1, v3, v4, v2, arg12.getTypeface());
    }

    public Integer getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public Integer getEdgeColor() {
        return this.mEdgeColor;
    }

    public Integer getEdgeType() {
        return this.mEdgeType;
    }

    public Integer getForegroundColor() {
        return this.mForegroundColor;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public Integer getWindowColor() {
        return this.mWindowColor;
    }
}

