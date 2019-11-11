package org.chromium.content.browser.input;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.WindowManager$BadTokenException;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import org.chromium.ui.widget.UiWidgetFactory;

public class SelectPopupDialog implements Ui {
    private static final int[] SELECT_DIALOG_ATTRS;
    private final AlertDialog mListBoxPopup;
    private final SelectPopup mSelectPopup;
    private boolean mSelectionNotified;

    static {
        SelectPopupDialog.SELECT_DIALOG_ATTRS = new int[]{0x7F0200BA, 0x7F0200BB};
    }

    public SelectPopupDialog(SelectPopup arg6, Context arg7, List arg8, boolean arg9, int[] arg10) {
        super();
        this.mSelectPopup = arg6;
        ListView v6 = new ListView(arg7);
        int v0 = 0;
        v6.setCacheColorHint(0);
        this.mListBoxPopup = UiWidgetFactory.getInstance().createAlertDialog(arg7);
        this.mListBoxPopup.setView(((View)v6));
        this.mListBoxPopup.setCancelable(true);
        SelectPopupDialog.setInverseBackgroundForced(this.mListBoxPopup);
        if(arg9) {
            this.mListBoxPopup.setButton(-1, this.mListBoxPopup.getContext().getString(0x104000A), new DialogInterface$OnClickListener(v6) {
                public void onClick(DialogInterface arg1, int arg2) {
                    SelectPopupDialog.access$100(SelectPopupDialog.this, SelectPopupDialog.access$000(this.val$listView));
                }
            });
            this.mListBoxPopup.setButton(-2, this.mListBoxPopup.getContext().getString(0x1040000), new DialogInterface$OnClickListener() {
                public void onClick(DialogInterface arg1, int arg2) {
                    SelectPopupDialog.access$100(SelectPopupDialog.this, null);
                }
            });
        }

        v6.setAdapter(new SelectPopupAdapter(this.mListBoxPopup.getContext(), this.getSelectDialogLayout(arg9), arg8));
        v6.setFocusableInTouchMode(true);
        if(arg9) {
            v6.setChoiceMode(2);
            while(v0 < arg10.length) {
                v6.setItemChecked(arg10[v0], true);
                ++v0;
            }
        }
        else {
            v6.setChoiceMode(1);
            v6.setOnItemClickListener(new AdapterView$OnItemClickListener(v6) {
                public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                    SelectPopupDialog.access$100(SelectPopupDialog.this, SelectPopupDialog.access$000(this.val$listView));
                    SelectPopupDialog.access$200(SelectPopupDialog.this).dismiss();
                }
            });
            if(arg10.length > 0) {
                v6.setSelection(arg10[0]);
                v6.setItemChecked(arg10[0], true);
            }
        }

        this.mListBoxPopup.setOnCancelListener(new DialogInterface$OnCancelListener() {
            public void onCancel(DialogInterface arg2) {
                SelectPopupDialog.access$100(SelectPopupDialog.this, null);
            }
        });
    }

    static int[] access$000(ListView arg0) {
        return SelectPopupDialog.getSelectedIndices(arg0);
    }

    static void access$100(SelectPopupDialog arg0, int[] arg1) {
        arg0.notifySelection(arg1);
    }

    static AlertDialog access$200(SelectPopupDialog arg0) {
        return arg0.mListBoxPopup;
    }

    private int getSelectDialogLayout(boolean arg4) {
        TypedArray v0 = this.mListBoxPopup.getContext().obtainStyledAttributes(0x7F0D00CA, SelectPopupDialog.SELECT_DIALOG_ATTRS);
        int v4 = v0.getResourceId((((int)arg4)) ^ 1, 0);
        v0.recycle();
        return v4;
    }

    private static int[] getSelectedIndices(ListView arg5) {
        SparseBooleanArray v5 = arg5.getCheckedItemPositions();
        int v0 = 0;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v5.size()) {
            if(v5.valueAt(v1)) {
                ++v2;
            }

            ++v1;
        }

        int[] v1_1 = new int[v2];
        v2 = 0;
        while(v0 < v5.size()) {
            if(v5.valueAt(v0)) {
                v1_1[v2] = v5.keyAt(v0);
                ++v2;
            }

            ++v0;
        }

        return v1_1;
    }

    public void hide(boolean arg1) {
        if(arg1) {
            this.mListBoxPopup.cancel();
            this.notifySelection(null);
        }
        else {
            this.mSelectionNotified = true;
            this.mListBoxPopup.cancel();
        }
    }

    private void notifySelection(int[] arg2) {
        if(this.mSelectionNotified) {
            return;
        }

        this.mSelectPopup.selectMenuItems(arg2);
        this.mSelectionNotified = true;
    }

    private static void setInverseBackgroundForced(AlertDialog arg1) {
        arg1.setInverseBackgroundForced(true);
    }

    public void show() {
        try {
            this.mListBoxPopup.show();
        }
        catch(WindowManager$BadTokenException ) {
            this.notifySelection(null);
        }
    }
}

