package org.chromium.components.minidump_uploader.util;

public interface CrashReportingPermissionManager {
    boolean isClientInMetricsSample();

    boolean isNetworkAvailableForCrashUploads();

    boolean isUploadEnabledForTests();

    boolean isUsageAndCrashReportingPermittedByUser();
}

