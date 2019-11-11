package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.View;

public interface NestedScrollingParent {
    int getNestedScrollAxes();

    boolean onNestedFling(@NonNull View arg1, float arg2, float arg3, boolean arg4);

    boolean onNestedPreFling(@NonNull View arg1, float arg2, float arg3);

    void onNestedPreScroll(@NonNull View arg1, int arg2, int arg3, @NonNull int[] arg4);

    void onNestedScroll(@NonNull View arg1, int arg2, int arg3, int arg4, int arg5);

    void onNestedScrollAccepted(@NonNull View arg1, @NonNull View arg2, int arg3);

    boolean onStartNestedScroll(@NonNull View arg1, @NonNull View arg2, int arg3);

    void onStopNestedScroll(@NonNull View arg1);
}

