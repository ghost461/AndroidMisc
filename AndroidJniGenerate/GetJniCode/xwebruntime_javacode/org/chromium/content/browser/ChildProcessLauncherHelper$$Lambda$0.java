package org.chromium.content.browser;

import android.os.Handler;
import org.chromium.base.Callback;

final class ChildProcessLauncherHelper$$Lambda$0 implements Runnable {
    private final Handler arg$1;
    private final Callback arg$2;

    ChildProcessLauncherHelper$$Lambda$0(Handler arg1, Callback arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        ChildProcessLauncherHelper.lambda$getProcessIdsByType$2$ChildProcessLauncherHelper(this.arg$1, this.arg$2);
    }
}

