package org.chromium.components.webrestrictions.browser;

import android.database.Cursor;
import org.chromium.base.annotations.CalledByNative;

public class WebRestrictionsClientResult {
    private final Cursor mCursor;

    public WebRestrictionsClientResult(Cursor arg1) {
        super();
        this.mCursor = arg1;
        if(arg1 == null) {
            return;
        }

        arg1.moveToFirst();
    }

    @CalledByNative public int getColumnCount() {
        if(this.mCursor == null) {
            return 0;
        }

        return this.mCursor.getColumnCount();
    }

    @CalledByNative public String getColumnName(int arg2) {
        if(this.mCursor == null) {
            return null;
        }

        return this.mCursor.getColumnName(arg2);
    }

    @CalledByNative public int getInt(int arg2) {
        if(this.mCursor == null) {
            return 0;
        }

        return this.mCursor.getInt(arg2);
    }

    @CalledByNative public String getString(int arg2) {
        if(this.mCursor == null) {
            return null;
        }

        return this.mCursor.getString(arg2);
    }

    @CalledByNative public boolean isString(int arg3) {
        boolean v1 = false;
        if(this.mCursor == null) {
            return 0;
        }

        if(this.mCursor.getType(arg3) == 3) {
            v1 = true;
        }

        return v1;
    }

    @CalledByNative public boolean shouldProceed() {
        boolean v1 = false;
        if(this.mCursor == null) {
            return 0;
        }

        if(this.mCursor.getInt(0) > 0) {
            v1 = true;
        }

        return v1;
    }
}

