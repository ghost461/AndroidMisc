package com.tencent.xweb.xprofile;

public abstract class XProfileConstants {
    public static final String CATEGORY_FIRST_PAINT_TIME = "xprofile.timing";
    public static final String CATEGORY_FRAME_COST = "xprofile.frameCost";
    public static final int MSG_MANUAL_START_PROFILER = 10;
    public static final int MSG_MANUAL_STOP_PROFILER = 11;
    public static final int MSG_MANUAL_TRACING_STOP_WITH_RES = 12;
    public static final int MSG_NOTIFY_STOP_FRAME_COST_TRACING = 8;
    public static final int MSG_NOTIFY_WEBVIEW_HIDE = 5;
    public static final int MSG_NOTIFY_WEBVIEW_INACTIVE_IN_PROCESS = 7;
    public static final int MSG_NOTIFY_WEBVIEW_SHOW = 6;
    public static final int MSG_PAGE_LOAD_FINISHED = 4;
    public static final int MSG_PAGE_LOAD_STARTED = 3;
    public static final int MSG_TRACING_CONFIG_LOADED = 2;
    public static final int MSG_TRACING_STOP_WITH_RES = 1;
    public static final int MSG_WINDOW_PERFORMANCE_RESULT_RECEIVED = 9;
    public static final int PROFILER_TYPE_TRACING = 0;
    public static final int PROFILER_TYPE_WINDOW_PERFORMANCE = 1;

    public XProfileConstants() {
        super();
    }
}

