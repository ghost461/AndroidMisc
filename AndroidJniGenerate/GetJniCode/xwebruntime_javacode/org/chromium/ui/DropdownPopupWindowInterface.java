package org.chromium.ui;

import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import org.chromium.base.VisibleForTesting;

@VisibleForTesting public interface DropdownPopupWindowInterface {
    void disableHideOnOutsideTap();

    void dismiss();

    ListView getListView();

    boolean isShowing();

    void postShow();

    void setAdapter(ListAdapter arg1);

    void setContentDescriptionForAccessibility(CharSequence arg1);

    void setInitialSelection(int arg1);

    void setOnDismissListener(PopupWindow$OnDismissListener arg1);

    void setOnItemClickListener(AdapterView$OnItemClickListener arg1);

    void setRtl(boolean arg1);

    void show();
}

