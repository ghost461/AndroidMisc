package org.xwalk.core.internal;

import android.graphics.Bitmap;

@XWalkAPI(createExternally=true) public abstract class XWalkGetBitmapCallbackInternal {
    @XWalkAPI public XWalkGetBitmapCallbackInternal() {
        super();
    }

    @XWalkAPI public abstract void onFinishGetBitmap(Bitmap arg1, int arg2);
}

