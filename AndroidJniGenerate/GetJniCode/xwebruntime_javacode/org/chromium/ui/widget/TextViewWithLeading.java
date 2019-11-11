package org.chromium.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import org.xwalk.core.R$styleable;

public class TextViewWithLeading extends TextView {
    protected TextViewWithLeading(Context arg1) {
        super(arg1);
    }

    public TextViewWithLeading(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        TypedArray v3 = arg3.obtainStyledAttributes(arg4, styleable.TextViewWithLeading, 0, 0);
        if(v3.hasValue(0)) {
            this.setLineSpacing(v3.getDimension(0, 0f) - this.getPaint().getFontMetrics(null), 1f);
        }

        v3.recycle();
    }
}

