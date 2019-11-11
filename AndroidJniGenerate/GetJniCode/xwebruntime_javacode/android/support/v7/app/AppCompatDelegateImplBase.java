package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R$attr;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.TintTypedArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window$Callback;
import android.view.Window;

@RequiresApi(value=14) abstract class AppCompatDelegateImplBase extends AppCompatDelegate {
    final class android.support.v7.app.AppCompatDelegateImplBase$1 implements Thread$UncaughtExceptionHandler {
        android.support.v7.app.AppCompatDelegateImplBase$1(Thread$UncaughtExceptionHandler arg1) {
            this.val$defHandler = arg1;
            super();
        }

        private boolean shouldWrapException(Throwable arg3) {
            boolean v1 = false;
            if((arg3 instanceof Resources$NotFoundException)) {
                String v3 = arg3.getMessage();
                if(v3 != null && ((v3.contains("drawable")) || (v3.contains("Drawable")))) {
                    v1 = true;
                }

                return v1;
            }

            return 0;
        }

        public void uncaughtException(Thread arg4, Throwable arg5) {
            if(this.shouldWrapException(arg5)) {
                StringBuilder v1 = new StringBuilder();
                v1.append(arg5.getMessage());
                v1.append(". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                Resources$NotFoundException v0 = new Resources$NotFoundException(v1.toString());
                ((Throwable)v0).initCause(arg5.getCause());
                ((Throwable)v0).setStackTrace(arg5.getStackTrace());
                this.val$defHandler.uncaughtException(arg4, ((Throwable)v0));
            }
            else {
                this.val$defHandler.uncaughtException(arg4, arg5);
            }
        }
    }

    class ActionBarDrawableToggleImpl implements Delegate {
        ActionBarDrawableToggleImpl(AppCompatDelegateImplBase arg1) {
            AppCompatDelegateImplBase.this = arg1;
            super();
        }

        public Context getActionBarThemedContext() {
            return AppCompatDelegateImplBase.this.getActionBarThemedContext();
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray v0 = TintTypedArray.obtainStyledAttributes(this.getActionBarThemedContext(), null, new int[]{attr.homeAsUpIndicator});
            Drawable v1 = v0.getDrawable(0);
            v0.recycle();
            return v1;
        }

        public boolean isNavigationVisible() {
            ActionBar v0 = AppCompatDelegateImplBase.this.getSupportActionBar();
            boolean v0_1 = v0 == null || (v0.getDisplayOptions() & 4) == 0 ? false : true;
            return v0_1;
        }

        public void setActionBarDescription(int arg2) {
            ActionBar v0 = AppCompatDelegateImplBase.this.getSupportActionBar();
            if(v0 != null) {
                v0.setHomeActionContentDescription(arg2);
            }
        }

        public void setActionBarUpIndicator(Drawable arg2, int arg3) {
            ActionBar v0 = AppCompatDelegateImplBase.this.getSupportActionBar();
            if(v0 != null) {
                v0.setHomeAsUpIndicator(arg2);
                v0.setHomeActionContentDescription(arg3);
            }
        }
    }

    class AppCompatWindowCallbackBase extends WindowCallbackWrapper {
        AppCompatWindowCallbackBase(AppCompatDelegateImplBase arg1, Window$Callback arg2) {
            AppCompatDelegateImplBase.this = arg1;
            super(arg2);
        }

        public boolean dispatchKeyEvent(KeyEvent arg2) {
            boolean v2 = (AppCompatDelegateImplBase.this.dispatchKeyEvent(arg2)) || (super.dispatchKeyEvent(arg2)) ? true : false;
            return v2;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent arg3) {
            boolean v3 = (super.dispatchKeyShortcutEvent(arg3)) || (AppCompatDelegateImplBase.this.onKeyShortcut(arg3.getKeyCode(), arg3)) ? true : false;
            return v3;
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int arg2, Menu arg3) {
            if(arg2 == 0 && !(arg3 instanceof MenuBuilder)) {
                return 0;
            }

            return super.onCreatePanelMenu(arg2, arg3);
        }

        public boolean onMenuOpened(int arg2, Menu arg3) {
            super.onMenuOpened(arg2, arg3);
            AppCompatDelegateImplBase.this.onMenuOpened(arg2, arg3);
            return 1;
        }

        public void onPanelClosed(int arg2, Menu arg3) {
            super.onPanelClosed(arg2, arg3);
            AppCompatDelegateImplBase.this.onPanelClosed(arg2, arg3);
        }

        public boolean onPreparePanel(int arg4, View arg5, Menu arg6) {
            Menu v0 = (arg6 instanceof MenuBuilder) ? arg6 : null;
            if(arg4 == 0 && v0 == null) {
                return 0;
            }

            if(v0 != null) {
                ((MenuBuilder)v0).setOverrideVisibleItems(true);
            }

            boolean v4 = super.onPreparePanel(arg4, arg5, arg6);
            if(v0 != null) {
                ((MenuBuilder)v0).setOverrideVisibleItems(false);
            }

            return v4;
        }
    }

    static final boolean DEBUG = false;
    static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    private static final boolean SHOULD_INSTALL_EXCEPTION_HANDLER;
    ActionBar mActionBar;
    final AppCompatCallback mAppCompatCallback;
    final Window$Callback mAppCompatWindowCallback;
    final Context mContext;
    private boolean mEatKeyUpEvent;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private boolean mIsStarted;
    MenuInflater mMenuInflater;
    final Window$Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private CharSequence mTitle;
    final Window mWindow;
    boolean mWindowNoTitle;
    private static boolean sInstalledExceptionHandler;
    private static final int[] sWindowBackgroundStyleable;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 21 ? true : false;
        AppCompatDelegateImplBase.SHOULD_INSTALL_EXCEPTION_HANDLER = v0;
        if((AppCompatDelegateImplBase.SHOULD_INSTALL_EXCEPTION_HANDLER) && !AppCompatDelegateImplBase.sInstalledExceptionHandler) {
            Thread.setDefaultUncaughtExceptionHandler(new android.support.v7.app.AppCompatDelegateImplBase$1(Thread.getDefaultUncaughtExceptionHandler()));
            AppCompatDelegateImplBase.sInstalledExceptionHandler = true;
        }

        AppCompatDelegateImplBase.sWindowBackgroundStyleable = new int[]{0x1010054};
    }

    AppCompatDelegateImplBase(Context arg1, Window arg2, AppCompatCallback arg3) {
        super();
        this.mContext = arg1;
        this.mWindow = arg2;
        this.mAppCompatCallback = arg3;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        if((this.mOriginalWindowCallback instanceof AppCompatWindowCallbackBase)) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }

        this.mAppCompatWindowCallback = this.wrapWindowCallback(this.mOriginalWindowCallback);
        this.mWindow.setCallback(this.mAppCompatWindowCallback);
        TintTypedArray v1 = TintTypedArray.obtainStyledAttributes(arg1, null, AppCompatDelegateImplBase.sWindowBackgroundStyleable);
        Drawable v2 = v1.getDrawableIfKnown(0);
        if(v2 != null) {
            this.mWindow.setBackgroundDrawable(v2);
        }

        v1.recycle();
    }

    public boolean applyDayNight() {
        return 0;
    }

    abstract boolean dispatchKeyEvent(KeyEvent arg1);

    final Context getActionBarThemedContext() {
        ActionBar v0 = this.getSupportActionBar();
        Context v0_1 = v0 != null ? v0.getThemedContext() : null;
        if(v0_1 == null) {
            v0_1 = this.mContext;
        }

        return v0_1;
    }

    public final Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl(this);
    }

    public MenuInflater getMenuInflater() {
        if(this.mMenuInflater == null) {
            this.initWindowDecorActionBar();
            Context v1 = this.mActionBar != null ? this.mActionBar.getThemedContext() : this.mContext;
            this.mMenuInflater = new SupportMenuInflater(v1);
        }

        return this.mMenuInflater;
    }

    public ActionBar getSupportActionBar() {
        this.initWindowDecorActionBar();
        return this.mActionBar;
    }

    final CharSequence getTitle() {
        if((this.mOriginalWindowCallback instanceof Activity)) {
            return this.mOriginalWindowCallback.getTitle();
        }

        return this.mTitle;
    }

    final Window$Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    abstract void initWindowDecorActionBar();

    final boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return 0;
    }

    final boolean isStarted() {
        return this.mIsStarted;
    }

    public void onDestroy() {
        this.mIsDestroyed = true;
    }

    abstract boolean onKeyShortcut(int arg1, KeyEvent arg2);

    abstract boolean onMenuOpened(int arg1, Menu arg2);

    abstract void onPanelClosed(int arg1, Menu arg2);

    public void onSaveInstanceState(Bundle arg1) {
    }

    public void onStart() {
        this.mIsStarted = true;
    }

    public void onStop() {
        this.mIsStarted = false;
    }

    abstract void onTitleChanged(CharSequence arg1);

    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    public void setHandleNativeActionModesEnabled(boolean arg1) {
    }

    public void setLocalNightMode(int arg1) {
    }

    public final void setTitle(CharSequence arg1) {
        this.mTitle = arg1;
        this.onTitleChanged(arg1);
    }

    abstract ActionMode startSupportActionModeFromWindow(Callback arg1);

    Window$Callback wrapWindowCallback(Window$Callback arg2) {
        return new AppCompatWindowCallbackBase(this, arg2);
    }
}

