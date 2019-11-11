package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import com.tencent.xweb.xprofile.model.WindowPerformance;
import org.chromium.base.SysUtils;
import org.xwalk.core.internal.XWalkPreferencesInternal;
import org.xwalk.core.internal.XWalkViewDelegate;
import org.xwalk.core.internal.reporter.XWebReporter;

public class WindowPerformanceResultProcessor extends MessageProcessor {
    private static final int WXWEB_KV_XWEB_WINDOW_PERFORMANCE = 0x462C;
    private static String sProcessName;
    private static String sRuntimeVersion;
    private static String sXWebSDKVersion;

    public WindowPerformanceResultProcessor() {
        super("WindowPerformanceResultProcessor");
    }

    private String getExtraKvParam() {
        if(WindowPerformanceResultProcessor.sXWebSDKVersion == null) {
            WindowPerformanceResultProcessor.sXWebSDKVersion = XWalkPreferencesInternal.getStringValue("xwebsdk-version");
        }

        if(WindowPerformanceResultProcessor.sRuntimeVersion == null) {
            WindowPerformanceResultProcessor.sRuntimeVersion = XWalkViewDelegate.sApkVer;
        }

        if(WindowPerformanceResultProcessor.sProcessName == null) {
            WindowPerformanceResultProcessor.sProcessName = SysUtils.getAppName();
        }

        return WindowPerformanceResultProcessor.sXWebSDKVersion + "," + WindowPerformanceResultProcessor.sRuntimeVersion + "," + WindowPerformanceResultProcessor.sProcessName;
    }

    private void handleAndReport(WindowPerformance arg3) {
        XWebReporter.setKVLog(0x462C, this.getExtraKvParam() + "," + arg3.toKvStr());
    }

    public boolean handleMessage(Message arg3) {
        if(arg3.what == 9) {
            this.handleAndReport(arg3.obj);
            return 1;
        }

        return super.handleMessage(arg3);
    }
}

