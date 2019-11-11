package org.chromium.base.process_launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.ObserverList;
import org.chromium.base.VisibleForTesting;

public class ChildConnectionAllocator {
    @VisibleForTesting public interface ConnectionFactory {
        ChildProcessConnection createConnection(Context arg1, ComponentName arg2, boolean arg3, boolean arg4, Bundle arg5);
    }

    class ConnectionFactoryImpl implements ConnectionFactory {
        ConnectionFactoryImpl(org.chromium.base.process_launcher.ChildConnectionAllocator$1 arg1) {
            this();
        }

        private ConnectionFactoryImpl() {
            super();
        }

        public ChildProcessConnection createConnection(Context arg8, ComponentName arg9, boolean arg10, boolean arg11, Bundle arg12) {
            return new ChildProcessConnection(arg8, arg9, arg10, arg11, arg12);
        }
    }

    public abstract class Listener {
        public Listener() {
            super();
        }

        public void onConnectionAllocated(ChildConnectionAllocator arg1, ChildProcessConnection arg2) {
        }

        public void onConnectionFreed(ChildConnectionAllocator arg1, ChildProcessConnection arg2) {
        }
    }

    private static final long FREE_CONNECTION_DELAY_MILLIS = 1;
    private static final String TAG = "ChildConnAllocator";
    private final boolean mBindAsExternalService;
    private final boolean mBindToCaller;
    private final ChildProcessConnection[] mChildProcessConnections;
    private ConnectionFactory mConnectionFactory;
    private final ArrayList mFreeConnectionIndices;
    private final Handler mLauncherHandler;
    private final ObserverList mListeners;
    private final String mPackageName;
    private final String mServiceClassName;
    private final boolean mUseStrongBinding;

    static {
    }

    private ChildConnectionAllocator(Handler arg3, String arg4, String arg5, boolean arg6, boolean arg7, boolean arg8, int arg9) {
        super();
        this.mListeners = new ObserverList();
        this.mConnectionFactory = new ConnectionFactoryImpl(null);
        this.mLauncherHandler = arg3;
        this.mPackageName = arg4;
        this.mServiceClassName = arg5;
        this.mBindToCaller = arg6;
        this.mBindAsExternalService = arg7;
        this.mUseStrongBinding = arg8;
        this.mChildProcessConnections = new ChildProcessConnection[arg9];
        this.mFreeConnectionIndices = new ArrayList(arg9);
        int v3;
        for(v3 = 0; v3 < arg9; ++v3) {
            this.mFreeConnectionIndices.add(Integer.valueOf(v3));
        }
    }

    static boolean access$100(ChildConnectionAllocator arg0) {
        return arg0.isRunningOnLauncherThread();
    }

    static Handler access$200(ChildConnectionAllocator arg0) {
        return arg0.mLauncherHandler;
    }

    static void access$300(ChildConnectionAllocator arg0, ChildProcessConnection arg1) {
        arg0.free(arg1);
    }

    public void addListener(Listener arg2) {
        this.mListeners.addObserver(arg2);
    }

    public ChildProcessConnection allocate(Context arg9, Bundle arg10, ServiceCallback arg11) {
        if(this.mFreeConnectionIndices.isEmpty()) {
            Log.d("ChildConnAllocator", "Ran out of services to allocate.");
            return null;
        }

        int v0 = this.mFreeConnectionIndices.remove(0).intValue();
        String v1 = this.mPackageName;
        StringBuilder v2 = new StringBuilder();
        v2.append(this.mServiceClassName);
        v2.append(v0);
        ComponentName v3 = new ComponentName(v1, v2.toString());
        org.chromium.base.process_launcher.ChildConnectionAllocator$1 v7 = new ServiceCallback(arg11) {
            private void freeConnectionWithDelay(ChildProcessConnection arg5) {
                ChildConnectionAllocator.this.mLauncherHandler.postDelayed(new Runnable(arg5) {
                    public void run() {
                        this.this$1.this$0.free(this.val$connection);
                    }
                }, 1);
            }

            public void onChildProcessDied(ChildProcessConnection arg3) {
                if(this.val$serviceCallback != null) {
                    ChildConnectionAllocator.this.mLauncherHandler.post(new Runnable(arg3) {
                        public void run() {
                            this.this$1.val$serviceCallback.onChildProcessDied(this.val$connection);
                        }
                    });
                }

                this.freeConnectionWithDelay(arg3);
            }

            public void onChildStartFailed(ChildProcessConnection arg3) {
                if(this.val$serviceCallback != null) {
                    ChildConnectionAllocator.this.mLauncherHandler.post(new Runnable(arg3) {
                        public void run() {
                            this.this$1.val$serviceCallback.onChildStartFailed(this.val$connection);
                        }
                    });
                }

                this.freeConnectionWithDelay(arg3);
            }

            public void onChildStarted() {
                if(this.val$serviceCallback != null) {
                    ChildConnectionAllocator.this.mLauncherHandler.post(new Runnable() {
                        public void run() {
                            this.this$1.val$serviceCallback.onChildStarted();
                        }
                    });
                }
            }
        };
        ChildProcessConnection v9 = this.mConnectionFactory.createConnection(arg9, v3, this.mBindToCaller, this.mBindAsExternalService, arg10);
        this.mChildProcessConnections[v0] = v9;
        Iterator v10 = this.mListeners.iterator();
        while(v10.hasNext()) {
            v10.next().onConnectionAllocated(this, v9);
        }

        v9.start(this.mUseStrongBinding, ((ServiceCallback)v7));
        Log.d("ChildConnAllocator", "Allocator allocated and bound a connection, name: %s, slot: %d", this.mServiceClassName, Integer.valueOf(v0));
        return v9;
    }

    @VisibleForTesting public int allocatedConnectionsCountForTesting() {
        return this.mChildProcessConnections.length - this.mFreeConnectionIndices.size();
    }

    public boolean anyConnectionAllocated() {
        boolean v0 = this.mFreeConnectionIndices.size() < this.mChildProcessConnections.length ? true : false;
        return v0;
    }

    public static ChildConnectionAllocator create(Context arg9, Handler arg10, String arg11, String arg12, String arg13, boolean arg14, boolean arg15, boolean arg16) {
        String v4_1;
        int v7;
        String v2 = arg11;
        PackageManager v0 = arg9.getPackageManager();
        int v1 = 0x80;
        try {
            ApplicationInfo v1_1 = v0.getApplicationInfo(v2, v1);
            int v4 = -1;
            if(v1_1.metaData != null) {
                v7 = v1_1.metaData.getInt(arg13, v4);
            }
            else {
                goto label_12;
            }

            goto label_13;
        }
        catch(PackageManager$NameNotFoundException ) {
            throw new RuntimeException("Could not get application info.");
        }

    label_12:
        v7 = -1;
    label_13:
        if(v7 < 0) {
            throw new RuntimeException("Illegal meta data value for number of child services");
        }

        try {
            StringBuilder v3 = new StringBuilder();
            v4_1 = arg12;
            v3.append(v4_1);
            v3.append("0");
            v0.getServiceInfo(new ComponentName(v2, v3.toString()), 0);
        }
        catch(PackageManager$NameNotFoundException ) {
            throw new RuntimeException("Illegal meta data value: the child service doesn\'t exist");
        }

        return new ChildConnectionAllocator(arg10, v2, v4_1, arg14, arg15, arg16, v7);
    }

    @VisibleForTesting public static ChildConnectionAllocator createForTest(String arg9, String arg10, int arg11, boolean arg12, boolean arg13, boolean arg14) {
        return new ChildConnectionAllocator(new Handler(), arg9, arg10, arg12, arg13, arg14, arg11);
    }

    private void free(ChildProcessConnection arg5) {
        int v0 = Arrays.asList(this.mChildProcessConnections).indexOf(arg5);
        if(v0 == -1) {
            Log.e("ChildConnAllocator", "Unable to find connection to free.", new Object[0]);
        }
        else {
            this.mChildProcessConnections[v0] = null;
            this.mFreeConnectionIndices.add(Integer.valueOf(v0));
            Log.d("ChildConnAllocator", "Allocator freed a connection, name: %s, slot: %d", this.mServiceClassName, Integer.valueOf(v0));
        }

        Iterator v0_1 = this.mListeners.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().onConnectionFreed(this, arg5);
        }
    }

    @VisibleForTesting public ChildProcessConnection getChildProcessConnectionAtSlotForTesting(int arg2) {
        return this.mChildProcessConnections[arg2];
    }

    public static int getNumberOfServices(Context arg1, String arg2, String arg3) {
        int v0;
        try {
            ApplicationInfo v1_1 = arg1.getPackageManager().getApplicationInfo(arg2, 0x80);
            v0 = -1;
            if(v1_1.metaData != null) {
                v0 = v1_1.metaData.getInt(arg3, v0);
            }
        }
        catch(PackageManager$NameNotFoundException v1) {
            throw new RuntimeException("Could not get application info", ((Throwable)v1));
        }

        if(v0 < 0) {
            throw new RuntimeException("Illegal meta data value for number of child services");
        }

        return v0;
    }

    public int getNumberOfServices() {
        return this.mChildProcessConnections.length;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public boolean isConnectionFromAllocator(ChildProcessConnection arg6) {
        ChildProcessConnection[] v0 = this.mChildProcessConnections;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            if(v0[v3] == arg6) {
                return 1;
            }
        }

        return 0;
    }

    public boolean isFreeConnectionAvailable() {
        return this.mFreeConnectionIndices.isEmpty() ^ 1;
    }

    private boolean isRunningOnLauncherThread() {
        boolean v0 = this.mLauncherHandler.getLooper() == Looper.myLooper() ? true : false;
        return v0;
    }

    public void removeListener(Listener arg2) {
        this.mListeners.removeObserver(arg2);
    }

    @VisibleForTesting public void setConnectionFactoryForTesting(ConnectionFactory arg1) {
        this.mConnectionFactory = arg1;
    }
}

