package android.support.v4.media.session;

import android.media.session.PlaybackState$Builder;
import android.media.session.PlaybackState$CustomAction$Builder;
import android.media.session.PlaybackState$CustomAction;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.Iterator;
import java.util.List;

@RequiresApi(value=21) class PlaybackStateCompatApi21 {
    final class CustomAction {
        CustomAction() {
            super();
        }

        public static String getAction(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getAction();
        }

        public static Bundle getExtras(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getExtras();
        }

        public static int getIcon(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getIcon();
        }

        public static CharSequence getName(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getName();
        }

        public static Object newInstance(String arg1, CharSequence arg2, int arg3, Bundle arg4) {
            PlaybackState$CustomAction$Builder v0 = new PlaybackState$CustomAction$Builder(arg1, arg2, arg3);
            v0.setExtras(arg4);
            return v0.build();
        }
    }

    PlaybackStateCompatApi21() {
        super();
    }

    public static long getActions(Object arg2) {
        return ((PlaybackState)arg2).getActions();
    }

    public static long getActiveQueueItemId(Object arg2) {
        return ((PlaybackState)arg2).getActiveQueueItemId();
    }

    public static long getBufferedPosition(Object arg2) {
        return ((PlaybackState)arg2).getBufferedPosition();
    }

    public static List getCustomActions(Object arg0) {
        return ((PlaybackState)arg0).getCustomActions();
    }

    public static CharSequence getErrorMessage(Object arg0) {
        return ((PlaybackState)arg0).getErrorMessage();
    }

    public static long getLastPositionUpdateTime(Object arg2) {
        return ((PlaybackState)arg2).getLastPositionUpdateTime();
    }

    public static float getPlaybackSpeed(Object arg0) {
        return ((PlaybackState)arg0).getPlaybackSpeed();
    }

    public static long getPosition(Object arg2) {
        return ((PlaybackState)arg2).getPosition();
    }

    public static int getState(Object arg0) {
        return ((PlaybackState)arg0).getState();
    }

    public static Object newInstance(int arg8, long arg9, long arg11, float arg13, long arg14, CharSequence arg16, long arg17, List arg19, long arg20) {
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
        return v7.build();
    }
}

