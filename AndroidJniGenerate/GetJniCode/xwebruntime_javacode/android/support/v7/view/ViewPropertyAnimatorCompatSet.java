package android.support.v7.view;

import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ViewPropertyAnimatorCompatSet {
    class android.support.v7.view.ViewPropertyAnimatorCompatSet$1 extends ViewPropertyAnimatorListenerAdapter {
        private int mProxyEndCount;
        private boolean mProxyStarted;

        android.support.v7.view.ViewPropertyAnimatorCompatSet$1(ViewPropertyAnimatorCompatSet arg1) {
            ViewPropertyAnimatorCompatSet.this = arg1;
            super();
            this.mProxyStarted = false;
            this.mProxyEndCount = 0;
        }

        public void onAnimationEnd(View arg2) {
            int v2 = this.mProxyEndCount + 1;
            this.mProxyEndCount = v2;
            if(v2 == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                if(ViewPropertyAnimatorCompatSet.this.mListener != null) {
                    ViewPropertyAnimatorCompatSet.this.mListener.onAnimationEnd(null);
                }

                this.onEnd();
            }
        }

        public void onAnimationStart(View arg2) {
            if(this.mProxyStarted) {
                return;
            }

            this.mProxyStarted = true;
            if(ViewPropertyAnimatorCompatSet.this.mListener != null) {
                ViewPropertyAnimatorCompatSet.this.mListener.onAnimationStart(null);
            }
        }

        void onEnd() {
            this.mProxyEndCount = 0;
            this.mProxyStarted = false;
            ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
        }
    }

    final ArrayList mAnimators;
    private long mDuration;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;

    public ViewPropertyAnimatorCompatSet() {
        super();
        this.mDuration = -1;
        this.mProxyListener = new android.support.v7.view.ViewPropertyAnimatorCompatSet$1(this);
        this.mAnimators = new ArrayList();
    }

    public void cancel() {
        if(!this.mIsStarted) {
            return;
        }

        Iterator v0 = this.mAnimators.iterator();
        while(v0.hasNext()) {
            v0.next().cancel();
        }

        this.mIsStarted = false;
    }

    void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat arg2) {
        if(!this.mIsStarted) {
            this.mAnimators.add(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat arg3, ViewPropertyAnimatorCompat arg4) {
        this.mAnimators.add(arg3);
        arg4.setStartDelay(arg3.getDuration());
        this.mAnimators.add(arg4);
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long arg2) {
        if(!this.mIsStarted) {
            this.mDuration = arg2;
        }

        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator arg2) {
        if(!this.mIsStarted) {
            this.mInterpolator = arg2;
        }

        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener arg2) {
        if(!this.mIsStarted) {
            this.mListener = arg2;
        }

        return this;
    }

    public void start() {
        if(this.mIsStarted) {
            return;
        }

        Iterator v0 = this.mAnimators.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(this.mDuration >= 0) {
                ((ViewPropertyAnimatorCompat)v1).setDuration(this.mDuration);
            }

            if(this.mInterpolator != null) {
                ((ViewPropertyAnimatorCompat)v1).setInterpolator(this.mInterpolator);
            }

            if(this.mListener != null) {
                ((ViewPropertyAnimatorCompat)v1).setListener(this.mProxyListener);
            }

            ((ViewPropertyAnimatorCompat)v1).start();
        }

        this.mIsStarted = true;
    }
}

