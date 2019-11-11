package android.support.v7.view;

import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window$Callback;
import android.view.WindowManager$LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class WindowCallbackWrapper implements Window$Callback {
    final Window$Callback mWrapped;

    public WindowCallbackWrapper(Window$Callback arg2) {
        super();
        if(arg2 == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }

        this.mWrapped = arg2;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent arg2) {
        return this.mWrapped.dispatchGenericMotionEvent(arg2);
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        return this.mWrapped.dispatchKeyEvent(arg2);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent arg2) {
        return this.mWrapped.dispatchKeyShortcutEvent(arg2);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg2) {
        return this.mWrapped.dispatchPopulateAccessibilityEvent(arg2);
    }

    public boolean dispatchTouchEvent(MotionEvent arg2) {
        return this.mWrapped.dispatchTouchEvent(arg2);
    }

    public boolean dispatchTrackballEvent(MotionEvent arg2) {
        return this.mWrapped.dispatchTrackballEvent(arg2);
    }

    public void onActionModeFinished(ActionMode arg2) {
        this.mWrapped.onActionModeFinished(arg2);
    }

    public void onActionModeStarted(ActionMode arg2) {
        this.mWrapped.onActionModeStarted(arg2);
    }

    public void onAttachedToWindow() {
        this.mWrapped.onAttachedToWindow();
    }

    public void onContentChanged() {
        this.mWrapped.onContentChanged();
    }

    public boolean onCreatePanelMenu(int arg2, Menu arg3) {
        return this.mWrapped.onCreatePanelMenu(arg2, arg3);
    }

    public View onCreatePanelView(int arg2) {
        return this.mWrapped.onCreatePanelView(arg2);
    }

    public void onDetachedFromWindow() {
        this.mWrapped.onDetachedFromWindow();
    }

    public boolean onMenuItemSelected(int arg2, MenuItem arg3) {
        return this.mWrapped.onMenuItemSelected(arg2, arg3);
    }

    public boolean onMenuOpened(int arg2, Menu arg3) {
        return this.mWrapped.onMenuOpened(arg2, arg3);
    }

    public void onPanelClosed(int arg2, Menu arg3) {
        this.mWrapped.onPanelClosed(arg2, arg3);
    }

    @RequiresApi(value=26) public void onPointerCaptureChanged(boolean arg2) {
        this.mWrapped.onPointerCaptureChanged(arg2);
    }

    public boolean onPreparePanel(int arg2, View arg3, Menu arg4) {
        return this.mWrapped.onPreparePanel(arg2, arg3, arg4);
    }

    @RequiresApi(value=24) public void onProvideKeyboardShortcuts(List arg2, Menu arg3, int arg4) {
        this.mWrapped.onProvideKeyboardShortcuts(arg2, arg3, arg4);
    }

    public boolean onSearchRequested() {
        return this.mWrapped.onSearchRequested();
    }

    @RequiresApi(value=23) public boolean onSearchRequested(SearchEvent arg2) {
        return this.mWrapped.onSearchRequested(arg2);
    }

    public void onWindowAttributesChanged(WindowManager$LayoutParams arg2) {
        this.mWrapped.onWindowAttributesChanged(arg2);
    }

    public void onWindowFocusChanged(boolean arg2) {
        this.mWrapped.onWindowFocusChanged(arg2);
    }

    public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2) {
        return this.mWrapped.onWindowStartingActionMode(arg2);
    }

    @RequiresApi(value=23) public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2, int arg3) {
        return this.mWrapped.onWindowStartingActionMode(arg2, arg3);
    }
}

