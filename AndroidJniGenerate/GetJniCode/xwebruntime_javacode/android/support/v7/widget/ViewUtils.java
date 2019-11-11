package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ViewUtils {
    private static final String TAG = "ViewUtils";
    private static Method sComputeFitSystemWindowsMethod;

    static {
        if(Build$VERSION.SDK_INT >= 18) {
            try {
                ViewUtils.sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                if(ViewUtils.sComputeFitSystemWindowsMethod.isAccessible()) {
                    return;
                }

                ViewUtils.sComputeFitSystemWindowsMethod.setAccessible(true);
            }
            catch(NoSuchMethodException ) {
                Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    private ViewUtils() {
        super();
    }

    public static void computeFitSystemWindows(View arg3, Rect arg4, Rect arg5) {
        if(ViewUtils.sComputeFitSystemWindowsMethod != null) {
            try {
                ViewUtils.sComputeFitSystemWindowsMethod.invoke(arg3, arg4, arg5);
            }
            catch(Exception v3) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", ((Throwable)v3));
            }
        }
    }

    public static boolean isLayoutRtl(View arg1) {
        boolean v0 = true;
        if(ViewCompat.getLayoutDirection(arg1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static void makeOptionalFitsSystemWindows(View arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            try {
                Method v0 = arg4.getClass().getMethod("makeOptionalFitsSystemWindows");
                if(!v0.isAccessible()) {
                    v0.setAccessible(true);
                }

                v0.invoke(arg4);
            }
            catch(IllegalAccessException v4) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", ((Throwable)v4));
            }
            catch(InvocationTargetException v4_1) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", ((Throwable)v4_1));
            }
            catch(NoSuchMethodException ) {
                Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            }
        }
    }
}

