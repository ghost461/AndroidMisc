package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
    class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
        boolean mAnimEndCalled;
        ViewPropertyAnimatorCompat mVpa;

        ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat arg1) {
            super();
            this.mVpa = arg1;
        }

        public void onAnimationCancel(View arg3) {
            Object v0 = arg3.getTag(0x7E000000);
            if((v0 instanceof ViewPropertyAnimatorListener)) {
            }
            else {
                v0 = null;
            }

            if(v0 != null) {
                ((ViewPropertyAnimatorListener)v0).onAnimationCancel(arg3);
            }
        }

        public void onAnimationEnd(View arg4) {
            int v1 = -1;
            Paint v2 = null;
            if(this.mVpa.mOldLayerType > v1) {
                arg4.setLayerType(this.mVpa.mOldLayerType, v2);
                this.mVpa.mOldLayerType = v1;
            }

            if(Build$VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
                if(this.mVpa.mEndAction != null) {
                    Runnable v0 = this.mVpa.mEndAction;
                    this.mVpa.mEndAction = ((Runnable)v2);
                    v0.run();
                }

                Object v0_1 = arg4.getTag(0x7E000000);
                if((v0_1 instanceof ViewPropertyAnimatorListener)) {
                    Object v2_1 = v0_1;
                }

                if(v2 != null) {
                    ((ViewPropertyAnimatorListener)v2).onAnimationEnd(arg4);
                }

                this.mAnimEndCalled = true;
            }
        }

        public void onAnimationStart(View arg4) {
            this.mAnimEndCalled = false;
            Paint v1 = null;
            if(this.mVpa.mOldLayerType > -1) {
                arg4.setLayerType(2, v1);
            }

            if(this.mVpa.mStartAction != null) {
                Runnable v0 = this.mVpa.mStartAction;
                this.mVpa.mStartAction = ((Runnable)v1);
                v0.run();
            }

            Object v0_1 = arg4.getTag(0x7E000000);
            if((v0_1 instanceof ViewPropertyAnimatorListener)) {
                Object v1_1 = v0_1;
            }

            if(v1 != null) {
                ((ViewPropertyAnimatorListener)v1).onAnimationStart(arg4);
            }
        }
    }

    static final int LISTENER_TAG_ID = 0x7E000000;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction;
    int mOldLayerType;
    Runnable mStartAction;
    private WeakReference mView;

    ViewPropertyAnimatorCompat(View arg2) {
        super();
        this.mStartAction = null;
        this.mEndAction = null;
        this.mOldLayerType = -1;
        this.mView = new WeakReference(arg2);
    }

    public ViewPropertyAnimatorCompat alpha(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().alpha(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().alphaBy(arg2);
        }

        return this;
    }

    public void cancel() {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().cancel();
        }
    }

    public long getDuration() {
        Object v0 = this.mView.get();
        if(v0 != null) {
            return ((View)v0).animate().getDuration();
        }

        return 0;
    }

    public Interpolator getInterpolator() {
        Object v0 = this.mView.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 18) {
            return ((View)v0).animate().getInterpolator();
        }

        return null;
    }

    public long getStartDelay() {
        Object v0 = this.mView.get();
        if(v0 != null) {
            return ((View)v0).animate().getStartDelay();
        }

        return 0;
    }

    public ViewPropertyAnimatorCompat rotation(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().rotation(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().rotationBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().rotationX(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().rotationXBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().rotationY(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().rotationYBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().scaleX(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().scaleXBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().scaleY(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().scaleYBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().setDuration(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().setInterpolator(((TimeInterpolator)arg2));
        }

        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener arg4) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            if(Build$VERSION.SDK_INT >= 16) {
                this.setListenerInternal(((View)v0), arg4);
            }
            else {
                ((View)v0).setTag(0x7E000000, arg4);
                this.setListenerInternal(((View)v0), new ViewPropertyAnimatorListenerApi14(this));
            }
        }

        return this;
    }

    private void setListenerInternal(View arg3, ViewPropertyAnimatorListener arg4) {
        if(arg4 != null) {
            arg3.animate().setListener(new AnimatorListenerAdapter(arg4, arg3) {
                public void onAnimationCancel(Animator arg2) {
                    this.val$listener.onAnimationCancel(this.val$view);
                }

                public void onAnimationEnd(Animator arg2) {
                    this.val$listener.onAnimationEnd(this.val$view);
                }

                public void onAnimationStart(Animator arg2) {
                    this.val$listener.onAnimationStart(this.val$view);
                }
            });
        }
        else {
            arg3.animate().setListener(null);
        }
    }

    public ViewPropertyAnimatorCompat setStartDelay(long arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().setStartDelay(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener arg4) {
        Object v0 = this.mView.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 19) {
            ValueAnimator$AnimatorUpdateListener v1 = null;
            if(arg4 != null) {
                android.support.v4.view.ViewPropertyAnimatorCompat$2 v1_1 = new ValueAnimator$AnimatorUpdateListener(arg4, ((View)v0)) {
                    public void onAnimationUpdate(ValueAnimator arg2) {
                        this.val$listener.onAnimationUpdate(this.val$view);
                    }
                };
            }

            ((View)v0).animate().setUpdateListener(v1);
        }

        return this;
    }

    public void start() {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().start();
        }
    }

    public ViewPropertyAnimatorCompat translationX(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().translationX(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().translationXBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().translationY(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().translationYBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float arg4) {
        Object v0 = this.mView.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 21) {
            ((View)v0).animate().translationZ(arg4);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float arg4) {
        Object v0 = this.mView.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 21) {
            ((View)v0).animate().translationZBy(arg4);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable arg4) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            if(Build$VERSION.SDK_INT >= 16) {
                ((View)v0).animate().withEndAction(arg4);
            }
            else {
                this.setListenerInternal(((View)v0), new ViewPropertyAnimatorListenerApi14(this));
                this.mEndAction = arg4;
            }
        }

        return this;
    }

    public ViewPropertyAnimatorCompat withLayer() {
        Object v0 = this.mView.get();
        if(v0 != null) {
            if(Build$VERSION.SDK_INT >= 16) {
                ((View)v0).animate().withLayer();
            }
            else {
                this.mOldLayerType = ((View)v0).getLayerType();
                this.setListenerInternal(((View)v0), new ViewPropertyAnimatorListenerApi14(this));
            }
        }

        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable arg4) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            if(Build$VERSION.SDK_INT >= 16) {
                ((View)v0).animate().withStartAction(arg4);
            }
            else {
                this.setListenerInternal(((View)v0), new ViewPropertyAnimatorListenerApi14(this));
                this.mStartAction = arg4;
            }
        }

        return this;
    }

    public ViewPropertyAnimatorCompat x(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().x(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().xBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat y(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().y(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float arg2) {
        Object v0 = this.mView.get();
        if(v0 != null) {
            ((View)v0).animate().yBy(arg2);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat z(float arg4) {
        Object v0 = this.mView.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 21) {
            ((View)v0).animate().z(arg4);
        }

        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float arg4) {
        Object v0 = this.mView.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 21) {
            ((View)v0).animate().zBy(arg4);
        }

        return this;
    }
}

