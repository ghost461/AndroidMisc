package org.xwalk.core.internal.extension.api;

import android.content.Context;
import android.os.Build$VERSION;
import android.view.Display;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class XWalkDisplayManager {
    public interface DisplayListener {
        void onDisplayAdded(int arg1);

        void onDisplayChanged(int arg1);

        void onDisplayRemoved(int arg1);
    }

    private static Context mContext;
    private static XWalkDisplayManager mInstance;
    protected final ArrayList mListeners;

    static {
    }

    public XWalkDisplayManager() {
        super();
        this.mListeners = new ArrayList();
    }

    public abstract Display getDisplay(int arg1);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String arg1);

    public static XWalkDisplayManager getInstance(Context arg1) {
        if(XWalkDisplayManager.mContext != null) {
        }
        else {
            XWalkDisplayManager.mContext = arg1.getApplicationContext();
        }

        if(XWalkDisplayManager.mInstance == null) {
            XWalkDisplayManager.mInstance = Build$VERSION.SDK_INT >= 17 ? new DisplayManagerJBMR1(XWalkDisplayManager.mContext) : new DisplayManagerNull();
        }

        return XWalkDisplayManager.mInstance;
    }

    public abstract Display[] getPresentationDisplays();

    protected void notifyDisplayAdded(int arg3) {
        Iterator v0 = this.mListeners.iterator();
        while(v0.hasNext()) {
            v0.next().onDisplayAdded(arg3);
        }
    }

    protected void notifyDisplayChanged(int arg3) {
        Iterator v0 = this.mListeners.iterator();
        while(v0.hasNext()) {
            v0.next().onDisplayChanged(arg3);
        }
    }

    protected void notifyDisplayRemoved(int arg3) {
        Iterator v0 = this.mListeners.iterator();
        while(v0.hasNext()) {
            v0.next().onDisplayRemoved(arg3);
        }
    }

    public void registerDisplayListener(DisplayListener arg2) {
        this.mListeners.add(arg2);
    }

    public void unregisterDisplayListener(DisplayListener arg2) {
        this.mListeners.remove(arg2);
    }
}

