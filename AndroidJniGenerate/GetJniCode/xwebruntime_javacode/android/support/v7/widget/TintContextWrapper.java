package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK;
    private final Resources mResources;
    private final Resources$Theme mTheme;
    private static ArrayList sCache;

    static {
        TintContextWrapper.CACHE_LOCK = new Object();
    }

    private TintContextWrapper(@NonNull Context arg3) {
        super(arg3);
        if(VectorEnabledTintResources.shouldBeUsed()) {
            this.mResources = new VectorEnabledTintResources(((Context)this), arg3.getResources());
            this.mTheme = this.mResources.newTheme();
            this.mTheme.setTo(arg3.getTheme());
        }
        else {
            this.mResources = new TintResources(((Context)this), arg3.getResources());
            this.mTheme = null;
        }
    }

    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    public Resources getResources() {
        return this.mResources;
    }

    public Resources$Theme getTheme() {
        Resources$Theme v0 = this.mTheme == null ? super.getTheme() : this.mTheme;
        return v0;
    }

    public void setTheme(int arg3) {
        if(this.mTheme == null) {
            super.setTheme(arg3);
        }
        else {
            this.mTheme.applyStyle(arg3, true);
        }
    }

    private static boolean shouldWrap(@NonNull Context arg2) {
        boolean v1 = false;
        if(!(arg2 instanceof TintContextWrapper) && !(arg2.getResources() instanceof TintResources)) {
            if((arg2.getResources() instanceof VectorEnabledTintResources)) {
            }
            else {
                if(Build$VERSION.SDK_INT < 21 || (VectorEnabledTintResources.shouldBeUsed())) {
                    v1 = true;
                }

                return v1;
            }
        }

        return 0;
    }

    public static Context wrap(@NonNull Context arg4) {
        Object v2;
        if(TintContextWrapper.shouldWrap(arg4)) {
            Object v0 = TintContextWrapper.CACHE_LOCK;
            __monitor_enter(v0);
            try {
                if(TintContextWrapper.sCache == null) {
                    TintContextWrapper.sCache = new ArrayList();
                }
                else {
                    int v1;
                    for(v1 = TintContextWrapper.sCache.size() - 1; v1 >= 0; --v1) {
                        v2 = TintContextWrapper.sCache.get(v1);
                        if(v2 == null || ((WeakReference)v2).get() == null) {
                            TintContextWrapper.sCache.remove(v1);
                        }
                    }

                    for(v1 = TintContextWrapper.sCache.size() - 1; v1 >= 0; --v1) {
                        v2 = TintContextWrapper.sCache.get(v1);
                        v2 = v2 != null ? ((WeakReference)v2).get() : null;
                        if(v2 != null && ((TintContextWrapper)v2).getBaseContext() == arg4) {
                            __monitor_exit(v0);
                            return ((Context)v2);
                        }
                    }
                }

                TintContextWrapper v1_1 = new TintContextWrapper(arg4);
                TintContextWrapper.sCache.add(new WeakReference(v1_1));
                __monitor_exit(v0);
                return ((Context)v1_1);
            label_49:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_49;
            }

            throw v4;
        }

        return arg4;
    }
}

