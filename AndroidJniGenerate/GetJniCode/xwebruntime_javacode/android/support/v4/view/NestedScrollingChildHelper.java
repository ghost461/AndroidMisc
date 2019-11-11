package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(@NonNull View arg1) {
        super();
        this.mView = arg1;
    }

    public boolean dispatchNestedFling(float arg3, float arg4, boolean arg5) {
        if(this.isNestedScrollingEnabled()) {
            ViewParent v0 = this.getNestedScrollingParentForType(0);
            if(v0 != null) {
                return ViewParentCompat.onNestedFling(v0, this.mView, arg3, arg4, arg5);
            }
        }

        return 0;
    }

    public boolean dispatchNestedPreFling(float arg3, float arg4) {
        if(this.isNestedScrollingEnabled()) {
            ViewParent v0 = this.getNestedScrollingParentForType(0);
            if(v0 != null) {
                return ViewParentCompat.onNestedPreFling(v0, this.mView, arg3, arg4);
            }
        }

        return 0;
    }

    public boolean dispatchNestedPreScroll(int arg7, int arg8, @Nullable int[] arg9, @Nullable int[] arg10) {
        return this.dispatchNestedPreScroll(arg7, arg8, arg9, arg10, 0);
    }

    public boolean dispatchNestedPreScroll(int arg11, int arg12, @Nullable int[] arg13, @Nullable int[] arg14, int arg15) {
        int v9;
        int v8;
        if(this.isNestedScrollingEnabled()) {
            ViewParent v2 = this.getNestedScrollingParentForType(arg15);
            if(v2 == null) {
                return 0;
            }
            else {
                boolean v0 = true;
                if(arg11 == 0) {
                    if(arg12 != 0) {
                    }
                    else {
                        if(arg14 != null) {
                            arg14[0] = 0;
                            arg14[1] = 0;
                        }
                        else {
                        }

                        return 0;
                    }
                }

                if(arg14 != null) {
                    this.mView.getLocationInWindow(arg14);
                    v8 = arg14[0];
                    v9 = arg14[1];
                }
                else {
                    v8 = 0;
                    v9 = 0;
                }

                if(arg13 == null) {
                    if(this.mTempNestedScrollConsumed == null) {
                        this.mTempNestedScrollConsumed = new int[2];
                    }

                    arg13 = this.mTempNestedScrollConsumed;
                }

                arg13[0] = 0;
                arg13[1] = 0;
                ViewParentCompat.onNestedPreScroll(v2, this.mView, arg11, arg12, arg13, arg15);
                if(arg14 != null) {
                    this.mView.getLocationInWindow(arg14);
                    arg14[0] -= v8;
                    arg14[1] -= v9;
                }

                if(arg13[0] == 0) {
                    if(arg13[1] != 0) {
                    }
                    else {
                        v0 = false;
                    }
                }

                return v0;
            }
        }

        return 0;
    }

    public boolean dispatchNestedScroll(int arg8, int arg9, int arg10, int arg11, @Nullable int[] arg12) {
        return this.dispatchNestedScroll(arg8, arg9, arg10, arg11, arg12, 0);
    }

    public boolean dispatchNestedScroll(int arg15, int arg16, int arg17, int arg18, @Nullable int[] arg19, int arg20) {
        int v13;
        int v12;
        NestedScrollingChildHelper v0 = this;
        int[] v1 = arg19;
        if(v0.isNestedScrollingEnabled()) {
            int v2 = arg20;
            ViewParent v4 = v0.getNestedScrollingParentForType(v2);
            if(v4 == null) {
                return 0;
            }
            else {
                if(arg15 == 0 && arg16 == 0 && arg17 == 0) {
                    if(arg18 != 0) {
                    }
                    else {
                        if(v1 != null) {
                            v1[0] = 0;
                            v1[1] = 0;
                        }
                        else {
                        }

                        return 0;
                    }
                }

                if(v1 != null) {
                    v0.mView.getLocationInWindow(v1);
                    v12 = v1[0];
                    v13 = v1[1];
                }
                else {
                    v12 = 0;
                    v13 = 0;
                }

                ViewParentCompat.onNestedScroll(v4, v0.mView, arg15, arg16, arg17, arg18, v2);
                if(v1 != null) {
                    v0.mView.getLocationInWindow(v1);
                    v1[0] -= v12;
                    v1[1] -= v13;
                }

                return 1;
            }
        }

        return 0;
    }

    private ViewParent getNestedScrollingParentForType(int arg1) {
        switch(arg1) {
            case 0: {
                goto label_5;
            }
            case 1: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return this.mNestedScrollingParentNonTouch;
    label_5:
        return this.mNestedScrollingParentTouch;
    }

    public boolean hasNestedScrollingParent() {
        return this.hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int arg1) {
        boolean v1 = this.getNestedScrollingParentForType(arg1) != null ? true : false;
        return v1;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void onStopNestedScroll(@NonNull View arg1) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void setNestedScrollingEnabled(boolean arg2) {
        if(this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }

        this.mIsNestedScrollingEnabled = arg2;
    }

    private void setNestedScrollingParentForType(int arg1, ViewParent arg2) {
        switch(arg1) {
            case 0: {
                this.mNestedScrollingParentTouch = arg2;
                break;
            }
            case 1: {
                this.mNestedScrollingParentNonTouch = arg2;
                break;
            }
            default: {
                break;
            }
        }
    }

    public boolean startNestedScroll(int arg2) {
        return this.startNestedScroll(arg2, 0);
    }

    public boolean startNestedScroll(int arg5, int arg6) {
        if(this.hasNestedScrollingParent(arg6)) {
            return 1;
        }

        if(this.isNestedScrollingEnabled()) {
            ViewParent v0 = this.mView.getParent();
            View v2 = this.mView;
            while(v0 != null) {
                if(ViewParentCompat.onStartNestedScroll(v0, v2, this.mView, arg5, arg6)) {
                    this.setNestedScrollingParentForType(arg6, v0);
                    ViewParentCompat.onNestedScrollAccepted(v0, v2, this.mView, arg5, arg6);
                    return 1;
                }
                else {
                    if((v0 instanceof View)) {
                        ViewParent v2_1 = v0;
                    }

                    v0 = v0.getParent();
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    public void stopNestedScroll() {
        this.stopNestedScroll(0);
    }

    public void stopNestedScroll(int arg3) {
        ViewParent v0 = this.getNestedScrollingParentForType(arg3);
        if(v0 != null) {
            ViewParentCompat.onStopNestedScroll(v0, this.mView, arg3);
            this.setNestedScrollingParentForType(arg3, null);
        }
    }
}

