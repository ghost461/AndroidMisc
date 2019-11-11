package android.support.v4.text;

import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(value=14) class ICUCompatIcs {
    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static {
        try {
            Class v0_1 = Class.forName("libcore.icu.ICU");
            if(v0_1 == null) {
                return;
            }

            ICUCompatIcs.sGetScriptMethod = v0_1.getMethod("getScript", String.class);
            ICUCompatIcs.sAddLikelySubtagsMethod = v0_1.getMethod("addLikelySubtags", String.class);
        }
        catch(Exception v0) {
            ICUCompatIcs.sGetScriptMethod = null;
            ICUCompatIcs.sAddLikelySubtagsMethod = null;
            Log.w("ICUCompatIcs", ((Throwable)v0));
        }
    }

    ICUCompatIcs() {
        super();
    }

    private static String addLikelySubtags(Locale arg3) {
        String v3 = arg3.toString();
        try {
            if(ICUCompatIcs.sAddLikelySubtagsMethod == null) {
                return v3;
            }

            return ICUCompatIcs.sAddLikelySubtagsMethod.invoke(null, v3);
        }
        catch(InvocationTargetException v0) {
            Log.w("ICUCompatIcs", ((Throwable)v0));
        }
        catch(IllegalAccessException v0_1) {
            Log.w("ICUCompatIcs", ((Throwable)v0_1));
        }

        return v3;
    }

    private static String getScript(String arg3) {
        Object v0 = null;
        try {
            if(ICUCompatIcs.sGetScriptMethod == null) {
                goto label_17;
            }

            return ICUCompatIcs.sGetScriptMethod.invoke(v0, arg3);
        }
        catch(InvocationTargetException v3) {
            Log.w("ICUCompatIcs", ((Throwable)v3));
        }
        catch(IllegalAccessException v3_1) {
            Log.w("ICUCompatIcs", ((Throwable)v3_1));
        }

    label_17:
        return ((String)v0);
    }

    public static String maximizeAndGetScript(Locale arg0) {
        String v0 = ICUCompatIcs.addLikelySubtags(arg0);
        if(v0 != null) {
            return ICUCompatIcs.getScript(v0);
        }

        return null;
    }
}

