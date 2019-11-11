package org.chromium.net;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class UploadDataProvider implements Closeable {
    public UploadDataProvider() {
        super();
    }

    public void close() throws IOException {
    }

    public abstract long getLength() throws IOException;

    public abstract void read(UploadDataSink arg1, ByteBuffer arg2) throws IOException;

    public abstract void rewind(UploadDataSink arg1) throws IOException;
}

