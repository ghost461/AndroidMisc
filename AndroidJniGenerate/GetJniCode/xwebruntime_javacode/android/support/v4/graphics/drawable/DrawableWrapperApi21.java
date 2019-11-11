package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.lang.reflect.Method;

@RequiresApi(value=21) class DrawableWrapperApi21 extends DrawableWrapperApi19 {
    class DrawableWrapperStateLollipop extends DrawableWrapperState {
        DrawableWrapperStateLollipop(@Nullable DrawableWrapperState arg1, @Nullable Resources arg2) {
            super(arg1, arg2);
        }

        public Drawable newDrawable(@Nullable Resources arg2) {
            return new DrawableWrapperApi21(((DrawableWrapperState)this), arg2);
        }
    }

    private static final String TAG = "DrawableWrapperApi21";
    private static Method sIsProjectedDrawableMethod;

    DrawableWrapperApi21(Drawable arg1) {
        super(arg1);
        this.findAndCacheIsProjectedDrawableMethod();
    }

    DrawableWrapperApi21(DrawableWrapperState arg1, Resources arg2) {
        super(arg1, arg2);
        this.findAndCacheIsProjectedDrawableMethod();
    }

    private void findAndCacheIsProjectedDrawableMethod() {
        if(DrawableWrapperApi21.sIsProjectedDrawableMethod == null) {
            try {
                DrawableWrapperApi21.sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected");
            }
            catch(Exception v0) {
                Log.w("DrawableWrapperApi21", "Failed to retrieve Drawable#isProjected() method", ((Throwable)v0));
            }
        }
    }

    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    public void getOutline(Outline arg2) {
        this.mDrawable.getOutline(arg2);
    }

    protected boolean isCompatTintEnabled() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT == 21) {
            Drawable v0 = this.mDrawable;
            if(((v0 instanceof GradientDrawable)) || ((v0 instanceof DrawableContainer)) || ((v0 instanceof InsetDrawable)) || ((v0 instanceof RippleDrawable))) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public boolean isProjected() {
        if(this.mDrawable != null && DrawableWrapperApi21.sIsProjectedDrawableMethod != null) {
            try {
                return DrawableWrapperApi21.sIsProjectedDrawableMethod.invoke(this.mDrawable).booleanValue();
            }
            catch(Exception v0) {
                Log.w("DrawableWrapperApi21", "Error calling Drawable#isProjected() method", ((Throwable)v0));
            }
        }

        return 0;
    }

    @NonNull DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateLollipop(this.mState, null);
    }

    public void setHotspot(float arg2, float arg3) {
        this.mDrawable.setHotspot(arg2, arg3);
    }

    public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
        this.mDrawable.setHotspotBounds(arg2, arg3, arg4, arg5);
    }

    public boolean setState(int[] arg1) {
        if(super.setState(arg1)) {
            this.invalidateSelf();
            return 1;
        }

        return 0;
    }

    public void setTint(int arg2) {
        if(this.isCompatTintEnabled()) {
            super.setTint(arg2);
        }
        else {
            this.mDrawable.setTint(arg2);
        }
    }

    public void setTintList(ColorStateList arg2) {
        if(this.isCompatTintEnabled()) {
            super.setTintList(arg2);
        }
        else {
            this.mDrawable.setTintList(arg2);
        }
    }

    public void setTintMode(PorterDuff$Mode arg2) {
        if(this.isCompatTintEnabled()) {
            super.setTintMode(arg2);
        }
        else {
            this.mDrawable.setTintMode(arg2);
        }
    }
}

