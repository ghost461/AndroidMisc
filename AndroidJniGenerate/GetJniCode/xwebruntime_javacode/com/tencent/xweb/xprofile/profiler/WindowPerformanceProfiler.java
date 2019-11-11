package com.tencent.xweb.xprofile.profiler;

import com.tencent.xweb.xprofile.XProfileManager;
import com.tencent.xweb.xprofile.model.ProfileConfig;
import com.tencent.xweb.xprofile.model.WindowPerformance;
import java.util.Random;
import org.xwalk.core.internal.XWalkSettingsInternal$WindowPerformanceHandler;
import org.xwalk.core.internal.XWalkSettingsInternal;

public class WindowPerformanceProfiler extends Profiler implements WindowPerformanceHandler {
    private int mEnableRatio;

    public WindowPerformanceProfiler() {
        super("WindowPerformanceProfiler");
    }

    public void onReceivedConfig(ProfileConfig arg2) {
        this.mEnableRatio = arg2.enableWindowPerformanceSampleRatio;
        this.logDebug("mEnableRatio is " + this.mEnableRatio);
        if(new Random().nextInt(10000) < this.mEnableRatio) {
            XWalkSettingsInternal.setWindowPerformanceHandler(((WindowPerformanceHandler)this));
            XWalkSettingsInternal.setWindowPerformanceReporterEnable(true);
        }
    }

    public void onWindowPerformanceReport(WindowPerformance arg3) {
        XProfileManager.getInstance().sendMessage(9, arg3);
    }
}

