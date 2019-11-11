package org.chromium.content.browser;

import java.util.Map$Entry;
import java.util.Map;
import org.chromium.base.Callback;

final class ChildProcessLauncherHelper$$Lambda$1 implements Callback {
    private final Map arg$1;

    ChildProcessLauncherHelper$$Lambda$1(Map arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void onResult(Object arg2) {
        ChildProcessLauncherHelper.lambda$null$0$ChildProcessLauncherHelper(this.arg$1, ((Map$Entry)arg2));
    }
}

