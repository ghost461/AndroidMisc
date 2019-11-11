package org.chromium.base.process_launcher;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IChildProcessService extends IInterface {
    public abstract class Stub extends Binder implements IChildProcessService {
        class Proxy implements IChildProcessService {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean bindToCaller() throws RemoteException {
                boolean v3;
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    v3 = false;
                    this.mRemote.transact(1, v0, v1, 0);
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

                v3 = true;
            label_12:
                v1.recycle();
                v0.recycle();
                return v3;
            }

            public void crashIntentionallyForTesting() throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    this.mRemote.transact(3, v0, null, 1);
                }
                catch(Throwable v1) {
                    v0.recycle();
                    throw v1;
                }

                v0.recycle();
            }

            public String getInterfaceDescriptor() {
                return "org.chromium.base.process_launcher.IChildProcessService";
            }

            public void onMemoryPressure(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    v0.writeInt(arg5);
                    this.mRemote.transact(4, v0, null, 1);
                }
                catch(Throwable v5) {
                    v0.recycle();
                    throw v5;
                }

                v0.recycle();
            }

            public void setupConnection(Bundle arg4, ICallbackInt arg5, List arg6) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    if(arg4 != null) {
                        v0.writeInt(1);
                        arg4.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    Parcel v4_1 = null;
                    IBinder v5 = arg5 != null ? arg5.asBinder() : ((IBinder)v4_1);
                    v0.writeStrongBinder(v5);
                    v0.writeBinderList(arg6);
                    this.mRemote.transact(2, v0, v4_1, 1);
                }
                catch(Throwable v4) {
                    v0.recycle();
                    throw v4;
                }

                v0.recycle();
            }
        }

        private static final String DESCRIPTOR = "org.chromium.base.process_launcher.IChildProcessService";
        static final int TRANSACTION_bindToCaller = 1;
        static final int TRANSACTION_crashIntentionallyForTesting = 3;
        static final int TRANSACTION_onMemoryPressure = 4;
        static final int TRANSACTION_setupConnection = 2;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "org.chromium.base.process_launcher.IChildProcessService");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IChildProcessService asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("org.chromium.base.process_launcher.IChildProcessService");
            if(v0 != null && ((v0 instanceof IChildProcessService))) {
                return ((IChildProcessService)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            Bundle v3_2;
            if(arg3 == 0x5F4E5446) {
                goto label_34;
            }

            switch(arg3) {
                case 1: {
                    goto label_28;
                }
                case 2: {
                    goto label_15;
                }
                case 3: {
                    goto label_11;
                }
                case 4: {
                    goto label_6;
                }
            }

            return super.onTransact(arg3, arg4, arg5, arg6);
        label_6:
            arg4.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            this.onMemoryPressure(arg4.readInt());
            return 1;
        label_11:
            arg4.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            this.crashIntentionallyForTesting();
            return 1;
        label_28:
            arg4.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            boolean v3 = this.bindToCaller();
            arg5.writeNoException();
            arg5.writeInt(((int)v3));
            return 1;
        label_15:
            arg4.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            if(arg4.readInt() != 0) {
                Object v3_1 = Bundle.CREATOR.createFromParcel(arg4);
            }
            else {
                v3_2 = null;
            }

            this.setupConnection(v3_2, org.chromium.base.process_launcher.ICallbackInt$Stub.asInterface(arg4.readStrongBinder()), arg4.createBinderArrayList());
            return 1;
        label_34:
            arg5.writeString("org.chromium.base.process_launcher.IChildProcessService");
            return 1;
        }
    }

    boolean bindToCaller() throws RemoteException;

    void crashIntentionallyForTesting() throws RemoteException;

    void onMemoryPressure(int arg1) throws RemoteException;

    void setupConnection(Bundle arg1, ICallbackInt arg2, List arg3) throws RemoteException;
}

