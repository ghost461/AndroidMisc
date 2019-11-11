package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationSideChannel extends IInterface {
    public abstract class Stub extends Binder implements INotificationSideChannel {
        class Proxy implements INotificationSideChannel {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void cancel(String arg3, int arg4, String arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    v0.writeString(arg3);
                    v0.writeInt(arg4);
                    v0.writeString(arg5);
                    this.mRemote.transact(2, v0, null, 1);
                }
                catch(Throwable v3) {
                    v0.recycle();
                    throw v3;
                }

                v0.recycle();
            }

            public void cancelAll(String arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    v0.writeString(arg5);
                    this.mRemote.transact(3, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.app.INotificationSideChannel";
            }

            public void notify(String arg3, int arg4, String arg5, Notification arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    v0.writeString(arg3);
                    v0.writeInt(arg4);
                    v0.writeString(arg5);
                    if(arg6 != null) {
                        v0.writeInt(1);
                        arg6.writeToParcel(v0, 0);
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
        }

        private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.v4.app.INotificationSideChannel");
        }

        public IBinder asBinder() {
            return this;
        }

        public static INotificationSideChannel asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            if(v0 != null && ((v0 instanceof INotificationSideChannel))) {
                return ((INotificationSideChannel)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            Object v4;
            if(arg3 == 0x5F4E5446) {
                goto label_31;
            }

            switch(arg3) {
                case 1: {
                    goto label_18;
                }
                case 2: {
                    goto label_11;
                }
                case 3: {
                    goto label_6;
                }
            }

            return super.onTransact(arg3, arg4, arg5, arg6);
        label_18:
            arg4.enforceInterface("android.support.v4.app.INotificationSideChannel");
            String v3 = arg4.readString();
            int v5 = arg4.readInt();
            String v6 = arg4.readString();
            if(arg4.readInt() != 0) {
                v4 = Notification.CREATOR.createFromParcel(arg4);
            }
            else {
                Notification v4_1 = null;
            }

            this.notify(v3, v5, v6, ((Notification)v4));
            return 1;
        label_6:
            arg4.enforceInterface("android.support.v4.app.INotificationSideChannel");
            this.cancelAll(arg4.readString());
            return 1;
        label_11:
            arg4.enforceInterface("android.support.v4.app.INotificationSideChannel");
            this.cancel(arg4.readString(), arg4.readInt(), arg4.readString());
            return 1;
        label_31:
            arg5.writeString("android.support.v4.app.INotificationSideChannel");
            return 1;
        }
    }

    void cancel(String arg1, int arg2, String arg3) throws RemoteException;

    void cancelAll(String arg1) throws RemoteException;

    void notify(String arg1, int arg2, String arg3, Notification arg4) throws RemoteException;
}

