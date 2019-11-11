package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import com.tencent.xweb.xprofile.XProfileManager;

public class ManualStartProfilerProcessor extends MessageProcessor {
    public ManualStartProfilerProcessor() {
        super("ManualStartProfilerProcessor");
    }

    public boolean handleMessage(Message arg4) {
        if(arg4.what == 10) {
            XProfileManager.getInstance().notifyManualStartOrStopProfiler(true, arg4.arg1, arg4.obj);
            return 1;
        }

        if(arg4.what == 11) {
            XProfileManager.getInstance().notifyManualStartOrStopProfiler(false, 0, null);
            return 1;
        }

        return super.handleMessage(arg4);
    }
}

