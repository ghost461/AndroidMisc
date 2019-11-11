package com.tencent.xweb.screenshot;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class PlaceHolderBitmap {
    private volatile boolean mCanBeRecycled;
    private final String mFilePath;
    private final int mHeight;
    @NonNull private final Bitmap mOrigin;
    private final int mTopOffset;
    private final int mWidth;

    public PlaceHolderBitmap(@NonNull Bitmap arg1, int arg2, @NonNull String arg3) {
        super();
        this.mOrigin = arg1;
        this.mHeight = this.mOrigin.getHeight();
        this.mWidth = this.mOrigin.getWidth();
        this.mTopOffset = arg2;
        this.mFilePath = arg3;
    }

    public Bitmap getBitmap() {
        return this.mOrigin;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getTopOffset() {
        return this.mTopOffset;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isRecycled() {
        return this.mOrigin.isRecycled();
    }

    public void markCanBeRecycled() {
        this.mCanBeRecycled = true;
    }

    public void recycleIfNeeded() {
        if(!this.isRecycled() && (this.mCanBeRecycled)) {
            this.mOrigin.recycle();
        }
    }
}

