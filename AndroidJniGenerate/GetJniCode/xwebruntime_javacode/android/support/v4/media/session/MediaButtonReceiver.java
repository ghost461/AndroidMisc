package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver$PendingResult;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat$ConnectionCallback;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

public class MediaButtonReceiver extends BroadcastReceiver {
    class MediaButtonConnectionCallback extends ConnectionCallback {
        private final Context mContext;
        private final Intent mIntent;
        private MediaBrowserCompat mMediaBrowser;
        private final BroadcastReceiver$PendingResult mPendingResult;

        MediaButtonConnectionCallback(Context arg1, Intent arg2, BroadcastReceiver$PendingResult arg3) {
            super();
            this.mContext = arg1;
            this.mIntent = arg2;
            this.mPendingResult = arg3;
        }

        private void finish() {
            this.mMediaBrowser.disconnect();
            this.mPendingResult.finish();
        }

        public void onConnected() {
            try {
                new MediaControllerCompat(this.mContext, this.mMediaBrowser.getSessionToken()).dispatchMediaButtonEvent(this.mIntent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            }
            catch(RemoteException v0) {
                Log.e("MediaButtonReceiver", "Failed to create a media controller", ((Throwable)v0));
            }

            this.finish();
        }

        public void onConnectionFailed() {
            this.finish();
        }

        public void onConnectionSuspended() {
            this.finish();
        }

        void setMediaBrowser(MediaBrowserCompat arg1) {
            this.mMediaBrowser = arg1;
        }
    }

    private static final String TAG = "MediaButtonReceiver";

    public MediaButtonReceiver() {
        super();
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context arg1, long arg2) {
        ComponentName v0 = MediaButtonReceiver.getMediaButtonReceiverComponent(arg1);
        if(v0 == null) {
            Log.w("MediaButtonReceiver", "A unique media button receiver could not be found in the given context, so couldn\'t build a pending intent.");
            return null;
        }

        return MediaButtonReceiver.buildMediaButtonPendingIntent(arg1, v0, arg2);
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context arg2, ComponentName arg3, long arg4) {
        PendingIntent v0 = null;
        if(arg3 == null) {
            Log.w("MediaButtonReceiver", "The component name of media button receiver should be provided.");
            return v0;
        }

        int v1 = PlaybackStateCompat.toKeyCode(arg4);
        if(v1 == 0) {
            Log.w("MediaButtonReceiver", "Cannot build a media button pending intent with the given action: " + arg4);
            return v0;
        }

        Intent v4 = new Intent("android.intent.action.MEDIA_BUTTON");
        v4.setComponent(arg3);
        v4.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, v1));
        return PendingIntent.getBroadcast(arg2, v1, v4, 0);
    }

    static ComponentName getMediaButtonReceiverComponent(Context arg3) {
        Intent v0 = new Intent("android.intent.action.MEDIA_BUTTON");
        v0.setPackage(arg3.getPackageName());
        List v3 = arg3.getPackageManager().queryBroadcastReceivers(v0, 0);
        if(v3.size() == 1) {
            Object v3_1 = v3.get(0);
            return new ComponentName(((ResolveInfo)v3_1).activityInfo.packageName, ((ResolveInfo)v3_1).activityInfo.name);
        }

        if(v3.size() > 1) {
            Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        }

        return null;
    }

    private static ComponentName getServiceComponentByAction(Context arg3, String arg4) {
        PackageManager v0 = arg3.getPackageManager();
        Intent v1 = new Intent(arg4);
        v1.setPackage(arg3.getPackageName());
        List v0_1 = v0.queryIntentServices(v1, 0);
        if(v0_1.size() == 1) {
            Object v3 = v0_1.get(0);
            return new ComponentName(((ResolveInfo)v3).serviceInfo.packageName, ((ResolveInfo)v3).serviceInfo.name);
        }

        if(v0_1.isEmpty()) {
            return null;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected 1 service that handles ");
        v1_1.append(arg4);
        v1_1.append(", found ");
        v1_1.append(v0_1.size());
        throw new IllegalStateException(v1_1.toString());
    }

    public static KeyEvent handleIntent(MediaSessionCompat arg2, Intent arg3) {
        if(arg2 != null && arg3 != null && ("android.intent.action.MEDIA_BUTTON".equals(arg3.getAction()))) {
            if(!arg3.hasExtra("android.intent.extra.KEY_EVENT")) {
            }
            else {
                Parcelable v3 = arg3.getParcelableExtra("android.intent.extra.KEY_EVENT");
                arg2.getController().dispatchMediaButtonEvent(((KeyEvent)v3));
                return ((KeyEvent)v3);
            }
        }

        return null;
    }

    public void onReceive(Context arg4, Intent arg5) {
        if(arg5 != null && ("android.intent.action.MEDIA_BUTTON".equals(arg5.getAction()))) {
            if(!arg5.hasExtra("android.intent.extra.KEY_EVENT")) {
            }
            else {
                ComponentName v0 = MediaButtonReceiver.getServiceComponentByAction(arg4, "android.intent.action.MEDIA_BUTTON");
                if(v0 != null) {
                    arg5.setComponent(v0);
                    MediaButtonReceiver.startForegroundService(arg4, arg5);
                    return;
                }
                else {
                    v0 = MediaButtonReceiver.getServiceComponentByAction(arg4, "android.media.browse.MediaBrowserService");
                    if(v0 != null) {
                        BroadcastReceiver$PendingResult v1 = this.goAsync();
                        arg4 = arg4.getApplicationContext();
                        MediaButtonConnectionCallback v2 = new MediaButtonConnectionCallback(arg4, arg5, v1);
                        MediaBrowserCompat v5 = new MediaBrowserCompat(arg4, v0, ((ConnectionCallback)v2), null);
                        v2.setMediaBrowser(v5);
                        v5.connect();
                        return;
                    }
                    else {
                        throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
                    }
                }
            }
        }

        Log.d("MediaButtonReceiver", "Ignore unsupported intent: " + arg5);
    }

    private static void startForegroundService(Context arg2, Intent arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            arg2.startForegroundService(arg3);
        }
        else {
            arg2.startService(arg3);
        }
    }
}

