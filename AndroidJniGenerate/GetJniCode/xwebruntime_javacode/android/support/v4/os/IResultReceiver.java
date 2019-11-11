package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IResultReceiver extends IInterface {
    public abstract class Stub extends Binder implements IResultReceiver {
        class Proxy implements IResultReceiver {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.os.IResultReceiver";
            }

            public void send(int arg3, Bundle arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    v0.writeInt(arg3);
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
        }

        private static final String DESCRIPTOR = "android.support.v4.os.IResultReceiver";
        static final int TRANSACTION_send = 1;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "android.support.v4.os.IResultReceiver");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IResultReceiver asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if(v0 != null && ((v0 instanceof IResultReceiver))) {
                return ((IResultReceiver)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            Bundle v4_1;
            if(arg3 != 1) {
                if(arg3 != 0x5F4E5446) {
                    return super.onTransact(arg3, arg4, arg5, arg6);
                }

                arg5.writeString("android.support.v4.os.IResultReceiver");
                return 1;
            }

            arg4.enforceInterface("android.support.v4.os.IResultReceiver");
            arg3 = arg4.readInt();
            if(arg4.readInt() != 0) {
                Object v4 = Bundle.CREATOR.createFromParcel(arg4);
            }
            else {
                v4_1 = null;
            }

            this.send(arg3, v4_1);
            return 1;
        }
    }

    void send(int arg1, Bundle arg2) throws RemoteException;
}

