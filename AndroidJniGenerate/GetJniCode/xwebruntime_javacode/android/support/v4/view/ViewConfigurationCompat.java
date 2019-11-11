package android.support.v4.view;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

@Deprecated public final class ViewConfigurationCompat {
    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod;

    static {
        if(Build$VERSION.SDK_INT == 25) {
            try {
                ViewConfigurationCompat.sGetScaledScrollFactorMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor");
            }
            catch(Exception ) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    private ViewConfigurationCompat() {
        super();
    }

    private static float getLegacyScrollFactor(ViewConfiguration arg3, Context arg4) {
        if(Build$VERSION.SDK_INT >= 25 && ViewConfigurationCompat.sGetScaledScrollFactorMethod != null) {
            try {
                return ((float)ViewConfigurationCompat.sGetScaledScrollFactorMethod.invoke(arg3).intValue());
            }
            catch(Exception ) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }

        TypedValue v3 = new TypedValue();
        if(arg4.getTheme().resolveAttribute(0x101004D, v3, true)) {
            return v3.getDimension(arg4.getResources().getDisplayMetrics());
        }

        return 0;
    }

    public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration arg2, @NonNull Context arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getScaledHorizontalScrollFactor();
        }

        return ViewConfigurationCompat.getLegacyScrollFactor(arg2, arg3);
    }

    @Deprecated public static int getScaledPagingTouchSlop(ViewConfiguration arg0) {
        return arg0.getScaledPagingTouchSlop();
    }

    public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration arg2, @NonNull Context arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getScaledVerticalScrollFactor();
        }

        return ViewConfigurationCompat.getLegacyScrollFactor(arg2, arg3);
    }

    @Deprecated public static boolean hasPermanentMenuKey(ViewConfiguration arg0) {
        return arg0.hasPermanentMenuKey();
    }
}

