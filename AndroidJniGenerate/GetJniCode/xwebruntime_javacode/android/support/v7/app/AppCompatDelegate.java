package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class AppCompatDelegate {
    @Retention(value=RetentionPolicy.SOURCE) @interface ApplyableNightMode {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface NightMode {
    }

    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
    public static final int MODE_NIGHT_NO = 1;
    static final int MODE_NIGHT_UNSPECIFIED = -100;
    public static final int MODE_NIGHT_YES = 2;
    static final String TAG = "AppCompatDelegate";
    private static boolean sCompatVectorFromResourcesEnabled = false;
    private static int sDefaultNightMode = -1;

    static {
    }

    AppCompatDelegate() {
        super();
    }

    public abstract void addContentView(View arg1, ViewGroup$LayoutParams arg2);

    public abstract boolean applyDayNight();

    public static AppCompatDelegate create(Activity arg1, AppCompatCallback arg2) {
        return AppCompatDelegate.create(((Context)arg1), arg1.getWindow(), arg2);
    }

    private static AppCompatDelegate create(Context arg2, Window arg3, AppCompatCallback arg4) {
        if(Build$VERSION.SDK_INT >= 24) {
            return new AppCompatDelegateImplN(arg2, arg3, arg4);
        }

        if(Build$VERSION.SDK_INT >= 23) {
            return new AppCompatDelegateImplV23(arg2, arg3, arg4);
        }

        if(Build$VERSION.SDK_INT >= 14) {
            return new AppCompatDelegateImplV14(arg2, arg3, arg4);
        }

        if(Build$VERSION.SDK_INT >= 11) {
            return new AppCompatDelegateImplV11(arg2, arg3, arg4);
        }

        return new AppCompatDelegateImplV9(arg2, arg3, arg4);
    }

    public static AppCompatDelegate create(Dialog arg1, AppCompatCallback arg2) {
        return AppCompatDelegate.create(arg1.getContext(), arg1.getWindow(), arg2);
    }

    public abstract View createView(@Nullable View arg1, String arg2, @NonNull Context arg3, @NonNull AttributeSet arg4);

    @Nullable public abstract View findViewById(@IdRes int arg1);

    public static int getDefaultNightMode() {
        return AppCompatDelegate.sDefaultNightMode;
    }

    @Nullable public abstract Delegate getDrawerToggleDelegate();

    public abstract MenuInflater getMenuInflater();

    @Nullable public abstract ActionBar getSupportActionBar();

    public abstract boolean hasWindowFeature(int arg1);

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public static boolean isCompatVectorFromResourcesEnabled() {
        return AppCompatDelegate.sCompatVectorFromResourcesEnabled;
    }

    public abstract boolean isHandleNativeActionModesEnabled();

    public abstract void onConfigurationChanged(Configuration arg1);

    public abstract void onCreate(Bundle arg1);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle arg1);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle arg1);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int arg1);

    public static void setCompatVectorFromResourcesEnabled(boolean arg0) {
        AppCompatDelegate.sCompatVectorFromResourcesEnabled = arg0;
    }

    public abstract void setContentView(@LayoutRes int arg1);

    public abstract void setContentView(View arg1);

    public abstract void setContentView(View arg1, ViewGroup$LayoutParams arg2);

    public static void setDefaultNightMode(int arg1) {
        switch(arg1) {
            case -1: 
            case 0: 
            case 1: 
            case 2: {
                AppCompatDelegate.sDefaultNightMode = arg1;
                break;
            }
            default: {
                Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
                break;
            }
        }
    }

    public abstract void setHandleNativeActionModesEnabled(boolean arg1);

    public abstract void setLocalNightMode(int arg1);

    public abstract void setSupportActionBar(@Nullable Toolbar arg1);

    public abstract void setTitle(@Nullable CharSequence arg1);

    @Nullable public abstract ActionMode startSupportActionMode(@NonNull Callback arg1);
}

