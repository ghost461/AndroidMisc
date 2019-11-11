package org.chromium.content_public.browser;

import android.support.annotation.Nullable;
import org.chromium.base.ObserverList$RewindableIterator;
import org.chromium.base.VisibleForTesting;
import org.chromium.content.browser.MediaSessionImpl;

public abstract class MediaSession {
    public MediaSession() {
        super();
    }

    public abstract void didReceiveAction(int arg1);

    @Nullable public static MediaSession fromWebContents(WebContents arg0) {
        return MediaSessionImpl.fromWebContents(arg0);
    }

    @VisibleForTesting public abstract RewindableIterator getObserversForTesting();

    public abstract void resume();

    public abstract void seekBackward(long arg1);

    public abstract void seekForward(long arg1);

    public abstract void stop();

    public abstract void suspend();
}

