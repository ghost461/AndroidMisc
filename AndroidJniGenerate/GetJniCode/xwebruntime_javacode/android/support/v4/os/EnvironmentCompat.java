package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    private EnvironmentCompat() {
        super();
    }

    public static String getStorageState(File arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            return Environment.getStorageState(arg3);
        }

        try {
            if(!arg3.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                return "unknown";
            }

            return Environment.getExternalStorageState();
        }
        catch(IOException v3) {
            Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + v3);
        }

        return "unknown";
    }
}

