package org.xwalk.core.internal;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;

class MixedContext extends ContextWrapper {
    private Context mActivityCtx;

    public MixedContext(Context arg1, Context arg2) {
        super(arg1);
        this.mActivityCtx = arg2;
    }

    public boolean bindService(Intent arg2, ServiceConnection arg3, int arg4) {
        return this.getApplicationContext().bindService(arg2, arg3, arg4);
    }

    public Context getApplicationContext() {
        return this.mActivityCtx.getApplicationContext();
    }

    public Object getSystemService(String arg2) {
        if(arg2.equals("layout_inflater")) {
            return super.getSystemService(arg2);
        }

        return this.mActivityCtx.getSystemService(arg2);
    }

    public void unbindService(ServiceConnection arg2) {
        this.getApplicationContext().unbindService(arg2);
    }
}

