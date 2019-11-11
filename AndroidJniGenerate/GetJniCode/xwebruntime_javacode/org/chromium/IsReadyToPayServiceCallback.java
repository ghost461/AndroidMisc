package org.chromium;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IsReadyToPayServiceCallback extends IInterface {
    public abstract class Stub extends Binder implements IsReadyToPayServiceCallback {
        class Proxy implements IsReadyToPayServiceCallback {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "org.chromium.IsReadyToPayServiceCallback";
            }

            public void handleIsReadyToPay(boolean arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.IsReadyToPayServiceCallback");
                    v0.writeInt(((int)arg4));
                    this.mRemote.transact(1, v0, null, 1);
                }
                catch(Throwable v4) {
                    v0.recycle();
                    throw v4;
                }

                v0.recycle();
            }
        }

        private static final String DESCRIPTOR = "org.chromium.IsReadyToPayServiceCallback";
        static final int TRANSACTION_handleIsReadyToPay = 1;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "org.chromium.IsReadyToPayServiceCallback");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IsReadyToPayServiceCallback asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("org.chromium.IsReadyToPayServiceCallback");
            if(v0 != null && ((v0 instanceof IsReadyToPayServiceCallback))) {
                return ((IsReadyToPayServiceCallback)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            if(arg3 != 1) {
                if(arg3 != 0x5F4E5446) {
                    return super.onTransact(arg3, arg4, arg5, arg6);
                }

                arg5.writeString("org.chromium.IsReadyToPayServiceCallback");
                return 1;
            }

            arg4.enforceInterface("org.chromium.IsReadyToPayServiceCallback");
            boolean v3 = arg4.readInt() != 0 ? true : false;
            this.handleIsReadyToPay(v3);
            return 1;
        }
    }

    void handleIsReadyToPay(boolean arg1) throws RemoteException;
}

