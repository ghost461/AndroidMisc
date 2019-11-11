package org.chromium.net;

public abstract class UploadDataSink {
    public UploadDataSink() {
        super();
    }

    public abstract void onReadError(Exception arg1);

    public abstract void onReadSucceeded(boolean arg1);

    public abstract void onRewindError(Exception arg1);

    public abstract void onRewindSucceeded();
}

