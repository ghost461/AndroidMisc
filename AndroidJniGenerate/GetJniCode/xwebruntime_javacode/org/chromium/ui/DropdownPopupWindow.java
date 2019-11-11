package org.chromium.ui;

import android.content.Context;
import android.os.Build$VERSION;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;

public class DropdownPopupWindow {
    private DropdownPopupWindowInterface mPopup;

    public DropdownPopupWindow(Context arg3, View arg4) {
        super();
        this.mPopup = Build$VERSION.SDK_INT >= 24 ? new DropdownPopupWindowImpl(arg3, arg4) : new DropdownPopupWindowJellyBean(arg3, arg4);
    }

    public void disableHideOnOutsideTap() {
        this.mPopup.disableHideOnOutsideTap();
    }

    public void dismiss() {
        this.mPopup.dismiss();
    }

    public ListView getListView() {
        return this.mPopup.getListView();
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void postShow() {
        this.mPopup.postShow();
    }

    public void setAdapter(ListAdapter arg2) {
        this.mPopup.setAdapter(arg2);
    }

    public void setContentDescriptionForAccessibility(CharSequence arg2) {
        this.mPopup.setContentDescriptionForAccessibility(arg2);
    }

    public void setInitialSelection(int arg2) {
        this.mPopup.setInitialSelection(arg2);
    }

    public void setOnDismissListener(PopupWindow$OnDismissListener arg2) {
        this.mPopup.setOnDismissListener(arg2);
    }

    public void setOnItemClickListener(AdapterView$OnItemClickListener arg2) {
        this.mPopup.setOnItemClickListener(arg2);
    }

    public void setRtl(boolean arg2) {
        this.mPopup.setRtl(arg2);
    }

    public void show() {
        this.mPopup.show();
    }
}

