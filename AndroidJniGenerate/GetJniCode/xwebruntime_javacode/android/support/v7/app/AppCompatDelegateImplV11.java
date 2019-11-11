package android.support.v7.app;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

@RequiresApi(value=14) class AppCompatDelegateImplV11 extends AppCompatDelegateImplV9 {
    AppCompatDelegateImplV11(Context arg1, Window arg2, AppCompatCallback arg3) {
        super(arg1, arg2, arg3);
    }

    View callActivityOnCreateView(View arg1, String arg2, Context arg3, AttributeSet arg4) {
        return null;
    }

    public boolean hasWindowFeature(int arg2) {
        boolean v2 = (super.hasWindowFeature(arg2)) || (this.mWindow.hasFeature(arg2)) ? true : false;
        return v2;
    }
}

