package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter$FilterResults;
import android.widget.Filter;

class CursorFilter extends Filter {
    interface CursorFilterClient {
        void changeCursor(Cursor arg1);

        CharSequence convertToString(Cursor arg1);

        Cursor getCursor();

        Cursor runQueryOnBackgroundThread(CharSequence arg1);
    }

    CursorFilterClient mClient;

    CursorFilter(CursorFilterClient arg1) {
        super();
        this.mClient = arg1;
    }

    public CharSequence convertResultToString(Object arg2) {
        return this.mClient.convertToString(((Cursor)arg2));
    }

    protected Filter$FilterResults performFiltering(CharSequence arg3) {
        Cursor v3 = this.mClient.runQueryOnBackgroundThread(arg3);
        Filter$FilterResults v0 = new Filter$FilterResults();
        if(v3 != null) {
            v0.count = v3.getCount();
            v0.values = v3;
        }
        else {
            v0.count = 0;
            v0.values = null;
        }

        return v0;
    }

    protected void publishResults(CharSequence arg2, Filter$FilterResults arg3) {
        Cursor v2 = this.mClient.getCursor();
        if(arg3.values != null && arg3.values != v2) {
            this.mClient.changeCursor(arg3.values);
        }
    }
}

