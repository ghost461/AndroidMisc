package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatDelegate;
import java.lang.ref.WeakReference;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class VectorEnabledTintResources extends Resources {
    public static final int MAX_SDK_WHERE_REQUIRED = 20;
    private final WeakReference mContextRef;

    public VectorEnabledTintResources(@NonNull Context arg3, @NonNull Resources arg4) {
        super(arg4.getAssets(), arg4.getDisplayMetrics(), arg4.getConfiguration());
        this.mContextRef = new WeakReference(arg3);
    }

    public Drawable getDrawable(int arg3) throws Resources$NotFoundException {
        Object v0 = this.mContextRef.get();
        if(v0 != null) {
            return AppCompatDrawableManager.get().onDrawableLoadedFromResources(((Context)v0), this, arg3);
        }

        return super.getDrawable(arg3);
    }

    public static boolean shouldBeUsed() {
        boolean v0 = !AppCompatDelegate.isCompatVectorFromResourcesEnabled() || Build$VERSION.SDK_INT > 20 ? false : true;
        return v0;
    }

    final Drawable superGetDrawable(int arg1) {
        return super.getDrawable(arg1);
    }
}

