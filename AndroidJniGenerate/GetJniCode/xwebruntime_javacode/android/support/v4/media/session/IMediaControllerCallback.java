package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import java.util.List;

public interface IMediaControllerCallback extends IInterface {
    public abstract class Stub extends Binder implements IMediaControllerCallback {
        class Proxy implements IMediaControllerCallback {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.media.session.IMediaControllerCallback";
            }

            public void onCaptioningEnabledChanged(boolean arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(((int)arg5));
                    this.mRemote.transact(11, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onEvent(String arg3, Bundle arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeString(arg3);
                    if(arg4 != null) {
                        v0.writeInt(1);
                        arg4.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(1, v0, null, 1);
                }
                catch(Throwable v3) {
                    v0.recycle();
                    throw v3;
                }

                v0.recycle();
            }

            public void onExtrasChanged(Bundle arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(7, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onMetadataChanged(MediaMetadataCompat arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(4, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onPlaybackStateChanged(PlaybackStateCompat arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(3, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onQueueChanged(List arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeTypedList(arg5);
                    this.mRemote.transact(5, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onQueueTitleChanged(CharSequence arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        TextUtils.writeToParcel(arg5, v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(6, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onRepeatModeChanged(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(arg5);
                    this.mRemote.transact(9, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onSessionDestroyed() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    this.mRemote.transact(2, v0, null, 1);
                }
                catch(Throwable v1) {
                    v0.recycle();
                    throw v1;
                }

                v0.recycle();
            }

            public void onShuffleModeChanged(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(arg5);
                    this.mRemote.transact(12, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onShuffleModeChangedDeprecated(boolean arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    v0.writeInt(((int)arg5));
                    this.mRemote.transact(10, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(8, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }
        }

        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
        static final int TRANSACTION_onCaptioningEnabledChanged = 11;
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onRepeatModeChanged = 9;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onShuffleModeChanged = 12;
        static final int TRANSACTION_onShuffleModeChangedDeprecated = 10;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.v4.media.session.IMediaControllerCallback");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IMediaControllerCallback asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(v0 != null && ((v0 instanceof IMediaControllerCallback))) {
                return ((IMediaControllerCallback)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg4, Parcel arg5, Parcel arg6, int arg7) throws RemoteException {
            Object v2_1;
            if(arg4 == 0x5F4E5446) {
                goto label_97;
            }

            boolean v0 = false;
            ParcelableVolumeInfo v2 = null;
            switch(arg4) {
                case 1: {
                    goto label_87;
                }
                case 2: {
                    goto label_83;
                }
                case 3: {
                    goto label_74;
                }
                case 4: {
                    goto label_65;
                }
                case 5: {
                    goto label_59;
                }
                case 6: {
                    goto label_50;
                }
                case 7: {
                    goto label_41;
                }
                case 8: {
                    goto label_32;
                }
                case 9: {
                    goto label_27;
                }
                case 10: {
                    goto label_20;
                }
                case 11: {
                    goto label_13;
                }
                case 12: {
                    goto label_8;
                }
            }

            return super.onTransact(arg4, arg5, arg6, arg7);
        label_65:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = MediaMetadataCompat.CREATOR.createFromParcel(arg5);
            }

            this.onMetadataChanged(((MediaMetadataCompat)v2));
            return 1;
        label_8:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.onShuffleModeChanged(arg5.readInt());
            return 1;
        label_41:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.onExtrasChanged(((Bundle)v2));
            return 1;
        label_74:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = PlaybackStateCompat.CREATOR.createFromParcel(arg5);
            }

            this.onPlaybackStateChanged(((PlaybackStateCompat)v2));
            return 1;
        label_13:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.onCaptioningEnabledChanged(v0);
            return 1;
        label_50:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg5);
            }

            this.onQueueTitleChanged(((CharSequence)v2));
            return 1;
        label_83:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.onSessionDestroyed();
            return 1;
        label_20:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v0 = true;
            }

            this.onShuffleModeChangedDeprecated(v0);
            return 1;
        label_87:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            String v4 = arg5.readString();
            if(arg5.readInt() != 0) {
                v2_1 = Bundle.CREATOR.createFromParcel(arg5);
            }

            this.onEvent(v4, ((Bundle)v2));
            return 1;
        label_59:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.onQueueChanged(arg5.createTypedArrayList(QueueItem.CREATOR));
            return 1;
        label_27:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.onRepeatModeChanged(arg5.readInt());
            return 1;
        label_32:
            arg5.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if(arg5.readInt() != 0) {
                v2_1 = ParcelableVolumeInfo.CREATOR.createFromParcel(arg5);
            }

            this.onVolumeInfoChanged(v2);
            return 1;
        label_97:
            arg6.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return 1;
        }
    }

    void onCaptioningEnabledChanged(boolean arg1) throws RemoteException;

    void onEvent(String arg1, Bundle arg2) throws RemoteException;

    void onExtrasChanged(Bundle arg1) throws RemoteException;

    void onMetadataChanged(MediaMetadataCompat arg1) throws RemoteException;

    void onPlaybackStateChanged(PlaybackStateCompat arg1) throws RemoteException;

    void onQueueChanged(List arg1) throws RemoteException;

    void onQueueTitleChanged(CharSequence arg1) throws RemoteException;

    void onRepeatModeChanged(int arg1) throws RemoteException;

    void onSessionDestroyed() throws RemoteException;

    void onShuffleModeChanged(int arg1) throws RemoteException;

    void onShuffleModeChangedDeprecated(boolean arg1) throws RemoteException;

    void onVolumeInfoChanged(ParcelableVolumeInfo arg1) throws RemoteException;
}

