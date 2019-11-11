package org.chromium.content.browser;

import android.content.ComponentCallbacks2;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import java.util.LinkedList;
import org.chromium.base.Log;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.base.process_launcher.ChildProcessConnection;

class BindingManagerImpl implements ComponentCallbacks2, BindingManager {
    private static final float MODERATE_BINDING_HIGH_REDUCE_RATIO = 0.5f;
    private static final float MODERATE_BINDING_LOW_REDUCE_RATIO = 0.25f;
    private static final long MODERATE_BINDING_POOL_CLEARER_DELAY_MILLIS = 10000;
    private static final String TAG = "cr_BindingManager";
    private final LinkedList mConnections;
    private final Runnable mDelayedClearer;
    private final int mMaxSize;
    private final boolean mOnTesting;

    static {
    }

    BindingManagerImpl(Context arg6, int arg7, boolean arg8) {
        super();
        this.mConnections = new LinkedList();
        Log.i("cr_BindingManager", "Moderate binding enabled: maxSize=%d", new Object[]{Integer.valueOf(arg7)});
        this.mOnTesting = arg8;
        this.mMaxSize = arg7;
        this.mDelayedClearer = new Runnable() {
            public void run() {
                Log.i("cr_BindingManager", "Release moderate connections: %d", new Object[]{Integer.valueOf(BindingManagerImpl.access$000(BindingManagerImpl.this).size())});
                if(!BindingManagerImpl.access$200(BindingManagerImpl.this)) {
                    RecordHistogram.recordCountHistogram("Android.ModerateBindingCount", BindingManagerImpl.access$000(BindingManagerImpl.this).size());
                }

                BindingManagerImpl.this.removeAllConnections();
            }
        };
        arg6.registerComponentCallbacks(((ComponentCallbacks)this));
    }

    static LinkedList access$000(BindingManagerImpl arg0) {
        return arg0.mConnections;
    }

    static void access$100(BindingManagerImpl arg0, float arg1) {
        arg0.reduce(arg1);
    }

    static boolean access$200(BindingManagerImpl arg0) {
        return arg0.mOnTesting;
    }

    private void addAndUseConnection(ChildProcessConnection arg3) {
        if(!this.mConnections.removeFirstOccurrence(arg3)) {
            arg3.addModerateBinding();
        }

        if(this.mConnections.size() == this.mMaxSize) {
            this.removeOldConnections(1);
        }

        this.mConnections.add(0, arg3);
    }

    public void dropRecency(ChildProcessConnection arg1) {
        this.removeConnection(arg1);
    }

    public void increaseRecency(ChildProcessConnection arg1) {
        this.addAndUseConnection(arg1);
    }

    public void onBroughtToForeground() {
        LauncherThread.removeCallbacks(this.mDelayedClearer);
    }

    public void onConfigurationChanged(Configuration arg1) {
    }

    public void onLowMemory() {
        LauncherThread.post(new Runnable() {
            public void run() {
                Log.i("cr_BindingManager", "onLowMemory: evict %d bindings", new Object[]{Integer.valueOf(BindingManagerImpl.this.mConnections.size())});
                BindingManagerImpl.this.removeAllConnections();
            }
        });
    }

    public void onSentToBackground() {
        if(this.mConnections.isEmpty()) {
            return;
        }

        LauncherThread.postDelayed(this.mDelayedClearer, 10000);
    }

    public void onTrimMemory(int arg2) {
        LauncherThread.post(new Runnable(arg2) {
            public void run() {
                Log.i("cr_BindingManager", "onTrimMemory: level=%d, size=%d", new Object[]{Integer.valueOf(this.val$level), Integer.valueOf(BindingManagerImpl.this.mConnections.size())});
                if(BindingManagerImpl.this.mConnections.isEmpty()) {
                    return;
                }

                if(this.val$level <= 5) {
                    BindingManagerImpl.this.reduce(0.25f);
                }
                else if(this.val$level <= 10) {
                    BindingManagerImpl.this.reduce(0.5f);
                }
                else if(this.val$level == 20) {
                    return;
                }
                else {
                    BindingManagerImpl.this.removeAllConnections();
                }
            }
        });
    }

    private void reduce(float arg7) {
        int v0 = this.mConnections.size();
        int v7 = ((int)((((float)v0)) * (1f - arg7)));
        Log.i("cr_BindingManager", "Reduce connections from %d to %d", new Object[]{Integer.valueOf(v0), Integer.valueOf(v7)});
        this.removeOldConnections(v0 - v7);
    }

    public void releaseAllModerateBindings() {
        this.removeAllConnections();
    }

    void removeAllConnections() {
        this.removeOldConnections(this.mConnections.size());
    }

    private void removeConnection(ChildProcessConnection arg2) {
        if(this.mConnections.removeFirstOccurrence(arg2)) {
            arg2.removeModerateBinding();
        }
    }

    private void removeOldConnections(int arg3) {
        int v0;
        for(v0 = 0; v0 < arg3; ++v0) {
            this.mConnections.removeLast().removeModerateBinding();
        }
    }
}

