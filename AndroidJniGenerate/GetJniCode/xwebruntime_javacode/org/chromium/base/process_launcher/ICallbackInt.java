package org.chromium.base.process_launcher;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICallbackInt extends IInterface {
    public abstract class Stub extends Binder implements ICallbackInt {
        class Proxy implements ICallbackInt {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void call(int arg4) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.base.process_launcher.ICallbackInt");
                    v0.writeInt(arg4);
                    this.mRemote.transact(1, v0, null, 1);
                }
                catch(Throwable v4) {
                    v0.recycle();
                    throw v4;
                }

                v0.recycle();
            }

            public String getInterfaceDescriptor() {
                return "org.chromium.base.process_launcher.ICallbackInt";
            }
        }

        private static final String DESCRIPTOR = "org.chromium.base.process_launcher.ICallbackInt";
        static final int TRANSACTION_call = 1;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "org.chromium.base.process_launcher.ICallbackInt");
        }

        public IBinder asBinder() {
            return this;
        }

        public static ICallbackInt asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("org.chromium.base.process_launcher.ICallbackInt");
            if(v0 != null && ((v0 instanceof ICallbackInt))) {
                return ((ICallbackInt)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            if(arg3 != 1) {
                if(arg3 != 0x5F4E5446) {
                    return super.onTransact(arg3, arg4, arg5, arg6);
                }

                arg5.writeString("org.chromium.base.process_launcher.ICallbackInt");
                return 1;
            }

            arg4.enforceInterface("org.chromium.base.process_launcher.ICallbackInt");
            this.call(arg4.readInt());
            return 1;
        }
    }

    void call(int arg1) throws RemoteException;
}

