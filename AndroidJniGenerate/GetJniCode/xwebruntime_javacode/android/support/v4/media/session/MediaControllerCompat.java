package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder$DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.SupportActivity$ExtraData;
import android.support.v4.app.SupportActivity;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class MediaControllerCompat {
    public abstract class Callback implements IBinder$DeathRecipient {
        class MessageHandler extends Handler {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_REPEAT_MODE = 9;
            private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
            private static final int MSG_UPDATE_SHUFFLE_MODE_DEPRECATED = 10;
            private static final int MSG_UPDATE_VOLUME = 4;
            boolean mRegistered;

            MessageHandler(Callback arg1, Looper arg2) {
                Callback.this = arg1;
                super(arg2);
                this.mRegistered = false;
            }

            public void handleMessage(Message arg3) {
                if(!this.mRegistered) {
                    return;
                }

                switch(arg3.what) {
                    case 1: {
                        Callback.this.onSessionEvent(arg3.obj, arg3.getData());
                        break;
                    }
                    case 2: {
                        Callback.this.onPlaybackStateChanged(arg3.obj);
                        break;
                    }
                    case 3: {
                        Callback.this.onMetadataChanged(arg3.obj);
                        break;
                    }
                    case 4: {
                        Callback.this.onAudioInfoChanged(arg3.obj);
                        break;
                    }
                    case 5: {
                        Callback.this.onQueueChanged(arg3.obj);
                        break;
                    }
                    case 6: {
                        Callback.this.onQueueTitleChanged(arg3.obj);
                        break;
                    }
                    case 7: {
                        Callback.this.onExtrasChanged(arg3.obj);
                        break;
                    }
                    case 8: {
                        Callback.this.onSessionDestroyed();
                        break;
                    }
                    case 9: {
                        Callback.this.onRepeatModeChanged(arg3.obj.intValue());
                        break;
                    }
                    case 10: {
                        Callback.this.onShuffleModeChanged(arg3.obj.booleanValue());
                        break;
                    }
                    case 11: {
                        Callback.this.onCaptioningEnabledChanged(arg3.obj.booleanValue());
                        break;
                    }
                    case 12: {
                        Callback.this.onShuffleModeChanged(arg3.obj.intValue());
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

        class StubApi21 implements android.support.v4.media.session.MediaControllerCompatApi21$Callback {
            private final WeakReference mCallback;

            StubApi21(Callback arg2) {
                super();
                this.mCallback = new WeakReference(arg2);
            }

            public void onAudioInfoChanged(int arg9, int arg10, int arg11, int arg12, int arg13) {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).onAudioInfoChanged(new PlaybackInfo(arg9, arg10, arg11, arg12, arg13));
                }
            }

            public void onExtrasChanged(Bundle arg2) {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).onExtrasChanged(arg2);
                }
            }

            public void onMetadataChanged(Object arg2) {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(arg2));
                }
            }

            public void onPlaybackStateChanged(Object arg3) {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    if(((Callback)v0).mHasExtraCallback) {
                    }
                    else {
                        ((Callback)v0).onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(arg3));
                    }
                }
            }

            public void onQueueChanged(List arg2) {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).onQueueChanged(QueueItem.fromQueueItemList(arg2));
                }
            }

            public void onQueueTitleChanged(CharSequence arg2) {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).onQueueTitleChanged(arg2);
                }
            }

            public void onSessionDestroyed() {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).onSessionDestroyed();
                }
            }

            public void onSessionEvent(String arg4, Bundle arg5) {
                Object v0 = this.mCallback.get();
                if(v0 != null && (!((Callback)v0).mHasExtraCallback || Build$VERSION.SDK_INT >= 23)) {
                    ((Callback)v0).onSessionEvent(arg4, arg5);
                }
            }
        }

        class StubCompat extends Stub {
            private final WeakReference mCallback;

            StubCompat(Callback arg2) {
                super();
                this.mCallback = new WeakReference(arg2);
            }

            public void onCaptioningEnabledChanged(boolean arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(11, Boolean.valueOf(arg4), null);
                }
            }

            public void onEvent(String arg3, Bundle arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(1, arg3, arg4);
                }
            }

            public void onExtrasChanged(Bundle arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(7, arg4, null);
                }
            }

            public void onMetadataChanged(MediaMetadataCompat arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(3, arg4, null);
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(2, arg4, null);
                }
            }

            public void onQueueChanged(List arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(5, arg4, null);
                }
            }

            public void onQueueTitleChanged(CharSequence arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(6, arg4, null);
                }
            }

            public void onRepeatModeChanged(int arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(9, Integer.valueOf(arg4), null);
                }
            }

            public void onSessionDestroyed() throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(8, null, null);
                }
            }

            public void onShuffleModeChanged(int arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(12, Integer.valueOf(arg4), null);
                }
            }

            public void onShuffleModeChangedDeprecated(boolean arg4) throws RemoteException {
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    ((Callback)v0).postToHandler(10, Boolean.valueOf(arg4), null);
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo arg10) throws RemoteException {
                Object v8_1;
                Object v0 = this.mCallback.get();
                if(v0 != null) {
                    Bundle v1 = null;
                    if(arg10 != null) {
                        PlaybackInfo v8 = new PlaybackInfo(arg10.volumeType, arg10.audioStream, arg10.controlType, arg10.maxVolume, arg10.currentVolume);
                    }
                    else {
                        v8_1 = v1;
                    }

                    ((Callback)v0).postToHandler(4, v8_1, v1);
                }
            }
        }

        private final Object mCallbackObj;
        MessageHandler mHandler;
        boolean mHasExtraCallback;

        public Callback() {
            super();
            this.mCallbackObj = Build$VERSION.SDK_INT >= 21 ? MediaControllerCompatApi21.createCallback(new StubApi21(this)) : new StubCompat(this);
        }

        static Object access$000(Callback arg0) {
            return arg0.mCallbackObj;
        }

        public void binderDied() {
            this.onSessionDestroyed();
        }

        public void onAudioInfoChanged(PlaybackInfo arg1) {
        }

        public void onCaptioningEnabledChanged(boolean arg1) {
        }

        public void onExtrasChanged(Bundle arg1) {
        }

        public void onMetadataChanged(MediaMetadataCompat arg1) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat arg1) {
        }

        public void onQueueChanged(List arg1) {
        }

        public void onQueueTitleChanged(CharSequence arg1) {
        }

        public void onRepeatModeChanged(int arg1) {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String arg1, Bundle arg2) {
        }

        public void onShuffleModeChanged(int arg1) {
        }

        @Deprecated public void onShuffleModeChanged(boolean arg1) {
        }

        void postToHandler(int arg2, Object arg3, Bundle arg4) {
            if(this.mHandler != null) {
                Message v2 = this.mHandler.obtainMessage(arg2, arg3);
                v2.setData(arg4);
                v2.sendToTarget();
            }
        }

        void setHandler(Handler arg2) {
            if(arg2 != null) {
                this.mHandler = new MessageHandler(this, arg2.getLooper());
                this.mHandler.mRegistered = true;
            }
            else if(this.mHandler != null) {
                this.mHandler.mRegistered = false;
                this.mHandler.removeCallbacksAndMessages(null);
                this.mHandler = null;
            }
        }
    }

    class MediaControllerExtraData extends ExtraData {
        private final MediaControllerCompat mMediaController;

        MediaControllerExtraData(MediaControllerCompat arg1) {
            super();
            this.mMediaController = arg1;
        }

        MediaControllerCompat getMediaController() {
            return this.mMediaController;
        }
    }

    interface MediaControllerImpl {
        void addQueueItem(MediaDescriptionCompat arg1);

        void addQueueItem(MediaDescriptionCompat arg1, int arg2);

        void adjustVolume(int arg1, int arg2);

        boolean dispatchMediaButtonEvent(KeyEvent arg1);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isShuffleModeEnabled();

        void registerCallback(Callback arg1, Handler arg2);

        void removeQueueItem(MediaDescriptionCompat arg1);

        void sendCommand(String arg1, Bundle arg2, ResultReceiver arg3);

        void setVolumeTo(int arg1, int arg2);

        void unregisterCallback(Callback arg1);
    }

    @RequiresApi(value=21) class MediaControllerImplApi21 implements MediaControllerImpl {
        class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference mMediaControllerImpl;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 arg1, Handler arg2) {
                super(arg2);
                this.mMediaControllerImpl = new WeakReference(arg1);
            }

            protected void onReceiveResult(int arg2, Bundle arg3) {
                Object v2 = this.mMediaControllerImpl.get();
                if(v2 != null) {
                    if(arg3 == null) {
                    }
                    else {
                        ((MediaControllerImplApi21)v2).mExtraBinder = android.support.v4.media.session.IMediaSession$Stub.asInterface(BundleCompat.getBinder(arg3, "android.support.v4.media.session.EXTRA_BINDER"));
                        ((MediaControllerImplApi21)v2).processPendingCallbacks();
                        return;
                    }
                }
            }
        }

        class ExtraCallback extends StubCompat {
            ExtraCallback(Callback arg1) {
                super(arg1);
            }

            public void onExtrasChanged(Bundle arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void onMetadataChanged(MediaMetadataCompat arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueChanged(List arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueTitleChanged(CharSequence arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void onSessionDestroyed() throws RemoteException {
                throw new AssertionError();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo arg1) throws RemoteException {
                throw new AssertionError();
            }
        }

        private HashMap mCallbackMap;
        protected final Object mControllerObj;
        private IMediaSession mExtraBinder;
        private final List mPendingCallbacks;

        public MediaControllerImplApi21(Context arg2, Token arg3) throws RemoteException {
            super();
            this.mPendingCallbacks = new ArrayList();
            this.mCallbackMap = new HashMap();
            this.mControllerObj = MediaControllerCompatApi21.fromToken(arg2, arg3.getToken());
            if(this.mControllerObj == null) {
                throw new RemoteException();
            }

            this.mExtraBinder = arg3.getExtraBinder();
            if(this.mExtraBinder == null) {
                this.requestExtraBinder();
            }
        }

        public MediaControllerImplApi21(Context arg2, MediaSessionCompat arg3) {
            super();
            this.mPendingCallbacks = new ArrayList();
            this.mCallbackMap = new HashMap();
            this.mControllerObj = MediaControllerCompatApi21.fromToken(arg2, arg3.getSessionToken().getToken());
            this.mExtraBinder = arg3.getSessionToken().getExtraBinder();
            if(this.mExtraBinder == null) {
                this.requestExtraBinder();
            }
        }

        static IMediaSession access$202(MediaControllerImplApi21 arg0, IMediaSession arg1) {
            arg0.mExtraBinder = arg1;
            return arg1;
        }

        static void access$300(MediaControllerImplApi21 arg0) {
            arg0.processPendingCallbacks();
        }

        public void addQueueItem(MediaDescriptionCompat arg7) {
            if((this.getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn\'t support queue management operations");
            }

            Bundle v0 = new Bundle();
            v0.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", ((Parcelable)arg7));
            this.sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", v0, null);
        }

        public void addQueueItem(MediaDescriptionCompat arg7, int arg8) {
            if((this.getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn\'t support queue management operations");
            }

            Bundle v0 = new Bundle();
            v0.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", ((Parcelable)arg7));
            v0.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", arg8);
            this.sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", v0, null);
        }

        public void adjustVolume(int arg2, int arg3) {
            MediaControllerCompatApi21.adjustVolume(this.mControllerObj, arg2, arg3);
        }

        public boolean dispatchMediaButtonEvent(KeyEvent arg2) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, arg2);
        }

        public Bundle getExtras() {
            return MediaControllerCompatApi21.getExtras(this.mControllerObj);
        }

        public long getFlags() {
            return MediaControllerCompatApi21.getFlags(this.mControllerObj);
        }

        public Object getMediaController() {
            return this.mControllerObj;
        }

        public MediaMetadataCompat getMetadata() {
            Object v0 = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
            MediaMetadataCompat v0_1 = v0 != null ? MediaMetadataCompat.fromMediaMetadata(v0) : null;
            return v0_1;
        }

        public String getPackageName() {
            return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
        }

        public PlaybackInfo getPlaybackInfo() {
            Object v0 = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
            PlaybackInfo v7 = v0 != null ? new PlaybackInfo(android.support.v4.media.session.MediaControllerCompatApi21$PlaybackInfo.getPlaybackType(v0), android.support.v4.media.session.MediaControllerCompatApi21$PlaybackInfo.getLegacyAudioStream(v0), android.support.v4.media.session.MediaControllerCompatApi21$PlaybackInfo.getVolumeControl(v0), android.support.v4.media.session.MediaControllerCompatApi21$PlaybackInfo.getMaxVolume(v0), android.support.v4.media.session.MediaControllerCompatApi21$PlaybackInfo.getCurrentVolume(v0)) : null;
            return v7;
        }

        public PlaybackStateCompat getPlaybackState() {
            if(this.mExtraBinder != null) {
                try {
                    return this.mExtraBinder.getPlaybackState();
                }
                catch(RemoteException v0) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", ((Throwable)v0));
                }
            }

            Object v0_1 = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
            PlaybackStateCompat v0_2 = v0_1 != null ? PlaybackStateCompat.fromPlaybackState(v0_1) : null;
            return v0_2;
        }

        public List getQueue() {
            List v0 = MediaControllerCompatApi21.getQueue(this.mControllerObj);
            return v0 != null ? QueueItem.fromQueueItemList(v0) : null;
        }

        public CharSequence getQueueTitle() {
            return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
        }

        public int getRatingType() {
            if(Build$VERSION.SDK_INT < 22 && this.mExtraBinder != null) {
                try {
                    return this.mExtraBinder.getRatingType();
                }
                catch(RemoteException v0) {
                    Log.e("MediaControllerCompat", "Dead object in getRatingType.", ((Throwable)v0));
                }
            }

            return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
        }

        public int getRepeatMode() {
            if(this.mExtraBinder != null) {
                try {
                    return this.mExtraBinder.getRepeatMode();
                }
                catch(RemoteException v0) {
                    Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", ((Throwable)v0));
                }
            }

            return 0;
        }

        public PendingIntent getSessionActivity() {
            return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
        }

        public int getShuffleMode() {
            if(this.mExtraBinder != null) {
                try {
                    return this.mExtraBinder.getShuffleMode();
                }
                catch(RemoteException v0) {
                    Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", ((Throwable)v0));
                }
            }

            return 0;
        }

        public TransportControls getTransportControls() {
            TransportControlsApi21 v1;
            Object v0 = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if(v0 != null) {
                v1 = new TransportControlsApi21(v0);
            }
            else {
                TransportControls v1_1 = null;
            }

            return ((TransportControls)v1);
        }

        public boolean isCaptioningEnabled() {
            if(this.mExtraBinder != null) {
                try {
                    return this.mExtraBinder.isCaptioningEnabled();
                }
                catch(RemoteException v0) {
                    Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", ((Throwable)v0));
                }
            }

            return 0;
        }

        public boolean isShuffleModeEnabled() {
            if(this.mExtraBinder != null) {
                try {
                    return this.mExtraBinder.isShuffleModeEnabledDeprecated();
                }
                catch(RemoteException v0) {
                    Log.e("MediaControllerCompat", "Dead object in isShuffleModeEnabled.", ((Throwable)v0));
                }
            }

            return 0;
        }

        private void processPendingCallbacks() {
            if(this.mExtraBinder == null) {
                return;
            }

            List v0 = this.mPendingCallbacks;
            __monitor_enter(v0);
            try {
                Iterator v1_1 = this.mPendingCallbacks.iterator();
                while(true) {
                    if(!v1_1.hasNext()) {
                        goto label_23;
                    }

                    Object v2 = v1_1.next();
                    ExtraCallback v3 = new ExtraCallback(((Callback)v2));
                    this.mCallbackMap.put(v2, v3);
                    ((Callback)v2).mHasExtraCallback = true;
                    try {
                        this.mExtraBinder.registerCallbackListener(((IMediaControllerCallback)v3));
                        continue;
                    }
                    catch(RemoteException v1_2) {
                        try {
                            Log.e("MediaControllerCompat", "Dead object in registerCallback.", ((Throwable)v1_2));
                        label_23:
                            this.mPendingCallbacks.clear();
                            __monitor_exit(v0);
                            return;
                        label_28:
                            __monitor_exit(v0);
                            break;
                        }
                        catch(Throwable v1) {
                            goto label_28;
                        }
                    }
                }
            }
            catch(Throwable v1) {
                goto label_28;
            }

            throw v1;
        }

        public final void registerCallback(Callback arg3, Handler arg4) {
            String v4_1;
            MediaControllerCompatApi21.registerCallback(this.mControllerObj, arg3.mCallbackObj, arg4);
            if(this.mExtraBinder != null) {
                ExtraCallback v4 = new ExtraCallback(arg3);
                this.mCallbackMap.put(arg3, v4);
                arg3.mHasExtraCallback = true;
                try {
                    this.mExtraBinder.registerCallbackListener(((IMediaControllerCallback)v4));
                }
                catch(RemoteException v3) {
                    v4_1 = "MediaControllerCompat";
                    Log.e(v4_1, "Dead object in registerCallback.", ((Throwable)v3));
                }

                return;
            }

            List v4_2 = this.mPendingCallbacks;
            __monitor_enter(v4_2);
            try {
                this.mPendingCallbacks.add(arg3);
                __monitor_exit(v4_2);
                return;
            label_26:
                __monitor_exit(((ExtraCallback)v4_1));
            }
            catch(Throwable v3_1) {
                goto label_26;
            }

            throw v3_1;
        }

        public void removeQueueItem(MediaDescriptionCompat arg7) {
            if((this.getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn\'t support queue management operations");
            }

            Bundle v0 = new Bundle();
            v0.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", ((Parcelable)arg7));
            this.sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", v0, null);
        }

        private void requestExtraBinder() {
            this.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
        }

        public void sendCommand(String arg2, Bundle arg3, ResultReceiver arg4) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, arg2, arg3, arg4);
        }

        public void setVolumeTo(int arg2, int arg3) {
            MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, arg2, arg3);
        }

        public final void unregisterCallback(Callback arg3) {
            String v0_2;
            MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, arg3.mCallbackObj);
            if(this.mExtraBinder != null) {
                try {
                    HashMap v0 = this.mCallbackMap;
                    Object v3_1 = v0.remove(arg3);
                    if(v3_1 == null) {
                        return;
                    }

                    IMediaSession v0_1 = this.mExtraBinder;
                    v0_1.unregisterCallbackListener(((IMediaControllerCallback)v3_1));
                }
                catch(RemoteException v3) {
                    v0_2 = "MediaControllerCompat";
                    Log.e(v0_2, "Dead object in unregisterCallback.", ((Throwable)v3));
                }

                return;
            }

            List v0_3 = this.mPendingCallbacks;
            __monitor_enter(v0_3);
            try {
                this.mPendingCallbacks.remove(arg3);
                __monitor_exit(v0_3);
                return;
            label_23:
                __monitor_exit(((HashMap)v0_2));
            }
            catch(Throwable v3_2) {
                goto label_23;
            }

            throw v3_2;
        }
    }

    @RequiresApi(value=23) class MediaControllerImplApi23 extends MediaControllerImplApi21 {
        public MediaControllerImplApi23(Context arg1, Token arg2) throws RemoteException {
            super(arg1, arg2);
        }

        public MediaControllerImplApi23(Context arg1, MediaSessionCompat arg2) {
            super(arg1, arg2);
        }

        public TransportControls getTransportControls() {
            TransportControls v1_1;
            Object v0 = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if(v0 != null) {
                TransportControlsApi23 v1 = new TransportControlsApi23(v0);
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    @RequiresApi(value=24) class MediaControllerImplApi24 extends MediaControllerImplApi23 {
        public MediaControllerImplApi24(Context arg1, Token arg2) throws RemoteException {
            super(arg1, arg2);
        }

        public MediaControllerImplApi24(Context arg1, MediaSessionCompat arg2) {
            super(arg1, arg2);
        }

        public TransportControls getTransportControls() {
            TransportControls v1_1;
            Object v0 = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if(v0 != null) {
                TransportControlsApi24 v1 = new TransportControlsApi24(v0);
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    class MediaControllerImplBase implements MediaControllerImpl {
        private IMediaSession mBinder;
        private TransportControls mTransportControls;

        public MediaControllerImplBase(Token arg1) {
            super();
            this.mBinder = android.support.v4.media.session.IMediaSession$Stub.asInterface(arg1.getToken());
        }

        public void addQueueItem(MediaDescriptionCompat arg7) {
            try {
                if((this.mBinder.getFlags() & 4) == 0) {
                    throw new UnsupportedOperationException("This session doesn\'t support queue management operations");
                }

                this.mBinder.addQueueItem(arg7);
            }
            catch(RemoteException v7) {
                Log.e("MediaControllerCompat", "Dead object in addQueueItem.", ((Throwable)v7));
            }
        }

        public void addQueueItem(MediaDescriptionCompat arg7, int arg8) {
            try {
                if((this.mBinder.getFlags() & 4) == 0) {
                    throw new UnsupportedOperationException("This session doesn\'t support queue management operations");
                }

                this.mBinder.addQueueItemAt(arg7, arg8);
            }
            catch(RemoteException v7) {
                Log.e("MediaControllerCompat", "Dead object in addQueueItemAt.", ((Throwable)v7));
            }
        }

        public void adjustVolume(int arg3, int arg4) {
            try {
                this.mBinder.adjustVolume(arg3, arg4, null);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in adjustVolume.", ((Throwable)v3));
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent arg3) {
            if(arg3 == null) {
                throw new IllegalArgumentException("event may not be null.");
            }

            try {
                this.mBinder.sendMediaButton(arg3);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", ((Throwable)v3));
            }

            return 0;
        }

        public Bundle getExtras() {
            try {
                return this.mBinder.getExtras();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getExtras.", ((Throwable)v0));
                return null;
            }
        }

        public long getFlags() {
            try {
                return this.mBinder.getFlags();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getFlags.", ((Throwable)v0));
                return 0;
            }
        }

        public Object getMediaController() {
            return null;
        }

        public MediaMetadataCompat getMetadata() {
            try {
                return this.mBinder.getMetadata();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", ((Throwable)v0));
                return null;
            }
        }

        public String getPackageName() {
            try {
                return this.mBinder.getPackageName();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getPackageName.", ((Throwable)v0));
                return null;
            }
        }

        public PlaybackInfo getPlaybackInfo() {
            PlaybackInfo v1;
            try {
                ParcelableVolumeInfo v0_1 = this.mBinder.getVolumeAttributes();
                v1 = new PlaybackInfo(v0_1.volumeType, v0_1.audioStream, v0_1.controlType, v0_1.maxVolume, v0_1.currentVolume);
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo.", ((Throwable)v0));
                return null;
            }

            return v1;
        }

        public PlaybackStateCompat getPlaybackState() {
            try {
                return this.mBinder.getPlaybackState();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", ((Throwable)v0));
                return null;
            }
        }

        public List getQueue() {
            try {
                return this.mBinder.getQueue();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getQueue.", ((Throwable)v0));
                return null;
            }
        }

        public CharSequence getQueueTitle() {
            try {
                return this.mBinder.getQueueTitle();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getQueueTitle.", ((Throwable)v0));
                return null;
            }
        }

        public int getRatingType() {
            try {
                return this.mBinder.getRatingType();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getRatingType.", ((Throwable)v0));
                return 0;
            }
        }

        public int getRepeatMode() {
            try {
                return this.mBinder.getRepeatMode();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", ((Throwable)v0));
                return 0;
            }
        }

        public PendingIntent getSessionActivity() {
            try {
                return this.mBinder.getLaunchPendingIntent();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", ((Throwable)v0));
                return null;
            }
        }

        public int getShuffleMode() {
            try {
                return this.mBinder.getShuffleMode();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", ((Throwable)v0));
                return 0;
            }
        }

        public TransportControls getTransportControls() {
            if(this.mTransportControls == null) {
                this.mTransportControls = new TransportControlsBase(this.mBinder);
            }

            return this.mTransportControls;
        }

        public boolean isCaptioningEnabled() {
            try {
                return this.mBinder.isCaptioningEnabled();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", ((Throwable)v0));
                return 0;
            }
        }

        public boolean isShuffleModeEnabled() {
            try {
                return this.mBinder.isShuffleModeEnabledDeprecated();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in isShuffleModeEnabled.", ((Throwable)v0));
                return 0;
            }
        }

        public void registerCallback(Callback arg3, Handler arg4) {
            if(arg3 == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }

            try {
                this.mBinder.asBinder().linkToDeath(((IBinder$DeathRecipient)arg3), 0);
                this.mBinder.registerCallbackListener(arg3.mCallbackObj);
            }
            catch(RemoteException v4) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback.", ((Throwable)v4));
                arg3.onSessionDestroyed();
            }
        }

        public void removeQueueItem(MediaDescriptionCompat arg7) {
            try {
                if((this.mBinder.getFlags() & 4) == 0) {
                    throw new UnsupportedOperationException("This session doesn\'t support queue management operations");
                }

                this.mBinder.removeQueueItem(arg7);
            }
            catch(RemoteException v7) {
                Log.e("MediaControllerCompat", "Dead object in removeQueueItem.", ((Throwable)v7));
            }
        }

        public void sendCommand(String arg3, Bundle arg4, ResultReceiver arg5) {
            try {
                this.mBinder.sendCommand(arg3, arg4, new ResultReceiverWrapper(arg5));
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in sendCommand.", ((Throwable)v3));
            }
        }

        public void setVolumeTo(int arg3, int arg4) {
            try {
                this.mBinder.setVolumeTo(arg3, arg4, null);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in setVolumeTo.", ((Throwable)v3));
            }
        }

        public void unregisterCallback(Callback arg3) {
            if(arg3 == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }

            try {
                this.mBinder.unregisterCallbackListener(arg3.mCallbackObj);
                this.mBinder.asBinder().unlinkToDeath(((IBinder$DeathRecipient)arg3), 0);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", ((Throwable)v3));
            }
        }
    }

    public final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int arg1, int arg2, int arg3, int arg4, int arg5) {
            super();
            this.mPlaybackType = arg1;
            this.mAudioStream = arg2;
            this.mVolumeControl = arg3;
            this.mMaxVolume = arg4;
            this.mCurrentVolume = arg5;
        }

        public int getAudioStream() {
            return this.mAudioStream;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getVolumeControl() {
            return this.mVolumeControl;
        }
    }

    public abstract class TransportControls {
        public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

        TransportControls() {
            super();
        }

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String arg1, Bundle arg2);

        public abstract void playFromSearch(String arg1, Bundle arg2);

        public abstract void playFromUri(Uri arg1, Bundle arg2);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String arg1, Bundle arg2);

        public abstract void prepareFromSearch(String arg1, Bundle arg2);

        public abstract void prepareFromUri(Uri arg1, Bundle arg2);

        public abstract void rewind();

        public abstract void seekTo(long arg1);

        public abstract void sendCustomAction(CustomAction arg1, Bundle arg2);

        public abstract void sendCustomAction(String arg1, Bundle arg2);

        public abstract void setCaptioningEnabled(boolean arg1);

        public abstract void setRating(RatingCompat arg1);

        public abstract void setRating(RatingCompat arg1, Bundle arg2);

        public abstract void setRepeatMode(int arg1);

        public abstract void setShuffleMode(int arg1);

        @Deprecated public abstract void setShuffleModeEnabled(boolean arg1);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long arg1);

        public abstract void stop();
    }

    class TransportControlsApi21 extends TransportControls {
        protected final Object mControlsObj;

        public TransportControlsApi21(Object arg1) {
            super();
            this.mControlsObj = arg1;
        }

        public void fastForward() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.fastForward(this.mControlsObj);
        }

        public void pause() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.pause(this.mControlsObj);
        }

        public void play() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.play(this.mControlsObj);
        }

        public void playFromMediaId(String arg2, Bundle arg3) {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.playFromMediaId(this.mControlsObj, arg2, arg3);
        }

        public void playFromSearch(String arg2, Bundle arg3) {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.playFromSearch(this.mControlsObj, arg2, arg3);
        }

        public void playFromUri(Uri arg3, Bundle arg4) {
            if(arg3 != null) {
                if(Uri.EMPTY.equals(arg3)) {
                }
                else {
                    Bundle v0 = new Bundle();
                    v0.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", ((Parcelable)arg3));
                    v0.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", ((Parcelable)arg4));
                    this.sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", v0);
                    return;
                }
            }

            throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
        }

        public void prepare() {
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE", null);
        }

        public void prepareFromMediaId(String arg3, Bundle arg4) {
            Bundle v0 = new Bundle();
            v0.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", arg3);
            v0.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", arg4);
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", v0);
        }

        public void prepareFromSearch(String arg3, Bundle arg4) {
            Bundle v0 = new Bundle();
            v0.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", arg3);
            v0.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", arg4);
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", v0);
        }

        public void prepareFromUri(Uri arg3, Bundle arg4) {
            Bundle v0 = new Bundle();
            v0.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", ((Parcelable)arg3));
            v0.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", arg4);
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", v0);
        }

        public void rewind() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.rewind(this.mControlsObj);
        }

        public void seekTo(long arg2) {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.seekTo(this.mControlsObj, arg2);
        }

        public void sendCustomAction(String arg2, Bundle arg3) {
            MediaControllerCompat.validateCustomAction(arg2, arg3);
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.sendCustomAction(this.mControlsObj, arg2, arg3);
        }

        public void sendCustomAction(CustomAction arg2, Bundle arg3) {
            MediaControllerCompat.validateCustomAction(arg2.getAction(), arg3);
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.sendCustomAction(this.mControlsObj, arg2.getAction(), arg3);
        }

        public void setCaptioningEnabled(boolean arg3) {
            Bundle v0 = new Bundle();
            v0.putBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED", arg3);
            this.sendCustomAction("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED", v0);
        }

        public void setRating(RatingCompat arg2) {
            Object v0 = this.mControlsObj;
            Object v2 = arg2 != null ? arg2.getRating() : null;
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.setRating(v0, v2);
        }

        public void setRating(RatingCompat arg3, Bundle arg4) {
            Bundle v0 = new Bundle();
            v0.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", ((Parcelable)arg3));
            v0.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", ((Parcelable)arg4));
            this.sendCustomAction("android.support.v4.media.session.action.SET_RATING", v0);
        }

        public void setRepeatMode(int arg3) {
            Bundle v0 = new Bundle();
            v0.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", arg3);
            this.sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", v0);
        }

        public void setShuffleMode(int arg3) {
            Bundle v0 = new Bundle();
            v0.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", arg3);
            this.sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", v0);
        }

        public void setShuffleModeEnabled(boolean arg3) {
            Bundle v0 = new Bundle();
            v0.putBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED", arg3);
            this.sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED", v0);
        }

        public void skipToNext() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.skipToNext(this.mControlsObj);
        }

        public void skipToPrevious() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.skipToPrevious(this.mControlsObj);
        }

        public void skipToQueueItem(long arg2) {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.skipToQueueItem(this.mControlsObj, arg2);
        }

        public void stop() {
            android.support.v4.media.session.MediaControllerCompatApi21$TransportControls.stop(this.mControlsObj);
        }
    }

    @RequiresApi(value=23) class TransportControlsApi23 extends TransportControlsApi21 {
        public TransportControlsApi23(Object arg1) {
            super(arg1);
        }

        public void playFromUri(Uri arg2, Bundle arg3) {
            android.support.v4.media.session.MediaControllerCompatApi23$TransportControls.playFromUri(this.mControlsObj, arg2, arg3);
        }
    }

    @RequiresApi(value=24) class TransportControlsApi24 extends TransportControlsApi23 {
        public TransportControlsApi24(Object arg1) {
            super(arg1);
        }

        public void prepare() {
            android.support.v4.media.session.MediaControllerCompatApi24$TransportControls.prepare(this.mControlsObj);
        }

        public void prepareFromMediaId(String arg2, Bundle arg3) {
            android.support.v4.media.session.MediaControllerCompatApi24$TransportControls.prepareFromMediaId(this.mControlsObj, arg2, arg3);
        }

        public void prepareFromSearch(String arg2, Bundle arg3) {
            android.support.v4.media.session.MediaControllerCompatApi24$TransportControls.prepareFromSearch(this.mControlsObj, arg2, arg3);
        }

        public void prepareFromUri(Uri arg2, Bundle arg3) {
            android.support.v4.media.session.MediaControllerCompatApi24$TransportControls.prepareFromUri(this.mControlsObj, arg2, arg3);
        }
    }

    class TransportControlsBase extends TransportControls {
        private IMediaSession mBinder;

        public TransportControlsBase(IMediaSession arg1) {
            super();
            this.mBinder = arg1;
        }

        public void fastForward() {
            try {
                this.mBinder.fastForward();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in fastForward.", ((Throwable)v0));
            }
        }

        public void pause() {
            try {
                this.mBinder.pause();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in pause.", ((Throwable)v0));
            }
        }

        public void play() {
            try {
                this.mBinder.play();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in play.", ((Throwable)v0));
            }
        }

        public void playFromMediaId(String arg2, Bundle arg3) {
            try {
                this.mBinder.playFromMediaId(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in playFromMediaId.", ((Throwable)v2));
            }
        }

        public void playFromSearch(String arg2, Bundle arg3) {
            try {
                this.mBinder.playFromSearch(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in playFromSearch.", ((Throwable)v2));
            }
        }

        public void playFromUri(Uri arg2, Bundle arg3) {
            try {
                this.mBinder.playFromUri(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in playFromUri.", ((Throwable)v2));
            }
        }

        public void prepare() {
            try {
                this.mBinder.prepare();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in prepare.", ((Throwable)v0));
            }
        }

        public void prepareFromMediaId(String arg2, Bundle arg3) {
            try {
                this.mBinder.prepareFromMediaId(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId.", ((Throwable)v2));
            }
        }

        public void prepareFromSearch(String arg2, Bundle arg3) {
            try {
                this.mBinder.prepareFromSearch(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromSearch.", ((Throwable)v2));
            }
        }

        public void prepareFromUri(Uri arg2, Bundle arg3) {
            try {
                this.mBinder.prepareFromUri(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromUri.", ((Throwable)v2));
            }
        }

        public void rewind() {
            try {
                this.mBinder.rewind();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in rewind.", ((Throwable)v0));
            }
        }

        public void seekTo(long arg2) {
            try {
                this.mBinder.seekTo(arg2);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in seekTo.", ((Throwable)v2));
            }
        }

        public void sendCustomAction(CustomAction arg1, Bundle arg2) {
            this.sendCustomAction(arg1.getAction(), arg2);
        }

        public void sendCustomAction(String arg2, Bundle arg3) {
            MediaControllerCompat.validateCustomAction(arg2, arg3);
            try {
                this.mBinder.sendCustomAction(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in sendCustomAction.", ((Throwable)v2));
            }
        }

        public void setCaptioningEnabled(boolean arg3) {
            try {
                this.mBinder.setCaptioningEnabled(arg3);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in setCaptioningEnabled.", ((Throwable)v3));
            }
        }

        public void setRating(RatingCompat arg3) {
            try {
                this.mBinder.rate(arg3);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in setRating.", ((Throwable)v3));
            }
        }

        public void setRating(RatingCompat arg2, Bundle arg3) {
            try {
                this.mBinder.rateWithExtras(arg2, arg3);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in setRating.", ((Throwable)v2));
            }
        }

        public void setRepeatMode(int arg3) {
            try {
                this.mBinder.setRepeatMode(arg3);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in setRepeatMode.", ((Throwable)v3));
            }
        }

        public void setShuffleMode(int arg3) {
            try {
                this.mBinder.setShuffleMode(arg3);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in setShuffleMode.", ((Throwable)v3));
            }
        }

        public void setShuffleModeEnabled(boolean arg3) {
            try {
                this.mBinder.setShuffleModeEnabledDeprecated(arg3);
            }
            catch(RemoteException v3) {
                Log.e("MediaControllerCompat", "Dead object in setShuffleModeEnabled.", ((Throwable)v3));
            }
        }

        public void skipToNext() {
            try {
                this.mBinder.next();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in skipToNext.", ((Throwable)v0));
            }
        }

        public void skipToPrevious() {
            try {
                this.mBinder.previous();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", ((Throwable)v0));
            }
        }

        public void skipToQueueItem(long arg2) {
            try {
                this.mBinder.skipToQueueItem(arg2);
            }
            catch(RemoteException v2) {
                Log.e("MediaControllerCompat", "Dead object in skipToQueueItem.", ((Throwable)v2));
            }
        }

        public void stop() {
            try {
                this.mBinder.stop();
            }
            catch(RemoteException v0) {
                Log.e("MediaControllerCompat", "Dead object in stop.", ((Throwable)v0));
            }
        }
    }

    static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final HashSet mRegisteredCallbacks;
    private final Token mToken;

    public MediaControllerCompat(Context arg3, @NonNull Token arg4) throws RemoteException {
        super();
        this.mRegisteredCallbacks = new HashSet();
        if(arg4 == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }

        this.mToken = arg4;
        if(Build$VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerImplApi24(arg3, arg4);
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerImplApi23(arg3, arg4);
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerImplApi21(arg3, arg4);
        }
        else {
            this.mImpl = new MediaControllerImplBase(this.mToken);
        }
    }

    public MediaControllerCompat(Context arg3, @NonNull MediaSessionCompat arg4) {
        super();
        this.mRegisteredCallbacks = new HashSet();
        if(arg4 == null) {
            throw new IllegalArgumentException("session must not be null");
        }

        this.mToken = arg4.getSessionToken();
        if(Build$VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerImplApi24(arg3, arg4);
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerImplApi23(arg3, arg4);
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerImplApi21(arg3, arg4);
        }
        else {
            this.mImpl = new MediaControllerImplBase(this.mToken);
        }
    }

    static void access$100(String arg0, Bundle arg1) {
        MediaControllerCompat.validateCustomAction(arg0, arg1);
    }

    public void addQueueItem(MediaDescriptionCompat arg2) {
        this.mImpl.addQueueItem(arg2);
    }

    public void addQueueItem(MediaDescriptionCompat arg2, int arg3) {
        this.mImpl.addQueueItem(arg2, arg3);
    }

    public void adjustVolume(int arg2, int arg3) {
        this.mImpl.adjustVolume(arg2, arg3);
    }

    public boolean dispatchMediaButtonEvent(KeyEvent arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }

        return this.mImpl.dispatchMediaButtonEvent(arg2);
    }

    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public long getFlags() {
        return this.mImpl.getFlags();
    }

    public static MediaControllerCompat getMediaController(@NonNull Activity arg3) {
        MediaControllerCompat v1 = null;
        if((arg3 instanceof SupportActivity)) {
            ExtraData v3 = ((SupportActivity)arg3).getExtraData(MediaControllerExtraData.class);
            if(v3 != null) {
                v1 = ((MediaControllerExtraData)v3).getMediaController();
            }

            return v1;
        }

        if(Build$VERSION.SDK_INT >= 21) {
            Object v0 = MediaControllerCompatApi21.getMediaController(arg3);
            if(v0 == null) {
                return v1;
            }

            v0 = MediaControllerCompatApi21.getSessionToken(v0);
            try {
                return new MediaControllerCompat(((Context)arg3), Token.fromToken(v0));
            }
            catch(RemoteException v3_1) {
                Log.e("MediaControllerCompat", "Dead object in getMediaController.", ((Throwable)v3_1));
            }
        }

        return v1;
    }

    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public List getQueue() {
        return this.mImpl.getQueue();
    }

    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }

    public int getRatingType() {
        return this.mImpl.getRatingType();
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public Token getSessionToken() {
        return this.mToken;
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public boolean isCaptioningEnabled() {
        return this.mImpl.isCaptioningEnabled();
    }

    @Deprecated public boolean isShuffleModeEnabled() {
        return this.mImpl.isShuffleModeEnabled();
    }

    public void registerCallback(@NonNull Callback arg2) {
        this.registerCallback(arg2, null);
    }

    public void registerCallback(@NonNull Callback arg2, Handler arg3) {
        if(arg2 == null) {
            throw new IllegalArgumentException("callback must not be null");
        }

        if(arg3 == null) {
            arg3 = new Handler();
        }

        arg2.setHandler(arg3);
        this.mImpl.registerCallback(arg2, arg3);
        this.mRegisteredCallbacks.add(arg2);
    }

    public void removeQueueItem(MediaDescriptionCompat arg2) {
        this.mImpl.removeQueueItem(arg2);
    }

    @Deprecated public void removeQueueItemAt(int arg3) {
        List v0 = this.getQueue();
        if(v0 != null && arg3 >= 0 && arg3 < v0.size()) {
            Object v3 = v0.get(arg3);
            if(v3 != null) {
                this.removeQueueItem(((QueueItem)v3).getDescription());
            }
        }
    }

    public void sendCommand(@NonNull String arg2, Bundle arg3, ResultReceiver arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            throw new IllegalArgumentException("command must neither be null nor empty");
        }

        this.mImpl.sendCommand(arg2, arg3, arg4);
    }

    public static void setMediaController(@NonNull Activity arg2, MediaControllerCompat arg3) {
        if((arg2 instanceof SupportActivity)) {
            arg2.putExtraData(new MediaControllerExtraData(arg3));
        }

        if(Build$VERSION.SDK_INT >= 21) {
            Object v0 = null;
            if(arg3 != null) {
                v0 = MediaControllerCompatApi21.fromToken(((Context)arg2), arg3.getSessionToken().getToken());
            }

            MediaControllerCompatApi21.setMediaController(arg2, v0);
        }
    }

    public void setVolumeTo(int arg2, int arg3) {
        this.mImpl.setVolumeTo(arg2, arg3);
    }

    public void unregisterCallback(@NonNull Callback arg3) {
        if(arg3 == null) {
            throw new IllegalArgumentException("callback must not be null");
        }

        Handler v0 = null;
        try {
            this.mRegisteredCallbacks.remove(arg3);
            this.mImpl.unregisterCallback(arg3);
        }
        catch(Throwable v1) {
            arg3.setHandler(v0);
            throw v1;
        }

        arg3.setHandler(v0);
    }

    private static void validateCustomAction(String arg3, Bundle arg4) {
        if(arg3 == null) {
            return;
        }

        int v0 = -1;
        int v1 = arg3.hashCode();
        if(v1 != 0xAF9FC575) {
            if(v1 != 503011406) {
            }
            else if(arg3.equals("android.support.v4.media.session.action.UNFOLLOW")) {
                v0 = 1;
            }
        }
        else if(arg3.equals("android.support.v4.media.session.action.FOLLOW")) {
            v0 = 0;
        }

        switch(v0) {
            case 0: 
            case 1: {
                if(arg4 != null && (arg4.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE"))) {
                    return;
                }

                goto label_24;
            }
            default: {
                break;
            }
        }

        return;
    label_24:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action ");
        v0_1.append(arg3);
        v0_1.append(".");
        throw new IllegalArgumentException(v0_1.toString());
    }
}

