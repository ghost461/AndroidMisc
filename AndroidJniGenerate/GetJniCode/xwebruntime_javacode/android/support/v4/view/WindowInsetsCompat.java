package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.WindowInsets;

public class WindowInsetsCompat {
    private final Object mInsets;

    public WindowInsetsCompat(WindowInsetsCompat arg4) {
        super();
        Object v1 = null;
        if(Build$VERSION.SDK_INT >= 20) {
            if(arg4 == null) {
            }
            else {
                WindowInsets v1_1 = new WindowInsets(arg4.mInsets);
            }

            this.mInsets = v1;
        }
        else {
            this.mInsets = v1;
        }
    }

    private WindowInsetsCompat(Object arg1) {
        super();
        this.mInsets = arg1;
    }

    public WindowInsetsCompat consumeStableInsets() {
        if(Build$VERSION.SDK_INT >= 21) {
            return new WindowInsetsCompat(this.mInsets.consumeStableInsets());
        }

        return null;
    }

    public WindowInsetsCompat consumeSystemWindowInsets() {
        if(Build$VERSION.SDK_INT >= 20) {
            return new WindowInsetsCompat(this.mInsets.consumeSystemWindowInsets());
        }

        return null;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((WindowInsetsCompat)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.mInsets != null) {
                    v0 = this.mInsets.equals(((WindowInsetsCompat)arg5).mInsets);
                }
                else if(((WindowInsetsCompat)arg5).mInsets == null) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
        }

        return 0;
    }

    public int getStableInsetBottom() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInsets.getStableInsetBottom();
        }

        return 0;
    }

    public int getStableInsetLeft() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInsets.getStableInsetLeft();
        }

        return 0;
    }

    public int getStableInsetRight() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInsets.getStableInsetRight();
        }

        return 0;
    }

    public int getStableInsetTop() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInsets.getStableInsetTop();
        }

        return 0;
    }

    public int getSystemWindowInsetBottom() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.getSystemWindowInsetBottom();
        }

        return 0;
    }

    public int getSystemWindowInsetLeft() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.getSystemWindowInsetLeft();
        }

        return 0;
    }

    public int getSystemWindowInsetRight() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.getSystemWindowInsetRight();
        }

        return 0;
    }

    public int getSystemWindowInsetTop() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.getSystemWindowInsetTop();
        }

        return 0;
    }

    public boolean hasInsets() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.hasInsets();
        }

        return 0;
    }

    public boolean hasStableInsets() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInsets.hasStableInsets();
        }

        return 0;
    }

    public boolean hasSystemWindowInsets() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.hasSystemWindowInsets();
        }

        return 0;
    }

    public int hashCode() {
        int v0 = this.mInsets == null ? 0 : this.mInsets.hashCode();
        return v0;
    }

    public boolean isConsumed() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.mInsets.isConsumed();
        }

        return 0;
    }

    public boolean isRound() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.mInsets.isRound();
        }

        return 0;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 20) {
            return new WindowInsetsCompat(this.mInsets.replaceSystemWindowInsets(arg3, arg4, arg5, arg6));
        }

        return null;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            return new WindowInsetsCompat(this.mInsets.replaceSystemWindowInsets(arg3));
        }

        return null;
    }

    static Object unwrap(WindowInsetsCompat arg0) {
        Object v0 = arg0 == null ? null : arg0.mInsets;
        return v0;
    }

    static WindowInsetsCompat wrap(Object arg1) {
        WindowInsetsCompat v1 = arg1 == null ? null : new WindowInsetsCompat(arg1);
        return v1;
    }
}

