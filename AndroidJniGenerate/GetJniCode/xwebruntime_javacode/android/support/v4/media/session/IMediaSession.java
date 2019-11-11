package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public interface IMediaSession extends IInterface {
    public abstract class Stub extends Binder implements IMediaSession {
        class Proxy implements IMediaSession {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public void addQueueItem(MediaDescriptionCompat arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(41, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void addQueueItemAt(MediaDescriptionCompat arg5, int arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    v0.writeInt(arg6);
                    this.mRemote.transact(42, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void adjustVolume(int arg4, int arg5, String arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg4);
                    v0.writeInt(arg5);
                    v0.writeString(arg6);
                    this.mRemote.transact(11, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void fastForward() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(22, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public Bundle getExtras() throws RemoteException {
                Object v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(0x1F, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        v2_1 = Bundle.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                Bundle v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return ((Bundle)v2_1);
            }

            public long getFlags() throws RemoteException {
                long v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(9, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readLong();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.media.session.IMediaSession";
            }

            public PendingIntent getLaunchPendingIntent() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(8, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = PendingIntent.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                PendingIntent v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public MediaMetadataCompat getMetadata() throws RemoteException {
                Object v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(27, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        v2_1 = MediaMetadataCompat.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                MediaMetadataCompat v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return ((MediaMetadataCompat)v2_1);
            }

            public String getPackageName() throws RemoteException {
                String v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(6, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readString();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(28, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = PlaybackStateCompat.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                PlaybackStateCompat v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public List getQueue() throws RemoteException {
                ArrayList v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(29, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.createTypedArrayList(QueueItem.CREATOR);
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return ((List)v2_1);
            }

            public CharSequence getQueueTitle() throws RemoteException {
                Object v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(30, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        v2_1 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                CharSequence v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return ((CharSequence)v2_1);
            }

            public int getRatingType() throws RemoteException {
                int v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(0x20, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readInt();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public int getRepeatMode() throws RemoteException {
                int v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(37, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readInt();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public int getShuffleMode() throws RemoteException {
                int v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(0x2F, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readInt();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public String getTag() throws RemoteException {
                String v2_1;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(7, v0, v1, 0);
                    v1.readException();
                    v2_1 = v1.readString();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
                return v2_1;
            }

            public ParcelableVolumeInfo getVolumeAttributes() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(10, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v2_1 = ParcelableVolumeInfo.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_14;
                    }

                    goto label_15;
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

            label_14:
                ParcelableVolumeInfo v2_2 = null;
            label_15:
                v1.recycle();
                v0.recycle();
                return v2_2;
            }

            public boolean isCaptioningEnabled() throws RemoteException {
                boolean v4;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v4 = false;
                    this.mRemote.transact(45, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_12;
                    }
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v4 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v4;
            }

            public boolean isShuffleModeEnabledDeprecated() throws RemoteException {
                boolean v4;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v4 = false;
                    this.mRemote.transact(38, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_12;
                    }
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v4 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v4;
            }

            public boolean isTransportControlEnabled() throws RemoteException {
                boolean v4;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v4 = false;
                    this.mRemote.transact(5, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_12;
                    }
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v4 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v4;
            }

            public void next() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(20, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void pause() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(18, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void play() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(13, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void playFromMediaId(String arg4, Bundle arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(14, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void playFromSearch(String arg4, Bundle arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(15, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void playFromUri(Uri arg5, Bundle arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(16, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void prepare() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(33, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void prepareFromMediaId(String arg4, Bundle arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(34, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void prepareFromSearch(String arg4, Bundle arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(35, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void prepareFromUri(Uri arg5, Bundle arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(36, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void previous() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(21, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void rate(RatingCompat arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(25, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void rateWithExtras(RatingCompat arg5, Bundle arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(51, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void registerCallbackListener(IMediaControllerCallback arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    this.mRemote.transact(3, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void removeQueueItem(MediaDescriptionCompat arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(43, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void removeQueueItemAt(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg5);
                    this.mRemote.transact(44, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void rewind() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(23, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void seekTo(long arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeLong(arg4);
                    this.mRemote.transact(24, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void sendCommand(String arg4, Bundle arg5, ResultReceiverWrapper arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(1, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void sendCustomAction(String arg4, Bundle arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeString(arg4);
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(26, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public boolean sendMediaButton(KeyEvent arg6) throws RemoteException {
                boolean v2;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v2 = true;
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(2, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() == 0) {
                        goto label_18;
                    }
                }
                catch(Throwable v6) {
                    v1.recycle();
                    v0.recycle();
                    throw v6;
                }

                goto label_19;
            label_18:
                v2 = false;
            label_19:
                v1.recycle();
                v0.recycle();
                return v2;
            }

            public void setCaptioningEnabled(boolean arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(((int)arg5));
                    this.mRemote.transact(46, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void setRepeatMode(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg5);
                    this.mRemote.transact(39, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void setShuffleMode(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg5);
                    this.mRemote.transact(0x30, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void setShuffleModeEnabledDeprecated(boolean arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(((int)arg5));
                    this.mRemote.transact(40, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }

            public void setVolumeTo(int arg4, int arg5, String arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeInt(arg4);
                    v0.writeInt(arg5);
                    v0.writeString(arg6);
                    this.mRemote.transact(12, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void skipToQueueItem(long arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    v0.writeLong(arg4);
                    this.mRemote.transact(17, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v4) {
                    v1.recycle();
                    v0.recycle();
                    throw v4;
                }

                v1.recycle();
                v0.recycle();
            }

            public void stop() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    this.mRemote.transact(19, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v2) {
                    v1.recycle();
                    v0.recycle();
                    throw v2;
                }

                v1.recycle();
                v0.recycle();
            }

            public void unregisterCallbackListener(IMediaControllerCallback arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    IBinder v5_1 = arg5 != null ? arg5.asBinder() : null;
                    v0.writeStrongBinder(v5_1);
                    this.mRemote.transact(4, v0, v1, 0);
                    v1.readException();
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

                v1.recycle();
                v0.recycle();
            }
        }

        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_addQueueItem = 41;
        static final int TRANSACTION_addQueueItemAt = 42;
        static final int TRANSACTION_adjustVolume = 11;
        static final int TRANSACTION_fastForward = 22;
        static final int TRANSACTION_getExtras = 0x1F;
        static final int TRANSACTION_getFlags = 9;
        static final int TRANSACTION_getLaunchPendingIntent = 8;
        static final int TRANSACTION_getMetadata = 27;
        static final int TRANSACTION_getPackageName = 6;
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getQueue = 29;
        static final int TRANSACTION_getQueueTitle = 30;
        static final int TRANSACTION_getRatingType = 0x20;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getShuffleMode = 0x2F;
        static final int TRANSACTION_getTag = 7;
        static final int TRANSACTION_getVolumeAttributes = 10;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_isShuffleModeEnabledDeprecated = 38;
        static final int TRANSACTION_isTransportControlEnabled = 5;
        static final int TRANSACTION_next = 20;
        static final int TRANSACTION_pause = 18;
        static final int TRANSACTION_play = 13;
        static final int TRANSACTION_playFromMediaId = 14;
        static final int TRANSACTION_playFromSearch = 15;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_prepare = 33;
        static final int TRANSACTION_prepareFromMediaId = 34;
        static final int TRANSACTION_prepareFromSearch = 35;
        static final int TRANSACTION_prepareFromUri = 36;
        static final int TRANSACTION_previous = 21;
        static final int TRANSACTION_rate = 25;
        static final int TRANSACTION_rateWithExtras = 51;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_removeQueueItem = 43;
        static final int TRANSACTION_removeQueueItemAt = 44;
        static final int TRANSACTION_rewind = 23;
        static final int TRANSACTION_seekTo = 24;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendCustomAction = 26;
        static final int TRANSACTION_sendMediaButton = 2;
        static final int TRANSACTION_setCaptioningEnabled = 46;
        static final int TRANSACTION_setRepeatMode = 39;
        static final int TRANSACTION_setShuffleMode = 0x30;
        static final int TRANSACTION_setShuffleModeEnabledDeprecated = 40;
        static final int TRANSACTION_setVolumeTo = 12;
        static final int TRANSACTION_skipToQueueItem = 17;
        static final int TRANSACTION_stop = 19;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.v4.media.session.IMediaSession");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IMediaSession asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if(v0 != null && ((v0 instanceof IMediaSession))) {
                return ((IMediaSession)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg4, Parcel arg5, Parcel arg6, int arg7) throws RemoteException {
            Bundle v7_1;
            Object v1_1;
            Uri v4_2;
            Object v4_1;
            MediaDescriptionCompat v1 = null;
            if(arg4 == 51) {
                goto label_405;
            }

            if(arg4 == 0x5F4E5446) {
                goto label_402;
            }

            boolean v0 = false;
            switch(arg4) {
                case 1: {
                    goto label_385;
                }
                case 2: {
                    goto label_374;
                }
                case 3: {
                    goto label_367;
                }
                case 4: {
                    goto label_360;
                }
                case 5: {
                    goto label_354;
                }
                case 6: {
                    goto label_348;
                }
                case 7: {
                    goto label_342;
                }
                case 8: {
                    goto label_332;
                }
                case 9: {
                    goto label_326;
                }
                case 10: {
                    goto label_316;
                }
                case 11: {
                    goto label_308;
                }
                case 12: {
                    goto label_300;
                }
                case 13: {
                    goto label_295;
                }
                case 14: {
                    goto label_284;
                }
                case 15: {
                    goto label_273;
                }
                case 16: {
                    goto label_257;
                }
                case 17: {
                    goto label_251;
                }
                case 18: {
                    goto label_246;
                }
                case 19: {
                    goto label_241;
                }
                case 20: {
                    goto label_236;
                }
                case 21: {
                    goto label_231;
                }
                case 22: {
                    goto label_226;
                }
                case 23: {
                    goto label_221;
                }
                case 24: {
                    goto label_215;
                }
                case 25: {
                    goto label_205;
                }
                case 26: {
                    goto label_194;
                }
                case 27: {
                    goto label_184;
                }
                case 28: {
                    goto label_174;
                }
                case 29: {
                    goto label_168;
                }
                case 30: {
                    goto label_158;
                }
                case 31: {
                    goto label_148;
                }
                case 32: {
                    goto label_142;
                }
                case 33: {
                    goto label_137;
                }
                case 34: {
                    goto label_126;
                }
                case 35: {
                    goto label_115;
                }
                case 36: {
                    goto label_99;
                }
                case 37: {
                    goto label_93;
                }
                case 38: {
                    goto label_87;
                }
                case 39: {
                    goto label_81;
                }
                case 40: {
                    goto label_73;
                }
                case 41: {
                    goto label_63;
                }
                case 42: {
                    goto label_52;
                }
                case 43: {
                    goto label_42;
                }
                case 44: {
                    goto label_36;
                }
                case 45: {
                    goto label_30;
                }
                case 46: {
                    goto label_22;
                }
                case 47: {
                    goto label_16;
                }
                case 48: {
                    goto label_10;
                }
            }

            return super.onTransact(arg4, arg5, arg6, arg7);
        label_354:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            boolean v4 = this.isTransportControlEnabled();
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_226:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.fastForward();
            arg6.writeNoException();
            return 1;
        label_99:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v4_1 = Uri.CREATOR.createFromParcel(arg5);
            }
            else {
                v4_2 = ((Uri)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.prepareFromUri(((Uri)v4_1), ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_231:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.previous();
            arg6.writeNoException();
            return 1;
        label_360:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.unregisterCallbackListener(android.support.v4.media.session.IMediaControllerCallback$Stub.asInterface(arg5.readStrongBinder()));
            arg6.writeNoException();
            return 1;
        label_236:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.next();
            arg6.writeNoException();
            return 1;
        label_367:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.registerCallbackListener(android.support.v4.media.session.IMediaControllerCallback$Stub.asInterface(arg5.readStrongBinder()));
            arg6.writeNoException();
            return 1;
        label_241:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.stop();
            arg6.writeNoException();
            return 1;
        label_115:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            String v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.prepareFromSearch(v4_3, ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_374:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = KeyEvent.CREATOR.createFromParcel(arg5);
            }

            v4 = this.sendMediaButton(((KeyEvent)v1));
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_246:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.pause();
            arg6.writeNoException();
            return 1;
        label_251:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.skipToQueueItem(arg5.readLong());
            arg6.writeNoException();
            return 1;
        label_126:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.prepareFromMediaId(v4_3, ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_385:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                Object v7 = Bundle.CREATOR.createFromParcel(arg5);
            }
            else {
                v7_1 = ((Bundle)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = ResultReceiverWrapper.CREATOR.createFromParcel(arg5);
            }

            this.sendCommand(v4_3, v7_1, ((ResultReceiverWrapper)v1));
            arg6.writeNoException();
            return 1;
        label_257:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v4_1 = Uri.CREATOR.createFromParcel(arg5);
            }
            else {
                v4_2 = ((Uri)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.playFromUri(((Uri)v4_1), ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_137:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.prepare();
            arg6.writeNoException();
            return 1;
        label_10:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.setShuffleMode(arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_142:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            arg4 = this.getRatingType();
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_16:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            arg4 = this.getShuffleMode();
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_273:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.playFromSearch(v4_3, ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_148:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            Bundle v4_4 = this.getExtras();
            arg6.writeNoException();
            if(v4_4 != null) {
                arg6.writeInt(1);
                v4_4.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_22:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.setCaptioningEnabled(v0);
            arg6.writeNoException();
            return 1;
        label_284:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.playFromMediaId(v4_3, ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_158:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            CharSequence v4_5 = this.getQueueTitle();
            arg6.writeNoException();
            if(v4_5 != null) {
                arg6.writeInt(1);
                TextUtils.writeToParcel(v4_5, arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_30:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4 = this.isCaptioningEnabled();
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_36:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.removeQueueItemAt(arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_295:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.play();
            arg6.writeNoException();
            return 1;
        label_168:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            List v4_6 = this.getQueue();
            arg6.writeNoException();
            arg6.writeTypedList(v4_6);
            return 1;
        label_42:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = MediaDescriptionCompat.CREATOR.createFromParcel(arg5);
            }

            this.removeQueueItem(v1);
            arg6.writeNoException();
            return 1;
        label_300:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.setVolumeTo(arg5.readInt(), arg5.readInt(), arg5.readString());
            arg6.writeNoException();
            return 1;
        label_174:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            PlaybackStateCompat v4_7 = this.getPlaybackState();
            arg6.writeNoException();
            if(v4_7 != null) {
                arg6.writeInt(1);
                v4_7.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_308:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.adjustVolume(arg5.readInt(), arg5.readInt(), arg5.readString());
            arg6.writeNoException();
            return 1;
        label_52:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = MediaDescriptionCompat.CREATOR.createFromParcel(arg5);
            }

            this.addQueueItemAt(v1, arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_184:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            MediaMetadataCompat v4_8 = this.getMetadata();
            arg6.writeNoException();
            if(v4_8 != null) {
                arg6.writeInt(1);
                v4_8.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_316:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            ParcelableVolumeInfo v4_9 = this.getVolumeAttributes();
            arg6.writeNoException();
            if(v4_9 != null) {
                arg6.writeInt(1);
                v4_9.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_63:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = MediaDescriptionCompat.CREATOR.createFromParcel(arg5);
            }

            this.addQueueItem(v1);
            arg6.writeNoException();
            return 1;
        label_194:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = arg5.readString();
            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.sendCustomAction(v4_3, ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        label_326:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            long v4_10 = this.getFlags();
            arg6.writeNoException();
            arg6.writeLong(v4_10);
            return 1;
        label_73:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.setShuffleModeEnabledDeprecated(v0);
            arg6.writeNoException();
            return 1;
        label_332:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            PendingIntent v4_11 = this.getLaunchPendingIntent();
            arg6.writeNoException();
            if(v4_11 != null) {
                arg6.writeInt(1);
                v4_11.writeToParcel(arg6, 1);
            }
            else {
                arg6.writeInt(0);
            }

            return 1;
        label_205:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v1_1 = RatingCompat.CREATOR.createFromParcel(arg5);
            }

            this.rate(((RatingCompat)v1));
            arg6.writeNoException();
            return 1;
        label_81:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.setRepeatMode(arg5.readInt());
            arg6.writeNoException();
            return 1;
        label_342:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = this.getTag();
            arg6.writeNoException();
            arg6.writeString(v4_3);
            return 1;
        label_215:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.seekTo(arg5.readLong());
            arg6.writeNoException();
            return 1;
        label_87:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4 = this.isShuffleModeEnabledDeprecated();
            arg6.writeNoException();
            arg6.writeInt(((int)v4));
            return 1;
        label_348:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            v4_3 = this.getPackageName();
            arg6.writeNoException();
            arg6.writeString(v4_3);
            return 1;
        label_221:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.rewind();
            arg6.writeNoException();
            return 1;
        label_93:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            arg4 = this.getRepeatMode();
            arg6.writeNoException();
            arg6.writeInt(arg4);
            return 1;
        label_402:
            arg6.writeString("android.support.v4.media.session.IMediaSession");
            return 1;
        label_405:
            arg5.enforceInterface("android.support.v4.media.session.IMediaSession");
            if(arg5.readInt() != 0) {
                v4_1 = RatingCompat.CREATOR.createFromParcel(arg5);
            }
            else {
                RatingCompat v4_12 = ((RatingCompat)v1);
            }

            if(arg5.readInt() != 0) {
                v1_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.rateWithExtras(((RatingCompat)v4_1), ((Bundle)v1));
            arg6.writeNoException();
            return 1;
        }
    }

    void addQueueItem(MediaDescriptionCompat arg1) throws RemoteException;

    void addQueueItemAt(MediaDescriptionCompat arg1, int arg2) throws RemoteException;

    void adjustVolume(int arg1, int arg2, String arg3) throws RemoteException;

    void fastForward() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    long getFlags() throws RemoteException;

    PendingIntent getLaunchPendingIntent() throws RemoteException;

    MediaMetadataCompat getMetadata() throws RemoteException;

    String getPackageName() throws RemoteException;

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    List getQueue() throws RemoteException;

    CharSequence getQueueTitle() throws RemoteException;

    int getRatingType() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    String getTag() throws RemoteException;

    ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    boolean isShuffleModeEnabledDeprecated() throws RemoteException;

    boolean isTransportControlEnabled() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFromMediaId(String arg1, Bundle arg2) throws RemoteException;

    void playFromSearch(String arg1, Bundle arg2) throws RemoteException;

    void playFromUri(Uri arg1, Bundle arg2) throws RemoteException;

    void prepare() throws RemoteException;

    void prepareFromMediaId(String arg1, Bundle arg2) throws RemoteException;

    void prepareFromSearch(String arg1, Bundle arg2) throws RemoteException;

    void prepareFromUri(Uri arg1, Bundle arg2) throws RemoteException;

    void previous() throws RemoteException;

    void rate(RatingCompat arg1) throws RemoteException;

    void rateWithExtras(RatingCompat arg1, Bundle arg2) throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback arg1) throws RemoteException;

    void removeQueueItem(MediaDescriptionCompat arg1) throws RemoteException;

    void removeQueueItemAt(int arg1) throws RemoteException;

    void rewind() throws RemoteException;

    void seekTo(long arg1) throws RemoteException;

    void sendCommand(String arg1, Bundle arg2, ResultReceiverWrapper arg3) throws RemoteException;

    void sendCustomAction(String arg1, Bundle arg2) throws RemoteException;

    boolean sendMediaButton(KeyEvent arg1) throws RemoteException;

    void setCaptioningEnabled(boolean arg1) throws RemoteException;

    void setRepeatMode(int arg1) throws RemoteException;

    void setShuffleMode(int arg1) throws RemoteException;

    void setShuffleModeEnabledDeprecated(boolean arg1) throws RemoteException;

    void setVolumeTo(int arg1, int arg2, String arg3) throws RemoteException;

    void skipToQueueItem(long arg1) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback arg1) throws RemoteException;
}

