package org.chromium.content.browser.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

class DateTimeSuggestionListAdapter extends ArrayAdapter {
    private final Context mContext;

    DateTimeSuggestionListAdapter(Context arg2, List arg3) {
        super(arg2, 0x7F090021, arg3);
        this.mContext = arg2;
    }

    public int getCount() {
        return super.getCount() + 1;
    }

    public View getView(int arg3, View arg4, ViewGroup arg5) {
        if(arg4 == null) {
            arg4 = LayoutInflater.from(this.mContext).inflate(0x7F090021, arg5, false);
        }

        View v5 = arg4.findViewById(0x7F070034);
        View v0 = arg4.findViewById(0x7F070033);
        if(arg3 == this.getCount() - 1) {
            ((TextView)v5).setText(this.mContext.getText(0x7F0C0042));
            ((TextView)v0).setText("");
        }
        else {
            ((TextView)v5).setText(this.getItem(arg3).localizedValue());
            ((TextView)v0).setText(this.getItem(arg3).label());
        }

        return arg4;
    }
}

