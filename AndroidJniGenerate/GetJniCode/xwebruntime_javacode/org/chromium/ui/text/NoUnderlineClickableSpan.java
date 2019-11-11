package org.chromium.ui.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;

public abstract class NoUnderlineClickableSpan extends ClickableSpan {
    private final int mColor;

    public NoUnderlineClickableSpan() {
        super();
        this.mColor = ApiCompatibilityUtils.getColor(ContextUtils.getApplicationContext().getResources(), 0x7F040035);
    }

    public void updateDrawState(TextPaint arg2) {
        super.updateDrawState(arg2);
        arg2.setUnderlineText(false);
        arg2.setColor(this.mColor);
    }
}

