package android.support.v7.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.SupportActionModeWrapper$CallbackWrapper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Window$Callback;
import android.view.Window;

@RequiresApi(value=14) class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11 {
    class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase {
        AppCompatWindowCallbackV14(AppCompatDelegateImplV14 arg1, Window$Callback arg2) {
            AppCompatDelegateImplV14.this = arg1;
            super(((AppCompatDelegateImplBase)arg1), arg2);
        }

        public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2) {
            if(AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
                return this.startAsSupportActionMode(arg2);
            }

            return super.onWindowStartingActionMode(arg2);
        }

        final ActionMode startAsSupportActionMode(ActionMode$Callback arg3) {
            CallbackWrapper v0 = new CallbackWrapper(AppCompatDelegateImplV14.this.mContext, arg3);
            android.support.v7.view.ActionMode v3 = AppCompatDelegateImplV14.this.startSupportActionMode(((Callback)v0));
            if(v3 != null) {
                return v0.getActionModeWrapper(v3);
            }

            return null;
        }
    }

    @VisibleForTesting final class AutoNightModeManager {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;

        AutoNightModeManager(@NonNull AppCompatDelegateImplV14 arg1, TwilightManager arg2) {
            AppCompatDelegateImplV14.this = arg1;
            super();
            this.mTwilightManager = arg2;
            this.mIsNight = arg2.isNight();
        }

        final void cleanup() {
            if(this.mAutoTimeChangeReceiver != null) {
                AppCompatDelegateImplV14.this.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }

        final void dispatchTimeChanged() {
            boolean v0 = this.mTwilightManager.isNight();
            if(v0 != this.mIsNight) {
                this.mIsNight = v0;
                AppCompatDelegateImplV14.this.applyDayNight();
            }
        }

        final int getApplyableNightMode() {
            this.mIsNight = this.mTwilightManager.isNight();
            int v0 = this.mIsNight ? 2 : 1;
            return v0;
        }

        final void setup() {
            this.cleanup();
            if(this.mAutoTimeChangeReceiver == null) {
                this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
                    public void onReceive(Context arg1, Intent arg2) {
                        this.this$1.dispatchTimeChanged();
                    }
                };
            }

            if(this.mAutoTimeChangeReceiverFilter == null) {
                this.mAutoTimeChangeReceiverFilter = new IntentFilter();
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }

            AppCompatDelegateImplV14.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }
    }

    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;

    AppCompatDelegateImplV14(Context arg1, Window arg2, AppCompatCallback arg3) {
        super(arg1, arg2, arg3);
        this.mLocalNightMode = -100;
        this.mHandleNativeActionModes = true;
    }

    public boolean applyDayNight() {
        int v0 = this.getNightMode();
        int v1 = this.mapNightMode(v0);
        boolean v1_1 = v1 != -1 ? this.updateForNightMode(v1) : false;
        if(v0 == 0) {
            this.ensureAutoNightModeManager();
            this.mAutoNightModeManager.setup();
        }

        this.mApplyDayNightCalled = true;
        return v1_1;
    }

    private void ensureAutoNightModeManager() {
        if(this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AutoNightModeManager(this, TwilightManager.getInstance(this.mContext));
        }
    }

    @VisibleForTesting final AutoNightModeManager getAutoNightModeManager() {
        this.ensureAutoNightModeManager();
        return this.mAutoNightModeManager;
    }

    private int getNightMode() {
        int v0 = this.mLocalNightMode != -100 ? this.mLocalNightMode : AppCompatDelegateImplV14.getDefaultNightMode();
        return v0;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    int mapNightMode(int arg2) {
        if(arg2 != -100) {
            if(arg2 != 0) {
                return arg2;
            }

            this.ensureAutoNightModeManager();
            return this.mAutoNightModeManager.getApplyableNightMode();
        }

        return -1;
    }

    public void onCreate(Bundle arg3) {
        super.onCreate(arg3);
        if(arg3 != null) {
            int v1 = -100;
            if(this.mLocalNightMode == v1) {
                this.mLocalNightMode = arg3.getInt("appcompat:local_night_mode", v1);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if(this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }

    public void onSaveInstanceState(Bundle arg3) {
        super.onSaveInstanceState(arg3);
        if(this.mLocalNightMode != -100) {
            arg3.putInt("appcompat:local_night_mode", this.mLocalNightMode);
        }
    }

    public void onStart() {
        super.onStart();
        this.applyDayNight();
    }

    public void onStop() {
        super.onStop();
        if(this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }

    public void setHandleNativeActionModesEnabled(boolean arg1) {
        this.mHandleNativeActionModes = arg1;
    }

    public void setLocalNightMode(int arg2) {
        switch(arg2) {
            case -1: 
            case 0: 
            case 1: 
            case 2: {
                if(this.mLocalNightMode != arg2) {
                    this.mLocalNightMode = arg2;
                    if(this.mApplyDayNightCalled) {
                        this.applyDayNight();
                    }
                }

                break;
            }
            default: {
                Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                break;
            }
        }
    }

    private boolean shouldRecreateOnNightModeChange() {
        boolean v1 = false;
        if((this.mApplyDayNightCalled) && ((this.mContext instanceof Activity))) {
            PackageManager v0 = this.mContext.getPackageManager();
            try {
                if((v0.getActivityInfo(new ComponentName(this.mContext, this.mContext.getClass()), 0).configChanges & 0x200) != 0) {
                    return v1;
                }
            }
            catch(PackageManager$NameNotFoundException v0_1) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", ((Throwable)v0_1));
                return 1;
            }

            return true;
        }

        return 0;
    }

    private boolean updateForNightMode(int arg5) {
        Resources v0 = this.mContext.getResources();
        Configuration v1 = v0.getConfiguration();
        int v2 = v1.uiMode & 0x30;
        arg5 = arg5 == 2 ? 0x20 : 16;
        if(v2 != arg5) {
            if(this.shouldRecreateOnNightModeChange()) {
                this.mContext.recreate();
            }
            else {
                Configuration v2_1 = new Configuration(v1);
                DisplayMetrics v1_1 = v0.getDisplayMetrics();
                v2_1.uiMode = arg5 | v2_1.uiMode & -49;
                v0.updateConfiguration(v2_1, v1_1);
                if(Build$VERSION.SDK_INT < 26) {
                    ResourcesFlusher.flush(v0);
                }
            }

            return 1;
        }

        return 0;
    }

    Window$Callback wrapWindowCallback(Window$Callback arg2) {
        return new AppCompatWindowCallbackV14(this, arg2);
    }
}

