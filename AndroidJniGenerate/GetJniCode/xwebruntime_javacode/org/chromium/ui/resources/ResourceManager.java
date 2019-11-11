package org.chromium.ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.SparseArray;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.display.DisplayAndroid;
import org.chromium.ui.resources.dynamics.DynamicResourceLoader;
import org.chromium.ui.resources.statics.StaticResourceLoader;
import org.chromium.ui.resources.system.SystemResourceLoader;

@JNINamespace(value="ui") @MainDex public class ResourceManager implements ResourceLoaderCallback {
    private final SparseArray mLoadedResources;
    private long mNativeResourceManagerPtr;
    private final float mPxToDp;
    private final SparseArray mResourceLoaders;

    static {
    }

    private ResourceManager(Resources arg3, int arg4, long arg5) {
        super();
        this.mResourceLoaders = new SparseArray();
        this.mLoadedResources = new SparseArray();
        this.mPxToDp = 1f / arg3.getDisplayMetrics().density;
        this.registerResourceLoader(new StaticResourceLoader(0, ((ResourceLoaderCallback)this), arg3));
        this.registerResourceLoader(new DynamicResourceLoader(1, ((ResourceLoaderCallback)this)));
        this.registerResourceLoader(new DynamicResourceLoader(2, ((ResourceLoaderCallback)this)));
        this.registerResourceLoader(new SystemResourceLoader(3, ((ResourceLoaderCallback)this), arg4));
        this.mNativeResourceManagerPtr = arg5;
    }

    public void clearTintedResourceCache() {
        if(this.mNativeResourceManagerPtr == 0) {
            return;
        }

        this.nativeClearTintedResourceCache(this.mNativeResourceManagerPtr);
    }

    @CalledByNative private static ResourceManager create(WindowAndroid arg2, long arg3) {
        Object v0 = arg2.getContext().get();
        if(v0 == null) {
            throw new IllegalStateException("Context should not be null during initialization.");
        }

        DisplayAndroid v2 = arg2.getDisplay();
        return new ResourceManager(((Context)v0).getResources(), Math.min(v2.getDisplayWidth(), v2.getDisplayHeight()), arg3);
    }

    @CalledByNative private void destroy() {
        this.mNativeResourceManagerPtr = 0;
    }

    public DynamicResourceLoader getBitmapDynamicResourceLoader() {
        return this.mResourceLoaders.get(2);
    }

    public DynamicResourceLoader getDynamicResourceLoader() {
        return this.mResourceLoaders.get(1);
    }

    @CalledByNative private long getNativePtr() {
        return this.mNativeResourceManagerPtr;
    }

    public LayoutResource getResource(int arg2, int arg3) {
        LayoutResource v2_1;
        Object v2 = this.mLoadedResources.get(arg2);
        if(v2 != null) {
            v2 = ((SparseArray)v2).get(arg3);
        }
        else {
            v2_1 = null;
        }

        return v2_1;
    }

    private native void nativeClearTintedResourceCache(long arg1) {
    }

    private native void nativeOnResourceReady(long arg1, int arg2, int arg3, Bitmap arg4, long arg5) {
    }

    private native void nativeRemoveResource(long arg1, int arg2, int arg3) {
    }

    public void onResourceLoaded(int arg10, int arg11, Resource arg12) {
        if(arg12 != null) {
            if(arg12.getBitmap() == null) {
            }
            else {
                this.saveMetadataForLoadedResource(arg10, arg11, arg12);
                if(this.mNativeResourceManagerPtr == 0) {
                    return;
                }
                else {
                    this.nativeOnResourceReady(this.mNativeResourceManagerPtr, arg10, arg11, arg12.getBitmap(), arg12.createNativeResource());
                    return;
                }
            }
        }
    }

    public void onResourceUnregistered(int arg3, int arg4) {
        if(arg3 != 2) {
            return;
        }

        this.nativeRemoveResource(this.mNativeResourceManagerPtr, arg3, arg4);
    }

    @CalledByNative private void preloadResource(int arg2, int arg3) {
        Object v2 = this.mResourceLoaders.get(arg2);
        if(v2 != null) {
            ((ResourceLoader)v2).preloadResource(arg3);
        }
    }

    public void preloadResources(int arg5, int[] arg6, int[] arg7) {
        Object v5 = this.mResourceLoaders.get(arg5);
        int v0 = 0;
        if(arg7 != null) {
            int v1 = arg7.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                ((ResourceLoader)v5).preloadResource(Integer.valueOf(arg7[v2]).intValue());
            }
        }

        if(arg6 != null) {
            int v7 = arg6.length;
            while(v0 < v7) {
                ((ResourceLoader)v5).loadResource(Integer.valueOf(arg6[v0]).intValue());
                ++v0;
            }
        }
    }

    private void registerResourceLoader(ResourceLoader arg3) {
        this.mResourceLoaders.put(arg3.getResourceType(), arg3);
    }

    @CalledByNative private void resourceRequested(int arg2, int arg3) {
        Object v2 = this.mResourceLoaders.get(arg2);
        if(v2 != null) {
            ((ResourceLoader)v2).loadResource(arg3);
        }
    }

    private void saveMetadataForLoadedResource(int arg3, int arg4, Resource arg5) {
        SparseArray v0_1;
        Object v0 = this.mLoadedResources.get(arg3);
        if(v0 == null) {
            v0_1 = new SparseArray();
            this.mLoadedResources.put(arg3, v0_1);
        }

        v0_1.put(arg4, new LayoutResource(this.mPxToDp, arg5));
    }
}

