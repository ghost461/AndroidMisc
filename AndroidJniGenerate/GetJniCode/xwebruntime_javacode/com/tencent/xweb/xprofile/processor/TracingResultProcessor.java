package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import com.google.gson.Gson;
import com.tencent.xweb.xprofile.model.MetaData$ChromiumTraceConfig;
import com.tencent.xweb.xprofile.model.TraceEvent;
import com.tencent.xweb.xprofile.model.TraceOutput;
import com.tencent.xweb.xprofile.model.TraceTimingResult;
import com.tencent.xweb.xprofile.util.TraceFrameCostHandler;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.chromium.base.SysUtils;
import org.xwalk.core.internal.RuntimeToSdkChannel;
import org.xwalk.core.internal.XWalkPreferencesInternal;
import org.xwalk.core.internal.XWalkViewDelegate;
import org.xwalk.core.internal.reporter.XWebReporter;

public class TracingResultProcessor extends MessageProcessor {
    private static final String FIRST_CONTENTFUL_PAINT_EVENT_NAME = "firstContentfulPaint";
    private static final String FIRST_MEANINGFUL_PAINT_EVENT_NAME = "firstMeaningfulPaint";
    private static final String PHASE_ASYNC_FINISH = "F";
    private static final String PHASE_ASYNC_START = "S";
    private static final String START_TO_COMMIT_EVENT_NAME = "Navigation StartToCommit";
    private static final int WXWEB_KV_XWEB_FRAME_COST = 0x461D;
    private static final int WXWEB_KV_XWEB_PROFILE = 0x3F49;
    private Gson mGson;
    private static String sProcessName;
    private static String sRuntimeVersion;
    private static String sXWebSDKVersion;

    public TracingResultProcessor() {
        super("TracingResultProcessor");
    }

    private String appendExtraKvParam(String arg2) {
        if(TracingResultProcessor.sXWebSDKVersion == null) {
            TracingResultProcessor.sXWebSDKVersion = XWalkPreferencesInternal.getStringValue("xwebsdk-version");
        }

        if(TracingResultProcessor.sRuntimeVersion == null) {
            TracingResultProcessor.sRuntimeVersion = XWalkViewDelegate.sApkVer;
        }

        if(TracingResultProcessor.sProcessName == null) {
            TracingResultProcessor.sProcessName = SysUtils.getAppName();
        }

        return arg2 + "," + TracingResultProcessor.sXWebSDKVersion + "," + TracingResultProcessor.sRuntimeVersion + "," + TracingResultProcessor.sProcessName;
    }

    public boolean handleMessage(Message arg5) {
        boolean v1 = false;
        int v2 = 12;
        if(arg5.what != 1) {
            if(arg5.what == v2) {
            }
            else {
                return 0;
            }
        }

        Object v0 = arg5.obj;
        if(arg5.what == v2) {
            v1 = true;
        }

        this.handleTracingResult(((String)v0), v1);
        return 1;
    }

    private void handleTracingResult(String arg4, boolean arg5) {
        if(this.mGson == null) {
            this.mGson = new Gson();
        }

        Object v0 = this.mGson.fromJson(arg4, TraceOutput.class);
        if(v0 != null) {
            if(((TraceOutput)v0).traceEvents == null) {
            }
            else {
                Object v4 = this.mGson.fromJson(((TraceOutput)v0).metadata.traceConfig, ChromiumTraceConfig.class);
                if(v4 != null) {
                    if(((ChromiumTraceConfig)v4).includedCategories.isEmpty()) {
                    }
                    else {
                        if(((ChromiumTraceConfig)v4).includedCategories.contains("xprofile.timing")) {
                            Collections.sort(((TraceOutput)v0).traceEvents);
                            this.processAndReportFirstPaintTracing(((TraceOutput)v0), arg5);
                        }

                        if(((ChromiumTraceConfig)v4).includedCategories.contains("xprofile.frameCost")) {
                            this.processAndReportFrameCostTracing(((TraceOutput)v0), arg5);
                        }

                        return;
                    }
                }

                this.logDebug("invalid chromiumTraceConfig: " + v4);
                return;
            }
        }

        this.logDebug("invalid output json: " + arg4);
    }

    private void processAndReportFirstPaintTracing(TraceOutput arg7, boolean arg8) {
        LinkedList v8 = new LinkedList();
        TraceTimingResult v0 = null;
        int v1 = 0;
        TraceTimingResult v2 = v0;
        while(v1 < arg7.traceEvents.size()) {
            Object v3 = arg7.traceEvents.get(v1);
            if(v2 == null) {
                v2 = new TraceTimingResult();
            }

            if(!((TraceEvent)v3).name.equals("Navigation StartToCommit") || !((TraceEvent)v3).phase.equals("S")) {
                if((((TraceEvent)v3).name.equals("Navigation StartToCommit")) && (((TraceEvent)v3).phase.equals("F")) && (v2.hasStart())) {
                    v2.setStartFinishedEvent(((TraceEvent)v3));
                    goto label_50;
                }

                if(((TraceEvent)v3).name.equals("firstMeaningfulPaint")) {
                    v2.setFirstMeaningful(((TraceEvent)v3));
                    if(!v2.isLegal()) {
                        goto label_50;
                    }

                    ((List)v8).add(v2);
                    v2 = v0;
                    goto label_50;
                }

                if(!((TraceEvent)v3).name.equals("firstContentfulPaint")) {
                    goto label_50;
                }

                v2.setFirstContentful(((TraceEvent)v3));
            }
            else {
                v2.setStart(((TraceEvent)v3));
            }

        label_50:
            ++v1;
        }

        this.logDebug("start report first paint tracing result: " + v8);
        this.reportFirstPaintCost(((List)v8));
    }

    private void processAndReportFrameCostTracing(TraceOutput arg3, boolean arg4) {
        TraceFrameCostHandler v0 = new TraceFrameCostHandler(arg4);
        Iterator v3 = arg3.traceEvents.iterator();
        while(v3.hasNext()) {
            v0.addTraceEvent(v3.next());
        }

        if(v0.isValid()) {
            XWebReporter.setKVLog(0x461D, this.appendExtraKvParam(v0.toKvString()));
        }

        if(arg4) {
            RuntimeToSdkChannel.invoke("KEY_XPROFILE_TRACING_FRAME_COST_RESULT", v0.getCostSequence());
        }
    }

    private void reportFirstPaintCost(List arg15) {
        Iterator v0 = arg15.iterator();
        int v1 = 0;
        while(v0.hasNext()) {
            Object v2 = v0.next();
            XWebReporter.idkeyStat(938, 200, ((TraceTimingResult)v2).getFromStartToFirstMeaningfulPaint());
            if(!((TraceTimingResult)v2).isContentfulLegal()) {
                continue;
            }

            XWebReporter.idkeyStat(938, 201, ((TraceTimingResult)v2).getFromStartToFirstContentfulPaint());
            ++v1;
            XWebReporter.setKVLog(0x3F49, this.appendExtraKvParam(((TraceTimingResult)v2).toKvString()));
        }

        XWebReporter.idkeyStat(938, 202, ((long)arg15.size()));
        XWebReporter.idkeyStat(938, 203, ((long)v1));
    }
}

