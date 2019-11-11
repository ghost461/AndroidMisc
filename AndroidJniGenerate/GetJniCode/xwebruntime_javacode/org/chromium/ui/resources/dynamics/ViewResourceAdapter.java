package org.chromium.ui.resources.dynamics;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View$OnLayoutChangeListener;
import android.view.View;
import org.chromium.base.TraceEvent;
import org.chromium.ui.resources.ResourceFactory;
import org.chromium.ui.resources.statics.NinePatchData;

public class ViewResourceAdapter implements View$OnLayoutChangeListener, DynamicResource {
    private Bitmap mBitmap;
    private Rect mBitmapSize;
    private final Rect mDirtyRect;
    private final View mView;

    static {
    }

    public ViewResourceAdapter(View arg2) {
        super();
        this.mDirtyRect = new Rect();
        this.mBitmapSize = new Rect();
        this.mView = arg2;
        this.mView.addOnLayoutChangeListener(((View$OnLayoutChangeListener)this));
    }

    protected void capture(Canvas arg2) {
        this.mView.draw(arg2);
    }

    public long createNativeResource() {
        return ResourceFactory.createBitmapResource(null);
    }

    public void dropCachedBitmap() {
        this.mBitmap = null;
    }

    public Bitmap getBitmap() {
        if(!this.isDirty()) {
            return this.mBitmap;
        }

        TraceEvent.begin("ViewResourceAdapter:getBitmap");
        if(this.validateBitmap()) {
            Canvas v0 = new Canvas(this.mBitmap);
            Rect v1 = this.mDirtyRect.isEmpty() ? null : this.mDirtyRect;
            this.onCaptureStart(v0, v1);
            if(!this.mDirtyRect.isEmpty()) {
                v0.clipRect(this.mDirtyRect);
            }

            this.capture(v0);
            this.onCaptureEnd();
        }
        else {
            this.mBitmap.setPixel(0, 0, 0);
        }

        this.mDirtyRect.setEmpty();
        TraceEvent.end("ViewResourceAdapter:getBitmap");
        return this.mBitmap;
    }

    public Rect getBitmapSize() {
        return this.mBitmapSize;
    }

    protected Rect getDirtyRect() {
        return this.mDirtyRect;
    }

    public final NinePatchData getNinePatchData() {
        return null;
    }

    public void invalidate(Rect arg4) {
        if(arg4 == null) {
            this.mDirtyRect.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
        }
        else {
            this.mDirtyRect.union(arg4);
        }
    }

    public boolean isDirty() {
        if(this.mBitmap == null) {
            this.mDirtyRect.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
        }

        return this.mDirtyRect.isEmpty() ^ 1;
    }

    protected void onCaptureEnd() {
    }

    protected void onCaptureStart(Canvas arg1, Rect arg2) {
    }

    public void onLayoutChange(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
        arg4 -= arg2;
        arg5 -= arg3;
        arg9 -= arg7;
        if(arg4 != arg8 - arg6 || arg5 != arg9) {
            this.mDirtyRect.set(0, 0, arg4, arg5);
        }
    }

    private boolean validateBitmap() {
        int v0 = this.mView.getWidth();
        int v1 = this.mView.getHeight();
        int v4 = v0 == 0 || v1 == 0 ? 1 : 0;
        if(v4 != 0) {
            v0 = 1;
            v1 = 1;
        }

        if(this.mBitmap != null && (this.mBitmap.getWidth() != v0 || this.mBitmap.getHeight() != v1)) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }

        if(this.mBitmap == null) {
            this.mBitmap = Bitmap.createBitmap(v0, v1, Bitmap$Config.ARGB_8888);
            this.mBitmap.setHasAlpha(true);
            this.mDirtyRect.set(0, 0, v0, v1);
            this.mBitmapSize.set(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
        }

        return v4 ^ 1;
    }
}

