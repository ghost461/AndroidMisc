package org.chromium.content.browser.webcontents;

import java.io.File;
import org.chromium.content.browser.MemoryDumperAndroid$MemoryDumpListener;

final class WebContentsImpl$$Lambda$0 implements MemoryDumpListener {
    private final File arg$1;

    WebContentsImpl$$Lambda$0(File arg1) {
        super();
        this.arg$1 = arg1;
    }

    public void onMemoryDumpFinished(String arg2) {
        WebContentsImpl.lambda$savePage$0$WebContentsImpl(this.arg$1, arg2);
    }
}

