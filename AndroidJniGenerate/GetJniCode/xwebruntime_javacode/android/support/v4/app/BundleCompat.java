package android.support.v4.app;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.Method;

public final class BundleCompat {
    class BundleCompatBaseImpl {
        private static final String TAG = "BundleCompatBaseImpl";
        private static Method sGetIBinderMethod;
        private static boolean sGetIBinderMethodFetched;
        private static Method sPutIBinderMethod;
        private static boolean sPutIBinderMethodFetched;

        BundleCompatBaseImpl() {
            super();
        }

        public static IBinder getBinder(Bundle arg6, String arg7) {
            if(!BundleCompatBaseImpl.sGetIBinderMethodFetched) {
                try {
                    BundleCompatBaseImpl.sGetIBinderMethod = Bundle.class.getMethod("getIBinder", String.class);
                    BundleCompatBaseImpl.sGetIBinderMethod.setAccessible(true);
                }
                catch(NoSuchMethodException v0) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", ((Throwable)v0));
                }

                BundleCompatBaseImpl.sGetIBinderMethodFetched = true;
            }

            Method v3 = null;
            if(BundleCompatBaseImpl.sGetIBinderMethod != null) {
                try {
                    return BundleCompatBaseImpl.sGetIBinderMethod.invoke(arg6, arg7);
                }
                catch(IllegalArgumentException v6) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", ((Throwable)v6));
                    BundleCompatBaseImpl.sGetIBinderMethod = v3;
                }
            }

            return ((IBinder)v3);
        }

        public static void putBinder(Bundle arg7, String arg8, IBinder arg9) {
            int v2 = 2;
            if(!BundleCompatBaseImpl.sPutIBinderMethodFetched) {
                try {
                    Class v0_1 = Bundle.class;
                    BundleCompatBaseImpl.sPutIBinderMethod = v0_1.getMethod("putIBinder", String.class, IBinder.class);
                    BundleCompatBaseImpl.sPutIBinderMethod.setAccessible(true);
                }
                catch(NoSuchMethodException v0) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", ((Throwable)v0));
                }

                BundleCompatBaseImpl.sPutIBinderMethodFetched = true;
            }

            if(BundleCompatBaseImpl.sPutIBinderMethod != null) {
                try {
                    Method v0_2 = BundleCompatBaseImpl.sPutIBinderMethod;
                    v0_2.invoke(arg7, arg8, arg9);
                }
                catch(IllegalArgumentException v7) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", ((Throwable)v7));
                    BundleCompatBaseImpl.sPutIBinderMethod = null;
                }
            }
        }
    }

    private BundleCompat() {
        super();
    }

    public static IBinder getBinder(Bundle arg2, String arg3) {
        if(Build$VERSION.SDK_INT >= 18) {
            return arg2.getBinder(arg3);
        }

        return BundleCompatBaseImpl.getBinder(arg2, arg3);
    }

    public static void putBinder(Bundle arg2, String arg3, IBinder arg4) {
        if(Build$VERSION.SDK_INT >= 18) {
            arg2.putBinder(arg3, arg4);
        }
        else {
            BundleCompatBaseImpl.putBinder(arg2, arg3, arg4);
        }
    }
}

