package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Window$Callback;
import android.view.Window;

@RequiresApi(value=23) class AppCompatDelegateImplV23 extends AppCompatDelegateImplV14 {
    class AppCompatWindowCallbackV23 extends AppCompatWindowCallbackV14 {
        AppCompatWindowCallbackV23(AppCompatDelegateImplV23 arg1, Window$Callback arg2) {
            AppCompatDelegateImplV23.this = arg1;
            super(((AppCompatDelegateImplV14)arg1), arg2);
        }

        public ActionMode onWindowStartingActionMode(ActionMode$Callback arg1) {
            return null;
        }

        public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2, int arg3) {
            if(AppCompatDelegateImplV23.this.isHandleNativeActionModesEnabled()) {
                if(arg3 != 0) {
                }
                else {
                    return this.startAsSupportActionMode(arg2);
                }
            }

            return super.onWindowStartingActionMode(arg2, arg3);
        }
    }

    private final UiModeManager mUiModeManager;

    AppCompatDelegateImplV23(Context arg1, Window arg2, AppCompatCallback arg3) {
        super(arg1, arg2, arg3);
        this.mUiModeManager = arg1.getSystemService("uimode");
    }

    int mapNightMode(int arg2) {
        if(arg2 == 0 && this.mUiModeManager.getNightMode() == 0) {
            return -1;
        }

        return super.mapNightMode(arg2);
    }

    Window$Callback wrapWindowCallback(Window$Callback arg2) {
        return new AppCompatWindowCallbackV23(this, arg2);
    }
}

