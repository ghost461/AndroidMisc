package org.chromium.base.process_launcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.TraceEvent;

public class ChildProcessLauncher {
    public abstract class Delegate {
        public Delegate() {
            super();
        }

        public ChildProcessConnection getBoundConnection(ChildConnectionAllocator arg1, ServiceCallback arg2) {
            return null;
        }

        public void onBeforeConnectionAllocated(Bundle arg1) {
        }

        public void onBeforeConnectionSetup(Bundle arg1) {
        }

        public void onConnectionEstablished(ChildProcessConnection arg1) {
        }

        public void onConnectionLost(ChildProcessConnection arg1) {
        }
    }

    private static final int NULL_PROCESS_HANDLE = 0;
    private static final String TAG = "ChildProcLauncher";
    private final List mClientInterfaces;
    private final String[] mCommandLine;
    private ChildProcessConnection mConnection;
    private final ChildConnectionAllocator mConnectionAllocator;
    private final Delegate mDelegate;
    private final FileDescriptorInfo[] mFilesToBeMapped;
    private final Handler mLauncherHandler;

    static {
    }

    public ChildProcessLauncher(Handler arg1, Delegate arg2, String[] arg3, FileDescriptorInfo[] arg4, ChildConnectionAllocator arg5, List arg6) {
        super();
        this.mLauncherHandler = arg1;
        this.isRunningOnLauncherThread();
        this.mCommandLine = arg3;
        this.mConnectionAllocator = arg5;
        this.mDelegate = arg2;
        this.mFilesToBeMapped = arg4;
        this.mClientInterfaces = arg6;
    }

    static boolean access$000(ChildProcessLauncher arg0) {
        return arg0.isRunningOnLauncherThread();
    }

    static ChildProcessConnection access$100(ChildProcessLauncher arg0) {
        return arg0.mConnection;
    }

    static ChildProcessConnection access$102(ChildProcessLauncher arg0, ChildProcessConnection arg1) {
        arg0.mConnection = arg1;
        return arg1;
    }

    static Handler access$200(ChildProcessLauncher arg0) {
        return arg0.mLauncherHandler;
    }

    static void access$300(ChildProcessLauncher arg0) {
        arg0.onChildProcessDied();
    }

    static ChildConnectionAllocator access$400(ChildProcessLauncher arg0) {
        return arg0.mConnectionAllocator;
    }

    static boolean access$500(ChildProcessLauncher arg0, ServiceCallback arg1, boolean arg2, boolean arg3) {
        return arg0.allocateAndSetupConnection(arg1, arg2, arg3);
    }

    static void access$600(ChildProcessLauncher arg0) {
        arg0.onServiceConnected();
    }

    private boolean allocateAndSetupConnection(ServiceCallback arg4, boolean arg5, boolean arg6) {
        Bundle v0 = new Bundle();
        this.mDelegate.onBeforeConnectionAllocated(v0);
        this.mConnection = this.mConnectionAllocator.allocate(ContextUtils.getApplicationContext(), v0, arg4);
        if(this.mConnection == null) {
            if(!arg6) {
                Log.d("ChildProcLauncher", "Failed to allocate a child connection (no queuing).");
                return 0;
            }

            this.mConnectionAllocator.addListener(new Listener(arg4, arg5, arg6) {
                public void onConnectionFreed(ChildConnectionAllocator arg3, ChildProcessConnection arg4) {
                    if(!arg3.isFreeConnectionAvailable()) {
                        return;
                    }

                    arg3.removeListener(((Listener)this));
                    ChildProcessLauncher.this.allocateAndSetupConnection(this.val$serviceCallback, this.val$setupConnection, this.val$queueIfNoFreeConnection);
                }
            });
            return 0;
        }

        if(arg5) {
            this.setupConnection();
        }

        return 1;
    }

    private Bundle createConnectionBundle() {
        Bundle v0 = new Bundle();
        v0.putStringArray("org.chromium.base.process_launcher.extra.command_line", this.mCommandLine);
        v0.putParcelableArray("org.chromium.base.process_launcher.extra.extraFiles", this.mFilesToBeMapped);
        return v0;
    }

    public List getClientInterfaces() {
        return this.mClientInterfaces;
    }

    public ChildProcessConnection getConnection() {
        return this.mConnection;
    }

    public ChildConnectionAllocator getConnectionAllocator() {
        return this.mConnectionAllocator;
    }

    public int getPid() {
        int v0 = this.mConnection == null ? 0 : this.mConnection.getPid();
        return v0;
    }

    private boolean isRunningOnLauncherThread() {
        boolean v0 = this.mLauncherHandler.getLooper() == Looper.myLooper() ? true : false;
        return v0;
    }

    private void onChildProcessDied() {
        if(this.getPid() != 0) {
            this.mDelegate.onConnectionLost(this.mConnection);
        }
    }

    private void onServiceConnected() {
        Log.d("ChildProcLauncher", "on connect callback, pid=%d", Integer.valueOf(this.mConnection.getPid()));
        this.mDelegate.onConnectionEstablished(this.mConnection);
        try {
            FileDescriptorInfo[] v1_1 = this.mFilesToBeMapped;
            int v2 = v1_1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v1_1[v3].fd.close();
            }
        }
        catch(IOException v1) {
            Log.w("ChildProcLauncher", "Failed to close FD.", new Object[]{v1});
        }
    }

    private void setupConnection() {
        org.chromium.base.process_launcher.ChildProcessLauncher$3 v0 = new ConnectionCallback() {
            public void onConnected(ChildProcessConnection arg1) {
                ChildProcessLauncher.this.onServiceConnected();
            }
        };
        Bundle v1 = this.createConnectionBundle();
        this.mDelegate.onBeforeConnectionSetup(v1);
        this.mConnection.setupConnection(v1, this.getClientInterfaces(), ((ConnectionCallback)v0));
    }

    public boolean start(boolean arg4, boolean arg5) {
        try {
            TraceEvent.begin("ChildProcessLauncher.start");
            org.chromium.base.process_launcher.ChildProcessLauncher$1 v0 = new ServiceCallback(arg4, arg5) {
                public void onChildProcessDied(ChildProcessConnection arg1) {
                    ChildProcessLauncher.this.onChildProcessDied();
                }

                public void onChildStartFailed(ChildProcessConnection arg3) {
                    Log.e("ChildProcLauncher", "ChildProcessConnection.start failed, trying again", new Object[0]);
                    ChildProcessLauncher.this.mLauncherHandler.post(new Runnable() {
                        public void run() {
                            this.this$1.this$0.mConnection = null;
                            this.this$1.this$0.start(this.this$1.val$setupConnection, this.this$1.val$queueIfNoFreeConnection);
                        }
                    });
                }

                public void onChildStarted() {
                }
            };
            this.mConnection = this.mDelegate.getBoundConnection(this.mConnectionAllocator, ((ServiceCallback)v0));
            if(this.mConnection == null) {
                goto label_15;
            }

            this.setupConnection();
        }
        catch(Throwable v4) {
            goto label_27;
        }

        TraceEvent.end("ChildProcessLauncher.start");
        return 1;
        try {
        label_15:
            if(this.allocateAndSetupConnection(((ServiceCallback)v0), arg4, arg5)) {
                goto label_22;
            }
        }
        catch(Throwable v4) {
        label_27:
            TraceEvent.end("ChildProcessLauncher.start");
            throw v4;
        }

        if(!arg5) {
            TraceEvent.end("ChildProcessLauncher.start");
            return 0;
        }

    label_22:
        TraceEvent.end("ChildProcessLauncher.start");
        return 1;
    }

    public void stop() {
        Log.d("ChildProcLauncher", "stopping child connection: pid=%d", Integer.valueOf(this.mConnection.getPid()));
        this.mConnection.stop();
    }
}

