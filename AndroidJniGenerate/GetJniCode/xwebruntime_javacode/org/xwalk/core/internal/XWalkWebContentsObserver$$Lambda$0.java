package org.xwalk.core.internal;

final class XWalkWebContentsObserver$$Lambda$0 implements Runnable {
    private final XWalkWebContentsObserver arg$1;
    private final String arg$2;

    XWalkWebContentsObserver$$Lambda$0(XWalkWebContentsObserver arg1, String arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        this.arg$1.lambda$didFinishNavigation$0$XWalkWebContentsObserver(this.arg$2);
    }
}

