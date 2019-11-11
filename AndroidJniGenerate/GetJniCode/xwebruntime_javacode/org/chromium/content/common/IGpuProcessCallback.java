package org.chromium.content.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import org.chromium.base.UnguessableToken;

public interface IGpuProcessCallback extends IInterface {
    public abstract class Stub extends Binder implements IGpuProcessCallback {
        class Proxy implements IGpuProcessCallback {
            private IBinder mRemote;

            Proxy(IBinder arg1) {
                super();
                this.mRemote = arg1;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void forwardSurfaceForSurfaceRequest(UnguessableToken arg4, Surface arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
                    if(arg4 != null) {
                        v0.writeInt(1);
                        arg4.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    if(arg5 != null) {
                        v0.writeInt(1);
                        arg5.writeToParcel(v0, 0);
                    }
                    else {
                        v0.writeInt(0);
                    }

                    this.mRemote.transact(1, v0, null, 1);
                }
                catch(Throwable v4) {
                    v0.recycle();
                    throw v4;
                }

                v0.recycle();
            }

            public String getInterfaceDescriptor() {
                return "org.chromium.content.common.IGpuProcessCallback";
            }

            public SurfaceWrapper getViewSurface(int arg5) throws RemoteException {
                Parcel v0 = Parcel.obtain();
                Parcel v1 = Parcel.obtain();
                try {
                    v0.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
                    v0.writeInt(arg5);
                    this.mRemote.transact(2, v0, v1, 0);
                    v1.readException();
                    if(v1.readInt() != 0) {
                        Object v5_1 = SurfaceWrapper.CREATOR.createFromParcel(v1);
                    }
                    else {
                        goto label_15;
                    }

                    goto label_16;
                }
                catch(Throwable v5) {
                    v1.recycle();
                    v0.recycle();
                    throw v5;
                }

            label_15:
                SurfaceWrapper v5_2 = null;
            label_16:
                v1.recycle();
                v0.recycle();
                return v5_2;
            }
        }

        private static final String DESCRIPTOR = "org.chromium.content.common.IGpuProcessCallback";
        static final int TRANSACTION_forwardSurfaceForSurfaceRequest = 1;
        static final int TRANSACTION_getViewSurface = 2;

        public Stub() {
            super();
            this.attachInterface(((IInterface)this), "org.chromium.content.common.IGpuProcessCallback");
        }

        public IBinder asBinder() {
            return this;
        }

        public static IGpuProcessCallback asInterface(IBinder arg2) {
            if(arg2 == null) {
                return null;
            }

            IInterface v0 = arg2.queryLocalInterface("org.chromium.content.common.IGpuProcessCallback");
            if(v0 != null && ((v0 instanceof IGpuProcessCallback))) {
                return ((IGpuProcessCallback)v0);
            }

            return new Proxy(arg2);
        }

        public boolean onTransact(int arg3, Parcel arg4, Parcel arg5, int arg6) throws RemoteException {
            Object v5_1;
            UnguessableToken v3_1;
            if(arg3 == 0x5F4E5446) {
                goto label_34;
            }

            switch(arg3) {
                case 1: {
                    goto label_18;
                }
                case 2: {
                    goto label_6;
                }
            }

            return super.onTransact(arg3, arg4, arg5, arg6);
        label_18:
            arg4.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
            Surface v5 = null;
            if(arg4.readInt() != 0) {
                Object v3 = UnguessableToken.CREATOR.createFromParcel(arg4);
            }
            else {
                v3_1 = ((UnguessableToken)v5);
            }

            if(arg4.readInt() != 0) {
                v5_1 = Surface.CREATOR.createFromParcel(arg4);
            }

            this.forwardSurfaceForSurfaceRequest(v3_1, ((Surface)v5_1));
            return 1;
        label_6:
            arg4.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
            SurfaceWrapper v3_2 = this.getViewSurface(arg4.readInt());
            arg5.writeNoException();
            if(v3_2 != null) {
                arg5.writeInt(1);
                v3_2.writeToParcel(arg5, 1);
            }
            else {
                arg5.writeInt(0);
            }

            return 1;
        label_34:
            arg5.writeString("org.chromium.content.common.IGpuProcessCallback");
            return 1;
        }
    }

    void forwardSurfaceForSurfaceRequest(UnguessableToken arg1, Surface arg2) throws RemoteException;

    SurfaceWrapper getViewSurface(int arg1) throws RemoteException;
}

