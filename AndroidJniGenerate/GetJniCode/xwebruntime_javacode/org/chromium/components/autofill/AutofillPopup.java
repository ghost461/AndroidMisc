package org.chromium.components.autofill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View$AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow$OnDismissListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.ui.DropdownPopupWindow;

public class AutofillPopup extends DropdownPopupWindow implements AdapterView$OnItemClickListener, AdapterView$OnItemLongClickListener, PopupWindow$OnDismissListener {
    class org.chromium.components.autofill.AutofillPopup$1 implements Runnable {
        org.chromium.components.autofill.AutofillPopup$1(AutofillPopup arg1) {
            AutofillPopup.this = arg1;
            super();
        }

        public void run() {
            AutofillPopup.this.mAutofillDelegate.accessibilityFocusCleared();
        }
    }

    private static final long CLEAR_ACCESSIBILITY_FOCUS_DELAY_MS = 100;
    private static final int ITEM_ID_SEPARATOR_ENTRY = -3;
    private final AutofillDelegate mAutofillDelegate;
    private final Runnable mClearAccessibilityFocusRunnable;
    private final Context mContext;
    private List mSuggestions;

    static {
    }

    public AutofillPopup(Context arg1, View arg2, AutofillDelegate arg3) {
        super(arg1, arg2);
        this.mClearAccessibilityFocusRunnable = new org.chromium.components.autofill.AutofillPopup$1(this);
        this.mContext = arg1;
        this.mAutofillDelegate = arg3;
        this.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        this.setOnDismissListener(((PopupWindow$OnDismissListener)this));
        this.disableHideOnOutsideTap();
        this.setContentDescriptionForAccessibility(this.mContext.getString(0x7F0C002F));
    }

    static AutofillDelegate access$000(AutofillPopup arg0) {
        return arg0.mAutofillDelegate;
    }

    static Runnable access$100(AutofillPopup arg0) {
        return arg0.mClearAccessibilityFocusRunnable;
    }

    @SuppressLint(value={"InlinedApi"}) public void filterAndShow(AutofillSuggestion[] arg13, boolean arg14, int arg15, int arg16, int arg17, int arg18) {
        AutofillPopup v0 = this;
        AutofillSuggestion[] v1 = arg13;
        v0.mSuggestions = new ArrayList(Arrays.asList(((Object[])v1)));
        ArrayList v6 = new ArrayList();
        HashSet v7 = new HashSet();
        int v2;
        for(v2 = 0; v2 < v1.length; ++v2) {
            if(v1[v2].getSuggestionId() == -3) {
                v7.add(Integer.valueOf(v6.size()));
            }
            else {
                v6.add(v1[v2]);
            }
        }

        ListAdapter v1_1 = null;
        Context v5 = v0.mContext;
        Integer v2_1 = null;
        Integer v8 = arg15 == 0 ? v2_1 : Integer.valueOf(arg15);
        Integer v9 = arg16 == 0 ? v2_1 : Integer.valueOf(arg16);
        Integer v10 = arg17 == 0 ? v2_1 : Integer.valueOf(arg17);
        if(arg18 != 0) {
            v2_1 = Integer.valueOf(arg18);
        }

        Integer v11 = v2_1;
        super(v5, ((List)v6), ((Set)v7), v8, v9, v10, v11);
        v0.setAdapter(v1_1);
        v0.setRtl(arg14);
        v0.show();
        v0.getListView().setOnItemLongClickListener(((AdapterView$OnItemLongClickListener)v0));
        v0.getListView().setAccessibilityDelegate(new View$AccessibilityDelegate() {
            public boolean onRequestSendAccessibilityEvent(ViewGroup arg5, View arg6, AccessibilityEvent arg7) {
                AutofillPopup.this.getListView().removeCallbacks(AutofillPopup.this.mClearAccessibilityFocusRunnable);
                if(arg7.getEventType() == 0x10000) {
                    AutofillPopup.this.getListView().postDelayed(AutofillPopup.this.mClearAccessibilityFocusRunnable, 100);
                }

                return super.onRequestSendAccessibilityEvent(arg5, arg6, arg7);
            }
        });
    }

    public void onDismiss() {
        this.mAutofillDelegate.dismissed();
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        this.mAutofillDelegate.suggestionSelected(this.mSuggestions.indexOf(arg1.getAdapter().getItem(arg3)));
    }

    public boolean onItemLongClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        Object v1 = arg1.getAdapter().getItem(arg3);
        if(!((AutofillSuggestion)v1).isDeletable()) {
            return 0;
        }

        this.mAutofillDelegate.deleteSuggestion(this.mSuggestions.indexOf(v1));
        return 1;
    }
}

