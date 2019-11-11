package org.chromium.content_public.common;

public final class ContentProcessInfo {
    private static boolean sIsChildProcess;

    private ContentProcessInfo() {
        super();
    }

    public static boolean inChildProcess() {
        return ContentProcessInfo.sIsChildProcess;
    }

    public static void setInChildProcess(boolean arg0) {
        ContentProcessInfo.sIsChildProcess = arg0;
    }
}

