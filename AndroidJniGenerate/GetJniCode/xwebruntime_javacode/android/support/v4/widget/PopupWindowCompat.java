package android.support.v4.widget;

import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
    @RequiresApi(value=19) class PopupWindowCompatApi19Impl extends PopupWindowCompatBaseImpl {
        PopupWindowCompatApi19Impl() {
            super();
        }

        public void showAsDropDown(PopupWindow arg1, View arg2, int arg3, int arg4, int arg5) {
            arg1.showAsDropDown(arg2, arg3, arg4, arg5);
        }
    }

    @RequiresApi(value=21) class PopupWindowCompatApi21Impl extends PopupWindowCompatApi19Impl {
        private static final String TAG = "PopupWindowCompatApi21";
        private static Field sOverlapAnchorField;

        static {
            try {
                PopupWindowCompatApi21Impl.sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                PopupWindowCompatApi21Impl.sOverlapAnchorField.setAccessible(true);
            }
            catch(NoSuchFieldException v0) {
                Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", ((Throwable)v0));
            }
        }

        PopupWindowCompatApi21Impl() {
            super();
        }

        public boolean getOverlapAnchor(PopupWindow arg3) {
            if(PopupWindowCompatApi21Impl.sOverlapAnchorField != null) {
                try {
                    return PopupWindowCompatApi21Impl.sOverlapAnchorField.get(arg3).booleanValue();
                }
                catch(IllegalAccessException v3) {
                    Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", ((Throwable)v3));
                }
            }

            return 0;
        }

        public void setOverlapAnchor(PopupWindow arg2, boolean arg3) {
            if(PopupWindowCompatApi21Impl.sOverlapAnchorField != null) {
                try {
                    PopupWindowCompatApi21Impl.sOverlapAnchorField.set(arg2, Boolean.valueOf(arg3));
                }
                catch(IllegalAccessException v2) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", ((Throwable)v2));
                }
            }
        }
    }

    @RequiresApi(value=23) class PopupWindowCompatApi23Impl extends PopupWindowCompatApi21Impl {
        PopupWindowCompatApi23Impl() {
            super();
        }

        public boolean getOverlapAnchor(PopupWindow arg1) {
            return arg1.getOverlapAnchor();
        }

        public int getWindowLayoutType(PopupWindow arg1) {
            return arg1.getWindowLayoutType();
        }

        public void setOverlapAnchor(PopupWindow arg1, boolean arg2) {
            arg1.setOverlapAnchor(arg2);
        }

        public void setWindowLayoutType(PopupWindow arg1, int arg2) {
            arg1.setWindowLayoutType(arg2);
        }
    }

    class PopupWindowCompatBaseImpl {
        private static Method sGetWindowLayoutTypeMethod;
        private static boolean sGetWindowLayoutTypeMethodAttempted;
        private static Method sSetWindowLayoutTypeMethod;
        private static boolean sSetWindowLayoutTypeMethodAttempted;

        PopupWindowCompatBaseImpl() {
            super();
        }

        public boolean getOverlapAnchor(PopupWindow arg1) {
            return 0;
        }

        public int getWindowLayoutType(PopupWindow arg6) {
            if(!PopupWindowCompatBaseImpl.sGetWindowLayoutTypeMethodAttempted) {
                try {
                    PopupWindowCompatBaseImpl.sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType");
                    PopupWindowCompatBaseImpl.sGetWindowLayoutTypeMethod.setAccessible(true);
                    goto label_11;
                }
                catch(Exception ) {
                label_11:
                    PopupWindowCompatBaseImpl.sGetWindowLayoutTypeMethodAttempted = true;
                }
            }

            if(PopupWindowCompatBaseImpl.sGetWindowLayoutTypeMethod == null) {
                return 0;
            }

            try {
                return PopupWindowCompatBaseImpl.sGetWindowLayoutTypeMethod.invoke(arg6).intValue();
            }
            catch(Exception ) {
                return 0;
            }
        }

        public void setOverlapAnchor(PopupWindow arg1, boolean arg2) {
        }

        public void setWindowLayoutType(PopupWindow arg7, int arg8) {
            if(!PopupWindowCompatBaseImpl.sSetWindowLayoutTypeMethodAttempted) {
                try {
                    PopupWindowCompatBaseImpl.sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                    PopupWindowCompatBaseImpl.sSetWindowLayoutTypeMethod.setAccessible(true);
                    goto label_13;
                }
                catch(Exception ) {
                label_13:
                    PopupWindowCompatBaseImpl.sSetWindowLayoutTypeMethodAttempted = true;
                }
            }

            if(PopupWindowCompatBaseImpl.sSetWindowLayoutTypeMethod != null) {
                try {
                    PopupWindowCompatBaseImpl.sSetWindowLayoutTypeMethod.invoke(arg7, Integer.valueOf(arg8));
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }

        public void showAsDropDown(PopupWindow arg2, View arg3, int arg4, int arg5, int arg6) {
            if((GravityCompat.getAbsoluteGravity(arg6, ViewCompat.getLayoutDirection(arg3)) & 7) == 5) {
                arg4 -= arg2.getWidth() - arg3.getWidth();
            }

            arg2.showAsDropDown(arg3, arg4, arg5);
        }
    }

    static final PopupWindowCompatBaseImpl IMPL;

    static {
        if(Build$VERSION.SDK_INT >= 23) {
            PopupWindowCompat.IMPL = new PopupWindowCompatApi23Impl();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            PopupWindowCompat.IMPL = new PopupWindowCompatApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            PopupWindowCompat.IMPL = new PopupWindowCompatApi19Impl();
        }
        else {
            PopupWindowCompat.IMPL = new PopupWindowCompatBaseImpl();
        }
    }

    private PopupWindowCompat() {
        super();
    }

    public static boolean getOverlapAnchor(PopupWindow arg1) {
        return PopupWindowCompat.IMPL.getOverlapAnchor(arg1);
    }

    public static int getWindowLayoutType(PopupWindow arg1) {
        return PopupWindowCompat.IMPL.getWindowLayoutType(arg1);
    }

    public static void setOverlapAnchor(PopupWindow arg1, boolean arg2) {
        PopupWindowCompat.IMPL.setOverlapAnchor(arg1, arg2);
    }

    public static void setWindowLayoutType(PopupWindow arg1, int arg2) {
        PopupWindowCompat.IMPL.setWindowLayoutType(arg1, arg2);
    }

    public static void showAsDropDown(PopupWindow arg6, View arg7, int arg8, int arg9, int arg10) {
        PopupWindowCompat.IMPL.showAsDropDown(arg6, arg7, arg8, arg9, arg10);
    }
}

