package android.support.v4.widget;

import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

public final class ListViewCompat {
    private ListViewCompat() {
        super();
    }

    public static boolean canScrollList(@NonNull ListView arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg4.canScrollList(arg5);
        }

        int v0 = arg4.getChildCount();
        boolean v1 = false;
        if(v0 == 0) {
            return 0;
        }

        int v2 = arg4.getFirstVisiblePosition();
        if(arg5 > 0) {
            arg5 = arg4.getChildAt(v0 - 1).getBottom();
            if(v2 + v0 < arg4.getCount() || arg5 > arg4.getHeight() - arg4.getListPaddingBottom()) {
                v1 = true;
            }

            return v1;
        }

        arg5 = arg4.getChildAt(0).getTop();
        if(v2 > 0 || arg5 < arg4.getListPaddingTop()) {
            v1 = true;
        }

        return v1;
    }

    public static void scrollListBy(@NonNull ListView arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.scrollListBy(arg3);
        }
        else {
            int v0 = arg2.getFirstVisiblePosition();
            if(v0 == -1) {
                return;
            }
            else {
                View v1 = arg2.getChildAt(0);
                if(v1 == null) {
                    return;
                }
                else {
                    arg2.setSelectionFromTop(v0, v1.getTop() - arg3);
                }
            }
        }
    }
}

