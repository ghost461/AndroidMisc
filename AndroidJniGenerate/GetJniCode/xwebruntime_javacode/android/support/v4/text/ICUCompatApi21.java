package android.support.v4.text;

import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(value=21) class ICUCompatApi21 {
    private static final String TAG = "ICUCompatApi21";
    private static Method sAddLikelySubtagsMethod;

    static {
        try {
            ICUCompatApi21.sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
            return;
        }
        catch(Exception v0) {
            throw new IllegalStateException(((Throwable)v0));
        }
    }

    ICUCompatApi21() {
        super();
    }

    public static String maximizeAndGetScript(Locale arg3) {
        try {
            return ICUCompatApi21.sAddLikelySubtagsMethod.invoke(null, arg3).getScript();
        }
        catch(IllegalAccessException v0) {
            Log.w("ICUCompatApi21", ((Throwable)v0));
        }
        catch(InvocationTargetException v0_1) {
            Log.w("ICUCompatApi21", ((Throwable)v0_1));
        }

        return arg3.getScript();
    }
}

