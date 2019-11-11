package org.xwalk.core.internal;

import android.os.Looper;
import android.util.Log;
import org.chromium.base.ThreadUtils;

public class XWalkViewShared {
    private static final String TAG = "XWalkViewShared";
    private XWalkContent mContent;
    private XWalkViewFactoryProvider mFactory;
    final Object mLock;
    private final XWalkViewRunQueue mRunQueue;
    private boolean mStarted;

    static {
    }

    public XWalkViewShared() {
        super();
        this.mLock = new Object();
        this.mRunQueue = new XWalkViewRunQueue(new XWalkViewShared$$Lambda$0(this));
    }

    protected boolean checkNeedsPost() {
        int v0 = ThreadUtils.runningOnUiThread() ^ 1;
        if(v0 == 0 && this.mContent == null) {
            throw new IllegalStateException("XwalkContents must be created if we are not posting!");
        }

        return ((boolean)v0);
    }

    public void checkThread() {
        if(!ThreadUtils.runningOnUiThread()) {
            ThreadUtils.postOnUiThread(new Runnable(this.createThreadException()) {
                public void run() {
                    throw this.val$threadViolation;
                }
            });
            throw this.createThreadException();
        }
    }

    private RuntimeException createThreadException() {
        return new IllegalStateException("Calling View methods on another thread than the UI thread.");
    }

    void ensureChromiumStartedLocked(boolean arg4) {
        if(this.mStarted) {
            return;
        }

        Looper v4 = !arg4 ? Looper.myLooper() : Looper.getMainLooper();
        String v0 = "XWalkViewShared";
        StringBuilder v1 = new StringBuilder();
        v1.append("Binding Chromium to ");
        String v2 = Looper.getMainLooper().equals(v4) ? "main" : "background";
        v1.append(v2);
        v1.append(" looper ");
        v1.append(v4);
        Log.v(v0, v1.toString());
        ThreadUtils.setUiThread(v4);
        if(ThreadUtils.runningOnUiThread()) {
            this.startChromiumLocked();
            return;
        }

        this.mStarted = true;
    }

    XWalkViewRunQueue getRunQueue() {
        return this.mRunQueue;
    }

    boolean hasStarted() {
        return this.mStarted;
    }

    public void setContentsOnUiThread(XWalkContent arg2) {
        if(this.mContent != null) {
            throw new RuntimeException("Cannot create multiple AwContents for the same SharedWebViewChromium");
        }

        this.mContent = arg2;
    }

    public void setFactory(XWalkViewFactoryProvider arg1) {
        this.mFactory = arg1;
    }

    protected void startChromiumLocked() {
        this.mLock.notifyAll();
        if(this.mStarted) {
            return;
        }

        this.mStarted = true;
        this.mRunQueue.drainQueue();
    }

    void startYourEngines(boolean arg2) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureChromiumStartedLocked(arg2);
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }
}

