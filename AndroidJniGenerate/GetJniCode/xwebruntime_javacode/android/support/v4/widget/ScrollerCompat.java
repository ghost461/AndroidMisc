package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

@Deprecated public final class ScrollerCompat {
    OverScroller mScroller;

    ScrollerCompat(Context arg2, Interpolator arg3) {
        super();
        OverScroller v0 = arg3 != null ? new OverScroller(arg2, arg3) : new OverScroller(arg2);
        this.mScroller = v0;
    }

    @Deprecated public void abortAnimation() {
        this.mScroller.abortAnimation();
    }

    @Deprecated public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Deprecated public static ScrollerCompat create(Context arg1) {
        return ScrollerCompat.create(arg1, null);
    }

    @Deprecated public static ScrollerCompat create(Context arg1, Interpolator arg2) {
        return new ScrollerCompat(arg1, arg2);
    }

    @Deprecated public void fling(int arg11, int arg12, int arg13, int arg14, int arg15, int arg16, int arg17, int arg18) {
        this.mScroller.fling(arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
    }

    @Deprecated public void fling(int arg13, int arg14, int arg15, int arg16, int arg17, int arg18, int arg19, int arg20, int arg21, int arg22) {
        this.mScroller.fling(arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22);
    }

    @Deprecated public float getCurrVelocity() {
        return this.mScroller.getCurrVelocity();
    }

    @Deprecated public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Deprecated public int getCurrY() {
        return this.mScroller.getCurrY();
    }

    @Deprecated public int getFinalX() {
        return this.mScroller.getFinalX();
    }

    @Deprecated public int getFinalY() {
        return this.mScroller.getFinalY();
    }

    @Deprecated public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Deprecated public boolean isOverScrolled() {
        return this.mScroller.isOverScrolled();
    }

    @Deprecated public void notifyHorizontalEdgeReached(int arg2, int arg3, int arg4) {
        this.mScroller.notifyHorizontalEdgeReached(arg2, arg3, arg4);
    }

    @Deprecated public void notifyVerticalEdgeReached(int arg2, int arg3, int arg4) {
        this.mScroller.notifyVerticalEdgeReached(arg2, arg3, arg4);
    }

    @Deprecated public boolean springBack(int arg8, int arg9, int arg10, int arg11, int arg12, int arg13) {
        return this.mScroller.springBack(arg8, arg9, arg10, arg11, arg12, arg13);
    }

    @Deprecated public void startScroll(int arg2, int arg3, int arg4, int arg5) {
        this.mScroller.startScroll(arg2, arg3, arg4, arg5);
    }

    @Deprecated public void startScroll(int arg7, int arg8, int arg9, int arg10, int arg11) {
        this.mScroller.startScroll(arg7, arg8, arg9, arg10, arg11);
    }
}

