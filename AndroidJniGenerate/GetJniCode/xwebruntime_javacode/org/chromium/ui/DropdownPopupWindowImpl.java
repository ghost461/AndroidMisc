package org.chromium.ui;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View$OnLayoutChangeListener;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.ui.widget.AnchoredPopupWindow$LayoutObserver;
import org.chromium.ui.widget.AnchoredPopupWindow;
import org.chromium.ui.widget.ViewRectProvider;

class DropdownPopupWindowImpl implements DropdownPopupWindowInterface, LayoutObserver {
    ListAdapter mAdapter;
    private final View mAnchorView;
    private AnchoredPopupWindow mAnchoredPopupWindow;
    private Drawable mBackground;
    private final Context mContext;
    private CharSequence mDescription;
    private int mHorizontalPadding;
    private int mInitialSelection;
    private View$OnLayoutChangeListener mLayoutChangeListener;
    private ListView mListView;
    private boolean mRtl;

    static {
    }

    public DropdownPopupWindowImpl(Context arg10, View arg11) {
        super();
        this.mInitialSelection = -1;
        this.mContext = arg10;
        this.mAnchorView = arg11;
        this.mAnchorView.setId(0x7F07003E);
        this.mAnchorView.setTag(this);
        this.mLayoutChangeListener = new View$OnLayoutChangeListener() {
            public void onLayoutChange(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
                if(arg1 == DropdownPopupWindowImpl.access$000(DropdownPopupWindowImpl.this)) {
                    DropdownPopupWindowImpl.this.show();
                }
            }
        };
        this.mAnchorView.addOnLayoutChangeListener(this.mLayoutChangeListener);
        org.chromium.ui.DropdownPopupWindowImpl$2 v11 = new PopupWindow$OnDismissListener() {
            public void onDismiss() {
                DropdownPopupWindowImpl.access$100(DropdownPopupWindowImpl.this).dismiss();
                DropdownPopupWindowImpl.access$000(DropdownPopupWindowImpl.this).removeOnLayoutChangeListener(DropdownPopupWindowImpl.access$200(DropdownPopupWindowImpl.this));
                DropdownPopupWindowImpl.access$000(DropdownPopupWindowImpl.this).setTag(null);
            }
        };
        this.mListView = new ListView(arg10);
        ViewRectProvider v0 = new ViewRectProvider(this.mAnchorView);
        v0.setIncludePadding(true);
        this.mBackground = ApiCompatibilityUtils.getDrawable(arg10.getResources(), 0x7F06005D);
        this.mAnchoredPopupWindow = new AnchoredPopupWindow(arg10, this.mAnchorView, this.mBackground, this.mListView, v0);
        this.mAnchoredPopupWindow.addOnDismissListener(((PopupWindow$OnDismissListener)v11));
        this.mAnchoredPopupWindow.setLayoutObserver(((LayoutObserver)this));
        Rect v10 = new Rect();
        this.mBackground.getPadding(v10);
        v0.setInsetPx(0, v10.bottom, 0, v10.top);
        this.mHorizontalPadding = v10.right + v10.left;
        this.mAnchoredPopupWindow.setPreferredHorizontalOrientation(1);
        this.mAnchoredPopupWindow.setUpdateOrientationOnChange(true);
        this.mAnchoredPopupWindow.setOutsideTouchable(true);
    }

    static View access$000(DropdownPopupWindowImpl arg0) {
        return arg0.mAnchorView;
    }

    static AnchoredPopupWindow access$100(DropdownPopupWindowImpl arg0) {
        return arg0.mAnchoredPopupWindow;
    }

    static View$OnLayoutChangeListener access$200(DropdownPopupWindowImpl arg0) {
        return arg0.mLayoutChangeListener;
    }

    public void disableHideOnOutsideTap() {
        this.mAnchoredPopupWindow.setDismissOnTouchInteraction(false);
    }

    public void dismiss() {
        this.mAnchoredPopupWindow.dismiss();
    }

    public ListView getListView() {
        return this.mListView;
    }

    public boolean isShowing() {
        return this.mAnchoredPopupWindow.isShowing();
    }

    private int measureContentWidth() {
        return UiUtils.computeMaxWidthOfListAdapterItems(this.mAdapter);
    }

    public void onPreLayoutChange(boolean arg1, int arg2, int arg3, int arg4, int arg5, Rect arg6) {
        this.mBackground.setBounds(arg6);
        AnchoredPopupWindow v2 = this.mAnchoredPopupWindow;
        Drawable v1 = arg1 ? ApiCompatibilityUtils.getDrawable(this.mContext.getResources(), 0x7F06005E) : ApiCompatibilityUtils.getDrawable(this.mContext.getResources(), 0x7F06005F);
        v2.setBackgroundDrawable(v1);
    }

    public void postShow() {
        this.mAnchoredPopupWindow.show();
    }

    public void setAdapter(ListAdapter arg2) {
        this.mAdapter = arg2;
        this.mListView.setAdapter(arg2);
        this.mAnchoredPopupWindow.onRectChanged();
    }

    public void setContentDescriptionForAccessibility(CharSequence arg1) {
        this.mDescription = arg1;
    }

    public void setInitialSelection(int arg1) {
        this.mInitialSelection = arg1;
    }

    public void setOnDismissListener(PopupWindow$OnDismissListener arg2) {
        this.mAnchoredPopupWindow.addOnDismissListener(arg2);
    }

    public void setOnItemClickListener(AdapterView$OnItemClickListener arg2) {
        this.mListView.setOnItemClickListener(arg2);
    }

    public void setRtl(boolean arg1) {
        this.mRtl = arg1;
    }

    public void show() {
        boolean v0 = this.mAnchoredPopupWindow.isShowing();
        this.mAnchoredPopupWindow.setVerticalOverlapAnchor(false);
        this.mAnchoredPopupWindow.setHorizontalOverlapAnchor(true);
        int v1 = this.measureContentWidth();
        if(this.mAnchorView.getWidth() < v1) {
            this.mAnchoredPopupWindow.setMaxWidth(v1 + this.mHorizontalPadding);
        }
        else {
            this.mAnchoredPopupWindow.setMaxWidth(this.mAnchorView.getWidth() + this.mHorizontalPadding);
        }

        this.mAnchoredPopupWindow.show();
        this.mListView.setDividerHeight(0);
        ApiCompatibilityUtils.setLayoutDirection(this.mListView, this.mRtl);
        if(!v0) {
            this.mListView.setContentDescription(this.mDescription);
            this.mListView.sendAccessibilityEvent(0x20);
        }

        if(this.mInitialSelection >= 0) {
            this.mListView.setSelection(this.mInitialSelection);
            this.mInitialSelection = -1;
        }
    }
}

