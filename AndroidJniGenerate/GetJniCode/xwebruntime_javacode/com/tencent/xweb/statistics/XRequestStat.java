package com.tencent.xweb.statistics;

import android.os.Build$VERSION;
import android.os.Handler;
import java.util.WeakHashMap;
import org.chromium.base.SysUtils;
import org.chromium.net.GURLUtils;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.XWalkContentsClient$WebResourceErrorInner;
import org.xwalk.core.internal.XWalkContentsClient$WebResourceRequestInner;
import org.xwalk.core.internal.XWalkViewInternal;
import org.xwalk.core.internal.reporter.XWebReporter;

public class XRequestStat {
    class com.tencent.xweb.statistics.XRequestStat$1 {
    }

    class Holder {
        private static final XRequestStat INSTANCE;

        static {
            Holder.INSTANCE = new XRequestStat(null);
        }

        private Holder() {
            super();
        }

        static XRequestStat access$000() {
            return Holder.INSTANCE;
        }
    }

    public static final String APPBRAND_PRELOAD_LINK = "https://servicewechat.com/preload/page-frame.html";
    public static final int APPBRAND_SCENE = 0;
    public static final String APPBRAND_URL_PREFIX = "https://servicewechat.com/";
    public static final String MP_BRIDGE_PREFIX = "http://mp.weixinbridge.com/";
    public static final String MP_LIST_URL_HTTPS_PREFIX = "https://servicewechat.com/preload/";
    public static final int MP_SCENE = 1;
    public static final String MP_URL_HTTPS_PREFIX = "https://mp.weixin.qq.com/";
    public static final String MP_URL_PREFIX = "http://mp.weixin.qq.com/";
    public static final int OUT_LINK_SCENE = 2;
    private final String TAG;
    private static final int WXWEB_ID_KEY_CONNECTION_REFUSED = 16;
    private static final int WXWEB_ID_KEY_ERR_CONTENT_DECODING_FAILED = 0x3F;
    private static final int WXWEB_ID_KEY_MP_CONNECTION_REFUSED = 17;
    private static final int WXWEB_ID_KEY_MP_ERR_CONTENT_DECODING_FAILED = 0x40;
    private static final int WXWEB_ID_KEY_MP_PAGE_FINISH = 19;
    private static final int WXWEB_ID_KEY_MP_PAGE_NO_ERROR = 20;
    private static final int WXWEB_ID_KEY_MP_PAGE_RECV_ERROR = 21;
    private static final int WXWEB_ID_KEY_MP_PAGE_START = 18;
    private static final int WXWEB_ID_KEY_PAGE_FINISH = 12;
    private static final int WXWEB_ID_KEY_PAGE_NO_ERROR = 13;
    private static final int WXWEB_ID_KEY_PAGE_RECV_ERROR = 14;
    private static final int WXWEB_ID_KEY_PAGE_START = 11;
    private static final int WXWEB_ID_KEY_USE_NEWDNS = 22;
    private static final int WXWEB_ID_KEY_USE_NEWDNS_TYPE0 = 36;
    private static final int WXWEB_KV_XWEB_REQUEST_ERR_DUMP = 0x4174;
    private String mApiLevel;
    private boolean mBackgound;
    private Handler mHandler;
    public WeakHashMap mPageloadErrorCount;
    private int mPhysicalMemoryKB;
    private String mProcessName;
    private boolean mRunning;
    private int mSeq;
    private int mViewCount;

    private XRequestStat() {
        super();
        this.mRunning = false;
        this.mSeq = 0;
        this.mBackgound = false;
        this.mViewCount = 0;
        this.mApiLevel = String.valueOf(Build$VERSION.SDK_INT);
        this.TAG = "XRequestStat";
        this.init();
    }

    XRequestStat(com.tencent.xweb.statistics.XRequestStat$1 arg1) {
        this();
    }

    public void OnNewDns(int arg7) {
        XWebReporter.idkeyStat(938, 22, 1);
        XWebReporter.idkeyStat(938, ((long)(arg7 + 36)), 1);
    }

    public void OnPageFinished(XWalkViewInternal arg19, String arg20) {
        XRequestStat v1 = this;
        XWalkViewInternal v2 = arg19;
        String v3 = arg20;
        __monitor_enter(this);
        try {
            if(v1.mPageloadErrorCount.containsKey(v2)) {
                goto label_19;
            }

            Log.i("XRequestStat", "onPageFinished: " + v3 + " but no start");
        }
        catch(Throwable v0) {
            goto label_64;
        }

        __monitor_exit(this);
        return;
        try {
        label_19:
            if(!v3.startsWith("file")) {
                goto label_24;
            }
        }
        catch(Throwable v0) {
            goto label_64;
        }

        __monitor_exit(this);
        return;
        try {
        label_24:
            int v4_1 = v1.mPageloadErrorCount.get(v2).intValue();
            XWebReporter.idkeyStat(938, 12, 1);
            if(v4_1 == 0) {
                XWebReporter.idkeyStat(938, 13, 1);
            }
            else {
                XWebReporter.idkeyStat(938, 14, 1);
            }

            if(XRequestStat.getUrlType(arg20) == 1) {
                XWebReporter.idkeyStat(938, 19, 1);
                if(v4_1 == 0) {
                    XWebReporter.idkeyStat(938, 20, 1);
                }
                else {
                    XWebReporter.idkeyStat(938, 21, 1);
                }
            }

            v1.mPageloadErrorCount.remove(v2);
        }
        catch(Throwable v0) {
        label_64:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public void OnPageStarted(XWalkViewInternal arg9, String arg10) {
        __monitor_enter(this);
        try {
            if(!this.mPageloadErrorCount.containsKey(arg9)) {
                goto label_16;
            }

            Log.i("XRequestStat", "onPageStarted: " + arg10 + " last no finish ?");
        }
        catch(Throwable v9) {
            goto label_39;
        }

        __monitor_exit(this);
        return;
        try {
        label_16:
            if(!arg10.startsWith("file")) {
                goto label_21;
            }
        }
        catch(Throwable v9) {
            goto label_39;
        }

        __monitor_exit(this);
        return;
        try {
        label_21:
            this.mPageloadErrorCount.put(arg9, Integer.valueOf(0));
            XWebReporter.idkeyStat(938, 11, 1);
            if(XRequestStat.getUrlType(arg10) == 1) {
                XWebReporter.idkeyStat(938, 18, 1);
            }
        }
        catch(Throwable v9) {
        label_39:
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
    }

    public void OnReceivedError(XWalkViewInternal arg9, WebResourceRequestInner arg10, WebResourceErrorInner arg11) {
        __monitor_enter(this);
        try {
            StringBuilder v0 = new StringBuilder();
            v0.append(GURLUtils.getOrigin(arg10.url));
            v0.append(",");
            v0.append(arg10.isMainFrame);
            v0.append(",");
            v0.append(arg10.hasUserGesture);
            v0.append(",");
            v0.append(arg10.method);
            v0.append(",");
            v0.append(-arg11.errorCode);
            v0.append(",");
            v0.append(XRequestStat.getUrlType(arg10.url));
            v0.append(",");
            v0.append(arg11.description);
            android.util.Log.e("XRequestStat", "OnReceivedError: " + arg11.description);
            XWebReporter.setKVLog(0x4174, v0.toString());
            if(arg11.description.equals("net::ERR_CONNECTION_REFUSED")) {
                if(XRequestStat.getUrlType(arg10.url) == 1) {
                    XWebReporter.idkeyStat(938, 17, 1);
                }

                XWebReporter.idkeyStat(938, 16, 1);
            }

            if(arg11.description.equals("net::ERR_CONTENT_DECODING_FAILED")) {
                if(XRequestStat.getUrlType(arg10.url) == 1) {
                    XWebReporter.idkeyStat(938, 0x40, 1);
                }

                XWebReporter.idkeyStat(938, 0x3F, 1);
            }

            if(XRequestStat.getUrlType(arg10.url) == 1) {
                XWebReporter.idkeyStat(938, ((long)(39 - arg11.errorCode)), 1);
            }

            if(this.mPageloadErrorCount.containsKey(arg9)) {
                goto label_100;
            }

            Log.i("XRequestStat", "onPageFinished: " + arg10.url + " but no start");
        }
        catch(Throwable v9) {
            goto label_110;
        }

        __monitor_exit(this);
        return;
        try {
        label_100:
            this.mPageloadErrorCount.put(arg9, Integer.valueOf(this.mPageloadErrorCount.get(arg9).intValue() + 1));
        }
        catch(Throwable v9) {
        label_110:
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
    }

    public static XRequestStat getInstance() {
        return Holder.access$000();
    }

    public static int getUrlType(String arg1) {
        if(arg1.startsWith("https://servicewechat.com/")) {
            return 0;
        }

        if(!arg1.startsWith("http://mp.weixin.qq.com/") && !arg1.startsWith("https://mp.weixin.qq.com/") && !arg1.startsWith("https://servicewechat.com/preload/")) {
            if(arg1.startsWith("http://mp.weixinbridge.com/")) {
            }
            else {
                return 2;
            }
        }

        return 1;
    }

    private void init() {
        this.mProcessName = SysUtils.getAppName();
        this.mPhysicalMemoryKB = SysUtils.amountOfPhysicalMemoryKB();
        this.mPageloadErrorCount = new WeakHashMap();
    }
}

