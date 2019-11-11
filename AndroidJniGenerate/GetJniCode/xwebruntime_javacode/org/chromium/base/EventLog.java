package org.chromium.base;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public class EventLog {
    public EventLog() {
        super();
    }

    @CalledByNative public static void writeEvent(int arg0, int arg1) {
        android.util.EventLog.writeEvent(arg0, arg1);
    }
}

