package org.chromium.ui;

import android.content.Context;
import android.graphics.Rect;
import android.view.View$OnLayoutChangeListener;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Log;

class DropdownPopupWindowJellyBean implements DropdownPopupWindowInterface {
    private static final String TAG = "AutofillPopup";
    ListAdapter mAdapter;
    private final View mAnchorView;
    private CharSequence mDescription;
    private int mInitialSelection;
    private View$OnLayoutChangeListener mLayoutChangeListener;
    private ListPopupWindow mListPopupWindow;
    private PopupWindow$OnDismissListener mOnDismissListener;
    private boolean mRtl;

    static {
    }

    public DropdownPopupWindowJellyBean(Context arg5, View arg6) {
        super();
        this.mInitialSelection = -1;
        this.mListPopupWindow = new ListPopupWindow(arg5, null, 0, 0x7F0D00A9);
        this.mAnchorView = arg6;
        this.mAnchorView.setId(0x7F07003E);
        this.mAnchorView.setTag(this);
        this.mLayoutChangeListener = new View$OnLayoutChangeListener() {
            public void onLayoutChange(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
                if(arg1 == DropdownPopupWindowJellyBean.access$000(DropdownPopupWindowJellyBean.this)) {
                    DropdownPopupWindowJellyBean.this.show();
                }
            }
        };
        this.mAnchorView.addOnLayoutChangeListener(this.mLayoutChangeListener);
        this.mListPopupWindow.setOnDismissListener(new PopupWindow$OnDismissListener() {
            public void onDismiss() {
                if(DropdownPopupWindowJellyBean.access$100(DropdownPopupWindowJellyBean.this) != null) {
                    DropdownPopupWindowJellyBean.access$100(DropdownPopupWindowJellyBean.this).onDismiss();
                }

                DropdownPopupWindowJellyBean.access$000(DropdownPopupWindowJellyBean.this).removeOnLayoutChangeListener(DropdownPopupWindowJellyBean.access$200(DropdownPopupWindowJellyBean.this));
                DropdownPopupWindowJellyBean.access$000(DropdownPopupWindowJellyBean.this).setTag(null);
            }
        });
        this.mListPopupWindow.setAnchorView(this.mAnchorView);
        Rect v5 = new Rect();
        this.mListPopupWindow.getBackground().getPadding(v5);
        this.mListPopupWindow.setVerticalOffset(-v5.top);
    }

    static View access$000(DropdownPopupWindowJellyBean arg0) {
        return arg0.mAnchorView;
    }

    static PopupWindow$OnDismissListener access$100(DropdownPopupWindowJellyBean arg0) {
        return arg0.mOnDismissListener;
    }

    static View$OnLayoutChangeListener access$200(DropdownPopupWindowJellyBean arg0) {
        return arg0.mLayoutChangeListener;
    }

    public void disableHideOnOutsideTap() {
        try {
            ListPopupWindow.class.getMethod("setForceIgnoreOutsideTouch", Boolean.TYPE).invoke(this.mListPopupWindow, Boolean.valueOf(true));
        }
        catch(Exception v2) {
            Log.e("AutofillPopup", "ListPopupWindow.setForceIgnoreOutsideTouch not found", new Object[]{v2});
        }
    }

    public void dismiss() {
        this.mListPopupWindow.dismiss();
    }

    public ListView getListView() {
        return this.mListPopupWindow.getListView();
    }

    public boolean isShowing() {
        return this.mListPopupWindow.isShowing();
    }

    private int measureContentWidth() {
        return UiUtils.computeMaxWidthOfListAdapterItems(this.mAdapter);
    }

    public void postShow() {
        this.mListPopupWindow.postShow();
    }

    public void setAdapter(ListAdapter arg2) {
        this.mAdapter = arg2;
        this.mListPopupWindow.setAdapter(arg2);
    }

    public void setContentDescriptionForAccessibility(CharSequence arg1) {
        this.mDescription = arg1;
    }

    public void setInitialSelection(int arg1) {
        this.mInitialSelection = arg1;
    }

    public void setOnDismissListener(PopupWindow$OnDismissListener arg1) {
        this.mOnDismissListener = arg1;
    }

    public void setOnItemClickListener(AdapterView$OnItemClickListener arg2) {
        this.mListPopupWindow.setOnItemClickListener(arg2);
    }

    public void setRtl(boolean arg1) {
        this.mRtl = arg1;
    }

    public void show() {
        this.mListPopupWindow.setInputMethodMode(1);
        int v0 = UiUtils.computeMaxWidthOfListAdapterItems(this.mAdapter);
        float v1 = ((float)this.mAnchorView.getLayoutParams().width);
        Rect v2 = new Rect();
        this.mListPopupWindow.getBackground().getPadding(v2);
        if((((float)(v2.left + v0 + v2.right))) > v1) {
            this.mListPopupWindow.setContentWidth(v0);
            Rect v0_1 = new Rect();
            this.mAnchorView.getWindowVisibleDisplayFrame(v0_1);
            if(this.mListPopupWindow.getWidth() > v0_1.width()) {
                this.mListPopupWindow.setWidth(v0_1.width());
            }
        }
        else {
            this.mListPopupWindow.setWidth(-2);
        }

        boolean v0_2 = this.mListPopupWindow.isShowing();
        this.mListPopupWindow.show();
        this.mListPopupWindow.getListView().setDividerHeight(0);
        ApiCompatibilityUtils.setLayoutDirection(this.mListPopupWindow.getListView(), this.mRtl);
        if(!v0_2) {
            this.mListPopupWindow.getListView().setContentDescription(this.mDescription);
            this.mListPopupWindow.getListView().sendAccessibilityEvent(0x20);
        }

        if(this.mInitialSelection >= 0) {
            this.mListPopupWindow.getListView().setSelection(this.mInitialSelection);
            this.mInitialSelection = -1;
        }
    }
}

