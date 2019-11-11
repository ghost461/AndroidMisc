package org.chromium.mojo.system.impl;

import android.util.Log;
import org.chromium.mojo.system.Core$HandleSignalsState;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.UntypedHandle;

abstract class HandleBase implements Handle {
    private static final String TAG = "HandleImpl";
    protected CoreImpl mCore;
    private int mMojoHandle;

    HandleBase(CoreImpl arg1, int arg2) {
        super();
        this.mCore = arg1;
        this.mMojoHandle = arg2;
    }

    protected HandleBase(HandleBase arg3) {
        super();
        this.mCore = arg3.mCore;
        int v0 = arg3.mMojoHandle;
        arg3.mMojoHandle = 0;
        this.mMojoHandle = v0;
    }

    public void close() {
        if(this.mMojoHandle != 0) {
            int v0 = this.mMojoHandle;
            this.mMojoHandle = 0;
            this.mCore.close(v0);
        }
    }

    protected final void finalize() throws Throwable {
        if(this.isValid()) {
            Log.w("HandleImpl", "Handle was not closed.");
            this.mCore.closeWithResult(this.mMojoHandle);
        }

        super.finalize();
    }

    public Core getCore() {
        return this.mCore;
    }

    int getMojoHandle() {
        return this.mMojoHandle;
    }

    void invalidateHandle() {
        this.mMojoHandle = 0;
    }

    public boolean isValid() {
        boolean v0 = this.mMojoHandle != 0 ? true : false;
        return v0;
    }

    public HandleSignalsState querySignalsState() {
        return this.mCore.queryHandleSignalsState(this.mMojoHandle);
    }

    public int releaseNativeHandle() {
        int v0 = this.mMojoHandle;
        this.mMojoHandle = 0;
        return v0;
    }

    public UntypedHandle toUntypedHandle() {
        return new UntypedHandleImpl(this);
    }
}

