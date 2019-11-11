package org.chromium.net;

import android.annotation.SuppressLint;
import java.nio.ByteBuffer;

public abstract class BidirectionalStream {
    public abstract class Builder {
        public static final int STREAM_PRIORITY_HIGHEST = 4;
        public static final int STREAM_PRIORITY_IDLE = 0;
        public static final int STREAM_PRIORITY_LOW = 2;
        public static final int STREAM_PRIORITY_LOWEST = 1;
        public static final int STREAM_PRIORITY_MEDIUM = 3;

        public Builder() {
            super();
        }

        public abstract Builder addHeader(String arg1, String arg2);

        @SuppressLint(value={"WrongConstant"}) public abstract BidirectionalStream build();

        public abstract Builder delayRequestHeadersUntilFirstFlush(boolean arg1);

        public abstract Builder setHttpMethod(String arg1);

        public abstract Builder setPriority(int arg1);
    }

    public abstract class Callback {
        public Callback() {
            super();
        }

        public void onCanceled(BidirectionalStream arg1, UrlResponseInfo arg2) {
        }

        public abstract void onFailed(BidirectionalStream arg1, UrlResponseInfo arg2, CronetException arg3);

        public abstract void onReadCompleted(BidirectionalStream arg1, UrlResponseInfo arg2, ByteBuffer arg3, boolean arg4);

        public abstract void onResponseHeadersReceived(BidirectionalStream arg1, UrlResponseInfo arg2);

        public void onResponseTrailersReceived(BidirectionalStream arg1, UrlResponseInfo arg2, HeaderBlock arg3) {
        }

        public abstract void onStreamReady(BidirectionalStream arg1);

        public abstract void onSucceeded(BidirectionalStream arg1, UrlResponseInfo arg2);

        public abstract void onWriteCompleted(BidirectionalStream arg1, UrlResponseInfo arg2, ByteBuffer arg3, boolean arg4);
    }

    public BidirectionalStream() {
        super();
    }

    public abstract void cancel();

    public abstract void flush();

    public abstract boolean isDone();

    public abstract void read(ByteBuffer arg1);

    public abstract void start();

    public abstract void write(ByteBuffer arg1, boolean arg2);
}

