package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor arg1);
    }

    public interface ViewBinder {
        boolean setViewValue(View arg1, Cursor arg2, int arg3);
    }

    private CursorToStringConverter mCursorToStringConverter;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected int[] mFrom;
    String[] mOriginalFrom;
    private int mStringConversionColumn;
    @RestrictTo(value={Scope.LIBRARY_GROUP}) protected int[] mTo;
    private ViewBinder mViewBinder;

    @Deprecated public SimpleCursorAdapter(Context arg1, int arg2, Cursor arg3, String[] arg4, int[] arg5) {
        super(arg1, arg2, arg3);
        this.mStringConversionColumn = -1;
        this.mTo = arg5;
        this.mOriginalFrom = arg4;
        this.findColumns(arg3, arg4);
    }

    public SimpleCursorAdapter(Context arg1, int arg2, Cursor arg3, String[] arg4, int[] arg5, int arg6) {
        super(arg1, arg2, arg3, arg6);
        this.mStringConversionColumn = -1;
        this.mTo = arg5;
        this.mOriginalFrom = arg4;
        this.findColumns(arg3, arg4);
    }

    public void bindView(View arg9, Context arg10, Cursor arg11) {
        ViewBinder v10 = this.mViewBinder;
        int v0 = this.mTo.length;
        int[] v1 = this.mFrom;
        int[] v2 = this.mTo;
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            View v5 = arg9.findViewById(v2[v4]);
            if(v5 != null) {
                boolean v6 = v10 != null ? v10.setViewValue(v5, arg11, v1[v4]) : false;
                if(v6) {
                    goto label_42;
                }

                String v6_1 = arg11.getString(v1[v4]);
                if(v6_1 == null) {
                    v6_1 = "";
                }

                if((v5 instanceof TextView)) {
                    this.setViewText(((TextView)v5), v6_1);
                    goto label_42;
                }

                if((v5 instanceof ImageView)) {
                    this.setViewImage(((ImageView)v5), v6_1);
                    goto label_42;
                }

                StringBuilder v10_1 = new StringBuilder();
                v10_1.append(v5.getClass().getName());
                v10_1.append(" is not a ");
                v10_1.append(" view that can be bounds by this SimpleCursorAdapter");
                throw new IllegalStateException(v10_1.toString());
            }

        label_42:
        }
    }

    public void changeCursorAndColumns(Cursor arg1, String[] arg2, int[] arg3) {
        this.mOriginalFrom = arg2;
        this.mTo = arg3;
        this.findColumns(arg1, this.mOriginalFrom);
        super.changeCursor(arg1);
    }

    public CharSequence convertToString(Cursor arg3) {
        if(this.mCursorToStringConverter != null) {
            return this.mCursorToStringConverter.convertToString(arg3);
        }

        if(this.mStringConversionColumn > -1) {
            return arg3.getString(this.mStringConversionColumn);
        }

        return super.convertToString(arg3);
    }

    private void findColumns(Cursor arg5, String[] arg6) {
        if(arg5 != null) {
            int v0 = arg6.length;
            if(this.mFrom == null || this.mFrom.length != v0) {
                this.mFrom = new int[v0];
            }

            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.mFrom[v1] = arg5.getColumnIndexOrThrow(arg6[v1]);
            }
        }
        else {
            this.mFrom = null;
        }
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.mCursorToStringConverter;
    }

    public int getStringConversionColumn() {
        return this.mStringConversionColumn;
    }

    public ViewBinder getViewBinder() {
        return this.mViewBinder;
    }

    public void setCursorToStringConverter(CursorToStringConverter arg1) {
        this.mCursorToStringConverter = arg1;
    }

    public void setStringConversionColumn(int arg1) {
        this.mStringConversionColumn = arg1;
    }

    public void setViewBinder(ViewBinder arg1) {
        this.mViewBinder = arg1;
    }

    public void setViewImage(ImageView arg2, String arg3) {
        try {
            arg2.setImageResource(Integer.parseInt(arg3));
        }
        catch(NumberFormatException ) {
            arg2.setImageURI(Uri.parse(arg3));
        }
    }

    public void setViewText(TextView arg1, String arg2) {
        arg1.setText(((CharSequence)arg2));
    }

    public Cursor swapCursor(Cursor arg2) {
        this.findColumns(arg2, this.mOriginalFrom);
        return super.swapCursor(arg2);
    }
}

