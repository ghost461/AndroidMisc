package org.chromium.ui.resources.dynamics;

import android.util.SparseArray;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader$ResourceLoaderCallback;
import org.chromium.ui.resources.ResourceLoader;

public class DynamicResourceLoader extends ResourceLoader {
    private final SparseArray mDynamicResources;

    static {
    }

    public DynamicResourceLoader(int arg1, ResourceLoaderCallback arg2) {
        super(arg1, arg2);
        this.mDynamicResources = new SparseArray();
    }

    public void loadResource(int arg3) {
        Object v0 = this.mDynamicResources.get(arg3);
        if(v0 == null) {
            return;
        }

        if(((DynamicResource)v0).isDirty()) {
            this.notifyLoadFinished(arg3, ((Resource)v0));
        }
    }

    public void preloadResource(int arg1) {
    }

    public void registerResource(int arg2, DynamicResource arg3) {
        this.mDynamicResources.put(arg2, arg3);
    }

    public void unregisterResource(int arg2) {
        this.mDynamicResources.remove(arg2);
        this.notifyResourceUnregistered(arg2);
    }
}

