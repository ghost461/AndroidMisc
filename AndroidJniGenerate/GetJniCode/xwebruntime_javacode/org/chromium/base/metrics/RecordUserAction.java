package org.chromium.base.metrics;

import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public class RecordUserAction {
    public interface UserActionCallback {
        @CalledByNative(value="UserActionCallback") void onActionRecorded(String arg1);
    }

    private static Throwable sDisabledBy;
    private static long sNativeActionCallback;

    static {
    }

    public RecordUserAction() {
        super();
    }

    static void access$000(String arg0) {
        RecordUserAction.nativeRecordUserAction(arg0);
    }

    private static native long nativeAddActionCallbackForTesting(UserActionCallback arg0) {
    }

    private static native void nativeRecordUserAction(String arg0) {
    }

    private static native void nativeRemoveActionCallbackForTesting(long arg0) {
    }

    public static void record(String arg1) {
        if(RecordUserAction.sDisabledBy != null) {
            return;
        }

        if(ThreadUtils.runningOnUiThread()) {
            RecordUserAction.nativeRecordUserAction(arg1);
            return;
        }

        ThreadUtils.runOnUiThread(new Runnable(arg1) {
            public void run() {
                RecordUserAction.nativeRecordUserAction(this.val$action);
            }
        });
    }

    public static void removeActionCallbackForTesting() {
        RecordUserAction.nativeRemoveActionCallbackForTesting(RecordUserAction.sNativeActionCallback);
        RecordUserAction.sNativeActionCallback = 0;
    }

    public static void setActionCallbackForTesting(UserActionCallback arg2) {
        RecordUserAction.sNativeActionCallback = RecordUserAction.nativeAddActionCallbackForTesting(arg2);
    }

    @VisibleForTesting public static void setDisabledForTests(boolean arg2) {
        if((arg2) && RecordUserAction.sDisabledBy != null) {
            throw new IllegalStateException("UserActions are already disabled.", RecordUserAction.sDisabledBy);
        }

        Throwable v2 = arg2 ? new Throwable() : null;
        RecordUserAction.sDisabledBy = v2;
    }
}

