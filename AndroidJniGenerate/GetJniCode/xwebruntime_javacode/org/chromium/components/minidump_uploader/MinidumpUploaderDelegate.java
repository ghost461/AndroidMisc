package org.chromium.components.minidump_uploader;

import java.io.File;
import org.chromium.components.minidump_uploader.util.CrashReportingPermissionManager;

public interface MinidumpUploaderDelegate {
    CrashReportingPermissionManager createCrashReportingPermissionManager();

    File getCrashParentDir();

    void prepareToUploadMinidumps(Runnable arg1);

    void recordUploadFailure(File arg1);

    void recordUploadSuccess(File arg1);
}

