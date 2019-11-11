package org.chromium.ui.resources.async;

import android.os.AsyncTask;
import android.util.SparseArray;
import java.util.concurrent.ExecutionException;
import org.chromium.base.TraceEvent;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader$ResourceLoaderCallback;
import org.chromium.ui.resources.ResourceLoader;

public class AsyncPreloadResourceLoader extends ResourceLoader {
    class AsyncLoadTask extends AsyncTask {
        private final int mResourceId;

        public AsyncLoadTask(AsyncPreloadResourceLoader arg1, int arg2) {
            AsyncPreloadResourceLoader.this = arg1;
            super();
            this.mResourceId = arg2;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected Resource doInBackground(Void[] arg2) {
            return AsyncPreloadResourceLoader.this.createResource(this.mResourceId);
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Resource)arg1));
        }

        protected void onPostExecute(Resource arg3) {
            if(AsyncPreloadResourceLoader.this.mOutstandingLoads.get(this.mResourceId) == null) {
                return;
            }

            AsyncPreloadResourceLoader.this.registerResource(arg3, this.mResourceId);
        }
    }

    public interface ResourceCreator {
        Resource create(int arg1);
    }

    private final ResourceCreator mCreator;
    private final SparseArray mOutstandingLoads;

    public AsyncPreloadResourceLoader(int arg1, ResourceLoaderCallback arg2, ResourceCreator arg3) {
        super(arg1, arg2);
        this.mOutstandingLoads = new SparseArray();
        this.mCreator = arg3;
    }

    static Resource access$000(AsyncPreloadResourceLoader arg0, int arg1) {
        return arg0.createResource(arg1);
    }

    static SparseArray access$100(AsyncPreloadResourceLoader arg0) {
        return arg0.mOutstandingLoads;
    }

    static void access$200(AsyncPreloadResourceLoader arg0, Resource arg1, int arg2) {
        arg0.registerResource(arg1, arg2);
    }

    private Resource createResource(int arg2) {
        Resource v2_1;
        try {
            TraceEvent.begin("AsyncPreloadResourceLoader.createResource");
            v2_1 = this.mCreator.create(arg2);
        }
        catch(Throwable v2) {
            TraceEvent.end("AsyncPreloadResourceLoader.createResource");
            throw v2;
        }

        TraceEvent.end("AsyncPreloadResourceLoader.createResource");
        return v2_1;
    }

    public void loadResource(int arg3) {
        Object v0 = this.mOutstandingLoads.get(arg3);
        if(v0 != null && !((AsyncLoadTask)v0).cancel(false)) {
            Resource v1 = null;
            try {
                this.registerResource(((AsyncLoadTask)v0).get(), arg3);
            }
            catch(ExecutionException ) {
                this.notifyLoadFinished(arg3, v1);
            }
            catch(InterruptedException ) {
                this.notifyLoadFinished(arg3, v1);
            }

            return;
        }

        this.registerResource(this.createResource(arg3), arg3);
    }

    public void preloadResource(int arg4) {
        if(this.mOutstandingLoads.get(arg4) != null) {
            return;
        }

        AsyncLoadTask v0 = new AsyncLoadTask(this, arg4);
        v0.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null);
        this.mOutstandingLoads.put(arg4, v0);
    }

    private void registerResource(Resource arg1, int arg2) {
        this.notifyLoadFinished(arg2, arg1);
        if(arg1 != null) {
            arg1.getBitmap().recycle();
        }

        this.mOutstandingLoads.remove(arg2);
    }
}

