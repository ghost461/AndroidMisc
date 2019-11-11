package android.support.v4.view;

import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class ViewParentCompat {
    @RequiresApi(value=19) class ViewParentCompatApi19Impl extends ViewParentCompatBaseImpl {
        ViewParentCompatApi19Impl() {
            super();
        }

        public void notifySubtreeAccessibilityStateChanged(ViewParent arg1, View arg2, View arg3, int arg4) {
            arg1.notifySubtreeAccessibilityStateChanged(arg2, arg3, arg4);
        }
    }

    @RequiresApi(value=21) class ViewParentCompatApi21Impl extends ViewParentCompatApi19Impl {
        ViewParentCompatApi21Impl() {
            super();
        }

        public boolean onNestedFling(ViewParent arg1, View arg2, float arg3, float arg4, boolean arg5) {
            try {
                return arg1.onNestedFling(arg2, arg3, arg4, arg5);
            }
            catch(AbstractMethodError v2) {
                Log.e("ViewParentCompat", "ViewParent " + arg1 + " does not implement interface " + "method onNestedFling", ((Throwable)v2));
                return 0;
            }
        }

        public boolean onNestedPreFling(ViewParent arg2, View arg3, float arg4, float arg5) {
            try {
                return arg2.onNestedPreFling(arg3, arg4, arg5);
            }
            catch(AbstractMethodError v3) {
                Log.e("ViewParentCompat", "ViewParent " + arg2 + " does not implement interface " + "method onNestedPreFling", ((Throwable)v3));
                return 0;
            }
        }

        public void onNestedPreScroll(ViewParent arg1, View arg2, int arg3, int arg4, int[] arg5) {
            try {
                arg1.onNestedPreScroll(arg2, arg3, arg4, arg5);
            }
            catch(AbstractMethodError v2) {
                Log.e("ViewParentCompat", "ViewParent " + arg1 + " does not implement interface " + "method onNestedPreScroll", ((Throwable)v2));
            }
        }

        public void onNestedScroll(ViewParent arg1, View arg2, int arg3, int arg4, int arg5, int arg6) {
            try {
                arg1.onNestedScroll(arg2, arg3, arg4, arg5, arg6);
            }
            catch(AbstractMethodError v2) {
                Log.e("ViewParentCompat", "ViewParent " + arg1 + " does not implement interface " + "method onNestedScroll", ((Throwable)v2));
            }
        }

        public void onNestedScrollAccepted(ViewParent arg2, View arg3, View arg4, int arg5) {
            try {
                arg2.onNestedScrollAccepted(arg3, arg4, arg5);
            }
            catch(AbstractMethodError v3) {
                Log.e("ViewParentCompat", "ViewParent " + arg2 + " does not implement interface " + "method onNestedScrollAccepted", ((Throwable)v3));
            }
        }

        public boolean onStartNestedScroll(ViewParent arg2, View arg3, View arg4, int arg5) {
            try {
                return arg2.onStartNestedScroll(arg3, arg4, arg5);
            }
            catch(AbstractMethodError v3) {
                Log.e("ViewParentCompat", "ViewParent " + arg2 + " does not implement interface " + "method onStartNestedScroll", ((Throwable)v3));
                return 0;
            }
        }

        public void onStopNestedScroll(ViewParent arg4, View arg5) {
            try {
                arg4.onStopNestedScroll(arg5);
            }
            catch(AbstractMethodError v5) {
                Log.e("ViewParentCompat", "ViewParent " + arg4 + " does not implement interface " + "method onStopNestedScroll", ((Throwable)v5));
            }
        }
    }

    class ViewParentCompatBaseImpl {
        ViewParentCompatBaseImpl() {
            super();
        }

        public void notifySubtreeAccessibilityStateChanged(ViewParent arg1, View arg2, View arg3, int arg4) {
        }

        public boolean onNestedFling(ViewParent arg2, View arg3, float arg4, float arg5, boolean arg6) {
            if((arg2 instanceof NestedScrollingParent)) {
                return ((NestedScrollingParent)arg2).onNestedFling(arg3, arg4, arg5, arg6);
            }

            return 0;
        }

        public boolean onNestedPreFling(ViewParent arg2, View arg3, float arg4, float arg5) {
            if((arg2 instanceof NestedScrollingParent)) {
                return ((NestedScrollingParent)arg2).onNestedPreFling(arg3, arg4, arg5);
            }

            return 0;
        }

        public void onNestedPreScroll(ViewParent arg2, View arg3, int arg4, int arg5, int[] arg6) {
            if((arg2 instanceof NestedScrollingParent)) {
                ((NestedScrollingParent)arg2).onNestedPreScroll(arg3, arg4, arg5, arg6);
            }
        }

        public void onNestedScroll(ViewParent arg8, View arg9, int arg10, int arg11, int arg12, int arg13) {
            if((arg8 instanceof NestedScrollingParent)) {
                arg8.onNestedScroll(arg9, arg10, arg11, arg12, arg13);
            }
        }

        public void onNestedScrollAccepted(ViewParent arg2, View arg3, View arg4, int arg5) {
            if((arg2 instanceof NestedScrollingParent)) {
                ((NestedScrollingParent)arg2).onNestedScrollAccepted(arg3, arg4, arg5);
            }
        }

        public boolean onStartNestedScroll(ViewParent arg2, View arg3, View arg4, int arg5) {
            if((arg2 instanceof NestedScrollingParent)) {
                return ((NestedScrollingParent)arg2).onStartNestedScroll(arg3, arg4, arg5);
            }

            return 0;
        }

        public void onStopNestedScroll(ViewParent arg2, View arg3) {
            if((arg2 instanceof NestedScrollingParent)) {
                ((NestedScrollingParent)arg2).onStopNestedScroll(arg3);
            }
        }
    }

    static final ViewParentCompatBaseImpl IMPL = null;
    private static final String TAG = "ViewParentCompat";

    static {
        if(Build$VERSION.SDK_INT >= 21) {
            ViewParentCompat.IMPL = new ViewParentCompatApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            ViewParentCompat.IMPL = new ViewParentCompatApi19Impl();
        }
        else {
            ViewParentCompat.IMPL = new ViewParentCompatBaseImpl();
        }
    }

    private ViewParentCompat() {
        super();
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent arg1, View arg2, View arg3, int arg4) {
        ViewParentCompat.IMPL.notifySubtreeAccessibilityStateChanged(arg1, arg2, arg3, arg4);
    }

    public static boolean onNestedFling(ViewParent arg6, View arg7, float arg8, float arg9, boolean arg10) {
        return ViewParentCompat.IMPL.onNestedFling(arg6, arg7, arg8, arg9, arg10);
    }

    public static boolean onNestedPreFling(ViewParent arg1, View arg2, float arg3, float arg4) {
        return ViewParentCompat.IMPL.onNestedPreFling(arg1, arg2, arg3, arg4);
    }

    public static void onNestedPreScroll(ViewParent arg8, View arg9, int arg10, int arg11, int[] arg12, int arg13) {
        if((arg8 instanceof NestedScrollingParent2)) {
            arg8.onNestedPreScroll(arg9, arg10, arg11, arg12, arg13);
        }
        else if(arg13 == 0) {
            ViewParentCompat.IMPL.onNestedPreScroll(arg8, arg9, arg10, arg11, arg12);
        }
    }

    public static void onNestedPreScroll(ViewParent arg6, View arg7, int arg8, int arg9, int[] arg10) {
        ViewParentCompat.onNestedPreScroll(arg6, arg7, arg8, arg9, arg10, 0);
    }

    public static void onNestedScroll(ViewParent arg9, View arg10, int arg11, int arg12, int arg13, int arg14, int arg15) {
        if((arg9 instanceof NestedScrollingParent2)) {
            arg9.onNestedScroll(arg10, arg11, arg12, arg13, arg14, arg15);
        }
        else if(arg15 == 0) {
            ViewParentCompat.IMPL.onNestedScroll(arg9, arg10, arg11, arg12, arg13, arg14);
        }
    }

    public static void onNestedScroll(ViewParent arg7, View arg8, int arg9, int arg10, int arg11, int arg12) {
        ViewParentCompat.onNestedScroll(arg7, arg8, arg9, arg10, arg11, arg12, 0);
    }

    public static void onNestedScrollAccepted(ViewParent arg1, View arg2, View arg3, int arg4, int arg5) {
        if((arg1 instanceof NestedScrollingParent2)) {
            ((NestedScrollingParent2)arg1).onNestedScrollAccepted(arg2, arg3, arg4, arg5);
        }
        else if(arg5 == 0) {
            ViewParentCompat.IMPL.onNestedScrollAccepted(arg1, arg2, arg3, arg4);
        }
    }

    public static void onNestedScrollAccepted(ViewParent arg1, View arg2, View arg3, int arg4) {
        ViewParentCompat.onNestedScrollAccepted(arg1, arg2, arg3, arg4, 0);
    }

    public static boolean onStartNestedScroll(ViewParent arg1, View arg2, View arg3, int arg4, int arg5) {
        if((arg1 instanceof NestedScrollingParent2)) {
            return ((NestedScrollingParent2)arg1).onStartNestedScroll(arg2, arg3, arg4, arg5);
        }

        if(arg5 == 0) {
            return ViewParentCompat.IMPL.onStartNestedScroll(arg1, arg2, arg3, arg4);
        }

        return 0;
    }

    public static boolean onStartNestedScroll(ViewParent arg1, View arg2, View arg3, int arg4) {
        return ViewParentCompat.onStartNestedScroll(arg1, arg2, arg3, arg4, 0);
    }

    public static void onStopNestedScroll(ViewParent arg1, View arg2, int arg3) {
        if((arg1 instanceof NestedScrollingParent2)) {
            ((NestedScrollingParent2)arg1).onStopNestedScroll(arg2, arg3);
        }
        else if(arg3 == 0) {
            ViewParentCompat.IMPL.onStopNestedScroll(arg1, arg2);
        }
    }

    public static void onStopNestedScroll(ViewParent arg1, View arg2) {
        ViewParentCompat.onStopNestedScroll(arg1, arg2, 0);
    }

    @Deprecated public static boolean requestSendAccessibilityEvent(ViewParent arg0, View arg1, AccessibilityEvent arg2) {
        return arg0.requestSendAccessibilityEvent(arg1, arg2);
    }
}

