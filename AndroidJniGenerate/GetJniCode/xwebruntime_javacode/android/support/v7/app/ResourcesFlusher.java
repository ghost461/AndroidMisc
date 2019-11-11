package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    ResourcesFlusher() {
        super();
    }

    static boolean flush(@NonNull Resources arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return ResourcesFlusher.flushNougats(arg2);
        }

        if(Build$VERSION.SDK_INT >= 23) {
            return ResourcesFlusher.flushMarshmallows(arg2);
        }

        if(Build$VERSION.SDK_INT >= 21) {
            return ResourcesFlusher.flushLollipops(arg2);
        }

        return 0;
    }

    @RequiresApi(value=21) private static boolean flushLollipops(@NonNull Resources arg4) {
        Object v4_1;
        if(!ResourcesFlusher.sDrawableCacheFieldFetched) {
            try {
                ResourcesFlusher.sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                ResourcesFlusher.sDrawableCacheField.setAccessible(true);
            }
            catch(NoSuchFieldException v0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", ((Throwable)v0));
            }

            ResourcesFlusher.sDrawableCacheFieldFetched = true;
        }

        if(ResourcesFlusher.sDrawableCacheField != null) {
            Object v0_1 = null;
            try {
                v4_1 = ResourcesFlusher.sDrawableCacheField.get(arg4);
            }
            catch(IllegalAccessException v4) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", ((Throwable)v4));
                v4_1 = v0_1;
            }

            if(v4_1 == null) {
                return 0;
            }

            ((Map)v4_1).clear();
            return 1;
        }

        return 0;
    }

    @RequiresApi(value=23) private static boolean flushMarshmallows(@NonNull Resources arg4) {
        Object v4_1;
        if(!ResourcesFlusher.sDrawableCacheFieldFetched) {
            try {
                ResourcesFlusher.sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                ResourcesFlusher.sDrawableCacheField.setAccessible(true);
            }
            catch(NoSuchFieldException v0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", ((Throwable)v0));
            }

            ResourcesFlusher.sDrawableCacheFieldFetched = true;
        }

        Object v0_1 = null;
        if(ResourcesFlusher.sDrawableCacheField != null) {
            try {
                v4_1 = ResourcesFlusher.sDrawableCacheField.get(arg4);
                goto label_26;
            }
            catch(IllegalAccessException v4) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", ((Throwable)v4));
            }
        }

        v4_1 = v0_1;
    label_26:
        boolean v0_2 = false;
        if(v4_1 == null) {
            return 0;
        }

        if(v4_1 != null && (ResourcesFlusher.flushThemedResourcesCache(v4_1))) {
            v0_2 = true;
        }

        return v0_2;
    }

    @RequiresApi(value=24) private static boolean flushNougats(@NonNull Resources arg6) {
        Object v6_1;
        boolean v1 = true;
        if(!ResourcesFlusher.sResourcesImplFieldFetched) {
            try {
                ResourcesFlusher.sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
                ResourcesFlusher.sResourcesImplField.setAccessible(true);
            }
            catch(NoSuchFieldException v0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", ((Throwable)v0));
            }

            ResourcesFlusher.sResourcesImplFieldFetched = true;
        }

        if(ResourcesFlusher.sResourcesImplField == null) {
            return 0;
        }

        Object v0_1 = null;
        try {
            v6_1 = ResourcesFlusher.sResourcesImplField.get(arg6);
        }
        catch(IllegalAccessException v6) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", ((Throwable)v6));
            v6_1 = v0_1;
        }

        if(v6_1 == null) {
            return 0;
        }

        if(!ResourcesFlusher.sDrawableCacheFieldFetched) {
            try {
                ResourcesFlusher.sDrawableCacheField = v6_1.getClass().getDeclaredField("mDrawableCache");
                ResourcesFlusher.sDrawableCacheField.setAccessible(true);
            }
            catch(NoSuchFieldException v3) {
                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", ((Throwable)v3));
            }

            ResourcesFlusher.sDrawableCacheFieldFetched = true;
        }

        if(ResourcesFlusher.sDrawableCacheField != null) {
            try {
                v6_1 = ResourcesFlusher.sDrawableCacheField.get(v6_1);
                goto label_54;
            }
            catch(IllegalAccessException v6) {
                Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", ((Throwable)v6));
            }
        }

        v6_1 = v0_1;
    label_54:
        if(v6_1 == null || !ResourcesFlusher.flushThemedResourcesCache(v6_1)) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    @RequiresApi(value=16) private static boolean flushThemedResourcesCache(@NonNull Object arg5) {
        if(!ResourcesFlusher.sThemedResourceCacheClazzFetched) {
            try {
                ResourcesFlusher.sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            }
            catch(ClassNotFoundException v0) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", ((Throwable)v0));
            }

            ResourcesFlusher.sThemedResourceCacheClazzFetched = true;
        }

        if(ResourcesFlusher.sThemedResourceCacheClazz == null) {
            return 0;
        }

        if(!ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesFieldFetched) {
            try {
                ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesField = ResourcesFlusher.sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
            }
            catch(NoSuchFieldException v0_1) {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", ((Throwable)v0_1));
            }

            ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
        }

        if(ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesField == null) {
            return 0;
        }

        Object v0_2 = null;
        try {
            arg5 = ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesField.get(arg5);
        }
        catch(IllegalAccessException v5) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", ((Throwable)v5));
            arg5 = v0_2;
        }

        if(arg5 != null) {
            ((LongSparseArray)arg5).clear();
            return 1;
        }

        return 0;
    }
}

