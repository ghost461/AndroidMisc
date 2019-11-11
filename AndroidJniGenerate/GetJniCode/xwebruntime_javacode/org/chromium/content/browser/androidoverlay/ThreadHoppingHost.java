package org.chromium.content.browser.androidoverlay;

import android.os.Handler;
import android.view.Surface;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Log;

class ThreadHoppingHost implements Host {
    private static final int CLEANUP_TIMEOUT_SECONDS = 2;
    private static final String TAG = "ThreadHoppingHost";
    private Handler mHandler;
    private final Host mHost;
    private final Semaphore mSemaphore;

    public ThreadHoppingHost(Host arg3) {
        super();
        this.mSemaphore = new Semaphore(0);
        this.mHandler = new Handler();
        this.mHost = arg3;
    }

    static Host access$000(ThreadHoppingHost arg0) {
        return arg0.mHost;
    }

    public void enforceClose() {
        this.mHandler.post(new Runnable() {
            public void run() {
                ThreadHoppingHost.this.mHost.enforceClose();
            }
        });
    }

    public void onClose() {
        this.mSemaphore.release(1);
    }

    public void onOverlayDestroyed() {
        this.mHandler.post(new Runnable() {
            public void run() {
                ThreadHoppingHost.this.mHost.onOverlayDestroyed();
            }
        });
    }

    public void onSurfaceReady(Surface arg3) {
        this.mHandler.post(new Runnable(arg3) {
            public void run() {
                ThreadHoppingHost.this.mHost.onSurfaceReady(this.val$surface);
            }
        });
    }

    public void waitForClose() {
        try {
        label_0:
            if(!this.mSemaphore.tryAcquire(2, TimeUnit.SECONDS)) {
                Log.d("ThreadHoppingHost", "Wait for semaphore timed out.");
            }
        }
        catch(InterruptedException ) {
            goto label_0;
        }
    }
}

