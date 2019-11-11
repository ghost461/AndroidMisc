package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.View$OnTouchListener;
import android.widget.PopupMenu;

public final class PopupMenuCompat {
    private PopupMenuCompat() {
        super();
    }

    public static View$OnTouchListener getDragToOpenListener(Object arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return ((PopupMenu)arg2).getDragToOpenListener();
        }

        return null;
    }
}

