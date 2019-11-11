package org.chromium.ui.resources.statics;

import android.content.res.Resources;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader$ResourceLoaderCallback;
import org.chromium.ui.resources.async.AsyncPreloadResourceLoader$ResourceCreator;
import org.chromium.ui.resources.async.AsyncPreloadResourceLoader;

public class StaticResourceLoader extends AsyncPreloadResourceLoader {
    public StaticResourceLoader(int arg2, ResourceLoaderCallback arg3, Resources arg4) {
        super(arg2, arg3, new ResourceCreator() {
            public Resource create(int arg3) {
                return StaticResource.create(this.val$resources, arg3, 0, 0);
            }
        });
    }
}

