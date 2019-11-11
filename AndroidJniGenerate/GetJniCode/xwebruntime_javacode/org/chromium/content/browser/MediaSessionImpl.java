package org.chromium.content.browser;

import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ObserverList$RewindableIterator;
import org.chromium.base.ObserverList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.MediaSession;
import org.chromium.content_public.browser.MediaSessionObserver;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.MediaMetadata;

@JNINamespace(value="content") public class MediaSessionImpl extends MediaSession {
    private long mNativeMediaSessionAndroid;
    private ObserverList mObservers;
    private RewindableIterator mObserversIterator;

    static {
    }

    private MediaSessionImpl(long arg1) {
        super();
        this.mNativeMediaSessionAndroid = arg1;
        this.mObservers = new ObserverList();
        this.mObserversIterator = this.mObservers.rewindableIterator();
    }

    public void addObserver(MediaSessionObserver arg2) {
        this.mObservers.addObserver(arg2);
    }

    @CalledByNative private static MediaSessionImpl create(long arg1) {
        return new MediaSessionImpl(arg1);
    }

    public void didReceiveAction(int arg3) {
        this.nativeDidReceiveAction(this.mNativeMediaSessionAndroid, arg3);
    }

    public static MediaSessionImpl fromWebContents(WebContents arg0) {
        return MediaSessionImpl.nativeGetMediaSessionFromWebContents(arg0);
    }

    public RewindableIterator getObserversForTesting() {
        return this.mObservers.rewindableIterator();
    }

    @CalledByNative private boolean hasObservers() {
        return this.mObservers.isEmpty() ^ 1;
    }

    @CalledByNative private void mediaSessionActionsChanged(int[] arg5) {
        HashSet v0 = new HashSet();
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.add(Integer.valueOf(arg5[v2]));
        }

        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().mediaSessionActionsChanged(((Set)v0));
        }
    }

    @CalledByNative private void mediaSessionDestroyed() {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().mediaSessionDestroyed();
        }

        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().stopObserving();
        }

        this.mObservers.clear();
        this.mNativeMediaSessionAndroid = 0;
    }

    @CalledByNative private void mediaSessionMetadataChanged(MediaMetadata arg2) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().mediaSessionMetadataChanged(arg2);
        }
    }

    @CalledByNative private void mediaSessionStateChanged(boolean arg2, boolean arg3) {
        this.mObserversIterator.rewind();
        while(this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().mediaSessionStateChanged(arg2, arg3);
        }
    }

    private native void nativeDidReceiveAction(long arg1, int arg2) {
    }

    private static native MediaSessionImpl nativeGetMediaSessionFromWebContents(WebContents arg0) {
    }

    private native void nativeResume(long arg1) {
    }

    private native void nativeSeekBackward(long arg1, long arg2) {
    }

    private native void nativeSeekForward(long arg1, long arg2) {
    }

    private native void nativeStop(long arg1) {
    }

    private native void nativeSuspend(long arg1) {
    }

    public void removeObserver(MediaSessionObserver arg2) {
        this.mObservers.removeObserver(arg2);
    }

    public void resume() {
        this.nativeResume(this.mNativeMediaSessionAndroid);
    }

    public void seekBackward(long arg3) {
        this.nativeSeekBackward(this.mNativeMediaSessionAndroid, arg3);
    }

    public void seekForward(long arg3) {
        this.nativeSeekForward(this.mNativeMediaSessionAndroid, arg3);
    }

    public void stop() {
        this.nativeStop(this.mNativeMediaSessionAndroid);
    }

    public void suspend() {
        this.nativeSuspend(this.mNativeMediaSessionAndroid);
    }
}

