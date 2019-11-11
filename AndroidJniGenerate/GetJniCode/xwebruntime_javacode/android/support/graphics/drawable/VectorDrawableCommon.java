package android.support.graphics.drawable;

import android.content.res.Resources$Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;

abstract class VectorDrawableCommon extends Drawable implements TintAwareDrawable {
    Drawable mDelegateDrawable;

    VectorDrawableCommon() {
        super();
    }

    public void applyTheme(Resources$Theme arg2) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, arg2);
            return;
        }
    }

    public void clearColorFilter() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.clearColorFilter();
            return;
        }

        super.clearColorFilter();
    }

    public ColorFilter getColorFilter() {
        if(this.mDelegateDrawable != null) {
            return DrawableCompat.getColorFilter(this.mDelegateDrawable);
        }

        return null;
    }

    public Drawable getCurrent() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getCurrent();
        }

        return super.getCurrent();
    }

    public int getMinimumHeight() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getMinimumHeight();
        }

        return super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getMinimumWidth();
        }

        return super.getMinimumWidth();
    }

    public boolean getPadding(Rect arg2) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getPadding(arg2);
        }

        return super.getPadding(arg2);
    }

    public int[] getState() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getState();
        }

        return super.getState();
    }

    public Region getTransparentRegion() {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getTransparentRegion();
        }

        return super.getTransparentRegion();
    }

    public void jumpToCurrentState() {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.jumpToCurrentState(this.mDelegateDrawable);
            return;
        }
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(arg2);
            return;
        }

        super.onBoundsChange(arg2);
    }

    protected boolean onLevelChange(int arg2) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setLevel(arg2);
        }

        return super.onLevelChange(arg2);
    }

    public void setChangingConfigurations(int arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setChangingConfigurations(arg2);
            return;
        }

        super.setChangingConfigurations(arg2);
    }

    public void setColorFilter(int arg2, PorterDuff$Mode arg3) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(arg2, arg3);
            return;
        }

        super.setColorFilter(arg2, arg3);
    }

    public void setFilterBitmap(boolean arg2) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setFilterBitmap(arg2);
            return;
        }
    }

    public void setHotspot(float arg2, float arg3) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setHotspot(this.mDelegateDrawable, arg2, arg3);
        }
    }

    public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setHotspotBounds(this.mDelegateDrawable, arg2, arg3, arg4, arg5);
            return;
        }
    }

    public boolean setState(int[] arg2) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(arg2);
        }

        return super.setState(arg2);
    }
}

