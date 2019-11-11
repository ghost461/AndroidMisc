package org.chromium.components.crash.browser;

import java.io.File;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

public class CrashDumpManager {
    public interface UploadMinidumpCallback {
        void tryToUploadMinidump(File arg1);
    }

    private static final String TAG = "CrashDumpManager";
    private static UploadMinidumpCallback sCallback;

    static {
    }

    public CrashDumpManager() {
        super();
    }

    static UploadMinidumpCallback access$000() {
        return CrashDumpManager.sCallback;
    }

    public static void registerUploadCallback(UploadMinidumpCallback arg0) {
        ThreadUtils.assertOnUiThread();
        CrashDumpManager.sCallback = arg0;
    }

    @CalledByNative public static void tryToUploadMinidump(String arg4) {
        ThreadUtils.assertOnBackgroundThread();
        if(arg4 == null) {
            Log.e("CrashDumpManager", "Minidump path should be non-null! Bailing...", new Object[0]);
            return;
        }

        File v1 = new File(arg4);
        if(v1.exists()) {
            if(!v1.isFile()) {
            }
            else {
                ThreadUtils.postOnUiThread(new Runnable(v1) {
                    public void run() {
                        if(CrashDumpManager.sCallback == null) {
                            Log.w("CrashDumpManager", "Ignoring crash observed before a callback was registered...", new Object[0]);
                            return;
                        }

                        CrashDumpManager.sCallback.tryToUploadMinidump(this.val$minidump);
                    }
                });
                return;
            }
        }

        Log.e("CrashDumpManager", "Minidump path \'" + arg4 + "\' should describe a file that exists! Bailing...", new Object[0]);
    }
}

