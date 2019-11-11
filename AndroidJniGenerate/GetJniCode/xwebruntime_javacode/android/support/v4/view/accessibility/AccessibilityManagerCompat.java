package android.support.v4.view.accessibility;

import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;
import android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

public final class AccessibilityManagerCompat {
    @Deprecated public interface AccessibilityStateChangeListener {
        @Deprecated void onAccessibilityStateChanged(boolean arg1);
    }

    @Deprecated public abstract class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener {
        public AccessibilityStateChangeListenerCompat() {
            super();
        }
    }

    class AccessibilityStateChangeListenerWrapper implements AccessibilityManager$AccessibilityStateChangeListener {
        AccessibilityStateChangeListener mListener;

        AccessibilityStateChangeListenerWrapper(@NonNull AccessibilityStateChangeListener arg1) {
            super();
            this.mListener = arg1;
        }

        public boolean equals(Object arg3) {
            if(this == (((AccessibilityStateChangeListenerWrapper)arg3))) {
                return 1;
            }

            if(arg3 != null) {
                if(this.getClass() != arg3.getClass()) {
                }
                else {
                    return this.mListener.equals(((AccessibilityStateChangeListenerWrapper)arg3).mListener);
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        public void onAccessibilityStateChanged(boolean arg2) {
            this.mListener.onAccessibilityStateChanged(arg2);
        }
    }

    public interface TouchExplorationStateChangeListener {
        void onTouchExplorationStateChanged(boolean arg1);
    }

    @RequiresApi(value=19) class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager$TouchExplorationStateChangeListener {
        final TouchExplorationStateChangeListener mListener;

        TouchExplorationStateChangeListenerWrapper(@NonNull TouchExplorationStateChangeListener arg1) {
            super();
            this.mListener = arg1;
        }

        public boolean equals(Object arg3) {
            if(this == (((TouchExplorationStateChangeListenerWrapper)arg3))) {
                return 1;
            }

            if(arg3 != null) {
                if(this.getClass() != arg3.getClass()) {
                }
                else {
                    return this.mListener.equals(((TouchExplorationStateChangeListenerWrapper)arg3).mListener);
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        public void onTouchExplorationStateChanged(boolean arg2) {
            this.mListener.onTouchExplorationStateChanged(arg2);
        }
    }

    private AccessibilityManagerCompat() {
        super();
    }

    @Deprecated public static boolean addAccessibilityStateChangeListener(AccessibilityManager arg1, AccessibilityStateChangeListener arg2) {
        if(arg2 == null) {
            return 0;
        }

        return arg1.addAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(arg2));
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager arg3, TouchExplorationStateChangeListener arg4) {
        if(Build$VERSION.SDK_INT >= 19) {
            if(arg4 == null) {
                return 0;
            }

            return arg3.addTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(arg4));
        }

        return 0;
    }

    @Deprecated public static List getEnabledAccessibilityServiceList(AccessibilityManager arg0, int arg1) {
        return arg0.getEnabledAccessibilityServiceList(arg1);
    }

    @Deprecated public static List getInstalledAccessibilityServiceList(AccessibilityManager arg0) {
        return arg0.getInstalledAccessibilityServiceList();
    }

    @Deprecated public static boolean isTouchExplorationEnabled(AccessibilityManager arg0) {
        return arg0.isTouchExplorationEnabled();
    }

    @Deprecated public static boolean removeAccessibilityStateChangeListener(AccessibilityManager arg1, AccessibilityStateChangeListener arg2) {
        if(arg2 == null) {
            return 0;
        }

        return arg1.removeAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(arg2));
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager arg3, TouchExplorationStateChangeListener arg4) {
        if(Build$VERSION.SDK_INT >= 19) {
            if(arg4 == null) {
                return 0;
            }

            return arg3.removeTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(arg4));
        }

        return 0;
    }
}

