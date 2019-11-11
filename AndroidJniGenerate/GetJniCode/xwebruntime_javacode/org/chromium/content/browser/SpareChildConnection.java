package org.chromium.content.browser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.process_launcher.ChildConnectionAllocator;
import org.chromium.base.process_launcher.ChildProcessConnection$ServiceCallback;
import org.chromium.base.process_launcher.ChildProcessConnection;

public class SpareChildConnection {
    private static final String TAG = "SpareChildConn";
    private ChildProcessConnection mConnection;
    private final ChildConnectionAllocator mConnectionAllocator;
    private boolean mConnectionReady;
    private ServiceCallback mConnectionServiceCallback;

    static {
    }

    public SpareChildConnection(Context arg2, ChildConnectionAllocator arg3, Bundle arg4) {
        super();
        this.mConnectionAllocator = arg3;
        this.mConnection = this.mConnectionAllocator.allocate(arg2, arg4, new ServiceCallback() {
            public void onChildProcessDied(ChildProcessConnection arg2) {
                if(SpareChildConnection.access$100(SpareChildConnection.this) != null) {
                    SpareChildConnection.access$100(SpareChildConnection.this).onChildProcessDied(arg2);
                }

                if(SpareChildConnection.access$300(SpareChildConnection.this) != null) {
                    SpareChildConnection.access$200(SpareChildConnection.this);
                }
            }

            public void onChildStartFailed(ChildProcessConnection arg4) {
                Log.e("SpareChildConn", "Failed to warm up the spare sandbox service", new Object[0]);
                if(SpareChildConnection.access$100(SpareChildConnection.this) != null) {
                    SpareChildConnection.access$100(SpareChildConnection.this).onChildStartFailed(arg4);
                }

                SpareChildConnection.access$200(SpareChildConnection.this);
            }

            public void onChildStarted() {
                SpareChildConnection.access$002(SpareChildConnection.this, true);
                if(SpareChildConnection.access$100(SpareChildConnection.this) != null) {
                    SpareChildConnection.access$100(SpareChildConnection.this).onChildStarted();
                    SpareChildConnection.access$200(SpareChildConnection.this);
                }
            }
        });
    }

    static boolean access$002(SpareChildConnection arg0, boolean arg1) {
        arg0.mConnectionReady = arg1;
        return arg1;
    }

    static ServiceCallback access$100(SpareChildConnection arg0) {
        return arg0.mConnectionServiceCallback;
    }

    static void access$200(SpareChildConnection arg0) {
        arg0.clearConnection();
    }

    static ChildProcessConnection access$300(SpareChildConnection arg0) {
        return arg0.mConnection;
    }

    private void clearConnection() {
        this.mConnection = null;
        this.mConnectionReady = false;
    }

    @VisibleForTesting public ChildProcessConnection getConnection() {
        return this.mConnection;
    }

    public ChildProcessConnection getConnection(ChildConnectionAllocator arg2, @NonNull ServiceCallback arg3) {
        if(!this.isEmpty() && this.mConnectionAllocator == arg2) {
            if(this.mConnectionServiceCallback != null) {
            }
            else {
                this.mConnectionServiceCallback = arg3;
                ChildProcessConnection v2 = this.mConnection;
                if(this.mConnectionReady) {
                    if(arg3 != null) {
                        LauncherThread.post(new Runnable(arg3) {
                            public void run() {
                                this.val$serviceCallback.onChildStarted();
                            }
                        });
                    }

                    this.clearConnection();
                }

                return v2;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        boolean v0 = this.mConnection == null || this.mConnectionServiceCallback != null ? true : false;
        return v0;
    }
}

