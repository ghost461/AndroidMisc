package android.support.v7.widget;

import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnHoverListener;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo(value={Scope.LIBRARY_GROUP}) class TooltipCompatHandler implements View$OnAttachStateChangeListener, View$OnHoverListener, View$OnLongClickListener {
    class android.support.v7.widget.TooltipCompatHandler$1 implements Runnable {
        android.support.v7.widget.TooltipCompatHandler$1(TooltipCompatHandler arg1) {
            TooltipCompatHandler.this = arg1;
            super();
        }

        public void run() {
            TooltipCompatHandler.this.show(false);
        }
    }

    class android.support.v7.widget.TooltipCompatHandler$2 implements Runnable {
        android.support.v7.widget.TooltipCompatHandler$2(TooltipCompatHandler arg1) {
            TooltipCompatHandler.this = arg1;
            super();
        }

        public void run() {
            TooltipCompatHandler.this.hide();
        }
    }

    private static final long HOVER_HIDE_TIMEOUT_MS = 15000;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500;
    private static final String TAG = "TooltipCompatHandler";
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private final Runnable mHideRunnable;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable;
    private final CharSequence mTooltipText;
    private static TooltipCompatHandler sActiveHandler;

    private TooltipCompatHandler(View arg2, CharSequence arg3) {
        super();
        this.mShowRunnable = new android.support.v7.widget.TooltipCompatHandler$1(this);
        this.mHideRunnable = new android.support.v7.widget.TooltipCompatHandler$2(this);
        this.mAnchor = arg2;
        this.mTooltipText = arg3;
        this.mAnchor.setOnLongClickListener(((View$OnLongClickListener)this));
        this.mAnchor.setOnHoverListener(((View$OnHoverListener)this));
    }

    static void access$000(TooltipCompatHandler arg0, boolean arg1) {
        arg0.show(arg1);
    }

    static void access$100(TooltipCompatHandler arg0) {
        arg0.hide();
    }

    private void hide() {
        if(TooltipCompatHandler.sActiveHandler == this) {
            TooltipCompatHandler v0 = null;
            TooltipCompatHandler.sActiveHandler = v0;
            if(this.mPopup != null) {
                this.mPopup.hide();
                this.mPopup = ((TooltipPopup)v0);
                this.mAnchor.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
            }
            else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }

        this.mAnchor.removeCallbacks(this.mShowRunnable);
        this.mAnchor.removeCallbacks(this.mHideRunnable);
    }

    public boolean onHover(View arg4, MotionEvent arg5) {
        if(this.mPopup != null && (this.mFromTouch)) {
            return 0;
        }

        Object v4 = this.mAnchor.getContext().getSystemService("accessibility");
        if((((AccessibilityManager)v4).isEnabled()) && (((AccessibilityManager)v4).isTouchExplorationEnabled())) {
            return 0;
        }

        int v4_1 = arg5.getAction();
        if(v4_1 != 7) {
            if(v4_1 != 10) {
            }
            else {
                this.hide();
            }
        }
        else if((this.mAnchor.isEnabled()) && this.mPopup == null) {
            this.mAnchorX = ((int)arg5.getX());
            this.mAnchorY = ((int)arg5.getY());
            this.mAnchor.removeCallbacks(this.mShowRunnable);
            this.mAnchor.postDelayed(this.mShowRunnable, ((long)ViewConfiguration.getLongPressTimeout()));
        }

        return 0;
    }

    public boolean onLongClick(View arg2) {
        this.mAnchorX = arg2.getWidth() / 2;
        this.mAnchorY = arg2.getHeight() / 2;
        this.show(true);
        return 1;
    }

    public void onViewAttachedToWindow(View arg1) {
    }

    public void onViewDetachedFromWindow(View arg1) {
        this.hide();
    }

    public static void setTooltipText(View arg1, CharSequence arg2) {
        if(TextUtils.isEmpty(arg2)) {
            if(TooltipCompatHandler.sActiveHandler != null && TooltipCompatHandler.sActiveHandler.mAnchor == arg1) {
                TooltipCompatHandler.sActiveHandler.hide();
            }

            arg1.setOnLongClickListener(null);
            arg1.setLongClickable(false);
            arg1.setOnHoverListener(null);
        }
        else {
            new TooltipCompatHandler(arg1, arg2);
        }
    }

    private void show(boolean arg8) {
        long v4;
        if(!ViewCompat.isAttachedToWindow(this.mAnchor)) {
            return;
        }

        if(TooltipCompatHandler.sActiveHandler != null) {
            TooltipCompatHandler.sActiveHandler.hide();
        }

        TooltipCompatHandler.sActiveHandler = this;
        this.mFromTouch = arg8;
        this.mPopup = new TooltipPopup(this.mAnchor.getContext());
        this.mPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
        this.mAnchor.addOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        if(this.mFromTouch) {
            v4 = 2500;
        }
        else if((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
            v4 = 3000 - (((long)ViewConfiguration.getLongPressTimeout()));
        }
        else {
            v4 = 15000 - (((long)ViewConfiguration.getLongPressTimeout()));
        }

        this.mAnchor.removeCallbacks(this.mHideRunnable);
        this.mAnchor.postDelayed(this.mHideRunnable, v4);
    }
}

