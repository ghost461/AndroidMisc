package org.chromium.ui.resources.dynamics;

import android.graphics.Bitmap;
import android.graphics.Rect;
import org.chromium.ui.resources.ResourceFactory;
import org.chromium.ui.resources.statics.NinePatchData;

public class BitmapDynamicResource implements DynamicResource {
    private static final Rect EMPTY_RECT;
    private Bitmap mBitmap;
    private boolean mIsDirty;
    private final int mResId;
    private final Rect mSize;

    static {
        BitmapDynamicResource.EMPTY_RECT = new Rect();
    }

    public BitmapDynamicResource(int arg2) {
        super();
        this.mSize = new Rect();
        this.mIsDirty = true;
        this.mResId = arg2;
    }

    public long createNativeResource() {
        return ResourceFactory.createBitmapResource(null);
    }

    public Bitmap getBitmap() {
        this.mIsDirty = false;
        return this.mBitmap;
    }

    public Rect getBitmapSize() {
        return this.mSize;
    }

    public NinePatchData getNinePatchData() {
        return null;
    }

    public int getResId() {
        return this.mResId;
    }

    public boolean isDirty() {
        return this.mIsDirty;
    }

    public void setBitmap(Bitmap arg4) {
        if(arg4 == null) {
            return;
        }

        this.mIsDirty = true;
        if(this.mBitmap != null) {
            this.mBitmap.recycle();
        }

        this.mBitmap = arg4;
        this.mSize.set(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }
}

