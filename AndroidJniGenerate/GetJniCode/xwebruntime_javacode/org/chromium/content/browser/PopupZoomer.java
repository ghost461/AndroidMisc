package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path$Direction;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region$Op;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.SystemClock;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.metrics.RecordHistogram;

public class PopupZoomer extends View {
    public interface OnTapListener {
        void onResolveTapDisambiguation(long arg1, float arg2, float arg3, boolean arg4);
    }

    public interface OnVisibilityChangedListener {
        void onPopupZoomerHidden(PopupZoomer arg1);

        void onPopupZoomerShown(PopupZoomer arg1);
    }

    class ReverseInterpolator implements Interpolator {
        private final Interpolator mInterpolator;

        public ReverseInterpolator(Interpolator arg1) {
            super();
            this.mInterpolator = arg1;
        }

        public float getInterpolation(float arg2) {
            float v0 = 1f - arg2;
            if(this.mInterpolator == null) {
                return v0;
            }

            return this.mInterpolator.getInterpolation(v0);
        }
    }

    private static final long ANIMATION_DURATION = 300;
    private static final String TAG = "cr.PopupZoomer";
    private static final String UMA_TAPDISAMBIGUATION = "Touchscreen.TapDisambiguation";
    private static final int UMA_TAPDISAMBIGUATION_BACKBUTTON = 1;
    private static final int UMA_TAPDISAMBIGUATION_COUNT = 6;
    private static final int UMA_TAPDISAMBIGUATION_OTHER = 0;
    private static final int UMA_TAPDISAMBIGUATION_TAPPEDINSIDE_DEPRECATED = 3;
    private static final int UMA_TAPDISAMBIGUATION_TAPPEDINSIDE_DIFFERENTNODE = 5;
    private static final int UMA_TAPDISAMBIGUATION_TAPPEDINSIDE_SAMENODE = 4;
    private static final int UMA_TAPDISAMBIGUATION_TAPPEDOUTSIDE = 2;
    private static final int ZOOM_BOUNDS_MARGIN = 25;
    private boolean mAnimating;
    private long mAnimationStartTime;
    private float mBottomExtrusion;
    private RectF mClipRect;
    private RectF mDrawRect;
    private final GestureDetector mGestureDetector;
    private final Interpolator mHideInterpolator;
    private float mLeftExtrusion;
    private float mMaxScrollX;
    private float mMaxScrollY;
    private float mMinScrollX;
    private float mMinScrollY;
    private boolean mNeedsToInitDimensions;
    private final OnTapListener mOnTapListener;
    private final OnVisibilityChangedListener mOnVisibilityChangedListener;
    private float mPopupScrollX;
    private float mPopupScrollY;
    private float mRightExtrusion;
    private float mScale;
    private float mShiftX;
    private float mShiftY;
    private final Interpolator mShowInterpolator;
    private boolean mShowing;
    private Rect mTargetBounds;
    private long mTimeLeft;
    private float mTopExtrusion;
    private final PointF mTouch;
    private RectF mViewClipRect;
    private Bitmap mZoomedBitmap;
    private static float sOverlayCornerRadius;
    private static Drawable sOverlayDrawable;
    private static Rect sOverlayPadding;

    public PopupZoomer(Context arg2, ViewGroup arg3, OnVisibilityChangedListener arg4, OnTapListener arg5) {
        super(arg2);
        this.mShowInterpolator = new OvershootInterpolator();
        this.mHideInterpolator = new ReverseInterpolator(this.mShowInterpolator);
        this.mScale = 1f;
        this.mTouch = new PointF();
        this.mOnVisibilityChangedListener = arg4;
        this.mOnTapListener = arg5;
        this.setVisibility(4);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.mGestureDetector = new GestureDetector(arg2, new GestureDetector$SimpleOnGestureListener() {
            private boolean handleTapOrPress(MotionEvent arg10, boolean arg11) {
                if(PopupZoomer.access$000(PopupZoomer.this)) {
                    return 1;
                }

                float v0 = arg10.getX();
                float v2 = arg10.getY();
                if(PopupZoomer.access$100(PopupZoomer.this, v0, v2)) {
                    PopupZoomer.access$200(PopupZoomer.this);
                }
                else if(PopupZoomer.access$400(PopupZoomer.this) != null) {
                    PointF v0_1 = PopupZoomer.access$500(PopupZoomer.this, v0, v2);
                    PopupZoomer.access$400(PopupZoomer.this).onResolveTapDisambiguation(arg10.getEventTime(), v0_1.x, v0_1.y, arg11);
                    PopupZoomer.access$600(PopupZoomer.this);
                }

                return 1;
            }

            public void onLongPress(MotionEvent arg2) {
                this.handleTapOrPress(arg2, true);
            }

            public boolean onScroll(MotionEvent arg3, MotionEvent arg4, float arg5, float arg6) {
                if(PopupZoomer.access$000(PopupZoomer.this)) {
                    return 1;
                }

                if(PopupZoomer.access$100(PopupZoomer.this, arg3.getX(), arg3.getY())) {
                    PopupZoomer.access$200(PopupZoomer.this);
                }
                else {
                    PopupZoomer.access$300(PopupZoomer.this, arg5, arg6);
                }

                return 1;
            }

            public boolean onSingleTapUp(MotionEvent arg2) {
                return this.handleTapOrPress(arg2, false);
            }
        });
    }

    protected boolean acceptZeroSizeView() {
        return 0;
    }

    static boolean access$000(PopupZoomer arg0) {
        return arg0.mAnimating;
    }

    static boolean access$100(PopupZoomer arg0, float arg1, float arg2) {
        return arg0.isTouchOutsideArea(arg1, arg2);
    }

    static void access$200(PopupZoomer arg0) {
        arg0.tappedOutside();
    }

    static void access$300(PopupZoomer arg0, float arg1, float arg2) {
        arg0.scroll(arg1, arg2);
    }

    static OnTapListener access$400(PopupZoomer arg0) {
        return arg0.mOnTapListener;
    }

    static PointF access$500(PopupZoomer arg0, float arg1, float arg2) {
        return arg0.convertTouchPoint(arg1, arg2);
    }

    static void access$600(PopupZoomer arg0) {
        arg0.tappedInside();
    }

    public void backButtonPressed() {
        if(!this.mShowing) {
            return;
        }

        this.recordHistogram(1);
        this.startAnimation(false);
    }

    private static float constrain(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            arg1 = arg2;
        }
        else if(arg1 > arg3) {
            arg1 = arg3;
        }

        return arg1;
    }

    private static int constrain(int arg0, int arg1, int arg2) {
        if(arg0 < arg1) {
            arg0 = arg1;
        }
        else if(arg0 > arg2) {
            arg0 = arg2;
        }

        return arg0;
    }

    private PointF convertTouchPoint(float arg3, float arg4) {
        return new PointF(this.mTouch.x + (arg3 - this.mShiftX - this.mTouch.x - this.mPopupScrollX) / this.mScale, this.mTouch.y + (arg4 - this.mShiftY - this.mTouch.y - this.mPopupScrollY) / this.mScale);
    }

    private static float getOverlayCornerRadius(Context arg2) {
        if(PopupZoomer.sOverlayCornerRadius == 0f) {
            try {
                PopupZoomer.sOverlayCornerRadius = arg2.getResources().getDimension(0x7F050069);
            }
            catch(Resources$NotFoundException ) {
                Log.w("cr.PopupZoomer", "No corner radius resource for PopupZoomer overlay found.", new Object[0]);
                PopupZoomer.sOverlayCornerRadius = 1f;
            }
        }

        return PopupZoomer.sOverlayCornerRadius;
    }

    private static Drawable getOverlayDrawable(Context arg2) {
        if(PopupZoomer.sOverlayDrawable == null) {
            try {
                PopupZoomer.sOverlayDrawable = ApiCompatibilityUtils.getDrawable(arg2.getResources(), 0x7F060070);
            }
            catch(Resources$NotFoundException ) {
                Log.w("cr.PopupZoomer", "No drawable resource for PopupZoomer overlay found.", new Object[0]);
                PopupZoomer.sOverlayDrawable = new ColorDrawable();
            }

            PopupZoomer.sOverlayPadding = new Rect();
            PopupZoomer.sOverlayDrawable.getPadding(PopupZoomer.sOverlayPadding);
        }

        return PopupZoomer.sOverlayDrawable;
    }

    public void hide(boolean arg2) {
        if(!this.mShowing) {
            return;
        }

        this.recordHistogram(0);
        if(arg2) {
            this.startAnimation(false);
        }
        else {
            this.hideImmediately();
        }
    }

    private void hideImmediately() {
        this.mAnimating = false;
        this.mShowing = false;
        this.mTimeLeft = 0;
        if(this.mOnVisibilityChangedListener != null) {
            this.mOnVisibilityChangedListener.onPopupZoomerHidden(this);
        }

        this.setVisibility(4);
        this.mZoomedBitmap.recycle();
        this.mZoomedBitmap = null;
    }

    private void initDimensions() {
        if(this.mTargetBounds != null) {
            if(this.mTouch == null) {
            }
            else {
                this.mScale = (((float)this.mZoomedBitmap.getWidth())) / (((float)this.mTargetBounds.width()));
                float v0 = this.mTouch.x - this.mScale * (this.mTouch.x - (((float)this.mTargetBounds.left)));
                float v1 = this.mTouch.y - this.mScale * (this.mTouch.y - (((float)this.mTargetBounds.top)));
                this.mClipRect = new RectF(v0, v1, (((float)this.mZoomedBitmap.getWidth())) + v0, (((float)this.mZoomedBitmap.getHeight())) + v1);
                v0 = ((float)(this.getWidth() - 25));
                v1 = ((float)(this.getHeight() - 25));
                float v3 = 25f;
                this.mViewClipRect = new RectF(v3, v3, v0, v1);
                this.mShiftX = 0f;
                this.mShiftY = 0f;
                if(this.mClipRect.left < v3) {
                    this.mShiftX = v3 - this.mClipRect.left;
                    this.mClipRect.left += this.mShiftX;
                    this.mClipRect.right += this.mShiftX;
                }
                else if(this.mClipRect.right > v0) {
                    this.mShiftX = v0 - this.mClipRect.right;
                    this.mClipRect.right += this.mShiftX;
                    this.mClipRect.left += this.mShiftX;
                }

                if(this.mClipRect.top < v3) {
                    this.mShiftY = v3 - this.mClipRect.top;
                    this.mClipRect.top += this.mShiftY;
                    this.mClipRect.bottom += this.mShiftY;
                }
                else if(this.mClipRect.bottom > v1) {
                    this.mShiftY = v1 - this.mClipRect.bottom;
                    this.mClipRect.bottom += this.mShiftY;
                    this.mClipRect.top += this.mShiftY;
                }

                this.mMaxScrollY = 0f;
                this.mMinScrollY = 0f;
                this.mMaxScrollX = 0f;
                this.mMinScrollX = 0f;
                if(this.mViewClipRect.right + this.mShiftX < this.mClipRect.right) {
                    this.mMinScrollX = this.mViewClipRect.right - this.mClipRect.right;
                }

                if(this.mViewClipRect.left + this.mShiftX > this.mClipRect.left) {
                    this.mMaxScrollX = this.mViewClipRect.left - this.mClipRect.left;
                }

                if(this.mViewClipRect.top + this.mShiftY > this.mClipRect.top) {
                    this.mMaxScrollY = this.mViewClipRect.top - this.mClipRect.top;
                }

                if(this.mViewClipRect.bottom + this.mShiftY < this.mClipRect.bottom) {
                    this.mMinScrollY = this.mViewClipRect.bottom - this.mClipRect.bottom;
                }

                this.mClipRect.intersect(this.mViewClipRect);
                this.mLeftExtrusion = this.mTouch.x - this.mClipRect.left;
                this.mRightExtrusion = this.mClipRect.right - this.mTouch.x;
                this.mTopExtrusion = this.mTouch.y - this.mClipRect.top;
                this.mBottomExtrusion = this.mClipRect.bottom - this.mTouch.y;
                v0 = (this.mTouch.x - (((float)this.mTargetBounds.centerX()))) / ((((float)this.mTargetBounds.width())) / 2f) + 0.5f;
                v3 = (this.mTouch.y - (((float)this.mTargetBounds.centerY()))) / ((((float)this.mTargetBounds.height())) / 2f) + 0.5f;
                v1 = this.mMaxScrollX - this.mMinScrollX;
                float v2 = this.mMaxScrollY - this.mMinScrollY;
                this.mPopupScrollX = v1 * v0 * -1f;
                this.mPopupScrollY = v2 * v3 * -1f;
                this.mPopupScrollX = PopupZoomer.constrain(this.mPopupScrollX, this.mMinScrollX, this.mMaxScrollX);
                this.mPopupScrollY = PopupZoomer.constrain(this.mPopupScrollY, this.mMinScrollY, this.mMaxScrollY);
                this.mDrawRect = new RectF();
                return;
            }
        }
    }

    public boolean isShowing() {
        boolean v0 = (this.mShowing) || (this.mAnimating) ? true : false;
        return v0;
    }

    private boolean isTouchOutsideArea(float arg2, float arg3) {
        return this.mClipRect.contains(arg2, arg3) ^ 1;
    }

    protected void onDraw(Canvas arg9) {
        if(this.isShowing()) {
            if(this.mZoomedBitmap == null) {
            }
            else {
                if(!this.acceptZeroSizeView() && (this.getWidth() == 0 || this.getHeight() == 0)) {
                    return;
                }

                if(this.mNeedsToInitDimensions) {
                    this.mNeedsToInitDimensions = false;
                    this.initDimensions();
                }

                arg9.save();
                float v3 = 1f;
                float v0 = PopupZoomer.constrain((((float)(SystemClock.uptimeMillis() - this.mAnimationStartTime + this.mTimeLeft))) / 300f, 0f, v3);
                if(v0 >= v3) {
                    this.mAnimating = false;
                    if(!this.isShowing()) {
                        this.hideImmediately();
                        return;
                    }
                }
                else {
                    this.invalidate();
                }

                v0 = this.mShowing ? this.mShowInterpolator.getInterpolation(v0) : this.mHideInterpolator.getInterpolation(v0);
                arg9.drawARGB(((int)(80f * v0)), 0, 0, 0);
                arg9.save();
                float v2 = (this.mScale - v3) * v0 / this.mScale + v3 / this.mScale;
                v3 -= v0;
                float v4 = -this.mShiftX * v3 / this.mScale;
                float v5 = -this.mShiftY * v3 / this.mScale;
                this.mDrawRect.left = this.mTouch.x - this.mLeftExtrusion * v2 + v4;
                this.mDrawRect.top = this.mTouch.y - this.mTopExtrusion * v2 + v5;
                this.mDrawRect.right = this.mTouch.x + this.mRightExtrusion * v2 + v4;
                this.mDrawRect.bottom = this.mTouch.y + this.mBottomExtrusion * v2 + v5;
                arg9.clipRect(this.mDrawRect);
                arg9.scale(v2, v2, this.mDrawRect.left, this.mDrawRect.top);
                arg9.translate(this.mPopupScrollX, this.mPopupScrollY);
                arg9.drawBitmap(this.mZoomedBitmap, this.mDrawRect.left, this.mDrawRect.top, null);
                arg9.restore();
                Drawable v2_1 = PopupZoomer.getOverlayDrawable(this.getContext());
                v2_1.setBounds((((int)this.mDrawRect.left)) - PopupZoomer.sOverlayPadding.left, (((int)this.mDrawRect.top)) - PopupZoomer.sOverlayPadding.top, (((int)this.mDrawRect.right)) + PopupZoomer.sOverlayPadding.right, (((int)this.mDrawRect.bottom)) + PopupZoomer.sOverlayPadding.bottom);
                v2_1.setAlpha(PopupZoomer.constrain(((int)(v0 * 255f)), 0, 0xFF));
                v2_1.draw(arg9);
                arg9.restore();
                return;
            }
        }
    }

    @SuppressLint(value={"ClickableViewAccessibility"}) public boolean onTouchEvent(MotionEvent arg2) {
        this.mGestureDetector.onTouchEvent(arg2);
        return 1;
    }

    private void recordHistogram(int arg3) {
        RecordHistogram.recordEnumeratedHistogram("Touchscreen.TapDisambiguation", arg3, 6);
    }

    private void scroll(float arg3, float arg4) {
        this.mPopupScrollX = PopupZoomer.constrain(this.mPopupScrollX - arg3, this.mMinScrollX, this.mMaxScrollX);
        this.mPopupScrollY = PopupZoomer.constrain(this.mPopupScrollY - arg4, this.mMinScrollY, this.mMaxScrollY);
        this.invalidate();
    }

    @VisibleForTesting void setBitmap(Bitmap arg6) {
        if(this.mZoomedBitmap != null) {
            this.mZoomedBitmap.recycle();
            this.mZoomedBitmap = null;
        }

        this.mZoomedBitmap = arg6;
        Canvas v6 = new Canvas(this.mZoomedBitmap);
        Path v0 = new Path();
        RectF v1 = new RectF(0f, 0f, ((float)v6.getWidth()), ((float)v6.getHeight()));
        float v2 = PopupZoomer.getOverlayCornerRadius(this.getContext());
        v0.addRoundRect(v1, v2, v2, Path$Direction.CCW);
        if(Build$VERSION.SDK_INT >= 26) {
            v6.clipOutPath(v0);
        }
        else {
            v6.clipPath(v0, Region$Op.DIFFERENCE);
        }

        Paint v0_1 = new Paint();
        v0_1.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.SRC));
        v0_1.setColor(0);
        v6.drawPaint(v0_1);
    }

    public void setLastTouch(float arg2, float arg3) {
        this.mTouch.x = arg2;
        this.mTouch.y = arg3;
    }

    private void setTargetBounds(Rect arg1) {
        this.mTargetBounds = arg1;
    }

    @VisibleForTesting void show(Rect arg2) {
        if(!this.mShowing) {
            if(this.mZoomedBitmap == null) {
            }
            else {
                this.setTargetBounds(arg2);
                this.startAnimation(true);
                return;
            }
        }
    }

    private void startAnimation(boolean arg10) {
        this.mAnimating = true;
        this.mShowing = arg10;
        long v1 = 0;
        this.mTimeLeft = v1;
        if(arg10) {
            this.setVisibility(0);
            this.mNeedsToInitDimensions = true;
            if(this.mOnVisibilityChangedListener != null) {
                this.mOnVisibilityChangedListener.onPopupZoomerShown(this);
            }
        }
        else {
            this.mTimeLeft = this.mAnimationStartTime + 300 - SystemClock.uptimeMillis();
            if(this.mTimeLeft < v1) {
                this.mTimeLeft = v1;
            }
        }

        this.mAnimationStartTime = SystemClock.uptimeMillis();
        this.invalidate();
    }

    private void tappedInside() {
        if(!this.mShowing) {
            return;
        }

        this.startAnimation(false);
    }

    private void tappedOutside() {
        if(!this.mShowing) {
            return;
        }

        this.recordHistogram(2);
        this.startAnimation(false);
    }
}

