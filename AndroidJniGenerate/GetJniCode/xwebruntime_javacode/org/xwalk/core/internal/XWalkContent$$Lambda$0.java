package org.xwalk.core.internal;

import android.graphics.Bitmap;
import org.chromium.content_public.browser.ContentBitmapCallback;

final class XWalkContent$$Lambda$0 implements ContentBitmapCallback {
    private final XWalkContent arg$1;

    XWalkContent$$Lambda$0(XWalkContent arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void onFinishGetBitmap(Bitmap arg2) {
        this.arg$1.lambda$initCaptureBitmapAsync$0$XWalkContent(arg2);
    }
}

