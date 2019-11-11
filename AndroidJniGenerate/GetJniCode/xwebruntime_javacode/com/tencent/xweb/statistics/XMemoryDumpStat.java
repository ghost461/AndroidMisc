package com.tencent.xweb.statistics;

import android.os.Build$VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.chromium.base.SysUtils;
import org.chromium.content.browser.MemoryDumperAndroid$MemoryDumpListener;
import org.chromium.content.browser.MemoryDumperAndroid;
import org.json.JSONObject;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.RuntimeToSdkChannel;
import org.xwalk.core.internal.reporter.XWebReporter;

public class XMemoryDumpStat {
    class Holder {
        private static final XMemoryDumpStat INSTANCE;

        static {
            Holder.INSTANCE = new XMemoryDumpStat(null);
        }

        private Holder() {
            super();
        }

        static XMemoryDumpStat access$100() {
            return Holder.INSTANCE;
        }
    }

    private static final String TAG = "XMemoryDumpStat";
    private static final int WXWEB_KV_XWEB_MEMORY_DUMP = 0x4080;
    private static final int delay = 10;
    private String mApiLevel;
    private boolean mBackgound;
    private boolean mEnable;
    private Handler mHandler;
    private int mPhysicalMemoryKB;
    private String mProcessName;
    private boolean mRunning;
    private int mSeq;
    private int mViewCount;
    private HandlerThread mWorkThread;

    private XMemoryDumpStat() {
        super();
        this.mEnable = false;
        this.mRunning = false;
        this.mSeq = 0;
        this.mBackgound = false;
        this.mViewCount = 0;
        this.mApiLevel = String.valueOf(Build$VERSION.SDK_INT);
        this.init();
    }

    XMemoryDumpStat(com.tencent.xweb.statistics.XMemoryDumpStat$1 arg1) {
        this();
    }

    public void Start() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if(!XMemoryDumpStat.this.mRunning) {
                    XMemoryDumpStat.access$608(XMemoryDumpStat.this);
                    XMemoryDumpStat.this.mRunning = true;
                    XMemoryDumpStat.this.doOnceDump(XMemoryDumpStat.this.mSeq);
                }
            }
        });
    }

    public void Stop() {
        this.mHandler.post(new Runnable() {
            public void run() {
                XMemoryDumpStat.this.mRunning = false;
            }
        });
    }

    static void access$200(XMemoryDumpStat arg0, String arg1) {
        arg0.processDumpJosn(arg1);
    }

    static Handler access$300(XMemoryDumpStat arg0) {
        return arg0.mHandler;
    }

    static void access$400(XMemoryDumpStat arg0, int arg1) {
        arg0.doOnceDump(arg1);
    }

    static boolean access$500(XMemoryDumpStat arg0) {
        return arg0.mRunning;
    }

    static boolean access$502(XMemoryDumpStat arg0, boolean arg1) {
        arg0.mRunning = arg1;
        return arg1;
    }

    static int access$600(XMemoryDumpStat arg0) {
        return arg0.mSeq;
    }

    static int access$608(XMemoryDumpStat arg2) {
        int v0 = arg2.mSeq;
        arg2.mSeq = v0 + 1;
        return v0;
    }

    static int access$700(XMemoryDumpStat arg0) {
        return arg0.mViewCount;
    }

    static int access$708(XMemoryDumpStat arg2) {
        int v0 = arg2.mViewCount;
        arg2.mViewCount = v0 + 1;
        return v0;
    }

    static int access$710(XMemoryDumpStat arg2) {
        int v0 = arg2.mViewCount;
        arg2.mViewCount = v0 - 1;
        return v0;
    }

    public void addView() {
        this.mHandler.post(new Runnable() {
            public void run() {
                XMemoryDumpStat.access$708(XMemoryDumpStat.this);
            }
        });
    }

    private void doOnceDump(int arg5) {
        if((this.mRunning) && this.mSeq == arg5 && (this.mEnable) && this.mViewCount != 0) {
            MemoryDumperAndroid.startMemoryDump(new MemoryDumpListener() {
                public void onMemoryDumpFinished(String arg3) {
                    XMemoryDumpStat.this.mHandler.post(new Runnable(arg3) {
                        public void run() {
                            this.this$1.this$0.processDumpJosn(this.val$resultJsonStr);
                        }
                    });
                }
            });
            this.mHandler.postDelayed(new Runnable(arg5) {
                public void run() {
                    XMemoryDumpStat.this.doOnceDump(this.val$seq);
                }
            }, 30000);
        }
    }

    public static XMemoryDumpStat getInstance() {
        return Holder.access$100();
    }

    private void init() {
        this.mWorkThread = new HandlerThread("XMemoryDumpStatThread", 10);
        this.mWorkThread.start();
        this.mHandler = new Handler(this.mWorkThread.getLooper());
        this.mProcessName = SysUtils.getAppName();
        this.mPhysicalMemoryKB = SysUtils.amountOfPhysicalMemoryKB();
        if(RuntimeToSdkChannel.getCmd("memorydump", null).equals("true")) {
            this.mEnable = true;
        }
    }

    public void onPause() {
        this.mHandler.post(new Runnable() {
            public void run() {
                XMemoryDumpStat.this.mRunning = false;
            }
        });
    }

    public void onResume() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if(XMemoryDumpStat.this.mViewCount > 0) {
                    XMemoryDumpStat.this.Start();
                }
            }
        });
    }

    private void processDumpJosn(String arg21) {
        XMemoryDumpStat v1 = this;
        StringBuilder v2 = new StringBuilder();
        try {
            JSONObject v3 = new JSONObject(arg21).getJSONObject("allocators");
            int v4 = Integer.parseInt(v3.getJSONObject("blink_gc").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            int v6 = Integer.parseInt(v3.getJSONObject("blink_gc/allocated_objects").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            v2.append("blinkGCSize:" + v4 / 0x400 + " KB");
            v2.append("| blinkGCallocatedObjectsSize:" + v6 / 0x400 + " KB");
            int v7_1 = Integer.parseInt(v3.getJSONObject("partition_alloc/allocated_objects").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            v2.append("| partitionAlloc:" + v7_1 / 0x400 + " KB");
            int v8_1 = Integer.parseInt(v3.getJSONObject("discardable").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            v2.append("| discardable:" + v8_1 / 0x400 + " KB");
            int v9_1 = Integer.parseInt(v3.getJSONObject("malloc").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            int v10 = Integer.parseInt(v3.getJSONObject("malloc/allocated_objects").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            v2.append("| malloc:" + v9_1 / 0x400 + " KB");
            v2.append("| mallocAllocatedObjects:" + v10 / 0x400 + " KB");
            int v11_1 = Integer.parseInt(v3.getJSONObject("java_heap").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            v2.append("| javaHeap:" + v11_1 / 0x400 + " KB");
            int v12_1 = Integer.parseInt(v3.getJSONObject("shared_memory").getJSONObject("attrs").getJSONObject("size").getString("value"), 16);
            v2.append("| shared_memory:" + v12_1 / 0x400 + " KB");
            int v13_1 = 0;
            Iterator v14 = v3.keys();
            while(v14.hasNext()) {
                Object v15 = v14.next();
                if(Pattern.compile("v8/isolate_(.+?)/heap_statistics").matcher(((CharSequence)v15)).find()) {
                    v13_1 += Integer.parseInt(v3.getJSONObject(((String)v15)).getJSONObject("attrs").getJSONObject("total_heap_size").getString("value"), 16);
                }
                else {
                }
            }

            v2.append("| v8_heap:" + v13_1 / 0x400 + " KB");
            Log.i("XMemoryDumpStat", "processDumpJosn: " + v2.toString());
            XWebReporter.setKVLog(0x4080, "" + v4 + "," + v6 + "," + v7_1 + "," + v8_1 + "," + v9_1 + "," + v10 + "," + v11_1 + "," + v1.mApiLevel + "," + v1.mPhysicalMemoryKB + "," + v1.mViewCount + "," + v1.mProcessName + "," + v13_1 + "," + v12_1);
            v13_1 = v13_1 + v4 + v6 + v7_1 + v12_1;
            Log.i("XMemoryDumpStat", "nativeTotal:" + v13_1 / 0x400 + " KB");
            XWebReporter.idkeyStat(938, 0, 1);
            XWebReporter.idkeyStat(938, ((long)(v13_1 / 0x400 / 0x400 / 25 + 1)), 1);
        }
        catch(Exception v0) {
            ThrowableExtension.printStackTrace(v0);
        }
    }

    public void relesaeView() {
        this.mHandler.post(new Runnable() {
            public void run() {
                XMemoryDumpStat.access$710(XMemoryDumpStat.this);
            }
        });
    }
}

