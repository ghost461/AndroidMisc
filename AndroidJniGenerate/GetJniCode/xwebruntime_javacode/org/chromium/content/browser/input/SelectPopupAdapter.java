package org.chromium.content.browser.input;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SelectPopupAdapter extends ArrayAdapter {
    private boolean mAreAllItemsEnabled;
    private List mItems;

    public SelectPopupAdapter(Context arg2, int arg3, List arg4) {
        super(arg2, arg3, arg4);
        this.mItems = new ArrayList(((Collection)arg4));
        this.mAreAllItemsEnabled = true;
        arg3 = 0;
        while(arg3 < this.mItems.size()) {
            if(this.mItems.get(arg3).getType() != 2) {
                this.mAreAllItemsEnabled = false;
            }
            else {
                ++arg3;
                continue;
            }

            return;
        }
    }

    public boolean areAllItemsEnabled() {
        return this.mAreAllItemsEnabled;
    }

    public View getView(int arg3, View arg4, ViewGroup arg5) {
        Drawable v0 = null;
        if(arg3 >= 0) {
            if(arg3 >= this.getCount()) {
            }
            else {
                arg4 = super.getView(arg3, arg4, arg5);
                arg4.setText(this.mItems.get(arg3).getLabel());
                if((arg4 instanceof CheckedTextView)) {
                    View v5 = arg4;
                    if(this.mItems.get(arg3).getType() == 0) {
                        if(((CheckedTextView)v5).getCheckMarkDrawable() != null) {
                            ((CheckedTextView)v5).setTag(((CheckedTextView)v5).getCheckMarkDrawable());
                            ((CheckedTextView)v5).setCheckMarkDrawable(v0);
                        }
                    }
                    else if(((CheckedTextView)v5).getCheckMarkDrawable() == null) {
                        ((CheckedTextView)v5).setCheckMarkDrawable(((CheckedTextView)v5).getTag());
                    }
                }

                boolean v5_1 = true;
                if(this.mItems.get(arg3).getType() != 1) {
                }
                else {
                    v5_1 = false;
                }

                arg4.setEnabled(v5_1);
                return arg4;
            }
        }

        return ((View)v0);
    }

    public boolean isEnabled(int arg3) {
        boolean v0 = false;
        if(arg3 >= 0) {
            if(arg3 >= this.getCount()) {
            }
            else {
                if(this.mItems.get(arg3).getType() == 2) {
                    v0 = true;
                }

                return v0;
            }
        }

        return 0;
    }
}

