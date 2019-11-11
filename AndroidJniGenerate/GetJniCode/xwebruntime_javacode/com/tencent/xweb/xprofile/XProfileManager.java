package com.tencent.xweb.xprofile;

import android.os.Bundle;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseArray;
import com.tencent.xweb.xprofile.model.ProfileConfig;
import com.tencent.xweb.xprofile.model.TraceConfig;
import com.tencent.xweb.xprofile.processor.ManualStartProfilerProcessor;
import com.tencent.xweb.xprofile.processor.MessageProcessor;
import com.tencent.xweb.xprofile.processor.PageLoadProcessor;
import com.tencent.xweb.xprofile.processor.TracingConfigProcessor;
import com.tencent.xweb.xprofile.processor.TracingResultProcessor;
import com.tencent.xweb.xprofile.processor.WebViewHideShowProcessor;
import com.tencent.xweb.xprofile.processor.WindowPerformanceResultProcessor;
import com.tencent.xweb.xprofile.profiler.Profiler;
import com.tencent.xweb.xprofile.profiler.TracingProfiler;
import com.tencent.xweb.xprofile.profiler.WindowPerformanceProfiler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.xwalk.core.internal.Log;

public class XProfileManager implements Handler$Callback {
    class com.tencent.xweb.xprofile.XProfileManager$1 extends SparseArray {
        com.tencent.xweb.xprofile.XProfileManager$1(XProfileManager arg2) {
            XProfileManager.this = arg2;
            super();
            this.put(0, new TracingProfiler(ContextUtils.getApplicationContext()));
            this.put(1, new WindowPerformanceProfiler());
        }
    }

    class Holder {
        private static final XProfileManager INSTANCE;

        static {
            Holder.INSTANCE = new XProfileManager(null);
        }

        private Holder() {
            super();
        }

        static XProfileManager access$000() {
            return Holder.INSTANCE;
        }
    }

    private static final String TAG = "XProfileManager";
    private ProfileConfig mConfig;
    private Handler mHandler;
    private List mMessageProcessors;
    private SparseArray mProfilerMap;
    private HandlerThread mWorkThread;

    private XProfileManager() {
        super();
        this.mProfilerMap = new com.tencent.xweb.xprofile.XProfileManager$1(this);
        MessageProcessor[] v1 = new MessageProcessor[6];
        int v3 = 0;
        v1[0] = new TracingResultProcessor();
        v1[1] = new TracingConfigProcessor();
        v1[2] = new PageLoadProcessor();
        v1[3] = new WebViewHideShowProcessor();
        v1[4] = new WindowPerformanceResultProcessor();
        v1[5] = new ManualStartProfilerProcessor();
        this.mMessageProcessors = new ArrayList(Arrays.asList(((Object[])v1)));
        while(v3 < this.mProfilerMap.size()) {
            Object v0 = this.mProfilerMap.valueAt(v3);
            if(((Profiler)v0).asMessageProcessor() != null) {
                this.mMessageProcessors.add(((Profiler)v0).asMessageProcessor());
            }

            ++v3;
        }

        this.init();
    }

    XProfileManager(com.tencent.xweb.xprofile.XProfileManager$1 arg1) {
        this();
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public static XProfileManager getInstance() {
        return Holder.access$000();
    }

    public boolean handleMessage(Message arg4) {
        Iterator v0 = this.mMessageProcessors.iterator();
        boolean v1 = false;
        do {
            if(!v0.hasNext()) {
                return v1;
            }

            v1 = v0.next().handleMessage(arg4);
        }
        while(!v1);

        return v1;
    }

    private void init() {
        this.mWorkThread = new HandlerThread("XProfileWorkThread", 10);
        this.mWorkThread.start();
        this.mHandler = new Handler(this.mWorkThread.getLooper(), ((Handler$Callback)this));
    }

    public static void manualStartProfile(int arg4, Bundle arg5) {
        ProfileConfig v0 = XProfileManager.parseConfig(arg5);
        Log.d("XProfileManager", "manualStartProfile with bundle " + arg5 + " and config parsed " + v0);
        XProfileManager.getInstance().sendMessage(10, arg4, 0, v0);
    }

    public static void manualStopProfile(int arg2) {
        XProfileManager.getInstance().sendMessage(11, Integer.valueOf(arg2));
    }

    public void notifyManualStartOrStopProfiler(boolean arg2, int arg3, ProfileConfig arg4) {
        if(this.mProfilerMap != null) {
            if(arg2) {
                this.mProfilerMap.get(arg3).onManualStart(arg4);
            }
            else {
                this.mProfilerMap.get(arg3).onManualStop();
            }
        }
    }

    public void notifyPageLoadFinished(String arg3) {
        if(this.mConfig != null && this.mProfilerMap != null) {
            int v0;
            for(v0 = 0; v0 < this.mProfilerMap.size(); ++v0) {
                this.mProfilerMap.valueAt(v0).onStopLoadPage(arg3);
            }
        }
    }

    public void notifyPageLoadStarted(String arg3) {
        if(this.mConfig != null && this.mProfilerMap != null) {
            int v0;
            for(v0 = 0; v0 < this.mProfilerMap.size(); ++v0) {
                this.mProfilerMap.valueAt(v0).onStartLoadPage(arg3);
            }
        }
    }

    public void notifyWebViewInactiveInProcess() {
        if(this.mConfig != null && this.mProfilerMap != null) {
            int v0;
            for(v0 = 0; v0 < this.mProfilerMap.size(); ++v0) {
                this.mProfilerMap.valueAt(v0).onWebViewInactiveInProcess();
            }
        }
    }

    private static ProfileConfig parseConfig(Bundle arg4) {
        String v0 = arg4.getString("enabledTraceCategory");
        int v1 = arg4.getInt("traceSampleRatio");
        int v4 = arg4.getInt("enableWindowPerformanceSampleRatio");
        ProfileConfig v2 = new ProfileConfig();
        v2.traceConfig = new TraceConfig();
        v2.traceConfig.enabledCategory = v0;
        v2.traceConfig.profileSampleRatioInTenThousand = v1;
        v2.enableWindowPerformanceSampleRatio = v4;
        return v2;
    }

    public void sendMessage(int arg2, int arg3, int arg4, Object arg5) {
        this.mHandler.obtainMessage(arg2, arg3, arg4, arg5).sendToTarget();
    }

    public void sendMessage(int arg2, Object arg3) {
        this.sendMessage(arg2, 0, 0, arg3);
    }

    public void sendMessageDelayed(int arg2, Object arg3, long arg4) {
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(arg2, arg3), arg4);
    }

    public void setConfig(ProfileConfig arg3) {
        if(this.mConfig != null) {
            return;
        }

        this.mConfig = arg3;
        if(this.mProfilerMap != null) {
            int v0;
            for(v0 = 0; v0 < this.mProfilerMap.size(); ++v0) {
                this.mProfilerMap.valueAt(v0).onReceivedConfig(arg3);
            }
        }
    }

    public static void setProfileConfig(Bundle arg4) {
        ProfileConfig v0 = XProfileManager.parseConfig(arg4);
        Log.d("XProfileManager", "setProfileConfig with bundle " + arg4 + " and config parsed " + v0);
        XProfileManager.getInstance().sendMessage(2, v0);
    }
}

