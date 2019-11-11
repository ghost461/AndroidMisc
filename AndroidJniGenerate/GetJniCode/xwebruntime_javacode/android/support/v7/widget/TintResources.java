package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
    private final WeakReference mContextRef;

    public TintResources(@NonNull Context arg1, @NonNull Resources arg2) {
        super(arg2);
        this.mContextRef = new WeakReference(arg1);
    }

    public Drawable getDrawable(int arg3) throws Resources$NotFoundException {
        Drawable v0 = super.getDrawable(arg3);
        Object v1 = this.mContextRef.get();
        if(v0 != null && v1 != null) {
            AppCompatDrawableManager.get();
            AppCompatDrawableManager.tintDrawableUsingColorFilter(((Context)v1), arg3, v0);
        }

        return v0;
    }
}

