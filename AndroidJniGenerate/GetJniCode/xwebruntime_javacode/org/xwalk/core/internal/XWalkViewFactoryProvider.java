package org.xwalk.core.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class XWalkViewFactoryProvider {
    private XWalkViewRunQueue mRunQueue;

    public XWalkViewFactoryProvider(XWalkViewRunQueue arg1) {
        super();
        this.mRunQueue = arg1;
    }

    void addTask(Runnable arg2) {
        this.mRunQueue.addTask(arg2);
    }

    Object runOnUiThreadBlocking(Callable arg3) {
        return this.mRunQueue.runBlockingFuture(new FutureTask(arg3));
    }

    void runVoidTaskOnUiThreadBlocking(Runnable arg2) {
        this.mRunQueue.runVoidTaskOnUiThreadBlocking(arg2);
    }
}

