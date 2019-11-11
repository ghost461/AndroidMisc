package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class CursorAdapter extends BaseAdapter implements CursorFilterClient, Filterable {
    class ChangeObserver extends ContentObserver {
        ChangeObserver(CursorAdapter arg1) {
            CursorAdapter.this = arg1;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return 1;
        }

        public void onChange(boolean arg1) {
            CursorAdapter.this.onContentChanged();
        }
    }

    class MyDataSetObserver extends DataSetObserver {
        MyDataSetObserver(CursorAdapter arg1) {
            CursorAdapter.this = arg1;
            super();
        }

        public void onChanged() {
            CursorAdapter.this.mDataValid = true;
            CursorAdapter.this.notifyDataSetChanged();
        }

        public void onInvalidated() {
            CursorAdapter.this.mDataValid = false;
            CursorAdapter.this.notifyDataSetInvalidated();
        }
    }

    @Deprecated public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected boolean mAutoRequery;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected ChangeObserver mChangeObserver;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected Context mContext;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected Cursor mCursor;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected CursorFilter mCursorFilter;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected DataSetObserver mDataSetObserver;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected boolean mDataValid;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected FilterQueryProvider mFilterQueryProvider;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected int mRowIDColumn;

    @Deprecated public CursorAdapter(Context arg2, Cursor arg3) {
        super();
        this.init(arg2, arg3, 1);
    }

    public CursorAdapter(Context arg1, Cursor arg2, int arg3) {
        super();
        this.init(arg1, arg2, arg3);
    }

    public CursorAdapter(Context arg1, Cursor arg2, boolean arg3) {
        super();
        int v3 = arg3 ? 1 : 2;
        this.init(arg1, arg2, v3);
    }

    public abstract void bindView(View arg1, Context arg2, Cursor arg3);

    public void changeCursor(Cursor arg1) {
        arg1 = this.swapCursor(arg1);
        if(arg1 != null) {
            arg1.close();
        }
    }

    public CharSequence convertToString(Cursor arg1) {
        String v1 = arg1 == null ? "" : arg1.toString();
        return ((CharSequence)v1);
    }

    public int getCount() {
        if((this.mDataValid) && this.mCursor != null) {
            return this.mCursor.getCount();
        }

        return 0;
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public View getDropDownView(int arg2, View arg3, ViewGroup arg4) {
        if(this.mDataValid) {
            this.mCursor.moveToPosition(arg2);
            if(arg3 == null) {
                arg3 = this.newDropDownView(this.mContext, this.mCursor, arg4);
            }

            this.bindView(arg3, this.mContext, this.mCursor);
            return arg3;
        }

        return null;
    }

    public Filter getFilter() {
        if(this.mCursorFilter == null) {
            this.mCursorFilter = new CursorFilter(((CursorFilterClient)this));
        }

        return this.mCursorFilter;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.mFilterQueryProvider;
    }

    public Object getItem(int arg2) {
        if((this.mDataValid) && this.mCursor != null) {
            this.mCursor.moveToPosition(arg2);
            return this.mCursor;
        }

        return null;
    }

    public long getItemId(int arg4) {
        long v1 = 0;
        if((this.mDataValid) && this.mCursor != null) {
            if(this.mCursor.moveToPosition(arg4)) {
                return this.mCursor.getLong(this.mRowIDColumn);
            }
            else {
                return v1;
            }
        }

        return v1;
    }

    public View getView(int arg2, View arg3, ViewGroup arg4) {
        if(!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }

        if(!this.mCursor.moveToPosition(arg2)) {
            StringBuilder v4 = new StringBuilder();
            v4.append("couldn\'t move cursor to position ");
            v4.append(arg2);
            throw new IllegalStateException(v4.toString());
        }

        if(arg3 == null) {
            arg3 = this.newView(this.mContext, this.mCursor, arg4);
        }

        this.bindView(arg3, this.mContext, this.mCursor);
        return arg3;
    }

    public boolean hasStableIds() {
        return 1;
    }

    void init(Context arg4, Cursor arg5, int arg6) {
        boolean v1 = false;
        if((arg6 & 1) == 1) {
            arg6 |= 2;
            this.mAutoRequery = true;
        }
        else {
            this.mAutoRequery = false;
        }

        if(arg5 != null) {
            v1 = true;
        }

        this.mCursor = arg5;
        this.mDataValid = v1;
        this.mContext = arg4;
        int v4 = v1 ? arg5.getColumnIndexOrThrow("_id") : -1;
        this.mRowIDColumn = v4;
        if((arg6 & 2) == 2) {
            this.mChangeObserver = new ChangeObserver(this);
            this.mDataSetObserver = new MyDataSetObserver(this);
        }
        else {
            this.mChangeObserver = null;
            this.mDataSetObserver = null;
        }

        if(v1) {
            if(this.mChangeObserver != null) {
                arg5.registerContentObserver(this.mChangeObserver);
            }

            if(this.mDataSetObserver == null) {
                return;
            }

            arg5.registerDataSetObserver(this.mDataSetObserver);
        }
    }

    @Deprecated protected void init(Context arg1, Cursor arg2, boolean arg3) {
        int v3 = arg3 ? 1 : 2;
        this.init(arg1, arg2, v3);
    }

    public View newDropDownView(Context arg1, Cursor arg2, ViewGroup arg3) {
        return this.newView(arg1, arg2, arg3);
    }

    public abstract View newView(Context arg1, Cursor arg2, ViewGroup arg3);

    protected void onContentChanged() {
        if((this.mAutoRequery) && this.mCursor != null && !this.mCursor.isClosed()) {
            this.mDataValid = this.mCursor.requery();
        }
    }

    public Cursor runQueryOnBackgroundThread(CharSequence arg2) {
        if(this.mFilterQueryProvider != null) {
            return this.mFilterQueryProvider.runQuery(arg2);
        }

        return this.mCursor;
    }

    public void setFilterQueryProvider(FilterQueryProvider arg1) {
        this.mFilterQueryProvider = arg1;
    }

    public Cursor swapCursor(Cursor arg3) {
        if(arg3 == this.mCursor) {
            return null;
        }

        Cursor v0 = this.mCursor;
        if(v0 != null) {
            if(this.mChangeObserver != null) {
                v0.unregisterContentObserver(this.mChangeObserver);
            }

            if(this.mDataSetObserver == null) {
                goto label_14;
            }

            v0.unregisterDataSetObserver(this.mDataSetObserver);
        }

    label_14:
        this.mCursor = arg3;
        if(arg3 != null) {
            if(this.mChangeObserver != null) {
                arg3.registerContentObserver(this.mChangeObserver);
            }

            if(this.mDataSetObserver != null) {
                arg3.registerDataSetObserver(this.mDataSetObserver);
            }

            this.mRowIDColumn = arg3.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            this.notifyDataSetChanged();
        }
        else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            this.notifyDataSetInvalidated();
        }

        return v0;
    }
}

