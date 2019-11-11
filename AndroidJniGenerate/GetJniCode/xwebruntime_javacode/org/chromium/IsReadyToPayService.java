package org.chromium;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IsReadyToPayService extends IInterface {
    public abstract class Stub extends Binder implements IsReadyToPayService {
        class Proxy implements IsReadyToPayService {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "org.chromium.IsReadyToPayService";
            }

            public void isReadyToPay(IsReadyToPayServiceCallback arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.IsReadyToPayService");
                    Parcel v1 = null;
                    IBinder v4_1 = arg4 != null ? arg4.asBinder() : ((IBinder)v1);
                    v0.writeStrongBinder(v4_1);
                    this.mRemote.transact(1, v0, v1, 1);
                }
                catch(Throwable v4) {
                    v0.recycle();
                    throw v4;
                }

                v0.recycle();
            }
        }

        private static final String DESCRIPTOR = "org.chromium.IsReadyToPayService";
        static final int TRANSACTION_isReadyToPay = 1;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "org.chromium.IsReadyToPayService");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IsReadyToPayService asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("org.chromium.IsReadyToPayService");
            if(v0 != null && ((v0 instanceof IsReadyToPayService))) {
                return ((IsReadyToPayService)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            if(arg3 != 1) {
                if(arg3 != 0x5F4E5446) {
                    return super.onTransact(arg3, arg4, arg5, arg6);
                }

                arg5.writeString("org.chromium.IsReadyToPayService");
                return 1;
            }

            arg4.enforceInterface("org.chromium.IsReadyToPayService");
            this.isReadyToPay(org.chromium.IsReadyToPayServiceCallback$Stub.asInterface(arg4.readStrongBinder()));
            return 1;
        }
    }

    void isReadyToPay(IsReadyToPayServiceCallback arg1) throws RemoteException;
}

