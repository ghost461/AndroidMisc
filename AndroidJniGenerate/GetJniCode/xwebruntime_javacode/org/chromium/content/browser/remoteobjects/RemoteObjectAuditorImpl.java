package org.chromium.content.browser.remoteobjects;

import android.os.Process;
import android.util.EventLog;

final class RemoteObjectAuditorImpl implements Auditor {
    private static final int sObjectGetClassInvocationAttemptLogTag = 70151;

    RemoteObjectAuditorImpl() {
        super();
    }

    public void onObjectGetClassInvocationAttempt() {
        EventLog.writeEvent(70151, Process.myUid());
    }
}

