package android.support.v7.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class DrawableWrapper extends Drawable implements Drawable$Callback {
    private Drawable mDrawable;

    public DrawableWrapper(Drawable arg1) {
        super();
        this.setWrappedDrawable(arg1);
    }

    public void draw(Canvas arg2) {
        this.mDrawable.draw(arg2);
    }

    public int getChangingConfigurations() {
        return this.mDrawable.getChangingConfigurations();
    }

    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    public boolean getPadding(Rect arg2) {
        return this.mDrawable.getPadding(arg2);
    }

    public int[] getState() {
        return this.mDrawable.getState();
    }

    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    public Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    public void invalidateDrawable(Drawable arg1) {
        this.invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(this.mDrawable);
    }

    public boolean isStateful() {
        return this.mDrawable.isStateful();
    }

    public void jumpToCurrentState() {
        DrawableCompat.jumpToCurrentState(this.mDrawable);
    }

    protected void onBoundsChange(Rect arg2) {
        this.mDrawable.setBounds(arg2);
    }

    protected boolean onLevelChange(int arg2) {
        return this.mDrawable.setLevel(arg2);
    }

    public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
        this.scheduleSelf(arg2, arg3);
    }

    public void setAlpha(int arg2) {
        this.mDrawable.setAlpha(arg2);
    }

    public void setAutoMirrored(boolean arg2) {
        DrawableCompat.setAutoMirrored(this.mDrawable, arg2);
    }

    public void setChangingConfigurations(int arg2) {
        this.mDrawable.setChangingConfigurations(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.mDrawable.setColorFilter(arg2);
    }

    public void setDither(boolean arg2) {
        this.mDrawable.setDither(arg2);
    }

    public void setFilterBitmap(boolean arg2) {
        this.mDrawable.setFilterBitmap(arg2);
    }

    public void setHotspot(float arg2, float arg3) {
        DrawableCompat.setHotspot(this.mDrawable, arg2, arg3);
    }

    public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
        DrawableCompat.setHotspotBounds(this.mDrawable, arg2, arg3, arg4, arg5);
    }

    public boolean setState(int[] arg2) {
        return this.mDrawable.setState(arg2);
    }

    public void setTint(int arg2) {
        DrawableCompat.setTint(this.mDrawable, arg2);
    }

    public void setTintList(ColorStateList arg2) {
        DrawableCompat.setTintList(this.mDrawable, arg2);
    }

    public void setTintMode(PorterDuff$Mode arg2) {
        DrawableCompat.setTintMode(this.mDrawable, arg2);
    }

    public boolean setVisible(boolean arg2, boolean arg3) {
        return (super.setVisible(arg2, arg3)) || (this.mDrawable.setVisible(arg2, arg3)) ? true : false;
    }

    public void setWrappedDrawable(Drawable arg3) {
        if(this.mDrawable != null) {
            this.mDrawable.setCallback(null);
        }

        this.mDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }
    }

    public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
        this.unscheduleSelf(arg2);
    }
}

