package com.tencent.xweb.xprofile.profiler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.xweb.xprofile.XProfileManager;
import com.tencent.xweb.xprofile.model.ProfileConfig;
import com.tencent.xweb.xprofile.model.TraceConfig;
import com.tencent.xweb.xprofile.processor.IMessageProcessor;
import com.tencent.xweb.xprofile.processor.MessageProcessor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.chromium.content.browser.MemoryTracingControllerAndroid$TracingHandler;
import org.chromium.content.browser.MemoryTracingControllerAndroid;

public class TracingProfiler extends Profiler implements TracingHandler {
    class com.tencent.xweb.xprofile.profiler.TracingProfiler$2 extends MessageProcessor {
        com.tencent.xweb.xprofile.profiler.TracingProfiler$2(TracingProfiler arg1, String arg2) {
            TracingProfiler.this = arg1;
            super(arg2);
        }

        public boolean handleMessage(Message arg3) {
            if(arg3.what == 8) {
                TracingProfiler.this.stopTracingInternal();
                return 1;
            }

            return super.handleMessage(arg3);
        }
    }

    class TracingConfigInternal {
        private Set mEnabledCategorySet;
        private int mPageCountPerTracing;
        private int mProfileSampleRatioInTenThousand;
        Random mRandom;

        TracingConfigInternal(TraceConfig arg3) {
            super();
            this.mRandom = new Random();
            this.mProfileSampleRatioInTenThousand = arg3.profileSampleRatioInTenThousand;
            this.mEnabledCategorySet = new HashSet();
            this.mEnabledCategorySet.addAll(Arrays.asList(arg3.enabledCategory.split(",")));
            this.mPageCountPerTracing = arg3.pageCountPerTracing;
        }

        String getEnabledCategoryStr(boolean arg3) {
            HashSet v0 = new HashSet(this.mEnabledCategorySet);
            if(arg3) {
                ((Set)v0).remove("xprofile.frameCost");
            }

            return TextUtils.join(",", ((Iterable)v0));
        }

        boolean isFrameCostEnabled() {
            return this.mEnabledCategorySet.contains("xprofile.frameCost");
        }

        boolean needStopTracing(int arg2) {
            boolean v2 = arg2 > this.mPageCountPerTracing ? true : false;
            return v2;
        }

        boolean shouldStartProfile() {
            boolean v0 = this.mRandom.nextInt(10000) < this.mProfileSampleRatioInTenThousand ? true : false;
            return v0;
        }

        public String toString() {
            return "TracingConfigInternal{mProfileSampleRatioInTenThousand=" + this.mProfileSampleRatioInTenThousand + ", mEnabledCategorySet=" + this.mEnabledCategorySet + ", mPageCountPerTracing=" + this.mPageCountPerTracing + '}';
        }
    }

    private static final long FRAME_COST_TRACING_LIMIT = 60000;
    private TracingConfigInternal mConfig;
    private boolean mFrameCostTracingIsRunning;
    private Handler mMainThreadHandler;
    private volatile boolean mManualProfileRunning;
    private int mPageLoadCount;
    private MessageProcessor mStopFrameCostTracingProcessor;
    private MemoryTracingControllerAndroid mTracingController;

    public TracingProfiler(Context arg3) {
        super("TracingProfiler");
        this.mStopFrameCostTracingProcessor = new com.tencent.xweb.xprofile.profiler.TracingProfiler$2(this, "mStopFrameCostTracingProcessor");
        this.mTracingController = new MemoryTracingControllerAndroid(arg3);
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mTracingController.setTracingHandler(((TracingHandler)this));
    }

    static MemoryTracingControllerAndroid access$000(TracingProfiler arg0) {
        return arg0.mTracingController;
    }

    static boolean access$102(TracingProfiler arg0, boolean arg1) {
        arg0.mManualProfileRunning = arg1;
        return arg1;
    }

    static void access$200(TracingProfiler arg0, boolean arg1) {
        arg0.startTracingInternal(arg1);
    }

    static void access$300(TracingProfiler arg0) {
        arg0.stopTracingInternal();
    }

    public IMessageProcessor asMessageProcessor() {
        return this.mStopFrameCostTracingProcessor;
    }

    final void lambda$startFrameCostTracingInternal$0$TracingProfiler() {
        this.mTracingController.startTracing(false, "xprofile.frameCost", "record-until-full");
    }

    final void lambda$startTracingInternal$1$TracingProfiler(String arg4) {
        this.mTracingController.startTracing(false, arg4, "record-until-full");
    }

    final void lambda$stopTracingInternal$2$TracingProfiler() {
        this.mTracingController.stopTracing();
    }

    public void onManualStart(ProfileConfig arg4) {
        this.onReceivedConfig(arg4);
        if(this.mTracingController.isTracing()) {
            this.logDebug("tracing is already running, finish it first");
            this.stopTracingInternal();
            Handler v4 = new Handler(Looper.myLooper());
            v4.postDelayed(new Runnable(v4) {
                public void run() {
                    if(TracingProfiler.this.mTracingController.isTracing()) {
                        this.val$handle.postDelayed(((Runnable)this), 10000);
                    }
                    else {
                        TracingProfiler.this.mManualProfileRunning = true;
                        TracingProfiler.this.startTracingInternal(false);
                    }
                }
            }, 10000);
        }
        else {
            this.mManualProfileRunning = true;
            this.startTracingInternal(false);
        }
    }

    public void onManualStop() {
        this.stopTracingInternal();
    }

    public void onReceivedConfig(ProfileConfig arg3) {
        if(arg3.traceConfig.isLegal()) {
            this.mConfig = new TracingConfigInternal(arg3.traceConfig);
            this.logDebug("config parsed: " + this.mConfig);
        }
        else {
            this.logDebug("invalid trace config received: " + arg3.traceConfig);
        }
    }

    public void onStopLoadPage(String arg6) {
        super.onStopLoadPage(arg6);
        if(this.mConfig == null) {
            return;
        }

        if(!this.mTracingController.isTracing() && ((this.mConfig.shouldStartProfile()) || (this.mFrameCostTracingIsRunning))) {
            if((this.mConfig.isFrameCostEnabled()) && !this.mFrameCostTracingIsRunning) {
                this.startFrameCostTracingInternal();
                XProfileManager.getInstance().sendMessageDelayed(8, null, 60000);
                this.mFrameCostTracingIsRunning = true;
                goto label_30;
            }

            this.mPageLoadCount = 0;
            this.mFrameCostTracingIsRunning = false;
            this.startTracingInternal(true);
        }

    label_30:
        if((this.mTracingController.isTracing()) && !this.mFrameCostTracingIsRunning) {
            ++this.mPageLoadCount;
            if(this.mConfig.needStopTracing(this.mPageLoadCount)) {
                this.stopTracingInternal();
            }
        }
    }

    public void onTracingStopped(String arg3) {
        if(this.mManualProfileRunning) {
            XProfileManager.getInstance().sendMessage(12, arg3);
            this.mManualProfileRunning = false;
        }

        XProfileManager.getInstance().sendMessage(1, arg3);
    }

    public void onWebViewInactiveInProcess() {
        this.stopTracing();
        super.onWebViewInactiveInProcess();
    }

    private void startFrameCostTracingInternal() {
        this.logDebug("startFrameCostTracingInternal");
        this.mMainThreadHandler.post(new TracingProfiler$$Lambda$0(this));
    }

    private void startTracingInternal(boolean arg3) {
        String v3 = this.mConfig.getEnabledCategoryStr(arg3);
        this.logDebug("start tracing");
        this.mMainThreadHandler.post(new TracingProfiler$$Lambda$1(this, v3));
    }

    private void stopTracing() {
        if(this.mPageLoadCount > 0) {
            this.stopTracingInternal();
        }
    }

    private void stopTracingInternal() {
        this.logDebug("stop tracing");
        this.mMainThreadHandler.post(new TracingProfiler$$Lambda$2(this));
    }
}

