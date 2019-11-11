package org.xwalk.core.internal;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import java.io.File;

public abstract class XWalkLongScreenshotCallbackInternal {
    public XWalkLongScreenshotCallbackInternal() {
        super();
    }

    public File getCacheFileDir() {
        return null;
    }

    public int getMaxHeightSupported() {
        return 0;
    }

    public File getResultFileDir() {
        return null;
    }

    public void onLongScreenshotFinished(int arg1, @Nullable String arg2) {
    }

    public Bitmap overrideScreenshot(Bitmap arg1) {
        return arg1;
    }
}

