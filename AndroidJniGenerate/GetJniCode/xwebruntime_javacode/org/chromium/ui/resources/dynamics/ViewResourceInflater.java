package org.chromium.ui.resources.dynamics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnDrawListener;

public class ViewResourceInflater {
    class org.chromium.ui.resources.dynamics.ViewResourceInflater$1 {
    }

    class ViewInflaterAdapter extends ViewResourceAdapter {
        public ViewInflaterAdapter(ViewResourceInflater arg1, View arg2) {
            ViewResourceInflater.this = arg1;
            super(arg2);
        }

        protected void onCaptureEnd() {
            ViewResourceInflater.this.onCaptureEnd();
        }
    }

    class ViewInflaterOnDrawListener implements ViewTreeObserver$OnDrawListener {
        ViewInflaterOnDrawListener(ViewResourceInflater arg1, org.chromium.ui.resources.dynamics.ViewResourceInflater$1 arg2) {
            this(arg1);
        }

        private ViewInflaterOnDrawListener(ViewResourceInflater arg1) {
            ViewResourceInflater.this = arg1;
            super();
        }

        public void onDraw() {
            ViewResourceInflater.this.invalidateResource();
        }
    }

    private static final int INVALID_ID = -1;
    private ViewGroup mContainer;
    private Context mContext;
    private boolean mIsAttached;
    private boolean mIsInvalidated;
    private int mLayoutId;
    private boolean mNeedsLayoutUpdate;
    private ViewInflaterOnDrawListener mOnDrawListener;
    private ViewResourceAdapter mResourceAdapter;
    private DynamicResourceLoader mResourceLoader;
    private View mView;
    private int mViewId;

    static {
    }

    public ViewResourceInflater(int arg1, int arg2, Context arg3, ViewGroup arg4, DynamicResourceLoader arg5) {
        super();
        this.mLayoutId = arg1;
        this.mViewId = arg2;
        this.mContext = arg3;
        this.mContainer = arg4;
        this.mResourceLoader = arg5;
    }

    static void access$100(ViewResourceInflater arg0) {
        arg0.invalidateResource();
    }

    private void attachView() {
        if(!this.mIsAttached) {
            this.mContainer.addView(this.mView);
            this.mIsAttached = true;
            if(this.mOnDrawListener == null) {
                this.mOnDrawListener = new ViewInflaterOnDrawListener(this, null);
                this.mView.getViewTreeObserver().addOnDrawListener(this.mOnDrawListener);
            }
        }
    }

    public void destroy() {
        if(this.mView == null) {
            return;
        }

        this.unregisterResource();
        this.detachView();
        this.mView = null;
        this.mLayoutId = -1;
        this.mViewId = -1;
        this.mContext = null;
        this.mContainer = null;
        this.mResourceLoader = null;
    }

    private void detachView() {
        if(this.mIsAttached) {
            if(this.mOnDrawListener != null) {
                this.mView.getViewTreeObserver().removeOnDrawListener(this.mOnDrawListener);
                this.mOnDrawListener = null;
            }

            this.mContainer.removeView(this.mView);
            this.mIsAttached = false;
        }
    }

    protected Context getContext() {
        return this.mContext;
    }

    protected int getHeightMeasureSpec() {
        return this.getUnspecifiedMeasureSpec();
    }

    public int getMeasuredHeight() {
        return this.mView.getMeasuredHeight();
    }

    public int getMeasuredWidth() {
        return this.mView.getMeasuredWidth();
    }

    private int getUnspecifiedMeasureSpec() {
        return View$MeasureSpec.makeMeasureSpec(0, 0);
    }

    protected View getView() {
        return this.mView;
    }

    public int getViewId() {
        return this.mViewId;
    }

    protected int getWidthMeasureSpec() {
        return this.getUnspecifiedMeasureSpec();
    }

    public void inflate() {
        if(this.mView != null) {
            return;
        }

        this.mView = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mContainer, false);
        this.onFinishInflate();
        this.registerResource();
        this.mNeedsLayoutUpdate = true;
    }

    public void invalidate() {
        this.invalidate(false);
    }

    public void invalidate(boolean arg2) {
        if(this.mView == null) {
            this.inflate();
        }

        this.mIsInvalidated = true;
        if(!this.mIsAttached && (this.shouldAttachView())) {
            this.attachView();
        }

        if(this.mIsAttached) {
            if(!arg2 && !this.mNeedsLayoutUpdate) {
                goto label_19;
            }

            this.updateLayoutParams();
        }
        else {
            this.layout();
            this.invalidateResource();
        }

    label_19:
        this.mNeedsLayoutUpdate = false;
    }

    private void invalidateResource() {
        if((this.mIsInvalidated) && this.mView != null && this.mResourceAdapter != null) {
            this.mIsInvalidated = false;
            this.mResourceAdapter.invalidate(null);
        }
    }

    protected void layout() {
        this.mView.measure(this.getWidthMeasureSpec(), this.getHeightMeasureSpec());
        this.mView.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
    }

    protected void onCaptureEnd() {
        if(this.shouldDetachViewAfterCapturing()) {
            this.detachView();
        }
    }

    protected void onFinishInflate() {
    }

    private void registerResource() {
        if(this.mResourceAdapter == null) {
            this.mResourceAdapter = new ViewInflaterAdapter(this, this.mView.findViewById(this.mViewId));
        }

        if(this.mResourceLoader != null) {
            this.mResourceLoader.registerResource(this.mViewId, this.mResourceAdapter);
        }
    }

    protected boolean shouldAttachView() {
        return 1;
    }

    protected boolean shouldDetachViewAfterCapturing() {
        return 1;
    }

    private void unregisterResource() {
        if(this.mResourceLoader != null) {
            this.mResourceLoader.unregisterResource(this.mViewId);
        }

        this.mResourceAdapter = null;
    }

    private void updateLayoutParams() {
        int v0 = this.getWidthMeasureSpec();
        int v2 = 0x40000000;
        int v3 = -2;
        v0 = View$MeasureSpec.getMode(v0) == v2 ? View$MeasureSpec.getSize(v0) : -2;
        int v1 = this.getHeightMeasureSpec();
        if(View$MeasureSpec.getMode(v1) == v2) {
            v3 = View$MeasureSpec.getSize(v1);
        }

        ViewGroup$LayoutParams v1_1 = this.mView.getLayoutParams();
        v1_1.width = v0;
        v1_1.height = v3;
        this.mView.setLayoutParams(v1_1);
    }
}

