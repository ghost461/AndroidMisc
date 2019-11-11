package org.chromium.ui.widget;

import android.graphics.Rect;
import android.view.View$OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.ViewTreeObserver;
import org.chromium.base.ApiCompatibilityUtils;

public class ViewRectProvider extends RectProvider implements View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnPreDrawListener {
    private final int[] mCachedWindowCoordinates;
    private boolean mIncludePadding;
    private final Rect mInsetRect;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;

    public ViewRectProvider(View arg3) {
        super();
        this.mCachedWindowCoordinates = new int[2];
        this.mInsetRect = new Rect();
        this.mView = arg3;
        this.mCachedWindowCoordinates[0] = -1;
        this.mCachedWindowCoordinates[1] = -1;
    }

    public void onGlobalLayout() {
        if(!this.mView.isShown()) {
            this.notifyRectHidden();
        }
    }

    public boolean onPreDraw() {
        if(!this.mView.isShown()) {
            this.notifyRectHidden();
        }
        else {
            this.refreshRectBounds();
        }

        return 1;
    }

    public void onViewAttachedToWindow(View arg1) {
    }

    public void onViewDetachedFromWindow(View arg1) {
        this.notifyRectHidden();
    }

    private void refreshRectBounds() {
        int v0 = this.mCachedWindowCoordinates[0];
        int v2 = this.mCachedWindowCoordinates[1];
        this.mView.getLocationInWindow(this.mCachedWindowCoordinates);
        this.mCachedWindowCoordinates[0] = Math.max(this.mCachedWindowCoordinates[0], 0);
        this.mCachedWindowCoordinates[1] = Math.max(this.mCachedWindowCoordinates[1], 0);
        if(this.mCachedWindowCoordinates[0] == v0 && this.mCachedWindowCoordinates[1] == v2) {
            return;
        }

        this.mRect.left = this.mCachedWindowCoordinates[0];
        this.mRect.top = this.mCachedWindowCoordinates[1];
        this.mRect.right = this.mRect.left + this.mView.getWidth();
        this.mRect.bottom = this.mRect.top + this.mView.getHeight();
        this.mRect.left += this.mInsetRect.left;
        this.mRect.top += this.mInsetRect.top;
        this.mRect.right -= this.mInsetRect.right;
        this.mRect.bottom -= this.mInsetRect.bottom;
        if(!this.mIncludePadding) {
            boolean v0_1 = ApiCompatibilityUtils.isLayoutRtl(this.mView);
            Rect v1 = this.mRect;
            v2 = v1.left;
            int v3 = v0_1 ? ApiCompatibilityUtils.getPaddingEnd(this.mView) : ApiCompatibilityUtils.getPaddingStart(this.mView);
            v1.left = v2 + v3;
            v1 = this.mRect;
            v2 = v1.right;
            v0 = v0_1 ? ApiCompatibilityUtils.getPaddingStart(this.mView) : ApiCompatibilityUtils.getPaddingEnd(this.mView);
            v1.right = v2 - v0;
            this.mRect.top += this.mView.getPaddingTop();
            this.mRect.bottom -= this.mView.getPaddingBottom();
        }

        this.mRect.right = Math.max(this.mRect.left, this.mRect.right);
        this.mRect.bottom = Math.max(this.mRect.top, this.mRect.bottom);
        this.mRect.right = Math.min(this.mRect.right, this.mView.getRootView().getWidth());
        this.mRect.bottom = Math.min(this.mRect.bottom, this.mView.getRootView().getHeight());
        this.notifyRectChanged();
    }

    public void setIncludePadding(boolean arg1) {
        this.mIncludePadding = arg1;
    }

    public void setInsetPx(int arg2, int arg3, int arg4, int arg5) {
        this.mInsetRect.set(arg2, arg3, arg4, arg5);
        this.refreshRectBounds();
    }

    public void startObserving(Observer arg2) {
        this.mView.addOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        this.mViewTreeObserver = this.mView.getViewTreeObserver();
        this.mViewTreeObserver.addOnGlobalLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)this));
        this.mViewTreeObserver.addOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
        this.refreshRectBounds();
        super.startObserving(arg2);
    }

    public void stopObserving() {
        this.mView.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        if(this.mViewTreeObserver != null && (this.mViewTreeObserver.isAlive())) {
            this.mViewTreeObserver.removeOnGlobalLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)this));
            this.mViewTreeObserver.removeOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
        }

        this.mViewTreeObserver = null;
        super.stopObserving();
    }
}

