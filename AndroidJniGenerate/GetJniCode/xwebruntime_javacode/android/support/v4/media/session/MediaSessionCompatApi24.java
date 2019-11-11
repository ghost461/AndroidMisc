package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(value=24) class MediaSessionCompatApi24 {
    public interface Callback extends android.support.v4.media.session.MediaSessionCompatApi23$Callback {
        void onPrepare();

        void onPrepareFromMediaId(String arg1, Bundle arg2);

        void onPrepareFromSearch(String arg1, Bundle arg2);

        void onPrepareFromUri(Uri arg1, Bundle arg2);
    }

    class CallbackProxy extends android.support.v4.media.session.MediaSessionCompatApi23$CallbackProxy {
        public CallbackProxy(Callback arg1) {
            super(((android.support.v4.media.session.MediaSessionCompatApi23$Callback)arg1));
        }

        public void onPrepare() {
            this.mCallback.onPrepare();
        }

        public void onPrepareFromMediaId(String arg2, Bundle arg3) {
            this.mCallback.onPrepareFromMediaId(arg2, arg3);
        }

        public void onPrepareFromSearch(String arg2, Bundle arg3) {
            this.mCallback.onPrepareFromSearch(arg2, arg3);
        }

        public void onPrepareFromUri(Uri arg2, Bundle arg3) {
            this.mCallback.onPrepareFromUri(arg2, arg3);
        }
    }

    private static final String TAG = "MediaSessionCompatApi24";

    MediaSessionCompatApi24() {
        super();
    }

    public static Object createCallback(Callback arg1) {
        return new CallbackProxy(arg1);
    }

    public static String getCallingPackage(Object arg4) {
        try {
            return arg4.getClass().getMethod("getCallingPackage").invoke(arg4);
        }
        catch(IllegalAccessException v4) {
            Log.e("MediaSessionCompatApi24", "Cannot execute MediaSession.getCallingPackage()", ((Throwable)v4));
            return null;
        }
    }
}

