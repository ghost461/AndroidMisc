package android.support.v4.view;

import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public final class ViewGroupCompat {
    @RequiresApi(value=18) class ViewGroupCompatApi18Impl extends ViewGroupCompatBaseImpl {
        ViewGroupCompatApi18Impl() {
            super();
        }

        public int getLayoutMode(ViewGroup arg1) {
            return arg1.getLayoutMode();
        }

        public void setLayoutMode(ViewGroup arg1, int arg2) {
            arg1.setLayoutMode(arg2);
        }
    }

    @RequiresApi(value=21) class ViewGroupCompatApi21Impl extends ViewGroupCompatApi18Impl {
        ViewGroupCompatApi21Impl() {
            super();
        }

        public int getNestedScrollAxes(ViewGroup arg1) {
            return arg1.getNestedScrollAxes();
        }

        public boolean isTransitionGroup(ViewGroup arg1) {
            return arg1.isTransitionGroup();
        }

        public void setTransitionGroup(ViewGroup arg1, boolean arg2) {
            arg1.setTransitionGroup(arg2);
        }
    }

    class ViewGroupCompatBaseImpl {
        ViewGroupCompatBaseImpl() {
            super();
        }

        public int getLayoutMode(ViewGroup arg1) {
            return 0;
        }

        public int getNestedScrollAxes(ViewGroup arg2) {
            if((arg2 instanceof NestedScrollingParent)) {
                return ((NestedScrollingParent)arg2).getNestedScrollAxes();
            }

            return 0;
        }

        public boolean isTransitionGroup(ViewGroup arg1) {
            return 0;
        }

        public void setLayoutMode(ViewGroup arg1, int arg2) {
        }

        public void setTransitionGroup(ViewGroup arg1, boolean arg2) {
        }
    }

    static final ViewGroupCompatBaseImpl IMPL = null;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    static {
        if(Build$VERSION.SDK_INT >= 21) {
            ViewGroupCompat.IMPL = new ViewGroupCompatApi21Impl();
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            ViewGroupCompat.IMPL = new ViewGroupCompatApi18Impl();
        }
        else {
            ViewGroupCompat.IMPL = new ViewGroupCompatBaseImpl();
        }
    }

    private ViewGroupCompat() {
        super();
    }

    public static int getLayoutMode(ViewGroup arg1) {
        return ViewGroupCompat.IMPL.getLayoutMode(arg1);
    }

    public static int getNestedScrollAxes(@NonNull ViewGroup arg1) {
        return ViewGroupCompat.IMPL.getNestedScrollAxes(arg1);
    }

    public static boolean isTransitionGroup(ViewGroup arg1) {
        return ViewGroupCompat.IMPL.isTransitionGroup(arg1);
    }

    @Deprecated public static boolean onRequestSendAccessibilityEvent(ViewGroup arg0, View arg1, AccessibilityEvent arg2) {
        return arg0.onRequestSendAccessibilityEvent(arg1, arg2);
    }

    public static void setLayoutMode(ViewGroup arg1, int arg2) {
        ViewGroupCompat.IMPL.setLayoutMode(arg1, arg2);
    }

    @Deprecated public static void setMotionEventSplittingEnabled(ViewGroup arg0, boolean arg1) {
        arg0.setMotionEventSplittingEnabled(arg1);
    }

    public static void setTransitionGroup(ViewGroup arg1, boolean arg2) {
        ViewGroupCompat.IMPL.setTransitionGroup(arg1, arg2);
    }
}

