package android.support.v4.media.session;

import android.media.session.PlaybackState$Builder;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.Iterator;
import java.util.List;

@RequiresApi(value=22) class PlaybackStateCompatApi22 {
    PlaybackStateCompatApi22() {
        super();
    }

    public static Bundle getExtras(Object arg0) {
        return ((PlaybackState)arg0).getExtras();
    }

    public static Object newInstance(int arg8, long arg9, long arg11, float arg13, long arg14, CharSequence arg16, long arg17, List arg19, long arg20, Bundle arg22) {
        PlaybackState$Builder v7 = new PlaybackState$Builder();
        v7.setState(arg8, arg9, arg13, arg17);
        v7.setBufferedPosition(arg11);
        v7.setActions(arg14);
        v7.setErrorMessage(arg16);
        Iterator v0 = arg19.iterator();
        while(v0.hasNext()) {
            v7.addCustomAction(v0.next());
        }

        v7.setActiveQueueItemId(arg20);
        v7.setExtras(arg22);
        return v7.build();
    }
}

