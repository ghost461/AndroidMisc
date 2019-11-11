package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R$styleable;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class DialogTitle extends TextView {
    public DialogTitle(Context arg1) {
        super(arg1);
    }

    public DialogTitle(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    public DialogTitle(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
    }

    protected void onMeasure(int arg7, int arg8) {
        super.onMeasure(arg7, arg8);
        Layout v0 = this.getLayout();
        if(v0 != null) {
            int v1 = v0.getLineCount();
            if(v1 > 0 && v0.getEllipsisCount(v1 - 1) > 0) {
                this.setSingleLine(false);
                this.setMaxLines(2);
                TypedArray v1_1 = this.getContext().obtainStyledAttributes(null, styleable.TextAppearance, 0x1010041, 0x1030044);
                int v2 = v1_1.getDimensionPixelSize(styleable.TextAppearance_android_textSize, 0);
                if(v2 != 0) {
                    this.setTextSize(0, ((float)v2));
                }

                v1_1.recycle();
                super.onMeasure(arg7, arg8);
            }
        }
    }
}

