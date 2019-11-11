package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

public final class AppCompatResources {
    class ColorStateListCacheEntry {
        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(@NonNull ColorStateList arg1, @NonNull Configuration arg2) {
            super();
            this.value = arg1;
            this.configuration = arg2;
        }
    }

    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal TL_TYPED_VALUE;
    private static final Object sColorStateCacheLock;
    private static final WeakHashMap sColorStateCaches;

    static {
        AppCompatResources.TL_TYPED_VALUE = new ThreadLocal();
        AppCompatResources.sColorStateCaches = new WeakHashMap(0);
        AppCompatResources.sColorStateCacheLock = new Object();
    }

    private AppCompatResources() {
        super();
    }

    private static void addColorStateListToCache(@NonNull Context arg3, @ColorRes int arg4, @NonNull ColorStateList arg5) {
        SparseArray v1_1;
        Object v0 = AppCompatResources.sColorStateCacheLock;
        __monitor_enter(v0);
        try {
            Object v1 = AppCompatResources.sColorStateCaches.get(arg3);
            if(v1 == null) {
                v1_1 = new SparseArray();
                AppCompatResources.sColorStateCaches.put(arg3, v1_1);
            }

            v1_1.append(arg4, new ColorStateListCacheEntry(arg5, arg3.getResources().getConfiguration()));
            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }

    @Nullable private static ColorStateList getCachedColorStateList(@NonNull Context arg4, @ColorRes int arg5) {
        Object v0 = AppCompatResources.sColorStateCacheLock;
        __monitor_enter(v0);
        try {
            Object v1 = AppCompatResources.sColorStateCaches.get(arg4);
            if(v1 != null && ((SparseArray)v1).size() > 0) {
                Object v2 = ((SparseArray)v1).get(arg5);
                if(v2 != null) {
                    if(((ColorStateListCacheEntry)v2).configuration.equals(arg4.getResources().getConfiguration())) {
                        __monitor_exit(v0);
                        return ((ColorStateListCacheEntry)v2).value;
                    }
                    else {
                        ((SparseArray)v1).remove(arg5);
                    }
                }
            }

            __monitor_exit(v0);
            return null;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_22;
        }

        throw v4;
    }

    public static ColorStateList getColorStateList(@NonNull Context arg2, @ColorRes int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColorStateList(arg3);
        }

        ColorStateList v0 = AppCompatResources.getCachedColorStateList(arg2, arg3);
        if(v0 != null) {
            return v0;
        }

        v0 = AppCompatResources.inflateColorStateList(arg2, arg3);
        if(v0 != null) {
            AppCompatResources.addColorStateListToCache(arg2, arg3, v0);
            return v0;
        }

        return ContextCompat.getColorStateList(arg2, arg3);
    }

    @Nullable public static Drawable getDrawable(@NonNull Context arg1, @DrawableRes int arg2) {
        return AppCompatDrawableManager.get().getDrawable(arg1, arg2);
    }

    @NonNull private static TypedValue getTypedValue() {
        TypedValue v0_1;
        Object v0 = AppCompatResources.TL_TYPED_VALUE.get();
        if(v0 == null) {
            v0_1 = new TypedValue();
            AppCompatResources.TL_TYPED_VALUE.set(v0_1);
        }

        return v0_1;
    }

    @Nullable private static ColorStateList inflateColorStateList(Context arg2, int arg3) {
        ColorStateList v1 = null;
        if(AppCompatResources.isColorInt(arg2, arg3)) {
            return v1;
        }

        Resources v0 = arg2.getResources();
        XmlResourceParser v3 = v0.getXml(arg3);
        try {
            return AppCompatColorStateListInflater.createFromXml(v0, ((XmlPullParser)v3), arg2.getTheme());
        }
        catch(Exception v2) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", ((Throwable)v2));
            return v1;
        }
    }

    private static boolean isColorInt(@NonNull Context arg2, @ColorRes int arg3) {
        Resources v2 = arg2.getResources();
        TypedValue v0 = AppCompatResources.getTypedValue();
        boolean v1 = true;
        v2.getValue(arg3, v0, true);
        if(v0.type < 28 || v0.type > 0x1F) {
            v1 = false;
        }
        else {
        }

        return v1;
    }
}

