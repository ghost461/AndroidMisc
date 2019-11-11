package org.xwalk.core.internal.extension;

import android.app.Activity;
import java.lang.ref.WeakReference;
import org.chromium.base.ApplicationStatus$ActivityStateListener;
import org.chromium.base.ApplicationStatus;
import org.xwalk.core.internal.XWalkExtensionInternal;

public abstract class XWalkExtensionWithActivityStateListener extends XWalkExtensionInternal {
    class XWalkActivityStateListener implements ActivityStateListener {
        WeakReference mExtensionRef;

        XWalkActivityStateListener(XWalkExtensionWithActivityStateListener arg1, XWalkExtensionWithActivityStateListener arg2) {
            XWalkExtensionWithActivityStateListener.this = arg1;
            super();
            this.mExtensionRef = new WeakReference(arg2);
        }

        public void onActivityStateChange(Activity arg2, int arg3) {
            Object v0 = this.mExtensionRef.get();
            if(v0 == null) {
                return;
            }

            ((XWalkExtensionWithActivityStateListener)v0).onActivityStateChange(arg2, arg3);
        }
    }

    private XWalkActivityStateListener mActivityStateListener;

    public XWalkExtensionWithActivityStateListener(String arg1, String arg2, Activity arg3) {
        super(arg1, arg2);
        this.initActivityStateListener(arg3);
    }

    public XWalkExtensionWithActivityStateListener(String arg1, String arg2, String[] arg3, Activity arg4) {
        super(arg1, arg2, arg3);
        this.initActivityStateListener(arg4);
    }

    private void initActivityStateListener(Activity arg3) {
        this.mActivityStateListener = new XWalkActivityStateListener(this, this);
        ApplicationStatus.registerStateListenerForActivity(this.mActivityStateListener, arg3, null);
    }

    public abstract void onActivityStateChange(Activity arg1, int arg2);
}

