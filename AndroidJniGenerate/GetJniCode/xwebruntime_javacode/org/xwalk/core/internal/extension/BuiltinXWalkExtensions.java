package org.xwalk.core.internal.extension;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.extension.api.launchscreen.LaunchScreenExtension;
import org.xwalk.core.internal.extension.api.wifidirect.WifiDirect;

public class BuiltinXWalkExtensions {
    private static final String TAG = "BuiltinXWalkExtension";
    private static HashMap sBuiltinExtensions;

    static {
        BuiltinXWalkExtensions.sBuiltinExtensions = new HashMap();
    }

    public BuiltinXWalkExtensions() {
        super();
    }

    private static String getExtensionJSFileContent(Context arg4, String arg5, boolean arg6) throws IOException {
        InputStream v0 = null;
        if(!arg6) {
            goto label_30;
        }

        try {
            Resources v6 = arg4.getResources();
            String v1 = new File(arg5).getName().split("\\.")[0];
            int v2 = v6.getIdentifier(v1, "raw", arg4.getPackageName());
            if(v2 > 0) {
                try {
                    v0 = v6.openRawResource(v2);
                }
                catch(Resources$NotFoundException ) {
                    Log.w("BuiltinXWalkExtension", "Inputstream failed to open for R.raw." + v1 + ", try to find it in assets");
                }
            }

        label_30:
            if(v0 == null) {
                v0 = arg4.getAssets().open(arg5);
            }

            byte[] v4_1 = new byte[v0.available()];
            v0.read(v4_1);
            arg5 = new String(v4_1);
            if(v0 == null) {
                return arg5;
            }

            goto label_40;
        label_29:
        }
        catch(Throwable v4) {
            goto label_29;
        }

        if(v0 != null) {
            v0.close();
        }

        throw v4;
    label_40:
        v0.close();
        return arg5;
    }

    public static void load(Context arg6) {
        try {
            BuiltinXWalkExtensions.sBuiltinExtensions.put("jsapi/launch_screen_api.js", new LaunchScreenExtension(BuiltinXWalkExtensions.getExtensionJSFileContent(arg6, "jsapi/launch_screen_api.js", true), arg6.getApplicationContext()));
        }
        catch(IOException ) {
            Log.w("BuiltinXWalkExtension", "Failed to read JS API file: jsapi/launch_screen_api.js");
        }

        if((arg6 instanceof Activity)) {
            try {
                BuiltinXWalkExtensions.sBuiltinExtensions.put("jsapi/wifidirect_api.js", new WifiDirect(BuiltinXWalkExtensions.getExtensionJSFileContent(arg6, "jsapi/wifidirect_api.js", true), ((Activity)arg6)));
            }
            catch(IOException ) {
                Log.w("BuiltinXWalkExtension", "Failed to read JS API file: jsapi/wifidirect_api.js");
            }
        }
    }
}

