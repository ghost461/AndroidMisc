package org.chromium.content.browser;

import android.os.Handler;
import android.os.Looper;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public class MemoryDumperAndroid {
    public interface MemoryDumpListener {
        void onMemoryDumpFinished(String arg1);
    }

    private static final String TAG = "MemoryDumperAndroid";
    private static boolean mIsStartDump;
    private static MemoryDumpListener mListener;
    private static Handler mMainLooperHandler;

    static {
        MemoryDumperAndroid.mMainLooperHandler = new Handler(Looper.getMainLooper());
    }

    private MemoryDumperAndroid() {
        super();
    }

    static final void bridge$lambda$0$MemoryDumperAndroid() {
        MemoryDumperAndroid.nativeStartMemoryDump();
    }

    private static native void nativeStartMemoryDump() {
    }

    @CalledByNative public static void onMemoryDumpFinished(String arg2) {
        Class v0 = MemoryDumperAndroid.class;
        __monitor_enter(v0);
        try {
            if(MemoryDumperAndroid.mListener != null) {
                MemoryDumperAndroid.mListener.onMemoryDumpFinished(arg2);
            }
            else {
                Log.d("MemoryDumperAndroid", arg2);
            }

            MemoryDumperAndroid.mIsStartDump = false;
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
    }

    public static boolean startMemoryDump(MemoryDumpListener arg4) {
        Class v0 = MemoryDumperAndroid.class;
        __monitor_enter(v0);
        try {
            if(MemoryDumperAndroid.mIsStartDump) {
                goto label_12;
            }

            MemoryDumperAndroid.mListener = arg4;
            MemoryDumperAndroid.mMainLooperHandler.post(MemoryDumperAndroid$$Lambda$0.$instance);
            MemoryDumperAndroid.mIsStartDump = true;
        }
        catch(Throwable v4) {
            goto label_20;
        }

        __monitor_exit(v0);
        return 1;
        try {
        label_12:
            Log.e("MemoryDumperAndroid", "try to startMemoryDump while another dump is not finished", new Object[0]);
        }
        catch(Throwable v4) {
        label_20:
            __monitor_exit(v0);
            throw v4;
        }

        __monitor_exit(v0);
        return 0;
    }
}

