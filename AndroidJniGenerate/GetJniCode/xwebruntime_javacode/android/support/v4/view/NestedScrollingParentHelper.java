package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper {
    private int mNestedScrollAxes;
    private final ViewGroup mViewGroup;

    public NestedScrollingParentHelper(@NonNull ViewGroup arg1) {
        super();
        this.mViewGroup = arg1;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxes;
    }

    public void onNestedScrollAccepted(@NonNull View arg2, @NonNull View arg3, int arg4) {
        this.onNestedScrollAccepted(arg2, arg3, arg4, 0);
    }

    public void onNestedScrollAccepted(@NonNull View arg1, @NonNull View arg2, int arg3, int arg4) {
        this.mNestedScrollAxes = arg3;
    }

    public void onStopNestedScroll(@NonNull View arg2) {
        this.onStopNestedScroll(arg2, 0);
    }

    public void onStopNestedScroll(@NonNull View arg1, int arg2) {
        this.mNestedScrollAxes = 0;
    }
}

