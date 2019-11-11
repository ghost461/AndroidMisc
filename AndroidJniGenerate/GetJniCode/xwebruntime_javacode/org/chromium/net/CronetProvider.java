package org.chromium.net;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class CronetProvider {
    private static final String GMS_CORE_CRONET_PROVIDER_CLASS = "com.google.android.gms.net.GmsCoreCronetProvider";
    private static final String JAVA_CRONET_PROVIDER_CLASS = "org.chromium.net.impl.JavaCronetProvider";
    private static final String NATIVE_CRONET_PROVIDER_CLASS = "org.chromium.net.impl.NativeCronetProvider";
    private static final String PLAY_SERVICES_CRONET_PROVIDER_CLASS = "com.google.android.gms.net.PlayServicesCronetProvider";
    public static final String PROVIDER_NAME_APP_PACKAGED = "App-Packaged-Cronet-Provider";
    public static final String PROVIDER_NAME_FALLBACK = "Fallback-Cronet-Provider";
    private static final String RES_KEY_CRONET_IMPL_CLASS = "CronetProviderClassName";
    private static final String TAG = "CronetProvider";
    protected final Context mContext;

    static {
    }

    protected CronetProvider(Context arg2) {
        super();
        if(arg2 == null) {
            throw new IllegalArgumentException("Context must not be null");
        }

        this.mContext = arg2;
    }

    private static boolean addCronetProviderFromResourceFile(Context arg4, Set arg5) {
        int v0 = arg4.getResources().getIdentifier("CronetProviderClassName", "string", arg4.getPackageName());
        if(v0 == 0) {
            return 0;
        }

        String v0_1 = arg4.getResources().getString(v0);
        if(v0_1 != null && !v0_1.equals("com.google.android.gms.net.PlayServicesCronetProvider") && !v0_1.equals("com.google.android.gms.net.GmsCoreCronetProvider") && !v0_1.equals("org.chromium.net.impl.JavaCronetProvider")) {
            if(v0_1.equals("org.chromium.net.impl.NativeCronetProvider")) {
            }
            else {
                if(!CronetProvider.addCronetProviderImplByClassName(arg4, v0_1, arg5, true)) {
                    String v4 = CronetProvider.TAG;
                    Log.e(v4, "Unable to instantiate Cronet implementation class " + v0_1 + " that is listed as in the app string resource file under " + "CronetProviderClassName" + " key");
                }

                return 1;
            }
        }

        return 0;
    }

    private static boolean addCronetProviderImplByClassName(Context arg5, String arg6, Set arg7, boolean arg8) {
        ClassLoader v0 = arg5.getClassLoader();
        try {
            arg7.add(v0.loadClass(arg6).asSubclass(CronetProvider.class).getConstructor(Context.class).newInstance(arg5));
            return 1;
        }
        catch(ClassNotFoundException v5) {
            CronetProvider.logReflectiveOperationException(arg6, arg8, ((Exception)v5));
        }
        catch(IllegalAccessException v5_1) {
            CronetProvider.logReflectiveOperationException(arg6, arg8, ((Exception)v5_1));
        }
        catch(NoSuchMethodException v5_2) {
            CronetProvider.logReflectiveOperationException(arg6, arg8, ((Exception)v5_2));
        }
        catch(InvocationTargetException v5_3) {
            CronetProvider.logReflectiveOperationException(arg6, arg8, ((Exception)v5_3));
        }
        catch(InstantiationException v5_4) {
            CronetProvider.logReflectiveOperationException(arg6, arg8, ((Exception)v5_4));
        }

        return 0;
    }

    public abstract Builder createBuilder();

    public static List getAllProviders(Context arg3) {
        LinkedHashSet v0 = new LinkedHashSet();
        CronetProvider.addCronetProviderFromResourceFile(arg3, ((Set)v0));
        CronetProvider.addCronetProviderImplByClassName(arg3, "com.google.android.gms.net.PlayServicesCronetProvider", ((Set)v0), false);
        CronetProvider.addCronetProviderImplByClassName(arg3, "com.google.android.gms.net.GmsCoreCronetProvider", ((Set)v0), false);
        CronetProvider.addCronetProviderImplByClassName(arg3, "org.chromium.net.impl.NativeCronetProvider", ((Set)v0), false);
        CronetProvider.addCronetProviderImplByClassName(arg3, "org.chromium.net.impl.JavaCronetProvider", ((Set)v0), false);
        return Collections.unmodifiableList(new ArrayList(((Collection)v0)));
    }

    public abstract String getName();

    public abstract String getVersion();

    public abstract boolean isEnabled();

    private static void logReflectiveOperationException(String arg2, boolean arg3, Exception arg4) {
        String v3;
        if(arg3) {
            v3 = CronetProvider.TAG;
            Log.e(v3, "Unable to load provider class: " + arg2, ((Throwable)arg4));
        }
        else if(Log.isLoggable(CronetProvider.TAG, 3)) {
            v3 = CronetProvider.TAG;
            Log.d(v3, "Tried to load " + arg2 + " provider class but it wasn\'t included in the app classpath");
        }
    }

    public String toString() {
        return "[class=" + this.getClass().getName() + ", name=" + this.getName() + ", version=" + this.getVersion() + ", enabled=" + this.isEnabled() + "]";
    }
}

