package android.support.v4.database;

import android.text.TextUtils;

public final class DatabaseUtilsCompat {
    private DatabaseUtilsCompat() {
        super();
    }

    public static String[] appendSelectionArgs(String[] arg3, String[] arg4) {
        if(arg3 != null) {
            if(arg3.length == 0) {
            }
            else {
                String[] v0 = new String[arg3.length + arg4.length];
                System.arraycopy(arg3, 0, v0, 0, arg3.length);
                System.arraycopy(arg4, 0, v0, arg3.length, arg4.length);
                return v0;
            }
        }

        return arg4;
    }

    public static String concatenateWhere(String arg2, String arg3) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            return arg3;
        }

        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            return arg2;
        }

        return "(" + arg2 + ") AND (" + arg3 + ")";
    }
}

