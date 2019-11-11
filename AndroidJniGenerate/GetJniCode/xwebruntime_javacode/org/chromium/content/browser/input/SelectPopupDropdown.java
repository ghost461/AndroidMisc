package org.chromium.content.browser.input;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.PopupWindow$OnDismissListener;
import java.util.List;
import org.chromium.ui.DropdownAdapter;
import org.chromium.ui.DropdownPopupWindow;

public class SelectPopupDropdown implements Ui {
    private final DropdownPopupWindow mDropdownPopupWindow;
    private final SelectPopup mSelectPopup;
    private boolean mSelectionNotified;

    public SelectPopupDropdown(SelectPopup arg9, Context arg10, View arg11, List arg12, int[] arg13, boolean arg14) {
        super();
        this.mSelectPopup = arg9;
        this.mDropdownPopupWindow = new DropdownPopupWindow(arg10, arg11);
        this.mDropdownPopupWindow.setOnItemClickListener(new AdapterView$OnItemClickListener() {
            public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                SelectPopupDropdown.access$000(SelectPopupDropdown.this, new int[]{arg3});
                SelectPopupDropdown.this.hide(false);
            }
        });
        int v9 = arg13.length > 0 ? arg13[0] : -1;
        this.mDropdownPopupWindow.setInitialSelection(v9);
        this.mDropdownPopupWindow.setAdapter(new DropdownAdapter(arg10, arg12, null, null, null, null, null));
        this.mDropdownPopupWindow.setRtl(arg14);
        this.mDropdownPopupWindow.setOnDismissListener(new PopupWindow$OnDismissListener() {
            public void onDismiss() {
                SelectPopupDropdown.access$000(SelectPopupDropdown.this, null);
            }
        });
    }

    static void access$000(SelectPopupDropdown arg0, int[] arg1) {
        arg0.notifySelection(arg1);
    }

    public void hide(boolean arg1) {
        if(arg1) {
            this.mDropdownPopupWindow.dismiss();
            this.notifySelection(null);
        }
        else {
            this.mSelectionNotified = true;
            this.mDropdownPopupWindow.dismiss();
        }
    }

    private void notifySelection(int[] arg2) {
        if(this.mSelectionNotified) {
            return;
        }

        this.mSelectPopup.selectMenuItems(arg2);
        this.mSelectionNotified = true;
    }

    public void show() {
        this.mDropdownPopupWindow.postShow();
    }
}

