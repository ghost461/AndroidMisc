package org.chromium.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

public class HorizontalListDividerDrawable extends LayerDrawable {
    private HorizontalListDividerDrawable(Drawable[] arg1) {
        super(arg1);
    }

    public static HorizontalListDividerDrawable create(Context arg4) {
        TypedArray v4 = arg4.obtainStyledAttributes(new int[]{0x1010214});
        Drawable v1 = v4.getDrawable(0);
        v4.recycle();
        return new HorizontalListDividerDrawable(new Drawable[]{v1});
    }

    protected void onBoundsChange(Rect arg9) {
        this.setLayerInset(0, 0, arg9.height() - this.getDrawable(0).getIntrinsicHeight(), 0, 0);
        super.onBoundsChange(arg9);
    }
}

