package android.support.v4.view;

import android.view.MenuItem;

public final class MenuCompat {
    private MenuCompat() {
        super();
    }

    @Deprecated public static void setShowAsAction(MenuItem arg0, int arg1) {
        arg0.setShowAsAction(arg1);
    }
}

