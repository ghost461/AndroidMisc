package org.chromium.components.minidump_uploader;

public interface MinidumpUploader {
    public interface UploadsFinishedCallback {
        void uploadsFinished(boolean arg1);
    }

    boolean cancelUploads();

    void uploadAllMinidumps(UploadsFinishedCallback arg1);
}

