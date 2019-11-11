package org.chromium.content.browser;

import java.util.Map;
import org.chromium.base.Callback;

final class ChildProcessLauncherHelper$$Lambda$2 implements Runnable {
    private final Callback arg$1;
    private final Map arg$2;

    ChildProcessLauncherHelper$$Lambda$2(Callback arg1, Map arg2) {
        super();
        this.arg$1 = arg1;
        this.arg$2 = arg2;
    }

    public void run() {
        ChildProcessLauncherHelper.lambda$null$1$ChildProcessLauncherHelper(this.arg$1, this.arg$2);
    }
}

