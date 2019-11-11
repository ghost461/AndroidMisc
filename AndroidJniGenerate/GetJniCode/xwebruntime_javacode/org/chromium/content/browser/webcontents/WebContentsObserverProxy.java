package org.chromium.content.browser.webcontents;

import org.chromium.base.ObserverList$RewindableIterator;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContentsObserver;

@JNINamespace(value="content") class WebContentsObserverProxy extends WebContentsObserver {
    private long mNativeWebContentsObserverProxy;
    private final ObserverList mObservers;
    private final RewindableIterator mObserversIterator;

    static {
    }

    public WebContentsObserverProxy(WebContentsImpl arg3) {
        super();
        ThreadUtils.assertOnUiThread();
        this.mNativeWebContentsObserverProxy = this.nativeInit(arg3);
        this.mObservers = new ObserverList();
        this.mObserversIterator = this.mObservers.rewindableIterator();
    }

    void addObserver(WebContentsObserver arg2) {
        this.mObservers.addObserver(arg2);
    }

    @CalledByNative public void destroy() {
        ThreadUtils.assertOnUiThread();
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().destroy();
        }

        this.mObservers.clear();
        long v2 = 0;
        if(this.mNativeWebContentsObserverProxy != v2) {
            this.nativeDestroy(this.mNativeWebContentsObserverProxy);
            this.mNativeWebContentsObserverProxy = v2;
        }
    }

    @CalledByNative public void didAttachInterstitialPage() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didAttachInterstitialPage();
        }
    }

    @CalledByNative public void didChangeThemeColor(int arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didChangeThemeColor(arg2);
        }
    }

    @CalledByNative public void didDetachInterstitialPage() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didDetachInterstitialPage();
        }
    }

    @CalledByNative public void didFailLoad(boolean arg2, int arg3, String arg4, String arg5) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFailLoad(arg2, arg3, arg4, arg5);
        }
    }

    @CalledByNative public void didFinishLoad(long arg2, String arg4, boolean arg5) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFinishLoad(arg2, arg4, arg5);
        }
    }

    @CalledByNative public void didFinishNavigation(String arg14, boolean arg15, boolean arg16, boolean arg17, boolean arg18, boolean arg19, int arg20, int arg21, String arg22, int arg23) {
        WebContentsObserverProxy v0 = this;
        Integer v1 = arg20 == -1 ? null : Integer.valueOf(arg20);
        v0.mObserversIterator.rewind();
        while(v0.mObserversIterator.hasNext()) {
            v0.mObserversIterator.next().didFinishNavigation(arg14, arg15, arg16, arg17, arg18, arg19, v1, arg21, arg22, arg23);
        }
    }

    @CalledByNative public void didFirstVisuallyNonEmptyPaint() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFirstVisuallyNonEmptyPaint();
        }
    }

    @CalledByNative public void didStartLoading(String arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStartLoading(arg2);
        }
    }

    @CalledByNative public void didStartNavigation(String arg2, boolean arg3, boolean arg4, boolean arg5) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStartNavigation(arg2, arg3, arg4, arg5);
        }
    }

    @CalledByNative public void didStopLoading(String arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStopLoading(arg2);
        }
    }

    @CalledByNative public void documentAvailableInMainFrame() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().documentAvailableInMainFrame();
        }
    }

    @CalledByNative public void documentLoadedInFrame(long arg2, boolean arg4) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().documentLoadedInFrame(arg2, arg4);
        }
    }

    @CalledByNative public void hasEffectivelyFullscreenVideoChange(boolean arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().hasEffectivelyFullscreenVideoChange(arg2);
        }
    }

    boolean hasObservers() {
        return this.mObservers.isEmpty() ^ 1;
    }

    private native void nativeDestroy(long arg1) {
    }

    private native long nativeInit(WebContentsImpl arg1) {
    }

    @CalledByNative public void navigationEntriesDeleted() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().navigationEntriesDeleted();
        }
    }

    @CalledByNative public void navigationEntryCommitted() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().navigationEntryCommitted();
        }
    }

    void removeObserver(WebContentsObserver arg2) {
        this.mObservers.removeObserver(arg2);
    }

    @CalledByNative public void renderProcessGone(boolean arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().renderProcessGone(arg2);
        }
    }

    @CalledByNative public void renderViewReady() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().renderViewReady();
        }
    }

    @CalledByNative public void titleWasSet(String arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().titleWasSet(arg2);
        }
    }

    @CalledByNative public void wasHidden() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().wasHidden();
        }
    }

    @CalledByNative public void wasShown() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().wasShown();
        }
    }
}

