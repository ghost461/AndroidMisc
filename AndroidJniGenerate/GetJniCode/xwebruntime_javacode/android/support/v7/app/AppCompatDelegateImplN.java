package android.support.v7.app;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.Menu;
import android.view.Window$Callback;
import android.view.Window;
import java.util.List;

@RequiresApi(value=24) class AppCompatDelegateImplN extends AppCompatDelegateImplV23 {
    class AppCompatWindowCallbackN extends AppCompatWindowCallbackV23 {
        AppCompatWindowCallbackN(AppCompatDelegateImplN arg1, Window$Callback arg2) {
            AppCompatDelegateImplN.this = arg1;
            super(((AppCompatDelegateImplV23)arg1), arg2);
        }

        public void onProvideKeyboardShortcuts(List arg4, Menu arg5, int arg6) {
            PanelFeatureState v0 = AppCompatDelegateImplN.this.getPanelState(0, true);
            if(v0 == null || v0.menu == null) {
                super.onProvideKeyboardShortcuts(arg4, arg5, arg6);
            }
            else {
                super.onProvideKeyboardShortcuts(arg4, v0.menu, arg6);
            }
        }
    }

    AppCompatDelegateImplN(Context arg1, Window arg2, AppCompatCallback arg3) {
        super(arg1, arg2, arg3);
    }

    Window$Callback wrapWindowCallback(Window$Callback arg2) {
        return new AppCompatWindowCallbackN(this, arg2);
    }
}

