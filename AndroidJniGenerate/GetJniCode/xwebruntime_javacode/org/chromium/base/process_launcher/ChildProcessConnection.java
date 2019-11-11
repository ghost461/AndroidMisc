package org.chromium.base.process_launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.util.List;
import javax.annotation.Nullable;
import org.chromium.base.Log;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.memory.MemoryPressureCallback;

public class ChildProcessConnection {
    @VisibleForTesting public interface ChildServiceConnection {
        boolean bind();

        boolean isBound();

        void unbind();
    }

    @VisibleForTesting public interface ChildServiceConnectionDelegate {
        void onServiceConnected(IBinder arg1);

        void onServiceDisconnected();
    }

    @VisibleForTesting public interface ChildServiceConnectionFactory {
        ChildServiceConnection createConnection(Intent arg1, int arg2, ChildServiceConnectionDelegate arg3);
    }

    class ChildServiceConnectionImpl implements ServiceConnection, ChildServiceConnection {
        private final int mBindFlags;
        private final Intent mBindIntent;
        private boolean mBound;
        private final Context mContext;
        private final ChildServiceConnectionDelegate mDelegate;

        ChildServiceConnectionImpl(Context arg1, Intent arg2, int arg3, ChildServiceConnectionDelegate arg4, org.chromium.base.process_launcher.ChildProcessConnection$1 arg5) {
            this(arg1, arg2, arg3, arg4);
        }

        private ChildServiceConnectionImpl(Context arg1, Intent arg2, int arg3, ChildServiceConnectionDelegate arg4) {
            super();
            this.mContext = arg1;
            this.mBindIntent = arg2;
            this.mBindFlags = arg3;
            this.mDelegate = arg4;
        }

        public boolean bind() {
            if(!this.mBound) {
                try {
                    TraceEvent.begin("ChildProcessConnection.ChildServiceConnectionImpl.bind");
                    this.mBound = this.mContext.bindService(this.mBindIntent, ((ServiceConnection)this), this.mBindFlags);
                }
                catch(Throwable v0) {
                    TraceEvent.end("ChildProcessConnection.ChildServiceConnectionImpl.bind");
                    throw v0;
                }

                TraceEvent.end("ChildProcessConnection.ChildServiceConnectionImpl.bind");
            }

            return this.mBound;
        }

        public boolean isBound() {
            return this.mBound;
        }

        public void onServiceConnected(ComponentName arg1, IBinder arg2) {
            this.mDelegate.onServiceConnected(arg2);
        }

        public void onServiceDisconnected(ComponentName arg1) {
            this.mDelegate.onServiceDisconnected();
        }

        public void unbind() {
            if(this.mBound) {
                this.mContext.unbindService(((ServiceConnection)this));
                this.mBound = false;
            }
        }
    }

    public interface ConnectionCallback {
        void onConnected(ChildProcessConnection arg1);
    }

    class ConnectionParams {
        final List mClientInterfaces;
        final Bundle mConnectionBundle;

        ConnectionParams(Bundle arg1, List arg2) {
            super();
            this.mConnectionBundle = arg1;
            this.mClientInterfaces = arg2;
        }
    }

    public interface ServiceCallback {
        void onChildProcessDied(ChildProcessConnection arg1);

        void onChildStartFailed(ChildProcessConnection arg1);

        void onChildStarted();
    }

    private static final String TAG = "ChildProcessConn";
    private final boolean mBindToCaller;
    private ConnectionCallback mConnectionCallback;
    private ConnectionParams mConnectionParams;
    private boolean mDidOnServiceConnected;
    private final ChildServiceConnection mInitialBinding;
    private final Handler mLauncherHandler;
    private MemoryPressureCallback mMemoryPressureCallback;
    private final ChildServiceConnection mModerateBinding;
    private int mModerateBindingCount;
    private int mPid;
    private IChildProcessService mService;
    private final Bundle mServiceBundle;
    private ServiceCallback mServiceCallback;
    private boolean mServiceConnectComplete;
    private boolean mServiceDisconnected;
    private final ComponentName mServiceName;
    private final ChildServiceConnection mStrongBinding;
    private int mStrongBindingCount;
    private boolean mUnbound;
    private final ChildServiceConnection mWaivedBinding;
    private boolean mWaivedBoundOnly;

    static {
    }

    public ChildProcessConnection(Context arg8, ComponentName arg9, boolean arg10, boolean arg11, Bundle arg12) {
        this(arg8, arg9, arg10, arg11, arg12, null);
    }

    @VisibleForTesting public ChildProcessConnection(Context arg3, ComponentName arg4, boolean arg5, boolean arg6, Bundle arg7, ChildServiceConnectionFactory arg8) {
        org.chromium.base.process_launcher.ChildProcessConnection$1 v8;
        super();
        this.mLauncherHandler = new Handler();
        this.mServiceName = arg4;
        Bundle v0 = arg7 != null ? arg7 : new Bundle();
        this.mServiceBundle = v0;
        this.mServiceBundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", arg5);
        this.mBindToCaller = arg5;
        if(arg8 == null) {
            v8 = new ChildServiceConnectionFactory(arg3) {
                public ChildServiceConnection createConnection(Intent arg8, int arg9, ChildServiceConnectionDelegate arg10) {
                    return new ChildServiceConnectionImpl(this.val$context, arg8, arg9, arg10, null);
                }
            };
        }

        org.chromium.base.process_launcher.ChildProcessConnection$2 v3 = new ChildServiceConnectionDelegate() {
            public void onServiceConnected(IBinder arg3) {
                ChildProcessConnection.access$200(ChildProcessConnection.this).post(new Runnable(arg3) {
                    public void run() {
                        ChildProcessConnection.access$100(this.this$1.this$0, this.val$service);
                    }
                });
            }

            public void onServiceDisconnected() {
                ChildProcessConnection.access$200(ChildProcessConnection.this).post(new Runnable() {
                    public void run() {
                        ChildProcessConnection.access$300(this.this$1.this$0);
                    }
                });
            }
        };
        Intent v5 = new Intent();
        v5.setComponent(arg4);
        if(arg7 != null) {
            v5.putExtras(arg7);
        }

        int v4 = arg6 ? 0x80000000 : 0;
        v4 |= 1;
        this.mInitialBinding = ((ChildServiceConnectionFactory)v8).createConnection(v5, v4, ((ChildServiceConnectionDelegate)v3));
        this.mModerateBinding = ((ChildServiceConnectionFactory)v8).createConnection(v5, v4, ((ChildServiceConnectionDelegate)v3));
        this.mStrongBinding = ((ChildServiceConnectionFactory)v8).createConnection(v5, v4 | 0x40, ((ChildServiceConnectionDelegate)v3));
        this.mWaivedBinding = ((ChildServiceConnectionFactory)v8).createConnection(v5, v4 | 0x20, ((ChildServiceConnectionDelegate)v3));
    }

    static void access$100(ChildProcessConnection arg0, IBinder arg1) {
        arg0.onServiceConnectedOnLauncherThread(arg1);
    }

    static Handler access$200(ChildProcessConnection arg0) {
        return arg0.mLauncherHandler;
    }

    static void access$300(ChildProcessConnection arg0) {
        arg0.onServiceDisconnectedOnLauncherThread();
    }

    static void access$400(ChildProcessConnection arg0, int arg1) {
        arg0.onSetupConnectionResult(arg1);
    }

    public void addInitialBinding() {
        this.mInitialBinding.bind();
        this.updateWaivedBoundOnlyState();
    }

    public void addModerateBinding() {
        if(!this.isConnected()) {
            Log.w("ChildProcessConn", "The connection is not bound for %d", new Object[]{Integer.valueOf(this.getPid())});
            return;
        }

        if(this.mModerateBindingCount == 0) {
            this.mModerateBinding.bind();
            this.updateWaivedBoundOnlyState();
        }

        ++this.mModerateBindingCount;
    }

    public void addStrongBinding() {
        if(!this.isConnected()) {
            Log.w("ChildProcessConn", "The connection is not bound for %d", new Object[]{Integer.valueOf(this.getPid())});
            return;
        }

        if(this.mStrongBindingCount == 0) {
            this.mStrongBinding.bind();
            this.updateWaivedBoundOnlyState();
        }

        ++this.mStrongBindingCount;
    }

    private boolean bind(boolean arg1) {
        ChildServiceConnection v1 = arg1 ? this.mStrongBinding : this.mInitialBinding;
        arg1 = v1.bind();
        if(!arg1) {
            return 0;
        }

        this.updateWaivedBoundOnlyState();
        this.mWaivedBinding.bind();
        return 1;
    }

    final void bridge$lambda$0$ChildProcessConnection(int arg1) {
        this.onMemoryPressure(arg1);
    }

    @VisibleForTesting public void crashServiceForTesting() throws RemoteException {
        this.mService.crashIntentionallyForTesting();
    }

    @VisibleForTesting public boolean didOnServiceConnectedForTesting() {
        return this.mDidOnServiceConnected;
    }

    private void doConnectionSetup() {
        try {
            TraceEvent.begin("ChildProcessConnection.doConnectionSetup");
            org.chromium.base.process_launcher.ChildProcessConnection$3 v0_1 = new Stub() {
                public void call(int arg3) {
                    ChildProcessConnection.this.mLauncherHandler.post(new Runnable(arg3) {
                        public void run() {
                            this.this$1.this$0.onSetupConnectionResult(this.val$pid);
                        }
                    });
                }
            };
            try {
                this.mService.setupConnection(this.mConnectionParams.mConnectionBundle, ((ICallbackInt)v0_1), this.mConnectionParams.mClientInterfaces);
                goto label_19;
            }
            catch(RemoteException v0_2) {
                try {
                    Log.e("ChildProcessConn", "Failed to setup connection.", new Object[]{v0_2});
                label_19:
                    this.mConnectionParams = null;
                }
                catch(Throwable v0) {
                label_26:
                    TraceEvent.end("ChildProcessConnection.doConnectionSetup");
                    throw v0;
                }
            }
        }
        catch(Throwable v0) {
            goto label_26;
        }

        TraceEvent.end("ChildProcessConnection.doConnectionSetup");
    }

    @VisibleForTesting protected Handler getLauncherHandler() {
        return this.mLauncherHandler;
    }

    public int getPid() {
        return this.mPid;
    }

    public final IChildProcessService getService() {
        return this.mService;
    }

    public final ComponentName getServiceName() {
        return this.mServiceName;
    }

    public boolean isConnected() {
        boolean v0 = this.mService != null ? true : false;
        return v0;
    }

    public boolean isInitialBindingBound() {
        return this.mInitialBinding.isBound();
    }

    public boolean isModerateBindingBound() {
        return this.mModerateBinding.isBound();
    }

    private boolean isRunningOnLauncherThread() {
        boolean v0 = this.mLauncherHandler.getLooper() == Looper.myLooper() ? true : false;
        return v0;
    }

    public boolean isStrongBindingBound() {
        return this.mStrongBinding.isBound();
    }

    public boolean isWaivedBoundOnlyOrWasWhenDied() {
        return this.mWaivedBoundOnly;
    }

    final void lambda$onMemoryPressure$2$ChildProcessConnection(int arg1) {
        this.onMemoryPressureOnLauncherThread(arg1);
    }

    static final void lambda$onServiceConnectedOnLauncherThread$0$ChildProcessConnection(MemoryPressureCallback arg0) {
        MemoryPressureListener.addCallback(arg0);
    }

    static final void lambda$unbind$1$ChildProcessConnection(MemoryPressureCallback arg0) {
        MemoryPressureListener.removeCallback(arg0);
    }

    private void notifyChildProcessDied() {
        if(this.mServiceCallback != null) {
            ServiceCallback v0 = this.mServiceCallback;
            this.mServiceCallback = null;
            v0.onChildProcessDied(this);
        }
    }

    private void onMemoryPressure(int arg3) {
        this.mLauncherHandler.post(new ChildProcessConnection$$Lambda$3(this, arg3));
    }

    private void onMemoryPressureOnLauncherThread(int arg2) {
        if(this.mService == null) {
            return;
        }

        try {
            this.mService.onMemoryPressure(arg2);
            return;
        }
        catch(RemoteException ) {
            return;
        }
    }

    private void onServiceConnectedOnLauncherThread(IBinder arg5) {
        if(this.mDidOnServiceConnected) {
            return;
        }

        try {
            TraceEvent.begin("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
            this.mDidOnServiceConnected = true;
            this.mService = org.chromium.base.process_launcher.IChildProcessService$Stub.asInterface(arg5);
            if(this.mBindToCaller) {
                try {
                    if(!this.mService.bindToCaller()) {
                        if(this.mServiceCallback != null) {
                            this.mServiceCallback.onChildStartFailed(this);
                        }

                        this.unbind();
                        goto label_19;
                    }

                    goto label_32;
                }
                catch(RemoteException v5_1) {
                    try {
                        Log.e("ChildProcessConn", "Failed to bind service to connection.", new Object[]{v5_1});
                    }
                    catch(Throwable v5) {
                        goto label_53;
                    }

                    TraceEvent.end("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
                    return;
                }
            }

            goto label_32;
        }
        catch(Throwable v5) {
            goto label_53;
        }

    label_19:
        TraceEvent.end("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
        return;
        try {
        label_32:
            if(this.mServiceCallback != null) {
                this.mServiceCallback.onChildStarted();
            }

            this.mServiceConnectComplete = true;
            if(this.mMemoryPressureCallback == null) {
                ChildProcessConnection$$Lambda$0 v5_2 = new ChildProcessConnection$$Lambda$0(this);
                ThreadUtils.postOnUiThread(new ChildProcessConnection$$Lambda$1(((MemoryPressureCallback)v5_2)));
                this.mMemoryPressureCallback = ((MemoryPressureCallback)v5_2);
            }

            if(this.mConnectionParams != null) {
                this.doConnectionSetup();
            }
        }
        catch(Throwable v5) {
        label_53:
            TraceEvent.end("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
            throw v5;
        }

        TraceEvent.end("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
    }

    private void onServiceDisconnectedOnLauncherThread() {
        if(this.mServiceDisconnected) {
            return;
        }

        this.mServiceDisconnected = true;
        Log.w("ChildProcessConn", "onServiceDisconnected (crash or killed by oom): pid=%d", new Object[]{Integer.valueOf(this.mPid)});
        this.stop();
        if(this.mConnectionCallback != null) {
            this.mConnectionCallback.onConnected(null);
            this.mConnectionCallback = null;
        }
    }

    private void onSetupConnectionResult(int arg1) {
        this.mPid = arg1;
        if(this.mConnectionCallback != null) {
            this.mConnectionCallback.onConnected(this);
        }

        this.mConnectionCallback = null;
    }

    public void removeInitialBinding() {
        this.mInitialBinding.unbind();
        this.updateWaivedBoundOnlyState();
    }

    public void removeModerateBinding() {
        if(!this.isConnected()) {
            Log.w("ChildProcessConn", "The connection is not bound for %d", new Object[]{Integer.valueOf(this.getPid())});
            return;
        }

        --this.mModerateBindingCount;
        if(this.mModerateBindingCount == 0) {
            this.mModerateBinding.unbind();
            this.updateWaivedBoundOnlyState();
        }
    }

    public void removeStrongBinding() {
        if(!this.isConnected()) {
            Log.w("ChildProcessConn", "The connection is not bound for %d", new Object[]{Integer.valueOf(this.getPid())});
            return;
        }

        --this.mStrongBindingCount;
        if(this.mStrongBindingCount == 0) {
            this.mStrongBinding.unbind();
            this.updateWaivedBoundOnlyState();
        }
    }

    public void setupConnection(Bundle arg2, @Nullable List arg3, ConnectionCallback arg4) {
        if(this.mServiceDisconnected) {
            Log.w("ChildProcessConn", "Tried to setup a connection that already disconnected.", new Object[0]);
            arg4.onConnected(null);
            return;
        }

        try {
            TraceEvent.begin("ChildProcessConnection.setupConnection");
            this.mConnectionCallback = arg4;
            this.mConnectionParams = new ConnectionParams(arg2, arg3);
            if(this.mServiceConnectComplete) {
                this.doConnectionSetup();
            }
        }
        catch(Throwable v2) {
            TraceEvent.end("ChildProcessConnection.setupConnection");
            throw v2;
        }

        TraceEvent.end("ChildProcessConnection.setupConnection");
    }

    public void start(boolean arg2, ServiceCallback arg3) {
        try {
            TraceEvent.begin("ChildProcessConnection.start");
            this.mServiceCallback = arg3;
            if(!this.bind(arg2)) {
                Log.e("ChildProcessConn", "Failed to establish the service connection.", new Object[0]);
                this.notifyChildProcessDied();
            }
        }
        catch(Throwable v2) {
            TraceEvent.end("ChildProcessConnection.start");
            throw v2;
        }

        TraceEvent.end("ChildProcessConnection.start");
    }

    public void stop() {
        this.unbind();
        this.notifyChildProcessDied();
    }

    @VisibleForTesting protected void unbind() {
        IChildProcessService v0 = null;
        this.mService = v0;
        this.mConnectionParams = ((ConnectionParams)v0);
        this.mUnbound = true;
        this.mStrongBinding.unbind();
        this.mWaivedBinding.unbind();
        this.mModerateBinding.unbind();
        this.mInitialBinding.unbind();
        if(this.mMemoryPressureCallback != null) {
            ThreadUtils.postOnUiThread(new ChildProcessConnection$$Lambda$2(this.mMemoryPressureCallback));
            this.mMemoryPressureCallback = ((MemoryPressureCallback)v0);
        }
    }

    private void updateWaivedBoundOnlyState() {
        if(!this.mUnbound) {
            boolean v0 = (this.mInitialBinding.isBound()) || (this.mStrongBinding.isBound()) || (this.mModerateBinding.isBound()) ? false : true;
            this.mWaivedBoundOnly = v0;
        }
    }
}

