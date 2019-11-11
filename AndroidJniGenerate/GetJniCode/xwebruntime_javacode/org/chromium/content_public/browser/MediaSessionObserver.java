package org.chromium.content_public.browser;

import android.support.annotation.Nullable;
import java.util.Set;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.content_public.common.MediaMetadata;

public abstract class MediaSessionObserver {
    private MediaSessionImpl mMediaSession;

    protected MediaSessionObserver(MediaSession arg2) {
        super();
        if(!(arg2 instanceof MediaSessionImpl)) {
            return;
        }

        this.mMediaSession = ((MediaSessionImpl)arg2);
        this.mMediaSession.addObserver(this);
    }

    @Nullable public final MediaSession getMediaSession() {
        return this.mMediaSession;
    }

    public void mediaSessionActionsChanged(Set arg1) {
    }

    public void mediaSessionDestroyed() {
    }

    public void mediaSessionMetadataChanged(MediaMetadata arg1) {
    }

    public void mediaSessionStateChanged(boolean arg1, boolean arg2) {
    }

    public final void stopObserving() {
        if(this.mMediaSession == null) {
            return;
        }

        this.mMediaSession.removeObserver(this);
        this.mMediaSession = null;
    }
}

