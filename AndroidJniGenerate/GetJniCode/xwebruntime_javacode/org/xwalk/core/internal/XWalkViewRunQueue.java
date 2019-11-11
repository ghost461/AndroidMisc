package org.xwalk.core.internal;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.chromium.base.ThreadUtils;

public class XWalkViewRunQueue {
    public interface ChromiumHasStartedCallable {
        boolean hasStarted();
    }

    private final ChromiumHasStartedCallable mChromiumHasStartedCallable;
    private final Queue mQueue;

    public XWalkViewRunQueue(ChromiumHasStartedCallable arg2) {
        super();
        this.mQueue = new ConcurrentLinkedQueue();
        this.mChromiumHasStartedCallable = arg2;
    }

    public void addTask(Runnable arg2) {
        this.mQueue.add(arg2);
        if(this.mChromiumHasStartedCallable.hasStarted()) {
            ThreadUtils.runOnUiThread(new Runnable() {
                public void run() {
                    XWalkViewRunQueue.this.drainQueue();
                }
            });
        }
    }

    public boolean chromiumHasStarted() {
        return this.mChromiumHasStartedCallable.hasStarted();
    }

    public void drainQueue() {
        if(this.mQueue != null) {
            if(this.mQueue.isEmpty()) {
            }
            else {
                Object v0;
                for(v0 = this.mQueue.poll(); v0 != null; v0 = this.mQueue.poll()) {
                    ((Runnable)v0).run();
                }

                return;
            }
        }
    }

    public Object runBlockingFuture(FutureTask arg4) {
        if(!this.chromiumHasStarted()) {
            throw new RuntimeException(new Throwable("Must be started before we block!"));
        }

        if(ThreadUtils.runningOnUiThread()) {
            throw new RuntimeException(new Throwable("This method should only be called off the UI thread"));
        }

        this.addTask(((Runnable)arg4));
        long v0 = 1;
        try {
            return arg4.get(v0, TimeUnit.SECONDS);
        }
        catch(Exception v4) {
            throw new RuntimeException(new Throwable(v4.getMessage()));
        }
        catch(TimeoutException ) {
            throw new RuntimeException(new Throwable("Probable deadlock detected due to WebView API being called on incorrect thread while the UI thread is blocked."));
        }
    }

    public Object runOnUiThreadBlocking(Callable arg2) {
        return this.runBlockingFuture(new FutureTask(arg2));
    }

    public void runVoidTaskOnUiThreadBlocking(Runnable arg3) {
        this.runBlockingFuture(new FutureTask(arg3, null));
    }
}

