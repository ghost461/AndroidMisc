package org.xwalk.core.internal;

import android.graphics.Bitmap;
import com.tencent.xwebview.bridgeprocessor.util.ReflectMethod;
import java.io.File;

public final class XWalkLongScreenshotCallbackBridge extends XWalkLongScreenshotCallbackInternal {
    private final ReflectMethod getCacheFileDirMethod;
    private final ReflectMethod getMaxHeightSupportedMethod;
    private final ReflectMethod getResultFileDirMethod;
    private Object mWrapper;
    private final ReflectMethod onLongScreenshotFinishedMethod;
    private final ReflectMethod overrideScreenshotMethod;

    public XWalkLongScreenshotCallbackBridge(Object arg6) {
        super();
        this.onLongScreenshotFinishedMethod = new ReflectMethod(null, "onLongScreenshotFinished", new Class[0]);
        this.getCacheFileDirMethod = new ReflectMethod(null, "getCacheFileDir", new Class[0]);
        this.getResultFileDirMethod = new ReflectMethod(null, "getResultFileDir", new Class[0]);
        this.overrideScreenshotMethod = new ReflectMethod(null, "overrideScreenshot", new Class[0]);
        this.getMaxHeightSupportedMethod = new ReflectMethod(null, "getMaxHeightSupported", new Class[0]);
        this.mWrapper = arg6;
        this.reflectInit();
    }

    public File getCacheFileDir() {
        if(this.getCacheFileDirMethod.isNull()) {
            return this.getCacheFileDirSuper();
        }

        return this.getCacheFileDirMethod.invoke(new Object[0]);
    }

    public File getCacheFileDirSuper() {
        return super.getCacheFileDir();
    }

    public int getMaxHeightSupported() {
        if(this.getMaxHeightSupportedMethod.isNull()) {
            return this.getMaxHeightSupportedSuper();
        }

        return this.getMaxHeightSupportedMethod.invoke(new Object[0]).intValue();
    }

    public int getMaxHeightSupportedSuper() {
        return super.getMaxHeightSupported();
    }

    public File getResultFileDir() {
        if(this.getResultFileDirMethod.isNull()) {
            return this.getResultFileDirSuper();
        }

        return this.getResultFileDirMethod.invoke(new Object[0]);
    }

    public File getResultFileDirSuper() {
        return super.getResultFileDir();
    }

    public void onLongScreenshotFinished(int arg4, String arg5) {
        if(this.onLongScreenshotFinishedMethod.isNull()) {
            this.onLongScreenshotFinishedSuper(arg4, arg5);
        }
        else {
            this.onLongScreenshotFinishedMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
        }
    }

    public void onLongScreenshotFinishedSuper(int arg1, String arg2) {
        super.onLongScreenshotFinished(arg1, arg2);
    }

    public Bitmap overrideScreenshot(Bitmap arg4) {
        if(this.overrideScreenshotMethod.isNull()) {
            return this.overrideScreenshotSuper(arg4);
        }

        return this.overrideScreenshotMethod.invoke(new Object[]{arg4});
    }

    public Bitmap overrideScreenshotSuper(Bitmap arg1) {
        return super.overrideScreenshot(arg1);
    }

    private void reflectInit() {
        this.onLongScreenshotFinishedMethod.init(this.mWrapper, null, "onLongScreenshotFinished", new Class[]{Integer.TYPE, String.class});
        this.getCacheFileDirMethod.init(this.mWrapper, null, "getCacheFileDir", new Class[0]);
        this.getResultFileDirMethod.init(this.mWrapper, null, "getResultFileDir", new Class[0]);
        this.overrideScreenshotMethod.init(this.mWrapper, null, "overrideScreenshot", new Class[]{Bitmap.class});
        this.getMaxHeightSupportedMethod.init(this.mWrapper, null, "getMaxHeightSupported", new Class[0]);
    }
}

