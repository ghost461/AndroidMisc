package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.View$OnTouchListener;
import android.view.View;
import android.widget.ListPopupWindow;

public final class ListPopupWindowCompat {
    private ListPopupWindowCompat() {
        super();
    }

    public static View$OnTouchListener createDragToOpenListener(ListPopupWindow arg2, View arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.createDragToOpenListener(arg3);
        }

        return null;
    }

    @Deprecated public static View$OnTouchListener createDragToOpenListener(Object arg0, View arg1) {
        return ListPopupWindowCompat.createDragToOpenListener(((ListPopupWindow)arg0), arg1);
    }
}

