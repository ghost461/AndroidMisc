package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

@RequiresApi(value=22) class MediaSessionCompatApi22 {
    MediaSessionCompatApi22() {
        super();
    }

    public static void setRatingType(Object arg0, int arg1) {
        ((MediaSession)arg0).setRatingType(arg1);
    }
}

