package org.chromium.ui.resources;

public abstract class ResourceLoader {
    public interface ResourceLoaderCallback {
        void onResourceLoaded(int arg1, int arg2, Resource arg3);

        void onResourceUnregistered(int arg1, int arg2);
    }

    private final ResourceLoaderCallback mCallback;
    private final int mResourceType;

    public ResourceLoader(int arg1, ResourceLoaderCallback arg2) {
        super();
        this.mResourceType = arg1;
        this.mCallback = arg2;
    }

    public int getResourceType() {
        return this.mResourceType;
    }

    public abstract void loadResource(int arg1);

    protected void notifyLoadFinished(int arg3, Resource arg4) {
        if(this.mCallback != null) {
            this.mCallback.onResourceLoaded(this.getResourceType(), arg3, arg4);
        }
    }

    protected void notifyResourceUnregistered(int arg3) {
        if(this.mCallback != null) {
            this.mCallback.onResourceUnregistered(this.getResourceType(), arg3);
        }
    }

    public abstract void preloadResource(int arg1);
}

