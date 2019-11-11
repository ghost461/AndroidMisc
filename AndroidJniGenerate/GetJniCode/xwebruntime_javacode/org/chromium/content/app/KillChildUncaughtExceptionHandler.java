package org.chromium.content.app;

import android.os.Process;
import org.chromium.base.BuildInfo;
import org.chromium.base.annotations.MainDex;

@MainDex class KillChildUncaughtExceptionHandler implements Thread$UncaughtExceptionHandler {
    private boolean mCrashing;

    KillChildUncaughtExceptionHandler() {
        super();
    }

    static void maybeInstallHandler() {
        if(BuildInfo.isDebugAndroid()) {
            return;
        }

        Thread.setDefaultUncaughtExceptionHandler(new KillChildUncaughtExceptionHandler());
    }

    public void uncaughtException(Thread arg1, Throwable arg2) {
        if(this.mCrashing) {
            return;
        }

        this.mCrashing = true;
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}

