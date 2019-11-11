package com.tencent.xweb.xprofile.profiler;

import com.tencent.xweb.xprofile.model.ProfileConfig;
import com.tencent.xweb.xprofile.processor.IMessageProcessor;
import org.xwalk.core.internal.Log;

public abstract class Profiler {
    private String mName;

    protected Profiler(String arg1) {
        super();
        this.mName = arg1;
    }

    public IMessageProcessor asMessageProcessor() {
        return null;
    }

    void logDebug(String arg2) {
        Log.d(this.mName, arg2);
    }

    void logError(String arg2) {
        Log.e(this.mName, arg2);
    }

    public void onManualStart(ProfileConfig arg1) {
    }

    public void onManualStop() {
    }

    public void onReceivedConfig(ProfileConfig arg1) {
    }

    public void onStartLoadPage(String arg1) {
    }

    public void onStopLoadPage(String arg1) {
    }

    public void onWebViewInactiveInProcess() {
    }
}

