package org.chromium.content.browser.selection;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.PopupWindow;

public class LegacyPastePopupMenu implements View$OnClickListener, PastePopupMenu {
    private final PopupWindow mContainer;
    private final Context mContext;
    private final PastePopupMenuDelegate mDelegate;
    private final int mLineOffsetY;
    private final View mParent;
    private View mPasteView;
    private final int mPasteViewLayout;
    private int mRawPositionX;
    private int mRawPositionY;
    private int mStatusBarHeight;
    private final int mWidthOffsetX;

    public LegacyPastePopupMenu(Context arg2, View arg3, PastePopupMenuDelegate arg4) {
        super();
        this.mParent = arg3;
        this.mDelegate = arg4;
        this.mContext = arg2;
        this.mContainer = new PopupWindow(this.mContext, null, 0x10102C8);
        this.mContainer.setSplitTouchEnabled(true);
        this.mContainer.setClippingEnabled(false);
        this.mContainer.setAnimationStyle(0);
        this.mContainer.setWidth(-2);
        this.mContainer.setHeight(-2);
        TypedArray v2 = this.mContext.getTheme().obtainStyledAttributes(new int[]{0x1010314});
        this.mPasteViewLayout = v2.getResourceId(v2.getIndex(0), 0);
        v2.recycle();
        this.mLineOffsetY = ((int)TypedValue.applyDimension(1, 5f, this.mContext.getResources().getDisplayMetrics()));
        this.mWidthOffsetX = ((int)TypedValue.applyDimension(1, 30f, this.mContext.getResources().getDisplayMetrics()));
        int v2_1 = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(v2_1 > 0) {
            this.mStatusBarHeight = this.mContext.getResources().getDimensionPixelSize(v2_1);
        }
    }

    public void hide() {
        this.mContainer.dismiss();
    }

    public void onClick(View arg1) {
        this.paste();
        this.hide();
    }

    private void paste() {
        this.mDelegate.paste();
    }

    private void positionAt(int arg8, int arg9) {
        if(this.mRawPositionX == arg8 && this.mRawPositionY == arg9) {
            return;
        }

        this.mRawPositionX = arg8;
        this.mRawPositionY = arg9;
        View v0 = this.mContainer.getContentView();
        int v1 = v0.getMeasuredWidth();
        int v0_1 = v0.getMeasuredHeight();
        int v2 = ((int)((((float)arg8)) - (((float)v1)) / 2f));
        arg9 = arg9 - v0_1 - this.mLineOffsetY;
        int v3 = this.mParent.getSystemUiVisibility() == 0 ? this.mStatusBarHeight : 0;
        int v5 = this.mContext.getResources().getDisplayMetrics().widthPixels;
        int v6 = 2;
        if(arg9 < v3) {
            arg9 = arg9 + v0_1 + this.mLineOffsetY;
            v0_1 = this.mWidthOffsetX / v6;
            if(arg8 + v1 < v5) {
                v2 += v0_1 + v1 / v6;
            }
            else {
                v2 -= v0_1 + v1 / v6;
            }
        }
        else {
            v2 = Math.min(v5 - v1, Math.max(0, v2));
        }

        int[] v8 = new int[v6];
        this.mParent.getLocationInWindow(v8);
        this.mContainer.showAtLocation(this.mParent, 0, v2 + v8[0], arg9 + v8[1]);
    }

    public void show(Rect arg2) {
        this.hide();
        this.updateContent();
        this.positionAt(arg2.left, arg2.bottom);
    }

    private void updateContent() {
        if(this.mPasteView == null) {
            Object v0 = this.mContext.getSystemService("layout_inflater");
            if(v0 != null) {
                this.mPasteView = ((LayoutInflater)v0).inflate(this.mPasteViewLayout, null);
            }

            if(this.mPasteView == null) {
                throw new IllegalArgumentException("Unable to inflate TextEdit paste window");
            }

            int v0_1 = View$MeasureSpec.makeMeasureSpec(0, 0);
            this.mPasteView.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            this.mPasteView.measure(v0_1, v0_1);
            this.mPasteView.setOnClickListener(((View$OnClickListener)this));
        }

        this.mContainer.setContentView(this.mPasteView);
    }
}

