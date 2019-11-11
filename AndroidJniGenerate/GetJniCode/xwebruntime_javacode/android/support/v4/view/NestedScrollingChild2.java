package android.support.v4.view;

import android.support.annotation.Nullable;

public interface NestedScrollingChild2 extends NestedScrollingChild {
    boolean dispatchNestedPreScroll(int arg1, int arg2, @Nullable int[] arg3, @Nullable int[] arg4, int arg5);

    boolean dispatchNestedScroll(int arg1, int arg2, int arg3, int arg4, @Nullable int[] arg5, int arg6);

    boolean hasNestedScrollingParent(int arg1);

    boolean startNestedScroll(int arg1, int arg2);

    void stopNestedScroll(int arg1);
}

