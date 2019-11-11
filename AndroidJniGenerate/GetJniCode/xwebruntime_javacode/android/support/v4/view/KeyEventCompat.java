package android.support.v4.view;

import android.view.KeyEvent$Callback;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.View;

@Deprecated public final class KeyEventCompat {
    private KeyEventCompat() {
        super();
    }

    @Deprecated public static boolean dispatch(KeyEvent arg0, KeyEvent$Callback arg1, Object arg2, Object arg3) {
        return arg0.dispatch(arg1, ((KeyEvent$DispatcherState)arg2), arg3);
    }

    @Deprecated public static Object getKeyDispatcherState(View arg0) {
        return arg0.getKeyDispatcherState();
    }

    @Deprecated public static boolean hasModifiers(KeyEvent arg0, int arg1) {
        return arg0.hasModifiers(arg1);
    }

    @Deprecated public static boolean hasNoModifiers(KeyEvent arg0) {
        return arg0.hasNoModifiers();
    }

    @Deprecated public static boolean isCtrlPressed(KeyEvent arg0) {
        return arg0.isCtrlPressed();
    }

    @Deprecated public static boolean isTracking(KeyEvent arg0) {
        return arg0.isTracking();
    }

    @Deprecated public static boolean metaStateHasModifiers(int arg0, int arg1) {
        return KeyEvent.metaStateHasModifiers(arg0, arg1);
    }

    @Deprecated public static boolean metaStateHasNoModifiers(int arg0) {
        return KeyEvent.metaStateHasNoModifiers(arg0);
    }

    @Deprecated public static int normalizeMetaState(int arg0) {
        return KeyEvent.normalizeMetaState(arg0);
    }

    @Deprecated public static void startTracking(KeyEvent arg0) {
        arg0.startTracking();
    }
}

