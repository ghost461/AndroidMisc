package org.chromium.content.app;

import org.chromium.base.process_launcher.ChildProcessService;

public class ContentChildProcessService extends ChildProcessService {
    public ContentChildProcessService() {
        super(new ContentChildProcessServiceDelegate());
    }
}

