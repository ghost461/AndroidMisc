package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataEditor;
import android.media.Rating;
import android.media.RemoteControlClient$MetadataEditor;
import android.media.RemoteControlClient$OnMetadataUpdateListener;
import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;
import android.media.RemoteControlClient;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat$Builder;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
    public abstract class Callback {
        class CallbackHandler extends Handler {
            private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;

            CallbackHandler(Callback arg1, Looper arg2) {
                Callback.this = arg1;
                super(arg2);
            }

            public void handleMessage(Message arg2) {
                if(arg2.what == 1) {
                    Callback.this.handleMediaPlayPauseKeySingleTapIfPending();
                }
            }
        }

        @RequiresApi(value=21) class StubApi21 implements android.support.v4.media.session.MediaSessionCompatApi21$Callback {
            StubApi21(Callback arg1) {
                Callback.this = arg1;
                super();
            }

            public void onCommand(String arg3, Bundle arg4, ResultReceiver arg5) {
                Object v3;
                try {
                    IBinder v1 = null;
                    if(arg3.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        v3 = Callback.this.mSessionImpl.get();
                        if(v3 == null) {
                            return;
                        }

                        arg4 = new Bundle();
                        IMediaSession v3_1 = ((MediaSessionImplApi21)v3).getSessionToken().getExtraBinder();
                        String v0 = "android.support.v4.media.session.EXTRA_BINDER";
                        if(v3_1 == null) {
                        }
                        else {
                            v1 = v3_1.asBinder();
                        }

                        BundleCompat.putBinder(arg4, v0, v1);
                        arg5.send(0, arg4);
                        return;
                    }

                    if(arg3.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        arg4.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        Callback.this.onAddQueueItem(arg4.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                        return;
                    }

                    if(arg3.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                        arg4.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        Callback.this.onAddQueueItem(arg4.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), arg4.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                        return;
                    }

                    if(arg3.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                        arg4.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        Callback.this.onRemoveQueueItem(arg4.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                        return;
                    }

                    if(arg3.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                        v3 = Callback.this.mSessionImpl.get();
                        if(v3 == null) {
                            return;
                        }

                        if(MediaSessionImplApi21.access$200(((MediaSessionImplApi21)v3)) == null) {
                            return;
                        }

                        int v4 = arg4.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                        if(v4 >= 0 && v4 < MediaSessionImplApi21.access$200(((MediaSessionImplApi21)v3)).size()) {
                            Object v1_1 = MediaSessionImplApi21.access$200(((MediaSessionImplApi21)v3)).get(v4);
                        }

                        if(v1 == null) {
                            return;
                        }

                        Callback.this.onRemoveQueueItem(((QueueItem)v1).getDescription());
                        return;
                    }

                    Callback.this.onCommand(arg3, arg4, arg5);
                }
                catch(BadParcelableException ) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                }
            }

            public void onCustomAction(String arg2, Bundle arg3) {
                if(arg2.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    Callback.this.onPlayFromUri(arg3.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), arg3.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.PREPARE")) {
                    Callback.this.onPrepare();
                }
                else if(arg2.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                    Callback.this.onPrepareFromMediaId(arg3.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID"), arg3.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                    Callback.this.onPrepareFromSearch(arg3.getString("android.support.v4.media.session.action.ARGUMENT_QUERY"), arg3.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                    Callback.this.onPrepareFromUri(arg3.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), arg3.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                    Callback.this.onSetCaptioningEnabled(arg3.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                    Callback.this.onSetRepeatMode(arg3.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED")) {
                    Callback.this.onSetShuffleModeEnabled(arg3.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                    Callback.this.onSetShuffleMode(arg3.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                }
                else if(arg2.equals("android.support.v4.media.session.action.SET_RATING")) {
                    arg3.setClassLoader(RatingCompat.class.getClassLoader());
                    Callback.this.onSetRating(arg3.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING"), arg3.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else {
                    Callback.this.onCustomAction(arg2, arg3);
                }
            }

            public void onFastForward() {
                Callback.this.onFastForward();
            }

            public boolean onMediaButtonEvent(Intent arg2) {
                return Callback.this.onMediaButtonEvent(arg2);
            }

            public void onPause() {
                Callback.this.onPause();
            }

            public void onPlay() {
                Callback.this.onPlay();
            }

            public void onPlayFromMediaId(String arg2, Bundle arg3) {
                Callback.this.onPlayFromMediaId(arg2, arg3);
            }

            public void onPlayFromSearch(String arg2, Bundle arg3) {
                Callback.this.onPlayFromSearch(arg2, arg3);
            }

            public void onRewind() {
                Callback.this.onRewind();
            }

            public void onSeekTo(long arg2) {
                Callback.this.onSeekTo(arg2);
            }

            public void onSetRating(Object arg2) {
                Callback.this.onSetRating(RatingCompat.fromRating(arg2));
            }

            public void onSetRating(Object arg2, Bundle arg3) {
                Callback.this.onSetRating(RatingCompat.fromRating(arg2), arg3);
            }

            public void onSkipToNext() {
                Callback.this.onSkipToNext();
            }

            public void onSkipToPrevious() {
                Callback.this.onSkipToPrevious();
            }

            public void onSkipToQueueItem(long arg2) {
                Callback.this.onSkipToQueueItem(arg2);
            }

            public void onStop() {
                Callback.this.onStop();
            }
        }

        @RequiresApi(value=23) class StubApi23 extends StubApi21 implements android.support.v4.media.session.MediaSessionCompatApi23$Callback {
            StubApi23(Callback arg1) {
                Callback.this = arg1;
                super(arg1);
            }

            public void onPlayFromUri(Uri arg2, Bundle arg3) {
                Callback.this.onPlayFromUri(arg2, arg3);
            }
        }

        @RequiresApi(value=24) class StubApi24 extends StubApi23 implements android.support.v4.media.session.MediaSessionCompatApi24$Callback {
            StubApi24(Callback arg1) {
                Callback.this = arg1;
                super(arg1);
            }

            public void onPrepare() {
                Callback.this.onPrepare();
            }

            public void onPrepareFromMediaId(String arg2, Bundle arg3) {
                Callback.this.onPrepareFromMediaId(arg2, arg3);
            }

            public void onPrepareFromSearch(String arg2, Bundle arg3) {
                Callback.this.onPrepareFromSearch(arg2, arg3);
            }

            public void onPrepareFromUri(Uri arg2, Bundle arg3) {
                Callback.this.onPrepareFromUri(arg2, arg3);
            }
        }

        private CallbackHandler mCallbackHandler;
        final Object mCallbackObj;
        private boolean mMediaPlayPauseKeyPending;
        private WeakReference mSessionImpl;

        public Callback() {
            super();
            CallbackHandler v0 = null;
            this.mCallbackHandler = v0;
            if(Build$VERSION.SDK_INT >= 24) {
                this.mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24(this));
            }
            else if(Build$VERSION.SDK_INT >= 23) {
                this.mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23(this));
            }
            else if(Build$VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21(this));
            }
            else {
                this.mCallbackObj = v0;
            }
        }

        static void access$000(Callback arg0) {
            arg0.handleMediaPlayPauseKeySingleTapIfPending();
        }

        static WeakReference access$100(Callback arg0) {
            return arg0.mSessionImpl;
        }

        static void access$300(Callback arg0, MediaSessionImpl arg1, Handler arg2) {
            arg0.setSessionImpl(arg1, arg2);
        }

        private void handleMediaPlayPauseKeySingleTapIfPending() {
            if(!this.mMediaPlayPauseKeyPending) {
                return;
            }

            int v0 = 0;
            this.mMediaPlayPauseKeyPending = false;
            this.mCallbackHandler.removeMessages(1);
            Object v1 = this.mSessionImpl.get();
            if(v1 == null) {
                return;
            }

            PlaybackStateCompat v1_1 = ((MediaSessionImpl)v1).getPlaybackState();
            long v3 = 0;
            long v5 = v1_1 == null ? v3 : v1_1.getActions();
            int v1_2 = v1_1 == null || v1_1.getState() != 3 ? 0 : 1;
            int v7 = (v5 & 0x204) != v3 ? 1 : 0;
            if((v5 & 0x202) != v3) {
                v0 = 1;
            }

            if(v1_2 != 0 && v0 != 0) {
                this.onPause();
            }
            else if(v1_2 == 0 && v7 != 0) {
                this.onPlay();
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat arg1) {
        }

        public void onAddQueueItem(MediaDescriptionCompat arg1, int arg2) {
        }

        public void onCommand(String arg1, Bundle arg2, ResultReceiver arg3) {
        }

        public void onCustomAction(String arg1, Bundle arg2) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent arg10) {
            Object v0 = this.mSessionImpl.get();
            if(v0 != null) {
                if(this.mCallbackHandler == null) {
                }
                else {
                    Parcelable v10 = arg10.getParcelableExtra("android.intent.extra.KEY_EVENT");
                    if(v10 != null) {
                        if(((KeyEvent)v10).getAction() != 0) {
                        }
                        else {
                            int v2 = ((KeyEvent)v10).getKeyCode();
                            if(v2 != 0x4F && v2 != 85) {
                                this.handleMediaPlayPauseKeySingleTapIfPending();
                                return 0;
                            }

                            if(((KeyEvent)v10).getRepeatCount() > 0) {
                                this.handleMediaPlayPauseKeySingleTapIfPending();
                            }
                            else if(this.mMediaPlayPauseKeyPending) {
                                this.mCallbackHandler.removeMessages(1);
                                this.mMediaPlayPauseKeyPending = false;
                                PlaybackStateCompat v10_1 = ((MediaSessionImpl)v0).getPlaybackState();
                                long v0_1 = 0;
                                long v3 = v10_1 == null ? v0_1 : v10_1.getActions();
                                if((v3 & 0x20) == v0_1) {
                                    return 1;
                                }

                                this.onSkipToNext();
                            }
                            else {
                                this.mMediaPlayPauseKeyPending = true;
                                this.mCallbackHandler.sendEmptyMessageDelayed(1, ((long)ViewConfiguration.getDoubleTapTimeout()));
                            }

                            return 1;
                        }
                    }

                    return 0;
                }
            }

            return 0;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String arg1, Bundle arg2) {
        }

        public void onPlayFromSearch(String arg1, Bundle arg2) {
        }

        public void onPlayFromUri(Uri arg1, Bundle arg2) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String arg1, Bundle arg2) {
        }

        public void onPrepareFromSearch(String arg1, Bundle arg2) {
        }

        public void onPrepareFromUri(Uri arg1, Bundle arg2) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat arg1) {
        }

        @Deprecated public void onRemoveQueueItemAt(int arg1) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long arg1) {
        }

        public void onSetCaptioningEnabled(boolean arg1) {
        }

        public void onSetRating(RatingCompat arg1) {
        }

        public void onSetRating(RatingCompat arg1, Bundle arg2) {
        }

        public void onSetRepeatMode(int arg1) {
        }

        public void onSetShuffleMode(int arg1) {
        }

        @Deprecated public void onSetShuffleModeEnabled(boolean arg1) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long arg1) {
        }

        public void onStop() {
        }

        private void setSessionImpl(MediaSessionImpl arg2, Handler arg3) {
            this.mSessionImpl = new WeakReference(arg2);
            if(this.mCallbackHandler != null) {
                this.mCallbackHandler.removeCallbacksAndMessages(null);
            }

            this.mCallbackHandler = new CallbackHandler(this, arg3.getLooper());
        }
    }

    interface MediaSessionImpl {
        String getCallingPackage();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Object getRemoteControlClient();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String arg1, Bundle arg2);

        void setActive(boolean arg1);

        void setCallback(Callback arg1, Handler arg2);

        void setCaptioningEnabled(boolean arg1);

        void setExtras(Bundle arg1);

        void setFlags(int arg1);

        void setMediaButtonReceiver(PendingIntent arg1);

        void setMetadata(MediaMetadataCompat arg1);

        void setPlaybackState(PlaybackStateCompat arg1);

        void setPlaybackToLocal(int arg1);

        void setPlaybackToRemote(VolumeProviderCompat arg1);

        void setQueue(List arg1);

        void setQueueTitle(CharSequence arg1);

        void setRatingType(int arg1);

        void setRepeatMode(int arg1);

        void setSessionActivity(PendingIntent arg1);

        void setShuffleMode(int arg1);

        void setShuffleModeEnabled(boolean arg1);
    }

    @RequiresApi(value=18) class MediaSessionImplApi18 extends MediaSessionImplBase {
        private static boolean sIsMbrPendingIntentSupported = true;

        static {
        }

        MediaSessionImplApi18(Context arg1, String arg2, ComponentName arg3, PendingIntent arg4) {
            super(arg1, arg2, arg3, arg4);
        }

        int getRccTransportControlFlagsFromActions(long arg6) {
            int v0 = super.getRccTransportControlFlagsFromActions(arg6);
            if((arg6 & 0x100) != 0) {
                v0 |= 0x100;
            }

            return v0;
        }

        void registerMediaButtonEventReceiver(PendingIntent arg3, ComponentName arg4) {
            if(MediaSessionImplApi18.sIsMbrPendingIntentSupported) {
                try {
                    this.mAudioManager.registerMediaButtonEventReceiver(arg3);
                }
                catch(NullPointerException ) {
                    Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    MediaSessionImplApi18.sIsMbrPendingIntentSupported = false;
                }
            }

            if(!MediaSessionImplApi18.sIsMbrPendingIntentSupported) {
                super.registerMediaButtonEventReceiver(arg3, arg4);
            }
        }

        public void setCallback(Callback arg1, Handler arg2) {
            super.setCallback(arg1, arg2);
            if(arg1 == null) {
                this.mRcc.setPlaybackPositionUpdateListener(null);
            }
            else {
                this.mRcc.setPlaybackPositionUpdateListener(new RemoteControlClient$OnPlaybackPositionUpdateListener() {
                    public void onPlaybackPositionUpdate(long arg2) {
                        MediaSessionImplApi18.this.postToHandler(18, Long.valueOf(arg2));
                    }
                });
            }
        }

        void setRccState(PlaybackStateCompat arg11) {
            long v0 = arg11.getPosition();
            float v2 = arg11.getPlaybackSpeed();
            long v3 = arg11.getLastPositionUpdateTime();
            long v5 = SystemClock.elapsedRealtime();
            if(arg11.getState() == 3) {
                long v7 = 0;
                if(v0 > v7) {
                    if(v3 > v7) {
                        v7 = v5 - v3;
                        if(v2 > 0f && v2 != 1f) {
                            v7 = ((long)((((float)v7)) * v2));
                        }
                    }

                    v0 += v7;
                }
            }

            this.mRcc.setPlaybackState(this.getRccStateFromState(arg11.getState()), v0, v2);
        }

        void unregisterMediaButtonEventReceiver(PendingIntent arg2, ComponentName arg3) {
            if(MediaSessionImplApi18.sIsMbrPendingIntentSupported) {
                this.mAudioManager.unregisterMediaButtonEventReceiver(arg2);
            }
            else {
                super.unregisterMediaButtonEventReceiver(arg2, arg3);
            }
        }
    }

    @RequiresApi(value=19) class MediaSessionImplApi19 extends MediaSessionImplApi18 {
        MediaSessionImplApi19(Context arg1, String arg2, ComponentName arg3, PendingIntent arg4) {
            super(arg1, arg2, arg3, arg4);
        }

        RemoteControlClient$MetadataEditor buildRccMetadata(Bundle arg11) {
            RemoteControlClient$MetadataEditor v0 = super.buildRccMetadata(arg11);
            long v2 = 0;
            long v4 = this.mState == null ? v2 : this.mState.getActions();
            int v1 = Long.compare(v4 & 0x80, v2);
            int v2_1 = 0x10000001;
            if(v1 != 0) {
                v0.addEditableKey(v2_1);
            }

            if(arg11 == null) {
                return v0;
            }

            if(arg11.containsKey("android.media.metadata.YEAR")) {
                v0.putLong(8, arg11.getLong("android.media.metadata.YEAR"));
            }

            if(arg11.containsKey("android.media.metadata.RATING")) {
                ((MediaMetadataEditor)v0).putObject(101, arg11.getParcelable("android.media.metadata.RATING"));
            }

            if(arg11.containsKey("android.media.metadata.USER_RATING")) {
                ((MediaMetadataEditor)v0).putObject(v2_1, arg11.getParcelable("android.media.metadata.USER_RATING"));
            }

            return v0;
        }

        int getRccTransportControlFlagsFromActions(long arg6) {
            int v0 = super.getRccTransportControlFlagsFromActions(arg6);
            if((arg6 & 0x80) != 0) {
                v0 |= 0x200;
            }

            return v0;
        }

        public void setCallback(Callback arg1, Handler arg2) {
            super.setCallback(arg1, arg2);
            if(arg1 == null) {
                this.mRcc.setMetadataUpdateListener(null);
            }
            else {
                this.mRcc.setMetadataUpdateListener(new RemoteControlClient$OnMetadataUpdateListener() {
                    public void onMetadataUpdate(int arg2, Object arg3) {
                        if(arg2 == 0x10000001 && ((arg3 instanceof Rating))) {
                            MediaSessionImplApi19.this.postToHandler(19, RatingCompat.fromRating(arg3));
                        }
                    }
                });
            }
        }
    }

    @RequiresApi(value=21) class MediaSessionImplApi21 implements MediaSessionImpl {
        class ExtraSession extends Stub {
            ExtraSession(MediaSessionImplApi21 arg1) {
                MediaSessionImplApi21.this = arg1;
                super();
            }

            public void addQueueItem(MediaDescriptionCompat arg1) {
                throw new AssertionError();
            }

            public void addQueueItemAt(MediaDescriptionCompat arg1, int arg2) {
                throw new AssertionError();
            }

            public void adjustVolume(int arg1, int arg2, String arg3) {
                throw new AssertionError();
            }

            public void fastForward() throws RemoteException {
                throw new AssertionError();
            }

            public Bundle getExtras() {
                throw new AssertionError();
            }

            public long getFlags() {
                throw new AssertionError();
            }

            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            public String getPackageName() {
                throw new AssertionError();
            }

            public PlaybackStateCompat getPlaybackState() {
                return MediaSessionCompat.getStateWithUpdatedPosition(MediaSessionImplApi21.this.mPlaybackState, MediaSessionImplApi21.this.mMetadata);
            }

            public List getQueue() {
                return null;
            }

            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            public int getRatingType() {
                return MediaSessionImplApi21.this.mRatingType;
            }

            public int getRepeatMode() {
                return MediaSessionImplApi21.this.mRepeatMode;
            }

            public int getShuffleMode() {
                return MediaSessionImplApi21.this.mShuffleMode;
            }

            public String getTag() {
                throw new AssertionError();
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            public boolean isCaptioningEnabled() {
                return MediaSessionImplApi21.this.mCaptioningEnabled;
            }

            public boolean isShuffleModeEnabledDeprecated() {
                return MediaSessionImplApi21.this.mShuffleModeEnabled;
            }

            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }

            public void next() throws RemoteException {
                throw new AssertionError();
            }

            public void pause() throws RemoteException {
                throw new AssertionError();
            }

            public void play() throws RemoteException {
                throw new AssertionError();
            }

            public void playFromMediaId(String arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void playFromSearch(String arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void playFromUri(Uri arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void prepare() throws RemoteException {
                throw new AssertionError();
            }

            public void prepareFromMediaId(String arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void prepareFromSearch(String arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void prepareFromUri(Uri arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void previous() throws RemoteException {
                throw new AssertionError();
            }

            public void rate(RatingCompat arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void rateWithExtras(RatingCompat arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public void registerCallbackListener(IMediaControllerCallback arg2) {
                if(!MediaSessionImplApi21.this.mDestroyed) {
                    MediaSessionImplApi21.this.mExtraControllerCallbacks.register(((IInterface)arg2));
                }
            }

            public void removeQueueItem(MediaDescriptionCompat arg1) {
                throw new AssertionError();
            }

            public void removeQueueItemAt(int arg1) {
                throw new AssertionError();
            }

            public void rewind() throws RemoteException {
                throw new AssertionError();
            }

            public void seekTo(long arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void sendCommand(String arg1, Bundle arg2, ResultReceiverWrapper arg3) {
                throw new AssertionError();
            }

            public void sendCustomAction(String arg1, Bundle arg2) throws RemoteException {
                throw new AssertionError();
            }

            public boolean sendMediaButton(KeyEvent arg1) {
                throw new AssertionError();
            }

            public void setCaptioningEnabled(boolean arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void setRepeatMode(int arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void setShuffleMode(int arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void setShuffleModeEnabledDeprecated(boolean arg1) throws RemoteException {
                throw new AssertionError();
            }

            public void setVolumeTo(int arg1, int arg2, String arg3) {
                throw new AssertionError();
            }

            public void skipToQueueItem(long arg1) {
                throw new AssertionError();
            }

            public void stop() throws RemoteException {
                throw new AssertionError();
            }

            public void unregisterCallbackListener(IMediaControllerCallback arg2) {
                MediaSessionImplApi21.this.mExtraControllerCallbacks.unregister(((IInterface)arg2));
            }
        }

        boolean mCaptioningEnabled;
        private boolean mDestroyed;
        private final RemoteCallbackList mExtraControllerCallbacks;
        private MediaMetadataCompat mMetadata;
        private PlaybackStateCompat mPlaybackState;
        private List mQueue;
        int mRatingType;
        int mRepeatMode;
        private final Object mSessionObj;
        int mShuffleMode;
        boolean mShuffleModeEnabled;
        private final Token mToken;

        public MediaSessionImplApi21(Context arg2, String arg3) {
            super();
            this.mDestroyed = false;
            this.mExtraControllerCallbacks = new RemoteCallbackList();
            this.mSessionObj = MediaSessionCompatApi21.createSession(arg2, arg3);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new ExtraSession(this));
        }

        public MediaSessionImplApi21(Object arg3) {
            super();
            this.mDestroyed = false;
            this.mExtraControllerCallbacks = new RemoteCallbackList();
            this.mSessionObj = MediaSessionCompatApi21.verifySession(arg3);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new ExtraSession(this));
        }

        static List access$200(MediaSessionImplApi21 arg0) {
            return arg0.mQueue;
        }

        static boolean access$600(MediaSessionImplApi21 arg0) {
            return arg0.mDestroyed;
        }

        static RemoteCallbackList access$700(MediaSessionImplApi21 arg0) {
            return arg0.mExtraControllerCallbacks;
        }

        static PlaybackStateCompat access$800(MediaSessionImplApi21 arg0) {
            return arg0.mPlaybackState;
        }

        static MediaMetadataCompat access$900(MediaSessionImplApi21 arg0) {
            return arg0.mMetadata;
        }

        public String getCallingPackage() {
            if(Build$VERSION.SDK_INT < 24) {
                return null;
            }

            return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
        }

        public Object getMediaSession() {
            return this.mSessionObj;
        }

        public PlaybackStateCompat getPlaybackState() {
            return this.mPlaybackState;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }

        public void release() {
            this.mDestroyed = true;
            MediaSessionCompatApi21.release(this.mSessionObj);
        }

        public void sendSessionEvent(String arg3, Bundle arg4) {
            if(Build$VERSION.SDK_INT < 23) {
                int v0 = this.mExtraControllerCallbacks.beginBroadcast() - 1;
                while(true) {
                    if(v0 >= 0) {
                        IInterface v1 = this.mExtraControllerCallbacks.getBroadcastItem(v0);
                        try {
                            ((IMediaControllerCallback)v1).onEvent(arg3, arg4);
                            goto label_10;
                        }
                        catch(RemoteException ) {
                        label_10:
                            --v0;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    goto label_14;
                }

                this.mExtraControllerCallbacks.finishBroadcast();
            }

        label_14:
            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, arg3, arg4);
        }

        public void setActive(boolean arg2) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, arg2);
        }

        public void setCallback(Callback arg3, Handler arg4) {
            Object v0 = this.mSessionObj;
            Object v1 = arg3 == null ? null : arg3.mCallbackObj;
            MediaSessionCompatApi21.setCallback(v0, v1, arg4);
            if(arg3 != null) {
                arg3.setSessionImpl(((MediaSessionImpl)this), arg4);
            }
        }

        public void setCaptioningEnabled(boolean arg3) {
            if(this.mCaptioningEnabled != arg3) {
                this.mCaptioningEnabled = arg3;
                int v0 = this.mExtraControllerCallbacks.beginBroadcast() - 1;
                while(true) {
                    if(v0 >= 0) {
                        IInterface v1 = this.mExtraControllerCallbacks.getBroadcastItem(v0);
                        try {
                            ((IMediaControllerCallback)v1).onCaptioningEnabledChanged(arg3);
                            goto label_10;
                        }
                        catch(RemoteException ) {
                        label_10:
                            --v0;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    return;
                }

                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setExtras(Bundle arg2) {
            MediaSessionCompatApi21.setExtras(this.mSessionObj, arg2);
        }

        public void setFlags(int arg2) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, arg2);
        }

        public void setMediaButtonReceiver(PendingIntent arg2) {
            MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, arg2);
        }

        public void setMetadata(MediaMetadataCompat arg2) {
            this.mMetadata = arg2;
            Object v0 = this.mSessionObj;
            Object v2 = arg2 == null ? null : arg2.getMediaMetadata();
            MediaSessionCompatApi21.setMetadata(v0, v2);
        }

        public void setPlaybackState(PlaybackStateCompat arg3) {
            this.mPlaybackState = arg3;
            int v0 = this.mExtraControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mExtraControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onPlaybackStateChanged(arg3);
                    goto label_8;
                }
                catch(RemoteException ) {
                label_8:
                    --v0;
                    continue;
                }
            }

            this.mExtraControllerCallbacks.finishBroadcast();
            Object v0_1 = this.mSessionObj;
            Object v3 = arg3 == null ? null : arg3.getPlaybackState();
            MediaSessionCompatApi21.setPlaybackState(v0_1, v3);
        }

        public void setPlaybackToLocal(int arg2) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, arg2);
        }

        public void setPlaybackToRemote(VolumeProviderCompat arg2) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, arg2.getVolumeProvider());
        }

        public void setQueue(List arg3) {
            ArrayList v0;
            this.mQueue = arg3;
            if(arg3 != null) {
                v0 = new ArrayList();
                Iterator v3 = arg3.iterator();
                while(v3.hasNext()) {
                    ((List)v0).add(v3.next().getQueueItem());
                }
            }
            else {
                List v0_1 = null;
            }

            MediaSessionCompatApi21.setQueue(this.mSessionObj, ((List)v0));
        }

        public void setQueueTitle(CharSequence arg2) {
            MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, arg2);
        }

        public void setRatingType(int arg3) {
            if(Build$VERSION.SDK_INT < 22) {
                this.mRatingType = arg3;
            }
            else {
                MediaSessionCompatApi22.setRatingType(this.mSessionObj, arg3);
            }
        }

        public void setRepeatMode(int arg3) {
            if(this.mRepeatMode != arg3) {
                this.mRepeatMode = arg3;
                int v0 = this.mExtraControllerCallbacks.beginBroadcast() - 1;
                while(true) {
                    if(v0 >= 0) {
                        IInterface v1 = this.mExtraControllerCallbacks.getBroadcastItem(v0);
                        try {
                            ((IMediaControllerCallback)v1).onRepeatModeChanged(arg3);
                            goto label_10;
                        }
                        catch(RemoteException ) {
                        label_10:
                            --v0;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    return;
                }

                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setSessionActivity(PendingIntent arg2) {
            MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, arg2);
        }

        public void setShuffleMode(int arg3) {
            if(this.mShuffleMode != arg3) {
                this.mShuffleMode = arg3;
                int v0 = this.mExtraControllerCallbacks.beginBroadcast() - 1;
                while(true) {
                    if(v0 >= 0) {
                        IInterface v1 = this.mExtraControllerCallbacks.getBroadcastItem(v0);
                        try {
                            ((IMediaControllerCallback)v1).onShuffleModeChanged(arg3);
                            goto label_10;
                        }
                        catch(RemoteException ) {
                        label_10:
                            --v0;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    return;
                }

                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setShuffleModeEnabled(boolean arg3) {
            if(this.mShuffleModeEnabled != arg3) {
                this.mShuffleModeEnabled = arg3;
                int v0 = this.mExtraControllerCallbacks.beginBroadcast() - 1;
                while(true) {
                    if(v0 >= 0) {
                        IInterface v1 = this.mExtraControllerCallbacks.getBroadcastItem(v0);
                        try {
                            ((IMediaControllerCallback)v1).onShuffleModeChangedDeprecated(arg3);
                            goto label_10;
                        }
                        catch(RemoteException ) {
                        label_10:
                            --v0;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    return;
                }

                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }
    }

    class MediaSessionImplBase implements MediaSessionImpl {
        class android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$1 extends android.support.v4.media.VolumeProviderCompat$Callback {
            android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$1(MediaSessionImplBase arg1) {
                MediaSessionImplBase.this = arg1;
                super();
            }

            public void onVolumeChanged(VolumeProviderCompat arg8) {
                if(MediaSessionImplBase.this.mVolumeProvider != arg8) {
                    return;
                }

                MediaSessionImplBase.this.sendVolumeInfoChanged(new ParcelableVolumeInfo(MediaSessionImplBase.this.mVolumeType, MediaSessionImplBase.this.mLocalStream, arg8.getVolumeControl(), arg8.getMaxVolume(), arg8.getCurrentVolume()));
            }
        }

        final class Command {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;

            public Command(String arg1, Bundle arg2, ResultReceiver arg3) {
                super();
                this.command = arg1;
                this.extras = arg2;
                this.stub = arg3;
            }
        }

        class MediaSessionStub extends Stub {
            MediaSessionStub(MediaSessionImplBase arg1) {
                MediaSessionImplBase.this = arg1;
                super();
            }

            public void addQueueItem(MediaDescriptionCompat arg3) {
                MediaSessionImplBase.this.postToHandler(25, arg3);
            }

            public void addQueueItemAt(MediaDescriptionCompat arg3, int arg4) {
                MediaSessionImplBase.this.postToHandler(26, arg3, arg4);
            }

            public void adjustVolume(int arg1, int arg2, String arg3) {
                MediaSessionImplBase.this.adjustVolume(arg1, arg2);
            }

            public void fastForward() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(16);
            }

            public Bundle getExtras() {
                Object v0 = MediaSessionImplBase.this.mLock;
                __monitor_enter(v0);
                try {
                    __monitor_exit(v0);
                    return MediaSessionImplBase.this.mExtras;
                label_8:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_8;
                }

                throw v1;
            }

            public long getFlags() {
                Object v0 = MediaSessionImplBase.this.mLock;
                __monitor_enter(v0);
                try {
                    __monitor_exit(v0);
                    return ((long)MediaSessionImplBase.this.mFlags);
                label_9:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_9;
                }

                throw v1;
            }

            public PendingIntent getLaunchPendingIntent() {
                Object v0 = MediaSessionImplBase.this.mLock;
                __monitor_enter(v0);
                try {
                    __monitor_exit(v0);
                    return MediaSessionImplBase.this.mSessionActivity;
                label_8:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_8;
                }

                throw v1;
            }

            public MediaMetadataCompat getMetadata() {
                return MediaSessionImplBase.this.mMetadata;
            }

            public String getPackageName() {
                return MediaSessionImplBase.this.mPackageName;
            }

            public PlaybackStateCompat getPlaybackState() {
                MediaMetadataCompat v2;
                PlaybackStateCompat v1_1;
                Object v0 = MediaSessionImplBase.this.mLock;
                __monitor_enter(v0);
                try {
                    v1_1 = MediaSessionImplBase.this.mState;
                    v2 = MediaSessionImplBase.this.mMetadata;
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    try {
                    label_11:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_11;
                    }

                    throw v1;
                }

                return MediaSessionCompat.getStateWithUpdatedPosition(v1_1, v2);
            }

            public List getQueue() {
                Object v0 = MediaSessionImplBase.this.mLock;
                __monitor_enter(v0);
                try {
                    __monitor_exit(v0);
                    return MediaSessionImplBase.this.mQueue;
                label_8:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_8;
                }

                throw v1;
            }

            public CharSequence getQueueTitle() {
                return MediaSessionImplBase.this.mQueueTitle;
            }

            public int getRatingType() {
                return MediaSessionImplBase.this.mRatingType;
            }

            public int getRepeatMode() {
                return MediaSessionImplBase.this.mRepeatMode;
            }

            public int getShuffleMode() {
                return MediaSessionImplBase.this.mShuffleMode;
            }

            public String getTag() {
                return MediaSessionImplBase.this.mTag;
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                int v6;
                int v7;
                int v5;
                int v4;
                int v3;
                Object v0 = MediaSessionImplBase.this.mLock;
                __monitor_enter(v0);
                try {
                    v3 = MediaSessionImplBase.this.mVolumeType;
                    v4 = MediaSessionImplBase.this.mLocalStream;
                    VolumeProviderCompat v1_1 = MediaSessionImplBase.this.mVolumeProvider;
                    if(v3 == 2) {
                        int v2 = v1_1.getVolumeControl();
                        v5 = v1_1.getMaxVolume();
                        v7 = v1_1.getCurrentVolume();
                        v6 = v5;
                        v5 = v2;
                    }
                    else {
                        v6 = MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(v4);
                        v7 = MediaSessionImplBase.this.mAudioManager.getStreamVolume(v4);
                        v5 = 2;
                    }

                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    try {
                    label_34:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_34;
                    }

                    throw v1;
                }

                return new ParcelableVolumeInfo(v3, v4, v5, v6, v7);
            }

            public boolean isCaptioningEnabled() {
                return MediaSessionImplBase.this.mCaptioningEnabled;
            }

            public boolean isShuffleModeEnabledDeprecated() {
                return MediaSessionImplBase.this.mShuffleModeEnabled;
            }

            public boolean isTransportControlEnabled() {
                boolean v0 = (MediaSessionImplBase.this.mFlags & 2) != 0 ? true : false;
                return v0;
            }

            public void next() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(14);
            }

            public void pause() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(12);
            }

            public void play() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(7);
            }

            public void playFromMediaId(String arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(8, arg3, arg4);
            }

            public void playFromSearch(String arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(9, arg3, arg4);
            }

            public void playFromUri(Uri arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(10, arg3, arg4);
            }

            public void prepare() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(3);
            }

            public void prepareFromMediaId(String arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(4, arg3, arg4);
            }

            public void prepareFromSearch(String arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(5, arg3, arg4);
            }

            public void prepareFromUri(Uri arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(6, arg3, arg4);
            }

            public void previous() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(15);
            }

            public void rate(RatingCompat arg3) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(19, arg3);
            }

            public void rateWithExtras(RatingCompat arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(0x1F, arg3, arg4);
            }

            public void registerCallbackListener(IMediaControllerCallback arg2) {
                if(MediaSessionImplBase.this.mDestroyed) {
                    try {
                        arg2.onSessionDestroyed();
                        return;
                    }
                    catch(Exception ) {
                        return;
                    }
                }

                MediaSessionImplBase.this.mControllerCallbacks.register(((IInterface)arg2));
            }

            public void removeQueueItem(MediaDescriptionCompat arg3) {
                MediaSessionImplBase.this.postToHandler(27, arg3);
            }

            public void removeQueueItemAt(int arg3) {
                MediaSessionImplBase.this.postToHandler(28, arg3);
            }

            public void rewind() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(17);
            }

            public void seekTo(long arg2) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(18, Long.valueOf(arg2));
            }

            public void sendCommand(String arg3, Bundle arg4, ResultReceiverWrapper arg5) {
                MediaSessionImplBase.this.postToHandler(1, new Command(arg3, arg4, arg5.mResultReceiver));
            }

            public void sendCustomAction(String arg3, Bundle arg4) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(20, arg3, arg4);
            }

            public boolean sendMediaButton(KeyEvent arg4) {
                boolean v1 = true;
                if((MediaSessionImplBase.this.mFlags & 1) != 0) {
                }
                else {
                    v1 = false;
                }

                if(v1) {
                    MediaSessionImplBase.this.postToHandler(21, arg4);
                }

                return v1;
            }

            public void setCaptioningEnabled(boolean arg3) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(29, Boolean.valueOf(arg3));
            }

            public void setRepeatMode(int arg3) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(23, arg3);
            }

            public void setShuffleMode(int arg3) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(30, arg3);
            }

            public void setShuffleModeEnabledDeprecated(boolean arg3) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(24, Boolean.valueOf(arg3));
            }

            public void setVolumeTo(int arg1, int arg2, String arg3) {
                MediaSessionImplBase.this.setVolumeTo(arg1, arg2);
            }

            public void skipToQueueItem(long arg2) {
                MediaSessionImplBase.this.postToHandler(11, Long.valueOf(arg2));
            }

            public void stop() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(13);
            }

            public void unregisterCallbackListener(IMediaControllerCallback arg2) {
                MediaSessionImplBase.this.mControllerCallbacks.unregister(((IInterface)arg2));
            }
        }

        class MessageHandler extends Handler {
            private static final int KEYCODE_MEDIA_PAUSE = 0x7F;
            private static final int KEYCODE_MEDIA_PLAY = 0x7E;
            private static final int MSG_ADD_QUEUE_ITEM = 25;
            private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_RATE_EXTRA = 0x1F;
            private static final int MSG_REMOVE_QUEUE_ITEM = 27;
            private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_CAPTIONING_ENABLED = 29;
            private static final int MSG_SET_REPEAT_MODE = 23;
            private static final int MSG_SET_SHUFFLE_MODE = 30;
            private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;

            public MessageHandler(MediaSessionImplBase arg1, Looper arg2) {
                MediaSessionImplBase.this = arg1;
                super(arg2);
            }

            public void handleMessage(Message arg4) {
                Object v4;
                Callback v0 = MediaSessionImplBase.this.mCallback;
                if(v0 == null) {
                    return;
                }

                switch(arg4.what) {
                    case 1: {
                        v0.onCommand(arg4.obj.command, arg4.obj.extras, arg4.obj.stub);
                        break;
                    }
                    case 2: {
                        MediaSessionImplBase.this.adjustVolume(arg4.arg1, 0);
                        break;
                    }
                    case 3: {
                        v0.onPrepare();
                        break;
                    }
                    case 4: {
                        v0.onPrepareFromMediaId(arg4.obj, arg4.getData());
                        break;
                    }
                    case 5: {
                        v0.onPrepareFromSearch(arg4.obj, arg4.getData());
                        break;
                    }
                    case 6: {
                        v0.onPrepareFromUri(arg4.obj, arg4.getData());
                        break;
                    }
                    case 7: {
                        v0.onPlay();
                        break;
                    }
                    case 8: {
                        v0.onPlayFromMediaId(arg4.obj, arg4.getData());
                        break;
                    }
                    case 9: {
                        v0.onPlayFromSearch(arg4.obj, arg4.getData());
                        break;
                    }
                    case 10: {
                        v0.onPlayFromUri(arg4.obj, arg4.getData());
                        break;
                    }
                    case 11: {
                        v0.onSkipToQueueItem(arg4.obj.longValue());
                        break;
                    }
                    case 12: {
                        v0.onPause();
                        break;
                    }
                    case 13: {
                        v0.onStop();
                        break;
                    }
                    case 14: {
                        v0.onSkipToNext();
                        break;
                    }
                    case 15: {
                        v0.onSkipToPrevious();
                        break;
                    }
                    case 16: {
                        v0.onFastForward();
                        break;
                    }
                    case 17: {
                        v0.onRewind();
                        break;
                    }
                    case 18: {
                        v0.onSeekTo(arg4.obj.longValue());
                        break;
                    }
                    case 19: {
                        v0.onSetRating(arg4.obj);
                        break;
                    }
                    case 20: {
                        v0.onCustomAction(arg4.obj, arg4.getData());
                        break;
                    }
                    case 21: {
                        v4 = arg4.obj;
                        Intent v1 = new Intent("android.intent.action.MEDIA_BUTTON");
                        v1.putExtra("android.intent.extra.KEY_EVENT", ((Parcelable)v4));
                        if(v0.onMediaButtonEvent(v1)) {
                            return;
                        }

                        this.onMediaButtonEvent(((KeyEvent)v4), v0);
                        break;
                    }
                    case 22: {
                        MediaSessionImplBase.this.setVolumeTo(arg4.arg1, 0);
                        break;
                    }
                    case 23: {
                        v0.onSetRepeatMode(arg4.arg1);
                        break;
                    }
                    case 24: {
                        v0.onSetShuffleModeEnabled(arg4.obj.booleanValue());
                        break;
                    }
                    case 25: {
                        v0.onAddQueueItem(arg4.obj);
                        break;
                    }
                    case 26: {
                        v0.onAddQueueItem(arg4.obj, arg4.arg1);
                        break;
                    }
                    case 27: {
                        v0.onRemoveQueueItem(arg4.obj);
                        break;
                    }
                    case 28: {
                        if(MediaSessionImplBase.this.mQueue == null) {
                            return;
                        }

                        v4 = arg4.arg1 < 0 || arg4.arg1 >= MediaSessionImplBase.this.mQueue.size() ? null : MediaSessionImplBase.this.mQueue.get(arg4.arg1);
                        if(v4 == null) {
                            return;
                        }

                        v0.onRemoveQueueItem(((QueueItem)v4).getDescription());
                        break;
                    }
                    case 29: {
                        v0.onSetCaptioningEnabled(arg4.obj.booleanValue());
                        break;
                    }
                    case 30: {
                        v0.onSetShuffleMode(arg4.arg1);
                        break;
                    }
                    case 31: {
                        v0.onSetRating(arg4.obj, arg4.getData());
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            private void onMediaButtonEvent(KeyEvent arg10, Callback arg11) {
                if(arg10 != null) {
                    if(arg10.getAction() != 0) {
                    }
                    else {
                        long v1 = 0;
                        long v3 = MediaSessionImplBase.this.mState == null ? v1 : MediaSessionImplBase.this.mState.getActions();
                        int v10 = arg10.getKeyCode();
                        if(v10 != 0x4F) {
                            switch(v10) {
                                case 85: {
                                    goto label_54;
                                }
                                case 86: {
                                    goto label_49;
                                }
                                case 87: {
                                    goto label_44;
                                }
                                case 88: {
                                    goto label_39;
                                }
                                case 89: {
                                    goto label_34;
                                }
                                case 90: {
                                    goto label_29;
                                }
                            }

                            switch(v10) {
                                case 126: {
                                    goto label_24;
                                }
                                case 127: {
                                    goto label_19;
                                }
                            }

                            return;
                        label_19:
                            if((v3 & 2) != v1) {
                                arg11.onPause();
                                return;
                            label_24:
                                if((v3 & 4) != v1) {
                                    arg11.onPlay();
                                    return;
                                label_49:
                                    if((v3 & 1) != v1) {
                                        arg11.onStop();
                                        return;
                                    label_34:
                                        if((v3 & 8) != v1) {
                                            arg11.onRewind();
                                            return;
                                        label_39:
                                            if((v3 & 16) != v1) {
                                                arg11.onSkipToPrevious();
                                                return;
                                            label_44:
                                                if((v3 & 0x20) != v1) {
                                                    arg11.onSkipToNext();
                                                    return;
                                                label_29:
                                                    if((v3 & 0x40) != v1) {
                                                        arg11.onFastForward();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                        label_54:
                            Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
                        }

                        return;
                    }
                }
            }

            public void post(int arg2, Object arg3, int arg4) {
                this.obtainMessage(arg2, arg4, 0, arg3).sendToTarget();
            }

            public void post(int arg1, Object arg2, Bundle arg3) {
                Message v1 = this.obtainMessage(arg1, arg2);
                v1.setData(arg3);
                v1.sendToTarget();
            }

            public void post(int arg2) {
                this.post(arg2, null);
            }

            public void post(int arg1, Object arg2) {
                this.obtainMessage(arg1, arg2).sendToTarget();
            }
        }

        static final int RCC_PLAYSTATE_NONE;
        final AudioManager mAudioManager;
        volatile Callback mCallback;
        boolean mCaptioningEnabled;
        private final Context mContext;
        final RemoteCallbackList mControllerCallbacks;
        boolean mDestroyed;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        boolean mIsActive;
        private boolean mIsMbrRegistered;
        private boolean mIsRccRegistered;
        int mLocalStream;
        final Object mLock;
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        final RemoteControlClient mRcc;
        int mRepeatMode;
        PendingIntent mSessionActivity;
        int mShuffleMode;
        boolean mShuffleModeEnabled;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        final String mTag;
        private final Token mToken;
        private android.support.v4.media.VolumeProviderCompat$Callback mVolumeCallback;
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;

        public MediaSessionImplBase(Context arg3, String arg4, ComponentName arg5, PendingIntent arg6) {
            super();
            this.mLock = new Object();
            this.mControllerCallbacks = new RemoteCallbackList();
            this.mDestroyed = false;
            this.mIsActive = false;
            this.mIsMbrRegistered = false;
            this.mIsRccRegistered = false;
            this.mVolumeCallback = new android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$1(this);
            if(arg5 == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }

            this.mContext = arg3;
            this.mPackageName = arg3.getPackageName();
            this.mAudioManager = arg3.getSystemService("audio");
            this.mTag = arg4;
            this.mMediaButtonReceiverComponentName = arg5;
            this.mMediaButtonReceiverIntent = arg6;
            this.mStub = new MediaSessionStub(this);
            this.mToken = new Token(this.mStub);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            this.mRcc = new RemoteControlClient(arg6);
        }

        void adjustVolume(int arg3, int arg4) {
            if(this.mVolumeType != 2) {
                this.mAudioManager.adjustStreamVolume(this.mLocalStream, arg3, arg4);
            }
            else if(this.mVolumeProvider != null) {
                this.mVolumeProvider.onAdjustVolume(arg3);
            }
        }

        RemoteControlClient$MetadataEditor buildRccMetadata(Bundle arg7) {
            Bitmap v2_1;
            Parcelable v2;
            RemoteControlClient$MetadataEditor v0 = this.mRcc.editMetadata(true);
            if(arg7 == null) {
                return v0;
            }

            int v3 = 100;
            if(arg7.containsKey("android.media.metadata.ART")) {
                v2 = arg7.getParcelable("android.media.metadata.ART");
                if(v2 != null) {
                    v2_1 = ((Bitmap)v2).copy(((Bitmap)v2).getConfig(), false);
                }

                v0.putBitmap(v3, v2_1);
            }
            else {
                if(!arg7.containsKey("android.media.metadata.ALBUM_ART")) {
                    goto label_26;
                }

                v2 = arg7.getParcelable("android.media.metadata.ALBUM_ART");
                if(v2 != null) {
                    v2_1 = ((Bitmap)v2).copy(((Bitmap)v2).getConfig(), false);
                }

                v0.putBitmap(v3, v2_1);
            }

        label_26:
            if(arg7.containsKey("android.media.metadata.ALBUM")) {
                v0.putString(1, arg7.getString("android.media.metadata.ALBUM"));
            }

            if(arg7.containsKey("android.media.metadata.ALBUM_ARTIST")) {
                v0.putString(13, arg7.getString("android.media.metadata.ALBUM_ARTIST"));
            }

            if(arg7.containsKey("android.media.metadata.ARTIST")) {
                v0.putString(2, arg7.getString("android.media.metadata.ARTIST"));
            }

            if(arg7.containsKey("android.media.metadata.AUTHOR")) {
                v0.putString(3, arg7.getString("android.media.metadata.AUTHOR"));
            }

            if(arg7.containsKey("android.media.metadata.COMPILATION")) {
                v0.putString(15, arg7.getString("android.media.metadata.COMPILATION"));
            }

            if(arg7.containsKey("android.media.metadata.COMPOSER")) {
                v0.putString(4, arg7.getString("android.media.metadata.COMPOSER"));
            }

            if(arg7.containsKey("android.media.metadata.DATE")) {
                v0.putString(5, arg7.getString("android.media.metadata.DATE"));
            }

            if(arg7.containsKey("android.media.metadata.DISC_NUMBER")) {
                v0.putLong(14, arg7.getLong("android.media.metadata.DISC_NUMBER"));
            }

            if(arg7.containsKey("android.media.metadata.DURATION")) {
                v0.putLong(9, arg7.getLong("android.media.metadata.DURATION"));
            }

            if(arg7.containsKey("android.media.metadata.GENRE")) {
                v0.putString(6, arg7.getString("android.media.metadata.GENRE"));
            }

            if(arg7.containsKey("android.media.metadata.TITLE")) {
                v0.putString(7, arg7.getString("android.media.metadata.TITLE"));
            }

            if(arg7.containsKey("android.media.metadata.TRACK_NUMBER")) {
                v0.putLong(0, arg7.getLong("android.media.metadata.TRACK_NUMBER"));
            }

            if(arg7.containsKey("android.media.metadata.WRITER")) {
                v0.putString(11, arg7.getString("android.media.metadata.WRITER"));
            }

            return v0;
        }

        public String getCallingPackage() {
            return null;
        }

        public Object getMediaSession() {
            return null;
        }

        public PlaybackStateCompat getPlaybackState() {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return this.mState;
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_6;
            }

            throw v1;
        }

        int getRccStateFromState(int arg1) {
            switch(arg1) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
                case 4: {
                    return 4;
                }
                case 5: {
                    return 5;
                }
                case 7: {
                    return 9;
                }
                case 6: 
                case 8: {
                    return 8;
                }
                case 9: {
                    return 7;
                }
                case 10: 
                case 11: {
                    return 6;
                }
            }

            return -1;
        }

        int getRccTransportControlFlagsFromActions(long arg8) {
            long v0 = 0;
            int v2 = (arg8 & 1) != v0 ? 0x20 : 0;
            if((arg8 & 2) != v0) {
                v2 |= 16;
            }

            if((arg8 & 4) != v0) {
                v2 |= 4;
            }

            if((arg8 & 8) != v0) {
                v2 |= 2;
            }

            if((arg8 & 16) != v0) {
                v2 |= 1;
            }

            if((arg8 & 0x20) != v0) {
                v2 |= 0x80;
            }

            if((arg8 & 0x40) != v0) {
                v2 |= 0x40;
            }

            if((arg8 & 0x200) != v0) {
                v2 |= 8;
            }

            return v2;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public boolean isActive() {
            return this.mIsActive;
        }

        void postToHandler(int arg2) {
            this.postToHandler(arg2, null);
        }

        void postToHandler(int arg2, Object arg3) {
            this.postToHandler(arg2, arg3, null);
        }

        void postToHandler(int arg2, int arg3) {
            this.postToHandler(arg2, null, arg3);
        }

        void postToHandler(int arg3, Object arg4, int arg5) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                if(this.mHandler != null) {
                    this.mHandler.post(arg3, arg4, arg5);
                }

                __monitor_exit(v0);
                return;
            label_9:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_9;
            }

            throw v3;
        }

        void postToHandler(int arg3, Object arg4, Bundle arg5) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                if(this.mHandler != null) {
                    this.mHandler.post(arg3, arg4, arg5);
                }

                __monitor_exit(v0);
                return;
            label_9:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_9;
            }

            throw v3;
        }

        void registerMediaButtonEventReceiver(PendingIntent arg1, ComponentName arg2) {
            this.mAudioManager.registerMediaButtonEventReceiver(arg2);
        }

        public void release() {
            this.mIsActive = false;
            this.mDestroyed = true;
            this.update();
            this.sendSessionDestroyed();
        }

        private void sendCaptioningEnabled(boolean arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onCaptioningEnabledChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendEvent(String arg3, Bundle arg4) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onEvent(arg3, arg4);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendExtras(Bundle arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onExtrasChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onMetadataChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueue(List arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onQueueChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onQueueTitleChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendRepeatMode(int arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onRepeatModeChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendSessionDestroyed() {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onSessionDestroyed();
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
            this.mControllerCallbacks.kill();
        }

        public void sendSessionEvent(String arg1, Bundle arg2) {
            this.sendEvent(arg1, arg2);
        }

        private void sendShuffleMode(int arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onShuffleModeChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendShuffleModeEnabled(boolean arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onShuffleModeChangedDeprecated(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendState(PlaybackStateCompat arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onPlaybackStateChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        void sendVolumeInfoChanged(ParcelableVolumeInfo arg3) {
            int v0 = this.mControllerCallbacks.beginBroadcast() - 1;
            while(v0 >= 0) {
                IInterface v1 = this.mControllerCallbacks.getBroadcastItem(v0);
                try {
                    ((IMediaControllerCallback)v1).onVolumeInfoChanged(arg3);
                    goto label_7;
                }
                catch(RemoteException ) {
                label_7:
                    --v0;
                    continue;
                }
            }

            this.mControllerCallbacks.finishBroadcast();
        }

        public void setActive(boolean arg2) {
            if(arg2 == this.mIsActive) {
                return;
            }

            this.mIsActive = arg2;
            if(this.update()) {
                this.setMetadata(this.mMetadata);
                this.setPlaybackState(this.mState);
            }
        }

        public void setCallback(Callback arg3, Handler arg4) {
            this.mCallback = arg3;
            if(arg3 != null) {
                if(arg4 == null) {
                    arg4 = new Handler();
                }

                Object v3 = this.mLock;
                __monitor_enter(v3);
                try {
                    if(this.mHandler != null) {
                        this.mHandler.removeCallbacksAndMessages(null);
                    }

                    this.mHandler = new MessageHandler(this, arg4.getLooper());
                    this.mCallback.setSessionImpl(((MediaSessionImpl)this), arg4);
                    __monitor_exit(v3);
                    return;
                label_21:
                    __monitor_exit(v3);
                }
                catch(Throwable v4) {
                    goto label_21;
                }

                throw v4;
            }
        }

        public void setCaptioningEnabled(boolean arg2) {
            if(this.mCaptioningEnabled != arg2) {
                this.mCaptioningEnabled = arg2;
                this.sendCaptioningEnabled(arg2);
            }
        }

        public void setExtras(Bundle arg1) {
            this.mExtras = arg1;
            this.sendExtras(arg1);
        }

        public void setFlags(int arg2) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                this.mFlags = arg2;
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                try {
                label_7:
                    __monitor_exit(v0);
                }
                catch(Throwable v2) {
                    goto label_7;
                }

                throw v2;
            }

            this.update();
        }

        public void setMediaButtonReceiver(PendingIntent arg1) {
        }

        public void setMetadata(MediaMetadataCompat arg3) {
            if(arg3 != null) {
                arg3 = new Builder(arg3, MediaSessionCompat.sMaxBitmapSize).build();
            }

            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                this.mMetadata = arg3;
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                try {
                label_21:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_21;
                }

                throw v3;
            }

            this.sendMetadata(arg3);
            if(!this.mIsActive) {
                return;
            }

            Bundle v3_1 = arg3 == null ? null : arg3.getBundle();
            this.buildRccMetadata(v3_1).apply();
        }

        public void setPlaybackState(PlaybackStateCompat arg4) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                this.mState = arg4;
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                try {
                label_22:
                    __monitor_exit(v0);
                }
                catch(Throwable v4) {
                    goto label_22;
                }

                throw v4;
            }

            this.sendState(arg4);
            if(!this.mIsActive) {
                return;
            }

            if(arg4 == null) {
                this.mRcc.setPlaybackState(0);
                this.mRcc.setTransportControlFlags(0);
            }
            else {
                this.setRccState(arg4);
                this.mRcc.setTransportControlFlags(this.getRccTransportControlFlagsFromActions(arg4.getActions()));
            }
        }

        public void setPlaybackToLocal(int arg7) {
            if(this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }

            this.mVolumeType = 1;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream)));
        }

        public void setPlaybackToRemote(VolumeProviderCompat arg8) {
            if(arg8 == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }

            if(this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }

            this.mVolumeType = 2;
            this.mVolumeProvider = arg8;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
            arg8.setCallback(this.mVolumeCallback);
        }

        public void setQueue(List arg1) {
            this.mQueue = arg1;
            this.sendQueue(arg1);
        }

        public void setQueueTitle(CharSequence arg1) {
            this.mQueueTitle = arg1;
            this.sendQueueTitle(arg1);
        }

        public void setRatingType(int arg1) {
            this.mRatingType = arg1;
        }

        void setRccState(PlaybackStateCompat arg2) {
            this.mRcc.setPlaybackState(this.getRccStateFromState(arg2.getState()));
        }

        public void setRepeatMode(int arg2) {
            if(this.mRepeatMode != arg2) {
                this.mRepeatMode = arg2;
                this.sendRepeatMode(arg2);
            }
        }

        public void setSessionActivity(PendingIntent arg2) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                this.mSessionActivity = arg2;
                __monitor_exit(v0);
                return;
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_6;
            }

            throw v2;
        }

        public void setShuffleMode(int arg2) {
            if(this.mShuffleMode != arg2) {
                this.mShuffleMode = arg2;
                this.sendShuffleMode(arg2);
            }
        }

        public void setShuffleModeEnabled(boolean arg2) {
            if(this.mShuffleModeEnabled != arg2) {
                this.mShuffleModeEnabled = arg2;
                this.sendShuffleModeEnabled(arg2);
            }
        }

        void setVolumeTo(int arg3, int arg4) {
            if(this.mVolumeType != 2) {
                this.mAudioManager.setStreamVolume(this.mLocalStream, arg3, arg4);
            }
            else if(this.mVolumeProvider != null) {
                this.mVolumeProvider.onSetVolumeTo(arg3);
            }
        }

        void unregisterMediaButtonEventReceiver(PendingIntent arg1, ComponentName arg2) {
            this.mAudioManager.unregisterMediaButtonEventReceiver(arg2);
        }

        boolean update() {
            boolean v1 = true;
            if(this.mIsActive) {
                if(!this.mIsMbrRegistered && (this.mFlags & 1) != 0) {
                    this.registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = true;
                }
                else if((this.mIsMbrRegistered) && (this.mFlags & 1) == 0) {
                    this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }

                if(!this.mIsRccRegistered && (this.mFlags & 2) != 0) {
                    this.mAudioManager.registerRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = true;
                    return v1;
                }

                if(!this.mIsRccRegistered) {
                    goto label_59;
                }

                if((this.mFlags & 2) != 0) {
                    goto label_59;
                }

                this.mRcc.setPlaybackState(0);
                this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                this.mIsRccRegistered = false;
                goto label_59;
            }
            else {
                if(this.mIsMbrRegistered) {
                    this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }

                if(this.mIsRccRegistered) {
                    this.mRcc.setPlaybackState(0);
                    this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = false;
                }

            label_59:
                v1 = false;
            }

            return v1;
        }
    }

    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    public final class QueueItem implements Parcelable {
        final class android.support.v4.media.session.MediaSessionCompat$QueueItem$1 implements Parcelable$Creator {
            android.support.v4.media.session.MediaSessionCompat$QueueItem$1() {
                super();
            }

            public QueueItem createFromParcel(Parcel arg2) {
                return new QueueItem(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public QueueItem[] newArray(int arg1) {
                return new QueueItem[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR = null;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;

        static {
            QueueItem.CREATOR = new android.support.v4.media.session.MediaSessionCompat$QueueItem$1();
        }

        QueueItem(Parcel arg3) {
            super();
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(arg3);
            this.mId = arg3.readLong();
        }

        public QueueItem(MediaDescriptionCompat arg2, long arg3) {
            this(null, arg2, arg3);
        }

        private QueueItem(Object arg4, MediaDescriptionCompat arg5, long arg6) {
            super();
            if(arg5 == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }

            if(arg6 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }

            this.mDescription = arg5;
            this.mId = arg6;
            this.mItem = arg4;
        }

        public int describeContents() {
            return 0;
        }

        public static QueueItem fromQueueItem(Object arg4) {
            if(arg4 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    return new QueueItem(arg4, MediaDescriptionCompat.fromMediaDescription(android.support.v4.media.session.MediaSessionCompatApi21$QueueItem.getDescription(arg4)), android.support.v4.media.session.MediaSessionCompatApi21$QueueItem.getQueueId(arg4));
                }
            }

            return null;
        }

        public static List fromQueueItemList(List arg2) {
            if(arg2 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    ArrayList v0 = new ArrayList();
                    Iterator v2 = arg2.iterator();
                    while(v2.hasNext()) {
                        ((List)v0).add(QueueItem.fromQueueItem(v2.next()));
                    }

                    return ((List)v0);
                }
            }

            return null;
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public long getQueueId() {
            return this.mId;
        }

        public Object getQueueItem() {
            if(this.mItem == null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    this.mItem = android.support.v4.media.session.MediaSessionCompatApi21$QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
                    return this.mItem;
                }
            }

            return this.mItem;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
        }

        public void writeToParcel(Parcel arg3, int arg4) {
            this.mDescription.writeToParcel(arg3, arg4);
            arg3.writeLong(this.mId);
        }
    }

    final class ResultReceiverWrapper implements Parcelable {
        final class android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1 implements Parcelable$Creator {
            android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1() {
                super();
            }

            public ResultReceiverWrapper createFromParcel(Parcel arg2) {
                return new ResultReceiverWrapper(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public ResultReceiverWrapper[] newArray(int arg1) {
                return new ResultReceiverWrapper[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        private ResultReceiver mResultReceiver;

        static {
            ResultReceiverWrapper.CREATOR = new android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1();
        }

        public ResultReceiverWrapper(ResultReceiver arg1) {
            super();
            this.mResultReceiver = arg1;
        }

        ResultReceiverWrapper(Parcel arg2) {
            super();
            this.mResultReceiver = ResultReceiver.CREATOR.createFromParcel(arg2);
        }

        static ResultReceiver access$400(ResultReceiverWrapper arg0) {
            return arg0.mResultReceiver;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            this.mResultReceiver.writeToParcel(arg2, arg3);
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface SessionFlags {
    }

    public final class Token implements Parcelable {
        final class android.support.v4.media.session.MediaSessionCompat$Token$1 implements Parcelable$Creator {
            android.support.v4.media.session.MediaSessionCompat$Token$1() {
                super();
            }

            public Token createFromParcel(Parcel arg3) {
                Parcelable v3;
                if(Build$VERSION.SDK_INT >= 21) {
                    v3 = arg3.readParcelable(null);
                }
                else {
                    IBinder v3_1 = arg3.readStrongBinder();
                }

                return new Token(v3);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public Token[] newArray(int arg1) {
                return new Token[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        private final IMediaSession mExtraBinder;
        private final Object mInner;

        static {
            Token.CREATOR = new android.support.v4.media.session.MediaSessionCompat$Token$1();
        }

        Token(Object arg2) {
            this(arg2, null);
        }

        Token(Object arg1, IMediaSession arg2) {
            super();
            this.mInner = arg1;
            this.mExtraBinder = arg2;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object arg4) {
            boolean v0 = true;
            if(this == (((Token)arg4))) {
                return 1;
            }

            if(!(arg4 instanceof Token)) {
                return 0;
            }

            if(this.mInner == null) {
                if(((Token)arg4).mInner == null) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }

            if(((Token)arg4).mInner == null) {
                return 0;
            }

            return this.mInner.equals(((Token)arg4).mInner);
        }

        public static Token fromToken(Object arg1) {
            return Token.fromToken(arg1, null);
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public static Token fromToken(Object arg2, IMediaSession arg3) {
            if(arg2 != null && Build$VERSION.SDK_INT >= 21) {
                return new Token(MediaSessionCompatApi21.verifyToken(arg2), arg3);
            }

            return null;
        }

        @RestrictTo(value={Scope.LIBRARY_GROUP}) public IMediaSession getExtraBinder() {
            return this.mExtraBinder;
        }

        public Object getToken() {
            return this.mInner;
        }

        public int hashCode() {
            if(this.mInner == null) {
                return 0;
            }

            return this.mInner.hashCode();
        }

        public void writeToParcel(Parcel arg3, int arg4) {
            if(Build$VERSION.SDK_INT >= 21) {
                arg3.writeParcelable(this.mInner, arg4);
            }
            else {
                arg3.writeStrongBinder(this.mInner);
            }
        }
    }

    static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    static final String ACTION_ARGUMENT_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    static final String ACTION_SET_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    static final String EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    static final String TAG = "MediaSessionCompat";
    private final ArrayList mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;
    static int sMaxBitmapSize;

    private MediaSessionCompat(Context arg3, MediaSessionImpl arg4) {
        super();
        this.mActiveListeners = new ArrayList();
        this.mImpl = arg4;
        if(Build$VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(arg4.getMediaSession())) {
            this.setCallback(new Callback() {
            });
        }

        this.mController = new MediaControllerCompat(arg3, this);
    }

    public MediaSessionCompat(Context arg2, String arg3) {
        this(arg2, arg3, null, null);
    }

    public MediaSessionCompat(Context arg3, String arg4, ComponentName arg5, PendingIntent arg6) {
        super();
        this.mActiveListeners = new ArrayList();
        if(arg3 == null) {
            throw new IllegalArgumentException("context must not be null");
        }

        if(TextUtils.isEmpty(((CharSequence)arg4))) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }

        if(arg5 == null) {
            arg5 = MediaButtonReceiver.getMediaButtonReceiverComponent(arg3);
            if(arg5 == null) {
                Log.w("MediaSessionCompat", "Couldn\'t find a unique registered media button receiver in the given context.");
            }
        }

        if(arg5 != null && arg6 == null) {
            Intent v6 = new Intent("android.intent.action.MEDIA_BUTTON");
            v6.setComponent(arg5);
            arg6 = PendingIntent.getBroadcast(arg3, 0, v6, 0);
        }

        if(Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaSessionImplApi21(arg3, arg4);
            this.setCallback(new Callback() {
            });
            this.mImpl.setMediaButtonReceiver(arg6);
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            this.mImpl = new MediaSessionImplApi19(arg3, arg4, arg5, arg6);
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            this.mImpl = new MediaSessionImplApi18(arg3, arg4, arg5, arg6);
        }
        else {
            this.mImpl = new MediaSessionImplBase(arg3, arg4, arg5, arg6);
        }

        this.mController = new MediaControllerCompat(arg3, this);
        if(MediaSessionCompat.sMaxBitmapSize == 0) {
            MediaSessionCompat.sMaxBitmapSize = ((int)TypedValue.applyDimension(1, 320f, arg3.getResources().getDisplayMetrics()));
        }
    }

    static PlaybackStateCompat access$500(PlaybackStateCompat arg0, MediaMetadataCompat arg1) {
        return MediaSessionCompat.getStateWithUpdatedPosition(arg0, arg1);
    }

    public void addOnActiveChangeListener(OnActiveChangeListener arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }

        this.mActiveListeners.add(arg2);
    }

    public static MediaSessionCompat fromMediaSession(Context arg2, Object arg3) {
        if(arg2 != null && arg3 != null && Build$VERSION.SDK_INT >= 21) {
            return new MediaSessionCompat(arg2, new MediaSessionImplApi21(arg3));
        }

        return null;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }

    public MediaControllerCompat getController() {
        return this.mController;
    }

    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }

    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    private static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat arg14, MediaMetadataCompat arg15) {
        long v9;
        if(arg14 != null) {
            long v2 = -1;
            if(arg14.getPosition() == v2) {
            }
            else {
                if(arg14.getState() == 3 || arg14.getState() == 4 || arg14.getState() == 5) {
                    long v0 = arg14.getLastPositionUpdateTime();
                    long v4 = 0;
                    if(v0 > v4) {
                        long v12 = SystemClock.elapsedRealtime();
                        long v8 = (((long)(arg14.getPlaybackSpeed() * (((float)(v12 - v0)))))) + arg14.getPosition();
                        if(arg15 != null && (arg15.containsKey("android.media.metadata.DURATION"))) {
                            v2 = arg15.getLong("android.media.metadata.DURATION");
                        }

                        if(v2 >= v4 && v8 > v2) {
                            v9 = v2;
                        }
                        else if(v8 < v4) {
                            v9 = v4;
                        }
                        else {
                            v9 = v8;
                        }

                        return new android.support.v4.media.session.PlaybackStateCompat$Builder(arg14).setState(arg14.getState(), v9, arg14.getPlaybackSpeed(), v12).build();
                    }
                }

                return arg14;
            }
        }

        return arg14;
    }

    public boolean isActive() {
        return this.mImpl.isActive();
    }

    public void release() {
        this.mImpl.release();
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }

        this.mActiveListeners.remove(arg2);
    }

    public void sendSessionEvent(String arg2, Bundle arg3) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }

        this.mImpl.sendSessionEvent(arg2, arg3);
    }

    public void setActive(boolean arg2) {
        this.mImpl.setActive(arg2);
        Iterator v2 = this.mActiveListeners.iterator();
        while(v2.hasNext()) {
            v2.next().onActiveChanged();
        }
    }

    public void setCallback(Callback arg2) {
        this.setCallback(arg2, null);
    }

    public void setCallback(Callback arg2, Handler arg3) {
        MediaSessionImpl v0 = this.mImpl;
        if(arg3 != null) {
        }
        else {
            arg3 = new Handler();
        }

        v0.setCallback(arg2, arg3);
    }

    public void setCaptioningEnabled(boolean arg2) {
        this.mImpl.setCaptioningEnabled(arg2);
    }

    public void setExtras(Bundle arg2) {
        this.mImpl.setExtras(arg2);
    }

    public void setFlags(int arg2) {
        this.mImpl.setFlags(arg2);
    }

    public void setMediaButtonReceiver(PendingIntent arg2) {
        this.mImpl.setMediaButtonReceiver(arg2);
    }

    public void setMetadata(MediaMetadataCompat arg2) {
        this.mImpl.setMetadata(arg2);
    }

    public void setPlaybackState(PlaybackStateCompat arg2) {
        this.mImpl.setPlaybackState(arg2);
    }

    public void setPlaybackToLocal(int arg2) {
        this.mImpl.setPlaybackToLocal(arg2);
    }

    public void setPlaybackToRemote(VolumeProviderCompat arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }

        this.mImpl.setPlaybackToRemote(arg2);
    }

    public void setQueue(List arg2) {
        this.mImpl.setQueue(arg2);
    }

    public void setQueueTitle(CharSequence arg2) {
        this.mImpl.setQueueTitle(arg2);
    }

    public void setRatingType(int arg2) {
        this.mImpl.setRatingType(arg2);
    }

    public void setRepeatMode(int arg2) {
        this.mImpl.setRepeatMode(arg2);
    }

    public void setSessionActivity(PendingIntent arg2) {
        this.mImpl.setSessionActivity(arg2);
    }

    public void setShuffleMode(int arg2) {
        this.mImpl.setShuffleMode(arg2);
    }

    @Deprecated public void setShuffleModeEnabled(boolean arg2) {
        this.mImpl.setShuffleModeEnabled(arg2);
    }
}

