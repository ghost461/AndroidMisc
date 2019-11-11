package org.chromium.content.browser;

import org.chromium.base.VisibleForTesting;

public class RenderCoordinates {
    class org.chromium.content.browser.RenderCoordinates$1 {
    }

    public class NormalizedPoint {
        private float mXAbsoluteCss;
        private float mYAbsoluteCss;

        NormalizedPoint(RenderCoordinates arg1, org.chromium.content.browser.RenderCoordinates$1 arg2) {
            this(arg1);
        }

        private NormalizedPoint(RenderCoordinates arg1) {
            RenderCoordinates.this = arg1;
            super();
        }

        public float getXAbsoluteCss() {
            return this.mXAbsoluteCss;
        }

        public float getXLocalDip() {
            return (this.mXAbsoluteCss - RenderCoordinates.this.mScrollXCss) * RenderCoordinates.this.mPageScaleFactor;
        }

        public float getXPix() {
            return this.getXLocalDip() * RenderCoordinates.this.mDeviceScaleFactor;
        }

        public float getYAbsoluteCss() {
            return this.mYAbsoluteCss;
        }

        public float getYLocalDip() {
            return (this.mYAbsoluteCss - RenderCoordinates.this.mScrollYCss) * RenderCoordinates.this.mPageScaleFactor;
        }

        public float getYPix() {
            return this.getYLocalDip() * RenderCoordinates.this.mDeviceScaleFactor + RenderCoordinates.this.mTopContentOffsetYPix;
        }

        public void setAbsoluteCss(float arg1, float arg2) {
            this.mXAbsoluteCss = arg1;
            this.mYAbsoluteCss = arg2;
        }

        public void setLocalDip(float arg2, float arg3) {
            this.setAbsoluteCss(arg2 / RenderCoordinates.this.mPageScaleFactor + RenderCoordinates.this.mScrollXCss, arg3 / RenderCoordinates.this.mPageScaleFactor + RenderCoordinates.this.mScrollYCss);
        }

        public void setScreen(float arg2, float arg3) {
            this.setLocalDip(arg2 / RenderCoordinates.this.mDeviceScaleFactor, arg3 / RenderCoordinates.this.mDeviceScaleFactor);
        }
    }

    private float mContentHeightCss;
    private float mContentWidthCss;
    private float mDeviceScaleFactor;
    private boolean mHasFrameInfo;
    private float mLastFrameViewportHeightCss;
    private float mLastFrameViewportWidthCss;
    private float mMaxPageScaleFactor;
    private float mMinPageScaleFactor;
    private float mPageScaleFactor;
    private float mScrollXCss;
    private float mScrollYCss;
    private float mTopContentOffsetYPix;

    public RenderCoordinates() {
        super();
        this.mPageScaleFactor = 1f;
        this.mMinPageScaleFactor = 1f;
        this.mMaxPageScaleFactor = 1f;
        this.mDeviceScaleFactor = 1f;
    }

    static float access$000(RenderCoordinates arg0) {
        return arg0.mScrollXCss;
    }

    static float access$100(RenderCoordinates arg0) {
        return arg0.mPageScaleFactor;
    }

    static float access$200(RenderCoordinates arg0) {
        return arg0.mScrollYCss;
    }

    static float access$300(RenderCoordinates arg0) {
        return arg0.mDeviceScaleFactor;
    }

    static float access$400(RenderCoordinates arg0) {
        return arg0.mTopContentOffsetYPix;
    }

    public NormalizedPoint createNormalizedPoint() {
        return new NormalizedPoint(this, null);
    }

    public float fromDipToPix(float arg2) {
        return arg2 * this.mDeviceScaleFactor;
    }

    public float fromLocalCssToPix(float arg2) {
        return arg2 * this.mPageScaleFactor * this.mDeviceScaleFactor;
    }

    public float fromPixToDip(float arg2) {
        return arg2 / this.mDeviceScaleFactor;
    }

    public float fromPixToLocalCss(float arg3) {
        return arg3 / (this.mDeviceScaleFactor * this.mPageScaleFactor);
    }

    public float getContentHeightCss() {
        return this.mContentHeightCss;
    }

    public float getContentHeightPix() {
        return this.fromLocalCssToPix(this.mContentHeightCss);
    }

    public int getContentHeightPixInt() {
        return ((int)Math.ceil(((double)this.getContentHeightPix())));
    }

    public float getContentOffsetYPix() {
        return this.mTopContentOffsetYPix;
    }

    public float getContentWidthCss() {
        return this.mContentWidthCss;
    }

    public float getContentWidthPix() {
        return this.fromLocalCssToPix(this.mContentWidthCss);
    }

    public int getContentWidthPixInt() {
        return ((int)Math.ceil(((double)this.getContentWidthPix())));
    }

    public float getDeviceScaleFactor() {
        return this.mDeviceScaleFactor;
    }

    public float getLastFrameViewportHeightCss() {
        return this.mLastFrameViewportHeightCss;
    }

    public float getLastFrameViewportHeightPix() {
        return this.fromLocalCssToPix(this.mLastFrameViewportHeightCss);
    }

    public int getLastFrameViewportHeightPixInt() {
        return ((int)Math.ceil(((double)this.getLastFrameViewportHeightPix())));
    }

    public float getLastFrameViewportWidthCss() {
        return this.mLastFrameViewportWidthCss;
    }

    public float getLastFrameViewportWidthPix() {
        return this.fromLocalCssToPix(this.mLastFrameViewportWidthCss);
    }

    public int getLastFrameViewportWidthPixInt() {
        return ((int)Math.ceil(((double)this.getLastFrameViewportWidthPix())));
    }

    public float getMaxHorizontalScrollPix() {
        return this.getContentWidthPix() - this.getLastFrameViewportWidthPix();
    }

    public int getMaxHorizontalScrollPixInt() {
        return ((int)Math.floor(((double)this.getMaxHorizontalScrollPix())));
    }

    public float getMaxPageScaleFactor() {
        return this.mMaxPageScaleFactor;
    }

    public float getMaxVerticalScrollPix() {
        return this.getContentHeightPix() - this.getLastFrameViewportHeightPix();
    }

    public int getMaxVerticalScrollPixInt() {
        return ((int)Math.floor(((double)this.getMaxVerticalScrollPix())));
    }

    public float getMinPageScaleFactor() {
        return this.mMinPageScaleFactor;
    }

    public float getPageScaleFactor() {
        return this.mPageScaleFactor;
    }

    public float getScrollX() {
        return this.mScrollXCss;
    }

    public float getScrollXPix() {
        return this.fromLocalCssToPix(this.mScrollXCss);
    }

    public int getScrollXPixInt() {
        return ((int)Math.floor(((double)this.getScrollXPix())));
    }

    public float getScrollY() {
        return this.mScrollYCss;
    }

    public float getScrollYPix() {
        return this.fromLocalCssToPix(this.mScrollYCss);
    }

    public int getScrollYPixInt() {
        return ((int)Math.floor(((double)this.getScrollYPix())));
    }

    public boolean hasFrameInfo() {
        return this.mHasFrameInfo;
    }

    public void reset() {
        this.mScrollYCss = 0f;
        this.mScrollXCss = 0f;
        this.mPageScaleFactor = 1f;
        this.mHasFrameInfo = false;
    }

    public void setDeviceScaleFactor(float arg1) {
        this.mDeviceScaleFactor = arg1;
    }

    @VisibleForTesting public void setFrameInfoForTest(float arg1, float arg2) {
        this.reset();
        this.mDeviceScaleFactor = arg1;
        this.mTopContentOffsetYPix = arg2;
    }

    void updateContentSizeCss(float arg1, float arg2) {
        this.mContentWidthCss = arg1;
        this.mContentHeightCss = arg2;
    }

    public void updateFrameInfo(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10) {
        this.mScrollXCss = arg1;
        this.mScrollYCss = arg2;
        this.mPageScaleFactor = arg7;
        this.mMinPageScaleFactor = arg8;
        this.mMaxPageScaleFactor = arg9;
        this.mTopContentOffsetYPix = arg10;
        this.updateContentSizeCss(arg3, arg4);
        this.mLastFrameViewportWidthCss = arg5;
        this.mLastFrameViewportHeightCss = arg6;
        this.mHasFrameInfo = true;
    }
}

