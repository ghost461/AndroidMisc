package android.support.v4.view;

import android.support.annotation.Nullable;

public interface NestedScrollingChild {
    boolean dispatchNestedFling(float arg1, float arg2, boolean arg3);

    boolean dispatchNestedPreFling(float arg1, float arg2);

    boolean dispatchNestedPreScroll(int arg1, int arg2, @Nullable int[] arg3, @Nullable int[] arg4);

    boolean dispatchNestedScroll(int arg1, int arg2, int arg3, int arg4, @Nullable int[] arg5);

    boolean hasNestedScrollingParent();

    boolean isNestedScrollingEnabled();

    void setNestedScrollingEnabled(boolean arg1);

    boolean startNestedScroll(int arg1);

    void stopNestedScroll();
}

