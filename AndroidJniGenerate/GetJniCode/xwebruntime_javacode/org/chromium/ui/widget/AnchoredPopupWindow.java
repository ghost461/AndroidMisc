package org.chromium.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.WindowManager$BadTokenException;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.PopupWindow;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.VisibleForTesting;

public class AnchoredPopupWindow implements View$OnTouchListener, Observer {
    class org.chromium.ui.widget.AnchoredPopupWindow$1 implements Runnable {
        org.chromium.ui.widget.AnchoredPopupWindow$1(AnchoredPopupWindow arg1) {
            AnchoredPopupWindow.this = arg1;
            super();
        }

        public void run() {
            if(AnchoredPopupWindow.this.mPopupWindow.isShowing()) {
                AnchoredPopupWindow.this.dismiss();
            }
        }
    }

    class org.chromium.ui.widget.AnchoredPopupWindow$2 implements PopupWindow$OnDismissListener {
        org.chromium.ui.widget.AnchoredPopupWindow$2(AnchoredPopupWindow arg1) {
            AnchoredPopupWindow.this = arg1;
            super();
        }

        public void onDismiss() {
            if(AnchoredPopupWindow.this.mIgnoreDismissal) {
                return;
            }

            AnchoredPopupWindow.this.mHandler.removeCallbacks(AnchoredPopupWindow.this.mDismissRunnable);
            Iterator v0 = AnchoredPopupWindow.this.mDismissListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onDismiss();
            }

            AnchoredPopupWindow.this.mRectProvider.stopObserving();
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface HorizontalOrientation {
    }

    public interface LayoutObserver {
        void onPreLayoutChange(boolean arg1, int arg2, int arg3, int arg4, int arg5, Rect arg6);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface VerticalOrientation {
    }

    public static final int HORIZONTAL_ORIENTATION_CENTER = 1;
    public static final int HORIZONTAL_ORIENTATION_MAX_AVAILABLE_SPACE = 0;
    public static final int VERTICAL_ORIENTATION_ABOVE = 2;
    public static final int VERTICAL_ORIENTATION_BELOW = 1;
    public static final int VERTICAL_ORIENTATION_MAX_AVAILABLE_SPACE;
    private final Rect mCachedPaddingRect;
    private final Rect mCachedWindowRect;
    private final Context mContext;
    private final PopupWindow$OnDismissListener mDismissListener;
    private ObserverList mDismissListeners;
    private boolean mDismissOnTouchInteraction;
    private final Runnable mDismissRunnable;
    private final Handler mHandler;
    private int mHeight;
    private boolean mHorizontalOverlapAnchor;
    private boolean mIgnoreDismissal;
    private LayoutObserver mLayoutObserver;
    private int mMarginPx;
    private int mMaxWidthPx;
    private final PopupWindow mPopupWindow;
    private boolean mPositionBelow;
    private boolean mPositionToLeft;
    private int mPreferredHorizontalOrientation;
    private int mPreferredVerticalOrientation;
    private final RectProvider mRectProvider;
    private final View mRootView;
    private View$OnTouchListener mTouchListener;
    private boolean mUpdateOrientationOnChange;
    private boolean mVerticalOverlapAnchor;
    private int mWidth;
    private int mX;
    private int mY;

    public AnchoredPopupWindow(Context arg2, View arg3, Drawable arg4, View arg5, RectProvider arg6) {
        super();
        this.mCachedPaddingRect = new Rect();
        this.mCachedWindowRect = new Rect();
        this.mDismissRunnable = new org.chromium.ui.widget.AnchoredPopupWindow$1(this);
        this.mDismissListener = new org.chromium.ui.widget.AnchoredPopupWindow$2(this);
        this.mDismissListeners = new ObserverList();
        this.mPreferredVerticalOrientation = 0;
        this.mPreferredHorizontalOrientation = 0;
        this.mContext = arg2;
        this.mRootView = arg3.getRootView();
        this.mPopupWindow = UiWidgetFactory.getInstance().createPopupWindow(this.mContext);
        this.mHandler = new Handler();
        this.mRectProvider = arg6;
        this.mPopupWindow.setWidth(-2);
        this.mPopupWindow.setHeight(-2);
        this.mPopupWindow.setBackgroundDrawable(arg4);
        this.mPopupWindow.setContentView(arg5);
        this.mPopupWindow.setTouchInterceptor(((View$OnTouchListener)this));
        this.mPopupWindow.setOnDismissListener(this.mDismissListener);
    }

    static PopupWindow access$000(AnchoredPopupWindow arg0) {
        return arg0.mPopupWindow;
    }

    static boolean access$100(AnchoredPopupWindow arg0) {
        return arg0.mIgnoreDismissal;
    }

    static Runnable access$200(AnchoredPopupWindow arg0) {
        return arg0.mDismissRunnable;
    }

    static Handler access$300(AnchoredPopupWindow arg0) {
        return arg0.mHandler;
    }

    static ObserverList access$400(AnchoredPopupWindow arg0) {
        return arg0.mDismissListeners;
    }

    static RectProvider access$500(AnchoredPopupWindow arg0) {
        return arg0.mRectProvider;
    }

    public void addOnDismissListener(PopupWindow$OnDismissListener arg2) {
        this.mDismissListeners.addObserver(arg2);
    }

    private static int clamp(int arg1, int arg2, int arg3) {
        int v0 = arg2 > arg3 ? arg3 : arg2;
        if(arg2 > arg3) {
        }
        else {
            arg2 = arg3;
        }

        if(arg1 < v0) {
            arg1 = v0;
        }
        else if(arg1 > arg2) {
            arg1 = arg2;
        }

        return arg1;
    }

    public void dismiss() {
        this.mPopupWindow.dismiss();
    }

    @VisibleForTesting static int getMaxContentWidth(int arg0, int arg1, int arg2, int arg3) {
        arg1 -= arg2 * 2;
        if(arg0 == 0 || arg0 >= arg1) {
            arg0 = arg1;
        }
        else {
        }

        if(arg0 > arg3) {
            arg0 -= arg3;
        }
        else {
            arg0 = 0;
        }

        return arg0;
    }

    @VisibleForTesting static int getPopupX(Rect arg1, Rect arg2, int arg3, int arg4, boolean arg5, int arg6, boolean arg7) {
        int v1;
        int v5;
        if(arg6 == 1) {
            v5 = arg1.left + (arg1.width() - arg3) / 2 + arg4;
        }
        else if(arg7) {
            v1 = arg5 ? arg1.right : arg1.left;
            v5 = v1 - arg3;
        }
        else {
            v1 = arg5 ? arg1.left : arg1.right;
            v5 = v1;
        }

        return AnchoredPopupWindow.clamp(v5, arg4, arg2.right - arg3 - arg4);
    }

    @VisibleForTesting static int getPopupY(Rect arg0, int arg1, boolean arg2, boolean arg3) {
        int v0;
        if(arg3) {
            v0 = arg2 ? arg0.top : arg0.bottom;
            return v0;
        }

        v0 = arg2 ? arg0.bottom : arg0.top;
        return v0 - arg1;
    }

    @VisibleForTesting static int getSpaceLeftOfAnchor(Rect arg0, Rect arg1, boolean arg2) {
        int v0 = arg2 ? arg0.right : arg0.left;
        return v0 - arg1.left;
    }

    @VisibleForTesting static int getSpaceRightOfAnchor(Rect arg0, Rect arg1, boolean arg2) {
        int v1 = arg1.right;
        int v0 = arg2 ? arg0.left : arg0.right;
        return v1 - v0;
    }

    public boolean isShowing() {
        return this.mPopupWindow.isShowing();
    }

    public void onRectChanged() {
        this.updatePopupLayout();
    }

    public void onRectHidden() {
        this.dismiss();
    }

    @SuppressLint(value={"ClickableViewAccessibility"}) public boolean onTouch(View arg2, MotionEvent arg3) {
        boolean v2 = this.mTouchListener == null || !this.mTouchListener.onTouch(arg2, arg3) ? false : true;
        if(this.mDismissOnTouchInteraction) {
            this.dismiss();
        }

        return v2;
    }

    public void removeOnDismissListener(PopupWindow$OnDismissListener arg2) {
        this.mDismissListeners.removeObserver(arg2);
    }

    public void setAnimationStyle(int arg2) {
        this.mPopupWindow.setAnimationStyle(arg2);
    }

    public void setBackgroundDrawable(Drawable arg2) {
        this.mPopupWindow.setBackgroundDrawable(arg2);
    }

    public void setDismissOnTouchInteraction(boolean arg2) {
        this.mDismissOnTouchInteraction = arg2;
        this.mPopupWindow.setOutsideTouchable(this.mDismissOnTouchInteraction);
    }

    public void setFocusable(boolean arg2) {
        this.mPopupWindow.setFocusable(true);
    }

    public void setHorizontalOverlapAnchor(boolean arg1) {
        this.mHorizontalOverlapAnchor = arg1;
    }

    public void setLayoutObserver(LayoutObserver arg1) {
        this.mLayoutObserver = arg1;
    }

    public void setMargin(int arg1) {
        this.mMarginPx = arg1;
    }

    public void setMaxWidth(int arg1) {
        this.mMaxWidthPx = arg1;
    }

    public void setOutsideTouchable(boolean arg2) {
        this.mPopupWindow.setOutsideTouchable(arg2);
    }

    public void setPreferredHorizontalOrientation(int arg1) {
        this.mPreferredHorizontalOrientation = arg1;
    }

    public void setPreferredVerticalOrientation(int arg1) {
        this.mPreferredVerticalOrientation = arg1;
    }

    public void setTouchInterceptor(View$OnTouchListener arg1) {
        this.mTouchListener = arg1;
    }

    public void setUpdateOrientationOnChange(boolean arg1) {
        this.mUpdateOrientationOnChange = arg1;
    }

    public void setVerticalOverlapAnchor(boolean arg1) {
        this.mVerticalOverlapAnchor = arg1;
    }

    @VisibleForTesting static boolean shouldPositionLeftOfAnchor(int arg3, int arg4, int arg5, boolean arg6, boolean arg7) {
        boolean v0 = false;
        boolean v1 = true;
        boolean v2 = arg3 >= arg4 ? true : false;
        if(!arg7 || v2 == arg6) {
            v0 = v2;
        }
        else {
            if(!arg6 || arg5 > arg3) {
                v1 = v2;
            }
            else {
            }

            if(!arg6 && arg5 <= arg4) {
                return v0;
            }

            v0 = v1;
        }

        return v0;
    }

    public void show() {
        if(this.mPopupWindow.isShowing()) {
            return;
        }

        this.mRectProvider.startObserving(((Observer)this));
        this.updatePopupLayout();
        this.showPopupWindow();
    }

    private void showPopupWindow() {
        try {
            this.mPopupWindow.showAtLocation(this.mRootView, 0x800033, this.mX, this.mY);
            return;
        }
        catch(WindowManager$BadTokenException ) {
            return;
        }
    }

    private void updatePopupLayout() {
        AnchoredPopupWindow v1 = this;
        boolean v2 = v1.mPositionBelow;
        boolean v3 = v1.mPositionToLeft;
        boolean v5 = true;
        boolean v4 = !v1.mPopupWindow.isShowing() || (v1.mUpdateOrientationOnChange) ? false : true;
        v1.mPopupWindow.getBackground().getPadding(v1.mCachedPaddingRect);
        int v7 = v1.mCachedPaddingRect.left + v1.mCachedPaddingRect.right;
        int v8 = v1.mCachedPaddingRect.top + v1.mCachedPaddingRect.bottom;
        int v9 = AnchoredPopupWindow.getMaxContentWidth(v1.mMaxWidthPx, v1.mRootView.getWidth(), v1.mMarginPx, v8);
        View v10 = v1.mPopupWindow.getContentView();
        v9 = View$MeasureSpec.makeMeasureSpec(v9, 0x80000000);
        v10.measure(v9, View$MeasureSpec.makeMeasureSpec(0, 0));
        int v12 = v10.getMeasuredHeight();
        int v13 = v10.getMeasuredWidth();
        v1.mRootView.getWindowVisibleDisplayFrame(v1.mCachedWindowRect);
        int[] v15 = new int[2];
        v1.mRootView.getLocationOnScreen(v15);
        v1.mCachedWindowRect.offset(-v15[0], -v15[1]);
        Rect v11 = v1.mRectProvider.getRect();
        int v14 = v1.mVerticalOverlapAnchor ? v11.bottom : v11.top;
        v14 = v14 - v1.mCachedWindowRect.top - v8 - v1.mMarginPx;
        int v15_1 = v1.mCachedWindowRect.bottom;
        int v6 = v1.mVerticalOverlapAnchor ? v11.top : v11.bottom;
        v6 = v15_1 - v6 - v8 - v1.mMarginPx;
        v15_1 = v12 <= v6 ? 1 : 0;
        v12 = v12 <= v14 ? 1 : 0;
        if(v15_1 == 0 || v6 < v14) {
            if(v12 == 0) {
            }
            else {
                v5 = false;
            }
        }

        v1.mPositionBelow = v5;
        if((v4) && v2 != v1.mPositionBelow) {
            if((v2) && v15_1 != 0) {
                v1.mPositionBelow = true;
            }

            if(v2) {
                goto label_102;
            }

            if(v12 == 0) {
                goto label_102;
            }

            v1.mPositionBelow = false;
        }

    label_102:
        int v23 = v6;
        if(v1.mPreferredVerticalOrientation == 1 && v15_1 != 0) {
            v1.mPositionBelow = true;
        }

        if(v1.mPreferredVerticalOrientation == 2 && v12 != 0) {
            v1.mPositionBelow = false;
        }

        if(v1.mPreferredHorizontalOrientation == 0) {
            v1.mPositionToLeft = AnchoredPopupWindow.shouldPositionLeftOfAnchor(AnchoredPopupWindow.getSpaceLeftOfAnchor(v11, v1.mCachedWindowRect, v1.mHorizontalOverlapAnchor), AnchoredPopupWindow.getSpaceRightOfAnchor(v11, v1.mCachedWindowRect, v1.mHorizontalOverlapAnchor), v13 + v8 + v1.mMarginPx, v3, v4);
        }

        if(v1.mPositionBelow) {
            v14 = v23;
        }

        v10.measure(v9, View$MeasureSpec.makeMeasureSpec(v14, 0x80000000));
        v1.mWidth = v10.getMeasuredWidth() + v7;
        v1.mHeight = v10.getMeasuredHeight() + v8;
        v1.mX = AnchoredPopupWindow.getPopupX(v11, v1.mCachedWindowRect, v1.mWidth, v1.mMarginPx, v1.mHorizontalOverlapAnchor, v1.mPreferredHorizontalOrientation, v1.mPositionToLeft);
        v1.mY = AnchoredPopupWindow.getPopupY(v11, v1.mHeight, v1.mVerticalOverlapAnchor, v1.mPositionBelow);
        if(v1.mLayoutObserver != null) {
            v1.mLayoutObserver.onPreLayoutChange(v1.mPositionBelow, v1.mX, v1.mY, v1.mWidth, v1.mHeight, v11);
        }

        if((v1.mPopupWindow.isShowing()) && v1.mPositionBelow != v2) {
            try {
                v1.mIgnoreDismissal = true;
                v1.mPopupWindow.dismiss();
                this.showPopupWindow();
            }
            catch(Throwable v0) {
                v1.mIgnoreDismissal = false;
                throw v0;
            }

            v1.mIgnoreDismissal = false;
        }

        v1.mPopupWindow.update(v1.mX, v1.mY, v1.mWidth, v1.mHeight);
    }
}

